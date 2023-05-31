package mod.gottsch.forge.gealdorcraft.core.item;

import mod.gottsch.forge.gealdorcraft.core.GealdorCraft;
import mod.gottsch.forge.gealdorcraft.core.setup.Registration;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * Created by Mark Gottschling on 5/29/2023
 */
public class GealdorCraftItems {
    // item groups/tabs
    public static final CreativeModeTab GEALDORCRAFT_ITEM_GROUP = new CreativeModeTab(GealdorCraft.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(GealdorCraftItems.GEALDORCRAFT_TAB.get());
        }
    };

    public static final Supplier<Item.Properties> GEALDORCRAFT_PROPS_SUPPLIER = () -> new Item.Properties().tab(GEALDORCRAFT_ITEM_GROUP);
    // tab items
    public static final RegistryObject<Item> GEALDORCRAFT_TAB = Registration.ITEMS.register("gealdor_tab", () -> new Item(new Item.Properties()));

    public static RegistryObject<Item> COPPER_RING = Registration.ITEMS.register("copper_ring", () -> new Jewelry(GEALDORCRAFT_PROPS_SUPPLIER.get()));
}
