
package mod.gottsch.forge.magic_treasures.core.capability;

import mod.gottsch.forge.magic_treasures.MagicTreasures;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Mark Gottschling on 5/17/2024
 */
public class ManaWellCapability implements ICapabilitySerializable<CompoundTag> {
        public static final ResourceLocation ID = new ResourceLocation(MagicTreasures.MOD_ID, "mana_well");

    // reference of handler/data for easy access
    private final IManaWellHandler handler;
    // holder of the handler/data
    private final LazyOptional<IManaWellHandler> optional;

      public ManaWellCapability(IManaWellHandler handler) {
        this.handler = handler;
        optional = LazyOptional.of(() -> handler);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == MagicTreasuresCapabilities.MANA_WELL_CAPABILITY) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return (CompoundTag)handler.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        handler.deserializeNBT(tag);
    }

    /**
     *
     * @param event
     */
    public static void register(RegisterCapabilitiesEvent event) {
        event.register(IJewelryHandler.class);
    }
}
