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
 * Created by Mark Gottschling on 6/1/2023
 */
public enum JewelryMaterialTier implements IJewelryMaterialTier {
    NONE(-1, "none", 0, IntegerRange.EMPTY, 0, IntegerRange.EMPTY, 0),
    WOOD(1, "wood", 1, new IntegerRange(30, 50), 0, new IntegerRange(10, 10), 2),
    IRON(2, "iron", 1, new IntegerRange(150, 300), 3, new IntegerRange(20, 30), 3),
    COPPER(3, "copper", 1, new IntegerRange(50, 100), 1, new IntegerRange(20, 30), 4),
    SILVER(4, "silver", 1, new IntegerRange(100, 200), 2, new IntegerRange(30, 40), 5),
    GOLD(5, "gold", 1, new IntegerRange(100, 200), 2, new IntegerRange(30, 40), 6),
    BLOOD(5, "blood", 1, new IntegerRange(200, 300), 2, new IntegerRange(60, 80), 7),
    BONE(6, "bone", 1, new IntegerRange(150, 300), 0, new IntegerRange(50, 70), 8),
    SHADOW(7, "shadow", 2, new IntegerRange(200, 350), 3, new IntegerRange(80, 100), 9),
    ATIUM(8, "atium", 2, new IntegerRange(300, 350), 3, new IntegerRange(100, 120), 10)
    ;
    // ...

    private static final Map<Integer, IEnum> codes = new HashMap<Integer, IEnum>();
    private static final Map<String, IEnum> values = new HashMap<String, IEnum>();
    private Integer code;
    private String value;

    private final int spells;
    private final IntegerRange uses;
    private final int repairs;
    private final IntegerRange mana;
    private final int maxLevel;

    // setup reverse lookup
    static {
        for (IJewelryMaterialTier x : EnumSet.allOf(JewelryMaterialTier.class)) {
            codes.put(x.getCode(), x);
            values.put(x.getValue(), x);
        }
    }

    /**
     * Full constructor
     * @param code
     * @param value
     */
    JewelryMaterialTier(Integer code, String value, int spells, IntegerRange uses, int repairs, IntegerRange mana, int maxLevel) {
        this.code = code;
        this.value = value;
        this.spells = spells;
        this.uses = uses;
        this.repairs = repairs;
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

    @Override
    public int getSpells() {
        return spells;
    }

    @Override
    public IntegerRange getUses() {
        return uses;
    }

    @Override
    public int getRepairs() {
        return repairs;
    }

    @Override
    public IntegerRange getMana() {
        return mana;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    /**
     *
     * @return
     */
    public static List<String> getNames() {
        List<String> names = EnumSet.allOf(JewelryMaterialTier.class).stream().map(x -> x.name()).collect(Collectors.toList());
        return names;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
