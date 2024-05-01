/*
 * This file is part of  MAGIC_THINGS.
 * Copyright (c) 2023 Mark Gottschling (gottsch)
 *
 * MAGIC_THINGS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MAGIC_THINGS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MAGIC_THINGS.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
package mod.gottsch.forge.magic_things.core.item;

import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.capability.IJewelryHandler;
import mod.gottsch.forge.magic_things.core.capability.JewelryCapability;
import mod.gottsch.forge.magic_things.core.capability.JewelryHandler;
import mod.gottsch.forge.magic_things.core.setup.Registration;
import mod.gottsch.forge.magic_things.core.util.ModUtil;
import mod.gottsch.forge.magic_things.core.spell.MagicThingsSpells;
import mod.gottsch.forge.magic_things.core.spell.SpellRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.Lists;


/**
 * Created by Mark Gottschling on 5/29/2023
 */
public class MagicThingsItems {
    // item groups/tabs
    public static final CreativeModeTab MAGIC_THINGS_ITEM_GROUP = new CreativeModeTab(MagicThings.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(MagicThingsItems.MAGIC_THINGS_TAB.get());
        }
    };

    public static final Supplier<Item.Properties> MAGIC_THINGS_PROPS_SUPPLIER = () -> new Item.Properties().tab(MAGIC_THINGS_ITEM_GROUP);
    // tab items
    public static final RegistryObject<Item> MAGIC_THINGS_TAB = Registration.ITEMS.register("magic_things_tab", () -> new Item(new Item.Properties()));

    // gemstones
	public static RegistryObject<Item> JADEITE = Registration.ITEMS.register("jadeite", () -> new Gemstone(MAGIC_THINGS_PROPS_SUPPLIER.get()));
	public static RegistryObject<Item> TOPAZ = Registration.ITEMS.register("topaz", () -> new Item(MAGIC_THINGS_PROPS_SUPPLIER.get()));
    public static RegistryObject<Item> ONYX = Registration.ITEMS.register("onyx", () -> new Item(MAGIC_THINGS_PROPS_SUPPLIER.get()));
	public static RegistryObject<Item> RUBY = Registration.ITEMS.register("ruby", () -> new Gemstone(MAGIC_THINGS_PROPS_SUPPLIER.get()));
    public static RegistryObject<Item> SAPPHIRE = Registration.ITEMS.register("sapphire", () -> new Gemstone(MAGIC_THINGS_PROPS_SUPPLIER.get()));
    public static RegistryObject<Item> WHITE_PEARL = Registration.ITEMS.register("white_pearl", () -> new Gemstone(MAGIC_THINGS_PROPS_SUPPLIER.get()));
    public static RegistryObject<Item> BLACK_PEARL = Registration.ITEMS.register("black_pearl", () -> new Gemstone(MAGIC_THINGS_PROPS_SUPPLIER.get()));

    /*
     * a list of all mod generated items.
     */
    public static final List<RegistryObject<Item>> ALL_JEWELRY = Lists.newArrayList();
    
    // TEMP item
    public static RegistryObject<Item> COPPER_RING = Registration.ITEMS.register("copper_ring", () -> new Jewelry(MAGIC_THINGS_PROPS_SUPPLIER.get()) {
        public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
            IJewelryHandler handler = new JewelryHandler.Builder(
                    JewelryType.RING,
                    JewelryMaterialTier.COPPER,
                    JewelryStoneTier.NONE,
                    JewelrySizeTier.REGULAR)
					.build();

            // return capability
            return new JewelryCapability(handler);
        }
    });
    
    // TEMP how to make a topaz copper ring ? - the stone would have to be optional in the builder. size can be optional too and defaults to REGULAR
    public static RegistryObject<Item> COPPER_TOPAZ_RING = Registration.ITEMS.register("copper_topaz_ring", () -> new Jewelry(MAGIC_THINGS_PROPS_SUPPLIER.get()) {
        public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
            IJewelryHandler handler = new JewelryHandler.Builder(JewelryType.RING, JewelryMaterialTier.COPPER)
            		.withStone(TOPAZ.getId())
            		.build();
            return new JewelryCapability(handler);
        }
    });
    
    public static RegistryObject<Item> GREAT_COPPER_ONYX_RING = Registration.ITEMS.register("great_copper_onyx_ring", () -> new Jewelry(MAGIC_THINGS_PROPS_SUPPLIER.get()) {
        public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
            IJewelryHandler handler = new JewelryHandler.Builder(JewelryType.RING, JewelryMaterialTier.COPPER)
            		.withSize(JewelrySizeTier.GREAT)
            		.withStone(ONYX.getId())
            		.build();
            return new JewelryCapability(handler);
        }
    });

	// TEMP
	public static RegistryObject<Item>HEALING_RING = Registration.ITEMS.register("healing_ring", () -> new Jewelry(MAGIC_THINGS_PROPS_SUPPLIER.get()) {
		public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
			IJewelryHandler handler = new JewelryHandler.Builder(JewelryType.RING, JewelryMaterialTier.COPPER)
					.withSize(JewelrySizeTier.REGULAR)
					.withStone(RUBY.getId())
					.build();
			handler.getSpells().add(SpellRegistry.get(ModUtil.asLocation("lesser_healing")).orElse(MagicThingsSpells.LESSER_HEALING).entity());

			return new JewelryCapability(handler);
		}
	});
      
    static {
    	ALL_JEWELRY.add(COPPER_RING);
    	ALL_JEWELRY.add(COPPER_TOPAZ_RING);
    	ALL_JEWELRY.add(GREAT_COPPER_ONYX_RING);
		// TODO this is a special jewelry
//		ALL_JEWELRY.add(HEALING_RING);
    }
    
    
    // TODO create/register all jewelry

	public static void register() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		Registration.ITEMS.register(eventBus);		
	}
	
	/**
	 * 
	 * @author Mark Gottschling Jul 11, 2023
	 *
	 */
	public static class JewelryBuilder {
		protected List<IJewelryType> types = new ArrayList<>();
		protected List<IJewelrySizeTier> sizes = new ArrayList<>();
		protected List<IJewelryMaterialTier> materials = new ArrayList<>();
		protected List<ResourceLocation> stones = new ArrayList<>();
		
		
		protected String modid;
		
		public JewelryBuilder(String modid) {
			this.modid = modid;
		}
		
		public JewelryBuilder clear() {
			types.clear();
			sizes.clear();
			materials.clear();
			stones.clear();
			return this;
		}
		
		/*
		 * convenience setup
		 */
		public JewelryBuilder useBaseDefaults() {
			types(JewelryType.BRACELET, JewelryType.NECKLACE, JewelryType.RING); // ...
			sizes(JewelrySizeTier.REGULAR, JewelrySizeTier.GREAT);
			materials(JewelryMaterialTier.IRON, 
					JewelryMaterialTier.COPPER, 
					JewelryMaterialTier.SILVER, 
					JewelryMaterialTier.GOLD);
			return this;
		}
		
		public JewelryBuilder useSourceDefaults() {
			stones(
					Items.DIAMOND.getRegistryName(),
					// TODO remove emerald and rename with new gem
//					Items.EMERALD.getRegistryName(),
					JADEITE.getId(),
					TOPAZ.getId(),
					ONYX.getId(),
					RUBY.getId(),
					SAPPHIRE.getId(),
					WHITE_PEARL.getId(),
					BLACK_PEARL.getId()
					);
			return this;
		}
		
		public JewelryBuilder types(IJewelryType... types) {
			getTypes().addAll(Arrays.asList(types));
			return this;
		}
		
		public JewelryBuilder sizes(IJewelrySizeTier... sizes) {
			getSizes().addAll(Arrays.asList(sizes));
			return this;
		}
		
		public JewelryBuilder materials(IJewelryMaterialTier... materials) {
			getMaterials().addAll(Arrays.asList(materials));
			return this;
		}
		
		public JewelryBuilder stones(ResourceLocation... sources) {
			getStones().addAll(Arrays.asList(sources));
			return this;
		}

		public List<IJewelryType> getTypes() {
			return types;
		}

		public List<IJewelrySizeTier> getSizes() {
			return sizes;
		}

		public List<IJewelryMaterialTier> getMaterials() {
			return materials;
		}

		public List<ResourceLocation> getStones() {
			return stones;
		}

		public String getModid() {
			return modid;
		}
	}
}
