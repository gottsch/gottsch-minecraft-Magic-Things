
package mod.gottsch.forge.magic_things.datagen;

import mod.gottsch.forge.magic_things.MagicThings;
import org.apache.commons.lang3.text.WordUtils;

import mod.gottsch.forge.magic_things.core.item.MagicThingsItems;
import mod.gottsch.forge.magic_things.core.util.LangUtil;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

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
        
        // ringss
//        add(GealdorCraftItems.COPPER_RING.get(), "Copper Ring");

        // TODO build a resource location to lookup items from
        
        // TODO need a registry by resource location to store all items
        
		MagicThingsItems.ALL_JEWELRY.forEach(item -> {
			add(item.get(), WordUtils.capitalizeFully(item.get().getRegistryName().getPath().replace("_", " ")));
		});
        
        /*
         *  Util.tooltips
         */
        // general
        add(LangUtil.tooltip("boolean.yes"), "Yes");
        add(LangUtil.tooltip("boolean.no"), "No");
        add(LangUtil.tooltip("hold_shift"), "Hold [SHIFT] to expand");

        // jewelry
        add(LangUtil.tooltip("jewelry.usage"), "Hold in hand or equip for buffs");
        add(LangUtil.tooltip("jewelry.max_level"), "Spell Max. Level: %s");

        // spells
        add(LangUtil.tooltip("spell.name.lesser_healing"), "Lesser Healing");
        add(LangUtil.tooltip("spell.name.healing"), "Healing");
        add(LangUtil.tooltip("spell.name.greater_healing"), "Greater Healing");
        add(LangUtil.tooltip("spell.name.regeneration"), "Regeneration");

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
