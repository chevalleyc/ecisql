package com.ethercis.graphql.composition;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class ActivityQLType extends EcisQLType {


    public static final String TIMING = "timing";
    public static final String ACTION_ARCHETYPE_ID = "action_archetype_id";
    public static final String DESCRIPTION = "description";
    private final String id = I_RmObjectQLRegistry.ACTIVITY;

    protected ActivityQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Defines a single activity within an Instruction, such as a medication administration")
                .field(
                        newFieldDefinition()
                                .name(TIMING)
                                .description("Timing of the activity, in the form of a parsable string, such as an HL7 GTS or ISO8601 string")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_PARSABLE)))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(ACTION_ARCHETYPE_ID)
                                .description("Perl-compliant regular expression pattern, enclosed in //' delimiters")
                                .type(GraphQLString)
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
                .getObjectType();

        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
