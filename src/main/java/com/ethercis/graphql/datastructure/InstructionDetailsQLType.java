package com.ethercis.graphql.datastructure;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class InstructionDetailsQLType extends EcisQLType {

    public static final String INSTRUCTION_ID = "instruction_id";
    public static final String ACTIVITY_ID = "activity_id";
    public static final String WF_DETAILS = "wf_details";
    private final String id = I_RmObjectQLRegistry.INSTRUCTION_DETAILS;

    protected InstructionDetailsQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Used to record details of the Instruction causing an Action")
                .field(
                        newFieldDefinition()
                                .name(INSTRUCTION_ID)
                                .description("Reference to causing Instruction")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.LOCATABLE_REF)))

                )
                .field(
                        newFieldDefinition()
                                .name(ACTIVITY_ID)
                                .description("Identifier of Activity within Instruction, in the form of its archetype path")
                                .type(new GraphQLNonNull(GraphQLString))

                )
                .field(
                        newFieldDefinition()
                                .name(WF_DETAILS)
                                .description("Various workflow engine state details")
                                .type(rmObjectType.unionType(I_RmObjectQLRegistry.ITEM_STRUCTURE))

                )
                .build();

        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
