package mod.gottsch.forge.magic_treasures.datagen;

import mod.gottsch.forge.magic_treasures.core.block.MagicTreasuresBlocks;
import mod.gottsch.forge.magic_treasures.core.item.MagicTreasuresItems;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;

/**
 * Created by Mark Gottschling on Nov 12, 2024
 */
public class BlockLootTables extends BlockLoot {

    public BlockLootTables() {
        super();
    }

    @Override
    public void addTables() {
        this.add(MagicTreasuresBlocks.DEEPSLATE_JADEITE_ORE.get(), block -> createOreDrop(block,
                MagicTreasuresItems.DEEPSLATE_JADEITE_ORE.get()));
        this.add(MagicTreasuresBlocks.DEEPSLATE_ONYX_ORE.get(), block -> createOreDrop(block,
                MagicTreasuresItems.DEEPSLATE_ONYX_ORE.get()));
        this.add(MagicTreasuresBlocks.DEEPSLATE_RUBY_ORE.get(), block -> createOreDrop(block,
                MagicTreasuresItems.DEEPSLATE_RUBY_ORE.get()));
        this.add(MagicTreasuresBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), block -> createOreDrop(block,
                MagicTreasuresItems.DEEPSLATE_SAPPHIRE_ORE.get()));
        this.add(MagicTreasuresBlocks.DEEPSLATE_TOPAZ_ORE.get(), block -> createOreDrop(block,
                MagicTreasuresItems.DEEPSLATE_TOPAZ_ORE.get()));

        this.add(MagicTreasuresBlocks.JADEITE_ORE.get(), block -> createOreDrop(block,
                MagicTreasuresItems.JADEITE_ORE.get()));
        this.add(MagicTreasuresBlocks.ONYX_ORE.get(), block -> createOreDrop(block,
                MagicTreasuresItems.ONYX_ORE.get()));
        this.add(MagicTreasuresBlocks.RUBY_ORE.get(), block -> createOreDrop(block,
                MagicTreasuresItems.RUBY_ORE.get()));
        this.add(MagicTreasuresBlocks.SAPPHIRE_ORE.get(), block -> createOreDrop(block,
                MagicTreasuresItems.SAPPHIRE_ORE.get()));
        this.add(MagicTreasuresBlocks.TOPAZ_ORE.get(), block -> createOreDrop(block,
                MagicTreasuresItems.TOPAZ_ORE.get()));

        this.add(MagicTreasuresBlocks.SILVER_ORE.get(), block -> createOreDrop(block,
                MagicTreasuresItems.RAW_SILVER.get()));

        this.add(MagicTreasuresBlocks.DEEPSLATE_SILVER_ORE.get(), block -> createOreDrop(block,
                MagicTreasuresItems.RAW_SILVER.get()));
    }

//    @Override
//    protected Iterable<Block> getKnownBlocks() {
//        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
//    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        // only return blocks that are generated in this class
        return Arrays.asList(
                MagicTreasuresBlocks.DEEPSLATE_JADEITE_ORE.get(),
                MagicTreasuresBlocks.DEEPSLATE_ONYX_ORE.get(),
                MagicTreasuresBlocks.DEEPSLATE_RUBY_ORE.get(),
                MagicTreasuresBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                MagicTreasuresBlocks.DEEPSLATE_TOPAZ_ORE.get(),
                MagicTreasuresBlocks.DEEPSLATE_SILVER_ORE.get(),

                MagicTreasuresBlocks.JADEITE_ORE.get(),
                MagicTreasuresBlocks.ONYX_ORE.get(),
                MagicTreasuresBlocks.RUBY_ORE.get(),
                MagicTreasuresBlocks.SAPPHIRE_ORE.get(),
                MagicTreasuresBlocks.TOPAZ_ORE.get(),
                MagicTreasuresBlocks.SILVER_ORE.get()
        );
    }
}
