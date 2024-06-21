/*
 * This file is part of  Magic Things.
 * Copyright (c) 2023 Mark Gottschling (gottsch)
 *
 * Magic Things is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Magic Things is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Magic Things.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
package mod.gottsch.forge.magic_things.datagen;

import com.google.common.collect.Maps;
import mod.gottsch.forge.gottschcore.enums.IRarity;
import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.capability.MagicThingsCapabilities;
import mod.gottsch.forge.magic_things.core.item.*;
import mod.gottsch.forge.magic_things.core.jewelry.JewelryMaterial;
import mod.gottsch.forge.magic_things.core.jewelry.JewelryMaterials;
import mod.gottsch.forge.magic_things.core.jewelry.JewelrySizeTier;
import mod.gottsch.forge.magic_things.core.rarity.MagicThingsRarity;
import mod.gottsch.forge.magic_things.core.setup.Registration;
import mod.gottsch.forge.magic_things.core.tag.MagicThingsTags;
import mod.gottsch.forge.treasure2.core.item.TreasureItems;
import mod.gottsch.forge.treasure2.core.tags.TreasureTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

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
//		TYPE_TAG_MAP.put(JewelryType.CHARM, MagicThingsTags.Items.CHARMS);
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
		JEWELRY_RARITY_TAG_MAP.put(MagicThingsRarity.LEGENDARY, MagicThingsTags.Items.JEWELRY_LEGENDARY);
		JEWELRY_RARITY_TAG_MAP.put(MagicThingsRarity.MYTHICAL, MagicThingsTags.Items.JEWELRY_MYTHICAL);

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
		// TODO add blood, shadow, atium, etc

		// jewelry tools
		tag(MagicThingsTags.Items.STONE_REMOVAL_TOOLS)
				.addTag(Tags.Items.SHEARS)
				.add(Items.STONE_AXE, Items.IRON_AXE, Items.DIAMOND_AXE, Items.NETHERITE_AXE);

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

//						if (handler.getJewelrySizeTier() != JewelrySizeTier.REGULAR && rarity.getCode() < 2) {
//							rarity = MagicThingsRarity.getByCode(rarity.getCode() + 1);
//						}
						rarity = MagicThingsRarity.getByCode(rarity.getCode() + handler.getJewelrySizeTier().getCode());
					}
//					else {
//						// check the material
//						if (handler.getJewelrySizeTier() != JewelrySizeTier.REGULAR && materialRarity.getCode() < 2) {
//							rarity = MagicThingsRarity.getByCode(rarity.getCode() + 1);
//						}
//					}
					else {
						rarity = MagicThingsRarity.getByCode(rarity.getCode() + handler.getJewelrySizeTier().getCode());
					}
					tag(JEWELRY_RARITY_TAG_MAP.get(rarity)).add(registryItem.get());
				} else {
//					if (materialRarity.getCode() < 2) {
						rarity = MagicThingsRarity.getByCode(rarity.getCode() + handler.getJewelrySizeTier().getCode());
//					}
//					if (handler.getJewelrySizeTier() != JewelrySizeTier.REGULAR) {
//						if (materialRarity.getCode() < 2) {
//							rarity = MagicThingsRarity.getByCode(rarity.getCode() + 2);
//						} else {
//							rarity = MagicThingsRarity.getByCode(rarity.getCode() + 1);
//						}
//					}
					tag(JEWELRY_RARITY_TAG_MAP.get(rarity)).add(registryItem.get());
				}
			});
		});

		// TODO is it better to include the specials in the rarity map and randomly select OR
		// TODO manually include in the loot tables?
		// explicitly categorize special jewelry
		tag(MagicThingsTags.Items.JEWELRY_COMMON).add(MagicThingsItems.SILBROS_RING_OF_VITALITY.get());
		tag(MagicThingsTags.Items.JEWELRY_COMMON).add(MagicThingsItems.STRONGMANS_BRACERS.get());
		tag(MagicThingsTags.Items.JEWELRY_COMMON).add(MagicThingsItems.PEASANTS_FORTUNE.get());

		tag(MagicThingsTags.Items.JEWELRY_UNCOMMON).add(MagicThingsItems.MALDRITCHS_FIRST_AMULET.get());
		tag(MagicThingsTags.Items.JEWELRY_UNCOMMON).add(MagicThingsItems.AQUA_RING.get());
		tag(MagicThingsTags.Items.JEWELRY_UNCOMMON).add(MagicThingsItems.AMULET_OF_DEFENCE.get());

		tag(MagicThingsTags.Items.JEWELRY_SCARCE).add(MagicThingsItems.JOURNEYMANS_BANDS.get());
		tag(MagicThingsTags.Items.JEWELRY_SCARCE).add(MagicThingsItems.ADEPHAGIAS_BOUNTY.get());
		tag(MagicThingsTags.Items.JEWELRY_SCARCE).add(MagicThingsItems.MEDICS_TOKEN.get());

		tag(MagicThingsTags.Items.JEWELRY_RARE).add(MagicThingsItems.SALANDAARS_WARD.get());
		tag(MagicThingsTags.Items.JEWELRY_RARE).add(MagicThingsItems.ANGELS_RING.get());
		tag(MagicThingsTags.Items.JEWELRY_RARE).add(MagicThingsItems.RING_OF_FORTITUDE.get());

		// TODO move to Legendary or mythical ?
		tag(MagicThingsTags.Items.JEWELRY_EPIC).add(MagicThingsItems.RING_LIFE_DEATH.get());
		tag(MagicThingsTags.Items.JEWELRY_EPIC).add(MagicThingsItems.EYE_OF_THE_PHOENIX.get());

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

		// recharging stones
		tag(MagicThingsTags.Items.RECHARGERS).add(Items.AMETHYST_SHARD);
		tag(MagicThingsTags.Items.RECHARGERS).add(Items.EMERALD);
		tag(MagicThingsTags.Items.RECHARGERS).add(MagicThingsItems.RECHARGE_SCROLL.get());

		Registration.ITEMS.getEntries().forEach(o -> {
			if (o.get() instanceof SpellScroll) {
				tag(MagicThingsTags.Items.SPELL_SCROLLS).add(o.get());
			}
		});

		// belts
		tag(MagicThingsTags.Items.CURIOUS_BELT).add(MagicThingsItems.SKULL_BELT.get());

		// ores
		tag(MagicThingsTags.Items.ORE_SILVER).add(MagicThingsItems.SILVER_ORE_ITEM.get());
		tag(MagicThingsTags.Items.ORE_TOPAZ).add(MagicThingsItems.TOPAZ_ORE_ITEM.get());
		tag(MagicThingsTags.Items.ORE_ONYX).add(MagicThingsItems.ONYX_ORE_ITEM.get());
		tag(MagicThingsTags.Items.ORE_JADE).add(MagicThingsItems.JADEITE_ORE_ITEM.get());
		tag(MagicThingsTags.Items.ORE_JADEITE).add(MagicThingsItems.JADEITE_ORE_ITEM.get());
		tag(MagicThingsTags.Items.ORE_RUBY).add(MagicThingsItems.RUBY_ORE_ITEM.get());
		tag(MagicThingsTags.Items.ORE_SAPPHIRE).add(MagicThingsItems.SAPPHIRE_ORE_ITEM.get());

		// gems
		tag(MagicThingsTags.Items.GEMS_TOPAZ).add(MagicThingsItems.TOPAZ.get());
		tag(MagicThingsTags.Items.GEMS_ONYX).add(MagicThingsItems.ONYX.get());
		tag(MagicThingsTags.Items.GEMS_JADE).add(MagicThingsItems.JADEITE.get());
		tag(MagicThingsTags.Items.GEMS_JADEITE).add(MagicThingsItems.JADEITE.get());
		tag(MagicThingsTags.Items.GEMS_RUBY).add(MagicThingsItems.RUBY.get());
		tag(MagicThingsTags.Items.GEMS_SAPPHIRE).add(MagicThingsItems.SAPPHIRE.get());
		tag(MagicThingsTags.Items.GEMS_PEARL).add(MagicThingsItems.WHITE_PEARL.get());
		tag(MagicThingsTags.Items.GEMS_PEARL).add(MagicThingsItems.BLACK_PEARL.get());

		// ingots
		tag(MagicThingsTags.Items.INGOTS_SILVER).add(MagicThingsItems.SILVER_INGOT.get());

		// special jewelry tags
		tag(MagicThingsTags.Items.CASTLE_RING_RUBY).add(MagicThingsItems.RUBY.get());
		tag(MagicThingsTags.Items.CASTLE_RING_SAPPHIRE).add(MagicThingsItems.SAPPHIRE.get());

		// //////////////////////////////////////////////
		// treasure2 integration
		// //////////////////////////////////////////////

		// add treasure2 gems to mt stone tiers
		tag(MagicThingsTags.Items.STONE_TIER1).addOptional(TreasureItems.TOPAZ.getId());
		tag(MagicThingsTags.Items.STONE_TIER2).addOptional(TreasureItems.ONYX.getId());
		tag(MagicThingsTags.Items.STONE_TIER5).addOptional(TreasureItems.RUBY.getId());
		tag(MagicThingsTags.Items.STONE_TIER6).addOptional(TreasureItems.SAPPHIRE.getId());
		tag(MagicThingsTags.Items.STONE_TIER5).addOptional(TreasureItems.WHITE_PEARL.getId());
		tag(MagicThingsTags.Items.STONE_TIER6).addOptional(TreasureItems.BLACK_PEARL.getId());

		// add treasure2 gems to mt stone rarities
		tag(MagicThingsTags.Items.STONE_RARITY_COMMON).addOptional(TreasureItems.TOPAZ.getId());
		tag(MagicThingsTags.Items.STONE_RARITY_UNCOMMON).addOptional(TreasureItems.ONYX.getId());
		tag(MagicThingsTags.Items.STONE_RARITY_RARE).addOptional(TreasureItems.RUBY.getId());
		tag(MagicThingsTags.Items.STONE_RARITY_EPIC).addOptional(TreasureItems.SAPPHIRE.getId());
		tag(MagicThingsTags.Items.STONE_RARITY_RARE).addOptional(TreasureItems.WHITE_PEARL.getId());
		tag(MagicThingsTags.Items.STONE_RARITY_EPIC).addOptional(TreasureItems.BLACK_PEARL.getId());

		// add treasure2 gems mt special jewelry tags
		tag(MagicThingsTags.Items.CASTLE_RING_RUBY).addOptional(TreasureItems.RUBY.getId());
		tag(MagicThingsTags.Items.CASTLE_RING_SAPPHIRE).addOptional(TreasureItems.SAPPHIRE.getId());

		// wishables
		tag(TreasureTags.Items.SCARCE_WISHABLE).add(MagicThingsItems.TOPAZ.get());
		tag(TreasureTags.Items.SCARCE_WISHABLE).add(MagicThingsItems.ONYX.get());
		tag(TreasureTags.Items.SCARCE_WISHABLE).add(MagicThingsItems.JADEITE.get());
		tag(TreasureTags.Items.RARE_WISHABLE).add(MagicThingsItems.RUBY.get());
		tag(TreasureTags.Items.RARE_WISHABLE).add(MagicThingsItems.WHITE_PEARL.get());
		tag(TreasureTags.Items.EPIC_WISHABLE).add(MagicThingsItems.SAPPHIRE.get());
		tag(TreasureTags.Items.EPIC_WISHABLE).add(MagicThingsItems.BLACK_PEARL.get());

		// pouch
		tag(TreasureTags.Items.POUCH).addTag(MagicThingsTags.Items.STONES);
		tag(TreasureTags.Items.POUCH).addTag(MagicThingsTags.Items.RECHARGERS);
		tag(TreasureTags.Items.POUCH).addTag(MagicThingsTags.Items.JEWELRY);

		// /////////////////
		// diamethysts integration
		// //////////////////

		tag(MagicThingsTags.Items.RECHARGERS).addOptional(new ResourceLocation("diamethysts:diamethyst_shard"));
		tag(MagicThingsTags.Items.RECHARGERS).addOptional(new ResourceLocation("diamethysts:diamethyst_crystal"));
	}
}
