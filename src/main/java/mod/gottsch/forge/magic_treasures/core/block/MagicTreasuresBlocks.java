package mod.gottsch.forge.magic_treasures.core.block;

import mod.gottsch.forge.magic_treasures.core.setup.Registration;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class MagicTreasuresBlocks {

    // ore
    public static final Supplier<BlockBehaviour.Properties> ORE_PROPS = () -> BlockBehaviour.Properties.copy(Blocks.STONE);
    public static final Supplier<BlockBehaviour.Properties> DEEPSLATE_ORE_PROPS = () -> BlockBehaviour.Properties.copy(Blocks.DEEPSLATE);

    // TODO look up deepslate hardness
    public static final RegistryObject<Block> TOPAZ_ORE = Registration.BLOCKS.register("topaz_ore", () -> new Block(ORE_PROPS.get()));
    public static final RegistryObject<Block> DEEPSLATE_TOPAZ_ORE = Registration.BLOCKS.register("deepslate_topaz_ore", () -> new Block(DEEPSLATE_ORE_PROPS.get()));

    public static final RegistryObject<Block> ONYX_ORE = Registration.BLOCKS.register("onyx_ore", () -> new Block(ORE_PROPS.get()));
    public static final RegistryObject<Block> DEEPSLATE_ONYX_ORE = Registration.BLOCKS.register("deepslate_onyx_ore", () -> new Block(DEEPSLATE_ORE_PROPS.get()));

    public static final RegistryObject<Block> JADEITE_ORE = Registration.BLOCKS.register("jadeite_ore", () -> new Block(ORE_PROPS.get()));
    public static final RegistryObject<Block> DEEPSLATE_JADEITE_ORE = Registration.BLOCKS.register("deepslate_jadeite_ore", () -> new Block(DEEPSLATE_ORE_PROPS.get()));

    public static final RegistryObject<Block> RUBY_ORE = Registration.BLOCKS.register("ruby_ore", () -> new Block(ORE_PROPS.get()));
    public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = Registration.BLOCKS.register("deepslate_ruby_ore", () -> new Block(DEEPSLATE_ORE_PROPS.get()));

    public static final RegistryObject<Block> SAPPHIRE_ORE = Registration.BLOCKS.register("sapphire_ore", () -> new Block(ORE_PROPS.get()));
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = Registration.BLOCKS.register("deepslate_sapphire_ore", () -> new Block(DEEPSLATE_ORE_PROPS.get()));

    public static final RegistryObject<Block> SILVER_ORE = Registration.BLOCKS.register("silver_ore", () -> new Block(ORE_PROPS.get()));
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = Registration.BLOCKS.register("deepslate_silver_ore", () -> new Block(DEEPSLATE_ORE_PROPS.get()));

    /**
     *
     */
    public static void register() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Registration.BLOCKS.register(eventBus);
    }
}
