package mod.gottsch.forge.magic_things.core.capability;

import net.minecraft.nbt.Tag;

/**
 * Created by Mark Gottschling on 5/17/2024
 */
public interface IManaWellHandler {
    public Tag serializeNBT();
    public void deserializeNBT(Tag tag);
}
