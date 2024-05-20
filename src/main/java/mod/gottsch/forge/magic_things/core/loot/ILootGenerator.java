package mod.gottsch.forge.magic_things.core.loot;

import mod.gottsch.forge.gottschcore.enums.IRarity;
import mod.gottsch.forge.gottschcore.spatial.ICoords;
import mod.gottsch.forge.treasure2.core.enums.ILootTableType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Random;

/**
  * 
  * @author Mark Gottschling Jun 12, 2023
  *
  */
@Deprecated
public interface ILootGenerator {

	/**
	 * @param world
	 * @param random
	 * @param rarity
	 * @return
	 */
	Pair<List<ItemStack>, List<ItemStack>> generateLoot(Level world, Random random,
//														ILootTableType type,
														IRarity rarity, Player player, ICoords coords);
}