package com.ethercis.graphql.datastructure;

import com.ethercis.graphql.commons.EcisQLType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by christian on 3/9/2017.
 */
public class ParticipationQLType extends EcisQLType {

    public static final String FUNCTION = "function";
    public static final String MODE = "mode";
    public static final String TIME = "time";
    public static final String PERFORMER = "performer";
    private final String id = I_RmObjectQLRegistry.PARTICIPATION;


    protected ParticipationQLType(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }


    @Override
    public GraphQLObjectType objectType() {
        GraphQLObjectType elementType = newObject()
                .name(id)
                .description("Model of a participation of a Party (any Actor or Role) in an activity")
                .field(
                        newFieldDefinition()
                                .name(FUNCTION)
                                .description("The function of the Party in this participation")
//                                .dataFetcher(function)
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.DV_TEXT)))
                )
                .field(
                        newFieldDefinition()
                                .name(MODE)
                                .description("Optional field for recording the 'mode' of the performer / activity interaction")
//                                .dataFetcher(mode)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.DV_CODED_TEXT))
                )
                .field(
                        newFieldDefinition()
                                .name(TIME)
                                .description("The time interval during which the participation took place")
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.DV_INTERVAL))
                )
                .field(
                        newFieldDefinition()
                                .name(PERFORMER)
                                .description("The id and possibly demographic system link of the party participating in the activity")
//                                .dataFetcher(performer)
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.PARTY_IDENTIFIED)))
                )
                .build();
        return elementType;
    }

    @Override
    public String id() {
        return id;
    }
}
