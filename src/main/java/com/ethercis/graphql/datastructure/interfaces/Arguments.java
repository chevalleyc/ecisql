package com.ethercis.graphql.datastructure.interfaces;

import java.util.Map;

/**
 * Created by christian on 4/11/2017.
 */
public class Arguments {

    private Map<String, Object> arguments;

    public Arguments(Map<String, Object> args){
        this.arguments = args;
    }

    public boolean isNull(String entry){
        if (!arguments.containsKey(entry))
            return true;
        if (arguments.get(entry) == null)
            return true;
        return false;
    }

    public boolean hasSetArguments(){
        for (Object value: arguments.values()){
            if (value != null)
                return true;
        }
        return false;
    }
}
