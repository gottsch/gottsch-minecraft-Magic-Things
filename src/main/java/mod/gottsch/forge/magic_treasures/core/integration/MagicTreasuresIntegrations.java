
package mod.gottsch.forge.magic_treasures.core.integration;

import mod.gottsch.forge.magic_treasures.MagicTreasures;
import mod.gottsch.forge.magic_treasures.core.config.Config;
import mod.gottsch.forge.magic_treasures.core.event.IEquipmentSpellHandler;
import mod.gottsch.forge.magic_treasures.core.event.SpellEventHandler;
import mod.gottsch.forge.treasure2.api.TreasureApi;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;

/**
 * 
 * @author Mark Gottschling on Sep 16, 2022
 *
 */
public class MagicTreasuresIntegrations {
	private static final String className = "mod.gottsch.forge.magic_treasures.core.event.CuriosEquipmentSpellHandler";

	public static void registerCuriosIntegration() {
		IEquipmentSpellHandler equipmentSpellHandler = null;
		if (Config.SERVER.integration.enableCurios.get() && ModList.get().isLoaded("curios")) {
			MagicTreasures.LOGGER.debug("curios IS loaded");
			try {
				equipmentSpellHandler =
						(IEquipmentSpellHandler) Class.forName(className).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				MagicTreasures.LOGGER.warn("Unable to load Curios compatibility class -> {}", className);
			}
		}
		if (equipmentSpellHandler == null) {
			MagicTreasures.LOGGER.debug("equipmentHandler is null");
//			equipmentCharmHandler = new HotbarEquipmentCharmHandler();
		}
		MinecraftForge.EVENT_BUS.register(new SpellEventHandler(equipmentSpellHandler));
	}

	public static void registerTreasure2Integration() {
		if (ModList.get().isLoaded("treasure2")) {
			TreasureApi.registerLootTables(MagicTreasures.MOD_ID);
		}
	}
}
