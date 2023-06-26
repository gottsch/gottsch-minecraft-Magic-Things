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
package mod.gottsch.forge.gealdorcraft.core.tag;

import mod.gottsch.forge.gealdorcraft.GealdorCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Created by Mark Gottschling on 5/29/2023
 */
@Mod.EventBusSubscriber(modid = GealdorCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GealdorCraftTags {

    public static class Items {
        // jewelry
        public static final TagKey<Item> RINGS = mod(GealdorCraft.MOD_ID, "jewelry/rings");
        public static final TagKey<Item> BRACELETS = mod(GealdorCraft.MOD_ID, "jewelry/bracelets");
        public static final TagKey<Item> NECKLACES = mod(GealdorCraft.MOD_ID, "jewelry/necklaces");
        public static final TagKey<Item> CHARMS = mod(GealdorCraft.MOD_ID, "jewelry/charms");
        public static final TagKey<Item> POCKETS = mod(GealdorCraft.MOD_ID, "jewelry/pockets");

        // stone tiers
        public static final TagKey<Item> STONE_TIER1 = mod(GealdorCraft.MOD_ID, "jewelry/stone_tiers/tier1");
        public static final TagKey<Item> STONE_TIER2 = mod(GealdorCraft.MOD_ID, "jewelry/stone_tiers/tier2");
        public static final TagKey<Item> STONE_TIER3 = mod(GealdorCraft.MOD_ID, "jewelry/stone_tiers/tier3");
        public static final TagKey<Item> STONE_TIER4 = mod(GealdorCraft.MOD_ID, "jewelry/stone_tiers/tier4");
        public static final TagKey<Item> STONE_TIER5= mod(GealdorCraft.MOD_ID, "jewelry/stone_tiers/tier5");
        public static final TagKey<Item> STONE_TIER6 = mod(GealdorCraft.MOD_ID, "jewelry/stone_tiers/tier6");
        
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
		GealdorCraft.LOGGER.info("in tags updated event");
	}
}
