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
package mod.gottsch.forge.magic_things.datagen;

import mod.gottsch.forge.magic_things.MagicThings;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

/**
 * Created by Mark Gottschling on 6/1/2023
 */
@Mod.EventBusSubscriber(modid = MagicThings.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new Recipes(generator));
//            generator.addProvider(new TutLootTables(generator));
        	MagicThingsBlockTagsProvider blockTags = new MagicThingsBlockTagsProvider(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new MagicThingsItemTagsProvider(generator, blockTags, event.getExistingFileHelper()));
//            generator.addProvider(new Magic ThingsBiomeTagsProvider(generator, event.getExistingFileHelper()));

        }
        if (event.includeClient()) {
//        	 generator.addProvider(new BlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new ItemModelsProvider(generator, event.getExistingFileHelper()));
            generator.addProvider(new LanguageGen(generator, "en_us"));
        }
    }
}