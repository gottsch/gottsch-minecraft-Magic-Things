/*
 * This file is part of  GealdorCraft.
 * Copyright (c) 2023 Mark Gottschling (gottsch)
 *
 * GealdorCraft is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GealdorCraft is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GealdorCraft.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
package mod.gottsch.forge.magic_things.datagen;

import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.capability.MagicThingsCapabilities;
import mod.gottsch.forge.magic_things.core.jewelry.JewelrySizeTier;
import mod.gottsch.forge.magic_things.core.item.MagicThingsItems;
import mod.gottsch.forge.magic_things.core.spell.SpellRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * Created by Mark Gottschling on 6/1/2023
 */
public class ItemModelsProvider extends ItemModelProvider {

	public ItemModelsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, MagicThings.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		// tabs
//		singleTexture(TreasureItems.TREASURE_TAB.get().getRegistryName().getPath(),
//				mcLoc("item/generated"), "layer0", modLoc("item/treasure_tab"));

		// tools
		singleTexture(MagicThingsItems.JEWELRY_PLIERS.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jewelry_pliers"));

		// ingots
		singleTexture(MagicThingsItems.SILVER_INGOT.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/silver_ingot"));

		// recipes
		singleTexture(MagicThingsItems.RING_RECIPE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/ring_recipe_scroll"));
		singleTexture(MagicThingsItems.NECKLACE_RECIPE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/necklace_recipe_scroll"));
		singleTexture(MagicThingsItems.BRACELET_RECIPE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/bracelet_recipe_scroll"));

		// recharge scroll
		singleTexture(MagicThingsItems.RECHARGE_SCROLL.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/recharge_scroll"));

		/*
		 * jewelry
		 */
		// stones
		singleTexture(MagicThingsItems.JADEITE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gem/jadeite"));
		singleTexture(MagicThingsItems.TOPAZ.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gem/topaz"));
		singleTexture(MagicThingsItems.ONYX.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gem/onyx"));
		singleTexture(MagicThingsItems.RUBY.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gem/ruby"));
		singleTexture(MagicThingsItems.SAPPHIRE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gem/sapphire"));
		singleTexture(MagicThingsItems.WHITE_PEARL.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gem/white_pearl"));
		singleTexture(MagicThingsItems.BLACK_PEARL.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gem/black_pearl"));
		
		// TODO this method does not distinguish between regular and special jewelry ie Gottsch Ring of the Moon.
		MagicThingsItems.STANDARD_JEWELRY.forEach(item -> {
			singleTexture(item.getId().getPath(),
					modLoc("item/jewelry"), "layer0", modLoc("item/jewelry/" + item.getId().getPath()));
		});

		// scrolls
		SpellRegistry.values().forEach(spell -> {
			singleTexture(spell.getName().getPath() + "_scroll",
					mcLoc("item/generated"), "layer0", modLoc(
							switch(spell.getLevel()) {
								case 1,2 -> "item/yellow_spell_scroll";
								case 3,4 -> "item/green_spell_scroll";
								case 5,6 -> "item/blue_spell_scroll";
								case 7,8 -> "item/red_spell_scroll";
                                default -> "item/black_spell_scroll";
                            }));
		});

		// special / non-standard jewelry
		singleTexture(MagicThingsItems.PEASANTS_FORTUNE.getId().getPath()
				, modLoc("item/jewelry"), "layer0", modLoc("item/jewelry/great_iron_ring"));

		singleTexture(MagicThingsItems.MEDICS_TOKEN.getId().getPath(), modLoc("item/jewelry"), "layer0", modLoc("item/jewelry/medics_token"));
		singleTexture(MagicThingsItems.ADEPHAGIAS_BOUNTY.getId().getPath(), modLoc("item/jewelry"), "layer0", modLoc("item/jewelry/adephagias_bounty"));

		singleTexture(MagicThingsItems.ANGELS_RING.getId().getPath()
				, modLoc("item/jewelry"), "layer0", modLoc("item/jewelry/angels_ring"));

		singleTexture(MagicThingsItems.SALANDAARS_WARD.getId().getPath()
				, modLoc("item/jewelry"), "layer0", modLoc("item/jewelry/salandaars_ward"));

		singleTexture(MagicThingsItems.RING_OF_FORTITUDE.getId().getPath()
				, modLoc("item/jewelry"), "layer0", modLoc("item/jewelry/ring_of_fortitude"));

		singleTexture(MagicThingsItems.EYE_OF_THE_PHOENIX.getId().getPath()
				, modLoc("item/jewelry"), "layer0", modLoc("item/jewelry/eye_of_the_phoenix"));

		singleTexture(MagicThingsItems.RING_LIFE_DEATH.getId().getPath()
				, modLoc("item/jewelry"), "layer0", modLoc("item/jewelry/ring_of_life_death"));

		// belts
		singleTexture(MagicThingsItems.SKULL_BELT.getId().getPath()
				, mcLoc("item/generated"), "layer0", modLoc("item/skull_belt"));

		/*
		 * blocks
		 */
		withExistingParent(MagicThingsItems.TOPAZ_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/topaz_ore"));
		withExistingParent(MagicThingsItems.ONYX_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/onyx_ore"));
		withExistingParent(MagicThingsItems.JADEITE_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/jadeite_ore"));
		withExistingParent(MagicThingsItems.RUBY_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/ruby_ore"));
		withExistingParent(MagicThingsItems.SAPPHIRE_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/sapphire_ore"));
		withExistingParent(MagicThingsItems.SILVER_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/silver_ore"));

		withExistingParent(MagicThingsItems.DEEPSLATE_TOPAZ_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/deepslate_topaz_ore"));
		withExistingParent(MagicThingsItems.DEEPSLATE_ONYX_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/deepslate_onyx_ore"));
		withExistingParent(MagicThingsItems.DEEPSLATE_JADEITE_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/deepslate_jadeite_ore"));
		withExistingParent(MagicThingsItems.DEEPSLATE_RUBY_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/deepslate_ruby_ore"));
		withExistingParent(MagicThingsItems.DEEPSLATE_SAPPHIRE_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/deepslate_sapphire_ore"));
		withExistingParent(MagicThingsItems.DEEPSLATE_SILVER_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/deepslate_silver_ore"));
	}
	
	@Deprecated
	private ResourceLocation buildLoc(Item item) {
		StringBuilder loc = new StringBuilder("items/jewelry/");
		
		ItemStack stack = new ItemStack(item);
		stack.getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).ifPresent(c -> {
		if (c.getJewelrySizeTier() != JewelrySizeTier.REGULAR) {
			loc .append(c.getJewelrySizeTier().getValue() + "_");
		}
		
		// stone (can't get from key....)

			if (c.hasStone()) {
				loc.append(c.getStone().getPath()).append("_");
			}
			
			// material
			loc.append(c.getMaterial().getName() + "_");
			
			// type
			loc.append(c.getJewelryType().getValue());
			
		});		
		return mcLoc(loc.toString());
	}

}
