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
package mod.gottsch.forge.magic_things.core.item;

import mod.gottsch.forge.magic_things.core.registry.StoneRegistry;
import mod.gottsch.forge.magic_things.core.util.LangUtil;
import mod.gottsch.forge.magic_things.core.util.MathUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by Mark Gottschling on 7/4/2023
 */
public class Gemstone extends Item {

    public Gemstone(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        return ((MutableComponent)super.getName(stack)).withStyle(getNameColor());
    }

    public ChatFormatting getNameColor() {
        return ChatFormatting.WHITE;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component>tooltip, TooltipFlag flag) {
        super.appendHoverText(itemStack, level, tooltip, flag);
        tooltip.add(new TranslatableComponent(LangUtil.tooltip("gemstone.usage")).withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        tooltip.add(new TranslatableComponent(LangUtil.NEWLINE));

        // TODO all this needs to be added to TooltipEvent
        LangUtil.appendAdvancedHoverText(tooltip, tt -> {

            StoneRegistry.getStoneTier(itemStack.getItem()).ifPresent(tier -> {
//                tt.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("divider")).withStyle(ChatFormatting.GRAY)));
//                tt.add(new TranslatableComponent(LangUtil.NEWLINE));

                StoneRegistry.getRarity(itemStack.getItem()).ifPresent(r -> {
                    tt.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("gemstone.rarity"), ChatFormatting.GOLD + WordUtils.capitalizeFully(r.getName()))));
                });
//                tt.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("gemstone.tier"), ChatFormatting.GOLD + WordUtils.capitalizeFully(tier.getName().getPath()))));
                tt.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("gemstone.mana"), ChatFormatting.BLUE + String.valueOf(tier.getMana()))));
                tt.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("gemstone.recharges"), ChatFormatting.GREEN + String.valueOf(tier.getRecharges()))));

                if (tier.getSpellCostFactor() < 1.0) {
                    tt.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("gemstone.cost_factor"), ChatFormatting.AQUA + LangUtil.negativePercent(tier.getSpellCostFactor()))));
                } else if (tier.getSpellCostFactor() > 1.0) {
                    tt.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("gemstone.cost_factor"), ChatFormatting.AQUA + LangUtil.positivePercent(tier.getSpellCostFactor()))));
                }

                if (tier.getSpellCooldownFactor() < 1.0) {
                    tt.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("gemstone.cooldown_factor"), ChatFormatting.AQUA + LangUtil.negativePercent(tier.getSpellCooldownFactor()))));
                } else if (tier.getSpellCooldownFactor() > 1.0) {
                    tt.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("gemstone.cooldown_factor"), ChatFormatting.AQUA + LangUtil.positivePercent(tier.getSpellCooldownFactor()))));
                }

                if (tier.getSpellEffectAmountFactor() < 1.0) {
                    tt.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("gemstone.effect_amount_factor"), ChatFormatting.AQUA + LangUtil.negativePercent(tier.getSpellEffectAmountFactor()))));
                } else if (tier.getSpellEffectAmountFactor() > 1.0) {
                    tt.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("gemstone.effect_amount_factor"), ChatFormatting.AQUA + LangUtil.positivePercent(tier.getSpellEffectAmountFactor()))));
                }

                // frequency
                // NOTE the sign changes, is different than the others
                if (tier.getSpellFrequencyFactor() > 1.0) {
                    tt.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("gemstone.frequency_factor"), ChatFormatting.AQUA + LangUtil.negativePercent(tier.getSpellFrequencyFactor()))));
                } else if (tier.getSpellFrequencyFactor() < 1.0) {
                    tt.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("gemstone.frequency_factor"), ChatFormatting.AQUA + LangUtil.positivePercent(tier.getSpellFrequencyFactor()))));
                }

                // range
                if (tier.getSpellRangeFactor() < 1.0) {
                    tt.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("gemstone.range_factor"), ChatFormatting.AQUA + LangUtil.negativePercent(tier.getSpellRangeFactor()))));
                } else if (tier.getSpellRangeFactor() > 1.0) {
                    tt.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("gemstone.range_factor"), ChatFormatting.AQUA + LangUtil.positivePercent(tier.getSpellRangeFactor()))));
                }

                tt.add(new TranslatableComponent(LangUtil.NEWLINE));
            });
        });
    }
}
