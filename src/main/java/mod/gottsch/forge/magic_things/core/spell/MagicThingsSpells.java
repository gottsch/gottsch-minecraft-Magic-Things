package mod.gottsch.forge.magic_things.core.spell;

import mod.gottsch.forge.magic_things.core.rarity.MagicThingsRarity;
import mod.gottsch.forge.magic_things.core.util.ModUtil;

/**
 *
 */
public class MagicThingsSpells {
    public static final String GREATER_HEALING = "greater_healing";

    // TODO need to change variable name
    // default spell
    public static final ISpell DEFAULT_HEALING = SpellRegistry.register(new HealingSpell.Builder(ModUtil.asLocation("lesser_healing"), 1, MagicThingsRarity.COMMON)
            .with($ -> {
                $.spellCost = 1.0;
                $.effectAmount = 1.0;
                $.frequency = 200; // 10 seconds
                $.effectStackable = true;
            })	.build());

    static {
        // register all spells
        SpellRegistry.register(DEFAULT_HEALING);

        ///// healing spells /////
        SpellRegistry.register(new HealingSpell.Builder(ModUtil.asLocation("healing"), 3, MagicThingsRarity.UNCOMMON).with($ -> {
            $.spellCost = 2.0;
            $.effectAmount = 2.0;
            $.frequency = 180; // 9 seconds
            $.effectStackable = true;
        })	.build());

        SpellRegistry.register(new HealingSpell.Builder(ModUtil.asLocation("greater_healing"), 5, MagicThingsRarity.SCARCE).with($ -> {
            $.spellCost = 2.5;
            $.effectAmount = 3.0;
            $.frequency = 160; // 8 seconds
            $.effectStackable = true;
        })	.build());

        SpellRegistry.register(new HealingSpell.Builder(ModUtil.asLocation("regeneration"), 8, MagicThingsRarity.RARE).with($ -> {
            $.spellCost = 3.0;
            $.effectAmount = 5.0;
            $.frequency = 140; // 7 seconds
            $.effectStackable = true;
        })	.build());

        ///// shield spells /////
        SpellRegistry.register(new ManaShieldSpell.Builder(ModUtil.asLocation("mana_buckler"), 2, MagicThingsRarity.UNCOMMON).with($ -> {
            $.spellCost = 0;
            $.effectAmount = 0.25;
            $.cooldown = 160; // 8 seconds
            $.effectStackable = true;
            $.priority = 2;
        })	.build());

        SpellRegistry.register(new ManaShieldSpell.Builder(ModUtil.asLocation("mana_shield"), 4, MagicThingsRarity.SCARCE).with($ -> {
            $.spellCost = 0;
            $.effectAmount = 0.5;
            $.cooldown = 140; // 7 seconds
            $.effectStackable = true;
            $.priority = 2;
        })	.build());

        SpellRegistry.register(new ManaShieldSpell.Builder(ModUtil.asLocation("mana_tower_shield"), 6, MagicThingsRarity.RARE).with($ -> {
            $.spellCost = 0;
            $.effectAmount = 0.75;
            $.cooldown = 120; // 6 seconds
            $.effectStackable = true;
            $.priority = 2;
        })	.build());

        SpellRegistry.register(new ManaShieldSpell.Builder(ModUtil.asLocation("mana_pavise_shield"), 8, MagicThingsRarity.EPIC).with($ -> {
            $.spellCost = 0;
            $.effectAmount = 1.0;
            $.cooldown = 100; // 5 seconds
            $.effectStackable = true;
            $.priority = 2;
        })	.build());

        // spectral armor spells
        SpellRegistry.register(new SpectralArmorSpell.Builder(ModUtil.asLocation("ghostly_armor"), 1, MagicThingsRarity.COMMON).with($ -> {
            $.spellCost = 1;
            $.effectAmount = 3.0;
            $.cooldown = 0; // 0 seconds
            $.effectStackable = false;
            $.priority = 3;
        })	.build());

        SpellRegistry.register(new SpectralArmorSpell.Builder(ModUtil.asLocation("spectral_armor"), 3, MagicThingsRarity.UNCOMMON).with($ -> {
            $.spellCost = 2;
            $.effectAmount = 5.0;
            $.cooldown = 0; // 0 seconds
            $.effectStackable = false;
            $.priority = 3;
        })	.build());

        SpellRegistry.register(new SpectralArmorSpell.Builder(ModUtil.asLocation("shadow_armor"), 5, MagicThingsRarity.SCARCE).with($ -> {
            $.spellCost = 2;
            $.effectAmount = 6.0;
            $.cooldown = 1; // 0 seconds
            $.effectStackable = false;
            $.priority = 3;
        })	.build());

        SpellRegistry.register(new SpectralArmorSpell.Builder(ModUtil.asLocation("disembodied_armor"), 7, MagicThingsRarity.RARE).with($ -> {
            $.spellCost = 4;
            $.effectAmount = 8.0;
            $.cooldown = 1; // 0 seconds
            $.effectStackable = false;
            $.priority = 3;
        })	.build());

        ///// drain spells /////
        SpellRegistry.register(new DrainSpell.Builder(ModUtil.asLocation("drain"), 3, MagicThingsRarity.UNCOMMON)
                .with($ -> {
                    $.spellCost = 1.0;
                    $.effectAmount = 2.0;
                    $.frequency = 180; // 9 seconds
                    $.range = 2.0;
                    $.effectStackable = true;
                })	.build());

        SpellRegistry.register(new DrainSpell.Builder(ModUtil.asLocation("greater_drain"), 6, MagicThingsRarity.SCARCE)
                .with($ -> {
                    $.spellCost = 2.0;
                    $.effectAmount = 4.0;
                    $.frequency = 150; // 7.5 seconds
                    $.range = 4.0;
                    $.effectStackable = true;
                })	.build());

        SpellRegistry.register(new DrainSpell.Builder(ModUtil.asLocation("desiccate"), 9, MagicThingsRarity.EPIC)
                .with($ -> {
                    $.spellCost = 3.0;
                    $.effectAmount = 6.0;
                    $.frequency = 120; // 6 seconds
                    $.range = 6.0;
                    $.effectStackable = true;
                })	.build());

        ///// fire resistance spells /////
        SpellRegistry.register(new FireResistanceSpell.Builder(ModUtil.asLocation("fire_resistance"), 1, MagicThingsRarity.COMMON).with($ -> {
            $.spellCost = 0;
            $.effectAmount = 0.3;
            $.effectStackable = true;
            $.priority = 2;
        })	.build());

        SpellRegistry.register(new FireResistanceSpell.Builder(ModUtil.asLocation("fire_ward"), 4, MagicThingsRarity.SCARCE).with($ -> {
            $.spellCost = 0;
            $.effectAmount = 0.7;
            $.effectStackable = true;
            $.priority = 2;
        })	.build());

        SpellRegistry.register(new FireResistanceSpell.Builder(ModUtil.asLocation("blessing_of_the_phoenix"), 7, MagicThingsRarity.RARE).with($ -> {
            $.spellCost = 0;
            $.effectAmount = 1.0;
            $.effectStackable = true;
            $.priority = 1;
        })	.build());

        ///// magic resistance spells /////
        SpellRegistry.register(new MagicResistanceSpell.Builder(ModUtil.asLocation("magic_resistance"), 2, MagicThingsRarity.COMMON).with($ -> {
            $.spellCost = 0;
            $.effectAmount = 0.3;
            $.effectStackable = true;
            $.priority = 1;
        })	.build());

        SpellRegistry.register(new MagicResistanceSpell.Builder(ModUtil.asLocation("magic_repulsion"), 5, MagicThingsRarity.SCARCE).with($ -> {
            $.spellCost = 0;
            $.effectAmount = 0.7;
            $.effectStackable = true;
            $.priority = 1;
        })	.build());

        SpellRegistry.register(new MagicResistanceSpell.Builder(ModUtil.asLocation("magic_immunity"), 8, MagicThingsRarity.EPIC).with($ -> {
            $.spellCost = 0;
            $.effectAmount = 1.0;
            $.effectStackable = true;
            $.priority = 0;
        })	.build());

        // satiety spells
        SpellRegistry.register(new SatietySpell.Builder(ModUtil.asLocation("satiety"), 2, MagicThingsRarity.COMMON).with($ -> {
            $.spellCost = 2.0;
            $.effectAmount = 1.0;
            $.frequency = 100; // 5 seconds
            $.effectStackable = true;
            $.priority = 1;
        })	.build());

        // reflection spells
        SpellRegistry.register(new ReflectionSpell.Builder(ModUtil.asLocation("reflection"), 3, MagicThingsRarity.UNCOMMON).with($ -> {
            $.spellCost = 5; // cost = min(spellCost, reflected amount)
            $.effectAmount = 1.0;
            $.cooldown = 100; // 5 seconds
            $.range = 3.0;
            $.effectStackable = true;
        })	.build());
        SpellRegistry.register(new ReflectionSpell.Builder(ModUtil.asLocation("crushing_response"), 6, MagicThingsRarity.RARE).with($ -> {
            $.spellCost = 8; // cost = min(spellCost, reflected amount)
            $.effectAmount = 2.0;
            $.cooldown = 100; // 5 seconds
            $.range = 6.0;
            $.effectStackable = true;
        })	.build());

        // paladin strike spells
        SpellRegistry.register(new PaladinStrikeSpell.Builder(ModUtil.asLocation("paladin_strike"), 4, MagicThingsRarity.SCARCE)
                .withLifeCost(2.0)
                .with($ -> {
                    $.spellCost = 1.0;
                    $.effectAmount = 2.0;
                    $.cooldown = 100; // 5 seconds
                    $.effectStackable = false;
                })	.build());

        SpellRegistry.register(new PaladinStrikeSpell.Builder(ModUtil.asLocation("paladin_smite"), 8, MagicThingsRarity.SCARCE)
                .withLifeCost(2.0)
                .with($ -> {
                    $.spellCost = 2.0;
                    $.effectAmount = 4.0;
                    $.cooldown = 100; // 5 seconds
                    $.effectStackable = false;
                })	.build());
    }

    /*
     * a do-nothing method used to force-load static properties
     */
    public static void init() {
    }
}
