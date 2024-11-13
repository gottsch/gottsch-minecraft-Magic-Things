/*
 * This file is part of  Magic Treasures.
 * Copyright (c) 2024 Mark Gottschling (gottsch)
 *
 * Magic Treasures is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Magic Treasures is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Magic Treasures.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
package mod.gottsch.forge.magic_treasures.core.event;

import mod.gottsch.forge.magic_treasures.core.capability.MagicTreasuresCapabilities;
import mod.gottsch.forge.magic_treasures.core.spell.SpellContext;
import mod.gottsch.forge.magic_treasures.core.spell.SpellEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.Event;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class CuriosEquipmentSpellHandler implements IEquipmentSpellHandler {
	private static final String CURIOS_ID = "curios";
	private static final List<String> CURIOS_SLOTS = Arrays.asList("necklace", "bracelet", "ring",
//			"charm",
			"belt");

	@Override
	public List<SpellContext> handleEquipmentSpells(Event event, ServerPlayer player) {
		List<SpellContext> contexts = new ArrayList<>();

		///////////////////////////////////
		// Comment out when running DataGen until I figure out why it's not working with Curios
		///////////////////////////////////
		// check curio slots
		LazyOptional<ICuriosItemHandler> handler = CuriosApi.getCuriosHelper().getCuriosHandler(player);
		handler.ifPresent(itemHandler -> {
			// curios type names -> head, necklace, back, bracelet, hands, ring, belt, charm, feet
			CURIOS_SLOTS.forEach(slot -> {
				Optional<ICurioStacksHandler> stacksOptional = itemHandler.getStacksHandler(slot);
				stacksOptional.ifPresent(stacksHandler -> {
					ItemStack curiosStack = stacksHandler.getStacks().getStackInSlot(0);
					curiosStack.getCapability(MagicTreasuresCapabilities.JEWELRY_CAPABILITY).ifPresent(cap -> {

							AtomicInteger index = new AtomicInteger();
							// requires indexed for-loop
							for (int i = 0; i < cap.getSpells().size(); i++) {
								SpellEntity entity =  ((List<SpellEntity>)cap.getSpells()).get(i);
								if (!entity.getSpell().getRegisteredEvent().equals(event.getClass())) {
									//	Treasure.LOGGER.debug("charm type -> {} is not register for this event -> {}", entity.getCharm().getType(), event.getClass().getSimpleName());
									continue;
								}
								index.set(i);
								SpellContext curiosContext = new SpellContext.Builder().with($ -> {
									$.slotProviderId = CURIOS_ID;
									$.slot = slot;
									$.itemStack = curiosStack;
									$.capability = cap;
									$.index = index.get();
									$.entity = entity;
								}).build();
								contexts.add(curiosContext);
							}

					});
				});
			});
		});

		return contexts;
	}
}
