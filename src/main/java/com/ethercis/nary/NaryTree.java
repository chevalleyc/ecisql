package com.ethercis.nary;

import java.util.List;

/**
 * This class holds a GraphQL query structure (it is not json btw)
 * Created by christian on 3/29/2017.
 */
public class NaryTree {

    private NaryNode naryTree;

    public NaryTree(String data){
        naryTree = new NaryNode(data);
    }

    public NaryTree(NaryNode node){
        naryTree = node;
    }

    public NaryNode addChild(String data){
        return naryTree.addChild(data);
    }

    public NaryNode addChild(NaryNode child){
        return naryTree.addChild(child);
    }

    public NaryNode getNaryTree() {
        return naryTree;
    }

    public String walk(){
        return (String) naryTree.walk(naryTree);
    }

    public void addAllChild(List<String> children) {
        naryTree.addAllChild(children);
    }

    public void addAllChildren(List<NaryNode<String>> nodes) {
        naryTree.addAllChildren(nodes);
    }

    public NaryNode child(){
        return child(0);
    }

    public NaryNode child(int i){
        if (naryTree.getChildren() == null)
            return null;
        if (i >=  childSize())
            return null;
        if (!(naryTree.getChildren().get(i) instanceof NaryNode))
            return null;
        return (NaryNode)naryTree.getChildren().get(i);
    }

    public Integer childSize(){
        return naryTree.getChildren().size();
    }
}
