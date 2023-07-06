package mod.gottsch.forge.gealdorcraft.core.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by Mark Gottschling on 7/4/2023
 */
public class Gemstone extends Item {

    public Gemstone(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> componentList, TooltipFlag flag) {
        super.appendHoverText(itemStack, level, componentList, flag);

        // TODO see Treasure2 WealthItem for appendHoverText
    }
}
