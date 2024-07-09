
package mod.gottsch.forge.magic_treasures.core.loot.modifier;

import com.google.gson.JsonObject;
import mod.gottsch.forge.gottschcore.enums.IRarity;
import mod.gottsch.forge.gottschcore.random.RandomHelper;
import mod.gottsch.forge.magic_treasures.api.MagicTreasuresApi;
import mod.gottsch.forge.magic_treasures.core.config.Config;
import mod.gottsch.forge.magic_treasures.core.item.MagicTreasuresItems;
import mod.gottsch.forge.magic_treasures.core.item.SpellScroll;
import mod.gottsch.forge.magic_treasures.core.rarity.MagicTreasuresRarity;
import mod.gottsch.forge.magic_treasures.core.registry.JewelryRegistry;
import mod.gottsch.forge.magic_treasures.core.registry.StoneRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

/**
 * 
 * @author Mark Gottschling Jun 12, 2023
 *
 */
public class LootModifierByRarity extends LootModifier {

	// the number of items to add
	private final int count;
	private final IRarity rarity;
	private final double chance;

	protected LootModifierByRarity(LootItemCondition[] conditionsIn, int count, IRarity rarity, double chance) {
		super(conditionsIn);
		this.count = count;
		this.rarity = rarity;
		this.chance = chance;
	}

	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {

		if (Config.SERVER.loot.enableVanillaLootModifiers.get() && RandomHelper.checkProbability(context.getLevel().getRandom(), chance * 100)) {
			List<Item> lootList = JewelryRegistry.get(rarity);
			lootList.addAll(StoneRegistry.get(rarity));
			lootList.addAll(
					MagicTreasuresItems.ALL_SPELL_SCROLLS.stream()
							.map(RegistryObject::get)
							.filter(scroll -> ((SpellScroll)scroll).getSpell().getRarity() == rarity)
							.toList()
			);
			// add some recipes
			if (rarity.getCode() > MagicTreasuresRarity.UNCOMMON.getCode()) {
				lootList.add(MagicTreasuresItems.RING_RECIPE.get());
				lootList.add(MagicTreasuresItems.BRACELET_RECIPE.get());
				lootList.add(MagicTreasuresItems.NECKLACE_RECIPE.get());
//				lootList.add(MagicTreasuresItems.BELT_RECIPE.get());
			}
			// grab the loot from the loot list
			for (int index = 0; index < Math.min(count, lootList.size()); index++) {
				ItemStack outputStack = new ItemStack(lootList.get(index));
				generatedLoot.add(outputStack);
			}
		}
		return generatedLoot;
	}

	/*
	 * 
	 */
	public static class Serializer extends GlobalLootModifierSerializer<LootModifierByRarity> {

		@Override
		public LootModifierByRarity read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions) {
			int count = GsonHelper.getAsInt(object, "count");
			String rarityStr = GsonHelper.getAsString(object, "rarity");
			IRarity rarity = MagicTreasuresApi.getRarity(rarityStr).orElse(MagicTreasuresRarity.COMMON);
			double chance = GsonHelper.getAsDouble(object, "chance");
			
			return new LootModifierByRarity(conditions, count, rarity, chance);
		}

		@Override
		public JsonObject write(LootModifierByRarity instance) {
			JsonObject json = makeConditions(instance.conditions);
			json.addProperty("count", Integer.valueOf(instance.count));
			json.addProperty("rarity", instance.rarity.getName());
			json.addProperty("chance", Double.valueOf(instance.chance));
			return json;
		}

	}
}
