package mod.gottsch.forge.magic_things.core.item;

import mod.gottsch.forge.magic_things.core.spell.ISpell;
import mod.gottsch.forge.magic_things.core.util.LangUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

/**
 * Created by Mark Gottschling on 5/04/2024
 */
public class SpellScroll extends Item {

    private ISpell spell;

    public SpellScroll(Properties properties, ISpell spell) {
        super(properties);
        this.spell = spell;
    }

    /**
     *
     */
    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(new TranslatableComponent(LangUtil.tooltip("spell_scroll.usage")).withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        tooltip.add(new TranslatableComponent(LangUtil.NEWLINE));

        // advanced tooltip (hold shift)
        LangUtil.appendAdvancedHoverText(tooltip, tt -> {
            tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("spell.name"), WordUtils.capitalizeFully(getSpell().getName().getPath()))));
            tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("spell.level"), getSpell().getLevel())));
            tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("spell.rarity"), getSpell().getRarity().getName())));
            tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("spell.cost"), getSpell().getSpellCost())));
            tooltip.add(new TranslatableComponent(LangUtil.INDENT2).append(new TranslatableComponent(LangUtil.tooltip("spell.effect_amount"), getSpell().getEffectAmount())));

            // TODO add conditional info ex frequency > 0
        });
    }

    public ISpell getSpell() {
        return spell;
    }

    public void setSpell(ISpell spell) {
        this.spell = spell;
    }
}
