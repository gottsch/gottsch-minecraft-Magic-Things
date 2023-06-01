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
package mod.gottsch.forge.gealdorcraft.core.setup;

import mod.gottsch.forge.gealdorcraft.api.GealdorCraftApi;
import mod.gottsch.forge.gealdorcraft.core.GealdorCraft;
import mod.gottsch.forge.gealdorcraft.core.config.Config;
import mod.gottsch.forge.gealdorcraft.core.item.JewelryMaterialTier;
import mod.gottsch.forge.gealdorcraft.core.item.JewelrySizeTier;
import mod.gottsch.forge.gealdorcraft.core.item.JewelryStoneTier;
import mod.gottsch.forge.gealdorcraft.core.item.JewelryType;
import mod.gottsch.forge.gealdorcraft.core.rarity.Rarity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * Created by Mark Gottschling on 5/3/2023
 */
@Mod.EventBusSubscriber(modid = GealdorCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetup {

    /**
     *
     * @param event
     */
    public static void init(final FMLCommonSetupEvent event) {
        // add mod specific logging
        Config.instance.addRollingFileAppender(GealdorCraft.MOD_ID);

        // register rarities
        GealdorCraftApi.registerRarity(Rarity.COMMON);
        GealdorCraftApi.registerRarity(Rarity.UNCOMMON);
        GealdorCraftApi.registerRarity(Rarity.SCARCE);
        GealdorCraftApi.registerRarity(Rarity.RARE);
        GealdorCraftApi.registerRarity(Rarity.EPIC);
        GealdorCraftApi.registerRarity(Rarity.LEGENDARY);
        GealdorCraftApi.registerRarity(Rarity.MYTHICAL);

        // register jewelry types
        GealdorCraftApi.registerJewelryType(JewelryType.RING);
        GealdorCraftApi.registerJewelryType(JewelryType.NECKLACE);
        GealdorCraftApi.registerJewelryType(JewelryType.BRACELET);
        GealdorCraftApi.registerJewelryType(JewelryType.POCKET);
        GealdorCraftApi.registerJewelryType(JewelryType.CHARM);
        // TODO add Broach ?

        // register jewelry size tiers
        GealdorCraftApi.registerJewelrySize(JewelrySizeTier.REGULAR);
        GealdorCraftApi.registerJewelrySize(JewelrySizeTier.GREAT);
        GealdorCraftApi.registerJewelrySize(JewelrySizeTier.LORDS);

        // register jewelry stone tiers
        GealdorCraftApi.registerJewelryStoneTier(JewelryStoneTier.TIER1);
        GealdorCraftApi.registerJewelryStoneTier(JewelryStoneTier.TIER2);
        GealdorCraftApi.registerJewelryStoneTier(JewelryStoneTier.TIER3);
        GealdorCraftApi.registerJewelryStoneTier(JewelryStoneTier.TIER4);
        GealdorCraftApi.registerJewelryStoneTier(JewelryStoneTier.TIER5);
        GealdorCraftApi.registerJewelryStoneTier(JewelryStoneTier.TIER6);

        // register jewelry material tiers
        GealdorCraftApi.registerJewelryMaterialTier(JewelryMaterialTier.WOOD);
        GealdorCraftApi.registerJewelryMaterialTier(JewelryMaterialTier.IRON);
        GealdorCraftApi.registerJewelryMaterialTier(JewelryMaterialTier.COPPER);
        GealdorCraftApi.registerJewelryMaterialTier(JewelryMaterialTier.SILVER);
        GealdorCraftApi.registerJewelryMaterialTier(JewelryMaterialTier.GOLD);
        GealdorCraftApi.registerJewelryMaterialTier(JewelryMaterialTier.BLOOD);
        GealdorCraftApi.registerJewelryMaterialTier(JewelryMaterialTier.BONE);
        GealdorCraftApi.registerJewelryMaterialTier(JewelryMaterialTier.SHADOW);
        GealdorCraftApi.registerJewelryMaterialTier(JewelryMaterialTier.ATIUM);
    }
}
