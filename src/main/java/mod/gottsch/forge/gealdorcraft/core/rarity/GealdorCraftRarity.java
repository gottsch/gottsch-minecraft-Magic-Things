/*
 * This file is part of  GealdorCraft.
 * Copyright (c) 2018 Mark Gottschling (gottsch)
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
package mod.gottsch.forge.gealdorcraft.core.rarity;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import mod.gottsch.forge.gottschcore.enums.IRarity;
import mod.gottsch.forge.gottschcore.enums.IEnum;

/**
 * @author Mark Gottschling onJan 11, 2018
 *
 */
public enum GealdorCraftRarity implements IRarity {
	// NOTE NONE was only added to return as a value if no rarity was set
	// yet, ex. when the mod is first loading and rarities aren't loaded yet, but
	// methods like Item.appendHoverText will fail if the Item.getRarity() returns null.
	// NONE is NOT registered in the RarityRegistry.
	NONE(-1, "none"),
	COMMON(0, "common"),
	UNCOMMON(1, "uncommon"),
	SCARCE(2, "scarce"),
	RARE(3, "rare"),
	EPIC(4, "epic"),
	LEGENDARY(5, "legendary"),
	MYTHICAL(6, "mythical");
	
	private static final Map<Integer, IEnum> codes = new HashMap<Integer, IEnum>();
	private static final Map<String, IEnum> values = new HashMap<String, IEnum>();
	private Integer code;
	private String value;
	
	// setup reverse lookup
	static {
		for (GealdorCraftRarity type : EnumSet.allOf(GealdorCraftRarity.class)) {
			codes.put(type.getCode(), type);
			values.put(type.getValue(), type);
		}
	}

	
	/**
	 * 
	 * @param value
	 */
	GealdorCraftRarity(int code, String value) {
		this.code = code;
		this.value = value;
	}
	
	@Override
	public String getName() {
		return name();
	}
	
	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	public static GealdorCraftRarity getByCode(Integer code) {
		return (GealdorCraftRarity) codes.get(code);
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static GealdorCraftRarity getByValue(String value) {
		return (GealdorCraftRarity) values.get(value);
	}

	@Override
	public Map<Integer, IEnum> getCodes() {
		return codes;
	}
	@Override
	public Map<String, IEnum> getValues() {
		return values;
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<String> getNames() {
		List<String> names = EnumSet.allOf(GealdorCraftRarity.class).stream().map(x -> x.name()).collect(Collectors.toList());
		return names;
	}
}