
package mod.gottsch.forge.magic_things.core.loot;

import mod.gottsch.forge.gottschcore.enums.IRarity;
import mod.gottsch.forge.gottschcore.loot.LootPoolShell;
import mod.gottsch.forge.gottschcore.loot.LootTableShell;
import mod.gottsch.forge.gottschcore.random.RandomHelper;
import mod.gottsch.forge.gottschcore.spatial.ICoords;
import mod.gottsch.forge.magic_things.MagicThings;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Mark Gottschling Jun 12, 2023
 *
 */
@Deprecated
public abstract class LootGenerator implements ILootGenerator {

	/**
	 * 
	 * @param world
	 * @param random
	 * @param rarity
	 * @return
	 */
	@Override
	public Pair<List<ItemStack>, List<ItemStack>> generateLoot(Level world, Random random,
															   IRarity rarity, Player player, ICoords coords) {

		// TODO will have to register all the loot tables OR
		// have multiple LootGenerators for the different mods to have their own loot table
		// and just pass in the loot table name

		// TODO only need one stack
		List<ItemStack> treasureStacks = new ArrayList<>();
		List<ItemStack> itemStacks = new ArrayList<>();

		// TODO remove
//		Optional<LootTableShell> tableShell = getLootTableShell(world, random, type, rarity);
//		if (tableShell.isEmpty()) {
//			treasureStacks.add(getDefaultLootItem(random, rarity));
//			return Pair.of(treasureStacks, itemStacks);
//		}

		// TODO get the loot table by the name from a registry or param
		// get the vanilla table from shell
//		LootTable table = world.getServer().getLootTables().get(tableShell.get().getResourceLocation());
		// get a list of loot pools
//		List<LootPoolShell> lootPoolShells = tableShell.get().getPools();

		// generate a context
//		LootContext lootContext = getLootContext(world, player, coords);

		// TODO this portion may need its own method (TEMPLATE PATTERN) so chests can separate out into different lists
//List<ItemStack> testStack = table.getRandomItems(lootContext);

//		for (LootPoolShell pool : lootPoolShells) {
//			MagicThings.LOGGER.debug("processing pool -> {}", pool.getName());
//			// go get the vanilla managed pool
//			LootPool lootPool = table.getPool(pool.getName());
//
//			/*
//			 * geneate loot from pools
//			 */
//			// separate into two item stack buckets - treasure and other
//			if (pool.getName().equalsIgnoreCase(MagicThingsLootGenerators.TREASURE_POOL)) {
//				MagicThings.LOGGER.debug("generating loot from treasure/charm pool -> {}", pool.getName());
//				lootPool.addRandomItems(treasureStacks::add, lootContext);
//			} else {
//				lootPool.addRandomItems(itemStacks::add, lootContext);
//			}
//		}
//
//		// populate a pair
//		Pair<List<ItemStack>, List<ItemStack>> lootStacks = Pair.of(treasureStacks, itemStacks);
//
		// inject injectables into the loot stacks
//		injectLoot(world, random, testStack,
////				type,
//				rarity, lootContext);

//		return lootStacks;
		return null;
	}

}
