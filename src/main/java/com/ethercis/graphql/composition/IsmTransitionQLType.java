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
public class IsmTransitionQLType extends EcisQLType {

    public static final String CURRENT_STATE = "current_state";
    public static final String TRANSITION = "transition";
    public static final String CAREFLOW_STEP = "careflow_step";
    private final String id = I_RmObjectQLRegistry.ISM_TRANSITION;

    protected IsmTransitionQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Model of a transition in the Instruction State Machine, caused by a careflow step")
                .field(
                        newFieldDefinition()
                                .name(CURRENT_STATE)
                                .description("The ISM current state")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_CODED_TEXT)))

                )
                .field(
                        newFieldDefinition()
                                .name(TRANSITION)
                                .description("The ISM transition which occurred to arrive in the current_state")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.DV_CODED_TEXT))

                )
                .field(
                        newFieldDefinition()
                                .name(CAREFLOW_STEP)
                                .description("The step in the careflow process which occurred as part of generating this action")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.DV_CODED_TEXT))

                )
                .build();

        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
