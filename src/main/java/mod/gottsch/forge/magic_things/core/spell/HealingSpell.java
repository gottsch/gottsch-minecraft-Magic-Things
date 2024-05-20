/*
 *  This file is part of  Magic Things.
 *  Copyright (c) 2024 Mark Gottschling (gottsch)
 *
 *  Magic Things is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Magic Things is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Magic Things.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
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
