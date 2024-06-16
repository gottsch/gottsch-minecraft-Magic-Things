
package mod.gottsch.forge.magic_things.core.util;

import java.util.List;
import java.util.function.Consumer;

import mod.gottsch.forge.magic_things.MagicThings;
import org.apache.commons.lang3.StringUtils;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

/**
 * 
 * @author Mark Gottschling Jun 2, 2023
 *
 */
public class LangUtil {
	public static final String BLANK = "";
	public static final String NEWLINE = "";
	public static final String INDENT2 = "  ";
	public static final String INDENT4 = "    ";
	
	/**
	 * 
	 * @param tooltip
	 * @param consumer
	 */
	public static void appendAdvancedHoverText(String modid, List<Component> tooltip, Consumer<List<Component>> consumer) {
		if (!Screen.hasShiftDown()) {
			tooltip.add(new TextComponent(NEWLINE));
			// TODO how do make this call to tooltip generic for any mod because it would require the modid
			tooltip.add(new TranslatableComponent(tooltip(modid, "hold_shift")).withStyle(ChatFormatting.GRAY));
			tooltip.add(new TextComponent(LangUtil.NEWLINE));
		}
		else {
			consumer.accept(tooltip);
		}
	}

	public static void appendHideableHoverText(String modid, List<Component> tooltip, Consumer<List<Component>> consumer) {
		if (!Screen.hasShiftDown()) {
			consumer.accept(tooltip);
		}
	}

    public static String name(String modid, String prefix, String suffix) {
    	return StringUtils.stripEnd(prefix.trim(), ".")
    			+ "."
    			+ modid
    			+ "."
    			+ StringUtils.stripStart(suffix.trim(), ".");
    }
    
    public static String item(String modid, String suffix) {
    	return name(modid, "item", suffix);
    }
    
    public static String tooltip(String modid, String suffix) {
    	return name(modid, "tooltip", suffix);
    }
    
    public static String screen(String modid, String suffix) {
    	return name(modid, "screen", suffix);
    }

	public static String chat(String modid, String suffix) {
		return name(modid, "chat", suffix);
	}
	
	/**
	 * TODO this is Magic Thing's extended methods
	 */
	public static void appendAdvancedHoverText(List<Component> tooltip, Consumer<List<Component>> consumer) {
		LangUtil.appendAdvancedHoverText(MagicThings.MOD_ID, tooltip, consumer);
	}

	public static void appendHideableHoverText(List<Component> tooltip, Consumer<List<Component>> consumer) {
		LangUtil.appendHideableHoverText(MagicThings.MOD_ID, tooltip, consumer);
	}
	
    public static String name(String prefix, String suffix) {
    	return name(MagicThings.MOD_ID, prefix, suffix);
    }
    
    /**
     * 
     * @param suffix
     * @return
     */
    public static String item(String suffix) {
    	return name(MagicThings.MOD_ID, "item", suffix);
    }
    
    public static String tooltip(String suffix) {
    	return name(MagicThings.MOD_ID, "tooltip", suffix);
    }
    
    public static String screen(String suffix) {
    	return name(MagicThings.MOD_ID, "screen", suffix);
    }

	public static String chat(String suffix) {
		return name(MagicThings.MOD_ID, "chat", suffix);
	}

	public static String asPercentString(double value) {
		return MathUtil.r1d(value) + "%";
	}

	public static String negativePercent(double value) {
		return "-" + MathUtil.r0d(Math.min(100, 100 - (value * 100))) + "%";
	}

	public static String
	positivePercent(double value) {
		return "+" + MathUtil.r0d(value * 100 - 100) + "%";
	}
}
