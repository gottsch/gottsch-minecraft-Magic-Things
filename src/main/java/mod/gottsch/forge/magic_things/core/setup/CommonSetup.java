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
package mod.gottsch.forge.magic_things.core.setup;

import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.config.Config;
import mod.gottsch.forge.magic_things.core.integration.MagicThingsIntegrations;
import mod.gottsch.forge.magic_things.core.item.*;
import mod.gottsch.forge.magic_things.core.jewelry.JewelryMaterials;
import mod.gottsch.forge.magic_things.core.jewelry.JewelryStoneTiers;
import mod.gottsch.forge.magic_things.core.rarity.MagicThingsRarity;
import mod.gottsch.forge.magic_things.api.MagicThingsApi;
import mod.gottsch.forge.magic_things.core.tag.MagicThingsTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * Created by Mark Gottschling on 5/3/2023
 */
@Mod.EventBusSubscriber(modid = MagicThings.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetup {

    /**
     *
     * @param event
     */
    public static void init(final FMLCommonSetupEvent event) {
        // add mod specific logging
        Config.instance.addRollingFileAppender(MagicThings.MOD_ID);

        // register rarities
        MagicThingsApi.registerRarity(MagicThingsRarity.COMMON);
        MagicThingsApi.registerRarity(MagicThingsRarity.UNCOMMON);
        MagicThingsApi.registerRarity(MagicThingsRarity.SCARCE);
        MagicThingsApi.registerRarity(MagicThingsRarity.RARE);
        MagicThingsApi.registerRarity(MagicThingsRarity.EPIC);
        MagicThingsApi.registerRarity(MagicThingsRarity.LEGENDARY);
        MagicThingsApi.registerRarity(MagicThingsRarity.MYTHICAL);

        // register jewelry types
        MagicThingsApi.registerJewelryType(JewelryType.RING);
        MagicThingsApi.registerJewelryType(JewelryType.NECKLACE);
        MagicThingsApi.registerJewelryType(JewelryType.BRACELET);
        MagicThingsApi.registerJewelryType(JewelryType.POCKET);
        MagicThingsApi.registerJewelryType(JewelryType.CHARM);
        // TODO add Broach ?

        // register jewelry size tiers
        MagicThingsApi.registerJewelrySize(JewelrySizeTier.REGULAR);
        MagicThingsApi.registerJewelrySize(JewelrySizeTier.GREAT);
        MagicThingsApi.registerJewelrySize(JewelrySizeTier.LORDS);

        // register jewelry stone tiers
        MagicThingsApi.registerJewelryStoneTier(JewelryStoneTiers.TIER1);
        MagicThingsApi.registerJewelryStoneTier(JewelryStoneTiers.TIER2);
        MagicThingsApi.registerJewelryStoneTier(JewelryStoneTiers.TIER3);
        MagicThingsApi.registerJewelryStoneTier(JewelryStoneTiers.TIER4);
        MagicThingsApi.registerJewelryStoneTier(JewelryStoneTiers.TIER5);
        MagicThingsApi.registerJewelryStoneTier(JewelryStoneTiers.TIER6);
        MagicThingsApi.registerJewelryStoneTier(JewelryStoneTiers.SKELETONS_HEART);

        // register jewelry material tiers
        MagicThingsApi.registerJewelryMaterial(JewelryMaterials.WOOD);
        MagicThingsApi.registerJewelryMaterial(JewelryMaterials.IRON);
        MagicThingsApi.registerJewelryMaterial(JewelryMaterials.COPPER);
        MagicThingsApi.registerJewelryMaterial(JewelryMaterials.SILVER);
        MagicThingsApi.registerJewelryMaterial(JewelryMaterials.GOLD);
        MagicThingsApi.registerJewelryMaterial(JewelryMaterials.BLOOD);
        MagicThingsApi.registerJewelryMaterial(JewelryMaterials.BONE);
        MagicThingsApi.registerJewelryMaterial(JewelryMaterials.SHADOW);
        MagicThingsApi.registerJewelryMaterial(JewelryMaterials.ATIUM);

        // register stone rarity tags
        MagicThingsApi.registerStoneRarityTag(MagicThingsRarity.COMMON, MagicThingsTags.Items.STONE_RARITY_COMMON);
        MagicThingsApi.registerStoneRarityTag(MagicThingsRarity.UNCOMMON, MagicThingsTags.Items.STONE_RARITY_UNCOMMON);
        MagicThingsApi.registerStoneRarityTag(MagicThingsRarity.SCARCE, MagicThingsTags.Items.STONE_RARITY_SCARCE);
        MagicThingsApi.registerStoneRarityTag(MagicThingsRarity.RARE, MagicThingsTags.Items.STONE_RARITY_RARE);
        MagicThingsApi.registerStoneRarityTag(MagicThingsRarity.EPIC, MagicThingsTags.Items.STONE_RARITY_EPIC);

        // register type tags (TODO this could be a stream of all JewelryTypes ?)
        MagicThingsApi.registerJewerlyTypeTag(JewelryType.RING, MagicThingsTags.Items.RINGS);
        MagicThingsApi.registerJewerlyTypeTag(JewelryType.BRACELET, MagicThingsTags.Items.BRACELETS);
        MagicThingsApi.registerJewerlyTypeTag(JewelryType.NECKLACE, MagicThingsTags.Items.NECKLACES);
        MagicThingsApi.registerJewerlyTypeTag(JewelryType.POCKET, MagicThingsTags.Items.POCKETS);
        MagicThingsApi.registerJewerlyTypeTag(JewelryType.CHARM, MagicThingsTags.Items.CHARMS);
        
        // register stone tier tags
        MagicThingsApi.registerJewerlyStoneTierTag(JewelryStoneTiers.TIER1, MagicThingsTags.Items.STONE_TIER1);
        MagicThingsApi.registerJewerlyStoneTierTag(JewelryStoneTiers.TIER2, MagicThingsTags.Items.STONE_TIER2);
        MagicThingsApi.registerJewerlyStoneTierTag(JewelryStoneTiers.TIER3, MagicThingsTags.Items.STONE_TIER3);
        MagicThingsApi.registerJewerlyStoneTierTag(JewelryStoneTiers.TIER4, MagicThingsTags.Items.STONE_TIER4);
        MagicThingsApi.registerJewerlyStoneTierTag(JewelryStoneTiers.TIER5, MagicThingsTags.Items.STONE_TIER5);
        MagicThingsApi.registerJewerlyStoneTierTag(JewelryStoneTiers.TIER6, MagicThingsTags.Items.STONE_TIER6);
        MagicThingsApi.registerJewerlyStoneTierTag(JewelryStoneTiers.SKELETONS_HEART, MagicThingsTags.Items.STONE_TIER_SKELETONS_HEART);

        // TODO these can be categorized/registered by rarity via tags.
        // register stones
        MagicThingsApi.registerJewelryStone(Items.DIAMOND);
        MagicThingsApi.registerJewelryStone(MagicThingsItems.JADEITE.get());
        MagicThingsApi.registerJewelryStone(MagicThingsItems.TOPAZ.get());
        MagicThingsApi.registerJewelryStone(MagicThingsItems.ONYX.get());
        MagicThingsApi.registerJewelryStone(MagicThingsItems.RUBY.get());
        MagicThingsApi.registerJewelryStone(MagicThingsItems.SAPPHIRE.get());
        MagicThingsApi.registerJewelryStone(MagicThingsItems.WHITE_PEARL.get());
        MagicThingsApi.registerJewelryStone(MagicThingsItems.BLACK_PEARL.get());

        // map stone items -> handlers
//        MagicThingsApi.registerJewelryStoneHandler(Items.DIAMOND, JewelryStoneHandlers.STANDARD);

        // integrations
        MagicThingsIntegrations.registerCuriosIntegration();

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
