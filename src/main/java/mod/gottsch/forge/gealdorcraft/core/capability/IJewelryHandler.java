package mod.gottsch.forge.gealdorcraft.core.capability;

import java.util.List;

import mod.gottsch.forge.gealdorcraft.core.item.IJewelryMaterialTier;
import mod.gottsch.forge.gealdorcraft.core.item.IJewelrySizeTier;
import mod.gottsch.forge.gealdorcraft.core.item.IJewelryStoneTier;
import mod.gottsch.forge.gealdorcraft.core.item.IJewelryType;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;

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

    public int getMaxMana();
    public void setMaxMana(int maxMana);

    public int getMana();
    public void setMana(int mana);

    public int getMaxRepairs();
    public void setMaxRepairs(int repairs);

    public int getRepairs();
    public void setRepairs(int repairs);

    public int getMaxSockets();
    public void setMaxSockets(int maxSockets);

    public int getSockets();
    public void setSockets(int sockets);

    public int getMaxLevel();
    public void setMaxLevel(int maxLevel);
    
	List<ResourceLocation> getStones();
	void setStones(List<ResourceLocation> stones);
}
