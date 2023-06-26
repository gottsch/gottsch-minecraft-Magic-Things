
package mod.gottsch.forge.gealdorcraft.core.registry;

import java.util.Map;

import com.google.common.collect.Maps;

import mod.gottsch.forge.gealdorcraft.core.item.IJewelryStoneTier;
import mod.gottsch.forge.gottschcore.enums.IRarity;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/**
 * 
 * @author Mark Gottschling Jun 8, 2023
 *
 */
public class TagRegistry {

	private static final Map<IJewelryStoneTier, TagKey<Item>> STONE_TIER_TAGS_REGISTRY = Maps.newHashMap();
	
	private TagRegistry() { }

	/**
	 * register a stoneTier tag to a stoneTier
	 * @param tier
	 * @param stoneTierTag
	 */
	public static void registerJewelryStoneTier(IJewelryStoneTier tier, TagKey<Item> tierTag) {
		STONE_TIER_TAGS_REGISTRY.put(tier, tierTag);
	}
}
