package com.ethercis.db;

import com.ethercis.db.support.AccessTestCase;
import com.ethercis.ecis_rm_db.CompositionData;
import com.ethercis.ecis_rm_db.CompositionExample;
import com.ethercis.graphql.cdr.CdrQLType;
import com.ethercis.graphql.cdr.ecis_rm_fetcher.CdrGetEcisRM;
import com.ethercis.graphql.commons.RmObjectQLRegistry;
import com.ethercis.util.QueryFromFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created by christian on 5/31/2017.
 */
public class CDRQLDBTest extends AccessTestCase {

    static final String FLAT_JSON_INPUT_DIRECTORY = "src/test/resources/flat_json_input";
    static final String TEST_OUTPUT_DIRECTORY = "src/test/resources/test_output";
    private String QUERY_INPUT_DIRECTORY = "src/test/resources/query";
    static boolean regressionTest = true;

    GraphQLSchema schema;


    @Before
    public void setUp() throws Exception {
        setupDomainAccess();

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();
        CdrQLType cdrQL = new CdrGetEcisRM(rmObjectQLRegistry);
        GraphQLObjectType cdrQLType = cdrQL.objectType();

        schema = GraphQLSchema.newSchema()
                .query(cdrQLType)
                .mutation(cdrQLType)
                .build();

        assertNotNull(schema);
    }

    private Map queryEhr(String query, Map<String, Object> parameters) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        GraphQL graphQL = GraphQL.newGraphQL(schema).build();
        Map results = graphQL.execute(query, this.testDomainAccess, parameters).getData();

        assertNotNull(results);
        System.out.println("===========================================================================");
        gson.toJson(results, System.out);
        return results;
    }


    @Test
    public void test_CDR_COMPOSITION_query_from_file() throws Exception {

        String query = new QueryFromFile("NCHCD - Clinical notes.v0.CDR.query", StandardCharsets.UTF_8, QUERY_INPUT_DIRECTORY).toString();

        assertNotNull(query);

        Map<String, Object> parameters = new HashMap<>();
        List<String> uidList = new ArrayList<>();
        uidList.add("602df8eb-c0c2-4cb3-92f4-ccc94e6e5616");
        parameters.put("composition_id", uidList);

        queryEhr(query, parameters);
    }

    @Test
    public void test_CDR_COMPOSITION_query_no_file() throws Exception {

        UUID compositionID = UUID.fromString("602df8eb-c0c2-4cb3-92f4-ccc94e6e5616");

        //get the template Id for the composition
        String templateId = new CompositionData(testDomainAccess, compositionID).templateId();

        String query = new CompositionExample(testDomainAccess, templateId, null).generate(compositionID);

        assertNotNull(query);

        Map<String, Object> parameters = new HashMap<>();
//        List<String> uidList = new ArrayList<>();
//        uidList.add("602df8eb-c0c2-4cb3-92f4-ccc94e6e5616");
//        parameters.put("composition_id", uidList);

        queryEhr(query, parameters);
    }

    @Test
    public void test_CDR_COMPOSITION_query_from_file2() throws Exception {

        String query = new QueryFromFile("IDCR - Laboratory Test Report.v0.CDR.query", StandardCharsets.UTF_8, QUERY_INPUT_DIRECTORY).toString();

        assertNotNull(query);

        Map<String, Object> parameters = new HashMap<>();
//        List<String> uidList = new ArrayList<>();
//        uidList.add("602df8eb-c0c2-4cb3-92f4-ccc94e6e5616");
//        parameters.put("composition_id", uidList);

        queryEhr(query, parameters);
    }


    @Test
    public void test_CDR_EHR_query_from_file() throws Exception {

        String query = new QueryFromFile("EHR.CDR.query", StandardCharsets.UTF_8, QUERY_INPUT_DIRECTORY).toString();

        assertNotNull(query);

        Map<String, Object> parameters = new HashMap<>();
        List<String> uidList = new ArrayList<>();
        uidList.add("05e28933-b59d-42be-80bf-6a4f58648262");
        parameters.put("ehr_id", uidList);

        queryEhr(query, parameters);
    }
}
