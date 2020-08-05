package com.ethercis.graphql.generator;

/**
 * Created by christian on 4/13/2017.
 */
public enum ExemplifyMode {

    ANY_FIELD ((byte)0x00),
    UNDEFINED ((byte)0x01),
    REQUIRED_FIELDS_ONLY ((byte)0x02),
    SET_VALUES_ONLY ((byte)0x04);

    private final byte mode;

    ExemplifyMode(byte mode){
        this.mode = mode;
    }

}
