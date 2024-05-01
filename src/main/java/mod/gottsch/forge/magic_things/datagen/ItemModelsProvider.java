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
import mod.gottsch.forge.magic_things.core.item.MagicThingsItems;
import mod.gottsch.forge.magic_things.core.item.JewelrySizeTier;
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
//
//		singleTexture(TreasureItems.ADORNMENTS_TAB.get().getRegistryName().getPath(),
//				mcLoc("item/generated"), "layer0", modLoc("item/adornment/ruby_gold_ring"));

		/*
		 * jewelry
		 */
		
		// rings
//		singleTexture(GealdorCraftItems.COPPER_RING.getId().getPath(),
//				mcLoc("item/generated"), "layer0", modLoc("items/jewelry/copper_ring"));
		
		// stones
		singleTexture(MagicThingsItems.JADEITE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gem/jadeite"));
		singleTexture(MagicThingsItems.TOPAZ.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gem/topaz"));
		singleTexture(MagicThingsItems.ONYX.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gem/onyx"));
		singleTexture(MagicThingsItems.RUBY.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gem/ruby"));
		singleTexture(MagicThingsItems.SAPPHIRE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gem/sapphire"));
		singleTexture(MagicThingsItems.WHITE_PEARL.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gem/white_pearl"));
		singleTexture(MagicThingsItems.BLACK_PEARL.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gem/black_pearl"));
		
		// TODO this method does not distinguish between regular and special jewelry ie Gottsch Ring of the Moon.
		MagicThingsItems.ALL_JEWELRY.forEach(item -> {
			singleTexture(item.getId().getPath(),
					mcLoc("item/generated"), "layer0", modLoc("item/jewelry/" + item.getId().getPath()));
		});
		// special / non-standard jewelry
		singleTexture(MagicThingsItems.HEALING_RING.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jewelry/copper_ring"));

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

			if (!c.getStones().isEmpty()) {
				loc.append(c.getStones().get(0).getPath() + "_");
			}
			
			// material
			loc.append(c.getJewelryMaterialTier().getValue() + "_");
			
			// type
			loc.append(c.getJewelryType().getValue());
			
		});		
		return mcLoc(loc.toString());
	}

}
