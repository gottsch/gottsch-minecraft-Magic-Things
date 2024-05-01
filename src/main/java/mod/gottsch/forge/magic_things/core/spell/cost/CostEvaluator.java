package mod.gottsch.forge.magic_things.core.spell.cost;

import java.util.Random;

import com.someguyssoftware.gottschcore.spatial.ICoords;

import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.capability.IJewelryHandler;
import mod.gottsch.forge.magic_things.core.capability.MagicThingsCapabilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;

/*
 * Generic cost evaluator
 * @param amount cost requested
 * @return actual cost incurred
 */
public class CostEvaluator implements ICostEvaluator {
	@Override
	public double apply(Level level, Random random, ICoords coords, Player player, Event event, final ItemStack stack, double amount) {

		IJewelryHandler entity = stack.getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).orElseThrow(IllegalStateException::new);
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
			MagicThings.LOGGER.error("Unable to write state to NBT:", e);
		}
		return tag;
	}

	@Override
	public void load(CompoundTag tag) {
		ICostEvaluator.super.load(tag);
		// NOTE don't load "class" here. the instance is already created.
	}
}
