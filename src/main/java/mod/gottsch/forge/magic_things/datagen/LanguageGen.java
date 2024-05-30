/*
 * This file is part of  Magic Things.
 * Copyright (c) 2023 Mark Gottschling (gottsch)
 *
 * Magic Things is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Magic Things is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Magic Things.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
package mod.gottsch.forge.magic_things.datagen;

import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.block.MagicThingsBlocks;
import mod.gottsch.forge.magic_things.core.item.MagicThingsItems;
import mod.gottsch.forge.magic_things.core.item.SpellScroll;
import mod.gottsch.forge.magic_things.core.setup.Registration;
import mod.gottsch.forge.magic_things.core.util.LangUtil;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.text.WordUtils;

/**
 * 
 * @author Mark Gottschling Jun 2, 2023
 *
 */
@SuppressWarnings("deprecation")
public class LanguageGen extends LanguageProvider {

    public LanguageGen(DataGenerator gen, String locale) {
        super(gen, MagicThings.MOD_ID, locale);
    }
    
    @Override
    protected void addTranslations() {
    	// tabs
        add("itemGroup." + MagicThings.MOD_ID, "Magic Things");
        add("itemGroup." + MagicThings.MOD_ID + ".jewelry_tab", "Magic Things Jewelry");

        // metals
        add(MagicThingsItems.SILVER_INGOT.get(), "Silver Ingot");

        // stones
        add(MagicThingsItems.TOPAZ.get(), "Topaz");
        add(MagicThingsItems.ONYX.get(), "Onyx");
        add(MagicThingsItems.JADEITE.get(), "Jadeite");
        add(MagicThingsItems.RUBY.get(), "Ruby");
        add(MagicThingsItems.SAPPHIRE.get(), "Sapphire");
        add(MagicThingsItems.WHITE_PEARL.get(), "White Pearl");
        add(MagicThingsItems.BLACK_PEARL.get(), "Black Pearl");
//        add(MagicThingsItems.SKELETONS_HEART.get(), "Skeleton's Heart");

        // regular jewelry
		MagicThingsItems.STANDARD_JEWELRY.forEach(item -> {
			add(item.get(), WordUtils.capitalizeFully(item.get().getRegistryName().getPath().replace("_", " ")));
		});

        // special jewelry
        add(MagicThingsItems.PEASANTS_FORTUNE.get(), WordUtils.capitalizeFully("Peasant's Fortune"));
        add(MagicThingsItems.MEDICS_TOKEN.get(), WordUtils.capitalizeFully("Medic's Token"));
        add(MagicThingsItems.ADEPHAGIAS_BOUNTY.get(), WordUtils.capitalizeFully("Adephagia's Bounty"));
        add(MagicThingsItems.ANGELS_RING.get(), WordUtils.capitalizeFully("Ring of Angels"));
        add(MagicThingsItems.SALANDAARS_WARD.get(), WordUtils.capitalizeFully("Sal'andaar's Ward"));
        add(MagicThingsItems.RING_OF_FORTITUDE.get(), WordUtils.capitalizeFully("Ring of Fortitude"));
        add(MagicThingsItems.EYE_OF_THE_PHOENIX.get(), WordUtils.capitalizeFully("Eye of the Phoenix"));
        add(MagicThingsItems.RING_LIFE_DEATH.get(), WordUtils.capitalizeFully("Ring of Life and Death"));

         // GOTTSCHS_AMULET_OF_HEAVENS
        // GOTTSCHS_RING_OF_MOON
        // SHADOWS_GIFT

        // belts
        add(MagicThingsItems.SKULL_BELT.get(), WordUtils.capitalizeFully("Skull Belt"));

        // scrolls
        Registration.ITEMS.getEntries().forEach(o -> {
            if (o.get() instanceof SpellScroll) {
                add(o.get(), WordUtils.capitalizeFully("Scroll of " + ((SpellScroll) o.get()).getSpell().getName().getPath().replace("_", " ")));
            }
        });

        // recipes scrolls
        add(MagicThingsItems.RING_RECIPE.get(), "Ring Recipe");
        add(MagicThingsItems.NECKLACE_RECIPE.get(), "Necklace Recipe");
        add(MagicThingsItems.BRACELET_RECIPE.get(), "Bracelet Recipe");

        // ore
        add(MagicThingsBlocks.TOPAZ_ORE.get(), "Topaz Ore");
        add(MagicThingsBlocks.ONYX_ORE.get(), "Onyx Ore");
        add(MagicThingsBlocks.JADEITE_ORE.get(), "Jadeite Ore");
        add(MagicThingsBlocks.RUBY_ORE.get(), "Ruby Ore");
        add(MagicThingsBlocks.SAPPHIRE_ORE.get(), "Sapphire Ore");
        add(MagicThingsBlocks.SILVER_ORE.get(), "Silver Ore");

        add(MagicThingsBlocks.DEEPSLATE_TOPAZ_ORE.get(), "Deepslate Topaz Ore");
        add(MagicThingsBlocks.DEEPSLATE_ONYX_ORE.get(), "Deepslate Onyx Ore");
        add(MagicThingsBlocks.DEEPSLATE_JADEITE_ORE.get(), "Deepslate Jadeite Ore");
        add(MagicThingsBlocks.DEEPSLATE_RUBY_ORE.get(), "Deepslate Ruby Ore");
        add(MagicThingsBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), "Deepslate Sapphire Ore");
        add(MagicThingsBlocks.DEEPSLATE_SILVER_ORE.get(), "Deepslate Silver Ore");

        
        /*
         *  Util.tooltips
         */
        // general
        add(LangUtil.tooltip("boolean.yes"), "Yes");
        add(LangUtil.tooltip("boolean.no"), "No");
        add(LangUtil.tooltip("infinite"), "Infinite");
        add(LangUtil.tooltip("hold_shift"), "Hold [SHIFT] to expand");
        add(LangUtil.tooltip("divider"), "--------------------");

        // tools
        add(LangUtil.tooltip("tools.jewelry_pliers"), "Required for removing gemstones from jewelry.");

        // gemstones
        add(LangUtil.tooltip("gemstone.usage"), "Place on an anvil with Magic Things jewelry to combine.");
        add(LangUtil.tooltip("gemstone.rarity"), "Rarity: %s");
        add(LangUtil.tooltip("gemstone.tier"), "Tier: %s");
        add(LangUtil.tooltip("gemstone.mana"), "Mana: %s");
        add(LangUtil.tooltip("gemstone.recharges"), "Recharges: %s");
        add(LangUtil.tooltip("gemstone.cost_factor"), "Mana Cost: %s");
        add(LangUtil.tooltip("gemstone.cooldown_factor"), "Cooldown Time: %s");
        add(LangUtil.tooltip("gemstone.effect_amount_factor"), "Effect Amount: %s");
        add(LangUtil.tooltip("gemstone.frequency_factor"), "Frequency: %s");
        add(LangUtil.tooltip("gemstone.range_factor"), "Range: %s");

        // gemstone tier names
//        add(LangUtil.tooltip("gemstone.tier.tier1.name"), "Tier 1");
//        add(LangUtil.tooltip("gemstone.tier.skeletons_heart.name"), "Skeleton's Heart");

        // jewelry
        add(LangUtil.tooltip("jewelry.usage"), "Hold in hand or equip for buffs.");
        add(LangUtil.tooltip("jewelry.material"), "Material: %s");
        add(LangUtil.tooltip("jewelry.stone"), "Stone: %s");

//        add(LangUtil.tooltip("jewelry.durability"), "Durability: ");
//        add(LangUtil.tooltip("jewelry.durability.gauge"), "[%s/%s]");
        add(LangUtil.tooltip("jewelry.durability.amount"), "Durability: [%s/%s]");
        add(LangUtil.tooltip("jewelry.durability.infinite"), "Durability: -%s-");
        add(LangUtil.tooltip("jewelry.durability.repairs"), "Repairs: %s");

        add(LangUtil.tooltip("jewelry.max_level"), "Spell Max. Level: %s");
        add(LangUtil.tooltip("jewelry.mana"), "Mana: [%s/%s]");
        add(LangUtil.tooltip("jewelry.mana.recharges"), "Recharges: %s");
        add(LangUtil.tooltip("jewelry.mana.gauge"), "[%s/%s]");
        add(LangUtil.tooltip("jewelry.spells"), "Spells:");

        add(LangUtil.tooltip("jewelry.stats.cost_factor"), "C:%s");
        add(LangUtil.tooltip("jewelry.stats.cooldown_factor"), "Cd:%s");
        add(LangUtil.tooltip("jewelry.stats.effect_amount_factor"), "E:%s");
        add(LangUtil.tooltip("jewelry.stats.frequency_factor"), "Fq:%s");
        add(LangUtil.tooltip("jewelry.stats.range_factor"), "R:%s");

        /*
         * spells
         */

        add(LangUtil.tooltip("spell.name"), "Name: %s");
        add(LangUtil.tooltip("spell.level"), "Level: %s");
        add(LangUtil.tooltip("spell.rarity"), "Rarity: %s");
        add(LangUtil.tooltip("spell.cost"), "Cost: %s");

        ///// healing /////
        add(LangUtil.tooltip("spell.name.lesser_healing"), "Lesser Healing");
        add(LangUtil.tooltip("spell.name.healing"), "Healing");
        add(LangUtil.tooltip("spell.name.greater_healing"), "Greater Healing");
        add(LangUtil.tooltip("spell.name.regeneration"), "Regeneration");

        add(LangUtil.tooltip("spell.healing.rate"), "Heals %s hp every %s seconds");

        ///// mana shield /////
        add(LangUtil.tooltip("spell.name.mana_buckler"), "Mana Buckler");
        add(LangUtil.tooltip("spell.name.mana_shield"), "Mana Shield");
        add(LangUtil.tooltip("spell.name.mana_tower_shield"), "Mana Tower Shield");
        add(LangUtil.tooltip("spell.name.mana_pavise_shield"), "Mana Pavise Shield");

        add(LangUtil.tooltip("spell.mana_shield.rate"), "Absorbs %s damage.");

        ///// spectral armor /////
        add(LangUtil.tooltip("spell.name.ghostly_armor"), "Ghostly Armor");
        add(LangUtil.tooltip("spell.name.spectral_armor"), "Spectral Armor");
        add(LangUtil.tooltip("spell.name.shadow_armor"), "Shadow Armor");
        add(LangUtil.tooltip("spell.name.disembodied_armor"), "Disembodied Armor");

        add(LangUtil.tooltip("spell.spectral_armor.rate"), "Reduces %s mob damage.");

        ///// drain /////
        add(LangUtil.tooltip("spell.name.drain"), "Drain");
        add(LangUtil.tooltip("spell.name.greater_drain"), "Greater Drain");
        add(LangUtil.tooltip("spell.name.desiccate"), "Desiccate");

        add(LangUtil.tooltip("spell.drain.rate"), "Drains %s hp from mobs within %s blocks every %s seconds.");

        ///// fire resistance /////
        add(LangUtil.tooltip("spell.name.fire_resistance"), "Fire Resistance");
        add(LangUtil.tooltip("spell.name.fire_ward"), "Fire Ward");
        add(LangUtil.tooltip("spell.name.blessing_of_the_phoenix"), "Blessing of the Phoenix");

        add(LangUtil.tooltip("spell.fire_resistance.rate"), "Resists %s fire damage.");

        ///// magic resistance /////
        add(LangUtil.tooltip("spell.name.magic_resistance"), "Magic Resistance");
        add(LangUtil.tooltip("spell.name.magic_ward"), "Magic Ward");
        add(LangUtil.tooltip("spell.name.salandaars_magic_coat"), "Sal'andaar's Magic Coat");

        add(LangUtil.tooltip("spell.magic_resistance.rate"), "Resists %s magic damage.");

        ///// wither resistance /////
        add(LangUtil.tooltip("spell.name.wither_resistance"), "Wither Resistance");
        add(LangUtil.tooltip("spell.name.wither_ward"), "Wither Ward");
        add(LangUtil.tooltip("spell.name.withers_skin"), "Wither's Skin");

        add(LangUtil.tooltip("spell.wither_resistance.rate"), "Resists %s wither damage.");

        ///// reflection /////
        add(LangUtil.tooltip("spell.name.reflection"), "Reflection");
        add(LangUtil.tooltip("spell.name.crushing_response"), "Crushing Response");

        add(LangUtil.tooltip("spell.reflection.rate"), "Reflects %s%% damage back onto mob.");

        ///// paladin strike /////
        add(LangUtil.tooltip("spell.name.paladin_strike"), "Paladin Strike");
        add(LangUtil.tooltip("spell.name.paladin_smite"), "Paladin Smite");

        add(LangUtil.tooltip("spell.paladin_strike.rate"), "Inflicts an addition %s hp every %s seconds costing %s hp.");

        ///// satiety /////
        add(LangUtil.tooltip("spell.name.satiety"), "Satiety");
        add(LangUtil.tooltip("spell.satiety.rate"), "Restores 0.5 hunger every %s seconds.");

        ///// cheat death /////
        add(LangUtil.tooltip("spell.name.cheat_death"), "Cheat Death");
        add(LangUtil.tooltip("spell.cheat_death.rate"), "Prevents death. Cooldown: %s seconds.");


        // scrolls
        add(LangUtil.tooltip("spell_scroll.usage"), "Place on an anvil with jewelry to combine.");

        add(LangUtil.tooltip("jewelry_recipe_scroll.usage"), "Combine with 4 ingots or respective metal to craft jewelry item.");

        /*
         * screens
         */
        // chests
        //add(LangUtil.screen("wood_chest.name"), "Wood Chest");
   
        /*
         *  chat
         */
        // keys
        //add(LangUtil.chat("key.key_break"), "Your key broke whilst attempting to unlock the lock!");
    
    }
}
