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
package mod.gottsch.forge.magic_treasures.core.event;

import mod.gottsch.forge.magic_treasures.MagicTreasures;
import mod.gottsch.forge.magic_treasures.core.world.feature.gen.MagicTreasuresOreGeneration;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.nio.file.Path;

/**
 * 
 * @author Mark Gottschling on May 10, 2024
 *
 */
@EventBusSubscriber(modid = MagicTreasures.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class WorldEventHandler {

	private static Path worldSavePath;
	private static boolean isLoaded = false;

	@SubscribeEvent
	public static void onBiomeLoading(final BiomeLoadingEvent event) {
		/* 
		 * NOTE: 
		 * generation must occur in the correct order according to GenerationStep.Decoration
		 */
		MagicTreasuresOreGeneration.generateOres(event);
	}

}
