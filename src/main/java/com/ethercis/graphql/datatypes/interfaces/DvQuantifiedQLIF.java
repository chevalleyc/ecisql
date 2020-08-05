package com.ethercis.graphql.datatypes.interfaces;

import com.ethercis.graphql.commons.EcisQLInterfaceType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;
import graphql.schema.TypeResolver;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInterfaceType.newInterface;

/**
 * Created by christian on 3/7/2017.
 */
public class DvQuantifiedQLIF extends EcisQLInterfaceType {

    private final String id = I_RmObjectQLRegistry.DV_QUANTIFIED;
    public static final String NORMAL_STATUS = "normal_status";
    public static final String NORMAL_RANGE = "normal_range";
    public static final String OTHER_REFERENCE_RANGE = "other_reference_range";
    public static final String MAGNITUDE_STATUS = "magnitude_status";

    protected DvQuantifiedQLIF(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    //TODO: support interval!
    @Override
    public GraphQLInterfaceType interfaceType() {
        GraphQLInterfaceType  graphQLInterfaceType = newInterface()
                .name(id)
                .description("Abstract class defining the concept of true quantified values")
                .field(
                        newFieldDefinition()
                                .name(NORMAL_STATUS)
                                .description("Optional normal status indicator of value with respect to normal range for this value")
//                                .dataFetcher(unitFetcher)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE))
                )
                .field(
                        newFieldDefinition()
                                .name(NORMAL_RANGE)
                                .description("Optional normal range")
//                                .dataFetcher(unitFetcher)
                                .type(new GraphQLTypeReference(I_RmObjectQLRegistry.DV_INTERVAL))
                )
                .field(
                        newFieldDefinition()
                                .name(OTHER_REFERENCE_RANGE)
                                .description("Optional tagged other reference ranges for this value in its particular measurement context")
//                                .dataFetcher(unitFetcher)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.REFERENCE_RANGE))
                )
                .field(
                        newFieldDefinition()
                                .name(MAGNITUDE_STATUS)
                                .description("Optional status of magnitude")
//                                .dataFetcher(unitFetcher)
                                .type(GraphQLString)
                )
                //TODO: accuracy: DvAny
                .typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(Object o) {
//                        return new DvQuantifiedQLType(quantified).objectType();
                        return null;
                    }
                })
                .build();
        return graphQLInterfaceType;
    }

    @Override
    public String id() {
        return id;
    }
}
