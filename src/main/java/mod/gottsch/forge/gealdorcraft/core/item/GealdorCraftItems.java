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
package mod.gottsch.forge.gealdorcraft.core.item;

import mod.gottsch.forge.gealdorcraft.GealdorCraft;
import mod.gottsch.forge.gealdorcraft.core.capability.IJewelryHandler;
import mod.gottsch.forge.gealdorcraft.core.capability.JewelryCapability;
import mod.gottsch.forge.gealdorcraft.core.capability.JewelryHandler;
import mod.gottsch.forge.gealdorcraft.core.setup.Registration;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * Created by Mark Gottschling on 5/29/2023
 */
public class GealdorCraftItems {
    // item groups/tabs
    public static final CreativeModeTab GEALDORCRAFT_ITEM_GROUP = new CreativeModeTab(GealdorCraft.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(GealdorCraftItems.GEALDORCRAFT_TAB.get());
        }
    };

    public static final Supplier<Item.Properties> GEALDORCRAFT_PROPS_SUPPLIER = () -> new Item.Properties().tab(GEALDORCRAFT_ITEM_GROUP);
    // tab items
    public static final RegistryObject<Item> GEALDORCRAFT_TAB = Registration.ITEMS.register("gealdor_tab", () -> new Item(new Item.Properties()));

    // TEMP item
    public static RegistryObject<Item> COPPER_RING = Registration.ITEMS.register("copper_ring", () -> new Jewelry(GEALDORCRAFT_PROPS_SUPPLIER.get()) {
        public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
            IJewelryHandler handler = new JewelryHandler.Builder(
                    JewelryType.RING,
                    JewelryMaterialTier.COPPER,
                    JewelryStoneTier.NONE,
                    JewelrySizeTier.REGULAR).with($ -> {
                        // TODO don't really need a builder ??? might for special jewel that overrides that tier calculations.
                    }).build();

//            handler = new JewelryHandler(JewelryType.RING, JewelryMaterialTier.COPPER, JewelryStoneTier.NONE, JewelrySizeTier.REGULAR);
            
            // return capability
            return new JewelryCapability(handler);
        }
    });
    
    // TEMP how to make a topaz copper ring ? - the stone would have to be optional in the builder. size can be optional too and defaults to REGULAR
    public static RegistryObject<Item> TOPAZ_COPPER_RING = Registration.ITEMS.register("topaz_copper_ring", () -> new Jewelry(GEALDORCRAFT_PROPS_SUPPLIER.get()) {
        public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
            IJewelryHandler handler = new JewelryHandler.Builder(JewelryType.RING, JewelryMaterialTier.COPPER)
            		.withStone(TOPAZ.get().getRegistryName())
            		.build();
            return new JewelryCapability(handler);
        }
    });
    
    public static RegistryObject<Item> GREAT_ONYX_COPPER_RING = Registration.ITEMS.register("great_onyx_copper_ring", () -> new Jewelry(GEALDORCRAFT_PROPS_SUPPLIER.get()) {
        public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
            IJewelryHandler handler = new JewelryHandler.Builder(JewelryType.RING, JewelryMaterialTier.COPPER)
            		.withSize(JewelrySizeTier.GREAT)
            		.withStone(ONYX.get().getRegistryName())
            		.build();
            return new JewelryCapability(handler);
        }
    });
    
    // TEMP stones/gems
    public static RegistryObject<Item> TOPAZ = Registration.ITEMS.register("topaz", () -> new Item(new Item.Properties()));
    public static RegistryObject<Item> ONYX = Registration.ITEMS.register("onyx", () -> new Item(new Item.Properties()));
    
	public static void register() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		Registration.ITEMS.register(eventBus);		
	}
}
