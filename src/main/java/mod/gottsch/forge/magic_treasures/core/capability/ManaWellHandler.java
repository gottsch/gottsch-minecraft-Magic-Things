package mod.gottsch.forge.magic_treasures.core.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;

/**
 * Created by Mark Gottschling on 5/17/2024
 */
public class ManaWellHandler implements IManaWellHandler, INBTSerializable<Tag> {
    private static final String MAX_MANA = "maxMana";
    private static final String MANA = "mana";
     private static final String RECHARGES = "recharges";


    private double maxMana;
    private double mana;
    private int recharges;

    public ManaWellHandler(double mana, int recharges) {
        this.mana = mana;
        this.recharges = recharges;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {

    }

    @Override
    public Tag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putDouble(MAX_MANA, getMaxMana());
        tag.putDouble(MANA, getMana());
        tag.putInt(RECHARGES, getRecharges());
        return tag;
    }

    @Override
    public void deserializeNBT(Tag tag) {
        if (tag instanceof CompoundTag compound) {
            if (compound.contains(MAX_MANA)) {
                this.maxMana = compound.getInt(MAX_MANA);
            }
            if (compound.contains(MANA)) {
                this.mana = compound.getInt(MANA);
            }
            if (compound.contains(RECHARGES)) {
                this.recharges = compound.getInt(RECHARGES);
            }

        }
    }

    @Override
    public double getMaxMana() {
        return maxMana;
    }

    @Override
    public void setMaxMana(double maxMana) {
        this.maxMana = maxMana;
    }

    @Override
    public double getMana() {
        return mana;
    }

    @Override
    public void setMana(double mana) {
        this.mana = mana;
    }

    @Override
    public int getRecharges() {
        return recharges;
    }

    @Override
    public void setRecharges(int recharges) {
        this.recharges = recharges;
    }
}
