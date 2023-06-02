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
package mod.gottsch.forge.gealdorcraft.core.capability;

import mod.gottsch.forge.gealdorcraft.core.GealdorCraft;
import mod.gottsch.forge.gealdorcraft.core.item.JewelryMaterialTier;
import mod.gottsch.forge.gealdorcraft.core.item.JewelrySizeTier;
import mod.gottsch.forge.gealdorcraft.core.item.JewelryStoneTier;
import mod.gottsch.forge.gealdorcraft.core.item.JewelryType;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Mark Gottschling on 6/1/2023
 */
public class JewelryCapability implements ICapabilitySerializable<CompoundTag> {
        public static final ResourceLocation ID = new ResourceLocation(GealdorCraft.MOD_ID, "jewelry");

    // reference of handler/data for easy access
    private final IJewelryHandler handler;// = new JewelryHandler();
    // holder of the handler/data
    private final LazyOptional<IJewelryHandler> optional;// = LazyOptional.of(() -> handler);

    public JewelryCapability() {
        this(new JewelryHandler(JewelryType.UNKNOWN, JewelryMaterialTier.NONE,
                JewelryStoneTier.NONE, JewelrySizeTier.UNKNOWN));
    }

    public JewelryCapability(IJewelryHandler handler) {
        this.handler = handler;
        optional = LazyOptional.of(() -> handler);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == GealdorCapabilities.JEWELRY_CAPABILITY) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return (CompoundTag)handler.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        handler.deserializeNBT(tag);
    }

    /**
     *
     * @param event
     */
    public static void register(RegisterCapabilitiesEvent event) {
        event.register(IJewelryHandler.class);
    }
}
