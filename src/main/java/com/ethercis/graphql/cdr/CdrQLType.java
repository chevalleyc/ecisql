package com.ethercis.graphql.cdr;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class CdrQLType extends EcisQLType {

    public static final String EHR = "ehr";
    public static final String COMPOSITION = "composition";
    private final String id = I_RmObjectQLRegistry.CDR;

    protected CdrQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Root CDR entity")
                .field(
                        newFieldDefinition()
                                .name(EHR)
                                .description("The EHRs contained in the CDR")
                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.EHR)))
                                .argument(rmObjectType.argument(I_RmObjectQLRegistry.EHR_ID_ARGUMENT))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(COMPOSITION)
                                .description("Compositions contained in the CDR")
                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.COMPOSITION)))
                                .argument(rmObjectType.argument(I_RmObjectQLRegistry.COMPOSITION_ID_ARGUMENT))
//                                .dataFetcher(dataFetcher)
                )
//                .field(
//                        newFieldDefinition()
//                                .name("demographic")
//                                .description("Parties associated with the CDR")
//                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.CONTRIBUTION)))
////                                .dataFetcher(dataFetcher)
//                )
                .build();

        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
