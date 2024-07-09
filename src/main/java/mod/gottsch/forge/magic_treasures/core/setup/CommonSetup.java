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
package mod.gottsch.forge.magic_treasures.core.setup;

import mod.gottsch.forge.magic_treasures.MagicTreasures;
import mod.gottsch.forge.magic_treasures.core.config.Config;
import mod.gottsch.forge.magic_treasures.core.integration.MagicTreasuresIntegrations;
import mod.gottsch.forge.magic_treasures.core.item.*;
import mod.gottsch.forge.magic_treasures.core.jewelry.JewelryMaterials;
import mod.gottsch.forge.magic_treasures.core.jewelry.JewelrySizeTier;
import mod.gottsch.forge.magic_treasures.core.jewelry.JewelryStoneTiers;
import mod.gottsch.forge.magic_treasures.core.rarity.MagicTreasuresRarity;
import mod.gottsch.forge.magic_treasures.api.MagicTreasuresApi;
import mod.gottsch.forge.magic_treasures.core.tag.MagicTreasuresTags;
import mod.gottsch.forge.treasure2.api.TreasureApi;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * Created by Mark Gottschling on 5/3/2023
 */
@Mod.EventBusSubscriber(modid = MagicTreasures.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetup {

    /**
     *
     * @param event
     */
    public static void init(final FMLCommonSetupEvent event) {
        // add mod specific logging
        Config.instance.addRollingFileAppender(MagicTreasures.MOD_ID);

        // register rarities
        MagicTreasuresApi.registerRarity(MagicTreasuresRarity.COMMON);
        MagicTreasuresApi.registerRarity(MagicTreasuresRarity.UNCOMMON);
        MagicTreasuresApi.registerRarity(MagicTreasuresRarity.SCARCE);
        MagicTreasuresApi.registerRarity(MagicTreasuresRarity.RARE);
        MagicTreasuresApi.registerRarity(MagicTreasuresRarity.EPIC);
        MagicTreasuresApi.registerRarity(MagicTreasuresRarity.LEGENDARY);
        MagicTreasuresApi.registerRarity(MagicTreasuresRarity.MYTHICAL);

        // register jewelry types
        MagicTreasuresApi.registerJewelryType(JewelryType.RING);
        MagicTreasuresApi.registerJewelryType(JewelryType.NECKLACE);
        MagicTreasuresApi.registerJewelryType(JewelryType.BRACELET);
        MagicTreasuresApi.registerJewelryType(JewelryType.POCKET);
//        MagicTreasuresApi.registerJewelryType(JewelryType.CHARM);
        // TODO add Broach ?

        // register jewelry size tiers
        MagicTreasuresApi.registerJewelrySize(JewelrySizeTier.REGULAR);
        MagicTreasuresApi.registerJewelrySize(JewelrySizeTier.GREAT);
        MagicTreasuresApi.registerJewelrySize(JewelrySizeTier.LORDS);

        // register jewelry stone tiers
        MagicTreasuresApi.registerJewelryStoneTier(JewelryStoneTiers.TIER1);
        MagicTreasuresApi.registerJewelryStoneTier(JewelryStoneTiers.TIER2);
        MagicTreasuresApi.registerJewelryStoneTier(JewelryStoneTiers.TIER3);
        MagicTreasuresApi.registerJewelryStoneTier(JewelryStoneTiers.TIER4);
        MagicTreasuresApi.registerJewelryStoneTier(JewelryStoneTiers.TIER5);
        MagicTreasuresApi.registerJewelryStoneTier(JewelryStoneTiers.TIER6);
        MagicTreasuresApi.registerJewelryStoneTier(JewelryStoneTiers.SKELETONS_HEART);

        // register jewelry material tiers
        MagicTreasuresApi.registerJewelryMaterial(JewelryMaterials.WOOD);
        MagicTreasuresApi.registerJewelryMaterial(JewelryMaterials.IRON);
        MagicTreasuresApi.registerJewelryMaterial(JewelryMaterials.COPPER);
        MagicTreasuresApi.registerJewelryMaterial(JewelryMaterials.SILVER);
        MagicTreasuresApi.registerJewelryMaterial(JewelryMaterials.GOLD);
        MagicTreasuresApi.registerJewelryMaterial(JewelryMaterials.BLOOD);
        MagicTreasuresApi.registerJewelryMaterial(JewelryMaterials.BONE);
        MagicTreasuresApi.registerJewelryMaterial(JewelryMaterials.SHADOW);
        MagicTreasuresApi.registerJewelryMaterial(JewelryMaterials.ATIUM);

        // register stone rarity tags
        MagicTreasuresApi.registerStoneRarityTag(MagicTreasuresRarity.COMMON, MagicTreasuresTags.Items.STONE_RARITY_COMMON);
        MagicTreasuresApi.registerStoneRarityTag(MagicTreasuresRarity.UNCOMMON, MagicTreasuresTags.Items.STONE_RARITY_UNCOMMON);
        MagicTreasuresApi.registerStoneRarityTag(MagicTreasuresRarity.SCARCE, MagicTreasuresTags.Items.STONE_RARITY_SCARCE);
        MagicTreasuresApi.registerStoneRarityTag(MagicTreasuresRarity.RARE, MagicTreasuresTags.Items.STONE_RARITY_RARE);
        MagicTreasuresApi.registerStoneRarityTag(MagicTreasuresRarity.EPIC, MagicTreasuresTags.Items.STONE_RARITY_EPIC);

        // register type tags (TODO this could be a stream of all JewelryTypes ?)
        MagicTreasuresApi.registerJewerlyTypeTag(JewelryType.RING, MagicTreasuresTags.Items.RINGS);
        MagicTreasuresApi.registerJewerlyTypeTag(JewelryType.BRACELET, MagicTreasuresTags.Items.BRACELETS);
        MagicTreasuresApi.registerJewerlyTypeTag(JewelryType.NECKLACE, MagicTreasuresTags.Items.NECKLACES);
        MagicTreasuresApi.registerJewerlyTypeTag(JewelryType.POCKET, MagicTreasuresTags.Items.POCKETS);
//        MagicTreasuresApi.registerJewerlyTypeTag(JewelryType.CHARM, MagicTreasuresTags.Items.CHARMS);
        
        // register stone tier tags
        MagicTreasuresApi.registerJewerlyStoneTierTag(JewelryStoneTiers.TIER1, MagicTreasuresTags.Items.STONE_TIER1);
        MagicTreasuresApi.registerJewerlyStoneTierTag(JewelryStoneTiers.TIER2, MagicTreasuresTags.Items.STONE_TIER2);
        MagicTreasuresApi.registerJewerlyStoneTierTag(JewelryStoneTiers.TIER3, MagicTreasuresTags.Items.STONE_TIER3);
        MagicTreasuresApi.registerJewerlyStoneTierTag(JewelryStoneTiers.TIER4, MagicTreasuresTags.Items.STONE_TIER4);
        MagicTreasuresApi.registerJewerlyStoneTierTag(JewelryStoneTiers.TIER5, MagicTreasuresTags.Items.STONE_TIER5);
        MagicTreasuresApi.registerJewerlyStoneTierTag(JewelryStoneTiers.TIER6, MagicTreasuresTags.Items.STONE_TIER6);
        MagicTreasuresApi.registerJewerlyStoneTierTag(JewelryStoneTiers.SKELETONS_HEART, MagicTreasuresTags.Items.STONE_TIER_SKELETONS_HEART);

        // TODO these can be categorized/registered by rarity via tags.
        // register stones
//        MagicTreasuresApi.registerJewelryStone(Items.DIAMOND);
//        MagicTreasuresApi.registerJewelryStone(MagicTreasuresItems.JADEITE.get());
//        MagicTreasuresApi.registerJewelryStone(MagicTreasuresItems.TOPAZ.get());
//        MagicTreasuresApi.registerJewelryStone(MagicTreasuresItems.ONYX.get());
//        MagicTreasuresApi.registerJewelryStone(MagicTreasuresItems.RUBY.get());
//        MagicTreasuresApi.registerJewelryStone(MagicTreasuresItems.SAPPHIRE.get());
//        MagicTreasuresApi.registerJewelryStone(MagicTreasuresItems.WHITE_PEARL.get());
//        MagicTreasuresApi.registerJewelryStone(MagicTreasuresItems.BLACK_PEARL.get());

        // map stone items -> handlers
//        MagicTreasuresApi.registerJewelryStoneHandler(Items.DIAMOND, JewelryStoneHandlers.STANDARD);

        // integrations
        MagicTreasuresIntegrations.registerCuriosIntegration();
        MagicTreasuresIntegrations.registerTreasure2Integration();

        // TODO is this registry necessary. could acomplish the same thing using tags and lets modpacks dev modifiy.
        // would have to have same item in the JewelryType tag and a JewerlyMaterial tag minimum. the Size and Stone tags would be optional
        // ex. I want to add tf:platium_diamond_ring.
        // type --> rings.json tag
        // material --> platium.json tag (given that platinum material is registered). (could register material thru intermod communication - still mod-based)
        // stone --> diamond.json
        /*
         *  register items.
         *  Note: JewelryRegistry checks if the item has the correct capability before registering.
         */
        // TEMP - use the Tag Registry event to register jewelry
//        Registration.ITEMS.getEntries().forEach(item -> {
//        	JewelryRegistry.register(item.get());
//        });
    }
}
