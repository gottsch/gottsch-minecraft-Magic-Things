package mod.gottsch.forge.magic_treasures.core.spell;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface ICastSpellContext {
    ItemStack getJewelry();

    SpellEntity getEntity();

    List<ItemStack> getManaWells();

    Player getPlayer();
}
