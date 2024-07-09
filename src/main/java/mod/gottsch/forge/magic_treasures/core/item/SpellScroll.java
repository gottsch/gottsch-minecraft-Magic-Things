package mod.gottsch.forge.magic_treasures.core.item;

import mod.gottsch.forge.magic_treasures.core.spell.ISpell;
import mod.gottsch.forge.magic_treasures.core.util.LangUtil;
import mod.gottsch.forge.magic_treasures.core.util.MathUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.text.WordUtils;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mark Gottschling on 5/04/2024
 */
public class SpellScroll extends Item {

    private ISpell spell;

    public SpellScroll(Properties properties, ISpell spell) {
        super(properties);
        this.spell = spell;
    }

    @Override
    public Component getName(ItemStack stack) {
        return ((MutableComponent)super.getName(stack)).withStyle(getNameColor());
    }

    public ChatFormatting getNameColor() {
        return ChatFormatting.BLUE;
    }

    /**
     *
     */
    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(new TranslatableComponent(LangUtil.tooltip("spell_scroll.usage")).withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        tooltip.add(new TranslatableComponent(LangUtil.NEWLINE));

        Optional.ofNullable(spell.getSpellDesc()).ifPresent(desc -> {
            tooltip.add(((MutableComponent)desc)
                    .withStyle(ChatFormatting.LIGHT_PURPLE)
                    .withStyle(ChatFormatting.ITALIC));
        });


        // advanced tooltip (hold shift)
        LangUtil.appendAdvancedHoverText(tooltip, tt -> {
            tooltip.add(new TranslatableComponent(LangUtil.NEWLINE));
            tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("divider")).withStyle(ChatFormatting.GRAY)));

            tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("spell.name"), new TranslatableComponent(LangUtil.tooltip("spell.name." + getSpell().getName().getPath())).withStyle(ChatFormatting.AQUA)))); //WordUtils.capitalizeFully(getSpell().getName().getPath().replace("_", " "))
            tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("spell.level"), ChatFormatting.GOLD + String.valueOf(getSpell().getLevel()))));
            tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("spell.rarity"), ChatFormatting.GOLD + getSpell().getRarity().getName())));
            if (spell.getSpellCost() <= 0) {
                tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("spell.cost.varies")).withStyle(ChatFormatting.BLUE)));
            } else {
                tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("spell.cost"), ChatFormatting.BLUE + MathUtil.r1d(getSpell().getSpellCost()))));
            }
            if (spell.getEffectAmount() > 0) {
                tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("spell.effect_amount"), ChatFormatting.RED + MathUtil.r1d(getSpell().getEffectAmount()))));
            }
            if (spell.getCooldown() > 0) {
                tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("spell.cooldown"), ChatFormatting.BLUE + MathUtil.r1d(getSpell().getCooldown()/20.0))));
            }
            if (spell.getFrequency() > 0) {
                tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("spell.frequency"), ChatFormatting.GREEN + MathUtil.r1d(getSpell().getFrequency()/20.0))));
            }
            if (spell.getRange() > 0) {
                tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("spell.range"), ChatFormatting.AQUA + MathUtil.r1d(getSpell().getRange()))));
            }
        });
    }

    public ISpell getSpell() {
        return spell;
    }

    public void setSpell(ISpell spell) {
        this.spell = spell;
    }
}
