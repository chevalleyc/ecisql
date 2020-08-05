package com.ethercis.graphql.composition;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class InstructionQLType extends EcisQLType {

    public static final String NARRATIVE = "narrative";
    public static final String EXPIRY_TIME = "expiry_time";
    public static final String WF_DEFINITION = "wf_definition";
    public static final String ACTIVITIES = "activities";
    private final String id = I_RmObjectQLRegistry.INSTRUCTION;

    protected InstructionQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Entry type for evaluation statements")
                .field(
                        newFieldDefinition()
                                .name(NARRATIVE)
                                .description("Mandatory human-readable version of what the Instruction is about")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_TEXT)))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(EXPIRY_TIME)
                                .description("Optional expiry date/time to assist determination of when an Instruction can be assumed to have expired")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE_TIME))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(WF_DEFINITION)
                                .description("Optional workflow engine executable expression of the Instruction")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.DV_PARSABLE))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(ACTIVITIES)
                                .description("List of all activities in Instruction")
                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.ACTIVITY)))
//                                .dataFetcher(dataFetcher)
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
