package com.ethercis.graphql.composition;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class EventContextQLType extends EcisQLType {

    public static final String START_TIME = "start_time";
    public static final String END_TIME = "end_time";
    public static final String LOCATION = "location";
    public static final String SETTING = "setting";
    public static final String OTHER_CONTEXT = "other_context";
    public static final String HEALTH_CARE_FACILITY = "health_care_facility";
    public static final String PARTICIPATION = "participation";
    private final String id = I_RmObjectQLRegistry.EVENT_CONTEXT;

    protected EventContextQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }


    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Documents the context information of a healthcare event")
                .field(
                        newFieldDefinition()
                                .name(START_TIME)
                                .description("Start time of the clinical session")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE_TIME)))
//                                .dataFetcher(startTime)
                )
                .field(
                        newFieldDefinition()
                                .name(END_TIME)
                                .description("End time of the clinical session")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.DV_DATE_TIME))
//                                .dataFetcher(endTime)
                )
                .field(
                        newFieldDefinition()
                                .name(LOCATION)
                                .description("The actual location where the session occurred")
                                .type(GraphQLString)
//                                .dataFetcher(location)
                )
                .field(
                        newFieldDefinition()
                                .name(SETTING)
                                .description("The setting in which the clinical session took place")
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_CODED_TEXT)))
//                                .dataFetcher(setting)
                )
                .field(
                        newFieldDefinition()
                                .name(OTHER_CONTEXT)
                                .description("Other optional context which will be archetyped")
                                .type(rmObjectType.unionType(I_RmObjectQLRegistry.ITEM_STRUCTURE))
//                                .dataFetcher(otherContext)
                )
                .field(
                        newFieldDefinition()
                                .name(HEALTH_CARE_FACILITY)
                                .description("The health care facility under whose care the event took place")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.PARTY_IDENTIFIED))
//                                .dataFetcher(healthCareFacility)
                )
                .field(
                        newFieldDefinition()
                                .name(PARTICIPATION)
                                .description("Parties involved in the healthcare event")
                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.PARTICIPATION)))
//                                .dataFetcher(participation)
                )
                .build();
        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
