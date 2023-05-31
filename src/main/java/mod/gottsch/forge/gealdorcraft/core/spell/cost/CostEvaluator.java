package mod.gottsch.forge.gealdorcraft.core.spell.cost;

import java.util.Random;

import com.someguyssoftware.gottschcore.spatial.ICoords;

import mod.gottsch.forge.gealdorcraft.core.GealdorCraft;
import mod.gottsch.forge.gealdorcraft.core.spell.ISpellEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;

/*
 * Generic cost evaluator
 * @param amount cost requested
 * @return actual cost incurred
 */
public class CostEvaluator implements ICostEvaluator {
	@Override
	public double apply(Level level, Random random, ICoords coords, Player player, Event event, final ISpellEntity entity, double amount) {
		
		double cost = 0;
		if (entity.getMana() >= amount) {
			cost = amount;
			entity.setMana(Mth.clamp(entity.getMana() - amount, 0D, entity.getMana()));
		}
		else {
			cost = entity.getMana();
			entity.setMana(0);
		}
		return cost;
	}
	
	@Override
	public CompoundTag save(CompoundTag tag) {
		ICostEvaluator.super.save(tag);
		try {
			tag.putString("costClass", getClass().getCanonicalName());
		}
		catch(Exception e) {
			GealdorCraft.LOGGER.error("Unable to write state to NBT:", e);
		}
		return tag;
	}

	@Override
	public void load(CompoundTag tag) {
		ICostEvaluator.super.load(tag);
		// NOTE don't load "class" here. the instance is already created.
	}
}
