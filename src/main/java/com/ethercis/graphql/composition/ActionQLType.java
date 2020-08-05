package com.ethercis.graphql.composition;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class ActionQLType extends EcisQLType {

    public static final String TIME = "time";
    public static final String ISM_TRANSITION = "ism_transition";
    public static final String INSTRUCTION_DETAILS = "instruction_details";
    public static final String DESCRIPTION = "description";
    private final String id = I_RmObjectQLRegistry.ACTION;

    protected ActionQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Used to record a clinical action that has been performed")
                .field(
                        newFieldDefinition()
                                .name(TIME)
                                .description("Point in time at which this action completed")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE_TIME)))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(ISM_TRANSITION)
                                .description("Details of transition in the Instruction state machine caused by this Action")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.ISM_TRANSITION)))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(INSTRUCTION_DETAILS)
                                .description("Details to of the Instruction that caused this Action to be performed")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.INSTRUCTION_DETAILS))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(DESCRIPTION)
                                .description("Description of the activity, in the form of an archetyped structure")
                                .type(new GraphQLNonNull(rmObjectType.unionType(I_RmObjectQLRegistry.ITEM_STRUCTURE)))
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
