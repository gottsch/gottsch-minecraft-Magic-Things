
package mod.gottsch.forge.magic_things.datagen;

import mod.gottsch.forge.magic_things.MagicThings;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;


public class GealdorCraftBlockTagsProvider extends BlockTagsProvider {
    /**
     * 
     * @param generatorIn
     * @param existingFileHelper
     */
	public GealdorCraftBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, MagicThings.MOD_ID, existingFileHelper);
    }
    
    @Override
    protected void addTags() {
    	// blocks rarity
//    	tag(TreasureTags.Blocks.COMMON_CHESTS).add(TreasureBlocks.WOOD_CHEST.get());

    }
}
