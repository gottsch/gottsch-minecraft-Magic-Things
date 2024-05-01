package mod.gottsch.forge.magic_things.core.spell;

import mod.gottsch.forge.gottschcore.enums.IRarity;
import mod.gottsch.forge.magic_things.core.rarity.MagicThingsRarity;
import mod.gottsch.forge.magic_things.core.spell.cost.ICostEvaluator;
import mod.gottsch.forge.magic_things.core.util.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.text.DecimalFormat;
import java.util.List;
import java.util.function.Consumer;

/**
 * Spells are a single instance within the mod like Blocks and Items.
 * They can generate a SpellEntity which has individual state like BlockEntity and ItemEntity.
 */
public abstract class Spell implements ISpell {
    protected static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#");
    public static final int TICKS_PER_SECOND = 20;
    public static final ChatFormatting SPELL_COLOR = ChatFormatting.AQUA;
    public static final ChatFormatting SPELL_DESC_COLOR = ChatFormatting.GRAY;


    private final ResourceLocation name;
    private final String type;
    private final int level;
    private final IRarity rarity;

    private double spellCost;
    private double effectAmount;
    private int duration;
    private int frequency;
    private double range;
    private double cooldown;
    private boolean effectStackable;
    private boolean exclusive;


    private ICostEvaluator costEvaluator;

    /*
     * builder constructor
     */
    public Spell(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.level = builder.level;
        this.rarity = builder.rarity;
        this.duration = builder.duration;
        this.frequency = builder.frequency;
        this.effectStackable = builder.effectStackable;
    }

    public SpellEntity entity() {
        return new SpellEntity(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void addInformation(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flagIn, SpellEntity entity) {
        tooltip.add(getLabel(entity));
        tooltip.add(getDesc(entity));
    }

    public Component getLabel(SpellEntity entity) {
        MutableComponent label = new TranslatableComponent(LangUtil.tooltip("spell.name.") + getName().getPath().toLowerCase());

        label.append(" ").append((this.effectStackable ? "+" : "-"));

        return new TranslatableComponent(LangUtil.INDENT2).append(label).withStyle(getSpellLabelColor());
    }

    public Component getDesc(SpellEntity entity) {
        return new TranslatableComponent(LangUtil.INDENT4).append(getSpellDesc(entity)).withStyle(ChatFormatting.ITALIC).withStyle( getCharmDescColor());
    }

    /**
     * Implemented by concrete Spell.
     * @param entity
     * @return
     */
    public Component getSpellDesc(SpellEntity entity) { return new TranslatableComponent(LangUtil.BLANK);};

    @Override
    public ChatFormatting getSpellLabelColor() {
        return SPELL_COLOR;
    }

    @Override
    public ChatFormatting getCharmDescColor() {
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
        public int frequency;
        public boolean effectStackable;

        public Builder(ResourceLocation name, String type, int level) {
            this(name, type, level, MagicThingsRarity.COMMON);
        }

        public Builder(ResourceLocation name, String type, int level, IRarity rarity) {
            this.name = name;
            this.type = type;
            this.level = level;
            this.rarity = rarity;
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
    public int getFrequency() {
        return frequency;
    }

    @Override
    public void setFrequency(int frequency) {
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
    public double getCooldown() {
        return cooldown;
    }

    @Override
    public void setCooldown(double cooldown) {
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
    public String toString() {
        return "Spell{" +
                "name=" + name +
                ", type='" + type + '\'' +
                ", level=" + level +
                ", rarity=" + rarity +
                ", spellCost=" + spellCost +
                ", effectAmount=" + effectAmount +
                ", duration=" + duration +
                ", frequency=" + frequency +
                ", range=" + range +
                ", cooldown=" + cooldown +
                ", effectStackable=" + effectStackable +
                ", exclusive=" + exclusive +
                ", costEvaluator=" + costEvaluator +
                '}';
    }
}
