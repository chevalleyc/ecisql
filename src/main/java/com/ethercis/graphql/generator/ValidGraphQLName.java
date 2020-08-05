package com.ethercis.graphql.generator;

/**
 * Created by christian on 4/14/2017.
 */
public class ValidGraphQLName {

    private final String toConvert;

    public ValidGraphQLName(String toConvert){
        this.toConvert = toConvert;
    }


    public String toString(){
        return toConvert
                .replaceAll("\\-", "_")
                .replaceAll("\\.", "_")
                .replaceAll(" ", "_")
                .replaceAll("&", "_")
                .replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "")
                .replaceAll(":", "")
                .replaceAll("\\?", "")
                .replaceAll("#", "");

    }
}
