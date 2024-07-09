
package mod.gottsch.forge.magic_treasures.core.event;

import mod.gottsch.forge.magic_treasures.core.spell.SpellContext;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.Event;

import java.util.List;

/**
 * 
 * @author Mark Gottschling on Aug 30, 2021
 *
 */
public interface IEquipmentSpellHandler {

	public List<SpellContext> handleEquipmentSpells(Event event, ServerPlayer player);
}
