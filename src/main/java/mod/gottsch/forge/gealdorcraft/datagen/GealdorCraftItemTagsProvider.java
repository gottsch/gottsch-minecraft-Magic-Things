/*
 * This file is part of  GealdorCraft.
 * Copyright (c) 2023 Mark Gottschling (gottsch)
 *
 * GealdorCraft is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GealdorCraft is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GealdorCraft.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
package mod.gottsch.forge.gealdorcraft.datagen;

import mod.gottsch.forge.gealdorcraft.core.GealdorCraft;
import mod.gottsch.forge.gealdorcraft.core.item.GealdorCraftItems;
import mod.gottsch.forge.gealdorcraft.core.tag.GealdorCraftTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * Created by Mark Gottschling on 5/29/2023
 */
public class GealdorCraftItemTagsProvider extends ItemTagsProvider {
	/**
	 * 
	 * @param dataGenerator
	 * @param blockTagProvider
	 * @param existingFileHelper
	 */
	public GealdorCraftItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(dataGenerator, blockTagProvider, GealdorCraft.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {     
		// keys rarity
		tag(GealdorCraftTags.Items.RINGS).add(GealdorCraftItems.COPPER_RING.get());


		// NOTE wither lock is a special and isn't used in the general locks list
		// tag(GealdorCraftTags.Items.SCARCE_LOCKS).add(GealdorItems.WITHER_LOCK.get());
		
		
		//        List<Adornment> adornments = GealdorAdornmentRegistry.getByType(AdornmentType.RING);
		//        adornments.forEach(ring -> {
		//        	tag(GealdorCraftTags.Items.RING).add(ring);
		//        });
		//        
		//        adornments = GealdorAdornmentRegistry.getByType(AdornmentType.NECKLACE);
		//        adornments.forEach(necklace -> {
		//        	tag(GealdorCraftTags.Items.NECKLACE).add(necklace);
		//        });
		//        
		//        adornments = GealdorAdornmentRegistry.getByType(AdornmentType.BRACELET);
		//        adornments.forEach(bracelet -> {
		//        	tag(GealdorCraftTags.Items.BRACELET).add(bracelet);
		//        });
		//        
		//        // special adornments
		//        tag(GealdorCraftTags.Items.RING).add(GealdorItems.ANGELS_RING.get());
		//        tag(GealdorCraftTags.Items.RING).add(GealdorItems.RING_OF_FORTITUDE.get());
		//        tag(GealdorCraftTags.Items.RING).add(GealdorItems.SHADOWS_GIFT.get());
		//        tag(GealdorCraftTags.Items.RING).add(GealdorItems.RING_OF_LIFE_DEATH.get());
		//        tag(GealdorCraftTags.Items.RING).add(GealdorItems.CASTLE_RING.get());
		//        tag(GealdorCraftTags.Items.RING).add(GealdorItems.PEASANTS_FORTUNE.get());
		//        tag(GealdorCraftTags.Items.RING).add(GealdorItems.GOTTSCHS_RING_OF_MOON.get());
		//        tag(GealdorCraftTags.Items.NECKLACE).add(GealdorItems.GOTTSCHS_AMULET_OF_HEAVENS.get());
		//        tag(GealdorCraftTags.Items.BRACELET).add(GealdorItems.BRACELET_OF_WONDER.get());
		//        tag(GealdorCraftTags.Items.NECKLACE).add(GealdorItems.MEDICS_TOKEN.get());
		//        tag(GealdorCraftTags.Items.BRACELET).add(GealdorItems.ADEPHAGIAS_BOUNTY.get());
		//        tag(GealdorCraftTags.Items.NECKLACE).add(GealdorItems.SALANDAARS_WARD.get());
		//        tag(GealdorCraftTags.Items.BRACELET).add(GealdorItems.MIRTHAS_TORCH.get());

		// pocket watch
		//        tag(GealdorCraftTags.Items.CHARM).add(GealdorItems.POCKET_WATCH.get());

	}
}
