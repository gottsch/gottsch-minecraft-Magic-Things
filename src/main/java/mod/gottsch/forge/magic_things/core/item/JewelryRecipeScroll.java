package mod.gottsch.forge.magic_things.core.item;

import mod.gottsch.forge.magic_things.core.util.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by Mark Gottschling on May 12, 2024
 */
public class JewelryRecipeScroll extends Item {

    public JewelryRecipeScroll(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component>tooltip, TooltipFlag flag) {
        super.appendHoverText(itemStack, level, tooltip, flag);
        tooltip.add(new TranslatableComponent(LangUtil.tooltip("recipe.jewelry.usage")).withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));

    }
}
