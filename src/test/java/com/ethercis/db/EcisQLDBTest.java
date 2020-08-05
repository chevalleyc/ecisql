package com.ethercis.db;

import com.ethercis.db.support.AccessTestCase;
import com.ethercis.graphql.commons.RmObjectQLRegistry;
import com.ethercis.graphql.ehr.EhrQLType;
import com.ethercis.graphql.ehr.ecis_rm_getter.EhrGetEcisRM;
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

import static org.junit.Assert.assertNotNull;

/**
 * Created by christian on 5/31/2017.
 */
public class EcisQLDBTest extends AccessTestCase {

    static final String FLAT_JSON_INPUT_DIRECTORY = "src/test/resources/flat_json_input";
    static final String TEST_OUTPUT_DIRECTORY = "src/test/resources/test_output";
    private String QUERY_INPUT_DIRECTORY = "src/test/resources/query";
    static boolean regressionTest = true;

    GraphQLSchema schema;


    @Before
    public void setUp() throws Exception {
        setupDomainAccess();

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();
        EhrQLType ehrQL = new EhrGetEcisRM(rmObjectQLRegistry);
        GraphQLObjectType ehrQLType = ehrQL.objectType();

        schema = GraphQLSchema.newSchema()
                .query(ehrQLType)
                .mutation(ehrQLType)
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
    public void test_NCHCD_Clinical_notes_v0_from_file() throws Exception {

        String query = new QueryFromFile("NCHCD - Clinical notes.v0.DB.query", StandardCharsets.UTF_8, QUERY_INPUT_DIRECTORY).toString();

        assertNotNull(query);

        Map<String, Object> parameters = new HashMap<>();
        List<String> uidList = new ArrayList<>();
        uidList.add("0e42e662-482b-4e19-882a-30caf0398d62");
        parameters.put("composition_id", uidList);

        queryEhr(query, parameters);

    }


}
