package com.ethercis.graphql.datastructure;

import com.ethercis.ehr.building.I_ContentBuilder;
import com.ethercis.ehr.keyvalues.PathValue;
import com.ethercis.ehr.knowledge.I_KnowledgeCache;
import com.ethercis.ehr.knowledge.KnowledgeCache;
import com.ethercis.ehr.util.FlatJsonCompositionConverter;
import com.ethercis.ehr.util.I_FlatJsonCompositionConverter;
import com.ethercis.graphql.commons.RmObjectQLRegistry;
import com.ethercis.graphql.composition.CompositionQLType;
import com.ethercis.graphql.composition.ecis_rm_getter.CompositionGetEcisRM;
import com.ethercis.graphql.generator.Exemplify;
import com.ethercis.graphql.generator.ExemplifyMode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.EventContext;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.entry.Evaluation;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.basic.DvIdentifier;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.*;
import org.openehr.rm.support.identification.UUID;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by christian on 3/22/2017.
 */
public class CompositionQLTypeTest {

    public static final String ECIS_EVALUATION_TEST_TEMPLATE = "ECIS EVALUATION TEST";
    I_KnowledgeCache knowledge;

    @Before
    public void setUp() throws Exception {
        Properties props = new Properties();
        props.put("knowledge.path.archetype", "/Development/Dropbox/eCIS_Development/knowledge/production/archetypes");
        props.put("knowledge.path.template", "/Development/Dropbox/eCIS_Development/knowledge/production/templates");
        props.put("knowledge.path.opt", "/Development/Dropbox/eCIS_Development/knowledge/production/operational_templates");
        props.put("knowledge.cachelocatable", "true");
        props.put("knowledge.forcecache", "true");
        knowledge = new KnowledgeCache(null, props);

        Pattern include = Pattern.compile(".*");

        knowledge.retrieveFileMap(include, null);
//
//        FileReader fileReader = new FileReader("/Development/Dropbox/eCIS_Development/samples/pathvalues_test1.json");
//
//        kvPairs = FlatJsonUtil.inputStream2Map(fileReader);
    }

    @Test
    public void testSimpleComposition() throws Exception {

        DvQuantity dvQuantity = new DvQuantity("kg", 10.0, 2);
        Element element = new Element("[at0003]", "testQuantity", dvQuantity);
        ItemStructure itemStructure = new ItemSingle("[at0002]", "testQuantity", element);
        UIDBasedID id = new HierObjectID(new UUID("123e4567-e89b-12d3-a456-426655440000"), "no extension");
        TemplateID templateID = new TemplateID("TEMPLATE");
        ArchetypeID archetypeID = new ArchetypeID("openEHR-EHR-EVALUATION.test.v1");
        Archetyped archetype_details = new Archetyped(archetypeID, templateID, "1.0.1");
        Evaluation evaluation = new Evaluation(id,
                "at[0002]",
                new DvText("Evaluation"),
                archetype_details,
                null,
                null,
                null,
                new CodePhrase("ISO_639-1", "en"),
                new CodePhrase("IANA_character-sets", "UTF-8"),
                new PartySelf(new PartyRef(new HierObjectID("1.2.4.5.6.12.1"), "ETHERCIS-NAMESPACE", "PARTY")),
                null,
                null,
                null,
                null,
                null,
                itemStructure,
                SimpleTerminologyService.getInstance());

        assertNotNull(evaluation);

        UIDBasedID objectID = new HierObjectID(new UUID("123e4567-e89b-12d3-a456-426655441111"), "no extension");
        ArchetypeID archetypeID1 = new ArchetypeID("openEHR-EHR-COMPOSITION.test.v1");
        TemplateID templateID1 = new TemplateID("TEMPLATE");
        Archetyped archetype_details1 = new Archetyped(archetypeID1, templateID1, "1.0.1");
        List<ContentItem> items = new ArrayList<>();
        items.add(evaluation);
        Composition composition = new Composition(objectID,
                "at[0001]",
                new DvText("compo_test"),
                archetype_details1,
                null,
                null,
                null,
                items,
                new CodePhrase("ISO_639-1", "en"),
                makeEventContext(),
                new PartyIdentified(new PartyRef(new HierObjectID("1.3.3.1"),"ETHERCIS-NAMESPACE", "ORGANISATION"),"joe doe", null),
                new DvCodedText("event",new CodePhrase("openehr", "433")),
                new CodePhrase("ISO_3166-1", "GB"),
                SimpleTerminologyService.getInstance()
                );

        assertNotNull(composition);

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();

        CompositionQLType compositionQL = new CompositionGetEcisRM(rmObjectQLRegistry);

        GraphQLObjectType objectType = compositionQL.objectType();

        assertNotNull(objectType);

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(objectType)
                .mutation(objectType)
                .build();

        assertNotNull(schema);

        String query =
"query composition {\n " +
"   name {value},\n " +
"   context {\n " +
"       health_care_facility {name},\n " +
"       participation {\n " +
"           performer {name},\n " +
"           function {value} \n " +
"       },\n " +
"       start_time{value}\n " +
"   },\n " +
"   # %5Bat0002%2C%20name/value%3D%27Evaluation%27%5D\n" +
"   at0002_Evaluation: content(archetype_node_id: \"at[0002]\", name_value: \"Evaluation\")  {\n " +
"   ... on EVALUATION {\n " +
"       name {value},\n" +
"       language {code_string},\n " +
"       encoding {code_string},\n " +
"       archetype_details {\n " +
"           archetype_id {value}\n " +
"           template_id {value}\n " +
"       },\n " +
"       data {\n " +
"           ... on ITEM_SINGLE {\n " +
"               name{value},\n " +
"               archetype_node_id,\n " +
"               item {\n " +
"                   name{value},\n " +
"                   archetype_node_id,\n " +
"                   value {\n " +
"                       ... on DV_QUANTITY {magnitude, units}}\n " +
"                   }\n " +
"               }\n " +
"           }\n " +
"       }\n " +
"   }\n " +
"}";

        Map results = (Map) new GraphQL(schema).execute(query, composition).getData();

        assertNotNull(results);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(results, System.out);

        //generate a query example
        Exemplify queryExample = new Exemplify(ExemplifyMode.ANY_FIELD);
        queryExample.generate(composition);
        System.out.println(queryExample.modelize("query composition \n", ""));

    }

    @Test
    public void testCompoEvaluation() throws Exception {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        I_ContentBuilder contentBuilder = I_ContentBuilder.getInstance(knowledge, "ECIS EVALUATION TEST");
        Composition composition = contentBuilder.generateNewComposition();

        assertNotNull(composition);
    }

    public static Participation makeParticipation(){
        PartyRef partyRef = new PartyRef(new HierObjectID("ref"), "ETHERCIS-NAMESPACE", "PARTY");
        List<DvIdentifier> identifiers = new ArrayList<>();
        identifiers.add(new DvIdentifier("NHS-UK", "NHS-UK", "999999-1234", "2.16.840.1.113883.2.1.4.3"));
        PartyIdentified performer = new PartyIdentified(partyRef, "HERR DOKTOR", null);
        TerminologyService terminologyService;
        try {
            terminologyService = SimpleTerminologyService.getInstance();
        } catch (Exception e){
            throw new IllegalArgumentException("Could not instantiate terminology service:"+e);
        }
        Participation participation = new Participation(performer,
                new DvText("wanderer"),
                new DvCodedText("telephone", "openehr", "204"),
                new DvInterval<>(new DvDateTime(DateTime.now().toString()), null),
                terminologyService);
        return participation;
    }

    private EventContext makeEventContext(){
        PartyRef partyRef = new PartyRef(new HierObjectID("ref"), "ETHERCIS-NAMESPACE", "party");
        org.openehr.rm.common.generic.PartyIdentified healthcareFacility = new org.openehr.rm.common.generic.PartyIdentified(partyRef, "hospital ZZ", null);
        DateTime timenow = new DateTime(0L);
        DvCodedText concept = new DvCodedText("other care", new CodePhrase("openehr", "238"));
        TerminologyService terminologyService = SimpleTerminologyService.getInstance();
        List<Participation> participations = new ArrayList<>();
        participations.add(makeParticipation());
        EventContext eventContext = new EventContext(healthcareFacility, new DvDateTime(2017, 06, 17, 13, 0, null), null, participations, "$SYSTEM$", concept, null, terminologyService);
        return eventContext;
    }

    @Test
    public void testQueryGenerated() throws Exception {

        DvQuantity dvQuantity = new DvQuantity("kg", 10.0, 2);
        Element element = new Element("[at0003]", "testQuantity", dvQuantity);
        ItemStructure itemStructure = new ItemSingle("[at0002]", "testQuantity", element);
        UIDBasedID id = new HierObjectID(new UUID("123e4567-e89b-12d3-a456-426655440000"), "no extension");
        TemplateID templateID = new TemplateID("TEMPLATE");
        ArchetypeID archetypeID = new ArchetypeID("openEHR-EHR-EVALUATION.test.v1");
        Archetyped archetype_details = new Archetyped(archetypeID, templateID, "1.0.1");
        Evaluation evaluation = new Evaluation(id,
                "at[0002]",
                new DvText("Evaluation"),
                archetype_details,
                null,
                null,
                null,
                new CodePhrase("ISO_639-1", "en"),
                new CodePhrase("IANA_character-sets", "UTF-8"),
                new PartySelf(new PartyRef(new HierObjectID("1.2.4.5.6.12.1"), "ETHERCIS-NAMESPACE", "PARTY")),
                null,
                null,
                null,
                null,
                null,
                itemStructure,
                SimpleTerminologyService.getInstance());

        assertNotNull(evaluation);

        UIDBasedID objectID = new HierObjectID(new UUID("123e4567-e89b-12d3-a456-426655441111"), "no extension");
        ArchetypeID archetypeID1 = new ArchetypeID("openEHR-EHR-COMPOSITION.test.v1");
        TemplateID templateID1 = new TemplateID("openEHR-EHR-COMPOSITION.test.v1");
        Archetyped archetype_details1 = new Archetyped(archetypeID1, templateID1, "1.0.1");
        List<ContentItem> items = new ArrayList<>();
        items.add(evaluation);
        Composition composition = new Composition(objectID,
                "at[0001]",
                new DvText("compo_test"),
                archetype_details1,
                null,
                null,
                null,
                items,
                new CodePhrase("ISO_639-1", "en"),
                makeEventContext(),
                new PartyIdentified(new PartyRef(new HierObjectID("1.3.3.1"),"ETHERCIS-NAMESPACE", "ORGANISATION"),"joe doe", null),
                new DvCodedText("event",new CodePhrase("openehr", "433")),
                new CodePhrase("ISO_3166-1", "GB"),
                SimpleTerminologyService.getInstance()
        );

        assertNotNull(composition);

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();

        CompositionQLType compositionQL = new CompositionGetEcisRM(rmObjectQLRegistry);

        GraphQLObjectType objectType = compositionQL.objectType();

        assertNotNull(objectType);

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(objectType)
                .mutation(objectType)
                .build();

        assertNotNull(schema);

        //generate a query example
        Exemplify queryExample = new Exemplify(ExemplifyMode.REQUIRED_FIELDS_ONLY);
        queryExample.generate(composition);
        String query = queryExample.modelize("query composition \n", "");


        Map results = (Map) new GraphQL(schema).execute(query, composition).getData();

        assertNotNull(results);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(results, System.out);

    }

    @Test
    public void testEvaluationQuery() throws Exception {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        I_ContentBuilder contentBuilder = I_ContentBuilder.getInstance(knowledge, "COLNEC Health Risk Assessment.v1");
        Composition composition = contentBuilder.generateNewComposition();

        assertNotNull(composition);

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();
        CompositionQLType compositionQL = new CompositionGetEcisRM(rmObjectQLRegistry);
        GraphQLObjectType compositionQLType = compositionQL.objectType();
        assertNotNull(compositionQLType);

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(compositionQLType)
                .mutation(compositionQLType)
                .build();

        assertNotNull(schema);

        //generate a query example
        String query = new Exemplify(ExemplifyMode.ANY_FIELD).generate(composition).modelize("query composition \n", "");

        Map results = (Map) new GraphQL(schema).execute(query, composition).getData();

        assertNotNull(results);

        gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(results, System.out);

    }

    private String queryFromFile(String fileName){

        StringBuffer queryBuffer = new StringBuffer();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(s -> queryBuffer.append(s+"\n"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return queryBuffer.toString();
    }

    @Test
    public void testFromFile1() throws Exception {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        I_ContentBuilder contentBuilder = I_ContentBuilder.getInstance(knowledge, "COLNEC Health Risk Assessment.v1");
        Composition composition = contentBuilder.generateNewComposition();

        assertNotNull(composition);

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();
        CompositionQLType compositionQL = new CompositionGetEcisRM(rmObjectQLRegistry);
        GraphQLObjectType compositionQLType = compositionQL.objectType();
        assertNotNull(compositionQLType);

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(compositionQLType)
                .mutation(compositionQLType)
                .build();

        assertNotNull(schema);

        //generate a query example
        String query = queryFromFile("C:\\Development\\Dropbox\\eCIS_Development\\test\\query_COLNEC Health Risk Assessment_QL_RESULT.txt");

        Map results = (Map) new GraphQL(schema).execute(query, composition).getData();

        assertNotNull(results);

        gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(results, System.out);

    }

    @Test
    public void testEvaluationWithArray() throws Exception {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        I_ContentBuilder contentBuilder = I_ContentBuilder.getInstance(knowledge, ECIS_EVALUATION_TEST_TEMPLATE);
        Composition composition = contentBuilder.generateNewComposition();

        assertNotNull(composition);

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();
        CompositionQLType compositionQL = new CompositionGetEcisRM(rmObjectQLRegistry);
        GraphQLObjectType compositionQLType = compositionQL.objectType();
        assertNotNull(compositionQLType);

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(compositionQLType)
                .mutation(compositionQLType)
                .build();

        assertNotNull(schema);

        //generate a query example
        String query = new Exemplify(ExemplifyMode.REQUIRED_FIELDS_ONLY).generate(composition).modelize("query composition \n", "");

        PathValue pathValue = new PathValue(contentBuilder, knowledge, ECIS_EVALUATION_TEST_TEMPLATE, null);

        Map<String, Object> updateValues = new HashMap<>();

        updateValues.put("/context/end_time", "2014-09-28T11:18:17.352+07:00");
        updateValues.put("/context/participation|function", "Cantine");
        updateValues.put("/context/participation|name", "Joe");
        updateValues.put("/context/participation|identifier", "99999-222");
        updateValues.put("/context/participation|mode", "face-to-face communication::openehr::216");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/data[at0001]/items[at0002]/items[openEHR-EHR-CLUSTER.diabetes.v1]/items[at0001, '#1']/items[at0003]", "at0004|Type 1|");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/data[at0001]/items[at0002]/items[openEHR-EHR-CLUSTER.diabetes.v1]/items[at0001, '#1']/items[at0008]", "true");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/data[at0001]/items[at0002]/items[openEHR-EHR-CLUSTER.diabetes.v1]/items[at0001, '#1']/items[at0007]", "2010-09-24");
        //adds another iteration of diabetes
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/data[at0001]/items[at0002]/items[openEHR-EHR-CLUSTER.diabetes.v1]/items[at0001, '#2']/items[at0003]", "at0005|Type 2|");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/data[at0001]/items[at0002]/items[openEHR-EHR-CLUSTER.diabetes.v1]/items[at0001, '#2']/items[at0008]", "true");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/data[at0001]/items[at0002]/items[openEHR-EHR-CLUSTER.diabetes.v1]/items[at0001, '#2']/items[at0007]", "2015-09-24");

        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/participation:0|function", "Oncologist");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/participation:0|identifier", "1345678");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/participation:0|mode", "face-to-face communication::openehr::216");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/participation:0|name", "Dr. Knock");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/participation:1|function", "Oncologist");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/participation:1|identifier", "999999-8");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/participation:1|mode", "face-to-face communication::openehr::216");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/participation:1|name", "Dr. Caligari");

        boolean result = pathValue.update(composition, updateValues);

        assertTrue(result);

        Map results = (Map) new GraphQL(schema).execute(query, composition).getData();

        assertNotNull(results);

        gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(results, System.out);

    }

    @Test
    public void testEvaluationWithArrayFromFile() throws Exception {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        I_ContentBuilder contentBuilder = I_ContentBuilder.getInstance(knowledge, ECIS_EVALUATION_TEST_TEMPLATE);
        Composition composition = contentBuilder.generateNewComposition();

        assertNotNull(composition);

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();
        CompositionQLType compositionQL = new CompositionGetEcisRM(rmObjectQLRegistry);
        GraphQLObjectType compositionQLType = compositionQL.objectType();
        assertNotNull(compositionQLType);

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(compositionQLType)
                .mutation(compositionQLType)
                .build();

        assertNotNull(schema);

        //generate a query example
        String query = queryFromFile("C:\\Development\\Dropbox\\eCIS_Development\\test\\ECIS_EVALUATION_TEST_query.txt");

        PathValue pathValue = new PathValue(contentBuilder, knowledge, ECIS_EVALUATION_TEST_TEMPLATE, null);

        Map<String, Object> updateValues = new HashMap<>();

        updateValues.put("/context/end_time", "2014-09-28T11:18:17.352+07:00");
        updateValues.put("/context/participation|function", "Cantine");
        updateValues.put("/context/participation|name", "Joe");
        updateValues.put("/context/participation|identifier", "99999-222");
        updateValues.put("/context/participation|mode", "face-to-face communication::openehr::216");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/data[at0001]/items[at0002]/items[openEHR-EHR-CLUSTER.diabetes.v1]/items[at0001, '#1']/items[at0003]", "at0004|Type 1|");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/data[at0001]/items[at0002]/items[openEHR-EHR-CLUSTER.diabetes.v1]/items[at0001, '#1']/items[at0008]", "true");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/data[at0001]/items[at0002]/items[openEHR-EHR-CLUSTER.diabetes.v1]/items[at0001, '#1']/items[at0007]", "2010-09-24");
        //adds another iteration of diabetes
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/data[at0001]/items[at0002]/items[openEHR-EHR-CLUSTER.diabetes.v1]/items[at0001, '#2']/items[at0003]", "at0005|Type 2|");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/data[at0001]/items[at0002]/items[openEHR-EHR-CLUSTER.diabetes.v1]/items[at0001, '#2']/items[at0008]", "true");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/data[at0001]/items[at0002]/items[openEHR-EHR-CLUSTER.diabetes.v1]/items[at0001, '#2']/items[at0007]", "2015-09-24");

        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/participation:0|function", "Oncologist");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/participation:0|identifier", "1345678");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/participation:0|mode", "face-to-face communication::openehr::216");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/participation:0|name", "Dr. Knock");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/participation:1|function", "Oncologist");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/participation:1|identifier", "999999-8");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/participation:1|mode", "face-to-face communication::openehr::216");
        updateValues.put("/content[openEHR-EHR-EVALUATION.verbal_examination.v1]/participation:1|name", "Dr. Caligari");

        boolean result = pathValue.update(composition, updateValues);


        gson = new GsonBuilder().setPrettyPrinting().create();

        I_FlatJsonCompositionConverter flatJsonCompositionConverter = FlatJsonCompositionConverter.getInstance(knowledge);
        Map flatten = flatJsonCompositionConverter.fromComposition(ECIS_EVALUATION_TEST_TEMPLATE, composition);

        System.out.println("===========================================================================");
        gson.toJson(flatten, System.out);

//        Map results = (Map) new GraphQL(schema).execute(query, composition).getData();

        GraphQL graphQL = GraphQL.newGraphQL(schema).build();

        Map results = graphQL.execute(query, composition).getData();

        assertNotNull(results);
        System.out.println("===========================================================================");
        gson.toJson(results, System.out);

    }

}