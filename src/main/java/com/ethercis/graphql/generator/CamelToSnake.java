package com.ethercis.graphql.generator;

/**
 * Created by christian on 4/13/2017.
 */
public class CamelToSnake {

    private String snakeIt;

    public CamelToSnake(String snakeIt){
        this.snakeIt = snakeIt;
    }

    public String toString(){
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < snakeIt.length(); i++) {
            if (Character.isUpperCase(snakeIt.charAt(i))) {
                if (i > 0) {
                    buffer.append('_');
                }
                buffer.append(Character.toLowerCase(snakeIt.charAt(i)));
            } else {
                buffer.append(snakeIt.charAt(i));
            }
        }
        return buffer.toString();
    }

}
