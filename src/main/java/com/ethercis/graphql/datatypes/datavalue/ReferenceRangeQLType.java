package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;

import static graphql.Scalars.GraphQLFloat;
import static graphql.Scalars.GraphQLInt;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/7/2017.
 */
public class ReferenceRangeQLType extends EcisQLType {

    public static final String MEANING = "meaning";
    public static final String RANGE = "range";
    private final String id = I_RmObjectQLRegistry.REFERENCE_RANGE;

    public ReferenceRangeQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("Models a ratio of values")
                .field(
                        newFieldDefinition()
                                .name(MEANING)
                                .description("Term whose value indicates the meaning of this range")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.DV_TEXT))
                )
                .field(
                        newFieldDefinition()
                                .name(RANGE)
                                .description("The data range for this meaning")
                                .type(new GraphQLTypeReference(I_RmObjectQLRegistry.DV_INTERVAL))
                )
                .build();

        return objectType;
    }

    @Override
    public String id() {
        return id;
    }
}
