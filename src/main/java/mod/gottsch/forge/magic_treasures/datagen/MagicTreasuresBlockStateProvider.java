package mod.gottsch.forge.magic_treasures.datagen;

import mod.gottsch.forge.magic_treasures.MagicTreasures;
import mod.gottsch.forge.magic_treasures.core.block.MagicTreasuresBlocks;
import mod.gottsch.forge.magic_treasures.core.tag.MagicTreasuresTags;
import mod.gottsch.forge.treasure2.Treasure;
import mod.gottsch.forge.treasure2.core.block.TreasureBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author Mark Gottschling on Aug 26, 2024
 *
 */
public class MagicTreasuresBlockStateProvider extends BlockStateProvider {

    public MagicTreasuresBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MagicTreasures.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        myDeepslateOreBlock(MagicTreasuresBlocks.DEEPSLATE_JADEITE_ORE.get(),
                "deepslate_ore_parent",
                "deepslate_top_jadeite_ore",
                "deepslate_jadeite_ore"
                );

        myDeepslateOreBlock(MagicTreasuresBlocks.DEEPSLATE_ONYX_ORE.get(),
                "deepslate_ore_parent",
                "deepslate_top_onyx_ore",
                "deepslate_onyx_ore"
        );

        myDeepslateOreBlock(MagicTreasuresBlocks.DEEPSLATE_RUBY_ORE.get(),
                "deepslate_ore_parent",
                "deepslate_top_ruby_ore",
                "deepslate_ruby_ore"
        );

        myDeepslateOreBlock(MagicTreasuresBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                "deepslate_ore_parent",
                "deepslate_top_sapphire_ore",
                "deepslate_sapphire_ore"
        );

        myDeepslateOreBlock(MagicTreasuresBlocks.DEEPSLATE_TOPAZ_ORE.get(),
                "deepslate_ore_parent",
                "deepslate_top_topaz_ore",
                "deepslate_topaz_ore"
        );

        myDeepslateOreBlock(MagicTreasuresBlocks.DEEPSLATE_SILVER_ORE.get(),
                "deepslate_ore_parent",
                "deepslate_top_silver_ore",
                "deepslate_silver_ore"
        );

        myOreBlock(MagicTreasuresBlocks.JADEITE_ORE.get(),
                "ore_parent",
                "jadeite_ore"
        );

        myOreBlock(MagicTreasuresBlocks.ONYX_ORE.get(),
                "ore_parent",
                "onyx_ore"
        );

        myOreBlock(MagicTreasuresBlocks.RUBY_ORE.get(),
                "ore_parent",
                "ruby_ore"
        );
        myOreBlock(MagicTreasuresBlocks.SAPPHIRE_ORE.get(),
                "ore_parent",
                "sapphire_ore"
        );
        myOreBlock(MagicTreasuresBlocks.TOPAZ_ORE.get(),
                "ore_parent",
                "topaz_ore"
        );
        myOreBlock(MagicTreasuresBlocks.SILVER_ORE.get(),
                "ore_parent",
                "silver_ore"
        );
    }

    public void myOreBlock(Block block, String parent, String texture) {
        myOreBlock(block, new ResourceLocation(MagicTreasures.MOD_ID, block(parent)),
                new ResourceLocation(MagicTreasures.MOD_ID, block(texture)));
    }

    public void myOreBlock(Block block, ResourceLocation parent,
                                    ResourceLocation texture) {
        ModelFile model = models().withExistingParent(name(block), parent)
                .texture("0", texture);

        simpleBlock(block, model);
    }

    public void myDeepslateOreBlock(Block block, String parent,
                                    String topTexture, String sideTexture) {

        myDeepslateOreBlock(block, new ResourceLocation(MagicTreasures.MOD_ID, block(parent)),
                new ResourceLocation(MagicTreasures.MOD_ID, block(topTexture)),
                new ResourceLocation(MagicTreasures.MOD_ID, block(sideTexture)));
    }

    public void myDeepslateOreBlock(Block block, ResourceLocation parent,
                                    ResourceLocation topTexture, ResourceLocation sideTexture) {

        ModelFile model = models().withExistingParent(name(block), parent)
                .texture("end", topTexture)
                .texture("side", sideTexture);

        simpleBlock(block, model);
    }

    public BlockModelBuilder twoTextures(String name, ResourceLocation parent,
                                         String textureKey1, ResourceLocation texture1,
                                         String textureKey2, ResourceLocation texture2) {
        return models().withExistingParent(name, parent)
                .texture(textureKey1, texture1)
                .texture(textureKey2, texture2);
    }

    private String block(String name) {
        return "block/" + name;
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}
