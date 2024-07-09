
package mod.gottsch.forge.magic_treasures.core.loot;

import mod.gottsch.forge.magic_treasures.core.loot.function.ImbueRandomly;
import mod.gottsch.forge.magic_treasures.core.loot.function.RandomGemstone;
import mod.gottsch.forge.magic_treasures.core.loot.function.RandomJewelry;
import mod.gottsch.forge.magic_treasures.core.loot.function.RandomSpell;
import mod.gottsch.forge.magic_treasures.core.util.ModUtil;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;

/**
 * 
 * @author Mark Gottschling on May 21, 2024
 *
 */
public class MagicTreasuresLootFunctions {

	public static LootItemFunctionType RANDOM_GEMSTONE;
	public static LootItemFunctionType RANDOM_JEWELRY;
	public static LootItemFunctionType RANDOM_SPELL;
	public static LootItemFunctionType IMBUE_RANDOMLY;

	/**
	 * 
	 */
	public static void register() {
		RANDOM_GEMSTONE = register("random_gemstone", new LootItemFunctionType(new RandomGemstone.Serializer()));
		RANDOM_JEWELRY = register("random_jewelry", new LootItemFunctionType(new RandomJewelry.Serializer()));
		RANDOM_SPELL = register("random_spell", new LootItemFunctionType(new RandomSpell.Serializer()));
		IMBUE_RANDOMLY = register("imbue_randomly", new LootItemFunctionType(new ImbueRandomly.Serializer()));
	}
	
	/**
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public static LootItemFunctionType register(String name, LootItemFunctionType type) {
		return Registry.register(Registry.LOOT_FUNCTION_TYPE, ModUtil.asLocation(name), type);
	}
}
