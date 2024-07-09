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
package mod.gottsch.forge.magic_treasures.core.item;

import mod.gottsch.forge.magic_treasures.MagicTreasures;
import mod.gottsch.forge.magic_treasures.core.capability.IJewelryHandler;
import mod.gottsch.forge.magic_treasures.core.capability.JewelryCapability;
import mod.gottsch.forge.magic_treasures.core.capability.MagicTreasuresCapabilities;
import mod.gottsch.forge.magic_treasures.core.util.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Mark Gottschling on 5/29/2023
 */
public class Jewelry extends Item implements IJewelry{
    private String loreKey;

    /**
     *
     * @param properties
     */
    public Jewelry(Properties properties) {
        super(properties.stacksTo(1));
    }

    /**
     * base method. items should override this to include fully populated capability.
     * @param stack The ItemStack
     * @param tag   NBT of this item serialized, or null.
     * @return
     */
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag tag) {
        return new JewelryCapability();
    }

    /**
     *
     */
    @Override
    public boolean isFoil(ItemStack stack) {
        return stack.getCapability(MagicTreasuresCapabilities.JEWELRY_CAPABILITY).map(handler -> !handler.getSpells().isEmpty()).orElse(false);
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
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(new TranslatableComponent(LangUtil.NEWLINE));

        // hide when [shift]
        LangUtil.appendHideableHoverText(tooltip, tt -> {
            tooltip.add(new TranslatableComponent(LangUtil.tooltip("jewelry.usage")).withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltip.add(new TranslatableComponent(LangUtil.NEWLINE));

            if (StringUtils.isNotBlank(getLoreKey())) {
                appendLoreHoverText(stack, level, tooltip, flag);
            }
        });

        // add handler tooltips
        stack.getCapability(MagicTreasuresCapabilities.JEWELRY_CAPABILITY).ifPresent(handler -> {
            handler.appendHoverText(stack, level, tooltip, flag);
        });
    }

    @Override
    public void appendLoreHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {

            // lore may be multiple lines, so separate on \n and add to tooltip
            TranslatableComponent lore = new TranslatableComponent(LangUtil.tooltip(getLoreKey()));
            for (String s : lore.getString().split("~")) {
                tooltip.add(new TextComponent(LangUtil.INDENT2)
                        .append(new TranslatableComponent(s))
                        .withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.ITALIC));
            }
        tooltip.add(new TextComponent(LangUtil.NEWLINE));
    }

    /**
     * NOTE getNBTShareTag() and readNBTShareTag() are required to sync item capabilities server -> client. I needed this when holding charms in hands and then swapping hands
     * or having the client update when the Anvil GUI is open.
     */
    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        IJewelryHandler handler = stack.getCapability(MagicTreasuresCapabilities.JEWELRY_CAPABILITY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
        try {
            tag = (CompoundTag) handler.serializeNBT();
        } catch (Exception e) {
            MagicTreasures.LOGGER.error("Unable to write state to NBT:", e);
        }
        return tag;
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundTag tag) {
        super.readShareTag(stack, tag);

        if (tag instanceof CompoundTag) {
            IJewelryHandler handler = stack.getCapability(MagicTreasuresCapabilities.JEWELRY_CAPABILITY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
            handler.deserializeNBT((CompoundTag) tag);
        }
    }

    @Override
    public String getLoreKey() {
        return loreKey;
    }
    @Override
    public Item setLoreKey(String loreKey) {
        this.loreKey = loreKey;
        return this;
    }
}
