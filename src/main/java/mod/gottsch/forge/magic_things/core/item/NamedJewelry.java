package mod.gottsch.forge.magic_things.core.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * May 7, 2024
 */
public class NamedJewelry extends Jewelry {
    public NamedJewelry(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        return ((MutableComponent)super.getName(stack)).withStyle(ChatFormatting.YELLOW);
    }
}
