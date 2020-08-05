package com.ethercis.graphql.commons;

/**
 * Created by christian on 3/21/2017.
 */
public class IntrospectionQuery {

    public final static String introspectionQuery =
            "query IntrospectionQuery {\n" +
                    "    __schema {\n" +
                    "      queryType { name }\n" +
                    "      mutationType { name }\n" +
                    "      subscriptionType { name }\n" +
                    "      types {\n" +
                    "        ...FullType\n" +
                    "      }\n" +
                    "      directives {\n" +
                    "        name\n" +
                    "        description\n" +
                    "        locations\n" +
                    "        args {\n" +
                    "          ...InputValue\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }\n" +
                    "  fragment FullType on __Type {\n" +
                    "    kind\n" +
                    "    name\n" +
                    "    description\n" +
                    "    fields(includeDeprecated: true) {\n" +
                    "      name\n" +
                    "      description\n" +
                    "      args {\n" +
                    "        ...InputValue\n" +
                    "      }\n" +
                    "      type {\n" +
                    "        ...TypeRef\n" +
                    "      }\n" +
                    "      isDeprecated\n" +
                    "      deprecationReason\n" +
                    "    }\n" +
                    "    inputFields {\n" +
                    "      ...InputValue\n" +
                    "    }\n" +
                    "    interfaces {\n" +
                    "      ...TypeRef\n" +
                    "    }\n" +
                    "    enumValues(includeDeprecated: true) {\n" +
                    "      name\n" +
                    "      description\n" +
                    "      isDeprecated\n" +
                    "      deprecationReason\n" +
                    "    }\n" +
                    "    possibleTypes {\n" +
                    "      ...TypeRef\n" +
                    "    }\n" +
                    "  }\n" +
                    "  fragment InputValue on __InputValue {\n" +
                    "    name\n" +
                    "    description\n" +
                    "    type { ...TypeRef }\n" +
                    "    defaultValue\n" +
                    "  }\n" +
                    "  fragment TypeRef on __Type {\n" +
                    "    kind\n" +
                    "    name\n" +
                    "    ofType {\n" +
                    "      kind\n" +
                    "      name\n" +
                    "      ofType {\n" +
                    "        kind\n" +
                    "        name\n" +
                    "        ofType {\n" +
                    "          kind\n" +
                    "          name\n" +
                    "          ofType {\n" +
                    "            kind\n" +
                    "            name\n" +
                    "            ofType {\n" +
                    "              kind\n" +
                    "              name\n" +
                    "              ofType {\n" +
                    "                kind\n" +
                    "                name\n" +
                    "                ofType {\n" +
                    "                  kind\n" +
                    "                  name\n" +
                    "                }\n" +
                    "              }\n" +
                    "            }\n" +
                    "          }\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }";

public static final String queryFields =
        "query fieldsOfMetaInformation {\n" +
        "  __type(name: \"%s\") " +
        "{\n" +
        "    name\n" +
        "    fields{\n" +
        "        name\n" +
        "        type {\n" +
        "          name\n" +
        "          kind\n" +
        "          ofType {\n" +
        "            name\n" +
        "            kind\n" +
        "            ofType {\n" +
        "              name\n" +
        "              kind\n" +
        "              ofType {\n" +
        "                name\n" +
        "                kind\n" +
        "              }\n" +
        "            }\n" +
        "          }\n" +
        "        }\n" +
        "      }\n" +
        "  }\n" +
        "  }";
}
