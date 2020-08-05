package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLBigInteger;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/7/2017.
 */
public class DvCountQLType extends EcisQLType {

    public static final String MAGNITUDE = "magnitude";
    private final String id = I_RmObjectQLRegistry.DV_COUNT;

    protected DvCountQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("Countable quantities")
                .field(
                        newFieldDefinition()
                                .name(MAGNITUDE)
                                .description("Value of this amount")
                                .type(new GraphQLNonNull(GraphQLBigInteger))

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
