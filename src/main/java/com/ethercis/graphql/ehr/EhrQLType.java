package com.ethercis.graphql.ehr;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.QLTypeInterface;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class EhrQLType extends EcisQLType {

    public static final String SYSTEM_ID = "system_id";
    public static final String EHR_ID = "ehr_id";
    public static final String CONTRIBUTIONS = "contributions";
    public static final String EHR_STATUS = "ehr_status";
    public static final String EHR_ACCESS = "ehr_access";
    public static final String COMPOSITIONS = "compositions";
    public static final String DIRECTORY = "directory";
    public static final String TIME_CREATED = "time_created";

    private final String id = I_RmObjectQLRegistry.EHR;

    protected EhrQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("The EHR object is the root object and access point of an EHR for a subject of care")
                .field(
                        newFieldDefinition()
                                .name(SYSTEM_ID)
                                .description("The id of the EHR system on which this EHR was created")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.HIER_OBJECT_ID)))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(EHR_ID)
                                .description("The id of this EHR")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.HIER_OBJECT_ID)))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(CONTRIBUTIONS)
                                .description("List of contributions causing changes to this EHR.")
                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.CONTRIBUTION)))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(EHR_STATUS)
                                .description("Reference to EHR_STATUS object for this EHR")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.EHR_STATUS)))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(EHR_ACCESS)
                                .description("Reference to EHR_ACCESS object for this EHR.")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.EHR_ACCESS))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(COMPOSITIONS)
                                .description("Master list of all Versioned Composition references in this EHR.")
                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.COMPOSITION)))
                                .argument(rmObjectType.argument(I_RmObjectQLRegistry.COMPOSITION_ID_ARGUMENT))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(DIRECTORY)
                                .description("Optional directory structure for this EHR")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.OBJECT_REF))
//                                .dataFetcher(dataFetcher)
                )
                .field(
                        newFieldDefinition()
                                .name(TIME_CREATED)
                                .description("Time of creation of the EHR")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE_TIME)))
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
