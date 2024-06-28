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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.api.MagicThingsApi;
import mod.gottsch.forge.magic_things.core.item.IJewelrySizeTier;
import mod.gottsch.forge.magic_things.core.item.IJewelryType;
import mod.gottsch.forge.magic_things.core.jewelry.JewelrySizeTier;
import mod.gottsch.forge.magic_things.core.item.JewelryType;
import mod.gottsch.forge.magic_things.core.jewelry.JewelryMaterial;
import mod.gottsch.forge.magic_things.core.jewelry.JewelryMaterials;
import mod.gottsch.forge.magic_things.core.jewelry.JewelryStoneTier;
import mod.gottsch.forge.magic_things.core.jewelry.JewelryStoneTiers;
import mod.gottsch.forge.magic_things.core.registry.StoneRegistry;
import mod.gottsch.forge.magic_things.core.spell.ISpell;
import mod.gottsch.forge.magic_things.core.spell.SpellEntity;
import mod.gottsch.forge.magic_things.core.spell.SpellRegistry;
import mod.gottsch.forge.magic_things.core.tag.MagicThingsTags;
import mod.gottsch.forge.magic_things.core.util.LangUtil;
import mod.gottsch.forge.magic_things.core.util.ModUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Created by Mark Gottschling on 6/1/2023
 */
public class JewelryHandler implements IJewelryHandler, INBTSerializable<Tag> {
    private static final String TYPE = "type";
//    private static final String MATERIAL_TIER = "materialTier";
    private static final String MATERIAL = "material";
    private static final String SIZE_TIER = "sizeTier";
    private static final String MAX_USES = "maxUses";
    private static final String USES = "uses";
    private static final String MAX_LEVEL = "maxLevel";
    private static final String MAX_MANA = "maxMana";
    private static final String MANA = "mana";
    private static final String MAX_REPAIRS = "maxRepairs";
    private static final String REPAIRS = "repairs";
    private static final String MAX_RECHARGES = "maxRecharges";
    private static final String RECHARGES = "recharges";
    private static final String STONE = "stone";
    private static final String SPELLS = "spells";
    private static final String BASE_NAME = "baseName";

    private static final String SPELL_COST_FACTOR = "spellCostFactor";
    private static final String SPELL_COOLDOWN_FACTOR = "spellCooldownFactor";
    private static final String SPELL_EFFECT_AMOUNT_FACTOR = "spellEffectAmountFactor";
    private static final String SPELL_FREQUENCY_FACTOR = "spellFrequencyFactor";
    private static final String SPELL_DURATION_FACTOR = "spellDurationFactor";
    private static final String SPELL_RANGE_FACTOR = "spellRangeFactor";

    private IJewelryType type;
    private JewelryMaterial material;
    private IJewelrySizeTier sizeTier;

    private int maxUses;
    private int uses;

    private int maxLevel;

    private double maxMana;
    private double mana;

    private int maxRepairs;
    private int repairs;

    private int maxRecharges;
    private int recharges;

//    private List<ResourceLocation> stones = new ArrayList<>(2);
    private ResourceLocation stone;
    private List<SpellEntity> spells = new ArrayList<>();

    // TODO can be moved out to JewelryNamingRegistry
    // -- storing a ResourceLocation key to a registry takes up more memory than just a string.
    // -- unless there is going to be a lot of properties here that deal with naming, it is better
    // -- just to leave it here.
    // TODO baseName doesn't really need to be persisted either
    private String baseName;
    // NOTE affixer is not persisted
    private Predicate<ItemStack> acceptsAffixer = p -> true;

    /*
     * factor overrides
     */
    private double spellCostFactor;
    private double spellEffectAmountFactor;
    private double spellFrequencyFactor;
    private double spellDurationFactor;
    private double spellCooldownFactor;
    private double spellRangeFactor;

    /*
     *
     */
    public static class Builder {
        public final IJewelryType type;
        public JewelryMaterial material;
        public IJewelrySizeTier sizeTier;
        public int maxUses = -1;
        public int uses;
        public int maxLevel = -1;
        public double maxMana = -1;
        public double mana;
        public int maxRepairs = -1;
        public int maxRecharges = -1;

        public ResourceLocation stone;
        public List<SpellEntity> spells = new ArrayList<>();
        public String baseName;
        public Predicate<ItemStack> acceptsAffixer = p -> true;

        public double spellCostFactor = -1.0;
        public double spellEffectAmountFactor = -1.0;
        public double spellFrequencyFactor = -1.0;
        public double spellDurationFactor = -1.0;
        public double spellCooldownFactor = -1.0;
        public double spellRangeFactor = -1.0;

        /**
         * TODO use this constructor
         * @param type
         * @param material
         */
        public Builder(IJewelryType type, JewelryMaterial material) {
            this.type = type;
            this.material = material;
            this.sizeTier = JewelrySizeTier.REGULAR;
        }

        public Builder(IJewelryType type, JewelryMaterial material, IJewelrySizeTier size) {
            this.type = type;
            this.material = material;
            this.sizeTier = size;
        }

        public Builder(IJewelryType type, JewelryMaterial material, ResourceLocation stone, IJewelrySizeTier sizeTier) {
            this.type = type;
            this.material = material;
            this.stone = stone;
            this.sizeTier = sizeTier;
        }

        public Builder with(Consumer<Builder> builder) {
            builder.accept(this);
            return this;
        }

        public Builder withStone(ResourceLocation stone) {
            this.stone = stone;
            return this;

        }

        public Builder withSize(JewelrySizeTier size) {
            this.sizeTier = size;
            return this;
        }

        public Builder setInfinite() {
            this.maxUses = Integer.MAX_VALUE;
            return this;
        }

        public IJewelryHandler build() {
            return new JewelryHandler(this);
        }
    }

    /**
     *
     * @param builder
     */
    public JewelryHandler(Builder builder) {
        // required properties
        this.type = builder.type;
        this.material = builder.material;
        this.stone = builder.stone;
        this.sizeTier = builder.sizeTier;

        // get the stone and stone tier
        Item stone = StoneRegistry.get(this.stone).orElse(Items.AIR);
        // determine the tier
        Optional<JewelryStoneTier> stoneTier = StoneRegistry.getStoneTier(stone);

        if (builder.maxUses <= 0) {
            this.maxUses = Math.round(material.getUses() * sizeTier.getUsesMultiplier());
        } else {
            this.maxUses = builder.maxUses;
        }
        this.uses = this.maxUses;

        if (builder.maxLevel <= 0) {
            this.maxLevel = material.getMaxLevel() + sizeTier.getCode();
        } else {
            this.maxLevel = builder.maxLevel;
        }
        if (builder.maxMana <= 0) {
            int mana = stoneTier.map(JewelryStoneTier::getMana).orElseGet(() -> 0);
            this.maxMana = Math.round((material.getMana() + mana) * sizeTier.getManaMultiplier());
        } else {
            this.maxMana = builder.maxMana;
        }
        this.mana = this.maxMana;

        // maxRepairs
        if (builder.maxRepairs < 0) {
            this.maxRepairs = material.getRepairs() + sizeTier.getRepairs();
        } else {
            this.maxRepairs = builder.maxRepairs;
        }
        // repairs
        this.repairs = this.maxRepairs;

        // maxRecharges
        if (builder.maxRecharges < 0) {
            this.maxRecharges =
                    material.getRecharges() +
                            stoneTier.map(JewelryStoneTier::getRecharges).orElseGet(() -> 0);
        } else {
            this.maxRecharges = builder.maxRecharges;
        }
        this.recharges = this.maxRecharges;

        this.spells.addAll(builder.spells);
        this.baseName = builder.baseName;
        this.acceptsAffixer = builder.acceptsAffixer;

        // spell factor calculations
        if (builder.spellCostFactor < 0) {
            this.spellCostFactor = calcSpellCostFactor();
        } else {
            this.spellCostFactor = builder.spellCostFactor;
        }

        if (builder.spellCooldownFactor < 0) {
            this.spellCooldownFactor = calcSpellCooldownFactor();
        } else {
            this.spellCooldownFactor = builder.spellCooldownFactor;
        }

        if (builder.spellDurationFactor < 0) {
            this.spellDurationFactor = calcSpellDurationFactor();
        } else {
            this.spellDurationFactor = builder.spellDurationFactor;
        }

        if (builder.spellEffectAmountFactor < 0) {
            this.spellEffectAmountFactor = calcSpellEffectAmountFactor();
        } else {
            this.spellEffectAmountFactor = builder.spellEffectAmountFactor;
        }

        if (builder.spellFrequencyFactor < 0) {
            this.spellFrequencyFactor = calcSpellFrequencyFactor();
        } else {
            this.spellFrequencyFactor = builder.spellFrequencyFactor;
        }

        if (builder.spellRangeFactor < 0) {
            this.spellRangeFactor = calcSpellRangeFactor();
        } else {
            this.spellRangeFactor = builder.spellRangeFactor;
        }
    }

    @Override
    // convenience method
    public JewelryStoneTier getStoneTier() {
        // get the stone and stone tier
        Item stone = StoneRegistry.get(this.stone).orElse(Items.AIR);
        // determine the tier
        return StoneRegistry.getStoneTier(stone).orElseGet(() -> JewelryStoneTiers.NONE);
    }

    @Override
    public boolean isUpgradable() {
        return getStone() == null || hasStone();
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {

        // spell max level
        tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("jewelry.max_level"),
                ChatFormatting.GOLD + String.valueOf(getMaxLevel()))));

        // durability
        if (isInfinite()) {
            tooltip.add(new TranslatableComponent(LangUtil.INDENT2)
                    .append(new TranslatableComponent(LangUtil.tooltip("jewelry.durability.infinite"), new TranslatableComponent(LangUtil.tooltip("infinite")).withStyle(ChatFormatting.GRAY))));
        } else {
            tooltip.add(new TranslatableComponent(LangUtil.INDENT2)
                    .append(new TranslatableComponent(LangUtil.tooltip("jewelry.durability.amount"),
                    ChatFormatting.GRAY + String.valueOf(getUses()),
                    ChatFormatting.GRAY + String.valueOf(getMaxUses()))));
        }

        // mana
        tooltip.add(new TranslatableComponent(LangUtil.INDENT2)
                .append(new TranslatableComponent(LangUtil.tooltip("jewelry.mana"),
                        ChatFormatting.BLUE + String.valueOf(Math.toIntExact(Math.round(getMana()))),
                        ChatFormatting.BLUE + String.valueOf(Math.toIntExact((long)Math.ceil(getMaxMana()))))));
                        // + getUsesGauge().getString())));

        if (!getSpells().isEmpty()) {
            tooltip.add(new TranslatableComponent(LangUtil.NEWLINE));
            tooltip.add(new TranslatableComponent(LangUtil.INDENT2)
                    .append(new TranslatableComponent(LangUtil.tooltip("divider")).withStyle(ChatFormatting.GRAY)));

            // add spells
            for (SpellEntity entity : getSpells()) {
                entity.getSpell().addInformation(stack, level, tooltip, flag, entity);
            }
        }

        // -----------
        MutableComponent component = new TranslatableComponent(LangUtil.INDENT2);
        Optional<MutableComponent> c = Optional.empty();
        if (getSpellCostFactor() != 1.0) {
            c = Optional.of(component);
            component.append(new TranslatableComponent(LangUtil.tooltip("jewelry.stats.cost_factor"), ChatFormatting.AQUA + formatStat(getSpellCostFactor())))
                    .append(" ");
        }
        if (getSpellCooldownFactor() != 1.0) {
            c = c.isEmpty() ? Optional.of(component) : c;
            component.append(new TranslatableComponent(LangUtil.tooltip("jewelry.stats.cooldown_factor"), ChatFormatting.AQUA + formatStat(getSpellCooldownFactor())))
                    .append(" ");
        }
        if (getSpellEffectAmountFactor() != 1.0) {
            c = c.isEmpty() ? Optional.of(component) : c;
            component.append(new TranslatableComponent(LangUtil.tooltip("jewelry.stats.effect_amount_factor"), ChatFormatting.AQUA + formatStat(getSpellEffectAmountFactor())))
                    .append(" ");
        }
        if (getSpellFrequencyFactor() != 1.0) {
            c = c.isEmpty() ? Optional.of(component) : c;
            component.append(new TranslatableComponent(LangUtil.tooltip("jewelry.stats.frequency_factor"), ChatFormatting.AQUA + formatStat(1.0 + (1.0 - getSpellFrequencyFactor()))))
                    .append(" ");
        }
        if (getSpellRangeFactor() != 1.0) {
            c = c.isEmpty() ? Optional.of(component) : c;
            component.append(new TranslatableComponent(LangUtil.tooltip("jewelry.stats.range_factor"), ChatFormatting.AQUA + formatStat(getSpellRangeFactor())))
                    .append(" ");
        }
        c.ifPresent(x -> {
            tooltip.add(new TranslatableComponent(LangUtil.NEWLINE));
            tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("divider")).withStyle(ChatFormatting.GRAY)));
            tooltip.add(component);
        });
        // ------------

        // advanced tooltip (hold shift)
        LangUtil.appendAdvancedHoverText(tooltip, tt -> {
            tooltip.add(new TranslatableComponent(LangUtil.NEWLINE));
            // material
            tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("jewelry.material"), ChatFormatting.GREEN + WordUtils.capitalizeFully(getMaterial().getId().getPath()))));
            // stones
            if (hasStone()) {
                tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("jewelry.stone"), ChatFormatting.YELLOW + WordUtils.capitalizeFully(getStone().getPath().replace("_", " ")))));
            }
            if (!isInfinite()) {
                tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("jewelry.durability.repairs"), ChatFormatting.GRAY + String.valueOf(getRepairs()))));
            } // TODO add else ? to display 0 repairs?
            tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("jewelry.mana.recharges"), ChatFormatting.BLUE + String.valueOf(getRecharges()))));
            tooltip.add(new TranslatableComponent(LangUtil.NEWLINE));

            appendSpecialHoverText(stack, level, tooltip, flag);
        });
    }

    @Override
    public void appendSpecialHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        // TODO this might be moot as this can't be anonymously set because a Handler class is instantiated by a Builder.
    }

    private String formatStat(double value) {
        if (value < 1.0) {
            return LangUtil.negativePercent(value);
        } else if (value > 1.0) {
            return LangUtil.positivePercent(value);
        }
        return "";
    }

    @Deprecated
    public Component getUsesGauge() {
        return new TranslatableComponent(LangUtil.tooltip("jewelry.mana.gauge"),
                String.valueOf(Math.toIntExact(Math.round(getMana()))),
                String.valueOf(Math.toIntExact((long)Math.ceil(getMaxMana()))));
    }

    @Override
    public double modifySpellCost(double cost) {
        return cost * getSpellCostFactor();
    }

    @Override
    public double getSpellCostFactor() {
        return spellCostFactor;
    }

    private double calcSpellCostFactor() {
        JewelryStoneTier stoneTier = getStoneTier();
        double materialModifier = getMaterial().getSpellCostFactor();
        return materialModifier * (stoneTier != null ? stoneTier.getSpellCostFactor() : 1);
    }

    @Override
    public double modifyEffectAmount(double amount) {
        return amount * getSpellEffectAmountFactor();
    }

    @Override
    public double getSpellEffectAmountFactor() {
        return spellEffectAmountFactor;
    }

    private double calcSpellEffectAmountFactor() {
        JewelryStoneTier stoneTier = getStoneTier();
        double materialModifier = getMaterial().getSpellEffectAmountFactor();
        return materialModifier * (stoneTier != null ? stoneTier.getSpellEffectAmountFactor() : 1);
    }

    @Override
    public int modifyDuration(int duration) {
        return (int)(duration * getSpellDurationFactor());
    }

    private double calcSpellDurationFactor() {
        JewelryStoneTier stoneTier = getStoneTier();
        double materialModifier = getMaterial().getSpellDurationFactor();
        return materialModifier * (stoneTier != null ? stoneTier.getSpellDurationFactor() : 1);
    }

    @Override
    public long modifyCooldown(long cooldown) {
        return (long)(cooldown * getSpellCooldownFactor());
    }

    @Override
    public double getSpellCooldownFactor() {
        return spellCooldownFactor;
    }

    private double calcSpellCooldownFactor() {
        JewelryStoneTier stoneTier = getStoneTier();
        double materialModifier = getMaterial().getSpellCooldownFactor();
        return materialModifier * (stoneTier != null ? stoneTier.getSpellCooldownFactor() : 1);
    }

    @Override
    public long modifyFrequency(long frequency) {
        return (long)(frequency * getSpellFrequencyFactor());
    }

    @Override
    public double getSpellFrequencyFactor() {
        return spellFrequencyFactor;
    }

    private double calcSpellFrequencyFactor() {
        JewelryStoneTier stoneTier = getStoneTier();
        double materialModifier = getMaterial().getSpellFrequencyFactor();
        return materialModifier * (stoneTier != null ? stoneTier.getSpellFrequencyFactor() : 1);
    }

    @Override
    public double modifyRange(double range) {
        return range * getSpellRangeFactor();
    }

    @Override
    public double getSpellRangeFactor() {
        return spellRangeFactor;
    }

    private double calcSpellRangeFactor() {
      JewelryStoneTier stoneTier = getStoneTier();
        double materialModifier = getMaterial().getSpellRangeFactor();
        return materialModifier * (stoneTier != null ? stoneTier.getSpellRangeFactor() : 1);
    }

    @Override
    public Tag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        // save by getName() as the EnumRegistry registers by name;
        tag.putString(TYPE, getJewelryType().getName());
        tag.putString(MATERIAL, getMaterial().getId().toString());

        if (getStone() != null) {
            tag.putString(STONE, getStone().toString());
        }

        tag.putString(SIZE_TIER, getJewelrySizeTier().getName());

        tag.putInt(MAX_USES, getMaxUses());
        tag.putInt(USES, getUses());
        tag.putDouble(MAX_MANA, getMaxMana());
        tag.putDouble(MANA, getMana());

        tag.putInt(MAX_LEVEL, getMaxLevel());

        tag.putInt(MAX_REPAIRS, getMaxRepairs());
        tag.putInt(REPAIRS, getRepairs());

        tag.putInt(MAX_RECHARGES, getMaxRecharges());
        tag.putInt(RECHARGES, getRecharges());

        ListTag spellsTag = new ListTag();
        for (SpellEntity entity : getSpells()) {
            CompoundTag entityTag = entity.save(new CompoundTag());
            spellsTag.add(entityTag);
        }
        tag.put(SPELLS, spellsTag);

        if (StringUtils.isNotBlank(this.baseName)) {
            tag.putString(BASE_NAME, getBaseName());
        }

        tag.putDouble(SPELL_COST_FACTOR, getSpellCostFactor());
        tag.putDouble(SPELL_COOLDOWN_FACTOR, getSpellCooldownFactor());
        tag.putDouble(SPELL_DURATION_FACTOR, getSpellDurationFactor());
        tag.putDouble(SPELL_EFFECT_AMOUNT_FACTOR, getSpellEffectAmountFactor());
        tag.putDouble(SPELL_FREQUENCY_FACTOR, getSpellFrequencyFactor());
        tag.putDouble(SPELL_RANGE_FACTOR, getSpellRangeFactor());

        return tag;
    }

    @Override
    public void deserializeNBT(Tag tag) {
        if (tag instanceof CompoundTag compound) {
            // tiers
            if (compound.contains(TYPE)) {
                // NOTE remember to pull from registry
                this.type = MagicThingsApi.getJewelryType(compound.getString(TYPE)).orElse(JewelryType.UNKNOWN);
            }
            if (compound.contains(MATERIAL)) {
                this.material = MagicThingsApi.getJewelryMaterial(ModUtil.asLocation(compound.getString(MATERIAL))).orElseGet(() -> JewelryMaterials.NONE);
            }
            if (compound.contains(STONE)) {
                ResourceLocation location = ModUtil.asLocation(compound.getString(STONE));
                this.stone = location;
            }

            if (compound.contains(SIZE_TIER)) {
                this.sizeTier = MagicThingsApi.getJewelrySize(compound.getString(SIZE_TIER)).orElse(JewelrySizeTier.UNKNOWN);
            }

            // properties
            if (compound.contains(MAX_USES)) {
                this.maxUses = compound.getInt(MAX_USES);
            }
            if (compound.contains(USES)) {
                this.uses = compound.getInt(USES);
            }
            if (compound.contains(MAX_LEVEL)) {
                this.maxLevel = compound.getInt(MAX_LEVEL);
            }
            if (compound.contains(MAX_MANA)) {
                this.maxMana = compound.getInt(MAX_MANA);
            }
            if (compound.contains(MANA)) {
                this.mana = compound.getInt(MANA);
            }
            if (compound.contains(MAX_REPAIRS)) {
                this.maxRepairs = compound.getInt(MAX_REPAIRS);
            }
            if (compound.contains(REPAIRS)) {
                this.repairs = compound.getInt(REPAIRS);
            }
            if (compound.contains(MAX_RECHARGES)) {
                this.maxRecharges = compound.getInt(MAX_RECHARGES);
            }
            if (compound.contains(RECHARGES)) {
                this.recharges = compound.getInt(RECHARGES);
            }

            // spells
            getSpells().clear();
            ListTag spellsTag = compound.getList(SPELLS, Tag.TAG_COMPOUND);
            spellsTag.forEach(spellTag -> {
                CompoundTag spellCompound = (CompoundTag) spellTag;
                // get the name
                if (spellCompound.contains(SpellEntity.NAME)) {
                    try {
                        ResourceLocation location = ModUtil.asLocation(spellCompound.getString(SpellEntity.NAME));
                        Optional<ISpell> spell = SpellRegistry.get(location);
                        if (spell.isEmpty()) {
                            throw new Exception(String.format("Unable to locate spell %s in registry.", location.toString()));
                        }
                        // generate an entity from the spell
                        SpellEntity entity = spell.get().entity();
                        // load the entity will the tag data
                        entity.load(spellCompound);
                        // add the entity to the spells
                        getSpells().add(entity);
                    } catch(Exception e) {
                        MagicThings.LOGGER.error("Unable to read state from tag ->", e);
                    }
                }
            });

            if (compound.contains(BASE_NAME)) {
                this.baseName = compound.getString(BASE_NAME);
            }

            if (compound.contains(SPELL_COST_FACTOR)) {
                this.spellCostFactor = compound.getDouble(SPELL_COST_FACTOR);
            }
            if (compound.contains(SPELL_COOLDOWN_FACTOR)) {
                this.spellCooldownFactor = compound.getDouble(SPELL_COOLDOWN_FACTOR);
            }
            if (compound.contains(SPELL_DURATION_FACTOR)) {
                this.spellDurationFactor = compound.getDouble(SPELL_DURATION_FACTOR);
            }
            if (compound.contains(SPELL_EFFECT_AMOUNT_FACTOR)) {
                this.spellEffectAmountFactor = compound.getDouble(SPELL_EFFECT_AMOUNT_FACTOR);
            }
            if (compound.contains(SPELL_FREQUENCY_FACTOR)) {
                this.spellFrequencyFactor = compound.getDouble(SPELL_FREQUENCY_FACTOR);
            }
            if (compound.contains(SPELL_RANGE_FACTOR)) {
                this.spellRangeFactor = compound.getDouble(SPELL_RANGE_FACTOR);
            }
        }
    }

    @Override
    public void setInfinite() {
        setUses(Integer.MAX_VALUE);
    }

    @Override
    public boolean isInfinite() {
        return getUses() == Integer.MAX_VALUE;
    }

    @Override
    public JewelryMaterial getMaterial() {
        return material;
    }

    @Override
    public IJewelrySizeTier getJewelrySizeTier() {
        return sizeTier;
    }

    @Override
    public IJewelryType getJewelryType() {
        return type;
    }

    @Override
    public int getMaxUses() {
        return maxUses;
    }

    @Override
    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public void setUses(int uses) {
        this.uses = uses;
    }

    @Override
    public double getMaxMana() {
        return maxMana;
    }

    @Override
    public void setMaxMana(double maxMana) {
        this.maxMana = maxMana;
    }

    @Override
    public double getMana() {
        return mana;
    }

    @Override
    public void setMana(double mana) {
        this.mana = mana;
    }

    @Override
    public int getMaxRepairs() {
        return maxRepairs;
    }

    @Override
    public void setMaxRepairs(int repairs) {
        this.maxRepairs =repairs;
    }

    @Override
    public int getRepairs() {
        return repairs;
    }

    @Override
    public void setRepairs(int repairs) {
        this.repairs = repairs;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
//        return getMaterial().getMaxLevel();
    }

    @Override
    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    @Override
    public ResourceLocation getStone() {
        return stone;
    }

    @Override
    public void setStone(ResourceLocation stone) {
        this.stone = stone;
    }

    @Override
    public boolean hasStone() {
        if (this.stone != null) {
            Item stoneItem = ForgeRegistries.ITEMS.getValue(this.stone);
            // TODO could check the StoneRegistry instead.
            return stoneItem != null
                    && stoneItem != Items.AIR
                    && stoneItem.builtInRegistryHolder().is(MagicThingsTags.Items.STONES);
        }
        return false;
    }

    @Override
    public List<SpellEntity> getSpells() {
        return spells;
    }

    @Override
    public void setSpells(List<SpellEntity> spells) {
        this.spells = spells;
    }

    @Override
    public int getRecharges() {
        return recharges;
    }

    @Override
    public void setRecharges(int recharges) {
        this.recharges = recharges;
    }

    @Override
    public int getMaxRecharges() {
        return maxRecharges;
    }

    @Override
    public void setMaxRecharges(int maxRecharges) {
        this.maxRecharges = maxRecharges;
    }

    @Override
    public String getBaseName() {
        return baseName;
    }

    @Override
    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    @Override
    public boolean acceptsAffixer(ItemStack stack) {
        return acceptsAffixer.test(stack);
    }

    @Override
    public void setSpellCostFactor(double spellCostFactor) {
        this.spellCostFactor = spellCostFactor;
    }

    @Override
    public void setSpellEffectAmountFactor(double spellEffectAmountFactor) {
        this.spellEffectAmountFactor = spellEffectAmountFactor;
    }

    @Override
    public void setSpellFrequencyFactor(double spellFrequencyFactor) {
        this.spellFrequencyFactor = spellFrequencyFactor;
    }

    @Override
    public double getSpellDurationFactor() {
        return spellDurationFactor;
    }

    @Override
    public void setSpellDurationFactor(double spellDurationFactor) {
        this.spellDurationFactor = spellDurationFactor;
    }

    @Override
    public void setSpellCooldownFactor(double spellCooldownFactor) {
        this.spellCooldownFactor = spellCooldownFactor;
    }

    @Override
    public void setSpellRangeFactor(double spellRangeFactor) {
        this.spellRangeFactor = spellRangeFactor;
    }
}
