
package mod.gottsch.forge.gealdorcraft.datagen;

import mod.gottsch.forge.gealdorcraft.GealdorCraft;
import mod.gottsch.forge.gealdorcraft.core.item.GealdorCraftItems;
import mod.gottsch.forge.gealdorcraft.core.util.LangUtil;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

/**
 * 
 * @author Mark Gottschling Jun 2, 2023
 *
 */
public class LanguageGen extends LanguageProvider {

    public LanguageGen(DataGenerator gen, String locale) {
        super(gen, GealdorCraft.MOD_ID, locale);
    }
    
    @Override
    protected void addTranslations() {
    	// tabs
        add("itemGroup." + GealdorCraft.MOD_ID, "GealdorCraft");
        add("itemGroup." + GealdorCraft.MOD_ID + ".jewelry_tab", "GealdorCraft2 Jewelry");
        
        // ringss
        add(GealdorCraftItems.COPPER_RING.get(), "Copper Ring");

        // TODO build a resource location to lookup items from
        
        // TODO need a registry by resource location to store all items
        
        /*
         *  Util.tooltips
         */
        // general
        add(LangUtil.tooltip("boolean.yes"), "Yes");
        add(LangUtil.tooltip("boolean.no"), "No");
        add(LangUtil.tooltip("hold_shift"), "Hold [SHIFT] to expand");

        /*
         * screens
         */
        // chests
        add(LangUtil.screen("wood_chest.name"), "Wood Chest");
   
        /*
         *  chat
         */
        // keys
        add(LangUtil.chat("key.key_break"), "Your key broke whilst attempting to unlock the lock!");
    
    }
}
