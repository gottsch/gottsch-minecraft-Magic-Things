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
package mod.gottsch.forge.magic_things.core.capability;

import mod.gottsch.forge.magic_things.MagicThings;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Created by Mark Gottschling on 6/1/2023
 */
public class MagicThingsCapabilities {
    public static Capability<IJewelryHandler> JEWELRY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {	});
    public static Capability<IJewelryHandler> MANA_WELL_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {	});

    /**
     *
     */
    @SubscribeEvent
    public static void register(final RegisterCapabilitiesEvent event) {
        JewelryCapability.register(event);
    }

    /**
     * Forge Bus Event Subscriber class
     */
    @Mod.EventBusSubscriber(modid = MagicThings.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeBusSubscriber {
        /*
         * NOTE called before entity is spawned in world
         */
        @SubscribeEvent
        public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
            Entity entity = event.getObject();
                // TODO ref the JewelryRegistry when ready
//            if (EchelonManager.isValidEntity(entity)) {
//                event.addCapability(JewelryCapability.ID, new JewelryCapability());
//            }
        }
    }
}
