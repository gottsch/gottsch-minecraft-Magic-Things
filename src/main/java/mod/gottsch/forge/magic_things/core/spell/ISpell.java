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
package mod.gottsch.forge.magic_things.core.spell;

import mod.gottsch.forge.gottschcore.enums.IRarity;
import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.spell.cost.CostEvaluator;
import mod.gottsch.forge.magic_things.core.spell.cost.ICostEvaluator;
import mod.gottsch.forge.magic_things.core.util.ModUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

/**
 * Created by Mark Gottschling on 5/3/2023
 */
public interface ISpell {
    public static final String SPELL = "spell";

    public static final String NAME = "name";
    public static final String RARITY = "rarity";
    public static final String SPELL_COST = "spellCost";
    public static final String DURATION = "duration";
    public static final String FREQUENCY = "frequency";
    public static final String EFFECT_AMOUNT = "effectAmount";
    public static final String COOLDOWN = "cooldown";
    public static final String RANGE = "range";
    public static final String COST_EVALUATOR = "costEvaluator";

    @Deprecated
    public static final String EXCLUSIVE = "exclusive";

    public SpellEntity entity();

    @SuppressWarnings("deprecation")
    void addInformation(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flagIn, SpellEntity entity);

    ChatFormatting getSpellLabelColor();

    ChatFormatting getCharmDescColor();

    // final properties
    public ResourceLocation getName();
    public String getType();
    public int getLevel();
    public IRarity getRarity();

    // mutator properties
    double getSpellCost();
    void setSpellCost(double spellCost);

    int getDuration();
    void setDuration(int duration);

    int getFrequency();
    void setFrequency(int frequency);

    double getRange();
    void setRange(double range);

    double getCooldown();
    void setCooldown(double cooldown);

    double getEffectAmount();
    void setEffectAmount(double amount);

    ICostEvaluator getCostEvaluator();
    void setCostEvaluator(ICostEvaluator costEvaluator);

    boolean isExclusive();
    void setExclusive(boolean exclusive);

    void update(ISpell spell);

    /**
     *
     * @param tag
     * @return
     */
    default public boolean load(CompoundTag tag) {
        if (tag.contains(NAME)) {
            String name = tag.getString(NAME);
            ResourceLocation location = ModUtil.asLocation(name);
            // TODO name can't be final if going to load it.... maybe a SpellFactory that loads Spells.
//            setName(location);
        }

        if (tag.contains(SPELL_COST)) {
            setSpellCost(tag.getDouble(SPELL_COST));
        }

        if (tag.contains(DURATION)) {
            setDuration(tag.getInt(DURATION));
        }
        if (tag.contains(FREQUENCY)) {
            setFrequency(tag.getInt(FREQUENCY));
        }
        if (tag.contains(EFFECT_AMOUNT)) {
            setEffectAmount(tag.getDouble(EFFECT_AMOUNT));
        }
        if (tag.contains(COOLDOWN)) {
            setCooldown(tag.getDouble(COOLDOWN));
        }
        if (tag.contains(RANGE)) {
            setRange(tag.getDouble(RANGE));
        }
        if (tag.contains(COST_EVALUATOR) && tag.getCompound(COST_EVALUATOR).contains("costClass")) {
            try {
                CompoundTag costTag = tag.getCompound(COST_EVALUATOR);

                String costEvalClass = costTag.getString("costClass");
//					Treasure.logger.warn("using parent cost eval class -> {}", costEvalClass);
                Object o = Class.forName(costEvalClass).newInstance();
                ((ICostEvaluator)o).load(tag);
                setCostEvaluator((ICostEvaluator)o);
            }
            catch(Exception e) {
                MagicThings.LOGGER.warn("unable to create cost evaluator from class string -> {}", tag.getCompound(COST_EVALUATOR).getString("costClass"));
                MagicThings.LOGGER.error(e);
                setCostEvaluator(new CostEvaluator());
            }
        }
        else {
            setCostEvaluator(new CostEvaluator());
        }

        if (tag.contains(EXCLUSIVE)) {
            setExclusive(tag.getBoolean(EXCLUSIVE));
        }

        return true;
    }

    /**
     * saves directly to the tag provided. ie does not make a new tag and append to tag param
     * @param tag
     * @return
     */
    default public CompoundTag save(CompoundTag tag) {
        if (ObjectUtils.isNotEmpty(getName())) {
            tag.putString(NAME, getName().toString());
        }

        // TODO finish...

        return tag;
    }

    boolean isEffectStackable();

    void setEffectStackable(boolean effectStackable);
}
