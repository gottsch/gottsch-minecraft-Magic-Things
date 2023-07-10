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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import mod.gottsch.forge.gealdorcraft.core.capability.GealdorCapabilities;
import mod.gottsch.forge.gealdorcraft.core.item.IJewelryMaterialTier;
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
	private static final Multimap<JewelryRegistryKey, Item> KEY_MAP = ArrayListMultimap.create()	;
	
	/**
	 * Note: this registers a known item.
	 * @param item
	 */
	public static void register(Item item) {		
		ItemStack stack = new ItemStack(item);
		stack.getCapability(GealdorCapabilities.JEWELRY_CAPABILITY).ifPresent(c -> {
			NAME_MAP.put(item.getRegistryName(), item);
			// generate a key
			c.getJewelryStoneTiers().forEach(stone -> {
				JewelryRegistryKey key = new JewelryRegistryKey(
						c.getJewelryType(),
						c.getJewelryMaterialTier(),
						stone,
						c.getJewelrySizeTier());
				
				KEY_MAP.put(key, item);
			});
		});
	}
		
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static Optional<Item> get(ResourceLocation name) {
		return Optional.ofNullable(NAME_MAP.get(name));
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public static List get(JewelryRegistryKey key) {
		return new ArrayList<>(KEY_MAP.get(key));
	}
	
	/**
	 * 
	 * @param material
	 * @return
	 */
	public static List<Item> get(IJewelryMaterialTier material) {
		List<Item> list = KEY_MAP.entries()
				.stream()
				.filter(e -> e.getKey().getMaterial().equals(material))
				.map(e -> e.getValue())
				.toList();
		return list;
	}
	
	/**
	 * 
	 */
	public static void clear() {
		NAME_MAP.clear();
		KEY_MAP.clear();
	}

	public static List<Item> getAll() {
		return new ArrayList<>(NAME_MAP.values());
	}
	
	public static Set<Entry<ResourceLocation, Item>> getNameEntries() {
		return NAME_MAP.entrySet();
	}
	
	public static Collection<Entry<JewelryRegistryKey, Item>> getKeyEntries() {
		return KEY_MAP.entries();
	}
}
