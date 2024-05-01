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
package mod.gottsch.forge.magic_things;

import mod.gottsch.forge.magic_things.core.config.Config;
import mod.gottsch.forge.magic_things.core.setup.CommonSetup;
import mod.gottsch.forge.magic_things.core.setup.Registration;
import mod.gottsch.forge.magic_things.core.spell.MagicThingsSpells;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * 
 * @author Mark Gottschling on May 3, 2023
 *
 */
@Mod(value = MagicThings.MOD_ID)
public class MagicThings {
	// logger
	public static Logger LOGGER = LogManager.getLogger(MagicThings.MOD_ID);

	public static final String MOD_ID = "gealdorcraft"; //"magicthings"

	/**
	 * 
	 */
	public MagicThings() {
		// TODO change to the new Echelons style of config setup
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);

		// force load of static blocks
		MagicThingsSpells.init();

		// register the deferred registries
        Registration.init();
        
		// Register the setup method for modloading
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		eventBus.addListener(CommonSetup::init);
	}
}
