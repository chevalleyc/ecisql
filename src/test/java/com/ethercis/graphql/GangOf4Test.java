package com.ethercis.graphql;

import com.ethercis.ehr.building.I_ContentBuilder;
import com.ethercis.ehr.json.FlatJsonUtil;
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
import org.junit.Before;
import org.junit.Test;
import org.openehr.rm.composition.Composition;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import static org.junit.Assert.assertNotNull;

/**
 * Created by christian on 5/23/2017.
 */
public class GangOf4Test {

    I_KnowledgeCache knowledge;
    static final String FLAT_JSON_INPUT_DIRECTORY = "src/test/resources/flat_json_input";
    static final String TEST_OUTPUT_DIRECTORY = "src/test/resources/test_output";
    private String QUERY_INPUT_DIRECTORY = "src/test/resources/query";
    static boolean regressionTest = true;

    GraphQLSchema schema;

    @Before
    public void setUp() throws Exception {
        Properties props = new Properties();
        props.put("knowledge.path.archetype", "src/test/resources/knowledge/archetypes");
        props.put("knowledge.path.template", "src/test/resources/knowledge/templates");
        props.put("knowledge.path.opt", "src/test/resources/knowledge/operational_templates");
        props.put("knowledge.cachelocatable", "true");
        props.put("knowledge.forcecache", "true");
        knowledge = new KnowledgeCache(null, props);

        Pattern include = Pattern.compile(".*");

        knowledge.retrieveFileMap(include, null);

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();
        CompositionQLType compositionQL = new CompositionGetEcisRM(rmObjectQLRegistry);
        GraphQLObjectType compositionQLType = compositionQL.objectType();
        assertNotNull(compositionQLType);

        schema = GraphQLSchema.newSchema()
                .query(compositionQLType)
                .mutation(compositionQLType)
                .build();

        assertNotNull(schema);
    }

    Composition getInstance(String templateId, String flatJsonTestInput) throws Exception {
        I_FlatJsonCompositionConverter jsonCompositionConverter = FlatJsonCompositionConverter.getInstance(knowledge);

        FileReader fileReader = new FileReader(FLAT_JSON_INPUT_DIRECTORY + "/" + flatJsonTestInput);

        Map map = FlatJsonUtil.inputStream2Map(fileReader);

        Composition lastComposition = jsonCompositionConverter.toComposition(templateId, map);

        return lastComposition;
    }

    String readQueryString(String querypath, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(QUERY_INPUT_DIRECTORY + "/" +querypath));
        return new String(encoded, encoding);
    }

    Composition getExample(String templateId) throws Exception {
        I_ContentBuilder contentBuilder = I_ContentBuilder.getInstance(knowledge, templateId);
//        I_ContentBuilder contentBuilder = I_ContentBuilder.getInstance(I_ContentBuilder.OPT, knowledge, "action test");

        Composition composition = contentBuilder.generateNewComposition();

        return composition;
    }


    private Map queryComposition(String query, Composition composition) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        GraphQL graphQL = GraphQL.newGraphQL(schema).build();
        Map results = graphQL.execute(query, composition).getData();

        assertNotNull(results);
        System.out.println("===========================================================================");
        gson.toJson(results, System.out);
        return results;
    }


    @Test
    public void test_IDCR_Adverse_Reaction_List_v1() throws Exception {

//        Composition composition = getInstance("IDCR - Adverse Reaction List.v1", "IDCR - Adverse Reaction List.v1.flat.json");
        Composition composition = getExample("IDCR - Adverse Reaction List.v1");
        //generate a query example
        String query = new Exemplify(ExemplifyMode.REQUIRED_FIELDS_ONLY).generate(composition).modelize("query composition \n", "");

        assertNotNull(query);

        queryComposition(query, getInstance("IDCR - Adverse Reaction List.v1", "IDCR - Adverse Reaction List.v1.flat.json"));

    }

    @Test
    public void test_IDCR_Laboratory_Test_Report_v0() throws Exception {

        Composition composition = getInstance("IDCR - Laboratory Test Report.v0", "IDCR-LaboratoryTestReport.v0.flat.json");
//        Composition composition = getExample("IDCR - Laboratory Test Report.v0");
        //generate a query example
        String query = new Exemplify(ExemplifyMode.ANY_FIELD).generate(composition).modelize("query composition \n", "");

        assertNotNull(query);

        queryComposition(query, getInstance("IDCR - Laboratory Test Report.v0", "IDCR-LaboratoryTestReport.v0.flat.json"));

    }

    @Test
    public void test_IDCR_Laboratory_Test_Report_v0_from_file() throws Exception {

        String query = readQueryString("IDCR - Laboratory Test Report.v0.query", StandardCharsets.UTF_8);

        assertNotNull(query);

        queryComposition(query, getInstance("IDCR - Laboratory Test Report.v0", "IDCR-LaboratoryTestReport.v0.flat.json"));

    }

    @Test
    public void test_IDCR_Service_Request_v0() throws Exception {

//        Composition composition = getInstance("IDCR - Laboratory Test Report.v0", "IDCR-LaboratoryTestReport.v0.flat.json");
        Composition composition = getExample("IDCR - Service Request.v0");
        //generate a query example
        String query = new Exemplify(ExemplifyMode.REQUIRED_FIELDS_ONLY).generate(composition).modelize("query composition \n", "");

        assertNotNull(query);

        queryComposition(query, getInstance("IDCR - Service Request.v0", "IDCR - Service Request.v0.flat.json"));

    }

    @Test
    public void test_COLNEC_Medication_FLAT() throws Exception {

//        Composition composition = getInstance("IDCR - Laboratory Test Report.v0", "IDCR-LaboratoryTestReport.v0.flat.json");
        Composition composition = getExample("COLNEC Medication");
        //generate a query example
        String query = new Exemplify(ExemplifyMode.REQUIRED_FIELDS_ONLY).generate(composition).modelize("query composition \n", "");

        assertNotNull(query);

        queryComposition(query, getInstance("COLNEC Medication", "COLNEC_Medication_FLAT.json"));

    }

    @Test
    public void test_DiADeM_Assessment_v1() throws Exception {

        Composition composition = getExample("DiADeM Assessment.v1");
        //generate a query example
        String query = new Exemplify(ExemplifyMode.REQUIRED_FIELDS_ONLY).generate(composition).modelize("query composition \n", "");

        assertNotNull(query);

        queryComposition(query, getInstance("DiADeM Assessment.v1", "DiADeM Assessment.v1.flat.json"));

    }

    @Test
    public void test_IDCR_Immunisation_summary_v0() throws Exception {

        Composition composition = getExample("IDCR - Immunisation summary.v0");
        //generate a query example
        String query = new Exemplify(ExemplifyMode.REQUIRED_FIELDS_ONLY).generate(composition).modelize("query composition \n", "");

        assertNotNull(query);

        queryComposition(query, getInstance("IDCR - Immunisation summary.v0", "IDCR - Immunisation summary.v0.flat.json"));

    }

    @Test
    public void test_IDCR_Relevant_contacts_v0() throws Exception {

        Composition composition = getExample("IDCR - Relevant contacts.v0");
        //generate a query example
        String query = new Exemplify(ExemplifyMode.REQUIRED_FIELDS_ONLY).generate(composition).modelize("query composition \n", "");

        assertNotNull(query);

        queryComposition(query, getInstance("IDCR - Relevant contacts.v0", "IDCR - Relevant contacts.v0.flat.json"));

    }

    @Test
    public void test_LCR_Medication_List_v0() throws Exception {

        Composition composition = getExample("LCR Medication List.v0");
        //generate a query example
        String query = new Exemplify(ExemplifyMode.REQUIRED_FIELDS_ONLY).generate(composition).modelize("query composition \n", "");

        assertNotNull(query);

        queryComposition(query, getInstance("LCR Medication List.v0", "LCR_Medication_List.v0.flat.json"));

    }

    @Test
    public void test_NCHCD_Clinical_notes_v0() throws Exception {

        Composition composition = getExample("NCHCD - Clinical notes.v0");
        //generate a query example
        String query = new Exemplify(ExemplifyMode.REQUIRED_FIELDS_ONLY).generate(composition).modelize("query composition \n", "");

        assertNotNull(query);

        queryComposition(query, getInstance("NCHCD - Clinical notes.v0", "NCHCD - Clinical notes.v0.flat.json"));

    }

    @Test
    public void test_NCHCD_Clinical_notes_v0_from_file() throws Exception {

        String query = readQueryString("NCHCD - Clinical notes.v0.query", StandardCharsets.UTF_8);

        assertNotNull(query);

        queryComposition(query, getInstance("NCHCD - Clinical notes.v0", "NCHCD - Clinical notes.v0.flat.json"));

    }

}
