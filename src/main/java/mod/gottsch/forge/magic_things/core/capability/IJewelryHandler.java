package mod.gottsch.forge.magic_things.core.capability;

import java.util.List;

import mod.gottsch.forge.magic_things.core.item.IJewelryMaterialTier;
import mod.gottsch.forge.magic_things.core.item.IJewelrySizeTier;
import mod.gottsch.forge.magic_things.core.item.IJewelryStoneTier;
import mod.gottsch.forge.magic_things.core.item.IJewelryType;
import mod.gottsch.forge.magic_things.core.spell.SpellEntity;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

/**
 * Created by Mark Gottschling on 6/1/2023
 */
public interface IJewelryHandler {
    public IJewelryMaterialTier getJewelryMaterialTier();
    public List<IJewelryStoneTier> getJewelryStoneTiers();
    public IJewelrySizeTier getJewelrySizeTier();
    public IJewelryType getJewelryType();

    public Tag serializeNBT();
    public void deserializeNBT(Tag tag);

    public int getUses();
    public void setUses(int uses);

    public double getMaxMana();
    public void setMaxMana(double maxMana);

    public double getMana();
    public void setMana(double mana);

    public int getMaxRepairs();
    public void setMaxRepairs(int repairs);

    public int getRepairs();
    public void setRepairs(int repairs);

    public int getMaxSockets();
    public void setMaxSockets(int maxSockets);

    public int getSockets();
    public void setSockets(int sockets);

    public int getMaxLevel();
//    public void setMaxLevel(int maxLevel);
    
	List<ResourceLocation> getStones();
	void setStones(List<ResourceLocation> stones);

    List<SpellEntity> getSpells();

    void setSpells(List<SpellEntity> spells);

    void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag);

}
