
package mod.gottsch.forge.gealdorcraft.core.registry;

import java.util.Map;

import com.google.common.collect.Maps;

import mod.gottsch.forge.gealdorcraft.core.item.IJewelryStoneTier;
import mod.gottsch.forge.gealdorcraft.core.item.IJewelryType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/**
 * 
 * @author Mark Gottschling Jun 8, 2023
 *
 */
public class TagRegistry {

	private static final Map<IJewelryType, TagKey<Item>> JEWELRY_TYPE_TAGS_REGISTRY = Maps.newHashMap();
	private static final Map<IJewelryStoneTier, TagKey<Item>> STONE_TIER_TAGS_REGISTRY = Maps.newHashMap();
	
	private TagRegistry() { }

	public static void registerJewelryType(IJewelryType type, TagKey<Item> tagKey) {
		JEWELRY_TYPE_TAGS_REGISTRY.put(type, tagKey);
	}
	
	/**
	 * register a stoneTier tag to a stoneTier
	 * @param tier
	 * @param stoneTierTag
	 */
	public static void registerJewelryStoneTier(IJewelryStoneTier tier, TagKey<Item> tierTag) {
		STONE_TIER_TAGS_REGISTRY.put(tier, tierTag);
	}
	
	public static TagKey<Item> getJewelryTypeTag(IJewelryType type) {
		if (JEWELRY_TYPE_TAGS_REGISTRY.containsKey(type)) {
			return JEWELRY_TYPE_TAGS_REGISTRY.get(type);
		}
		return null;
	}
}
