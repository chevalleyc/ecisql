package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;
import org.openehr.rm.datatypes.quantity.DvOrdinal;

import static graphql.Scalars.GraphQLInt;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/7/2017.
 */
public class DvOrdinalQLType extends EcisQLType {

    public static final String SYMBOL = "symbol";
    public static final String VALUE = "value";
    private final String id = I_RmObjectQLRegistry.DV_ORDINAL;

    private DvOrdinal dvOrdinal;

    public DvOrdinalQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("Models rankings and scores")
                .field(
                        newFieldDefinition()
                                .name(SYMBOL)
                                .description("Coded textual representation of this value in the enumeration")
//                                .dataFetcher(symbol)
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_CODED_TEXT)))
                )
                .field(
                        newFieldDefinition()
                                .name(VALUE)
                                .description("Value in ordered enumeration of values")
//                                .dataFetcher(value)
                                .type(new GraphQLNonNull(GraphQLInt))
                )
                .build();
        objectType = new QLTypeInterface(rmObjectType, objectType)
                .withInterface(rmObjectType.interfaceType(I_RmObjectQLRegistry.DV_AMOUNT))
                .withInterface(rmObjectType.interfaceType(I_RmObjectQLRegistry.DV_QUANTIFIED))
                .getObjectType();

        return objectType;
    }

    @Override
    public String id() {
        return id;
    }
}
