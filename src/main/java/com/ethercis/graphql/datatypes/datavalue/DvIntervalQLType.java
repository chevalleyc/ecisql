package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.commons.FormattingString;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLBoolean;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/7/2017.
 */
public class DvIntervalQLType extends EcisQLType {

    public static final String LOWER = "lower";
    public static final String UPPER = "upper";
    public static final String LOWER_UNBOUNDED = "lower_unbounded";
    public static final String UPPER_UNBOUNDED = "upper_unbounded";
    public static final String LOWER_INCLUDED = "lower_included";
    public static final String UPPER_INCLUDED = "upper_included";
    private final String id = I_RmObjectQLRegistry.DV_INTERVAL;

    protected DvIntervalQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType graphQLObjectType = newObject()
                .name(id)
                .description("Abstract class defining the concept of true quantified values")
                .field(
                        newFieldDefinition()
                                .name(LOWER)
                                .description("Lower ordered value for this interval")
                                .type(rmObjectType.unionType(I_RmObjectQLRegistry.DV_ORDERED))
                )
                .field(
                        newFieldDefinition()
                                .name(UPPER)
                                .description("Upper ordered value for this interval")
                                .type(rmObjectType.unionType(I_RmObjectQLRegistry.DV_ORDERED))
                )
                .field(
                        newFieldDefinition()
                                .name(LOWER_UNBOUNDED)
                                .description("Is lower unbounded")
                                .type(new GraphQLNonNull(GraphQLBoolean))
                )
                .field(
                        newFieldDefinition()
                                .name(UPPER_UNBOUNDED)
                                .description("Is upper unbounded")
                                .type(new GraphQLNonNull(GraphQLBoolean))
                )
                .field(
                        newFieldDefinition()
                                .name(LOWER_INCLUDED)
                                .description("Is lower included")
                                .type(new GraphQLNonNull(GraphQLBoolean))
                )
                .field(
                        newFieldDefinition()
                                .name(UPPER_INCLUDED)
                                .description("Is upper included")
                                .type(new GraphQLNonNull(GraphQLBoolean))
                )
                .build();
        return graphQLObjectType;
    }

    @Override
    public String id() {
        return id;
    }
}
