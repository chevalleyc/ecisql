package com.ethercis.graphql.datatypes.datavalue;

import com.ethercis.graphql.commons.RmObjectQLRegistry;
import com.ethercis.graphql.datatypes.datavalue.ecis_rm_getter.DvQuantityGetEcisRM;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.junit.Test;
import org.openehr.rm.datatypes.quantity.DvQuantity;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by christian on 3/14/2017.
 */
public class DvQuantityQLTypeTest {


    @Test
    public void testQuantity(){
        DvQuantity dvQuantity = new DvQuantity("kg", 10.0, 2);

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();
        DvQuantityQLType dvQuantityQLType = new DvQuantityGetEcisRM(rmObjectQLRegistry);

        GraphQLObjectType objectType = dvQuantityQLType.objectType();

        assertNotNull(objectType);

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(objectType)
                .mutation(objectType)
                .build();

        assertNotNull(schema);

        Map results = (Map) new GraphQL(schema).execute(
                "query {" +
                        " magnitude" +
                        " units" +
                        " precision" +
                "}", dvQuantity
        ).getData();

        assertEquals(results.get("magnitude"), 10.0);
        assertEquals(results.get("units"), "kg");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(results, System.out);

    }

    @Test
    public void testQuantityMutation(){
        DvQuantity dvQuantity = new DvQuantity("kg", 10.0, 2);

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();
        DvQuantityQLType dvQuantityQLType = new DvQuantityGetEcisRM(rmObjectQLRegistry);

        GraphQLObjectType objectType = dvQuantityQLType.objectType();

        assertNotNull(objectType);

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(objectType)
                .mutation(objectType)
                .build();

        assertNotNull(schema);

        Map results = (Map) new GraphQL(schema).execute(
                "query {" +
                        " magnitude" +
                        " units" +
                        " precision" +
                        "}", dvQuantity
        ).getData();

        assertEquals(results.get("magnitude"), 10.0);
        assertEquals(results.get("units"), "kg");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(results, System.out);

    }

}