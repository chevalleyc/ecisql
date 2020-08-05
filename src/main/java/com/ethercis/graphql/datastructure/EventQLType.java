package com.ethercis.graphql.datastructure;

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
public class EventQLType extends EcisQLType {

    public static final String TIME = "time";
    public static final String STATE = "state";
    public static final String DATA = "data";

    private final String id = I_RmObjectQLRegistry.EVENT;

    protected EventQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Root object of a linear history, i.e. time series structure")
                .field(
                        newFieldDefinition()
                                .name(TIME)
                                .description("Time of this event")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE_TIME)))

                )
                .field(
                        newFieldDefinition()
                                .name(STATE)
                                .description("Optional state data for this event")
                                .type(rmObjectType.unionType(I_RmObjectQLRegistry.ITEM_STRUCTURE))

                )
                .field(
                        newFieldDefinition()
                                .name(DATA)
                                .description("The data of this event")
                                .type(new GraphQLNonNull(rmObjectType.unionType(I_RmObjectQLRegistry.ITEM_STRUCTURE)))
                )
                .build();
        elementType = new QLTypeInterface(rmObjectType, elementType)
                .withInterface(I_RmObjectQLRegistry.LOCATABLE)
                .getObjectType();

        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
