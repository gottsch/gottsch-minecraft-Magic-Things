
package mod.gottsch.forge.magic_things.core.spell;

import mod.gottsch.forge.magic_things.core.capability.IJewelryHandler;
import mod.gottsch.forge.magic_things.core.item.IJewelry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 
 * @author Mark Gottschling on May 3, 20024
 *
 */
public class SpellContext {
	private InteractionHand hand;
	private String slotProviderId;
	private String slot;
	private ItemStack itemStack;
	private IJewelryHandler capability;
	private int index;
	private SpellEntity entity;

	/**
	 *
	 * @param builder
	 */
	SpellContext(Builder builder) {
		this.hand = builder.hand;
		this.slotProviderId = builder.slotProviderId;
		this.slot = builder.slot;
		this.itemStack = builder.itemStack;
		this.capability = builder.capability;
		this.index = builder.index;
		this.entity = builder.entity;
	}

	public static Comparator<SpellContext> priorityComparator = new Comparator<SpellContext>() {
		@Override
		public int compare(SpellContext p1, SpellContext p2) {
			// use p1 < p2 because the sort should be ascending
			if (p1.getEntity().getSpell().getPriority() < p2.getEntity().getSpell().getPriority()) {
				// greater than
				return 1;
			} else {
				// less than
				return -1;
			}
		}
	};

	public InteractionHand getHand() {
		return hand;
	}

	public String getSlotProviderId() {
		return slotProviderId;
	}

	public String getSlot() {
		return slot;
	}

	public ItemStack getItemStack() {
		return itemStack;
	}

	public int getIndex() {
		return index;
	}

	public SpellEntity getEntity() {
		return entity;
	}

	public static class Builder {
		public InteractionHand hand;
		public String slotProviderId;
		public String slot;
		public ItemStack itemStack;
		public IJewelryHandler capability;
		public int index;
		public SpellEntity entity;

		public Builder with(Consumer<Builder> builder)  {
			builder.accept(this);
			return this;
		}

		public Builder withIndex(int index) {
			this.index = index;
			return this;
		}

		public SpellContext build() {
			return new SpellContext(this);
		}
	}

	public IJewelryHandler getCapability() {
		return capability;
	}
}
