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
package mod.gottsch.forge.magic_things.core.spell;

import mod.gottsch.forge.gottschcore.enums.IRarity;
import mod.gottsch.forge.gottschcore.spatial.ICoords;
import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.capability.IJewelryHandler;
import mod.gottsch.forge.magic_things.core.capability.MagicThingsCapabilities;
import mod.gottsch.forge.magic_things.core.rarity.MagicThingsRarity;
import mod.gottsch.forge.magic_things.core.spell.cost.CostEvaluator;
import mod.gottsch.forge.magic_things.core.spell.cost.ICostEvaluator;
import mod.gottsch.forge.magic_things.core.util.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Spells are a single instance within the mod like Blocks and Items.
 * They can generate a SpellEntity which has individual state like BlockEntity and ItemEntity.
 */
public abstract class Spell implements ISpell {
    protected static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#");
    public static final int TICKS_PER_SECOND = 20;
    public static final ChatFormatting SPELL_COLOR = ChatFormatting.AQUA;
    public static final ChatFormatting SPELL_DESC_COLOR = ChatFormatting.LIGHT_PURPLE;

    // TODO getEffectAmount needs to take into account the material and stone
    // -- see CostEvaluator

    private final ResourceLocation name;
    private final String type;
    private final int level;
    private final IRarity rarity;

    private double spellCost;
    private double effectAmount;
    private int duration;
    // TODO frequency and cooldown are mutually exclusive, so make classes for each of them
    private long frequency;
    private double range;
    private long cooldown;
    private boolean effectStackable;
    private boolean exclusive;
    private int priority;

    private ICostEvaluator costEvaluator;

    /*
     * builder constructor
     */
    public Spell(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.level = builder.level;
        this.rarity = builder.rarity;
        this.spellCost = builder.spellCost;
        this.effectAmount = builder.effectAmount;
        this.duration = builder.duration;
        this.frequency = builder.frequency;
        this.range = builder.range;
        this.cooldown = builder.cooldown;
        this.effectStackable = builder.effectStackable;
        this.exclusive = builder.exclusive;
        this.priority = builder.priority;
        this.costEvaluator = builder.costEvaluator;
    }

    @Override
    public SpellEntity entity() {
        return new SpellEntity(this);
    }

    /**
     * wrapper method that checks for the existence of a ICostEvaluator else uses cost property
     * @param amount
     * @return
     */
    public double applyCost(Level level, Random random, ICoords coords, ICastSpellContext context, double amount) {

        if (getCostEvaluator() != null) {
//			Treasure.logger.debug("entity -> {} has a cost eval -> {}", entity.getClass().getSimpleName(), entity.getCostEvaluator().getClass().getSimpleName());
            return getCostEvaluator().apply(level, random, coords, context, amount);
        }
        else {
            IJewelryHandler handler = context.getJewelry().getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).orElseThrow(IllegalStateException::new);
            MagicThings.LOGGER.debug("Spell does not have a cost eval.");
            handler.setMana(Mth.clamp(handler.getMana() - 1.0,  0D, handler.getMana()));
        }
        return amount;
    }

    public double modifySpellCost(ItemStack jewelry) {
        IJewelryHandler handler = jewelry.getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).orElseThrow(IllegalStateException::new);
        return handler.modifySpellCost(getSpellCost());
//        JewelryStoneTier stoneTier = handler.getStoneTier();
//        double materialModifier = handler.getMaterial().getSpellCostFactor();
//
//        return getSpellCost() * materialModifier * (stoneTier != null ? stoneTier.getSpellCostFactor() : 1);
    }

    public double modifyEffectAmount(ItemStack jewelry) {
        IJewelryHandler handler = jewelry.getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).orElseThrow(IllegalStateException::new);
        return handler.modifyEffectAmount(getEffectAmount());
        //        JewelryStoneTier stoneTier = handler.getStoneTier();
//        double materialModifier = handler.getMaterial().getSpellEffectAmountFactor();
//        return getEffectAmount() * materialModifier * (stoneTier != null ? stoneTier.getSpellEffectAmountFactor() : 1);
    }

    public long modifyCooldown(ItemStack jewelry) {
        IJewelryHandler handler = jewelry.getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).orElseThrow(IllegalStateException::new);
        return handler.modifyCooldown(getCooldown());
        //        JewelryStoneTier stoneTier = handler.getStoneTier();
//        double materialModifier = handler.getMaterial().getSpellCooldownFactor();
//        return (long)(getCooldown() * materialModifier * (stoneTier != null ? stoneTier.getSpellCooldownFactor() : 1));
    }

    public long modifyFrequency(ItemStack jewelry) {
        IJewelryHandler handler = jewelry.getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).orElseThrow(IllegalStateException::new);
        return handler.modifyFrequency(getFrequency());
//        JewelryStoneTier stoneTier = handler.getStoneTier();
//        double materialModifier = handler.getMaterial().getSpellFrequencyFactor();
//
//        return (long)(
//                getFrequency() * materialModifier * (stoneTier != null ? stoneTier.getSpellFrequencyFactor() : 1));
    }

    public double modifyRange(ItemStack jewelry) {
        IJewelryHandler handler = jewelry.getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).orElseThrow(IllegalStateException::new);
        return handler.modifyRange(getRange());
//                JewelryStoneTier stoneTier = handler.getStoneTier();
//        double materialModifier = handler.getMaterial().getSpellRangeFactor();
//
//        return getRange() * materialModifier * (stoneTier != null ? stoneTier.getSpellRangeFactor() : 1);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void addInformation(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flagIn, SpellEntity entity) {
        tooltip.add(getLabel());
        getDesc(stack).ifPresent(tooltip::add);
    }

    private Component getLabel() {
        MutableComponent label = new TranslatableComponent(LangUtil.tooltip("spell.name.") + getName().getPath().toLowerCase());
        label.append(" ").append((this.effectStackable ? "+" : ""));
        return new TranslatableComponent(LangUtil.INDENT2).append(label.withStyle(getSpellLabelColor()).withStyle(ChatFormatting.BOLD));
    }

    // a short desc of its effect ex "Heals 1hp / 10 sec"
    private Optional<Component> getDesc(ItemStack jewelry) {
        Component desc = getSpellDesc(jewelry);
        return desc != null ? Optional.of(new TranslatableComponent(
                LangUtil.INDENT4).append(desc)
                .withStyle(ChatFormatting.ITALIC).withStyle(getSpellDescColor()))
                : Optional.empty();
    }

    // TODO would be nice if this returned Optional instead
    /**
     * Implemented by concrete Spell.
     * @return
     */
    @Override
    public Component getSpellDesc() { return null;}

    @Override
    public Component getSpellDesc(ItemStack jewelry) { return null;}

    @Override
    public ChatFormatting getSpellLabelColor() {
        return SPELL_COLOR;
    }

    @Override
    public ChatFormatting getSpellDescColor() {
        return SPELL_DESC_COLOR;
    }

    /**
     *
     */
    abstract public static class Builder {
        public ResourceLocation name;
        public String type;
        public int level;
        public IRarity rarity;
        public double spellCost;
        public double effectAmount;
        public int duration;
        public long frequency;
        public double range;
        public long cooldown;
        public boolean effectStackable;
        public boolean exclusive;
        public int priority;

        public ICostEvaluator costEvaluator;

        public Builder(ResourceLocation name, String type, int level) {
            this(name, type, level, MagicThingsRarity.COMMON);
        }

        public Builder(ResourceLocation name, String type, int level, IRarity rarity) {
            this.name = name;
            this.type = type;
            this.level = level;
            this.rarity = rarity;
            this.costEvaluator = new CostEvaluator();
        }

        abstract ISpell build();

        public Builder with(Consumer<Builder> builder)  {
            builder.accept(this);
            return this;
        }
    }

    ///////////////////////////////
    @Override
    public ResourceLocation getName() {
        return name;
    }
//
//    public void setName(ResourceLocation name) {
//        this.name = name;
//    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public IRarity getRarity() {
        return this.rarity;
    }

    @Override
    public double getSpellCost() {
        return spellCost;
    }

    @Override
    public void setSpellCost(double spellCost) {
        this.spellCost = spellCost;
    }

    public double getEffectAmount() {
        return effectAmount;
    }

    public void setEffectAmount(double effectAmount) {
        this.effectAmount = effectAmount;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public long getFrequency() {
        return frequency;
    }

    @Override
    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }

    @Override
    public double getRange() {
        return range;
    }

    @Override
    public void setRange(double range) {
        this.range = range;
    }

    @Override
    public long getCooldown() {
        return cooldown;
    }

    @Override
    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }

    @Override
    public boolean isExclusive() {
        return exclusive;
    }

    @Override
    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    @Override
    public ICostEvaluator getCostEvaluator() {
        return costEvaluator;
    }

    @Override
    public void setCostEvaluator(ICostEvaluator costEvaluator) {
        this.costEvaluator = costEvaluator;
    }

    @Override
    public boolean isEffectStackable() {
        return effectStackable;
    }

    @Override
    public void setEffectStackable(boolean effectStackable) {
        this.effectStackable = effectStackable;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Spell{" +
                "priority=" + priority +
                ", exclusive=" + exclusive +
                ", effectStackable=" + effectStackable +
                ", cooldown=" + cooldown +
                ", range=" + range +
                ", frequency=" + frequency +
                ", duration=" + duration +
                ", effectAmount=" + effectAmount +
                ", spellCost=" + spellCost +
                ", rarity=" + rarity +
                ", level=" + level +
                ", type='" + type + '\'' +
                ", name=" + name +
                '}';
    }
}
