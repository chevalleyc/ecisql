package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/7/2017.
 */
public class DvDurationQLType extends EcisQLType {

    public static final String VALUE = "value";
    private final String id = I_RmObjectQLRegistry.DV_DURATION;

    protected DvDurationQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("Represents a period of time with respect to a notional point in time")
                .field(
                        newFieldDefinition()
                                .name(VALUE)
                                .description("ISO8601 duration string")
//                                .dataFetcher(value)
                                .type(new GraphQLNonNull(GraphQLString))
                )
                .build();

        objectType = new QLTypeInterface(rmObjectType, objectType)
                .withInterface(I_RmObjectQLRegistry.DATE_TIME_STATICS)
                .withInterface(I_RmObjectQLRegistry.DV_AMOUNT)
                .withInterface(I_RmObjectQLRegistry.DV_TEMPORAL)
                .getObjectType();

        return objectType;
    }

    @Override
    public String id() {
        return id;
    }
}
