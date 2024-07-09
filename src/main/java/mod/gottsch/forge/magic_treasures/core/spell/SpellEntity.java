package mod.gottsch.forge.magic_treasures.core.spell;

import mod.gottsch.forge.magic_treasures.MagicTreasures;
import mod.gottsch.forge.magic_treasures.core.spell.cost.CostEvaluator;
import mod.gottsch.forge.magic_treasures.core.spell.cost.ICostEvaluator;
import mod.gottsch.forge.magic_treasures.core.util.ModUtil;
import mod.gottsch.forge.treasure2.Treasure;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Optional;

/**
 * Still need the entity but its purpose is to store additional data that needs to be
 * maintained to proper function.
 *
 */
public class SpellEntity {
    public static final String SPELL = "spell";

    public static final String NAME = "name";
//    public static final String SPELL_COST = "spellCost";
//    public static final String DURATION = "duration";
//    public static final String FREQUENCY = "frequency";
//    public static final String EFFECT_AMOUNT = "effectAmount";
//    public static final String COOLDOWN = "cooldown";
//    public static final String RANGE = "range";
//    public static final String EFFECT_STACKABLE = "effectStackable";
//    public static final String COST_EVALUATOR = "costEvaluator";

    @Deprecated
//    public static final String EXCLUSIVE = "exclusive";


    public ISpell spell;
//    private double spellCost;
//    private double effectAmount;
//    private int duration;
//    private long frequency;
//    private double range;
//    private double cooldown;
//    private boolean effectStackable;
//    private boolean exclusive;

//    private ICostEvaluator costEvaluator;

    /**
     * 
     */
    public SpellEntity() {}
    
    public SpellEntity(ISpell spell) {
        this.spell = spell;

        // NOTE these are all definition properties. don't need to duplicate them in an entity
//        this.spellCost = spell.getSpellCost();
//        this.effectAmount = spell.getEffectAmount();
//
//        this.duration = spell.getDuration();
//        this.frequency = spell.getFrequency();
//        this.range = spell.getRange();
//        this.cooldown = spell.getCooldown();
//        this.effectStackable = spell.isEffectStackable();
//        this.exclusive = spell.isExclusive();
//        this.costEvaluator = spell.getCostEvaluator();
    }

    // TODO this doesn't seem like it is used anywhere
    /**
     * Client-side. Only update those properties that need to be reflected on the client-side.
     */
//    public void update(SpellEntity entity) {
//        this.spell = entity.spell;
//        this.spellCost = entity.getSpellCost();
//        this.effectAmount = entity.getEffectAmount();
//
//        this.duration = entity.getDuration();
//        this.frequency = entity.getFrequency();
//        this.range = entity.getRange();
//        this.cooldown = entity.getCooldown();
//        this.effectStackable = entity.isEffectStackable();
//        this.exclusive = entity.isExclusive();
//        this.costEvaluator = entity.getCostEvaluator();
//    }
    public void clientUpdate(ItemStack stack) {

    }

    /**
     * saves directly to the tag provided. ie does not make a new tag and append to tag param
     * @param tag
     * @return
     */
    public CompoundTag save(CompoundTag tag) {
        if (ObjectUtils.isNotEmpty(spell.getName())) {
            tag.putString(NAME, spell.getName().toString());
        }

//        tag.putDouble(SPELL_COST, getSpellCost());
//        tag.putDouble(EFFECT_AMOUNT, getEffectAmount());
//        tag.putLong(DURATION, getDuration());
//        tag.putLong(FREQUENCY, getFrequency());
//        tag.putDouble(RANGE, getRange());
//        tag.putDouble(COOLDOWN, getCooldown());
//        tag.putBoolean(EFFECT_STACKABLE, isEffectStackable());
//        tag.putBoolean(EXCLUSIVE, isExclusive());

//        if (getCostEvaluator() != null) {
//            CompoundTag costEvaluatorTag = new CompoundTag();
//            getCostEvaluator().save(costEvaluatorTag);
//            tag.put(COST_EVALUATOR, costEvaluatorTag);
//        }
        return tag;
    }

    public boolean load(CompoundTag tag) {
        if (tag.contains(SpellEntity.NAME)) {
            ResourceLocation location = ModUtil.asLocation(tag.getString(SpellEntity.NAME));
            Optional<ISpell> spell = SpellRegistry.get(location);
           spell.ifPresentOrElse(this::setSpell, () -> MagicTreasures.LOGGER.warn("unable to spell %s in registry.", location.toString()));
        }

//        if (tag.contains(SPELL_COST)) {
//            setSpellCost(tag.getDouble(SPELL_COST));
//        }
//        if (tag.contains(DURATION)) {
//            setDuration(tag.getInt(DURATION));
//        }
//        if (tag.contains(FREQUENCY)) {
//            setFrequency(tag.getInt(FREQUENCY));
//        }
//        if (tag.contains(EFFECT_AMOUNT)) {
//            setEffectAmount(tag.getDouble(EFFECT_AMOUNT));
//        }
//        if (tag.contains(COOLDOWN)) {
//            setCooldown(tag.getDouble(COOLDOWN));
//        }
//        if (tag.contains(RANGE)) {
//            setRange(tag.getDouble(RANGE));
//        }
//        if (tag.contains(COST_EVALUATOR) && tag.getCompound(COST_EVALUATOR).contains("costClass")) {
//            try {
//                CompoundTag costTag = tag.getCompound(COST_EVALUATOR);
//
//                String costEvalClass = costTag.getString("costClass");
////					Treasure.logger.warn("using parent cost eval class -> {}", costEvalClass);
//                Object o = Class.forName(costEvalClass).newInstance();
//                ((ICostEvaluator)o).load(tag);
//                setCostEvaluator((ICostEvaluator)o);
//            }
//            catch(Exception e) {
//                MagicTreasures.LOGGER.warn("unable to create cost evaluator from class string -> {}", tag.getCompound(COST_EVALUATOR).getString("costClass"));
//                MagicTreasures.LOGGER.error(e);
//                setCostEvaluator(new CostEvaluator());
//            }
//        }
//        else {
//            setCostEvaluator(new CostEvaluator());
//        }

//        if (tag.contains(EXCLUSIVE)) {
//            setExclusive(tag.getBoolean(EXCLUSIVE));
//        }

        return true;
    }

    public ISpell getSpell() {
        return spell;
    }

    public void setSpell(ISpell spell) {
        this.spell = spell;
    }

//    public double getSpellCost() {
//        return spellCost;
//    }
//
//    public void setSpellCost(double spellCost) {
//        this.spellCost = spellCost;
//    }
//
//    public double getEffectAmount() {
//        return effectAmount;
//    }
//
//    public void setEffectAmount(double effectAmount) {
//        this.effectAmount = effectAmount;
//    }
//
//    public int getDuration() {
//        return duration;
//    }
//
//    public void setDuration(int duration) {
//        this.duration = duration;
//    }
//
//    public long getFrequency() {
//        return frequency;
//    }
//
//    public void setFrequency(long frequency) {
//        this.frequency = frequency;
//    }
//
//    public double getRange() {
//        return range;
//    }
//
//    public void setRange(double range) {
//        this.range = range;
//    }
//
//    public double getCooldown() {
//        return cooldown;
//    }
//
//    public void setCooldown(double cooldown) {
//        this.cooldown = cooldown;
//    }
//
//    public boolean isEffectStackable() {
//        return effectStackable;
//    }
//
//    public void setEffectStackable(boolean effectStackable) {
//        this.effectStackable = effectStackable;
//    }
//
//    public boolean isExclusive() {
//        return exclusive;
//    }
//
//    public void setExclusive(boolean exclusive) {
//        this.exclusive = exclusive;
//    }
//
//    public ICostEvaluator getCostEvaluator() {
//        return costEvaluator;
//    }
//
//    public void setCostEvaluator(ICostEvaluator costEvaluator) {
//        this.costEvaluator = costEvaluator;
//    }
//
//    @Override
//    public String toString() {
//        return "SpellEntity{" +
//                "spell=" + spell +
//                ", spellCost=" + spellCost +
//                ", effectAmount=" + effectAmount +
//                ", duration=" + duration +
//                ", frequency=" + frequency +
//                ", range=" + range +
//                ", cooldown=" + cooldown +
//                ", effectStackable=" + effectStackable +
//                ", exclusive=" + exclusive +
//                '}';
//    }


    @Override
    public String toString() {
        return "SpellEntity{" +
                "spell=" + spell +
                '}';
    }
}
