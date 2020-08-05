package com.ethercis.graphql.commons.interfaces;

import graphql.schema.*;

/**
 * Created by christian on 3/17/2017.
 */
public interface I_RmObjectQLRegistry {

    //================================= Data value
    String DATA_VALUE = "DATA_VALUE";
    String DV_AMOUNT = "DV_AMOUNT";
    String DATE_TIME_STATICS = "DATE_TIME_STATICS";
    String DV_QUANTIFIED = "DV_QUANTIFIED";
    String DV_TEMPORAL = "DV_TEMPORAL";
    String CODE_PHRASE = "CODE_PHRASE";
    String DV_BOOLEAN = "DV_BOOLEAN";
    String DV_CODED_TEXT = "DV_CODED_TEXT";
    String DV_COUNT = "DV_COUNT";
    String DV_DATE = "DV_DATE";
    String DV_DATE_TIME = "DV_DATE_TIME";
    String DV_DURATION = "DV_DURATION";
    String DV_EHR_URI = "DV_EHR_URI";
    String DV_INTERVAL = "DV_INTERVAL";
    String DV_ORDINAL = "DV_ORDINAL";
    String DV_PROPORTION = "DV_PROPORTION";
    String DV_QUANTITY = "DV_QUANTITY";
    String DV_TEXT = "DV_TEXT";
    String DV_TIME = "DV_TIME";
    String DV_URI = "DV_URI";
    String TERMINOLOGY_ID = "TERMINOLOGY_ID";
    String TERM_MAPPING = "TERM_MAPPING";

    //================================= Data structure
    String ARCHETYPED = "ARCHETYPED";
    String ARCHETYPE_ID = "ARCHETYPE_ID";
    String CARE_ENTRY = "CARE_ENTRY";
    String ENTRY = "ENTRY";
    String ELEMENT = "ELEMENT";
    String EVALUATION = "EVALUATION";
    String ITEM_LIST = "ITEM_LIST";
    String ITEM_SINGLE = "ITEM_SINGLE";
    String ITEM_STRUCTURE = "ITEM_STRUCTURE";
    String ITEM_TREE = "ITEM_TREE";
    String ITEM = "ITEM";
    String LINK = "LINK";
    String UID_BASE_ID = "UID_BASE_ID";
    String OBJECT_REF = "OBJECT_REF";
    String PARTICIPATION = "PARTICIPATION";
    String PARTY_PROXY = "PARTY_PROXY";
    String PARTY_REF = "PARTY_REF";
    String TEMPLATE_ID = "TEMPLATE_ID";
    String UID = "UID";
    String DATA_STRUCTURE = "DATA_STRUCTURE";
    String CONTENT_ITEM = "CONTENT_ITEM";
    String ACTION = "ACTION";
    String ACTIVITY = "ACTIVITY";
    String ADMIN_ENTRY = "ADMIN_ENTRY";
    String INSTRUCTION = "INSTRUCTION";
    String INSTRUCTION_DETAILS = "INSTRUCTION_DETAILS";
    String ISM_TRANSITION = "ISM_TRANSITION";
    String OBSERVATION ="OBSERVATION";
    String SECTION = "SECTION";
    String EVENT_CONTEXT = "EVENT_CONTEXT";
    String PARTY_IDENTIFIED = "PARTY_IDENTIFIED";
    String DV_IDENTIFIER = "DV_IDENTIFIER";
    String COMPOSITION = "COMPOSITION";
    String CLUSTER = "CLUSTER";
    String ARCHETYPE_NODE_ID_ARGUMENT = "ARCHETYPE_NODE_ID_ARGUMENT";
    String NODE_NAME_ARGUMENT = "NODE_NAME_ARGUMENT";
    String DV_MULTIMEDIA = "DV_MULTIMEDIA";
    String DV_ORDERED = "DV_ORDERED";
    String REFERENCE_RANGE = "REFERENCE_RANGE";
    String HISTORY = "HISTORY";
    String EVENT = "EVENT";
    String LOCATABLE = "LOCATABLE";
    String TEXT_DATA_VALUE = "TEXT_DATA_VALUE";
    String DV_PARSABLE = "DV_PARSABLE";
    String LOCATABLE_REF = "LOCATABLE_REF";
    String OBJECT_ID = "ID";
    String EHR = "EHR";
    String HIER_OBJECT_ID = "HIER_OBJECT_ID";
    String COMPOSITION_ID_ARGUMENT = "COMPOSITION_ID_ARGUMENT";
    String CONTRIBUTION = "CONTRIBUTION";
    String AUDIT_DETAILS = "AUDIT_DETAILS";
    String EHR_STATUS = "EHR_STATUS";
    String EHR_ACCESS = "EHR_ACCESS";
    String EHR_ID_ARGUMENT = "EHR_ID_ARGUMENT";
    String CDR = "CDR";


    GraphQLObjectType objectType(String id);

    GraphQLUnionType unionType(String id);

    GraphQLInterfaceType interfaceType(String id);

    GraphQLArgument argument(String id);


    GraphQLObjectType registerDataFetcher(GraphQLObjectType objectType, String fieldName, DataFetcher dataFetcher);

    GraphQLInterfaceType registerDataFetcher(GraphQLInterfaceType interfaceType, String fieldName, DataFetcher dataFetcher);
}
