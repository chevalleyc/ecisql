package com.ethercis.graphql.generator;

import com.ethercis.ehr.building.I_ContentBuilder;
import com.ethercis.ehr.knowledge.I_KnowledgeCache;
import com.ethercis.ehr.knowledge.KnowledgeCache;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.openehr.rm.composition.Composition;

import java.util.Properties;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by christian on 3/30/2017.
 */
public class QueryExampleTest {

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
    public void testGenerateExample() throws Exception {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        I_ContentBuilder contentBuilder = I_ContentBuilder.getInstance(knowledge, "ECIS EVALUATION TEST");
        Composition composition = contentBuilder.generateNewComposition();

        assertNotNull(composition);

        Exemplify queryExample = new Exemplify(ExemplifyMode.REQUIRED_FIELDS_ONLY);

        queryExample.generate(composition);

        System.out.println(queryExample.modelize("query composition \n", ""));

    }

    @Test
    public void testGenerateQueryCDRExample() throws Exception {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        I_ContentBuilder contentBuilder = I_ContentBuilder.getInstance(knowledge, "ECIS EVALUATION TEST");
        Composition composition = contentBuilder.generateNewComposition();

        assertNotNull(composition);

        Exemplify queryExample = new Exemplify(ExemplifyMode.REQUIRED_FIELDS_ONLY);

        queryExample.generate(composition);

        System.out.println(queryExample.modelize("query cdr {\n" +
                "\tcomposition (id:\"e54ffbfe-969e-4cae-bc5e-4850b298f5a4\")\n", "\n}", 2));

    }

}