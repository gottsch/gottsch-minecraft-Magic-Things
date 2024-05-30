package mod.gottsch.forge.magic_things.core.event;

import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.item.Gemstone;
import mod.gottsch.forge.magic_things.core.tag.MagicThingsTags;
import mod.gottsch.forge.magic_things.core.util.LangUtil;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 
 * @author Mark Gottschling May 26, 2024
 *
 */
@Mod.EventBusSubscriber(modid = MagicThings.MOD_ID)
public class ItemEventHandler {

	@SubscribeEvent
	public static void onItemInfo(ItemTooltipEvent event) {
		if (!(event.getItemStack().getItem() instanceof Gemstone) && event.getItemStack().is(MagicThingsTags.Items.STONES)) {
			event.getToolTip().add(new TranslatableComponent(LangUtil.tooltip("gemstone.usage")).withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
			event.getToolTip().add(new TranslatableComponent(LangUtil.NEWLINE));
		}
	}
}
