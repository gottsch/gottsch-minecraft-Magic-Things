/*
 * This file is part of  GealdronCraft.
 * Copyright (c) 2023 Mark Gottschling (gottsch)
 *
 * GealdronCraft is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GealdronCraft is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GealdronCraft.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
package mod.gottsch.forge.gealdorcraft.core.registry.support;

import java.util.Objects;

import mod.gottsch.forge.gealdorcraft.core.item.*;

/**
 * 
 * @author Mark Gottschling on Jul 7, 2023
 *
 */
public class JewelryRegistryKey {
	private IJewelryMaterialTier material;
	private IJewelryStoneTier stone;
	private IJewelrySizeTier size;
	
	/**
	 * 
	 * @param material
	 */
	public JewelryRegistryKey(IJewelryMaterialTier material) {
		this.material = material;
		this.stone = JewelryStoneTier.NONE;
		this.size = JewelrySizeTier.REGULAR;
	}
	
	/**
	 * 
	 * @param material
	 * @param stone
	 * @param size
	 */
	public JewelryRegistryKey(IJewelryMaterialTier material, IJewelryStoneTier stone, IJewelrySizeTier size) {
		this.material = material;
		this.stone = stone;
		this.size = size;
	}
	
	public IJewelryMaterialTier getMaterial() {
		return material;
	}
	public void setMaterial(IJewelryMaterialTier material) {
		this.material = material;
	}
	public IJewelrySizeTier getSize() {
		return size;
	}
	public void setSize(IJewelrySizeTier size) {
		this.size = size;
	}
	public IJewelryStoneTier getStone() {
		return stone;
	}
	public void setStone(IJewelryStoneTier stone) {
		this.stone = stone;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(material, size, stone);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JewelryRegistryKey other = (JewelryRegistryKey) obj;
		return Objects.equals(material, other.material) && Objects.equals(size, other.size)
				&& Objects.equals(stone, other.stone);
	}

	@Override
	public String toString() {
		return "JewelryRegistryKey [material=" + material + ", stone=" + stone + ", size=" + size + "]";
	}
	
}