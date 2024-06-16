/*
 * This file is part of  Magic Things.
 * Copyright (c) 2024 Mark Gottschling (gottsch)
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
package mod.gottsch.forge.magic_things.core.item;

import com.google.common.collect.Lists;
import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.block.MagicThingsBlocks;
import mod.gottsch.forge.magic_things.core.capability.IJewelryHandler;
import mod.gottsch.forge.magic_things.core.capability.JewelryCapability;
import mod.gottsch.forge.magic_things.core.capability.JewelryHandler;
import mod.gottsch.forge.magic_things.core.jewelry.JewelryMaterial;
import mod.gottsch.forge.magic_things.core.jewelry.JewelryMaterials;
import mod.gottsch.forge.magic_things.core.jewelry.JewelrySizeTier;
import mod.gottsch.forge.magic_things.core.setup.Registration;
import mod.gottsch.forge.magic_things.core.spell.MagicThingsSpells;
import mod.gottsch.forge.magic_things.core.spell.SpellRegistry;
import mod.gottsch.forge.magic_things.core.tag.MagicThingsTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;


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

	// tools
	public static RegistryObject<Item> JEWELRY_PLIERS = Registration.ITEMS.register("jewelry_pliers", () -> new JewelryPliers(MAGIC_THINGS_PROPS_SUPPLIER.get()));

	// spell scrolls
	public static final List<RegistryObject<Item>> ALL_SPELL_SCROLLS = Lists.newArrayList();
	static {
		SpellRegistry.values().forEach(spell -> {
			RegistryObject<Item> scroll = Registration.ITEMS.register(spell.getName().getPath() + "_scroll",
					() -> new SpellScroll(MAGIC_THINGS_PROPS_SUPPLIER.get(), spell));
			// add scroll registry item to a list
			ALL_SPELL_SCROLLS.add(scroll);
		});
	}

	// recipe scrolls
	public static RegistryObject<Item> RING_RECIPE = Registration.ITEMS.register("ring_recipe", () -> new JewelryRecipeScroll(MAGIC_THINGS_PROPS_SUPPLIER.get()));
	public static RegistryObject<Item> NECKLACE_RECIPE = Registration.ITEMS.register("necklace_recipe", () -> new JewelryRecipeScroll(MAGIC_THINGS_PROPS_SUPPLIER.get()));
	public static RegistryObject<Item> BRACELET_RECIPE = Registration.ITEMS.register("bracelet_recipe", () -> new JewelryRecipeScroll(MAGIC_THINGS_PROPS_SUPPLIER.get()));
//	public static RegistryObject<Item> BELT_RECIPE = Registration.ITEMS.register("belt_recipe", () -> new JewelryRecipeScroll(MAGIC_THINGS_PROPS_SUPPLIER.get()));

	// recharge scroll
	public static RegistryObject<Item> RECHARGE_SCROLL = Registration.ITEMS.register("recharge_scroll", () -> new Item(MAGIC_THINGS_PROPS_SUPPLIER.get()));

	// metals / ingots
	public static RegistryObject<Item> SILVER_INGOT = Registration.ITEMS.register("silver_ingot", () -> new Item(MAGIC_THINGS_PROPS_SUPPLIER.get()) {
		@Override
		public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component>tooltip, TooltipFlag flag) {
			// TODO
		}
	});

	// gemstones
	public static RegistryObject<Item> JADEITE = Registration.ITEMS.register("jadeite", () -> new Gemstone(MAGIC_THINGS_PROPS_SUPPLIER.get()));
	public static RegistryObject<Item> TOPAZ = Registration.ITEMS.register("topaz", () -> new Gemstone(MAGIC_THINGS_PROPS_SUPPLIER.get()));
    public static RegistryObject<Item> ONYX = Registration.ITEMS.register("onyx", () -> new Gemstone(MAGIC_THINGS_PROPS_SUPPLIER.get()));
	public static RegistryObject<Item> RUBY = Registration.ITEMS.register("ruby", () -> new Gemstone(MAGIC_THINGS_PROPS_SUPPLIER.get()));
    public static RegistryObject<Item> SAPPHIRE = Registration.ITEMS.register("sapphire", () -> new Gemstone(MAGIC_THINGS_PROPS_SUPPLIER.get()));
    public static RegistryObject<Item> WHITE_PEARL = Registration.ITEMS.register("white_pearl", () -> new Gemstone(MAGIC_THINGS_PROPS_SUPPLIER.get()));
    public static RegistryObject<Item> BLACK_PEARL = Registration.ITEMS.register("black_pearl", () -> new Gemstone(MAGIC_THINGS_PROPS_SUPPLIER.get()));

	// belts
	public static RegistryObject<Item> SKULL_BELT = Registration.ITEMS.register("skull_belt", () -> new ManaWell(MAGIC_THINGS_PROPS_SUPPLIER.get()));


	/*
     * a list of all mod generated items.
     */
    public static final List<RegistryObject<Item>> ALL_JEWELRY = Lists.newArrayList();
	public static final List<RegistryObject<Item>> STANDARD_JEWELRY = Lists.newArrayList();

	// custom jewelry
	public static RegistryObject<Item> PEASANTS_FORTUNE = Registration.ITEMS.register("peasants_fortune", () -> new NamedJewelry(MAGIC_THINGS_PROPS_SUPPLIER.get()) {
		public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
			IJewelryHandler handler = new JewelryHandler.Builder(JewelryType.RING, JewelryMaterials.IRON)
					.withSize(JewelrySizeTier.GREAT)
					.withStone(Items.BEDROCK.getRegistryName())
					.with($ -> {
						$.maxMana = 250;
						$.maxRecharges = 1;
						$.maxLevel = 4;
					})
					.build();


			return new JewelryCapability(handler);
		}
	});

	// scarce
	public static RegistryObject<Item> MEDICS_TOKEN = Registration.ITEMS.register("medics_token", () -> new NamedJewelry(MAGIC_THINGS_PROPS_SUPPLIER.get()) {
		public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
			IJewelryHandler handler = new JewelryHandler.Builder(JewelryType.NECKLACE, JewelryMaterials.GOLD)
					.withSize(JewelrySizeTier.GREAT)
					.withStone(JADEITE.getId())
					.with($ -> {
						$.spells.add(SpellRegistry.get(MagicThingsSpells.GREATER_HEALING).orElse(MagicThingsSpells.DEFAULT_HEALING).entity());
						$.maxMana = 300;
						$.maxRecharges = 1;
					})
					.setInfinite()
					.build();

			return new JewelryCapability(handler);
		}
	});

	// scarce
	public static RegistryObject<Item> ADEPHAGIAS_BOUNTY = Registration.ITEMS.register("adephagias_bounty", () -> new NamedJewelry(MAGIC_THINGS_PROPS_SUPPLIER.get()) {
		public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
			IJewelryHandler handler = new JewelryHandler.Builder(JewelryType.BRACELET, JewelryMaterials.GOLD)
					.withSize(JewelrySizeTier.GREAT)
					.withStone(JADEITE.getId())
					.with($ -> {
						$.spells.add(SpellRegistry.get(MagicThingsSpells.SATIETY).orElse(MagicThingsSpells.DEFAULT_HEALING).entity());
						$.maxMana = 300;
						$.maxRecharges = 1;
						$.maxLevel = 6;
					})
					.setInfinite()
					.build();

			return new JewelryCapability(handler);
		}
	});

	// rare
	public static RegistryObject<Item> SALANDAARS_WARD = Registration.ITEMS.register("salandaars_ward", () -> new NamedJewelry(MAGIC_THINGS_PROPS_SUPPLIER.get()) {
		@Override
		public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
			IJewelryHandler handler = new JewelryHandler.Builder(JewelryType.NECKLACE, JewelryMaterials.GOLD)
					.withSize(JewelrySizeTier.GREAT)
					.withStone(MagicThingsItems.RUBY.getId())
					.with($ -> {
						$.spells.add(SpellRegistry.get(MagicThingsSpells.MANA_TOWER_SHIELD).orElse(MagicThingsSpells.DEFAULT_HEALING).entity());
						$.maxMana = 350;
						$.maxRecharges = 3;
						$.maxLevel = 7;
					})
					.build();
			return new JewelryCapability(handler);
		}
	});

	// epic
	public static RegistryObject<Item> ANGELS_RING = Registration.ITEMS.register("angels_ring", () -> new NamedJewelry(MAGIC_THINGS_PROPS_SUPPLIER.get()) {
		public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
			IJewelryHandler handler = new JewelryHandler.Builder(JewelryType.RING, JewelryMaterials.GOLD)
					.withSize(JewelrySizeTier.GREAT)
					.withStone(MagicThingsItems.WHITE_PEARL.getId())
					.with($ -> {
						$.spells.add(SpellRegistry.get(MagicThingsSpells.REGENERATION).orElse(MagicThingsSpells.DEFAULT_HEALING).entity());
						$.maxMana = 400;
						$.maxRecharges = 3;
						$.maxLevel = 8;
					})
					.build();
			return new JewelryCapability(handler);
		}
	});

	// epic / legendary
	public static RegistryObject<Item> RING_LIFE_DEATH = Registration.ITEMS.register("ring_of_life_death", () -> new NamedJewelry(MAGIC_THINGS_PROPS_SUPPLIER.get()) {
		public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
			/*
			 *  add enchantment
			 */
			if (!EnchantmentHelper.hasVanishingCurse(stack)) {
				stack.enchant(Enchantments.VANISHING_CURSE, 1);
			}

			IJewelryHandler handler = new JewelryHandler.Builder(JewelryType.RING, JewelryMaterials.BLOOD)
					.withSize(JewelrySizeTier.LORDS)
					.withStone(RUBY.getId())
					.with($ -> {
						$.baseName = "twisted_ring";
						$.spells.add(SpellRegistry.get(MagicThingsSpells.CHEAT_DEATH).get().entity());
						$.spells.add(SpellRegistry.get(MagicThingsSpells.GREATER_DRAIN).get().entity());
						$.maxMana = 1000;
						$.maxRecharges = 0;
						$.maxRepairs = 0;
						$.maxLevel = 9;
						$.acceptsAffixer = p -> {
							return false;
						};
					})
					.setInfinite()
					.build();
			return new JewelryCapability(handler);
		}
	});

	// rare / epic
	public static RegistryObject<Item> RING_OF_FORTITUDE = Registration.ITEMS.register("ring_of_fortitude", () -> new NamedJewelry(MAGIC_THINGS_PROPS_SUPPLIER.get()) {
		public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
			/*
			 *  add enchantment
			 */
			if (!EnchantmentHelper.hasVanishingCurse(stack)) {
				stack.enchant(Enchantments.VANISHING_CURSE, 1);
			}

			IJewelryHandler handler = new JewelryHandler.Builder(JewelryType.RING, JewelryMaterials.GOLD)
					.withSize(JewelrySizeTier.GREAT)
					.withStone(SAPPHIRE.getId())
					.with($ -> {
						$.baseName = "castle_ring";
						$.spells.add(SpellRegistry.get(MagicThingsSpells.SHADOW_ARMOR).get().entity());
						$.maxMana = 350;
						$.maxRecharges = 3;
						$.maxLevel = 7;
						$.acceptsAffixer = castleRingAffixer;
					})
					.setInfinite()
					.build();

			return new JewelryCapability(handler);
		}
	});
//	.setLoreKey("jewelry.castle_ring.lore"));

	// rare / epic
	public static RegistryObject<Item> EYE_OF_THE_PHOENIX = Registration.ITEMS.register("eye_of_the_phoenix", () -> new NamedJewelry(MAGIC_THINGS_PROPS_SUPPLIER.get()) {
		public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {

			IJewelryHandler handler = new JewelryHandler.Builder(JewelryType.NECKLACE, JewelryMaterials.GOLD)
					.withSize(JewelrySizeTier.GREAT)
					.withStone(BLACK_PEARL.getId())
					.with($ -> {
						$.baseName = "amulet";
						$.spells.add(SpellRegistry.get(MagicThingsSpells.BLESSING_OF_THE_PHOENIX).get().entity());
						$.maxMana = 300;
						$.maxRecharges = 3;
						$.maxRepairs = 3;
						$.maxLevel = 7;
					})
					.setInfinite()
					.build();
			return new JewelryCapability(handler);
		}
	});

	// TEST
//	public static RegistryObject<Item> SCARAB = Registration.ITEMS.register("scarab", () -> new NamedJewelry(MAGIC_THINGS_PROPS_SUPPLIER.get()) {
//		public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
//			IJewelryHandler handler = new JewelryHandler.Builder(JewelryType.CHARM, JewelryMaterials.GOLD)
//					.withSize(JewelrySizeTier.REGULAR)
//					.withStone(Items.AIR.getRegistryName())
//					.with($ -> $.spells.add(SpellRegistry.get(ModUtil.asLocation("lesser_healing")).orElse(MagicThingsSpells.DEFAULT_HEALING).entity()))
//					.build();
//
//			return new JewelryCapability(handler);
//		}
//	});

	// TODO move to MagicThingsAffixes or Jewelry class
    static Predicate<ItemStack> castleRingAffixer = p -> {
		return p.is(MagicThingsTags.Items.CASTLE_RING_RUBY)
				| p.is(MagicThingsTags.Items.CASTLE_RING_SAPPHIRE);
	};

    static {

		// SCARAB will be a mana well
//		STANDARD_JEWELRY.add(SCARAB);

		// TODO this is a special jewelry
//		ALL_JEWELRY.add(SCARAB);

		List<Pair<String, Supplier<Jewelry>>> jewelry = new ArrayList<>();
		JewelryBuilder builder = new JewelryBuilder(MagicThings.MOD_ID);
		jewelry = builder.useBaseDefaults().deferredBuild();
		jewelry.addAll(builder.useStoneDefaults().deferredBuild());

		// castle rings
		JewelryBuilder builder2 = new JewelryBuilder(MagicThings.MOD_ID);
		jewelry.addAll(builder2
				.types(JewelryType.RING)
				.materials(JewelryMaterials.SILVER)
				.sizes(JewelrySizeTier.REGULAR)
				.with($ -> {
					$.baseName = "castle_ring";
					$.loreKey = "jewelry.castle_ring.lore";
					$.maxUses = 500;
					$.maxMana = 200;
					$.maxRepairs = 0;
					$.acceptsAffixer = p -> {
						return p.is(MagicThingsTags.Items.CASTLE_RING_RUBY)
								| p.is(MagicThingsTags.Items.CASTLE_RING_SAPPHIRE);
					};
					// TODO all castle rings should be infinite or very high durability
				})
				.deferredBuild());
		jewelry.addAll(builder2
				.stones(RUBY.getId(), SAPPHIRE.getId())
				.deferredBuild());

		// wood jewelry (only regular size)
		JewelryBuilder woodBuilder = new JewelryBuilder(MagicThings.MOD_ID);
		jewelry.addAll(woodBuilder
				.materials(JewelryMaterials.WOOD)
				.sizes(JewelrySizeTier.REGULAR)
				.types(JewelryType.RING, JewelryType.BRACELET, JewelryType.NECKLACE)
				.deferredBuild()
		);
		jewelry.addAll(woodBuilder.useStoneDefaults().deferredBuild());

		// bone jewelry (only regular for now)
//		JewelryBuilder boneBuilder = new JewelryBuilder(MagicThings.MOD_ID);
//		jewelry.addAll(boneBuilder
//				.materials(JewelryMaterials.BONE)
//				.sizes(JewelrySizeTier.REGULAR, JewelrySizeTier.GREAT)
//				.types(JewelryType.RING, JewelryType.BRACELET, JewelryType.NECKLACE)
//				.deferredBuild()
//		);
//		jewelry.addAll(boneBuilder.useStoneDefaults().deferredBuild());

		// POC Lord's Necklaces
		JewelryBuilder lordsNecklacesBuilder = new JewelryBuilder(MagicThings.MOD_ID);
		jewelry.addAll(lordsNecklacesBuilder
				.materials(
						JewelryMaterials.IRON,
						JewelryMaterials.COPPER,
						JewelryMaterials.SILVER,
						JewelryMaterials.GOLD)
				.sizes(JewelrySizeTier.LORDS)
				.types(JewelryType.NECKLACE)
				.deferredBuild()
		);
		jewelry.addAll(lordsNecklacesBuilder.useStoneDefaults().deferredBuild());

		// Hawk Ring POC
		JewelryBuilder hawkRingBuilder = new JewelryBuilder(MagicThings.MOD_ID);
		jewelry.addAll(hawkRingBuilder
				.materials(
						JewelryMaterials.IRON)
				.sizes(JewelrySizeTier.REGULAR)
				.types(JewelryType.RING)
				.with($ -> {
					$.baseName = "hawk_ring";
					$.maxMana = JewelryMaterials.IRON.getUses() * JewelryMaterials.IRON.getRepairs();
					$.maxLevel = JewelryMaterials.IRON.getMaxLevel() + 1;
				})
				.deferredBuild()
		);

		jewelry.forEach(pair -> {
			RegistryObject<Item> item = Registration.ITEMS.register(pair.getKey(), pair.getValue());
			STANDARD_JEWELRY.add(item);
			MagicThings.LOGGER.debug("adding item -> {} to standard registry", item.getId());
			ALL_JEWELRY.add(item);
		});

    }

	public static final RegistryObject<Item> TOPAZ_ORE_ITEM = fromBlock(MagicThingsBlocks.TOPAZ_ORE, MAGIC_THINGS_PROPS_SUPPLIER);
	public static final RegistryObject<Item> DEEPSLATE_TOPAZ_ORE_ITEM = fromBlock(MagicThingsBlocks.DEEPSLATE_TOPAZ_ORE, MAGIC_THINGS_PROPS_SUPPLIER);

	public static final RegistryObject<Item> ONYX_ORE_ITEM = fromBlock(MagicThingsBlocks.ONYX_ORE, MAGIC_THINGS_PROPS_SUPPLIER);
	public static final RegistryObject<Item> DEEPSLATE_ONYX_ORE_ITEM = fromBlock(MagicThingsBlocks.DEEPSLATE_ONYX_ORE, MAGIC_THINGS_PROPS_SUPPLIER);

	public static final RegistryObject<Item> JADEITE_ORE_ITEM = fromBlock(MagicThingsBlocks.JADEITE_ORE, MAGIC_THINGS_PROPS_SUPPLIER);
	public static final RegistryObject<Item> DEEPSLATE_JADEITE_ORE_ITEM = fromBlock(MagicThingsBlocks.DEEPSLATE_JADEITE_ORE, MAGIC_THINGS_PROPS_SUPPLIER);


	public static final RegistryObject<Item> RUBY_ORE_ITEM = fromBlock(MagicThingsBlocks.RUBY_ORE, MAGIC_THINGS_PROPS_SUPPLIER);
	public static final RegistryObject<Item> DEEPSLATE_RUBY_ORE_ITEM = fromBlock(MagicThingsBlocks.DEEPSLATE_RUBY_ORE, MAGIC_THINGS_PROPS_SUPPLIER);
	public static final RegistryObject<Item> SAPPHIRE_ORE_ITEM = fromBlock(MagicThingsBlocks.SAPPHIRE_ORE, MAGIC_THINGS_PROPS_SUPPLIER);
	public static final RegistryObject<Item> DEEPSLATE_SAPPHIRE_ORE_ITEM = fromBlock(MagicThingsBlocks.DEEPSLATE_SAPPHIRE_ORE, MAGIC_THINGS_PROPS_SUPPLIER);

	public static final RegistryObject<Item> SILVER_ORE_ITEM = fromBlock(MagicThingsBlocks.SILVER_ORE, MAGIC_THINGS_PROPS_SUPPLIER);
	public static final RegistryObject<Item> DEEPSLATE_SILVER_ORE_ITEM = fromBlock(MagicThingsBlocks.DEEPSLATE_SILVER_ORE, MAGIC_THINGS_PROPS_SUPPLIER);

	// TODO create/register all jewelry

	public static void register() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		Registration.ITEMS.register(eventBus);		
	}

	// convenience method: take a RegistryObject<Block> and make a corresponding RegistryObject<Item> from it
	public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block, Supplier<Item.Properties> itemProperties) {
		return Registration.ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), itemProperties.get()));
	}

	/**
	 * 
	 * @author Mark Gottschling Jul 11, 2023
	 *
	 */
	public static class JewelryBuilder {
		public List<JewelryType> types = new ArrayList<>();
		public List<JewelrySizeTier> sizes = new ArrayList<>();
		public List<JewelryMaterial> materials = new ArrayList<>();
		public List<ResourceLocation> stones = new ArrayList<>();
		public String baseName;
		public String loreKey;
		public int maxUses = -1;
		public int maxMana = -1;
		public int maxRepairs = -1;
		public int maxLevel = 0;
		public Predicate<ItemStack> acceptsAffixer = p -> true;
		
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
			materials(
//					JewelryMaterials.WOOD,
					JewelryMaterials.IRON,
					JewelryMaterials.COPPER,
					JewelryMaterials.SILVER,
					JewelryMaterials.GOLD,
					JewelryMaterials.BONE);
			return this;
		}
		
		public JewelryBuilder useStoneDefaults() {
			stones(
					Items.DIAMOND.getRegistryName(),
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

		public JewelryBuilder with(Consumer<JewelryBuilder> builder) {
			builder.accept(this);
			return this;
		}

		public JewelryBuilder types(JewelryType... types) {
			getTypes().addAll(Arrays.asList(types));
			return this;
		}
		
		public JewelryBuilder sizes(JewelrySizeTier... sizes) {
			getSizes().addAll(Arrays.asList(sizes));
			return this;
		}
		
		public JewelryBuilder materials(JewelryMaterial... materials) {
			getMaterials().addAll(Arrays.asList(materials));
			return this;
		}
		
		public JewelryBuilder stones(ResourceLocation... sources) {
			getStones().addAll(Arrays.asList(sources));
			return this;
		}

		/**
		 * Deferred build.
		 * This method returns a list of String/Supplier Pairs.
		 * This method can be used in a static call of a class to construct RegistryObjects
		 * The String in the Pair is used as the name (without the namespace)
		 * NOTE adornments are deferred and thus are not registered in
		 * TreasureAdornmentRegistry yet. That would have to happen in a post init like event.
		 * @return
		 */
		public List<Pair<String, Supplier<Jewelry>>> deferredBuild() {
			List<Pair<String, Supplier<Jewelry>>> jewelry = new ArrayList<>();

			if (types.isEmpty()) return jewelry;
			if (sizes.isEmpty()) return jewelry;

			List<ResourceLocation> tempStones = new ArrayList<>();
			if (stones.isEmpty()) {
				tempStones.add(Items.AIR.getRegistryName());
			}
			else {
				tempStones.addAll(stones);
			}

			// create the jewelry
			types.forEach(type -> {
				sizes.forEach(size -> {
					materials.forEach(material -> {
						tempStones.forEach(stone -> {
							// build the name
							String name = (size == JewelrySizeTier.REGULAR ? "" : size.getName() + "_")
									+ material.getName() + "_"
									+ (stone == Items.AIR.getRegistryName() ? "" :  stone.getPath() + "_")
									+ (StringUtils.isNotBlank(baseName) ? baseName : type.toString());

							// build the adornment supplier
							Supplier<Jewelry> a = deferredCreateJewelry(type, material, size, stone);
							MagicThings.LOGGER.debug("adding deferred jewelry item -> {}", name);

							// build a pair
							Pair<String, Supplier<Jewelry>> pair = Pair.of(name.toLowerCase(), a);
							jewelry.add(pair);
						});
					});
				});
			});
			return jewelry;
		}

		public Supplier<Jewelry> deferredCreateJewelry(JewelryType type, JewelryMaterial material, JewelrySizeTier size, ResourceLocation stone) {
			return () -> {
				Jewelry j = new Jewelry(MAGIC_THINGS_PROPS_SUPPLIER.get()) {
					public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
						IJewelryHandler handler = new JewelryHandler.Builder(type, material, stone, size)
								.with($ -> {
									$.baseName = JewelryBuilder.this.getBaseName();
									$.maxLevel = JewelryBuilder.this.maxLevel;
									$.maxUses = JewelryBuilder.this.maxUses;
									$.maxMana = JewelryBuilder.this.maxMana;
									$.maxRepairs = JewelryBuilder.this.maxRepairs;
									$.acceptsAffixer = JewelryBuilder.this.acceptsAffixer;
								})
								.build();
						return new JewelryCapability(handler);
					}
				};
				return (Jewelry) j.setLoreKey(JewelryBuilder.this.loreKey);
			};
		}

		public List<JewelryType> getTypes() {
			return types;
		}

		public List<JewelrySizeTier> getSizes() {
			return sizes;
		}

		public List<JewelryMaterial> getMaterials() {
			return materials;
		}

		public List<ResourceLocation> getStones() {
			return stones;
		}

		public String getBaseName() {
			return this.baseName;
		}

		public String getModid() {
			return modid;
		}
	}
}
