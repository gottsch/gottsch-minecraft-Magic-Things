package mod.gottsch.forge.magic_things.core.jewelry;

import mod.gottsch.forge.magic_things.core.capability.IJewelryHandler;
import mod.gottsch.forge.magic_things.core.capability.JewelryCapability;
import mod.gottsch.forge.magic_things.core.capability.MagicThingsCapabilities;
//import mod.gottsch.forge.magic_things.core.item.JewelryMaterialTier;

@Deprecated
public class JewelryStoneHandlers {
    public static final JewelryStoneHandler STANDARD = new JewelryStoneHandler()
            .setCanAffix(p -> true);

    // POC
    public static final JewelryStoneHandler IRON_ONLY = new JewelryStoneHandler()
            .setCanAffix(p -> {
                IJewelryHandler handler = p.getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).orElseThrow(IllegalStateException::new);
                return handler.getMaterial() == JewelryMaterials.IRON;
            });
}
