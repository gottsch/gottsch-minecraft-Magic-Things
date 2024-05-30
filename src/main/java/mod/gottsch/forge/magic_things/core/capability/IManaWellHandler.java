package mod.gottsch.forge.magic_things.core.capability;

import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * Created by Mark Gottschling on 5/17/2024
 */
public interface IManaWellHandler {
    void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag);

    public Tag serializeNBT();
    public void deserializeNBT(Tag tag);

    double getMaxMana();

    void setMaxMana(double maxMana);

    double getMana();

    void setMana(double mana);

    int getRecharges();

    void setRecharges(int recharges);
}
