/*
 * This file is part of  Magic Treasures.
 * Copyright (c) 2023 Mark Gottschling (gottsch)
 *
 * Magic Treasures is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Magic Treasures is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Magic Treasures.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
package mod.gottsch.forge.magic_treasures.core.loot.modifier;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mod.gottsch.forge.gottschcore.enums.IRarity;
import mod.gottsch.forge.gottschcore.random.RandomHelper;
import mod.gottsch.forge.magic_treasures.api.MagicTreasuresApi;
import mod.gottsch.forge.magic_treasures.core.config.Config;
import mod.gottsch.forge.magic_treasures.core.item.MagicTreasuresItems;
import mod.gottsch.forge.magic_treasures.core.item.SpellScroll;
import mod.gottsch.forge.magic_treasures.core.rarity.MagicTreasuresRarity;
import mod.gottsch.forge.magic_treasures.core.registry.JewelryRegistry;
import mod.gottsch.forge.magic_treasures.core.registry.StoneRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

/**
 * 
 * @author Mark Gottschling Jun 12, 2023
 *
 */
public class LootModifierByRarity extends LootModifier {
	// the number of items to add
	private final int count;
	private final String rarity;
	private final double chance;

	public static final Supplier<Codec<LootModifierByRarity>> CODEC = Suppliers.memoize(()
			-> RecordCodecBuilder.create(inst -> codecStart(inst)
			.and(Codec.INT.fieldOf("count").forGetter(m -> m.count))
			.and(Codec.STRING.fieldOf("rarity").forGetter(m -> m.rarity))
			.and(Codec.DOUBLE.fieldOf("chance").forGetter(m -> m.chance))
			.apply(inst, LootModifierByRarity::new)));


	protected LootModifierByRarity(LootItemCondition[] conditionsIn, int count, String rarity, double chance) {
		super(conditionsIn);
		this.count = count;
		this.rarity = rarity;
		this.chance = chance;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC.get();
	}

	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if (Config.SERVER.loot.enableVanillaLootModifiers.get() && RandomHelper.checkProbability(context.getLevel().getRandom(), chance * 100)) {
			IRarity rarity = MagicTreasuresApi.getRarity(this.rarity).orElse(MagicTreasuresRarity.NONE);
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
//	public static class Serializer extends GlobalLootModifierSerializer<LootModifierByRarity> {
//
//		@Override
//		public LootModifierByRarity read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions) {
//			int count = GsonHelper.getAsInt(object, "count");
//			String rarityStr = GsonHelper.getAsString(object, "rarity");
//			IRarity rarity = MagicTreasuresApi.getRarity(rarityStr).orElse(MagicTreasuresRarity.COMMON);
//			double chance = GsonHelper.getAsDouble(object, "chance");
//
//			return new LootModifierByRarity(conditions, count, rarity, chance);
//		}
//
//		@Override
//		public JsonObject write(LootModifierByRarity instance) {
//			JsonObject json = makeConditions(instance.conditions);
//			json.addProperty("count", Integer.valueOf(instance.count));
//			json.addProperty("rarity", instance.rarity.getName());
//			json.addProperty("chance", Double.valueOf(instance.chance));
//			return json;
//		}
//
//	}
}
