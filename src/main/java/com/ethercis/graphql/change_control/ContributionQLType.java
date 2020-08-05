package com.ethercis.graphql.change_control;

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
public class ContributionQLType extends EcisQLType {

    public static final String UID = "uid";
    public static final String VERSIONS = "versions";
    public static final String AUDIT = "audit";
    private final String id = I_RmObjectQLRegistry.CONTRIBUTION;

    protected ContributionQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Documents a Contribution (change set) of one or more versions added to a change-controlled repository")
                .field(
                        newFieldDefinition()
                                .name(UID)
                                .description("Unique identifier for this Contribution")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.HIER_OBJECT_ID)))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(VERSIONS)
                                .description("Set of references to versions causing changes to this EHR")
                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.OBJECT_REF)))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(AUDIT)
                                .description("Audit trail corresponding to the committal of this Contribution")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.AUDIT_DETAILS)))
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
