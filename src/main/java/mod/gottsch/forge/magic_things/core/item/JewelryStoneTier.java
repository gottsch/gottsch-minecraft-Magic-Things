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
package mod.gottsch.forge.magic_things.core.item;

import mod.gottsch.forge.magic_things.core.size.IntegerRange;
import mod.gottsch.forge.gottschcore.enums.IEnum;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Mark Gottschling on 5/31/2023
 */
public enum JewelryStoneTier implements IJewelryStoneTier {
    NONE(-1, "none", IntegerRange.EMPTY, 0),
    TIER1(1, "tier1", new IntegerRange(30, 40), 2),
    TIER2(2, "tier2", new IntegerRange(40, 60), 3),
    TIER3(3, "tier3", new IntegerRange(50, 80), 4),
    TIER4(4, "tier4", new IntegerRange(50, 80), 6),
    TIER5(5, "tier5", new IntegerRange(70, 100), 8),
    TIER6(6, "tier6", new IntegerRange(90, 120), 10)
    ;
    private static final Map<Integer, IEnum> codes = new HashMap<Integer, IEnum>();
    private static final Map<String, IEnum> values = new HashMap<String, IEnum>();
    private Integer code;
    private String value;

    private IntegerRange mana;
    @Deprecated
    private int maxLevel;

    // setup reverse lookup
    static {
        for (IJewelryStoneTier x : EnumSet.allOf(JewelryStoneTier.class)) {
            codes.put(x.getCode(), x);
            values.put(x.getValue(), x);
        }
    }

    /**
     * Full constructor
     * @param code
     * @param value
     */
    JewelryStoneTier(Integer code, String value, IntegerRange mana, int maxLevel) {
        this.code = code;
        this.value = value;
        this.mana = mana;
        this.maxLevel = maxLevel;
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
    public static IJewelrySizeTier getByCode(Integer code) {
        return (JewelrySizeTier) codes.get(code);
    }
    /**
     *
     * @param value
     * @return
     */
    public static IJewelrySizeTier getByValue(String value) {
        return (JewelrySizeTier) values.get(value);
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
        List<String> names = EnumSet.allOf(JewelryStoneTier.class).stream().map(x -> x.name()).collect(Collectors.toList());
        return names;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    @Override
	public IntegerRange getMana() {
		return mana;
	}
    @Deprecated
    @Override
	public int getMaxLevel() {
		return maxLevel;
	}
}
