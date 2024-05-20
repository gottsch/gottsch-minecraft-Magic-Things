package mod.gottsch.forge.magic_things.core.spell.cost;

import mod.gottsch.forge.gottschcore.spatial.ICoords;
import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.capability.IJewelryHandler;
import mod.gottsch.forge.magic_things.core.capability.MagicThingsCapabilities;
import mod.gottsch.forge.magic_things.core.jewelry.JewelryStoneTier;
import mod.gottsch.forge.magic_things.core.jewelry.JewelryStoneTiers;
import mod.gottsch.forge.magic_things.core.registry.StoneRegistry;
import mod.gottsch.forge.magic_things.core.spell.ICastSpellContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Optional;
import java.util.Random;

/*
 * Generic cost evaluator
 * @param amount cost requested
 * @return actual cost incurred
 */
public class CostEvaluator implements ICostEvaluator {
	@Override
	public double apply(Level level, Random random, ICoords coords, ICastSpellContext context, double amount) {
		IJewelryHandler handler = context.getJewelry().getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).orElseThrow(IllegalStateException::new);

		Optional<Item> stone = StoneRegistry.get(handler.getStone());
		JewelryStoneTier stoneTier = StoneRegistry.getStoneTier(stone.orElseGet(() -> Items.AIR)).orElse(JewelryStoneTiers.NONE);

		// calculate the new amount for cost
		double newAmount = amount * handler.getMaterial().getSpellCostFactor()
				* stoneTier.getSpellCostFactor();

		double cost = 0;
		if (handler.getMana() >= newAmount) {
			cost = newAmount;
			handler.setMana(Mth.clamp(handler.getMana() - newAmount, 0D, handler.getMana()));
		}
		else {
			cost = handler.getMana();
			handler.setMana(0);
		}
		return cost;
	}

	// cost evaluators should be stateless and have no need to save/load data
	@Deprecated
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

	@Deprecated
	@Override
	public void load(CompoundTag tag) {
		ICostEvaluator.super.load(tag);
		// NOTE don't load "class" here. the instance is already created.
	}
}
