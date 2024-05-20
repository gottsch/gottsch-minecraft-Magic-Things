package mod.gottsch.forge.magic_things.core.event;

import mod.gottsch.forge.magic_things.core.capability.MagicThingsCapabilities;
import mod.gottsch.forge.magic_things.core.spell.SpellContext;
import mod.gottsch.forge.magic_things.core.spell.SpellEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Mark Gottschling on Aug 30, 2021
 *
 */
public class HotbarEquipmentSpellHandler implements IEquipmentSpellHandler {
	private static final int MAX_HOTBAR_JEWELRY = 4;

	@Override
	public List<SpellContext> handleEquipmentSpells(Event event, ServerPlayer player) {
		final List<SpellContext> contexts = new ArrayList<>(5);
		AtomicInteger jewelryCount = new AtomicInteger(0);
		AtomicReference<String> hotbarSlotStr = new AtomicReference<>("");
		// check hotbar - get the context at each slot
		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			hotbarSlotStr.set(String.valueOf(hotbarSlot));
			ItemStack inventoryStack = player.getInventory().getItem(hotbarSlot);
			if (inventoryStack != player.getItemInHand(InteractionHand.MAIN_HAND)) {
				inventoryStack.getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).ifPresent(cap -> {

					AtomicInteger index = new AtomicInteger();
					// requires indexed for-loop
					for (int i = 0; i < cap.getSpells().size(); i++) {
						SpellEntity entity =  ((List<SpellEntity>)cap.getSpells()).get(i);
						if (!entity.getSpell().getRegisteredEvent().equals(event.getClass())) {
							continue;
						}
						index.set(i);
						SpellContext context  = new SpellContext.Builder().with($ -> {
							$.slotProviderId = "minecraft";
							$.slot = hotbarSlotStr.get();
							$.itemStack = inventoryStack;
							$.capability = cap;
							$.index = index.get();
							$.entity = entity;
						}).build();
						contexts.add(context);
					}

				});
				jewelryCount.getAndIncrement();
				if (jewelryCount.get() >= MAX_HOTBAR_JEWELRY) {
					break;
				}

			}
		}
		return contexts;
	}
}