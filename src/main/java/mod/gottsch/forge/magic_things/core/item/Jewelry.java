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
package mod.gottsch.forge.magic_things.core.item;

import mod.gottsch.forge.magic_things.core.capability.JewelryCapability;
import mod.gottsch.forge.magic_things.core.capability.MagicThingsCapabilities;
import mod.gottsch.forge.magic_things.core.util.LangUtil;
import mod.gottsch.forge.treasure2.core.capability.TreasureCapabilities;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.util.List;

/**
 * Created by Mark Gottschling on 5/29/2023
 */
public class Jewelry extends Item implements IJewelry{

    /**
     *
     * @param properties
     */
    public Jewelry(Properties properties) {
        super(properties);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {

        return new JewelryCapability();
    }

    @Override
    public Component getName(ItemStack itemStack) {
        if (isNamed()) {
            return new TranslatableComponent(this.getDescriptionId(itemStack)).withStyle(ChatFormatting.YELLOW);
        } else {
            return new TranslatableComponent(this.getDescriptionId(itemStack));
        }
    }

    /**
     *
     */
    @SuppressWarnings("deprecation")
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        tooltip.add(new TranslatableComponent(LangUtil.tooltip("jewelry.usage")).withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));

//        // add the durability tooltips
//        stack.getCapability(MagicThingsCapabilities.DURABILITY).ifPresent(cap -> {
//            cap.appendHoverText(stack, world, tooltip, flag);
//        });

        // add spells tooltips
        stack.getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).ifPresent(handler -> {
            handler.appendHoverText(stack, world, tooltip, flag);
        });
    }
}
