package com.ethercis.graphql.datatypes.interfaces;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/14/2017.
 */
public class DvQuantifiedQLType extends EcisQLType {

    private final String id = I_RmObjectQLRegistry.DV_QUANTIFIED;

    protected DvQuantifiedQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType objectType = newObject()
                .name(id)
                .description("Abstract class defining the concept of true quantified values")
                .field(
                        newFieldDefinition()
                                .name("normal_status")
                                .description("Optional normal status indicator of value with respect to normal range for this value")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE))
                )
                        //TODO: normal range: DvInterval
                        //TODO: reference range: DvInterval
                .field(
                        newFieldDefinition()
                                .name("magnitude_status")
                                .description("Optional status of magnitude")
                                .type(GraphQLString)
                )
                .withInterface(rmObjectType.interfaceType(I_RmObjectQLRegistry.DV_QUANTIFIED))
                .build();
        return objectType;
    }

    @Override
    public String id() {
        return id;
    }
}
