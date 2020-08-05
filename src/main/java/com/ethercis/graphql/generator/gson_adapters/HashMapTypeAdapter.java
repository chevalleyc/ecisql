package com.ethercis.graphql.generator.gson_adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by christian on 4/16/2017.
 */
public class HashMapTypeAdapter extends TypeAdapter<HashMap> {

        @Override
        public void write(JsonWriter out, HashMap linkedHashMap) throws IOException {
                out.beginObject();
                if (linkedHashMap.size() > 0) {
                        for (Object entry: linkedHashMap.entrySet()) {
                                if (entry instanceof Map.Entry) {

                                        out.name((String)((Map.Entry) entry).getKey());
                                        out.value((String)((Map.Entry) entry).getValue());
                                }
                        }
                }

        /* similar check for otherObject */
                out.endObject();
        }

        @Override
        public HashMap read(JsonReader in) throws IOException {
                // do something similar, but the other way around
                return null;
        }
}
