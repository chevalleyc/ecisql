package com.ethercis.graphql.ehr;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class EhrAccessQLType extends EcisQLType {

    private final String id = I_RmObjectQLRegistry.EHR_ACCESS;

    protected EhrAccessQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("EHR-wide access contol object")
                .build();

        elementType = new QLTypeInterface(rmObjectType, elementType).withInterface(I_RmObjectQLRegistry.ITEM).getObjectType();

        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
