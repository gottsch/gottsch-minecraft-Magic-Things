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

import com.google.common.collect.Maps;
import mod.gottsch.forge.gottschcore.enums.IRarity;
import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.capability.MagicThingsCapabilities;
import mod.gottsch.forge.magic_things.core.item.*;
import mod.gottsch.forge.magic_things.core.jewelry.JewelryMaterial;
import mod.gottsch.forge.magic_things.core.jewelry.JewelryMaterials;
import mod.gottsch.forge.magic_things.core.jewelry.JewelryStoneHandler;
import mod.gottsch.forge.magic_things.core.rarity.MagicThingsRarity;
import mod.gottsch.forge.magic_things.core.registry.StoneRegistry;
import mod.gottsch.forge.magic_things.core.setup.Registration;
import mod.gottsch.forge.magic_things.core.tag.MagicThingsTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.Optional;

/**
 * Created by Mark Gottschling on 5/29/2023
 */
public class MagicThingsItemTagsProvider extends ItemTagsProvider {
	/**
	 * 
	 * @param dataGenerator
	 * @param blockTagProvider
	 * @param existingFileHelper
	 */
	public MagicThingsItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(dataGenerator, blockTagProvider, MagicThings.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {

		// TODO add Silver Ingot to forge "ore registry" tag


		/*
		 * map from jewelry type attribute to tag
		 */
		Map<IJewelryType, TagKey<Item>> TYPE_TAG_MAP = Maps.newHashMap();
		TYPE_TAG_MAP.put(JewelryType.RING, MagicThingsTags.Items.RINGS);
		TYPE_TAG_MAP.put(JewelryType.BRACELET, MagicThingsTags.Items.BRACELETS);
//		TYPE_TAG_MAP.put(JewelryType.BROACH, GealdorCraftTags.Items.BROACHES);
		TYPE_TAG_MAP.put(JewelryType.CHARM, MagicThingsTags.Items.CHARMS);
//		TYPE_TAG_MAP.put(JewelryType.EARRING, GealdorCraftTags.Items.EARRINGS);
		TYPE_TAG_MAP.put(JewelryType.NECKLACE, MagicThingsTags.Items.NECKLACES);
		TYPE_TAG_MAP.put(JewelryType.POCKET, MagicThingsTags.Items.POCKETS);

		/*
		 * map from jewelry type attribute to curios tag
		 */
		Map<IJewelryType, TagKey<Item>> CURIOS_TYPE_TAG_MAP = Maps.newHashMap();
		CURIOS_TYPE_TAG_MAP.put(JewelryType.RING, MagicThingsTags.Items.CURIOUS_RING);
		CURIOS_TYPE_TAG_MAP.put(JewelryType.BRACELET, MagicThingsTags.Items.CURIOUS_BRACELET);
		CURIOS_TYPE_TAG_MAP.put(JewelryType.NECKLACE, MagicThingsTags.Items.CURIOUS_NECKLACE);

//		Map<Item, TagKey<Item>> STONE_TAG_MAP = Maps.newHashMap();
//		STONE_TAG_MAP.put(MagicThingsItems.TOPAZ.get(), MagicThingsTags.Items.TOPAZ);
//		STONE_TAG_MAP.put(MagicThingsItems.ONYX.get(), MagicThingsTags.Items.ONYX);
//		STONE_TAG_MAP.put(Items.DIAMOND, MagicThingsTags.Items.DIAMOND);
////		STONE_TAG_MAP.put(Items.EMERALD, GealdorCraftTags.Items.EMERALD);
//		STONE_TAG_MAP.put(MagicThingsItems.JADEITE.get(), MagicThingsTags.Items.JADEITE);
//		STONE_TAG_MAP.put(MagicThingsItems.RUBY.get(), MagicThingsTags.Items.RUBY);
//		STONE_TAG_MAP.put(MagicThingsItems.SAPPHIRE.get(), MagicThingsTags.Items.SAPPHIRE);
//		STONE_TAG_MAP.put(MagicThingsItems.WHITE_PEARL.get(), MagicThingsTags.Items.WHITE_PEARL);
//		STONE_TAG_MAP.put(MagicThingsItems.BLACK_PEARL.get(), MagicThingsTags.Items.BLACK_PEARL);
//
		Map<JewelryMaterial, TagKey<Item>> MATERIAL_TAG_MAP = Maps.newHashMap();
		MATERIAL_TAG_MAP.put(JewelryMaterials.WOOD, MagicThingsTags.Items.WOOD);
		MATERIAL_TAG_MAP.put(JewelryMaterials.IRON, MagicThingsTags.Items.IRON);
		MATERIAL_TAG_MAP.put(JewelryMaterials.COPPER, MagicThingsTags.Items.COPPER);
		MATERIAL_TAG_MAP.put(JewelryMaterials.SILVER, MagicThingsTags.Items.SILVER);
		MATERIAL_TAG_MAP.put(JewelryMaterials.GOLD, MagicThingsTags.Items.GOLD);
		MATERIAL_TAG_MAP.put(JewelryMaterials.BLOOD, MagicThingsTags.Items.BLOOD);
		MATERIAL_TAG_MAP.put(JewelryMaterials.BONE, MagicThingsTags.Items.BONE);
		MATERIAL_TAG_MAP.put(JewelryMaterials.SHADOW, MagicThingsTags.Items.SHADOW);
		MATERIAL_TAG_MAP.put(JewelryMaterials.ATIUM, MagicThingsTags.Items.ATIUM);
		
		Map<IJewelrySizeTier, TagKey<Item>> SIZE_TAG_MAP = Maps.newHashMap();
		SIZE_TAG_MAP.put(JewelrySizeTier.REGULAR, MagicThingsTags.Items.REGULAR);
		SIZE_TAG_MAP.put(JewelrySizeTier.GREAT, MagicThingsTags.Items.GREAT);
		SIZE_TAG_MAP.put(JewelrySizeTier.LORDS, MagicThingsTags.Items.LORDS);

		Map<IRarity, TagKey<Item>> JEWELRY_RARITY_TAG_MAP = Maps.newHashMap();
		JEWELRY_RARITY_TAG_MAP.put(MagicThingsRarity.COMMON, MagicThingsTags.Items.JEWELRY_COMMON);
		JEWELRY_RARITY_TAG_MAP.put(MagicThingsRarity.UNCOMMON, MagicThingsTags.Items.JEWELRY_UNCOMMON);
		JEWELRY_RARITY_TAG_MAP.put(MagicThingsRarity.SCARCE, MagicThingsTags.Items.JEWELRY_SCARCE);
		JEWELRY_RARITY_TAG_MAP.put(MagicThingsRarity.RARE, MagicThingsTags.Items.JEWELRY_RARE);
		JEWELRY_RARITY_TAG_MAP.put(MagicThingsRarity.EPIC, MagicThingsTags.Items.JEWELRY_EPIC);

		// default stone rarity mapping
		Map<Item, IRarity> STONE_RARITY_MAP = Maps.newHashMap();
		STONE_RARITY_MAP.put(MagicThingsItems.TOPAZ.get(), MagicThingsRarity.COMMON);
		STONE_RARITY_MAP.put(MagicThingsItems.ONYX.get(), MagicThingsRarity.UNCOMMON);
		STONE_RARITY_MAP.put(MagicThingsItems.JADEITE.get(), MagicThingsRarity.SCARCE);
		STONE_RARITY_MAP.put(Items.DIAMOND, MagicThingsRarity.SCARCE);
		STONE_RARITY_MAP.put(MagicThingsItems.RUBY.get(), MagicThingsRarity.RARE);
		STONE_RARITY_MAP.put(MagicThingsItems.SAPPHIRE.get(), MagicThingsRarity.EPIC);
		STONE_RARITY_MAP.put(MagicThingsItems.WHITE_PEARL.get(), MagicThingsRarity.RARE);
		STONE_RARITY_MAP.put(MagicThingsItems.BLACK_PEARL.get(), MagicThingsRarity.EPIC);

		// default material rarity mapping
		Map<JewelryMaterial, IRarity> MATERIAL_RARITY_MAP = Maps.newHashMap();
		MATERIAL_RARITY_MAP.put(JewelryMaterials.WOOD, MagicThingsRarity.COMMON);
		MATERIAL_RARITY_MAP.put(JewelryMaterials.IRON, MagicThingsRarity.COMMON);
		MATERIAL_RARITY_MAP.put(JewelryMaterials.COPPER, MagicThingsRarity.UNCOMMON);
		MATERIAL_RARITY_MAP.put(JewelryMaterials.SILVER, MagicThingsRarity.SCARCE);
		MATERIAL_RARITY_MAP.put(JewelryMaterials.GOLD, MagicThingsRarity.RARE);
		MATERIAL_RARITY_MAP.put(JewelryMaterials.BONE, MagicThingsRarity.SCARCE);

		/*
		 *  process all items.
		 *  if item has Jewelry capability, then categorize it into the different tags
		 */
		Registration.ITEMS.getEntries().forEach(registryItem -> {
			ItemStack stack = new ItemStack(registryItem.get());
			stack.getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).ifPresent(handler -> {
				// add to the type tag
				tag(TYPE_TAG_MAP.get(handler.getJewelryType())).add(registryItem.get());
				// -- curios integration
				TagKey<Item> curiosTagKey = CURIOS_TYPE_TAG_MAP.get(handler.getJewelryType());
				if (curiosTagKey != null) {
					tag(curiosTagKey).add(registryItem.get());
				}
				// add to the material tag
				tag(MATERIAL_TAG_MAP.get(handler.getMaterial())).add(registryItem.get());
				tag(SIZE_TAG_MAP.get(handler.getJewelrySizeTier())).add(registryItem.get());
				
				// add to the stone tag
//				if (c.hasStone()) {
//					Item stoneItem = ForgeRegistries.ITEMS.getValue(c.getStone());
//					tag(STONE_TAG_MAP.get(stoneItem)).add(item.get());
//				}

				//
				//
				/*
				 ********************************
				 calculate rarity by examining the stone and/ore material
				 ********************************
				 NOTE stone rarity takes precedence over material
				 NOTE stoneHandler, stoneTier, material do not contain any data related to Rarity.
				 Rarities are registered via tags and so, tags wouldn't be populated here yet.
				 Will have to setup hard-coded maps for the default setup that map from stone,
				 material to a rarity.
				 Ex.
				 	map.put(wood, common)
				 	...
				 	map.put(gold, rare)

				 	map2.put(topaz, common)
				 	...
				 	map2.put(ruby, rare)
				 */

				// only run this for standard jewelry (special is handled explicitly)
				if (!MagicThingsItems.STANDARD_JEWELRY.contains(registryItem)) return;

				IRarity materialRarity = MATERIAL_RARITY_MAP.get(handler.getMaterial());
				IRarity rarity = materialRarity;
				if (handler.getStone() != null && !handler.getStone().equals(Items.AIR.getRegistryName())) {
//				if (handler.hasStone()) { // can't use hasStone here as it uses tag which aren't loaded yet
					Item stoneItem = ForgeRegistries.ITEMS.getValue(handler.getStone());
					IRarity stoneRarity = STONE_RARITY_MAP.get(stoneItem);

					if (stoneRarity != null) {
						rarity = MagicThingsRarity.getByCode(Math.max(stoneRarity.getCode(), materialRarity.getCode()));

						if (handler.getJewelrySizeTier() != JewelrySizeTier.REGULAR && rarity.getCode() < 2) {
							rarity = MagicThingsRarity.getByCode(rarity.getCode() + 1);
						}
					} else {
						// check the material
						if (handler.getJewelrySizeTier() != JewelrySizeTier.REGULAR && materialRarity.getCode() < 2) {
							rarity = MagicThingsRarity.getByCode(rarity.getCode() + 1);
						}
					}
					tag(JEWELRY_RARITY_TAG_MAP.get(rarity)).add(registryItem.get());
				} else {
					if (handler.getJewelrySizeTier() != JewelrySizeTier.REGULAR && materialRarity.getCode() < 2) {
						rarity = MagicThingsRarity.getByCode(rarity.getCode() + 1);
					}
					tag(JEWELRY_RARITY_TAG_MAP.get(rarity)).add(registryItem.get());
				}
			});
		});

		// explicitly categorize special jewelry
//		tag(MagicThingsTags.Items.JEWELRY_RARE).add();

		tag(MagicThingsTags.Items.JEWELRY).addTags(MagicThingsTags.Items.RINGS);
		tag(MagicThingsTags.Items.JEWELRY).addTags(MagicThingsTags.Items.NECKLACES);
		tag(MagicThingsTags.Items.JEWELRY).addTags(MagicThingsTags.Items.BRACELETS);
//		tag(MagicThingsTags.Items.JEWELRY).addTags(MagicThingsTags.Items.POCKETS);
//		tag(MagicThingsTags.Items.JEWELRY).addTags(MagicThingsTags.Items.CHARMS);
		
		// rings - TODO merge with the above code block
//		tag(GealdorCraftTags.Items.RINGS).add(GealdorCraftItems.COPPER_RING.get());
//		tag(GealdorCraftTags.Items.RINGS).add(GealdorCraftItems.TOPAZ_COPPER_RING.get());
//		tag(GealdorCraftTags.Items.RINGS).add(GealdorCraftItems.GREAT_ONYX_COPPER_RING.get());
		
		// stone tiers
		tag(MagicThingsTags.Items.STONE_TIER1).add(MagicThingsItems.TOPAZ.get());
		tag(MagicThingsTags.Items.STONE_TIER2).add(MagicThingsItems.ONYX.get());
		tag(MagicThingsTags.Items.STONE_TIER3).add(Items.DIAMOND);
//		tag(MagicThingsTags.Items.STONE_TIER4).add(Items.EMERALD);
		tag(MagicThingsTags.Items.STONE_TIER4).add(MagicThingsItems.JADEITE.get());
		tag(MagicThingsTags.Items.STONE_TIER5).add(MagicThingsItems.RUBY.get());
		tag(MagicThingsTags.Items.STONE_TIER6).add(MagicThingsItems.SAPPHIRE.get());
		tag(MagicThingsTags.Items.STONE_TIER5).add(MagicThingsItems.WHITE_PEARL.get());
		tag(MagicThingsTags.Items.STONE_TIER6).add(MagicThingsItems.BLACK_PEARL.get());

		tag(MagicThingsTags.Items.STONE_RARITY_COMMON).add(MagicThingsItems.TOPAZ.get());
		tag(MagicThingsTags.Items.STONE_RARITY_UNCOMMON).add(MagicThingsItems.ONYX.get());
		tag(MagicThingsTags.Items.STONE_RARITY_SCARCE).add(Items.DIAMOND);
		tag(MagicThingsTags.Items.STONE_RARITY_SCARCE).add(MagicThingsItems.JADEITE.get());
		tag(MagicThingsTags.Items.STONE_RARITY_RARE).add(MagicThingsItems.RUBY.get());
		tag(MagicThingsTags.Items.STONE_RARITY_EPIC).add(MagicThingsItems.SAPPHIRE.get());
		tag(MagicThingsTags.Items.STONE_RARITY_RARE).add(MagicThingsItems.WHITE_PEARL.get());
		tag(MagicThingsTags.Items.STONE_RARITY_EPIC).add(MagicThingsItems.BLACK_PEARL.get());

		// TODO need to add a different way because if one tier doesn't have items, it won't be generated
		// and this will fail. AND probably should list out each explicitly as tier and stones server
		// different purposes.
		tag(MagicThingsTags.Items.STONES).addTags(
				MagicThingsTags.Items.STONE_TIER1,
				MagicThingsTags.Items.STONE_TIER2,
				MagicThingsTags.Items.STONE_TIER3,
				MagicThingsTags.Items.STONE_TIER4,
				MagicThingsTags.Items.STONE_TIER5,
				MagicThingsTags.Items.STONE_TIER6);

		Registration.ITEMS.getEntries().forEach(o -> {
			if (o.get() instanceof SpellScroll) {
				tag(MagicThingsTags.Items.SPELL_SCROLLS).add(o.get());
			}
		});
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
