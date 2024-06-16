package mod.gottsch.forge.magic_things.core.jewelry;

/**
 * @author Mark Gottschling May 8, 2024
 *
 */
// TODO setup each metal for cost, effect, duration, freq, range modifiers
public class JewelryMaterials {
    public static final JewelryMaterial NONE = new JewelryMaterial.Builder("none", 0, 0)
            .with($ -> {
                $.maxLevel = 0;
                $.acceptsAffixer = a -> false;
            }).build();

    public static final JewelryMaterial WOOD = new JewelryMaterial.Builder("wood", 40, 20)
            .with(
                    $ -> $.maxLevel = 2
            ).build();

    public static final JewelryMaterial IRON = new JewelryMaterial.Builder("iron", 200, 50)
            .with($ -> {
                $.repairs = 3;
                $.maxLevel = 3;
                $.spellDurationFactor = 1.25;
            }).build();

    public static final JewelryMaterial COPPER = new JewelryMaterial.Builder("copper", 75, 50)
            .with($ -> {
                $.repairs = 1;
                $.maxLevel = 4;
                $.spellCooldownFactor = 0.95;
                $.spellFrequencyFactor = 0.95;
            }).build();

    public static final JewelryMaterial SILVER = new JewelryMaterial.Builder("silver", 150, 75)
            .with($ -> {
                $.repairs = 2;
                $.maxLevel = 5;
                $.spellCostFactor = 0.95;
            }).build();

    public static final JewelryMaterial GOLD = new JewelryMaterial.Builder("gold", 150, 100)
            .with($ -> {
                $.repairs = 2;
                $.maxLevel = 6;
                $.spellCostFactor = 0.925;
                $.spellCooldownFactor = 0.96;
                $.spellFrequencyFactor = 0.96;
            }).build();

    public static final JewelryMaterial BONE = new JewelryMaterial.Builder("bone", 200, 50)
            .with($ -> {
                $.repairs = 0;
                $.maxLevel = 7;
                $.spellCostFactor = 1.05;
                $.spellEffectAmountFactor = 1.25;
            }).build();

    // aka hemalsteel
    public static final JewelryMaterial BLOOD = new JewelryMaterial.Builder("blood", 250, 100)
            .with($ -> {
                $.repairs = 2;
                $.maxLevel = 8;
                // TODO
            }).build();

    public static final JewelryMaterial SHADOW = new JewelryMaterial.Builder("shadow", 275, 125)
            .with($ -> {
                $.repairs = 3;
                $.maxLevel = 9;
                $.spells = 2;
                $.spellCostFactor = 1.1; // NOTE costs more!
                $.spellEffectAmountFactor = 1.5;
            }).build();

    public static final JewelryMaterial ATIUM = new JewelryMaterial.Builder("atium", 325, 200)
            .with($ -> {
                $.repairs = 3;
                $.maxLevel = 10;
                $.spells = 2;
                $.spellCostFactor = 0.9;
                $.spellCooldownFactor = 0.9;
                $.spellDurationFactor = 1.5;
                $.spellEffectAmountFactor = 1.12;
                $.spellFrequencyFactor = 0.9;
                $.spellRangeFactor = 1.1;
            }).build();
}
