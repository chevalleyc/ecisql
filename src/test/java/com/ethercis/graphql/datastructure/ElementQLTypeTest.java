package com.ethercis.graphql.datastructure;

import com.ethercis.graphql.commons.RmObjectQLRegistry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.junit.Test;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;
import org.openehr.rm.datatypes.text.DvCodedText;

import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by christian on 3/14/2017.
 */
public class ElementQLTypeTest {

    @Test
    public void testElementDvQuantity(){
        DvQuantity dvQuantity = new DvQuantity("kg", 10.0, 2);
        Element element = new Element("[at0001]", "test", dvQuantity);

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();

        ElementQLType elementQLType = new ElementQLType(rmObjectQLRegistry);

        GraphQLObjectType objectType = elementQLType.objectType();

        assertNotNull(objectType);

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(objectType)
                .mutation(objectType)
                .build();

        assertNotNull(schema);

//        "query " +
//                "{" +
////                        " archetype_node_id," +
////                        " name " +
////                            "{value}, " +
////                        "value " +
//                "{" +
//                " ... on DV_QUANTITY {magnitude, units}" +
//                " ... on ITEM {archetype_node_id, name {value}}" +
//                " ... on DV_BOOLEAN {value}" +
//                "}" +
//                "}"

        String query =  "query " +
                "{... on ITEM " +
                "   {archetype_node_id, name {value}}" +
                "value " +
                "{" +
                " ... on DV_QUANTITY {magnitude, units}" +
                " ... on DV_BOOLEAN {value}" +
                "}" +
                "}";

        Map results = (Map) new GraphQL(schema).execute(query, element).getData();

        assertEquals(((Map)results.get("value")).get("magnitude"), 10.0);
        assertEquals(((Map)results.get("value")).get("units"), "kg");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(results, System.out);

    }

    @Test
    public void testElementDvCodedText(){
        DvCodedText dvCodedText = new DvCodedText("coded_text", "local", "256");
        Element element = new Element("[at0001]", "testElement", dvCodedText);

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();

        ElementQLType elementQLType = new ElementQLType(rmObjectQLRegistry);

        GraphQLObjectType objectType = elementQLType.objectType();

        assertNotNull(objectType);

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(objectType)
                .mutation(objectType)
                .build();

        assertNotNull(schema);

        String query ="query " +
                "{... on ITEM " +
                "   {archetype_node_id, name {value}}" +
                "value " +
                "{" +
                " ... on DV_CODED_TEXT {value, defining_code {code_string, terminology_id{value}}}" +
                "}" +
                "}";

        Map results = (Map) new GraphQL(schema).execute(query, element).getData();

//        assertEquals(((Map)results.get("value")).get("magnitude"), 10.0);
//        assertEquals(((Map)results.get("value")).get("units"), "kg");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(results, System.out);

    }


    @Test
    public void testElementDvDateTime(){
        TimeZone timeZone = new SimpleTimeZone(3600000,"Europe/Paris");
        DvDateTime dvDateTime = new DvDateTime(2017, 12, 01, 13, 15, timeZone);
        Element element = new Element("[at0001]", "testElement", dvDateTime);

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();

        ElementQLType elementQLType = new ElementQLType(rmObjectQLRegistry);

        GraphQLObjectType objectType = elementQLType.objectType();

        assertNotNull(objectType);

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(objectType)
                .mutation(objectType)
                .build();

        assertNotNull(schema);

        Map results = (Map) new GraphQL(schema).execute(
                "query " +
                        "{... on ITEM " +
                        "   {archetype_node_id, name {value}}" +
                        "value " +
                        "{" +
                        " ... on DV_DATE_TIME {value}" +
                        "}" +
                        "}", element).getData();

//        assertEquals(((Map)results.get("value")).get("magnitude"), 10.0);
//        assertEquals(((Map)results.get("value")).get("units"), "kg");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(results, System.out);
    }

    @Test
    public void testElementDvDate(){
        TimeZone timeZone = new SimpleTimeZone(3600000,"Europe/Paris");
        DvDate dvDate = new DvDate(2017, 04, 06);
        Element element = new Element("[at0001]", "testElement", dvDate);

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();

        ElementQLType elementQLType = new ElementQLType(rmObjectQLRegistry);

        GraphQLObjectType objectType = elementQLType.objectType();

        assertNotNull(objectType);

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(objectType)
                .mutation(objectType)
                .build();

        assertNotNull(schema);

        Map results = (Map) new GraphQL(schema).execute(
                "query " +
                        "{... on ITEM " +
                        "   {archetype_node_id, name {value}}" +
                        "value " +
                        "{" +
                        " ... on DV_DATE {value}" +
                        "}" +
                        "}", element).getData();

//        assertEquals(((Map)results.get("value")).get("magnitude"), 10.0);
//        assertEquals(((Map)results.get("value")).get("units"), "kg");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(results, System.out);
    }

    @Test
    public void testElementDvTime(){
        TimeZone timeZone = new SimpleTimeZone(3600000,"Europe/Paris");
        DvTime dvTime = new DvTime(13, 45, 07, timeZone);
        Element element = new Element("[at0001]", "testElement", dvTime);

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();

        ElementQLType elementQLType = new ElementQLType(rmObjectQLRegistry);

        GraphQLObjectType objectType = elementQLType.objectType();

        assertNotNull(objectType);

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(objectType)
                .mutation(objectType)
                .build();

        assertNotNull(schema);

        Map results = (Map) new GraphQL(schema).execute(
                "query " +
                        "{... on ITEM " +
                        "   {archetype_node_id, name {value}}" +
                        "value " +
                        "{" +
                        " ... on DV_TIME {value}" +
                        "}" +
                        "}", element).getData();

//        assertEquals(((Map)results.get("value")).get("magnitude"), 10.0);
//        assertEquals(((Map)results.get("value")).get("units"), "kg");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(results, System.out);
    }
}