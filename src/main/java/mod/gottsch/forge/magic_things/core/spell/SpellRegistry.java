
package mod.gottsch.forge.magic_things.core.spell;

import mod.gottsch.forge.gottschcore.enums.IRarity;
import net.minecraft.resources.ResourceLocation;

import java.util.*;
import java.util.function.Predicate;

public class SpellRegistry {
    private static final Map<ResourceLocation, ISpell> REGISTRY = new HashMap<>();
    private static final Map<Integer, List<ISpell>> REGISTRY_BY_LEVEL = new HashMap<>();
    private static final Map<IRarity, List<ISpell>> REGISTRY_BY_RARITY = new HashMap<>();

    /**
     * 
     * @param spell
     */
    public static ISpell register(ISpell spell) {
        if (!REGISTRY.containsKey(spell.getName())) {
            REGISTRY.put(spell.getName(), spell);
        }
        if (!REGISTRY_BY_LEVEL.containsKey(spell.getLevel())) {
        	List<ISpell> spellList = new ArrayList<>();
        	spellList.add(spell);
        	REGISTRY_BY_LEVEL.put(spell.getLevel(), spellList);
        }
        else {
        	REGISTRY_BY_LEVEL.get(spell.getLevel()).add(spell);
        }
        if (!REGISTRY_BY_RARITY.containsKey(spell.getRarity())) {
            List<ISpell> charmList = new ArrayList<>();
            charmList.add(spell);
            REGISTRY_BY_RARITY.put(spell.getRarity(), charmList);
        }
        else {
            REGISTRY_BY_RARITY.get(spell.getRarity()).add(spell);
        }

        return spell;
    }

    /**
     * 
     * @param name
     * @return
     */
    public static Optional<ISpell> get(ResourceLocation name) {
        
        if (REGISTRY.containsKey(name)) {
            return Optional.of(REGISTRY.get(name));
        }
        return Optional.empty();
    }
    
    /**
     * @param level
     * @return
     */
    public static Optional<List<ISpell>> get(Integer level) {
        if (REGISTRY_BY_LEVEL.containsKey(level)) {
            return Optional.of(REGISTRY_BY_LEVEL.get(level));
        }
        return Optional.empty();
    }

    /**
     * @param rarity
     * @return
     */
    public static Optional<List<ISpell>> get(IRarity rarity) {
        if (REGISTRY_BY_RARITY.containsKey(rarity)) {
            return Optional.of(REGISTRY_BY_RARITY.get(rarity));
        }
        return Optional.empty();
    }

	/**
	 * 
	 * @param predicate
	 * @return
	 */
	public static Optional<List<ISpell>> getBy(Predicate<ISpell> predicate) {
		List<ISpell> charms = new ArrayList<>();
		for (ISpell c : SpellRegistry.values()) {
			if (predicate.test(c)) {
				charms.add(c);
			}
		}
		if (charms.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(charms);
	}
	
    /**
     * 
     * @return
     */
    public static List<ISpell> values() {
    	return new ArrayList<ISpell>(REGISTRY.values());
    }
}
