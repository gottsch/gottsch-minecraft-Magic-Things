
package mod.gottsch.forge.magic_things.core.spell;

import mod.gottsch.forge.gottschcore.enums.IRarity;
import mod.gottsch.forge.gottschcore.spatial.ICoords;
import mod.gottsch.forge.magic_things.core.capability.IJewelryHandler;
import mod.gottsch.forge.magic_things.core.capability.MagicThingsCapabilities;
import mod.gottsch.forge.magic_things.core.util.LangUtil;
import mod.gottsch.forge.magic_things.core.util.MathUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

import java.util.Random;

/**
 * @author Mark Gottschling on June 4, 2024
 *
 */
public class StrengthSpell extends CooldownSpell {
	public static final String TYPE = "strength";
	private static final Class<?> REGISTERED_EVENT = LivingEvent.LivingUpdateEvent.class;

	// amount to amplify strength effect by
	private int amplifier = 0;

	/**
	 *
	 * @param builder
	 */
	StrengthSpell(Builder builder) {
		super(builder);
		this.amplifier = builder.amplifier;
	}

	@Override
	public Class<?> getRegisteredEvent() {
		return REGISTERED_EVENT;
	}

	/**
	 * NOTE: it is assumed that only the allowable events are calling this action.
	 */
	@Override
	public boolean execute(Level world, Random random, ICoords coords, Event event, ICastSpellContext context) {
		boolean result = false;

		IJewelryHandler handler = context.getJewelry().getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).orElseThrow(IllegalStateException::new);

		if (handler.getMana() > 0 && context.getPlayer().isAlive()) {
			if (!context.getPlayer().hasEffect(MobEffects.DAMAGE_BOOST)) {
				context.getPlayer().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, handler.modifyDuration(getDuration()), getAmplifier()));
			}
			applyCost(world, random, coords, context, handler.modifySpellCost(getSpellCost()));
       		result = true;
		}    		
		return result;
	}

	@Override
	public Component getSpellDesc() {
		return new TranslatableComponent(LangUtil.tooltip("spell.strength.rate"),
				MathUtil.r1d(getDuration() / 20.0),
				MathUtil.r1d(getCooldown() / 20.0));
	}

	@Override
	public Component getSpellDesc(ItemStack jewelry) {
		return new TranslatableComponent(LangUtil.tooltip("spell.strength.rate"),
				MathUtil.r1d(modifyCooldown(jewelry) / 20.0),
				MathUtil.r1d(modifyCooldown(jewelry) / 20.0));
	}

	@Override
	public ChatFormatting getSpellLabelColor() {
		return ChatFormatting.RED;
	}

	public int getAmplifier() {
		return amplifier;
	}

	public static class Builder extends Spell.Builder {
		public int amplifier;

		public Builder(ResourceLocation name, int level, IRarity rarity) {
			super(name, TYPE, level, rarity);
		}

		public StrengthSpell.Builder withAmplifier(int amplifier)  {
			this.amplifier = amplifier;
			return this;
		}

		@Override
		public Spell build() {
			return  new StrengthSpell(this);
		}
	}
}
