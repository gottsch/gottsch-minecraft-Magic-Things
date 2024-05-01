package mod.gottsch.forge.magic_things.core.spell;

import mod.gottsch.forge.magic_things.core.rarity.MagicThingsRarity;
import mod.gottsch.forge.magic_things.core.util.ModUtil;

/**
 *
 */
public class MagicThingsSpells {
    public static String HEALING_TYPE = "healing";

    // default spell
    public static final ISpell LESSER_HEALING = SpellRegistry.register(new HealingSpell.Builder(ModUtil.asLocation("lesser_healing"), HEALING_TYPE, 1, MagicThingsRarity.COMMON).with($ -> {
        $.spellCost = 1.0;
        $.effectAmount = 1.0;
        $.frequency = 200; // 8 seconds
        $.effectStackable = true;
    })	.build());

    static {
        // register all spells
        SpellRegistry.register(LESSER_HEALING);

        ///// healing spells /////
        SpellRegistry.register(new HealingSpell.Builder(ModUtil.asLocation("healing"), HEALING_TYPE, 3, MagicThingsRarity.UNCOMMON).with($ -> {
            $.spellCost = 1.0;
            $.effectAmount = 1.0;
            $.frequency = 160; // 8 seconds
            $.effectStackable = true;
        })	.build());
    }

    /*
     * a do-nothing method used to force-load static properties
     */
    public static void init() {
    }
}
