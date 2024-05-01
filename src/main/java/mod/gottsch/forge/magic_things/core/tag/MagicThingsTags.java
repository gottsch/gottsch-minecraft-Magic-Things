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
package mod.gottsch.forge.magic_things.core.tag;

import java.util.Iterator;
import java.util.List;

import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.registry.JewelryRegistry;
import mod.gottsch.forge.magic_things.core.registry.TagRegistry;
import mod.gottsch.forge.magic_things.api.MagicThingsApi;
import mod.gottsch.forge.magic_things.core.item.IJewelryType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

/**
 * Created by Mark Gottschling on 5/29/2023
 */
@Mod.EventBusSubscriber(modid = MagicThings.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MagicThingsTags {

    public static class Items {
        // jewelry
        public static final TagKey<Item> RINGS = mod(MagicThings.MOD_ID, "jewelry/rings");
        public static final TagKey<Item> BRACELETS = mod(MagicThings.MOD_ID, "jewelry/bracelets");
        public static final TagKey<Item> NECKLACES = mod(MagicThings.MOD_ID, "jewelry/necklaces");
        public static final TagKey<Item> CHARMS = mod(MagicThings.MOD_ID, "jewelry/charms");
        public static final TagKey<Item> POCKETS = mod(MagicThings.MOD_ID, "jewelry/pockets");

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
        
        public static final TagKey<Item> TOPAZ = mod(MagicThings.MOD_ID, "jewelry/tiers/stones/topaz");
        public static final TagKey<Item> ONYX = mod(MagicThings.MOD_ID, "jewelry/tiers/stones/onyx");
        public static final TagKey<Item> DIAMOND = mod(MagicThings.MOD_ID, "jewelry/tiers/stones/diamond");
//        public static final TagKey<Item> EMERALD = mod(MagicThings.MOD_ID, "jewelry/tiers/stones/emerald");
        public static final TagKey<Item> JADEITE = mod(MagicThings.MOD_ID, "jewelry/tiers/stones/jadeite");
        public static final TagKey<Item> RUBY = mod(MagicThings.MOD_ID, "jewelry/tiers/stones/ruby");
        public static final TagKey<Item> SAPPHIRE = mod(MagicThings.MOD_ID, "jewelry/tiers/stones/sapphire");
        public static final TagKey<Item> WHITE_PEARL = mod(MagicThings.MOD_ID, "jewelry/tiers/stones/white_pearl");
        public static final TagKey<Item> BLACK_PEARL = mod(MagicThings.MOD_ID, "jewelry/tiers/stones/black_pearl");
        
        // stone tiers
        public static final TagKey<Item> STONE_TIER1 = mod(MagicThings.MOD_ID, "jewelry/stones/tier1");
        public static final TagKey<Item> STONE_TIER2 = mod(MagicThings.MOD_ID, "jewelry/stones/tier2");
        public static final TagKey<Item> STONE_TIER3 = mod(MagicThings.MOD_ID, "jewelry/stones/tier3");
        public static final TagKey<Item> STONE_TIER4 = mod(MagicThings.MOD_ID, "jewelry/stones/tier4");
        public static final TagKey<Item> STONE_TIER5 = mod(MagicThings.MOD_ID, "jewelry/stones/tier5");
        public static final TagKey<Item> STONE_TIER6 = mod(MagicThings.MOD_ID, "jewelry/stones/tier6");
        
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
	}
}
