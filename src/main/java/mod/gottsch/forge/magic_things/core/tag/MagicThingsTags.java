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
package mod.gottsch.forge.magic_things.core.tag;

import mod.gottsch.forge.gottschcore.enums.IRarity;
import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.api.MagicThingsApi;
import mod.gottsch.forge.magic_things.core.item.IJewelryType;
import mod.gottsch.forge.magic_things.core.item.JewelryType;
import mod.gottsch.forge.magic_things.core.jewelry.JewelryStoneTier;
import mod.gottsch.forge.magic_things.core.registry.JewelryRegistry;
import mod.gottsch.forge.magic_things.core.registry.StoneRegistry;
import mod.gottsch.forge.magic_things.core.registry.TagRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Mark Gottschling on 5/29/2023
 */
@Mod.EventBusSubscriber(modid = MagicThings.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MagicThingsTags {
    private static final String CURIOS_MODID = "curios";
    private static final String FORGE_MODID = "forge";

    public static class Items {
        // curios integration
        public static final TagKey<Item> CURIOUS_NECKLACE = mod(CURIOS_MODID, JewelryType.NECKLACE.getValue());
        public static final TagKey<Item> CURIOUS_RING = mod(CURIOS_MODID, JewelryType.RING.getValue());
        public static final TagKey<Item> CURIOUS_BRACELET = mod(CURIOS_MODID, JewelryType.BRACELET.getValue());
//        public static final TagKey<Item> CURIOUS_CHARM = mod(CURIOS_MODID, "charm");
        public static final TagKey<Item> CURIOUS_BELT = mod(CURIOS_MODID, "belt");

        // jewelry
        public static final TagKey<Item> RINGS = mod(MagicThings.MOD_ID, "jewelry/rings");
        public static final TagKey<Item> BRACELETS = mod(MagicThings.MOD_ID, "jewelry/bracelets");
        public static final TagKey<Item> NECKLACES = mod(MagicThings.MOD_ID, "jewelry/necklaces");
//        public static final TagKey<Item> CHARMS = mod(MagicThings.MOD_ID, "jewelry/charms");
        public static final TagKey<Item> POCKETS = mod(MagicThings.MOD_ID, "jewelry/pockets");
        public static final TagKey<Item> JEWELRY = mod(MagicThings.MOD_ID, "jewelry/all_jewelry");

        // other
        public static final TagKey<Item> STONE_REMOVAL_TOOLS = mod(MagicThings.MOD_ID, "tools/stone_removal");

        // categorization by tier
        public static final TagKey<Item> WOOD = mod(MagicThings.MOD_ID, "jewelry/tiers/materials/wood");
        public static final TagKey<Item> IRON = mod(MagicThings.MOD_ID, "jewelry/tiers/materials/iron");
        public static final TagKey<Item> COPPER = mod(MagicThings.MOD_ID, "jewelry/tiers/materials/copper");
        public static final TagKey<Item> SILVER = mod(MagicThings.MOD_ID, "jewelry/tiers/materials/silver");
        public static final TagKey<Item> GOLD = mod(MagicThings.MOD_ID, "jewelry/tiers/materials/gold");
        public static final TagKey<Item> BLOOD = mod(MagicThings.MOD_ID, "jewelry/tiers/materials/blood");
        public static final TagKey<Item> BONE = mod(MagicThings.MOD_ID, "jewelry/tiers/materials/bone");
        public static final TagKey<Item> SHADOW = mod(MagicThings.MOD_ID, "jewelry/tiers/materials/shadow");
        public static final TagKey<Item> ATIUM = mod(MagicThings.MOD_ID, "jewelry/tiers/materials/atium");
        
        public static final TagKey<Item> REGULAR = mod(MagicThings.MOD_ID, "jewelry/tiers/sizes/regular");
        public static final TagKey<Item> GREAT = mod(MagicThings.MOD_ID, "jewelry/tiers/sizes/great");
        public static final TagKey<Item> LORDS = mod(MagicThings.MOD_ID, "jewelry/tiers/sizes/lords");

        public static final TagKey<Item> JEWELRY_COMMON = mod(MagicThings.MOD_ID, "jewelry/rarity/common");
        public static final TagKey<Item> JEWELRY_UNCOMMON = mod(MagicThings.MOD_ID, "jewelry/rarity/uncommon");
        public static final TagKey<Item> JEWELRY_SCARCE = mod(MagicThings.MOD_ID, "jewelry/rarity/scarce");
        public static final TagKey<Item> JEWELRY_RARE = mod(MagicThings.MOD_ID, "jewelry/rarity/rare");
        public static final TagKey<Item> JEWELRY_EPIC = mod(MagicThings.MOD_ID, "jewelry/rarity/epic");

        // stone tiers
        public static final TagKey<Item> STONE_TIER1 = mod(MagicThings.MOD_ID, "jewelry/stones/tier/tier1");
        public static final TagKey<Item> STONE_TIER2 = mod(MagicThings.MOD_ID, "jewelry/stones/tier/tier2");
        public static final TagKey<Item> STONE_TIER3 = mod(MagicThings.MOD_ID, "jewelry/stones/tier/tier3");
        public static final TagKey<Item> STONE_TIER4 = mod(MagicThings.MOD_ID, "jewelry/stones/tier/tier4");
        public static final TagKey<Item> STONE_TIER5 = mod(MagicThings.MOD_ID, "jewelry/stones/tier/tier5");
        public static final TagKey<Item> STONE_TIER6 = mod(MagicThings.MOD_ID, "jewelry/stones/tier/tier6");
        public static final TagKey<Item> STONE_TIER_SKELETONS_HEART = mod(MagicThings.MOD_ID, "jewelry/stones/tier/skeletons_heart");

        // NOTE stone rarity is loosely equivalent to tier.
        public static final TagKey<Item> STONE_RARITY_COMMON = mod(MagicThings.MOD_ID, "jewelry/stones/rarity/common");
        public static final TagKey<Item> STONE_RARITY_UNCOMMON = mod(MagicThings.MOD_ID, "jewelry/stones/rarity/uncommon");
        public static final TagKey<Item> STONE_RARITY_SCARCE = mod(MagicThings.MOD_ID, "jewelry/stones/rarity/scarce");
        public static final TagKey<Item> STONE_RARITY_RARE = mod(MagicThings.MOD_ID, "jewelry/stones/rarity/rare");
        public static final TagKey<Item> STONE_RARITY_EPIC = mod(MagicThings.MOD_ID, "jewelry/stones/rarity/epic");

        public static final TagKey<Item> STONES = mod(MagicThings.MOD_ID, "jewelry/stones/all_stones");
        public static final TagKey<Item> RECHARGERS = mod(MagicThings.MOD_ID, "jewelry/stones/rechargers");


        public static final TagKey<Item> CASTLE_RING_RUBY = mod(MagicThings.MOD_ID, "jewelry/castle_ring/ruby");
        public static final TagKey<Item> CASTLE_RING_SAPPHIRE = mod(MagicThings.MOD_ID, "jewelry/castle_ring/sapphire");

        public static final TagKey<Item> SPELL_SCROLLS = mod(MagicThings.MOD_ID, "scrolls/spells");

        // /////
        // forge integration tags
        // /////

        // ores
        public static final TagKey<Item> ORE_SILVER = mod(FORGE_MODID, "ores/silver");
        public static final TagKey<Item> ORE_TOPAZ = mod(FORGE_MODID, "ores/topaz");
        public static final TagKey<Item> ORE_ONYX = mod(FORGE_MODID, "ores/onyx");
        public static final TagKey<Item> ORE_JADE = mod(FORGE_MODID, "ores/jade");
        public static final TagKey<Item> ORE_JADEITE = mod(FORGE_MODID, "ores/jadeite");
        public static final TagKey<Item> ORE_RUBY = mod(FORGE_MODID, "ores/ruby");
        public static final TagKey<Item> ORE_SAPPHIRE = mod(FORGE_MODID, "ores/sapphire");

        // gems
        public static final TagKey<Item> GEMS_TOPAZ = mod(FORGE_MODID, "gems/topaz");
        public static final TagKey<Item> GEMS_ONYX = mod(FORGE_MODID, "gems/onyx");
        public static final TagKey<Item> GEMS_JADE = mod(FORGE_MODID, "gems/jade");
        public static final TagKey<Item> GEMS_JADEITE = mod(FORGE_MODID, "gems/jadeite");
        public static final TagKey<Item> GEMS_RUBY = mod(FORGE_MODID, "gems/ruby");
        public static final TagKey<Item> GEMS_SAPPHIRE = mod(FORGE_MODID, "gems/sapphire");
        public static final TagKey<Item> GEMS_PEARL = mod(FORGE_MODID, "gems/pearl");

        // ingots
        public static final TagKey<Item> INGOTS_SILVER = mod(FORGE_MODID, "ingots/silver");

        /**
         *
         * @param domain
         * @param path
         * @return
         */
        public static TagKey<Item> mod(String domain, String path) {
            return ItemTags.create(new ResourceLocation(domain, path));
        }
    }
    
	@SubscribeEvent
	public static void registerTags(TagsUpdatedEvent event) {
		MagicThings.LOGGER.info("in tags updated event");
		
		// clear any registries
		JewelryRegistry.clear();

        /*
         * register all jewelry items by looking up the jewelry type tags.
         */
		// process all items in the JewelryType tags
        List<IJewelryType> types = MagicThingsApi.getJewelryTypes();
        types.forEach(type -> {
        	TagKey<Item> tagKey = TagRegistry.getJewelryTypeTag(type);
			if (tagKey != null) {
				// get the tag
				ITag<Item> tag = ForgeRegistries.ITEMS.tags().getTag(tagKey);
				// for each item in the tag
				for (Iterator<Item> iterator = tag.iterator(); iterator.hasNext();) {
					Item jewelry = iterator.next();
					JewelryRegistry.register(jewelry);
					MagicThings.LOGGER.info("registering jewelry -> {} ", jewelry.getRegistryName());
				}
			}
        });

        // process all items in the JewelryStoneTier tags
        List<JewelryStoneTier> tiers = MagicThingsApi.getJewelryStoneTiers();
        tiers.forEach(tier -> {
            TagKey<Item> tagKey = TagRegistry.getJewelryStoneTierTag(tier);
            if (tagKey != null) {
                // get the tag
                ITag<Item> tag = ForgeRegistries.ITEMS.tags().getTag(tagKey);
                // for each item in the tag
                for (Item stone : tag) {
                    // register the stone with the StoneRegistry
                    StoneRegistry.register(stone);
                    // register the stone with the tier in the StoneRegistry
                    StoneRegistry.register(stone, tier);
                    MagicThings.LOGGER.debug("registering stone to tier -> {} <--> {} ", stone.getRegistryName(), tier.getName());
                }
            }
        });

        // registry stones by rarity
        List<IRarity> rarities = MagicThingsApi.getRarities();
        rarities.forEach(rarity -> {
            TagKey<Item> tagKey = TagRegistry.getStoneRarityTag(rarity);
            if (tagKey != null) {
                ITag<Item> tag = ForgeRegistries.ITEMS.tags().getTag(tagKey);
                for (Item stone : tag) {
                    StoneRegistry.register(rarity, stone);
                    MagicThings.LOGGER.debug("registering stone to rarity -> {} <--> {} ", stone.getRegistryName(), rarity.getName());
                }
            }
            // register jewelry by rarity
            MagicThingsApi.getJewelryRarityTag(rarity)
                    .ifPresent(key -> {
                        ITag<Item> tag = ForgeRegistries.ITEMS.tags().getTag(key);
                        tag.forEach(item -> {
                            JewelryRegistry.register(rarity, item);
                            MagicThings.LOGGER.debug("registering jewelry to rarity -> {} <--> {} ", item.getRegistryName(), rarity.getName());
                        });
                    });
        });
	}
}
