/*
 * This file is part of  Magic Things.
 * Copyright (c) 2024 Mark Gottschling (gottsch)
 *
 * Magic Things is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Magic Things is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Magic Things.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
package mod.gottsch.forge.magic_things.core.event;

import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.capability.IJewelryHandler;
import mod.gottsch.forge.magic_things.core.capability.MagicThingsCapabilities;
import mod.gottsch.forge.magic_things.core.item.*;
import mod.gottsch.forge.magic_things.core.item.generator.JewelryGenerator;
import mod.gottsch.forge.magic_things.core.registry.JewelryRegistry;
import mod.gottsch.forge.magic_things.core.registry.StoneRegistry;
import mod.gottsch.forge.magic_things.core.spell.ISpell;
import mod.gottsch.forge.magic_things.core.tag.MagicThingsTags;
import mod.gottsch.forge.magic_things.core.util.ModUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.lwjgl.system.CallbackI;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 
 * @author Mark Gottschling on May 5, 2024
 *
 */
public class AnvilEventHandler {
	@EventBusSubscriber(modid = MagicThings.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
	public static class RegistrationHandler {

		private static JewelryGenerator generator = new JewelryGenerator();

		/**
		 * NOTE event does NOT activate unless there is an item in both anvil slots.
		 */
		@SubscribeEvent
		public static void onAnvilUpdate(AnvilUpdateEvent event) {
			ItemStack leftStack = event.getLeft();
			ItemStack rightStack = event.getRight();

			AtomicReference<ItemStack> resultOutStack = new AtomicReference<>(ItemStack.EMPTY);
			ItemStack outStack = ItemStack.EMPTY;

			leftStack.getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).ifPresent(handler -> {
				event.setMaterialCost(1);
				event.setCost(1);

				// add a stone to jewelry
				if (!handler.hasStone() && rightStack.is(MagicThingsTags.Items.STONES)) {
					/*
					 TODO need a better way of constructing a new jewelry item than using its registry name.
					 When using items from different mods, following the naming structure may not work.
					 ie. this ONLY works for items that use Magic Things naming convention AND don't
					 overlap in the tiers. What if 2 items have the same make-up ex copper, topaz, ring
					 */

					// TODO somehow interrogate leftStack to determine if it is a standard naming or not. - might end up being a capability property OR a tag??
					// TODO addStone should return an Optional
					resultOutStack.set(generator.addStone(leftStack, rightStack));
				}
				// remove a stone from jewelry
				else if (handler.hasStone() && rightStack.getItem() == MagicThingsItems.JEWELRY_PLIERS.get()) {
					// TODO need to check if max level still supports any existing spells and possible remove them.
					// TODO removeStone should return an Optional
					resultOutStack.set(generator.removeStone(leftStack));
				}
				// add a spell to jewelry
				else if (handler.getSpells().isEmpty() && rightStack.is(MagicThingsTags.Items.SPELL_SCROLLS)) {
					Optional<ItemStack> resultStack = generator.addSpells(leftStack, rightStack);
					resultStack.ifPresent(resultOutStack::set);
				}
				// recharge jewelry
				else if (handler.hasStone() && handler.getRecharges() > 0 && rightStack.is(Items.AMETHYST_SHARD)) {
					Optional<ItemStack> resultStack = generator.recharge(leftStack);
					resultStack.ifPresent(resultOutStack::set);
				}
				// repair jewelry
				else if (handler.getRepairs() > 0 && rightStack.is(MagicThingsTags.Items.JEWELRY)) {
					Optional<ItemStack> resultStack = generator.repair(leftStack);
					resultStack.ifPresent(resultOutStack::set);
				}
			});
			event.setOutput(resultOutStack.get());
		}
	}
}
