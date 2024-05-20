
package mod.gottsch.forge.magic_things.datagen;

import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.block.MagicThingsBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;


public class MagicThingsBlockTagsProvider extends BlockTagsProvider {
    /**
     * 
     * @param generatorIn
     * @param existingFileHelper
     */
	public MagicThingsBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, MagicThings.MOD_ID, existingFileHelper);
    }
    
    @Override
    protected void addTags() {
    	// blocks rarity
//    	tag(TreasureTags.Blocks.COMMON_CHESTS).add(TreasureBlocks.WOOD_CHEST.get());

        // ores needs pickaxe
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicThingsBlocks.JADEITE_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicThingsBlocks.ONYX_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicThingsBlocks.TOPAZ_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicThingsBlocks.RUBY_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicThingsBlocks.SAPPHIRE_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicThingsBlocks.SILVER_ORE.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicThingsBlocks.DEEPSLATE_JADEITE_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicThingsBlocks.DEEPSLATE_ONYX_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicThingsBlocks.DEEPSLATE_TOPAZ_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicThingsBlocks.DEEPSLATE_RUBY_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicThingsBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicThingsBlocks.DEEPSLATE_SILVER_ORE.get());

        // ore needs stone tool
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(MagicThingsBlocks.SILVER_ORE.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(MagicThingsBlocks.DEEPSLATE_SILVER_ORE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicThingsBlocks.TOPAZ_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicThingsBlocks.ONYX_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicThingsBlocks.JADEITE_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicThingsBlocks.RUBY_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicThingsBlocks.SAPPHIRE_ORE.get());
        // ore needs iron tool
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicThingsBlocks.DEEPSLATE_TOPAZ_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicThingsBlocks.DEEPSLATE_ONYX_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicThingsBlocks.DEEPSLATE_JADEITE_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicThingsBlocks.DEEPSLATE_RUBY_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicThingsBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
    }
}
