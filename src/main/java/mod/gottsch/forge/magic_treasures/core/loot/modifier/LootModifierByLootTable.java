
package mod.gottsch.forge.magic_treasures.core.loot.modifier;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mod.gottsch.forge.gottschcore.enums.IRarity;
import mod.gottsch.forge.gottschcore.random.RandomHelper;
import mod.gottsch.forge.magic_treasures.api.MagicTreasuresApi;
import mod.gottsch.forge.magic_treasures.core.config.Config;
import mod.gottsch.forge.magic_treasures.core.rarity.MagicTreasuresRarity;
import mod.gottsch.forge.magic_treasures.core.util.ModUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import java.util.List;
import java.util.function.Supplier;

/**
 * 
 * @author Mark Gottschling May 15, 2024
 *
 */
public class LootModifierByLootTable extends LootModifier {

	// the number of items to add
	private final int count;
	private final String rarity;
	private final double chance;
	private final String lootTable;

	public static final Supplier<Codec<LootModifierByLootTable>> CODEC = Suppliers.memoize(()
			-> RecordCodecBuilder.create(inst -> codecStart(inst)
			.and(Codec.INT.fieldOf("count").forGetter(m -> m.count))
			.and(Codec.STRING.fieldOf("rarity").forGetter(m -> m.rarity))
			.and(Codec.DOUBLE.fieldOf("chance").forGetter(m -> m.chance))
			.and(Codec.STRING.fieldOf("lootTable").forGetter(m -> m.lootTable))
			.apply(inst, LootModifierByLootTable::new)));

	protected LootModifierByLootTable(LootItemCondition[] conditionsIn, int count, String rarity, double chance, String lootTable) {
		super(conditionsIn);
		this.count = count;
		this.rarity = rarity;
		this.chance = chance;
		this.lootTable = lootTable;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC.get();
	}

	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if (Config.SERVER.loot.enableVanillaLootModifiers.get() && RandomHelper.checkProbability(context.getLevel().getRandom(), chance * 100)) {
			IRarity rarity = MagicTreasuresApi.getRarity(this.rarity).orElse(MagicTreasuresRarity.NONE);
			ResourceLocation lootTable = ModUtil.asLocation(this.lootTable);

			// get the loot table
			LootTable table = context.getLevel().getServer().getLootTables().get(lootTable);

			List<ItemStack> tempLoot = table.getRandomItems(context);
			// grab the loot from the loot list
			for (int index = 0; index < Math.min(count, tempLoot.size()); index++) {
				ItemStack outputStack = tempLoot.get(index);
				generatedLoot.add(outputStack);
			}
		}
		return generatedLoot;
	}

//	/*
//	 *
//	 */
//	public static class Serializer extends GlobalLootModifierSerializer<LootModifierByLootTable> {
//
//		@Override
//		public LootModifierByLootTable read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions) {
//			int count = GsonHelper.getAsInt(object, "count");
//			String rarityStr = GsonHelper.getAsString(object, "rarity");
//			IRarity rarity = MagicTreasuresApi.getRarity(rarityStr).orElse(MagicTreasuresRarity.COMMON);
//			double chance = GsonHelper.getAsDouble(object, "chance");
//			String lootTableStr = GsonHelper.getAsString(object, "lootTable");
//
//			return new LootModifierByLootTable(conditions, count, rarity, chance,
//					ModUtil.asLocation(lootTableStr));
//		}
//
//		@Override
//		public JsonObject write(LootModifierByLootTable instance) {
//			JsonObject json = makeConditions(instance.conditions);
//			json.addProperty("count", Integer.valueOf(instance.count));
//			json.addProperty("rarity", instance.rarity.getName());
//			json.addProperty("chance", Double.valueOf(instance.chance));
//			json.addProperty("lootTable", instance.lootTable.toString());
//			return json;
//		}
//
//	}
}
