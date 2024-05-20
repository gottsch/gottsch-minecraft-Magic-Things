
package mod.gottsch.forge.magic_things.core.spell;

import mod.gottsch.forge.gottschcore.enums.IRarity;
import mod.gottsch.forge.gottschcore.spatial.ICoords;
import mod.gottsch.forge.magic_things.core.capability.IJewelryHandler;
import mod.gottsch.forge.magic_things.core.capability.MagicThingsCapabilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.Event;

import java.util.Random;

/**
 * 
 * @author Mark Gottschling on Sep 25, 2022
 *
 */
public class MagicResistanceSpell extends Spell {
	public static final String MAGIC_RESISTENCE_TYPE = "magic_resistance";
	private static final Class<?> REGISTERED_EVENT = LivingDamageEvent.class;

	/**
	 *
	 * @param builder
	 */
	MagicResistanceSpell(Builder builder) {
		super(builder);
	}

	@Override
	public Class<?> getRegisteredEvent() {
		return REGISTERED_EVENT;
	}

	/**
	 * NOTE: it is assumed that only the allowable events are calling this action.
	 */
	@Override
	public boolean serverUpdate(Level world, Random random, ICoords coords, Event event, ICastSpellContext context) {
		boolean result = false;

		// exit if not fire damage
		if (((LivingDamageEvent)event).getSource().isMagic() && context.getPlayer().hasEffect(MobEffects.POISON)) {
			IJewelryHandler handler = context.getJewelry().getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).orElseThrow(IllegalStateException::new);

			if (handler.getMana() > 0 && context.getPlayer().isAlive()) {
				// get the source and amount
				double amount = ((LivingDamageEvent)event).getAmount();
				// calculate the new amount
				double newAmount = 0;
				double amountToSpell = amount * modifyEffectAmount(context.getJewelry());
				double amountToPlayer = amount - amountToSpell;
				// Treasure.logger.debug("amount to charm -> {}); amount to player -> {}", amountToCharm, amountToPlayer);
				double cost = applyCost(world, random, coords, context, amountToSpell);
				if (cost < amountToSpell) {
					newAmount =+ (amountToSpell - cost);
				}
				else {
					newAmount = amountToPlayer;
				}
				((LivingDamageEvent)event).setAmount((float) newAmount);
				result = true;
			}    
		}
		return result;
	}

//	@Override
//	public Component getCharmDesc(SpellEntity entity) {
//		return new TranslatableComponent("tooltip.charm.rate.poison_resistance", Math.toIntExact(Math.round(entity.getAmount() * 100)));
//	}

	public static class Builder extends Spell.Builder {

		public Builder(ResourceLocation name, int level, IRarity rarity) {
			super(name, MAGIC_RESISTENCE_TYPE, level, rarity);
		}

		@Override
		public Spell build() {
			return  new MagicResistanceSpell(this);
		}
	}
}
