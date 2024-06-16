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
package mod.gottsch.forge.magic_things;

import mod.gottsch.forge.magic_things.core.config.Config;
import mod.gottsch.forge.magic_things.core.integration.MagicThingsIntegrations;
import mod.gottsch.forge.magic_things.core.loot.MagicThingsLootFunctions;
import mod.gottsch.forge.magic_things.core.network.MagicThingsNetworking;
import mod.gottsch.forge.magic_things.core.setup.CommonSetup;
import mod.gottsch.forge.magic_things.core.setup.Registration;
import mod.gottsch.forge.magic_things.core.spell.MagicThingsSpells;
import mod.gottsch.forge.treasure2.core.network.TreasureNetworking;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

/**
 * 
 * @author Mark Gottschling on May 3, 2023
 *
 */
@Mod(value = MagicThings.MOD_ID)
public class MagicThings {
	// logger
	public static Logger LOGGER = LogManager.getLogger(MagicThings.MOD_ID);

	public static final String MOD_ID = "magic_things"; //"magic_things"

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

		eventBus.addListener(MagicThingsNetworking::common);
		// TODO anything that is registering magic things only, like jewelry material tiers, in common setup can and needs to be called before Registration.init()
		eventBus.addListener(CommonSetup::init);
		eventBus.addListener(this::interModComms);
	}

	/**
	 *
	 * @param event
	 */
	public void interModComms(InterModEnqueueEvent event) {
//		InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CHARM.getMessageBuilder().build());
		InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.NECKLACE.getMessageBuilder().build());
		InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.RING.getMessageBuilder().build());
		InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BRACELET.getMessageBuilder().build());
		InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BELT.getMessageBuilder().build());
	}
}
