/*
 * This file is part of  Magic Treasures.
 * Copyright (c) 2024 Mark Gottschling (gottsch)
 *
 * Magic Treasures is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Magic Treasures is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Magic Treasures.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
package mod.gottsch.forge.magic_treasures.core.world.feature;

import mod.gottsch.forge.magic_treasures.MagicTreasures;
import mod.gottsch.forge.magic_treasures.core.block.MagicTreasuresBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

import java.util.List;

/**
 * 
 * @author Mark Gottschling on May 10, 2024
 *
 */
public class MagicTreasuresConfiguredFeatures {
	private static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, MagicTreasures.MOD_ID);

	public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TOPAZ_ORE_KEY = registerKey("topaz_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_ONYX_ORE_KEY = registerKey("onyx_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_JADEITE_ORE_KEY = registerKey("jadeite_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_RUBY_ORE_KEY = registerKey("ruby_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SAPPHIRE_ORE_KEY = registerKey("sapphire_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SILVER_ORE_KEY = registerKey("silver_ore");


	/**
	 *
	 * @param context
	 */
	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		RuleTest ruleTest1 = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
		RuleTest ruleTest2 = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

		List<OreConfiguration.TargetBlockState> topazOres = List.of(OreConfiguration.target(ruleTest1,
						MagicTreasuresBlocks.TOPAZ_ORE.get().defaultBlockState()),
				OreConfiguration.target(ruleTest2, MagicTreasuresBlocks.DEEPSLATE_TOPAZ_ORE.get().defaultBlockState()));

		List<OreConfiguration.TargetBlockState> onyxOres = List.of(OreConfiguration.target(ruleTest1,
						MagicTreasuresBlocks.ONYX_ORE.get().defaultBlockState()),
				OreConfiguration.target(ruleTest2, MagicTreasuresBlocks.DEEPSLATE_ONYX_ORE.get().defaultBlockState()));

		List<OreConfiguration.TargetBlockState> jadeiteOres = List.of(OreConfiguration.target(ruleTest1,
						MagicTreasuresBlocks.JADEITE_ORE.get().defaultBlockState()),
				OreConfiguration.target(ruleTest2, MagicTreasuresBlocks.DEEPSLATE_JADEITE_ORE.get().defaultBlockState()));

		List<OreConfiguration.TargetBlockState> rubyOres = List.of(OreConfiguration.target(ruleTest1,
						MagicTreasuresBlocks.RUBY_ORE.get().defaultBlockState()),
				OreConfiguration.target(ruleTest2, MagicTreasuresBlocks.DEEPSLATE_RUBY_ORE.get().defaultBlockState()));

		List<OreConfiguration.TargetBlockState> sapphireOres = List.of(OreConfiguration.target(ruleTest1,
						MagicTreasuresBlocks.SAPPHIRE_ORE.get().defaultBlockState()),
				OreConfiguration.target(ruleTest2, MagicTreasuresBlocks.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState()));

		List<OreConfiguration.TargetBlockState> silverOres = List.of(OreConfiguration.target(ruleTest1,
						MagicTreasuresBlocks.SILVER_ORE.get().defaultBlockState()),
				OreConfiguration.target(ruleTest2, MagicTreasuresBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState()));

		register(context, OVERWORLD_TOPAZ_ORE_KEY, Feature.ORE, new OreConfiguration(topazOres, 3));
		register(context, OVERWORLD_ONYX_ORE_KEY, Feature.ORE, new OreConfiguration(onyxOres, 3));
		register(context, OVERWORLD_JADEITE_ORE_KEY, Feature.ORE, new OreConfiguration(jadeiteOres, 3));
		register(context, OVERWORLD_RUBY_ORE_KEY, Feature.ORE, new OreConfiguration(rubyOres, 3));
		register(context, OVERWORLD_SAPPHIRE_ORE_KEY, Feature.ORE, new OreConfiguration(sapphireOres, 3));
		register(context, OVERWORLD_SILVER_ORE_KEY, Feature.ORE, new OreConfiguration(silverOres, 4));

	}

	/**
	 *
	 */
	public static void register() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		FEATURES.register(bus);
	}

	public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MagicTreasures.MOD_ID, name));
	}

	private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
																						  ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
		context.register(key, new ConfiguredFeature<>(feature, configuration));
	}
	
//	private static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registry.FEATURE_REGISTRY, MagicTreasures.MOD_ID);
//	private static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, MagicTreasures.MOD_ID);
//	private static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, MagicTreasures.MOD_ID);
//
//	public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_TOPAZ_ORES = Suppliers.memoize(() -> List.of(
//			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MagicTreasuresBlocks.TOPAZ_ORE.get().defaultBlockState()),
//			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MagicTreasuresBlocks.DEEPSLATE_TOPAZ_ORE.get().defaultBlockState()))
//			);
//
//	public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_ONYX_ORES = Suppliers.memoize(() -> List.of(
//			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MagicTreasuresBlocks.ONYX_ORE.get().defaultBlockState()),
//			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MagicTreasuresBlocks.DEEPSLATE_ONYX_ORE.get().defaultBlockState()))
//			);
//
//	public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_JADEITE_ORES = Suppliers.memoize(() -> List.of(
//			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MagicTreasuresBlocks.JADEITE_ORE.get().defaultBlockState()),
//			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MagicTreasuresBlocks.DEEPSLATE_JADEITE_ORE.get().defaultBlockState()))
//	);
//
//	public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_RUBY_ORES = Suppliers.memoize(() -> List.of(
//			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MagicTreasuresBlocks.RUBY_ORE.get().defaultBlockState()),
//			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MagicTreasuresBlocks.DEEPSLATE_RUBY_ORE.get().defaultBlockState()))
//			);
//
//	public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_SAPPHIRE_ORES = Suppliers.memoize(() -> List.of(
//			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MagicTreasuresBlocks.SAPPHIRE_ORE.get().defaultBlockState()),
//			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MagicTreasuresBlocks.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState()))
//			);
//
//	public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_SILVER_ORES = Suppliers.memoize(() -> List.of(
//			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MagicTreasuresBlocks.SILVER_ORE.get().defaultBlockState()),
//			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MagicTreasuresBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState()))
//	);
//
//	// Configured features
//	public static final RegistryObject<ConfiguredFeature<?, ?>> TOPAZ_ORE_CONFIGURED = CONFIGURED_FEATURES.register("topaz_ore",
//			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_TOPAZ_ORES.get(), 3)));
//	
//	public static final RegistryObject<ConfiguredFeature<?, ?>> ONYX_ORE_CONFIGURED = CONFIGURED_FEATURES.register("onyx_ore",
//			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_ONYX_ORES.get(), 3)));
//
//	public static final RegistryObject<ConfiguredFeature<?, ?>> JADEITE_ORE_CONFIGURED = CONFIGURED_FEATURES.register("jadeite_ore",
//			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_JADEITE_ORES.get(), 3)));
//
//	public static final RegistryObject<ConfiguredFeature<?, ?>> RUBY_ORE_CONFIGURED = CONFIGURED_FEATURES.register("ruby_ore",
//			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_RUBY_ORES.get(), 3)));
//	
//	public static final RegistryObject<ConfiguredFeature<?, ?>> SAPPHIRE_ORE_CONFIGURED = CONFIGURED_FEATURES.register("sapphire_ore",
//			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_SAPPHIRE_ORES.get(), 3)));
//
//	public static final RegistryObject<ConfiguredFeature<?, ?>> SILVER_ORE_CONFIGURED = CONFIGURED_FEATURES.register("silver_ore",
//			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_SILVER_ORES.get(), 4)));
//
//	/*
//	 * placement
//	 * NOTE: the aboveBottom values are OFFSETS, ex -64 + x = placement depth
//	 * @54 -> -64 + 54 = -10, @ 84 -> -64 + 84 = 20, therefor the range is from y = -10 to 20
//	 */
//	public static final RegistryObject<PlacedFeature> TOPAZ_ORE_PLACED = PLACED_FEATURES.register("topaz_ore_placed",
//			() -> new PlacedFeature(TOPAZ_ORE_CONFIGURED.getHolder().get(), MagicTreasuresOrePlacement.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(34),
//					VerticalAnchor.aboveBottom(94)))));
//	
//	public static final RegistryObject<PlacedFeature> ONYX_ORE_PLACED = PLACED_FEATURES.register("onyx_ore_placed",
//			() -> new PlacedFeature(ONYX_ORE_CONFIGURED.getHolder().get(), MagicTreasuresOrePlacement.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(34),
//					VerticalAnchor.aboveBottom(94)))));
//
//	public static final RegistryObject<PlacedFeature> JADEITE_ORE_PLACED = PLACED_FEATURES.register("jadeite_ore_placed",
//			() -> new PlacedFeature(JADEITE_ORE_CONFIGURED.getHolder().get(), MagicTreasuresOrePlacement.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(24),
//					VerticalAnchor.aboveBottom(114)))));
//
//	public static final RegistryObject<PlacedFeature> RUBY_ORE_PLACED = PLACED_FEATURES.register("ruby_ore_placed",
//			() -> new PlacedFeature(RUBY_ORE_CONFIGURED.getHolder().get(), MagicTreasuresOrePlacement.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(14), 
//					VerticalAnchor.aboveBottom(70)))));
//	
//	public static final RegistryObject<PlacedFeature> SAPPHIRE_ORE_PLACED = PLACED_FEATURES.register("sapphire_ore_placed",
//			() -> new PlacedFeature(SAPPHIRE_ORE_CONFIGURED.getHolder().get(), MagicTreasuresOrePlacement.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(14), 
//					VerticalAnchor.aboveBottom(70)))));
//
//	public static final RegistryObject<PlacedFeature> SILVER_ORE_PLACED = PLACED_FEATURES.register("silver_ore_placed",
//			() -> new PlacedFeature(SILVER_ORE_CONFIGURED.getHolder().get(), MagicTreasuresOrePlacement.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(34),
//					VerticalAnchor.aboveBottom(90)))));
//
//	/**
//	 * 
//	 */
//	public static void register() {
//		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
//		CONFIGURED_FEATURES.register(bus);
//		PLACED_FEATURES.register(bus);
//		FEATURES.register(bus);
//
//	}
}
