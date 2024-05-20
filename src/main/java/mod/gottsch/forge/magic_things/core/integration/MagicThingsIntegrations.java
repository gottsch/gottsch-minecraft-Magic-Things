
package mod.gottsch.forge.magic_things.core.integration;

import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.config.Config;
import mod.gottsch.forge.magic_things.core.event.IEquipmentSpellHandler;
import mod.gottsch.forge.magic_things.core.event.SpellEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;

/**
 * 
 * @author Mark Gottschling on Sep 16, 2022
 *
 */
public class MagicThingsIntegrations {
	private static final String className = "mod.gottsch.forge.magic_things.core.event.CuriosEquipmentSpellHandler";

	public static void registerCuriosIntegration() {
		IEquipmentSpellHandler equipmentSpellHandler = null;
		if (Config.SERVER.integration.enableCurios.get() && ModList.get().isLoaded("curios")) {
			MagicThings.LOGGER.debug("curios IS loaded");
			try {
				equipmentSpellHandler =
						(IEquipmentSpellHandler) Class.forName(className).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				MagicThings.LOGGER.warn("Unable to load Curios compatibility class -> {}", className);
			}
		}
		if (equipmentSpellHandler == null) {
			MagicThings.LOGGER.debug("equipmentHandler is null");
//			equipmentCharmHandler = new HotbarEquipmentCharmHandler();
		}
		MinecraftForge.EVENT_BUS.register(new SpellEventHandler(equipmentSpellHandler));
	}
}
