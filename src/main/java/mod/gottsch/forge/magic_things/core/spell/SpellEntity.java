package mod.gottsch.forge.magic_things.core.spell;

public class SpellEntity {
    public ISpell spell;
    private double spellCost;
    private double effectAmount;
    private int duration;
    private int frequency;
    private double range;
    private double cooldown;
    private boolean effectStackable;
    private boolean exclusive;

    /**
     * 
     */
    public SpellEntity() {}
    
    public SpellEntity(ISpell spell) {
        this.spell = spell;
        this.spellCost = spell.getSpellCost();
        this.effectAmount = spell.getEffectAmount();

        this.duration = spell.getDuration();
        this.frequency = spell.getFrequency();
        this.range = spell.getRange();
        this.cooldown = spell.getCooldown();
        this.effectStackable = spell.isEffectStackable();
        this.exclusive = spell.isExclusive();
    }
    
    public ISpell getSpell() {
        return spell;
    }

    public void setSpell(ISpell spell) {
        this.spell = spell;
    }

    public double getSpellCost() {
        return spellCost;
    }

    public void setSpellCost(double spellCost) {
        this.spellCost = spellCost;
    }

    public double getEffectAmount() {
        return effectAmount;
    }

    public void setEffectAmount(double effectAmount) {
        this.effectAmount = effectAmount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getCooldown() {
        return cooldown;
    }

    public void setCooldown(double cooldown) {
        this.cooldown = cooldown;
    }

    public boolean isEffectStackable() {
        return effectStackable;
    }

    public void setEffectStackable(boolean effectStackable) {
        this.effectStackable = effectStackable;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    @Override
    public String toString() {
        return "SpellEntity{" +
                "spell=" + spell +
                ", spellCost=" + spellCost +
                ", effectAmount=" + effectAmount +
                ", duration=" + duration +
                ", frequency=" + frequency +
                ", range=" + range +
                ", cooldown=" + cooldown +
                ", effectStackable=" + effectStackable +
                ", exclusive=" + exclusive +
                '}';
    }
}
