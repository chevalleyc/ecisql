package com.ethercis.graphql.datastructure;

import com.ethercis.graphql.commons.RmObjectQLRegistry;
import com.ethercis.graphql.composition.EvaluationQLType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.junit.Test;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.composition.content.entry.Evaluation;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.*;
import org.openehr.terminology.SimpleTerminologyService;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by christian on 3/20/2017.
 */
public class EvaluationQLTypeTest {

    @Test
    public void testSimpleEvaluation() {
        DvQuantity dvQuantity = new DvQuantity("kg", 10.0, 2);
        Element element = new Element("[at0003]", "testQuantity", dvQuantity);
        ItemStructure itemStructure = new ItemSingle("[at0002]", "testQuantity", element);
        UIDBasedID id = new HierObjectID(new UUID("123e4567-e89b-12d3-a456-426655440000"), "no extension");
        TemplateID templateID = new TemplateID("TEMPLATE");
        ArchetypeID archetypeID = new ArchetypeID("openEHR-EHR-EVALUATION.test.v1");
        Archetyped archetype_details = new Archetyped(archetypeID, templateID, "1.0.1");
        Evaluation evaluation = new Evaluation(id,
                "at[0001]",
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

        RmObjectQLRegistry rmObjectQLRegistry = new RmObjectQLRegistry();

        EvaluationQLType evaluationQLType = new EvaluationQLType(rmObjectQLRegistry);

        GraphQLObjectType objectType = evaluationQLType.objectType();

        assertNotNull(objectType);

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(objectType)
                .mutation(objectType)
                .build();

        assertNotNull(schema);

//        String query = "query " +
//                "{" +
//                    "... on ITEM_SINGLE {" +
//                        "{... on ITEM " +
//                        "   {archetype_node_id, name {value}}" +
//                        "value " +
//                        "{" +
//                        " ... on DV_QUANTITY {magnitude, units}" +
//                        " ... on DV_BOOLEAN {value}" +
//                        "}" +
//                        "}" +
//                    "}" +
//                "}";

        String query = "query {" +
                            "language {code_string}," +
                            "encoding {code_string}," +
                            "archetype_details {" +
                                "archetype_id {value}" +
                                "template_id {value}" +
                            "}," +
                            "data " +
                                "{... on ITEM_SINGLE " +
                                            "{" +
                                            "name{value}," +
                                            "archetype_node_id," +
                                            "item " +
                                            "{" +
                                                "name{value}," +
                                                "archetype_node_id," +
                                                "value " +
                                                "{... on DV_QUANTITY {magnitude, units}}" +
                                            "}" +
                                        "}" +
                                "}" +
                            "}";

        Map results = (Map) new GraphQL(schema).execute(query, evaluation).getData();

        assertNotNull(results);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(results, System.out);
    }
}