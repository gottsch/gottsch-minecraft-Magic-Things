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

import java.util.Map;

import com.google.common.collect.Maps;

import mod.gottsch.forge.gealdorcraft.GealdorCraft;
import mod.gottsch.forge.gealdorcraft.core.capability.GealdorCapabilities;
import mod.gottsch.forge.gealdorcraft.core.item.GealdorCraftItems;
import mod.gottsch.forge.gealdorcraft.core.item.IJewelryMaterialTier;
import mod.gottsch.forge.gealdorcraft.core.item.IJewelrySizeTier;
import mod.gottsch.forge.gealdorcraft.core.item.IJewelryType;
import mod.gottsch.forge.gealdorcraft.core.item.JewelryMaterialTier;
import mod.gottsch.forge.gealdorcraft.core.item.JewelrySizeTier;
import mod.gottsch.forge.gealdorcraft.core.item.JewelryStoneTier;
import mod.gottsch.forge.gealdorcraft.core.item.JewelryType;
import mod.gottsch.forge.gealdorcraft.core.setup.Registration;
import mod.gottsch.forge.gealdorcraft.core.tag.GealdorCraftTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Created by Mark Gottschling on 5/29/2023
 */
public class GealdorCraftItemTagsProvider extends ItemTagsProvider {
	/**
	 * 
	 * @param dataGenerator
	 * @param blockTagProvider
	 * @param existingFileHelper
	 */
	public GealdorCraftItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(dataGenerator, blockTagProvider, GealdorCraft.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		/*
		 * maps to map from attribute to tag
		 */
		Map<IJewelryType, TagKey<Item>> TYPE_TAG_MAP = Maps.newHashMap();
		TYPE_TAG_MAP.put(JewelryType.RING, GealdorCraftTags.Items.RINGS);
		TYPE_TAG_MAP.put(JewelryType.BRACELET, GealdorCraftTags.Items.BRACELETS);
//		TYPE_TAG_MAP.put(JewelryType.BROACH, GealdorCraftTags.Items.BROACHES);
		TYPE_TAG_MAP.put(JewelryType.CHARM, GealdorCraftTags.Items.CHARMS);
//		TYPE_TAG_MAP.put(JewelryType.EARRING, GealdorCraftTags.Items.EARRINGS);
		TYPE_TAG_MAP.put(JewelryType.NECKLACE, GealdorCraftTags.Items.NECKLACES);
		TYPE_TAG_MAP.put(JewelryType.POCKET, GealdorCraftTags.Items.POCKETS);
		
		Map<Item, TagKey<Item>> STONE_TAG_MAP = Maps.newHashMap();
		STONE_TAG_MAP.put(GealdorCraftItems.TOPAZ.get(), GealdorCraftTags.Items.TOPAZ);
		STONE_TAG_MAP.put(GealdorCraftItems.ONYX.get(), GealdorCraftTags.Items.ONYX);
		STONE_TAG_MAP.put(Items.DIAMOND, GealdorCraftTags.Items.DIAMOND);
		STONE_TAG_MAP.put(Items.EMERALD, GealdorCraftTags.Items.EMERALD);
		STONE_TAG_MAP.put(GealdorCraftItems.RUBY.get(), GealdorCraftTags.Items.RUBY);
		STONE_TAG_MAP.put(GealdorCraftItems.SAPPHIRE.get(), GealdorCraftTags.Items.SAPPHIRE);
		STONE_TAG_MAP.put(GealdorCraftItems.WHITE_PEARL.get(), GealdorCraftTags.Items.WHITE_PEARL);
		STONE_TAG_MAP.put(GealdorCraftItems.BLACK_PEARL.get(), GealdorCraftTags.Items.BLACK_PEARL);
		
		Map<IJewelryMaterialTier, TagKey<Item>> MATERIAL_TAG_MAP = Maps.newHashMap();
		MATERIAL_TAG_MAP.put(JewelryMaterialTier.WOOD, GealdorCraftTags.Items.WOOD);
		MATERIAL_TAG_MAP.put(JewelryMaterialTier.IRON, GealdorCraftTags.Items.IRON);
		MATERIAL_TAG_MAP.put(JewelryMaterialTier.COPPER, GealdorCraftTags.Items.COPPER);
		MATERIAL_TAG_MAP.put(JewelryMaterialTier.SILVER, GealdorCraftTags.Items.SILVER);
		MATERIAL_TAG_MAP.put(JewelryMaterialTier.GOLD, GealdorCraftTags.Items.GOLD);
		MATERIAL_TAG_MAP.put(JewelryMaterialTier.BLOOD, GealdorCraftTags.Items.BLOOD);
		MATERIAL_TAG_MAP.put(JewelryMaterialTier.BONE, GealdorCraftTags.Items.BONE);
		MATERIAL_TAG_MAP.put(JewelryMaterialTier.SHADOW, GealdorCraftTags.Items.SHADOW);
		MATERIAL_TAG_MAP.put(JewelryMaterialTier.ATIUM, GealdorCraftTags.Items.ATIUM);
		
		Map<IJewelrySizeTier, TagKey<Item>> SIZE_TAG_MAP = Maps.newHashMap();
		SIZE_TAG_MAP.put(JewelrySizeTier.REGULAR, GealdorCraftTags.Items.REGULAR);
		SIZE_TAG_MAP.put(JewelrySizeTier.GREAT, GealdorCraftTags.Items.GREAT);
		SIZE_TAG_MAP.put(JewelrySizeTier.LORDS, GealdorCraftTags.Items.LORDS);
		
		/*
		 *  process all items.
		 *  if item has Jewelry capability, then categorize it into the different tags
		 */
		Registration.ITEMS.getEntries().forEach(item -> {
			ItemStack stack = new ItemStack(item.get());
			stack.getCapability(GealdorCapabilities.JEWELRY_CAPABILITY).ifPresent(c -> {
				// add to the type tag
				tag(TYPE_TAG_MAP.get(c.getJewelryType())).add(item.get());
				// add to the material tag
				tag(MATERIAL_TAG_MAP.get(c.getJewelryMaterialTier())).add(item.get());
				tag(SIZE_TAG_MAP.get(c.getJewelrySizeTier())).add(item.get());
				
				// add to the stone tag
				if (!c.getStones().isEmpty()) {
					c.getStones().forEach(stone -> {
						tag(STONE_TAG_MAP.get(ForgeRegistries.ITEMS.getValue(stone))).add(item.get());	
					});
				}
			});
		});
		
		// rings - TODO merge with the above code block
//		tag(GealdorCraftTags.Items.RINGS).add(GealdorCraftItems.COPPER_RING.get());
//		tag(GealdorCraftTags.Items.RINGS).add(GealdorCraftItems.TOPAZ_COPPER_RING.get());
//		tag(GealdorCraftTags.Items.RINGS).add(GealdorCraftItems.GREAT_ONYX_COPPER_RING.get());
		
		// stone tiers
		tag(GealdorCraftTags.Items.STONE_TIER1).add(GealdorCraftItems.TOPAZ.get());
		tag(GealdorCraftTags.Items.STONE_TIER2).add(GealdorCraftItems.ONYX.get());
		tag(GealdorCraftTags.Items.STONE_TIER3).add(Items.DIAMOND);
		tag(GealdorCraftTags.Items.STONE_TIER4).add(Items.EMERALD);
		tag(GealdorCraftTags.Items.STONE_TIER5).add(GealdorCraftItems.RUBY.get());
		tag(GealdorCraftTags.Items.STONE_TIER6).add(GealdorCraftItems.SAPPHIRE.get());
		tag(GealdorCraftTags.Items.STONE_TIER5).add(GealdorCraftItems.WHITE_PEARL.get());
		tag(GealdorCraftTags.Items.STONE_TIER6).add(GealdorCraftItems.BLACK_PEARL.get());

		
		//        List<Adornment> adornments = GealdorAdornmentRegistry.getByType(AdornmentType.RING);
		//        adornments.forEach(ring -> {
		//        	tag(GealdorCraftTags.Items.RING).add(ring);
		//        });
		//        
		//        adornments = GealdorAdornmentRegistry.getByType(AdornmentType.NECKLACE);
		//        adornments.forEach(necklace -> {
		//        	tag(GealdorCraftTags.Items.NECKLACE).add(necklace);
		//        });
		//        
		//        adornments = GealdorAdornmentRegistry.getByType(AdornmentType.BRACELET);
		//        adornments.forEach(bracelet -> {
		//        	tag(GealdorCraftTags.Items.BRACELET).add(bracelet);
		//        });
		//        
		//        // special adornments
		//        tag(GealdorCraftTags.Items.RING).add(GealdorItems.ANGELS_RING.get());
		//        tag(GealdorCraftTags.Items.RING).add(GealdorItems.RING_OF_FORTITUDE.get());
		//        tag(GealdorCraftTags.Items.RING).add(GealdorItems.SHADOWS_GIFT.get());
		//        tag(GealdorCraftTags.Items.RING).add(GealdorItems.RING_OF_LIFE_DEATH.get());
		//        tag(GealdorCraftTags.Items.RING).add(GealdorItems.CASTLE_RING.get());
		//        tag(GealdorCraftTags.Items.RING).add(GealdorItems.PEASANTS_FORTUNE.get());
		//        tag(GealdorCraftTags.Items.RING).add(GealdorItems.GOTTSCHS_RING_OF_MOON.get());
		//        tag(GealdorCraftTags.Items.NECKLACE).add(GealdorItems.GOTTSCHS_AMULET_OF_HEAVENS.get());
		//        tag(GealdorCraftTags.Items.BRACELET).add(GealdorItems.BRACELET_OF_WONDER.get());
		//        tag(GealdorCraftTags.Items.NECKLACE).add(GealdorItems.MEDICS_TOKEN.get());
		//        tag(GealdorCraftTags.Items.BRACELET).add(GealdorItems.ADEPHAGIAS_BOUNTY.get());
		//        tag(GealdorCraftTags.Items.NECKLACE).add(GealdorItems.SALANDAARS_WARD.get());
		//        tag(GealdorCraftTags.Items.BRACELET).add(GealdorItems.MIRTHAS_TORCH.get());

		// pocket watch
		//        tag(GealdorCraftTags.Items.CHARM).add(GealdorItems.POCKET_WATCH.get());

	}
}
