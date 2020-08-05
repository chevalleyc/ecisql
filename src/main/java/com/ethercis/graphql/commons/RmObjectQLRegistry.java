package com.ethercis.graphql.commons;

import com.ethercis.graphql.cdr.ecis_rm_fetcher.CdrGetEcisRM;
import com.ethercis.graphql.change_control.ecis_rm_fetcher.AuditDetailsGetEcisRM;
import com.ethercis.graphql.change_control.ecis_rm_fetcher.ContributionGetEcisRM;
import com.ethercis.graphql.commons.interfaces.I_RmObjectQLRegistry;
import com.ethercis.graphql.composition.ecis_rm_getter.*;
import com.ethercis.graphql.composition.interfaces.ecis_rm_getter.CareEntryIFGetEcisRM;
import com.ethercis.graphql.composition.interfaces.ecis_rm_getter.ContentItemIFGetEcisRM;
import com.ethercis.graphql.composition.interfaces.ecis_rm_getter.EntryIFGetEcisRM;
import com.ethercis.graphql.datastructure.*;
import com.ethercis.graphql.datastructure.ItemQLType;
import com.ethercis.graphql.datastructure.ItemStructureQLType;
import com.ethercis.graphql.datastructure.arguments.ArchetypeNodeIdArgument;
import com.ethercis.graphql.datastructure.arguments.CompositionIdArgument;
import com.ethercis.graphql.datastructure.arguments.NodeNameArgument;
import com.ethercis.graphql.datastructure.ecis_rm_getter.*;
import com.ethercis.graphql.datastructure.interfaces.ecis_rm_getter.*;
import com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter.*;
import com.ethercis.graphql.datatypes.datavalue.*;
import com.ethercis.graphql.datatypes.interfaces.ecis_rm_getter.*;
import com.ethercis.graphql.ehr.ecis_rm_getter.EhrAccessGetEcisRM;
import com.ethercis.graphql.ehr.ecis_rm_getter.EhrGetEcisRM;
import com.ethercis.graphql.ehr.ecis_rm_getter.EhrStatusGetEcisRM;
import com.ethercis.graphql.support.identification.ecis_rm_getter.*;
import graphql.schema.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by christian on 3/17/2017.
 */
public class RmObjectQLRegistry implements I_RmObjectQLRegistry {

    private final Map<String, GraphQLInterfaceType> interfaceTypeMap = new HashMap<>();
    private final Map<String, GraphQLObjectType> objectTypeMap = new HashMap<>();
    private final Map<String, GraphQLUnionType> unionTypeMap = new HashMap<>();
    private final Map<String, GraphQLArgument> argumentMap = new HashMap<>();

    public RmObjectQLRegistry() {
        //initialize the register with reusable

        argumentMap.put(ARCHETYPE_NODE_ID_ARGUMENT, new ArchetypeNodeIdArgument(this).argument());
        argumentMap.put(NODE_NAME_ARGUMENT, new NodeNameArgument(this).argument());
        argumentMap.put(COMPOSITION_ID_ARGUMENT, new CompositionIdArgument(this).argument());
        argumentMap.put(EHR_ID_ARGUMENT, new CompositionIdArgument(this).argument());

        interfaceTypeMap.put(DV_AMOUNT, new DvAmountIFGetEcisRM(this).interfaceType());

        objectTypeMap.put(TERMINOLOGY_ID, new TerminologyIdGetEcisRM(this).objectType());
        objectTypeMap.put(CODE_PHRASE, new CodePhraseGetEcisRM(this).objectType());
        objectTypeMap.put(TERM_MAPPING, new TermMappingGetEcisRM(this).objectType());

        objectTypeMap.put(DV_URI, new DvUriGetEcisRM(this).objectType());
        objectTypeMap.put(DV_MULTIMEDIA, new DvMultiMediaGetEcisRM(this).objectType());

        objectTypeMap.put(DV_TEXT, new DvTextGetEcisRM(this).objectType());
        objectTypeMap.put(DV_CODED_TEXT, new DvCodedTextGetEcisRM(this).objectType());

        unionTypeMap.put(TEXT_DATA_VALUE, new TextDataValueQLType(this).unionType());

        interfaceTypeMap.put(UID_BASE_ID, new UidBaseIdIFGetEcisRM(this).interfaceType());
        objectTypeMap.put(UID_BASE_ID, new UidBaseIdGetEcisRM(this).objectType());
        objectTypeMap.put(UID, new UidGetEcisRM(this).objectType());

        objectTypeMap.put(LINK, new LinkGetEcisRM(this).objectType());
        interfaceTypeMap.put(LINK, new LinkIFGetEcisRM(this).interfaceType());


        objectTypeMap.put(ARCHETYPE_ID, new ArchetypeIdGetEcisRM(this).objectType());
        objectTypeMap.put(TEMPLATE_ID, new TemplateIdGetEcisRM(this).objectType());
        objectTypeMap.put(ARCHETYPED, new ArchetypedGetEcisRM(this).objectType());

        interfaceTypeMap.put(ITEM, new ItemIFGetEcisRM(this).interfaceType());

        objectTypeMap.put(PARTY_REF, new PartyRefGetEcisRM(this).objectType());
        objectTypeMap.put(PARTY_PROXY, new PartyProxyGetEcisRM(this).objectType());


        objectTypeMap.put(OBJECT_REF, new ObjectRefGetEcisRM(this).objectType());

        interfaceTypeMap.put(ITEM_STRUCTURE, new ItemStructureIFGetEcisRM(this).interfaceType());

        objectTypeMap.put(REFERENCE_RANGE, new ReferenceRangeGetEcisRM(this).objectType());
        interfaceTypeMap.put(DV_QUANTIFIED, new DvQuantifiedIFGetEcisRM(this).interfaceType());

//        objectTypeMap.put(DV_AMOUNT, new DvAmountQLType(this).objectType());
        objectTypeMap.put(DATE_TIME_STATICS, new DvDateTimeStaticsGetEcisRM(this).objectType());
//        objectTypeMap.put(DV_QUANTIFIED, new DvQuantifiedQLType(this).objectType());

        interfaceTypeMap.put(DV_TEMPORAL, new DvTemporalIFGetEcisRM(this).interfaceType());
//        objectTypeMap.put(DV_TEMPORAL, new DvTemporalQLType(this).objectType());

        objectTypeMap.put(DV_BOOLEAN, new DvBooleanGetEcisRM(this).objectType());

        objectTypeMap.put(DV_COUNT, new DvCountGetEcisRM(this).objectType());

        objectTypeMap.put(DV_IDENTIFIER, new DvIdentifierGetEcisRM(this).objectType());

        interfaceTypeMap.put(DATE_TIME_STATICS, new DvDateTimeStaticsIFGetEcisRM(this).interfaceType());

        objectTypeMap.put(DV_DATE, new DvDateGetEcisRM(this).objectType());
        objectTypeMap.put(DV_DATE_TIME, new DvDateTimeGetEcisRM(this).objectType());

        objectTypeMap.put(DV_DURATION, new DvDurationGetEcisRM(this).objectType());
        objectTypeMap.put(DV_EHR_URI, new DvEhrUriGetEcisRM(this).objectType());

        objectTypeMap.put(DV_ORDINAL, new DvOrdinalGetEcisRM(this).objectType());
        objectTypeMap.put(DV_PROPORTION, new DvProportionGetEcisRM(this).objectType());
        objectTypeMap.put(DV_QUANTITY, new DvQuantityGetEcisRM(this).objectType());
        objectTypeMap.put(DV_TIME, new DvTimeGetEcisRM(this).objectType());
        unionTypeMap.put(DATA_VALUE, new DataValueQLType(this).unionType());
        unionTypeMap.put(DV_ORDERED, new DvOrderedQLType(this).unionType());

        interfaceTypeMap.put(DV_ORDERED, new DvOrderedIFGetEcisRM(this).interfaceType());
        objectTypeMap.put(DV_INTERVAL, new DvIntervalGetEcisRM(this).objectType());

        interfaceTypeMap.put(LOCATABLE, new LocatableIFGetEcisRM(this).interfaceType());
        interfaceTypeMap.put(DATA_STRUCTURE, new DataStructureIFGetEcisRM(this).interfaceType());
        objectTypeMap.put(DATA_STRUCTURE, new DataStructureGetEcisRM(this).objectType());

        objectTypeMap.put(ELEMENT, new ElementGetEcisRM(this).objectType());
        objectTypeMap.put(CLUSTER, new ClusterGetEcisRM(this).objectType());
        unionTypeMap.put(ITEM, new ItemQLType(this).unionType());
        objectTypeMap.put(ITEM_LIST, new ItemListGetEcisRM(this).objectType());
        objectTypeMap.put(ITEM_SINGLE, new ItemSingleGetEcisRM(this).objectType());
        objectTypeMap.put(ITEM_TREE, new ItemTreeGetEcisRM(this).objectType());

        unionTypeMap.put(ITEM_STRUCTURE, new ItemStructureQLType(this).unionType());

        objectTypeMap.put(PARTY_IDENTIFIED, new PartyIdentifiedGetEcisRM(this).objectType());
        objectTypeMap.put(PARTICIPATION, new ParticipationGetEcisRM(this).objectType());

        interfaceTypeMap.put(CARE_ENTRY, new CareEntryIFGetEcisRM(this).interfaceType());
        interfaceTypeMap.put(CONTENT_ITEM, new ContentItemIFGetEcisRM(this).interfaceType());

        interfaceTypeMap.put(ENTRY, new EntryIFGetEcisRM(this).interfaceType());

        objectTypeMap.put(EVALUATION, new EvaluationGetEcisRM(this).objectType());

        objectTypeMap.put(EVENT, new EventGetEcisRM(this).objectType());
        objectTypeMap.put(HISTORY, new HistoryGetEcisRM(this).objectType());
        objectTypeMap.put(OBSERVATION, new ObservationGetEcisRM(this).objectType());

        objectTypeMap.put(OBJECT_ID, new ObjectIdGetEcisRM(this).objectType());
        objectTypeMap.put(LOCATABLE_REF, new LocatableRefGetEcisRM(this).objectType());
        objectTypeMap.put(ISM_TRANSITION, new IsmTransitionGetEcisRM(this).objectType());
        objectTypeMap.put(DV_PARSABLE, new DvParsableGetEcisRM(this).objectType());
        objectTypeMap.put(INSTRUCTION_DETAILS, new InstructionDetailsGetEcisRM(this).objectType());
        objectTypeMap.put(ACTIVITY, new ActivityGetEcisRM(this).objectType());
        objectTypeMap.put(INSTRUCTION, new InstructionGetEcisRM(this).objectType());
        objectTypeMap.put(ACTION, new ActionGetEcisRM(this).objectType());

        objectTypeMap.put(SECTION, new SectionGetEcisRM(this).objectType());

        unionTypeMap.put(CONTENT_ITEM, new ContentItemQLType(this).unionType());

        objectTypeMap.put(EVENT_CONTEXT, new EventContextGetEcisRM(this).objectType());
        objectTypeMap.put(COMPOSITION, new CompositionGetEcisRM(this).objectType());

        objectTypeMap.put(HIER_OBJECT_ID, new HierObjectIdGetEcisRM(this).objectType());

        objectTypeMap.put(AUDIT_DETAILS, new AuditDetailsGetEcisRM(this).objectType());
        objectTypeMap.put(CONTRIBUTION, new ContributionGetEcisRM(this).objectType());
        objectTypeMap.put(EHR_STATUS, new EhrStatusGetEcisRM(this).objectType());
        objectTypeMap.put(EHR_ACCESS, new EhrAccessGetEcisRM(this).objectType());

        objectTypeMap.put(EHR, new EhrGetEcisRM(this).objectType());

        objectTypeMap.put(CDR, new CdrGetEcisRM(this).objectType());
    }

    @Override
    public GraphQLObjectType objectType(String id) {
        return objectTypeMap.get(id);
    }

    @Override
    public GraphQLUnionType unionType(String id) {
        return unionTypeMap.get(id);
    }

    @Override
    public GraphQLInterfaceType interfaceType(String id) {
        return interfaceTypeMap.get(id);
    }

    @Override
    public GraphQLArgument argument(String id) {
        return argumentMap.get(id);
    }

    private String resolveInterfaceName(String interfaceId, String fieldId) {
        return interfaceTypeMap.get(interfaceId).getFieldDefinition(fieldId).getName();
    }

    private String resolveInterfaceDescription(String interfaceId, String fieldId) {
        return interfaceTypeMap.get(interfaceId).getFieldDefinition(fieldId).getDescription();
    }

    private DataFetcher resolveInterfaceDataFetcher(String interfaceId, String fieldId) {
        return interfaceTypeMap.get(interfaceId).getFieldDefinition(fieldId).getDataFetcher();
    }

    private GraphQLType resolveInterfaceFieldType(String interfaceId, String fieldId) {
        return interfaceTypeMap.get(interfaceId).getFieldDefinition(fieldId).getType();
    }

    private GraphQLObjectType addInterfaceDefinedFields(GraphQLObjectType objectType, GraphQLInterfaceType interfaceType) {
        List<GraphQLFieldDefinition> fieldDefinitions = objectType.getFieldDefinitions();
        fieldDefinitions.addAll(interfaceType.getFieldDefinitions());

        GraphQLObjectType newObject = new GraphQLObjectType(objectType.getName(), objectType.getDescription(), fieldDefinitions, objectType.getInterfaces());
        return newObject;
    }

    private List<GraphQLFieldDefinition> attachDataFetcher(List<GraphQLFieldDefinition> definitions, String fieldName, DataFetcher dataFetcher) {
        List<GraphQLFieldDefinition> fieldDefinitions = new ArrayList<>();
        for (GraphQLFieldDefinition fieldDefinition : definitions) {
            if (fieldDefinition.getName().equals(fieldName)) {
                GraphQLFieldDefinition newDefinition = new GraphQLFieldDefinition(
                        fieldDefinition.getName(),
                        fieldDefinition.getDescription(),
                        fieldDefinition.getType(),
                        dataFetcher,
                        fieldDefinition.getArguments(),
                        fieldDefinition.getDeprecationReason());
                fieldDefinitions.add(newDefinition);
            } else
                fieldDefinitions.add(fieldDefinition);
        }
        return fieldDefinitions;
    }

    @Override
    public GraphQLObjectType registerDataFetcher(GraphQLObjectType objectType, String fieldName, DataFetcher dataFetcher) {
        GraphQLObjectType newObject = new GraphQLObjectType(
                objectType.getName(),
                objectType.getDescription(),
                attachDataFetcher(objectType.getFieldDefinitions(), fieldName, dataFetcher),
                objectType.getInterfaces());
        return newObject;
    }

    @Override
    public GraphQLInterfaceType registerDataFetcher(GraphQLInterfaceType interfaceType, String fieldName, DataFetcher dataFetcher) {
        GraphQLInterfaceType newObject = new GraphQLInterfaceType(
                interfaceType.getName(),
                interfaceType.getDescription(),
                attachDataFetcher(interfaceType.getFieldDefinitions(), fieldName, dataFetcher),
                interfaceType.getTypeResolver());
        return newObject;
    }


}
