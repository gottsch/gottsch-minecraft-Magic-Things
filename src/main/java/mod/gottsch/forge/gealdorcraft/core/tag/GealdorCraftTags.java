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

import mod.gottsch.forge.gealdorcraft.core.GealdorCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
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
}
