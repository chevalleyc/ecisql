package com.ethercis.nary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * Created by christian on 3/29/2017.
 */
public class Walk {

    NaryTree root;
    StringBuffer expression = new StringBuffer();
    final String OPEN_CURL = "{";
    final String CLOSE_CURL = "}";
    final String SEPARATOR = "\n";

    boolean isPretty = false;

    Deque<String> tabStack = new ArrayDeque<>();

    public Walk(NaryTree root) {
        this.root = root;
    }

    public Walk prettyPrint(){
        isPretty = true;
        return this;
    }

    private String tabs(){
        if (!isPretty)
            return "";

        Iterator<String> stringIterator = tabStack.iterator();

        String tabs = String.join("", tabStack);
        return tabs;
    }

    private String separator(){
        if (!isPretty)
            return "";
        else
            return SEPARATOR;

    }

    private String openCurl(){
        return OPEN_CURL;
    }

    private String closeCurl(){
        return CLOSE_CURL;
    }

    private void pushTab(){
        if (isPretty)
            tabStack.push("\t");
    }

    private void popTab(){
        if (isPretty)
            tabStack.pop();
    }

    public String inOrder() {
        NaryNode<String> treeNode = root.getNaryTree();
        expression.append(openCurl());
        pushTab();
        walk(treeNode);
        popTab();
        expression.append(closeCurl());
        return expression.toString();
    }

    public void walk(NaryNode<String> node) {

        if (node == null)
            return;

        else {
            expression.append(tabs()+node.getData());
            expression.append(separator());

            if (node.numberOfChildren() > 0) {
                expression.append(tabs()+openCurl()+separator());
                pushTab();
//                tabStack.push("\t");
                walk(node.getChildren().get(0));
                popTab();
//                tabStack.pop();
                expression.append(tabs()+closeCurl()+separator());
            }
            if (node.numberOfSibling() > 0) {
                for (NaryNode<String> sibling : node.siblings) {
                    walk(sibling);
                }
            }
        }
    }
}
