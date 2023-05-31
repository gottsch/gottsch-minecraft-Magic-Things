package mod.gottsch.forge.gealdorcraft.core.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Created by Mark Gottschling on 5/29/2023
 */
public class Jewelry extends Item implements IJewelry{

    /**
     *
     * @param properties
     */
    public Jewelry(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack itemStack) {
        if (isNamed()) {
            return new TranslatableComponent(this.getDescriptionId(itemStack)).withStyle(ChatFormatting.YELLOW);
        } else {
            return new TranslatableComponent(this.getDescriptionId(itemStack));
        }
    }
}
