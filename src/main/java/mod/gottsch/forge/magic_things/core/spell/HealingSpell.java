
package mod.gottsch.forge.magic_things.core.spell;

import mod.gottsch.forge.gottschcore.enums.IRarity;
import mod.gottsch.forge.gottschcore.spatial.ICoords;
import mod.gottsch.forge.magic_things.core.capability.IJewelryHandler;
import mod.gottsch.forge.magic_things.core.capability.MagicThingsCapabilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;

import java.util.Random;

/**
 *
 */
public class HealingSpell extends Spell {
    public static String HEALING_TYPE = "healing";
    private static final Class<?> REGISTERED_EVENT = LivingEvent.LivingUpdateEvent.class;

    /**
     *
     * @param builder
     */
    public HealingSpell(Builder builder) {
        super(builder);
    }

    public static class Builder extends Spell.Builder {
        public Builder(ResourceLocation name, int level, IRarity rarity) {
            super(name, HEALING_TYPE, level, rarity);
        }

        @Override
        public ISpell build() {
            return new HealingSpell(this);
        }
    }

    @Override
    public Class<?> getRegisteredEvent() {
        return REGISTERED_EVENT;
    }

    /**
     * NOTE: it is assumed that only the allowable events are calling this action.
     */

    @Override
    public boolean serverUpdate(Level level, Random random, ICoords coords, Event event, ICastSpellContext context) {
        boolean result = false;
        IJewelryHandler handler = context.getJewelry().getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).orElseThrow(IllegalStateException::new);
        if (level.getGameTime() % modifyFrequency(context.getJewelry()) == 0) {
            if (handler.getMana() > 0 && context.getPlayer().getHealth() < context.getPlayer().getMaxHealth() && context.getPlayer().isAlive()) {

                // determine the actual amount of health (0.0 -> getAmount())
                float amount = Math.min((float)modifyEffectAmount(context.getJewelry()), context.getPlayer().getMaxHealth() - context.getPlayer().getHealth());
                context.getPlayer().setHealth(Mth.clamp(context.getPlayer().getHealth() + amount, 0.0F, context.getPlayer().getMaxHealth()));
                applyCost(level, random, coords, context, modifySpellCost(context.getJewelry()));
                result = true;
            }
        }
        return result;
    }

}
