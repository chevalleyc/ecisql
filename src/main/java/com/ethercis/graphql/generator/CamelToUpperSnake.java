package com.ethercis.graphql.generator;

/**
 * Created by christian on 4/5/2017.
 */
public class CamelToUpperSnake {

    private final String aString;

    public CamelToUpperSnake(String aString) {
        this.aString = aString;
    }

    public String toString(){
        StringBuilder buffer = new StringBuilder();
        for(int i = 0; i < aString.length(); i++) {
            if(Character.isUpperCase(aString.charAt(i))) {
                if(i > 0) {
                    buffer.append('_');
                }
                buffer.append(Character.toLowerCase(aString.charAt(i)));
            } else {
                buffer.append(aString.charAt(i));
            }
        }
        return buffer.toString().toUpperCase();
    }
}
