package com.sdm.hw.common.capability;

/**
 * Constant class for Capability Framework.
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2018-01-29
 */

public class CapabilityConstant {
    //The regEx delimiter used in the expression
    public static final String EXPRESSION_DELIMITER = ".";

    /**
     * Utility classes, which are collections of static members, are not meant to be instantiated. Even
     * abstract utility classes, which can be extended, should not have public constructors.
     *
     * Java adds an implicit public constructor to every class which does not define at least one explicitly.
     * Hence, at least one non-public constructor should be defined.
     */
    private CapabilityConstant() {
    }
}
