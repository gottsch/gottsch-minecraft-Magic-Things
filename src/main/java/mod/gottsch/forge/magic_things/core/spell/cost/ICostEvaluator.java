
package mod.gottsch.forge.magic_things.core.spell.cost;

import java.util.Random;

import com.someguyssoftware.gottschcore.spatial.ICoords;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;

/**
 * Calculates and applies the cost to execute the charm.
 * @author Mark Gottschling on Jan 12, 2022
 *
 */
public interface ICostEvaluator {
	public double apply(Level level, Random random, ICoords coords, Player player, Event event, final ItemStack entity, double amount);

	default public CompoundTag save(CompoundTag tag) {
		return tag;
	}

	default public void load(CompoundTag tag) {}
}
