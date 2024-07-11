
package mod.gottsch.forge.magic_treasures.core.world.feature;

import com.google.common.base.Suppliers;
import mod.gottsch.forge.magic_treasures.MagicTreasures;
import mod.gottsch.forge.magic_treasures.core.block.MagicTreasuresBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

/**
 * 
 * @author Mark Gottschling on May 10, 2024
 *
 */
public class MagicTreasuresConfiguredFeatures {
	private static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registry.FEATURE_REGISTRY, MagicTreasures.MOD_ID);
	private static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, MagicTreasures.MOD_ID);
	private static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, MagicTreasures.MOD_ID);

	public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_TOPAZ_ORES = Suppliers.memoize(() -> List.of(
			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MagicTreasuresBlocks.TOPAZ_ORE.get().defaultBlockState()),
			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MagicTreasuresBlocks.DEEPSLATE_TOPAZ_ORE.get().defaultBlockState()))
			);

	public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_ONYX_ORES = Suppliers.memoize(() -> List.of(
			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MagicTreasuresBlocks.ONYX_ORE.get().defaultBlockState()),
			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MagicTreasuresBlocks.DEEPSLATE_ONYX_ORE.get().defaultBlockState()))
			);

	public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_JADEITE_ORES = Suppliers.memoize(() -> List.of(
			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MagicTreasuresBlocks.JADEITE_ORE.get().defaultBlockState()),
			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MagicTreasuresBlocks.DEEPSLATE_JADEITE_ORE.get().defaultBlockState()))
	);

	public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_RUBY_ORES = Suppliers.memoize(() -> List.of(
			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MagicTreasuresBlocks.RUBY_ORE.get().defaultBlockState()),
			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MagicTreasuresBlocks.DEEPSLATE_RUBY_ORE.get().defaultBlockState()))
			);

	public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_SAPPHIRE_ORES = Suppliers.memoize(() -> List.of(
			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MagicTreasuresBlocks.SAPPHIRE_ORE.get().defaultBlockState()),
			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MagicTreasuresBlocks.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState()))
			);

	public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_SILVER_ORES = Suppliers.memoize(() -> List.of(
			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MagicTreasuresBlocks.SILVER_ORE.get().defaultBlockState()),
			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MagicTreasuresBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState()))
	);

	// Configured features
	public static final RegistryObject<ConfiguredFeature<?, ?>> TOPAZ_ORE_CONFIGURED = CONFIGURED_FEATURES.register("topaz_ore",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_TOPAZ_ORES.get(), 3)));
	
	public static final RegistryObject<ConfiguredFeature<?, ?>> ONYX_ORE_CONFIGURED = CONFIGURED_FEATURES.register("onyx_ore",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_ONYX_ORES.get(), 3)));

	public static final RegistryObject<ConfiguredFeature<?, ?>> JADEITE_ORE_CONFIGURED = CONFIGURED_FEATURES.register("jadeite_ore",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_JADEITE_ORES.get(), 3)));

	public static final RegistryObject<ConfiguredFeature<?, ?>> RUBY_ORE_CONFIGURED = CONFIGURED_FEATURES.register("ruby_ore",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_RUBY_ORES.get(), 3)));
	
	public static final RegistryObject<ConfiguredFeature<?, ?>> SAPPHIRE_ORE_CONFIGURED = CONFIGURED_FEATURES.register("sapphire_ore",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_SAPPHIRE_ORES.get(), 3)));

	public static final RegistryObject<ConfiguredFeature<?, ?>> SILVER_ORE_CONFIGURED = CONFIGURED_FEATURES.register("silver_ore",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_SILVER_ORES.get(), 4)));

	/*
	 * placement
	 * NOTE: the aboveBottom values are OFFSETS, ex -64 + x = placement depth
	 * @54 -> -64 + 54 = -10, @ 84 -> -64 + 84 = 20, therefor the range is from y = -10 to 20
	 */
	public static final RegistryObject<PlacedFeature> TOPAZ_ORE_PLACED = PLACED_FEATURES.register("topaz_ore_placed",
			() -> new PlacedFeature(TOPAZ_ORE_CONFIGURED.getHolder().get(), MagicTreasuresOrePlacement.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(34),
					VerticalAnchor.aboveBottom(94)))));
	
	public static final RegistryObject<PlacedFeature> ONYX_ORE_PLACED = PLACED_FEATURES.register("onyx_ore_placed",
			() -> new PlacedFeature(ONYX_ORE_CONFIGURED.getHolder().get(), MagicTreasuresOrePlacement.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(34),
					VerticalAnchor.aboveBottom(94)))));

	public static final RegistryObject<PlacedFeature> JADEITE_ORE_PLACED = PLACED_FEATURES.register("jadeite_ore_placed",
			() -> new PlacedFeature(JADEITE_ORE_CONFIGURED.getHolder().get(), MagicTreasuresOrePlacement.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(24),
					VerticalAnchor.aboveBottom(114)))));

	public static final RegistryObject<PlacedFeature> RUBY_ORE_PLACED = PLACED_FEATURES.register("ruby_ore_placed",
			() -> new PlacedFeature(RUBY_ORE_CONFIGURED.getHolder().get(), MagicTreasuresOrePlacement.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(14), 
					VerticalAnchor.aboveBottom(70)))));
	
	public static final RegistryObject<PlacedFeature> SAPPHIRE_ORE_PLACED = PLACED_FEATURES.register("sapphire_ore_placed",
			() -> new PlacedFeature(SAPPHIRE_ORE_CONFIGURED.getHolder().get(), MagicTreasuresOrePlacement.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(14), 
					VerticalAnchor.aboveBottom(70)))));

	public static final RegistryObject<PlacedFeature> SILVER_ORE_PLACED = PLACED_FEATURES.register("silver_ore_placed",
			() -> new PlacedFeature(SILVER_ORE_CONFIGURED.getHolder().get(), MagicTreasuresOrePlacement.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(34),
					VerticalAnchor.aboveBottom(90)))));

	/**
	 * 
	 */
	public static void register() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		CONFIGURED_FEATURES.register(bus);
		PLACED_FEATURES.register(bus);
		FEATURES.register(bus);

	}
}
