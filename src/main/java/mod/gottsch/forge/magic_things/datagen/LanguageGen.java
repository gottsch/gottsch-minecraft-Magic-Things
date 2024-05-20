
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

        // rings
//        add(GealdorCraftItems.COPPER_RING.get(), "Copper Ring");

        // TODO build a resource location to lookup items from
        
        // TODO need a registry by resource location to store all items
        
		MagicThingsItems.STANDARD_JEWELRY.forEach(item -> {
			add(item.get(), WordUtils.capitalizeFully(item.get().getRegistryName().getPath().replace("_", " ")));
		});

        add(MagicThingsItems.MEDICS_TOKEN.get(), WordUtils.capitalizeFully("Medic's Token"));

//        add(MagicThingsItems.HEALING_SCROLL.get(), "Scroll of Healing");

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
        add(LangUtil.tooltip("hold_shift"), "Hold [SHIFT] to expand");

        // tools
        add(LangUtil.tooltip("tools.jewelry_pliers"), "Required for removing gemstones from jewelry.");

        // gemstones
        add(LangUtil.tooltip("gemstone.usage"), "Place on an anvil with jewelry to either add gemstone to the jewelry or to recharge it.");

        // jewelry
        add(LangUtil.tooltip("jewelry.usage"), "Hold in hand or equip for buffs.");
        add(LangUtil.tooltip("jewelry.material"), "Material: %s");
        add(LangUtil.tooltip("jewelry.stone"), "Stone: %s");

        add(LangUtil.tooltip("jewelry.durability.amount"), "Durability: [%s/%s]");
        add(LangUtil.tooltip("jewelry.durability.amount.infinite"), "Durability: [infinite]");
        add(LangUtil.tooltip("jewelry.durability.repairs"), "Repairs: %s");

        add(LangUtil.tooltip("jewelry.max_level"), "Spell Max. Level: %s");
        add(LangUtil.tooltip("jewelry.mana.recharges"), "Recharges: %s");
        add(LangUtil.tooltip("jewelry.mana.gauge"), "[%s/%s]");
        add(LangUtil.tooltip("jewelry.spells"), "Spells:");

        // spells
        add(LangUtil.tooltip("spell.name"), "Name: %s");
        add(LangUtil.tooltip("spell.level"), "Level: %s");
        add(LangUtil.tooltip("spell.rarity"), "Rarity: %s");
        add(LangUtil.tooltip("spell.cost"), "Cost: %s");

        add(LangUtil.tooltip("spell.name.lesser_healing"), "Lesser Healing");
        add(LangUtil.tooltip("spell.name.healing"), "Healing");
        add(LangUtil.tooltip("spell.name.greater_healing"), "Greater Healing");
        add(LangUtil.tooltip("spell.name.regeneration"), "Regeneration");

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
