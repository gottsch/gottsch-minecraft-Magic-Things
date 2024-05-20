package mod.gottsch.forge.magic_things.core.jewelry;

import mod.gottsch.forge.magic_things.core.capability.IJewelryHandler;
import mod.gottsch.forge.magic_things.core.capability.MagicThingsCapabilities;

/**
 * @author Mark Gottschling May 9, 2024
 *
 */
public class JewelryStoneTiers {
    public static final JewelryStoneTier NONE = new JewelryStoneTier.Builder("none", 0, 0).build();
    public static final JewelryStoneTier TIER1 = new JewelryStoneTier.Builder("tier1", 25, 1).build();
    public static final JewelryStoneTier TIER2 = new JewelryStoneTier.Builder("tier2", 50, 1).build();
    public static final JewelryStoneTier TIER3 = new JewelryStoneTier.Builder("tier3", 75, 2).build();
    public static final JewelryStoneTier TIER4 = new JewelryStoneTier.Builder("tier4", 100, 2).build();
    public static final JewelryStoneTier TIER5 = new JewelryStoneTier.Builder("tier5", 125, 3).build();
    public static final JewelryStoneTier TIER6 = new JewelryStoneTier.Builder("tier6", 150, 4).build();

    // POC: Skeleton's Heart gem can only affix to Bone material.
    public static final JewelryStoneTier SKELETONS_HEART =
            new JewelryStoneTier.Builder("skeletons_heart", 100, 3)
                    .with($ -> {
                        $.canAffix = p -> {
                            IJewelryHandler jewelryHandler = p.getCapability(MagicThingsCapabilities.JEWELRY_CAPABILITY).orElseThrow(IllegalStateException::new);
                            return jewelryHandler.getMaterial().equals(JewelryMaterials.BONE);
                        };
                        $.spellCostFactor = 0.75;
                    }).build();
}
