
package mod.gottsch.forge.magic_things.core.loot.function;

import com.google.gson.*;
import mod.gottsch.forge.gottschcore.enums.IRarity;
import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.api.MagicThingsApi;
import mod.gottsch.forge.magic_things.core.item.MagicThingsItems;
import mod.gottsch.forge.magic_things.core.loot.MagicThingsLootFunctions;
import mod.gottsch.forge.magic_things.core.rarity.MagicThingsRarity;
import mod.gottsch.forge.magic_things.core.registry.StoneRegistry;
import mod.gottsch.forge.magic_things.core.spell.ISpell;
import mod.gottsch.forge.magic_things.core.spell.MagicThingsSpells;
import mod.gottsch.forge.magic_things.core.spell.SpellRegistry;
import mod.gottsch.forge.magic_things.core.util.ModUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 *
 * @author Mark Gottschling on May 21, 2024
 *
 */
public class RandomGemstone extends LootItemConditionalFunction {
//	private static final ResourceLocation LOCATION = new ResourceLocation("gealdorcraft:random_gemstone");
	private static final String RARITY = "rarity";
	private static final String RARITIES = "rarities";
	private static final String GEMSTONES = "gemstones";
	private static final String COUNT = "count";

	private final NumberProvider count;
	private final IRarity rarity;
	private final List<IRarity> rarities;
	private final List<ResourceLocation> gemstones;


	/**
	 *
	 * @param conditions
	 * @param count
	 * @param rarity
	 */
	public RandomGemstone(LootItemCondition[] conditions, NumberProvider count,
						  IRarity rarity, List<IRarity> rarities, List<ResourceLocation> gemstones) {
		super(conditions);
		this.count = count;
		this.rarity = rarity;
		this.rarities = rarities;
		this.gemstones = gemstones;
	}

	@Override
	public LootItemFunctionType getType() {
		return MagicThingsLootFunctions.RANDOM_SPELL;
	}

	@Override
	public ItemStack run(ItemStack stack, LootContext context) {
		Random random = new Random();
		Optional<Item> stone = Optional.empty();

		MagicThings.LOGGER.debug("incoming stack -> {}", stack.getDisplayName());
		MagicThings.LOGGER.debug("rarity -> {}", rarity);

		List<Item> stones = new ArrayList<>();
		if (rarity != null) {
			// get all the gemstones by rarity
			stones.addAll(StoneRegistry.get(rarity));
			stone = selectStone(stones);
		} else if (!rarities.isEmpty()) {
			rarities.forEach(rarity -> {
				stones.addAll(StoneRegistry.get(rarity));
			});
			stone = selectStone(stones);
		} else if (!gemstones.isEmpty()) {
			stone = Optional.ofNullable(ForgeRegistries.ITEMS.getValue(gemstones.get(random.nextInt(gemstones.size()))));
		}

		// select random count
		int count = this.count == null ? 1 : this.count.getInt(context);
		MagicThings.LOGGER.debug("selected count -> {}", count);

        return stone.map(s -> new ItemStack(s, count)).orElse(stack);
    }

	public Optional<Item> selectStone(List<Item> stones) {
		if (!stones.isEmpty()) {
			return Optional.ofNullable(stones.get(new Random().nextInt(stones.size())));
		}
		return Optional.empty();
	}

	/*
	 *
	 */
	public static class Serializer extends LootItemConditionalFunction.Serializer<RandomGemstone> {
		public void serialize(JsonObject json, RandomGemstone value, JsonSerializationContext context) {
			super.serialize(json, value, context);
			json.add(COUNT, context.serialize(value.count));
			json.addProperty(RARITY, value.rarity.getName());
			if (!value.rarities.isEmpty()) {
				final JsonArray jsonArray = new JsonArray();
				value.rarities.forEach(r -> {
					jsonArray.add(new JsonPrimitive(r.getName().toString()));
				});
				json.add(RARITIES, jsonArray);
			}
		}

		public RandomGemstone deserialize(JsonObject jsonObject, JsonDeserializationContext context, LootItemCondition[] conditions) {
			NumberProvider numberProvider = null;
			if (jsonObject.has(COUNT)) {
				numberProvider = GsonHelper.getAsObject(jsonObject, COUNT, context, NumberProvider.class);
			}

			String rarityStr = "";
			if (jsonObject.has(RARITY)) {
				rarityStr = GsonHelper.getAsString(jsonObject, RARITY);
			}
			IRarity rarity = MagicThingsApi.getRarity(rarityStr).orElse(MagicThingsRarity.COMMON);

			List<IRarity> rarities = new ArrayList<>();
			if (jsonObject.has(RARITIES)) {
				GsonHelper.getAsJsonArray(jsonObject, RARITIES).forEach(element -> {
					String rarityName = GsonHelper.convertToString(element, RARITY);
					IRarity r = MagicThingsApi.getRarity(rarityName).orElse(MagicThingsRarity.NONE);
					if (r != MagicThingsRarity.NONE) {
						rarities.add(r);
					}
				});
			}

			List<ResourceLocation> gemstones = new ArrayList<>();
			if (jsonObject.has(GEMSTONES)) {
				GsonHelper.getAsJsonArray(jsonObject, GEMSTONES).forEach(element -> {
					ResourceLocation gemstone = ModUtil.asLocation(GsonHelper.convertToString(element, "gemstone"));
					// check if valid item
					Optional.ofNullable(ForgeRegistries.ITEMS.getValue(gemstone)).ifPresent(g -> {
						gemstones.add(gemstone);
					});
				});
			}
			return new RandomGemstone(conditions, numberProvider, rarity, rarities, gemstones);
		}
	}
}