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
package mod.gottsch.forge.magic_treasures.core.world.feature.gen;

import mod.gottsch.forge.magic_treasures.core.world.feature.MagicTreasuresConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

/**
 * 
 * @author Mark Gottschling on May 10, 2024
 *
 */
public class MagicTreasuresOreGeneration {
	
	public static void generateOres(final BiomeLoadingEvent event) {
		List<Holder<PlacedFeature>> base = 
				event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);
		
		base.add(MagicTreasuresConfiguredFeatures.TOPAZ_ORE_PLACED.getHolder().get());
		base.add(MagicTreasuresConfiguredFeatures.ONYX_ORE_PLACED.getHolder().get());
		base.add(MagicTreasuresConfiguredFeatures.JADEITE_ORE_PLACED.getHolder().get());
		base.add(MagicTreasuresConfiguredFeatures.RUBY_ORE_PLACED.getHolder().get());
		base.add(MagicTreasuresConfiguredFeatures.SAPPHIRE_ORE_PLACED.getHolder().get());
		base.add(MagicTreasuresConfiguredFeatures.SILVER_ORE_PLACED.getHolder().get());
	}
}
