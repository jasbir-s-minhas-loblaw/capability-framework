package com.sdm.hw.common.capability;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * This enum representing Province :
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2017-11-07
 */

public enum Province {
    ALBERTA("AB"),
    BRITISH_COLUMBIA("BC"),
    MANITOBA("MB"),
    NEW_BRUNSWICK("NB"),
    NEWFOUNDLAND_AND_LABRADOR("NL"),
    NORTHWEST_TERRITORIES("NT"),
    NOVA_SCOTIA("NS"),
    NUNAVUT("NU"),
    ONTARIO("ON"),
    PRINCE_EDWARD_ISLAND("PE"),
    QUEBEC("QC"),
    SASKATCHEWAN("SK"),
    YUKON("YT");

    private static final Map<String, Province> NAME_MAP;

    static {
        NAME_MAP = new HashMap<String, Province>();
        for (Province province : EnumSet.allOf(Province.class)) {
            NAME_MAP.put(province.code, province);
        }
    }


    /**
     * a String representing a path to the capability
     */
    private final String code;

    /**
     * constructor for Enum
     *
     * @param provinceCode String representing the province code.
     */
    Province(final String provinceCode) {
        this.code = provinceCode;
    }

    public static Province getProvince(String provinceCode){
        return NAME_MAP.get(provinceCode);
    }

    public String getCode() {
        return code;
    }
    /**
     * @see Enum#toString()
     */
    @Override
    public String toString() {
        return code;
    }
}