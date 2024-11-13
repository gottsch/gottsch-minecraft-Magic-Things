
package mod.gottsch.forge.magic_treasures.datagen;

import mod.gottsch.forge.magic_treasures.MagicTreasures;
import mod.gottsch.forge.magic_treasures.core.block.MagicTreasuresBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;


public class MagicTreasuresBlockTagsProvider extends BlockTagsProvider {
    /**
     * 
     * @param generatorIn
     * @param existingFileHelper
     */
	public MagicTreasuresBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, MagicTreasures.MOD_ID, existingFileHelper);
    }
    
    @Override
    protected void addTags() {

    	// blocks rarity
//    	tag(TreasureTags.Blocks.COMMON_CHESTS).add(TreasureBlocks.WOOD_CHEST.get());

        // ores needs pickaxe
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicTreasuresBlocks.JADEITE_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicTreasuresBlocks.ONYX_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicTreasuresBlocks.TOPAZ_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicTreasuresBlocks.RUBY_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicTreasuresBlocks.SAPPHIRE_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicTreasuresBlocks.SILVER_ORE.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicTreasuresBlocks.DEEPSLATE_JADEITE_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicTreasuresBlocks.DEEPSLATE_ONYX_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicTreasuresBlocks.DEEPSLATE_TOPAZ_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicTreasuresBlocks.DEEPSLATE_RUBY_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicTreasuresBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MagicTreasuresBlocks.DEEPSLATE_SILVER_ORE.get());

        // ore needs stone tool
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(MagicTreasuresBlocks.SILVER_ORE.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(MagicTreasuresBlocks.DEEPSLATE_SILVER_ORE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicTreasuresBlocks.TOPAZ_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicTreasuresBlocks.ONYX_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicTreasuresBlocks.JADEITE_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicTreasuresBlocks.RUBY_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicTreasuresBlocks.SAPPHIRE_ORE.get());
        // ore needs iron tool
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicTreasuresBlocks.DEEPSLATE_TOPAZ_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicTreasuresBlocks.DEEPSLATE_ONYX_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicTreasuresBlocks.DEEPSLATE_JADEITE_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicTreasuresBlocks.DEEPSLATE_RUBY_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(MagicTreasuresBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
    }
}
