/*
 * This file is part of  GealdronCraft.
 * Copyright (c) 2023 Mark Gottschling (gottsch)
 *
 * GealdronCraft is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GealdronCraft is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GealdronCraft.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
package mod.gottsch.forge.gealdorcraft.core.registry;

import java.util.Map;

import com.google.common.collect.Maps;

import mod.gottsch.forge.gealdorcraft.core.registry.support.JewelryRegistryKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Registers all the jewelery items by ResourceLocation, and MaterialType and Key(Material, Stone, Size)
 * @author Mark Gottschling Jul 6, 2023
 * @param <IJewerlyStoneTierTier>
 *
 */
public class JewelryRegistry<IJewerlyStoneTierTier> {
	private static final Map<ResourceLocation, Item> NAME_MAP = Maps.newHashMap();
	private static final Map<JewelryRegistryKey, Item> KEY_MAP = Maps.newHashMap();
	
	public static void register(Item item) {
		NAME_MAP.put(item.getRegistryName(), item);
		// NOTE capabilities are only on ItemStacks. The item itself is going to need properties or pass in the values
		ItemStack stack = new ItemStack(item);
		// TODO need to create the capability class first
//		stack.getCapability(JEWELRY)
//		KEY_MAP.put(new JewelryRegistryKey(), item);
	}
}
