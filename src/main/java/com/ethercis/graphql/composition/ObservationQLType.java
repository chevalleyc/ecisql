package com.ethercis.graphql.composition;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class ObservationQLType extends EcisQLType {

    public static final String STATE = "state";
    public static final String DATA = "data";
    private final String id = I_RmObjectQLRegistry.OBSERVATION;

    protected ObservationQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Entry type for evaluation statements")
                .field(
                        newFieldDefinition()
                                .name(STATE)
                                .description("Optional recording of the state of subject of this observation during the observation process")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.HISTORY))

                )
                .field(
                        newFieldDefinition()
                                .name(DATA)
                                .description("The data of this observation, in the form of a history of values which may be of any complexity")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.HISTORY)))
                )
                .build();
        elementType = new QLTypeInterface(rmObjectType, elementType)
                .withInterface(I_RmObjectQLRegistry.CONTENT_ITEM)
                .withInterface(I_RmObjectQLRegistry.CARE_ENTRY)
                .getObjectType();

        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
