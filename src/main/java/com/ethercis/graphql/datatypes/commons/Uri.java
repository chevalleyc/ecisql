package com.ethercis.graphql.datatypes.commons;

import com.ethercis.graphql.commons.interfaces.I_FieldDefinition;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLFieldDefinition;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;

/**
 * Created by christian on 3/7/2017.
 */
public class Uri implements I_FieldDefinition {

    private I_RmObjectQLRegistry rmObjectType;

    protected Uri(I_RmObjectQLRegistry rmObjectQLType) {
        this.rmObjectType = rmObjectQLType;
    }

    public GraphQLFieldDefinition fieldDefinition(){
        return newFieldDefinition()
                .name("hyperlink")
                .description("Optional link")
                .type(rmObjectType.objectType(I_RmObjectQLRegistry.DV_URI))
                .build();
    }
}
