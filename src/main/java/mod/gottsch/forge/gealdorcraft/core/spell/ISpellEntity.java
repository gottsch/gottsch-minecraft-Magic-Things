/* 
* This file is part of  Treasure2.
 * Copyright (c) 2023 Mark Gottschling (gottsch)
 * 
 * All rights reserved.
 *
 * Treasure2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Treasure2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Treasure2.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
package mod.gottsch.forge.gealdorcraft.core.spell;


import mod.gottsch.forge.gealdorcraft.GealdorCraft;
import mod.gottsch.forge.gealdorcraft.core.spell.cost.CostEvaluator;
import mod.gottsch.forge.gealdorcraft.core.spell.cost.ICostEvaluator;
import net.minecraft.nbt.CompoundTag;

/**
 *
 * @author Mark Gottschling on 5/4/2023
 *
 */
public interface ISpellEntity {
	public static final String CHARM = "spell";
	@Deprecated
	public static final String VALUE = "value";
	public static final String MANA = "mana";
	public static final String MAX_MANA = "maxMana";
	public static final String DURATION = "duration";
	public static final String FREQUENCY = "frequency";
	public static final String AMOUNT = "amount";
	public static final String COOLDOWN = "cooldown";
	public static final String RANGE = "range";
	public static final String COST_EVALUATOR = "costEvaluator";
	
	public static final String EXCLUSIVE = "exclusive";
	public static final String RECHARGES = "recharges";
	public static final String MAX_RECHARGES = "maxRecharges";
	
	ISpell getCharm();

	void setCharm(ISpell spell);

	double getMana();
	void setMana(double value);

	int getDuration();
	void setDuration(int duration);

	double getFrequency();	
	void setFrequency(double frequency);
	
	double getRange();
	void setRange(double range);

	double getCooldown();
	void setCooldown(double cooldown);
	
	double getAmount();
	void setAmount(double amount);

	ICostEvaluator getCostEvaluator();
	void setCostEvaluator(ICostEvaluator costEvaluator);
	
	// TESTING
	double getMaxMana();
	void setMaxMana(double maxMana);
	
	void update(ISpellEntity entity);

	/**
	 * 
	 * @param tag
	 * @return
	 */
	CompoundTag save(CompoundTag tag);

	default public boolean load(CompoundTag tag) {
		if (tag.contains(MANA)) {
			setMana(tag.getDouble(MANA));
		}
		// legacy
		else if (tag.contains(VALUE)) {
			setMana(tag.getDouble(VALUE));
		}
		
		if (tag.contains(DURATION)) {
			setDuration(tag.getInt(DURATION));
		}
		if (tag.contains(FREQUENCY)) {
			setFrequency(tag.getDouble(FREQUENCY));
		}
		if (tag.contains(AMOUNT)) {
			setAmount(tag.getDouble(AMOUNT));
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
				GealdorCraft.LOGGER.warn("unable to create cost evaluator from class string -> {}", tag.getCompound(COST_EVALUATOR).getString("costClass"));
				GealdorCraft.LOGGER.error(e);
				setCostEvaluator(new CostEvaluator());
			}
		}
		else {
			setCostEvaluator(new CostEvaluator());
		}
		
		if (tag.contains("maxMana")) {
			setMaxMana(tag.getDouble("maxMana"));
		}
		
		if (tag.contains(EXCLUSIVE)) {
			setExclusive(tag.getBoolean(EXCLUSIVE));
		}
		
		if (tag.contains(RECHARGES)) {
			setRecharges(tag.getInt(RECHARGES));
		}
		if (tag.contains(MAX_RECHARGES)) {
			setMaxRecharges(tag.getInt(MAX_RECHARGES));
		}
		return true;
	}

	boolean isExclusive();

	void setExclusive(boolean exclusive);

	int getRecharges();

	void setRecharges(int recharges);

	int getMaxRecharges();

	void setMaxRecharges(int maxRecharges);
}
