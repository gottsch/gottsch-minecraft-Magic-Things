package mod.gottsch.forge.magic_things.core.spell;

import mod.gottsch.forge.gottschcore.enums.IRarity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

/**
 *
 */
public class HealingSpell extends Spell {

    /**
     *
     * @param builder
     */
    public HealingSpell(Builder builder) {
        super(builder);
    }

    public static class Builder extends Spell.Builder {
        public Builder(ResourceLocation name, String type, int level, IRarity rarity) {
            super(name, type, level, rarity);
//            this.costEvaluator = new Costinator();
        }

        @Override
        public ISpell build() {
            return new HealingSpell(this);
        }
    }

    @Override
    public void update(ISpell spell) {

    }

    @Override
    public boolean load(CompoundTag tag) {
        return super.load(tag);
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        return super.save(tag);
    }
}
