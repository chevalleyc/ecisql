package com.ethercis.graphql.composition.interfaces;

import com.ethercis.graphql.commons.EcisQLInterfaceType;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import graphql.schema.*;
import org.openehr.rm.composition.content.entry.*;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInterfaceType.newInterface;

/**
 * Created by christian on 3/13/2017.
 */
public class CareEntryQLIF extends EcisQLInterfaceType {
    public static final String LANGUAGE = "language";
    public static final String ENCODING = "encoding";
    public static final String OTHER_PARTICIPATION = "other_participation";
    public static final String WORKFLOW_ID = "workflow_id";
    public static final String SUBJECT = "subject";
    public static final String PROVIDER = "provider";
    public static final String PROTOCOL = "protocol";
    public static final String GUIDELINE_ID = "guideline_id";
    private final String id = I_RmObjectQLRegistry.CARE_ENTRY;

    protected CareEntryQLIF(I_RmObjectQLRegistry rmObjectQLType) {
        super(rmObjectQLType);
    }

    //TODO: support interval!
    @Override
    public GraphQLInterfaceType interfaceType() {
        GraphQLInterfaceType graphQLInterfaceType = newInterface()
                .name(id)
                .description("The abstract parent of all ENTRY subtypes")
                .field(
                        newFieldDefinition()
                                .name(LANGUAGE)
                                .description("Mandatory indicator of the localised language in which this Entry is written")
//                                .dataFetcher(language)
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE)))
                )
                .field(
                        newFieldDefinition()
                                .name(ENCODING)
                                .description("Name of character set in which text values in this Entry are encoded")
//                                .dataFetcher(encoding)
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.CODE_PHRASE)))
                )
                .field(
                        newFieldDefinition()
                                .name(OTHER_PARTICIPATION)
                                .description("Other participations at ENTRY level")
//                                .dataFetcher(otherParticipation)
                                .type(new GraphQLList(rmObjectType.objectType(I_RmObjectQLRegistry.PARTICIPATION)))
                )
                .field(
                        newFieldDefinition()
                                .name(WORKFLOW_ID)
                                .description("dentifier of externally held workflow engine data for this workflow execution")
//                                .dataFetcher(workflowId)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.OBJECT_REF))
                )
                .field(
                        newFieldDefinition()
                                .name(SUBJECT)
                                .description("Id of human subject of this ENTRY")
//                                .dataFetcher(subject)
                                .type(new GraphQLNonNull(rmObjectType.objectType(I_RmObjectQLRegistry.PARTY_PROXY)))
                )
                .field(
                        newFieldDefinition()
                                .name(PROVIDER)
                                .description("Optional identification of provider of the information in this ENTRY")
//                                .dataFetcher(provider)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.PARTY_PROXY))
                )
                .field(
                        newFieldDefinition()
                                .name(PROTOCOL)
                                .description("Description of the method (i.e. how) the information in this entry was arrived at")
//                                .dataFetcher(protocol)
                                .type(rmObjectType.unionType(I_RmObjectQLRegistry.ITEM_STRUCTURE))
                )
                .field(
                        newFieldDefinition()
                                .name(GUIDELINE_ID)
                                .description("Optional external identifier of guideline creating this Entry if relevant")
//                                .dataFetcher(guidelineId)
                                .type(rmObjectType.objectType(I_RmObjectQLRegistry.OBJECT_REF))
                )
                .typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(Object source) {
                        if (source instanceof Action)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.ACTION);
                        else if (source instanceof Activity)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.ACTIVITY);
                        else if (source instanceof AdminEntry)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.ADMIN_ENTRY);
                        else if (source instanceof Evaluation)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.EVALUATION);
                        else if (source instanceof Instruction)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.INSTRUCTION);
                        else if (source instanceof Evaluation)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.INSTRUCTION_DETAILS);
                        else if (source instanceof ISMTransition)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.ISM_TRANSITION);
                        else if (source instanceof Observation)
                            return rmObjectType.objectType(I_RmObjectQLRegistry.OBSERVATION);
                        else
                            throw new IllegalArgumentException("Unhandled type:" + source.getClass());
                    }
                })
                .build();
        return graphQLInterfaceType;
    }

    @Override
    public String id() {
        return id;
    }
}
