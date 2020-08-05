package com.ethercis.graphql.change_control;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class AuditDetailsQLType extends EcisQLType {

    public static final String SYSTEM_ID = "system_id";
    public static final String TIME_COMMITTED = "time_committed";
    public static final String CHANGE_TYPE = "change_type";
    public static final String DESCRIPTION = "description";
    public static final String COMMITTER = "committer";
    private final String id = I_RmObjectQLRegistry.AUDIT_DETAILS;

    protected AuditDetailsQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("The set of attributes required to document the committal of an information item to a repository")
                .field(
                        newFieldDefinition()
                                .name(SYSTEM_ID)
                                .description("Identity of the system where the change was committed")
                                .type(new GraphQLNonNull(GraphQLString))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(TIME_COMMITTED)
                                .description("Time of committal of the item")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE_TIME)))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(CHANGE_TYPE)
                                .description("Type of change")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_CODED_TEXT)))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(DESCRIPTION)
                                .description("Reason for committal")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.DV_TEXT))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(COMMITTER)
                                .description("Identity and optional reference into identity management service, of user who committed the item")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.PARTY_PROXY)))
//                                .dataFetcher(dataFetcher)
                )
                .build();

        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
