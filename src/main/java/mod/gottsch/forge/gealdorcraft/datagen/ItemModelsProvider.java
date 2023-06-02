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
package mod.gottsch.forge.gealdorcraft.datagen;

import mod.gottsch.forge.gealdorcraft.core.GealdorCraft;
import mod.gottsch.forge.gealdorcraft.core.item.GealdorCraftItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * Created by Mark Gottschling on 6/1/2023
 */
public class ItemModelsProvider extends ItemModelProvider {

	public ItemModelsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, GealdorCraft.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		// tabs
//		singleTexture(TreasureItems.TREASURE_TAB.get().getRegistryName().getPath(),
//				mcLoc("item/generated"), "layer0", modLoc("item/treasure_tab"));
//
//		singleTexture(TreasureItems.ADORNMENTS_TAB.get().getRegistryName().getPath(),
//				mcLoc("item/generated"), "layer0", modLoc("item/adornment/ruby_gold_ring"));

		// keys
		singleTexture(GealdorCraftItems.COPPER_RING.get().getRegistryName().getPath(),
				modLoc("item/copper_ring"), "layer0", modLoc("item/jewelry/copper_ring"));

		// block items
//		withExistingParent(TreasureItems.WOOD_CHEST_ITEM.get().getRegistryName().getPath(), modLoc("block/wood_chest"));

		// topaz rings
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("topaz_iron_ring")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/topaz_iron_ring"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("topaz_copper_ring")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/topaz_copper_ring"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("topaz_silver_ring")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/topaz_silver_ring"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("topaz_gold_ring")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/topaz_gold_ring"));
		//        
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_iron_ring")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_iron_ring"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_copper_ring")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_copper_ring"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_silver_ring")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_silver_ring"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_gold_ring")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_gold_ring"));
		//        
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_blood_ring")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_blood_ring"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_black_ring")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_black_ring"));
		//        
		//        // topaz necklaces
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("topaz_iron_necklace")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/topaz_iron_necklace"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("topaz_copper_necklace")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/topaz_copper_necklace"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("topaz_silver_necklace")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/topaz_silver_necklace"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("topaz_gold_necklace")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/topaz_gold_necklace"));
		//        
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_iron_necklace")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_iron_necklace"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_copper_necklace")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_copper_necklace"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_silver_necklace")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_silver_necklace"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_gold_necklace")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_gold_necklace"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_blood_necklace")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_blood_necklace"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_black_necklace")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_black_necklace"));
		//        
		//        // topaz bracelets
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("topaz_iron_bracelet")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/topaz_iron_bracelet"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("topaz_copper_bracelet")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/topaz_copper_bracelet"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("topaz_silver_bracelet")).getRegistryName().getPath(),
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/topaz_silver_bracelet"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("topaz_gold_bracelet")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/topaz_gold_bracelet"));
		//        
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_iron_bracelet")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_iron_bracelet"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_copper_bracelet")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_copper_bracelet"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_silver_bracelet")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_silver_bracelet"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_gold_bracelet")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_gold_bracelet"));
		//        
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_blood_bracelet")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_blood_bracelet"));
		//        singleTexture(TreasureItems.ADORNMENT_ITEMS.get(modLoc("great_topaz_black_bracelet")).getRegistryName().getPath(),        		
		//        		mcLoc("treasure2:item/adornment"), "layer0", modLoc("item/adornments/great_topaz_black_bracelet"));
	}
}
