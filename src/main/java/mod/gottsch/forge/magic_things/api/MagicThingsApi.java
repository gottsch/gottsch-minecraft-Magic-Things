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
package mod.gottsch.forge.magic_things.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mod.gottsch.forge.magic_things.core.item.IJewelryMaterialTier;
import mod.gottsch.forge.magic_things.core.item.IJewelrySizeTier;
import mod.gottsch.forge.magic_things.core.item.IJewelryStoneTier;
import mod.gottsch.forge.magic_things.core.item.IJewelryType;
import mod.gottsch.forge.magic_things.core.registry.EnumRegistry;
import mod.gottsch.forge.magic_things.core.registry.TagRegistry;
import mod.gottsch.forge.gottschcore.enums.IEnum;
import mod.gottsch.forge.gottschcore.enums.IRarity;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/**
 * Created by Mark Gottschling on 5/4/2023
 */
public class MagicThingsApi {
    public static final String RARITY = "rarity";

    public static final String JEWELRY_TYPE = "jewelryType";
    public static final String JEWELRY_SIZE = "jewelrySize";
    public static final String JEWELRY_STONE = "jewelryStone";
    public static final String JEWELRY_MATERIAL = "jewelryMaterial";


	public static void registerJewerlyTypeTag(IJewelryType type, TagKey<Item> tagKey) {
		TagRegistry.registerJewelryType(type, tagKey);
	}
	
	public static void registerJewerlyStoneTierTag(IJewelryStoneTier tier, TagKey<Item> tierTagKey) {
		TagRegistry.registerJewelryStoneTier(tier, tierTagKey);
	}
	
    /**
     * TODO how to integrate with Treasure2 rarity and API.
     * @param rarity
     */
    public static void registerRarity(IRarity rarity) {
        EnumRegistry.register(RARITY, rarity);
    }

    /**
     *
     * @param key
     * @return
     */
    public static Optional<IRarity> getRarity(String key) {
        IEnum ienum = EnumRegistry.get(RARITY, key);
        if (ienum == null) {
            return Optional.empty();
        }
        else {
            return Optional.of((IRarity) ienum);
        }
    }

    public static void registerJewelryType(IJewelryType jewelryType) {
        EnumRegistry.register(JEWELRY_TYPE, jewelryType);
    }

	public static List<IJewelryType> getJewelryTypes() {
		List<IEnum> enums = EnumRegistry.getValues(JEWELRY_TYPE);
		ArrayList<IJewelryType> types = new ArrayList<>();
		if (!enums.isEmpty()) {
			types.addAll(enums.stream().map(e -> (IJewelryType)e).toList());
		}
		return types;
	}
	
    public static Optional<IJewelryType> getJewelryType(String key) {
        IEnum ienum = EnumRegistry.get(JEWELRY_TYPE, key);
        if (ienum == null) {
            return Optional.empty();
        }
        else {
            return Optional.of((IJewelryType) ienum);
        }
    }

    public static void registerJewelrySize(IJewelrySizeTier size) {
        EnumRegistry.register(JEWELRY_SIZE, size);
    }

    public static Optional<IJewelrySizeTier> getJewelrySize(String key) {
        IEnum ienum = EnumRegistry.get(JEWELRY_SIZE, key);
        if (ienum == null) {
            return Optional.empty();
        }
        else {
            return Optional.of((IJewelrySizeTier) ienum);
        }
    }

    public static void registerJewelryStoneTier(IJewelryStoneTier tier) {
        EnumRegistry.register(JEWELRY_STONE, tier);
    }

    public static Optional<IJewelryStoneTier> getJewelryStoneTier(String key) {
        IEnum ienum = EnumRegistry.get(JEWELRY_STONE, key);
        if (ienum == null) {
            return Optional.empty();
        }
        else {
            return Optional.of((IJewelryStoneTier) ienum);
        }
    }

    public static void registerJewelryMaterialTier(IJewelryMaterialTier tier) {
        EnumRegistry.register(JEWELRY_MATERIAL, tier);
    }

    public static Optional<IJewelryMaterialTier> getJewelryMaterialTier(String key) {
        IEnum ienum = EnumRegistry.get(JEWELRY_MATERIAL, key);
        if (ienum == null) {
            return Optional.empty();
        }
        else {
            return Optional.of((IJewelryMaterialTier) ienum);
        }
    }
}
