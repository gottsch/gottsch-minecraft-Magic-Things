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
package mod.gottsch.forge.magic_things.core.capability;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.api.MagicThingsApi;
import mod.gottsch.forge.magic_things.core.item.IJewelryMaterialTier;
import mod.gottsch.forge.magic_things.core.item.IJewelrySizeTier;
import mod.gottsch.forge.magic_things.core.item.IJewelryStoneTier;
import mod.gottsch.forge.magic_things.core.item.IJewelryType;
import mod.gottsch.forge.magic_things.core.item.JewelryMaterialTier;
import mod.gottsch.forge.magic_things.core.item.JewelrySizeTier;
import mod.gottsch.forge.magic_things.core.item.JewelryStoneTier;
import mod.gottsch.forge.magic_things.core.item.JewelryType;
import mod.gottsch.forge.magic_things.core.spell.MagicThingsSpells;
import mod.gottsch.forge.magic_things.core.spell.SpellEntity;
import mod.gottsch.forge.magic_things.core.util.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.INBTSerializable;
import org.apache.commons.compress.utils.Lists;

/**
 * Created by Mark Gottschling on 6/1/2023
 */
public class JewelryHandler implements IJewelryHandler, INBTSerializable<Tag> {
    private static final String TYPE = "type";
    private static final String MATERIAL_TIER = "materialTier";
    private static final String STONE_TIER = "stoneTier";
    private static final String SIZE_TIER = "sizeTier";
    private static final String USES = "uses";
    private static final String MAX_MANA = "maxMana";
    private static final String MANA = "mana";
    private static final String MAX_REPAIRS = "maxRepairs";
    private static final String REPAIRS = "repairs";
    private static final String MAX_SOCKETS = "maxSockets";
    private static final String SOCKETS = "sockets";
    private static final String MAX_LEVEL ="maxLevel";
    private static final String STONES = "stones";

    private IJewelryType type;
    private IJewelryMaterialTier materialTier;
    private List<IJewelryStoneTier> stoneTiers = new ArrayList<>(2);
    private IJewelrySizeTier sizeTier;

    private int uses;

    private double maxMana;
    private double mana;

    private int maxRepairs;
    private int repairs;

    @Deprecated
    private int maxSockets;
    private int sockets;

    // TODO rename to maxSpellLevel
    // TODO re-evaluate - the MaterialTier already has the maxLevel
    // TODO >> should this value be modifiable? ex. some magic gem increases the base level ?
//    private int maxLevel;

    // TODO need maxStones
    // TODO need to register Tags for Stone -> Tier
    public List<ResourceLocation> stones = new ArrayList<>(2);
    
    // TODO add Spell properties
    private List<SpellEntity> spells = Lists.newArrayList();

    //
    public static class Builder {
        public IJewelryType type;
        public IJewelryMaterialTier materialTier;
        public List<IJewelryStoneTier> stoneTiers = new ArrayList<>(2);
        public IJewelrySizeTier sizeTier;
        public int uses;
        public double maxMana;
        public double mana;
        public int maxRepairs;
        public int repairs;
//        public int maxSockets;
//        public int sockets;
        public int maxLevel;
        public List<ResourceLocation> stones = new ArrayList<>(2);

        /**
         * TODO use this constructor
         * @param type
         * @param materialTier
         */
        public Builder(IJewelryType type, IJewelryMaterialTier materialTier) {
            this.type = type;
            this.materialTier = materialTier;
            this.sizeTier = JewelrySizeTier.REGULAR;
            this.stoneTiers.add(JewelryStoneTier.NONE);
        }
        
        public Builder(IJewelryType type, IJewelryMaterialTier materialTier, IJewelryStoneTier stoneTier, IJewelrySizeTier sizeTier) {
            this.type = type;
            this.materialTier = materialTier;
            this.stoneTiers.add(stoneTier);
            this.sizeTier = sizeTier;
        }
        
        // TODO least likely to keep this constructor
        public Builder(IJewelryType type, IJewelryMaterialTier materialTier, IJewelrySizeTier sizeTier, List<ResourceLocation> stones) {
            this.type = type;
            this.materialTier = materialTier;
            this.sizeTier = sizeTier;

            for (int index = 0; index < sizeTier.getSockets(); index++) {
            	this.stones.add(stones.get(index));
            	// TODO compare all stones and get the the matching stoneTier
            }
        }

        public Builder with(Consumer<Builder> builder) {
            builder.accept(this);
            return this;
        }

        public Builder withStone(ResourceLocation stone) {
        	this.stones.add(stone);
        	return this;
        }
        
        public Builder withStones(List<ResourceLocation> stones) {
        	this.stones = stones;
        	return this;
        }
        
		public Builder withSize(JewelrySizeTier size) {
			this.sizeTier = size;
			return this;
		}
		
        public IJewelryHandler build() {
        	// should build do all the calculations or should the JewelryHandler do it all?
        	// probably build should
            return new JewelryHandler(this);
        }
    }

    /**
     *
     * @param type
     * @param materialTier
     * @param stoneTier
     * @param sizeTier
     */
    public JewelryHandler(IJewelryType type, IJewelryMaterialTier materialTier, IJewelryStoneTier stoneTier, IJewelrySizeTier sizeTier) {
        this.type = type;
        this.materialTier = materialTier;
        this.stoneTiers.add(stoneTier);
        this.sizeTier = sizeTier;

        // do calculations based on tiers
        this.uses = Math.round(materialTier.getUses().getInRange() * sizeTier.getUsesMultiplier());

        // TODO ... mana etc.
    }

    /**
     *
     * @param builder
     */
    public JewelryHandler(Builder builder) {
    	// required properties
        this.type = builder.type;
        this.materialTier = builder.materialTier;
        // optional and calculations
        this.stoneTiers = builder.stoneTiers;
        this.sizeTier = builder.sizeTier;
        if (builder.uses == 0) {
            this.uses = Math.round(materialTier.getUses().getInRange() * sizeTier.getUsesMultiplier());
        } else {
            this.uses = builder.uses;
        }
        if (builder.maxMana == 0) {
        	int mana = stoneTiers.stream()
        			  .map(x -> x.getMana().getInRange())
        			  .reduce(0, Integer::sum);        	
        	this.maxMana = Math.round((materialTier.getMana().getInRange() + mana) * sizeTier.getManaMultiplier());
        } else {
            this.maxMana = builder.maxMana;
        }
        this.mana = this.maxMana;

        // add stone up to max for size tier
        builder.stones.forEach(stone -> {
        	if (this.stones.size() < this.sizeTier.getSockets()) {
        		this.stones.add(stone);
        	}
        });

        
        // TODO ...
        // maxRepairs
        if (builder.maxRepairs == 0) {
            // TODO ...
        } else {
            this.maxRepairs = builder.maxRepairs;
        }
        // repairs
        this.repairs = this.maxRepairs;

        // maxSockets
//        if (builder.maxSockets == 0) {
//            // TODO ...
//        } else {
//            this.maxSockets = builder.maxSockets;
//        }
//        // sockets
//        this.sockets = this.maxSockets;

        // maxLevel
        // TODO ...
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(new TranslatableComponent(LangUtil.tooltip("jewelry.max_level"), getMaxLevel()).withStyle(ChatFormatting.DARK_AQUA));

        // add charms
        for (SpellEntity entity : getSpells()) {
            MagicThings.LOGGER.info("entity -> {}", entity);
            entity.getSpell().addInformation(stack, level, tooltip, flag, entity);
        }
    }

    @Override
    public Tag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        // save by getName() as the EnumRegistry registers by name;
        tag.putString(TYPE, getJewelryType().getName());
        tag.putString(MATERIAL_TIER, getJewelryMaterialTier().getName());
        
//        tag.putString(STONE_TIER, getJewelryStoneTier().getName());
        
        tag.putString(SIZE_TIER, getJewelrySizeTier().getName());

        tag.putInt(USES, getUses());
        tag.putDouble(MAX_MANA, getMaxMana());
        tag.putDouble(MANA, getMana());

        tag.putInt(MAX_REPAIRS, getMaxRepairs());
        tag.putInt(REPAIRS, getRepairs());

        tag.putInt(MAX_SOCKETS, getMaxSockets());
        tag.putInt(SOCKETS, getSockets());

//        tag.putInt(MAX_LEVEL, getMaxLevel());

        // TODO complete
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
            if (compound.contains(MATERIAL_TIER)) {
                this.materialTier = MagicThingsApi.getJewelryMaterialTier(compound.getString(MATERIAL_TIER)).orElse(JewelryMaterialTier.NONE);
            }
            // TODO this is now a list - UPDATE
            if (compound.contains(STONE_TIER)) {
//                this.stoneTier = GealdorCraftApi.getJewelryStoneTier(compound.getString(STONE_TIER)).orElse(JewelryStoneTier.NONE);
            	
            }
            if (compound.contains(SIZE_TIER)) {
                this.sizeTier = MagicThingsApi.getJewelrySize(compound.getString(SIZE_TIER)).orElse(JewelrySizeTier.UNKNOWN);
            }

            // properties
            if (compound.contains(USES)) {
                this.uses = compound.getInt(USES);
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
            if (compound.contains(MAX_SOCKETS)) {
                this.maxSockets = compound.getInt(MAX_SOCKETS);
            }
            if (compound.contains(SOCKETS)) {
                this.sockets = compound.getInt(SOCKETS);
            }
//            if (compound.contains(MAX_LEVEL)) {
//                this.maxLevel = compound.getInt(MAX_LEVEL);
//            }
        }
    }

    @Override
    public IJewelryMaterialTier getJewelryMaterialTier() {
        return materialTier;
    }

    @Override
    public List<IJewelryStoneTier> getJewelryStoneTiers() {
        return stoneTiers;
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
    public int getMaxSockets() {
        return maxSockets;
    }

    @Override
    public void setMaxSockets(int maxSockets) {
        this.maxSockets = maxSockets;
    }

    @Override
    public int getSockets() {
        return sockets;
    }

    @Override
    public void setSockets(int sockets) {
        this.sockets = sockets;
    }

    @Override
    public int getMaxLevel() {
        return getJewelryMaterialTier().getMaxLevel();
    }

    @Override
	public List<ResourceLocation> getStones() {
		return stones;
	}

    @Override
	public void setStones(List<ResourceLocation> stones) {
		this.stones = stones;
	}

    @Override
    public List<SpellEntity> getSpells() {
        return spells;
    }

    @Override
    public void setSpells(List<SpellEntity> spells) {
        this.spells = spells;
    }
}
