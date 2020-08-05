package com.ethercis.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by christian on 5/31/2017.
 */
public class QueryFromFile {

    String querypath;
    Charset encoding;
    String directory;

    public QueryFromFile(String querypath, Charset encoding, String directory) {
        this.querypath = querypath;
        this.encoding = encoding;
        this.directory = directory;
    }

    public String toString() {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(directory + "/" + querypath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded, encoding);
    }

}
