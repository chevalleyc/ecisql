package com.ethercis.nary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christian on 3/29/2017.
 */
public class NaryNode<T> {
    T data;
    List<NaryNode<T>> siblings;
    List<NaryNode<T>> children;

    public NaryNode(T data) {
        this.data = data;
        this.siblings = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public List<NaryNode<T>> getChildren() {
        return children;
    }

    public List<NaryNode<T>> getSiblings() {
        return siblings;
    }

    public NaryNode addChild(T data) {

        if (getChildren() == null) {
            children = new ArrayList<NaryNode<T>>();
        }

        NaryNode child = new NaryNode(data);

        children.add(child);

        return child;
    }

    public NaryNode addChild(NaryNode child) {

        if (getChildren() == null) {
            children = new ArrayList<NaryNode<T>>();
        }

        children.add(child);

        return child;
    }


    public void addAllChild(List<T> _children) {
        if (getChildren() == null) {
            children = new ArrayList<NaryNode<T>>();
        }

        NaryNode<T> child = new NaryNode(_children.get(0));

        children.add(child);

        //add the remainder as siblings
        for (T data: _children.subList(1, _children.size())){
            child.addSibling(data);
        }
    }

    public void addAllChildren(List<NaryNode<T>> _children) {
        if (getChildren() == null) {
            children = new ArrayList<NaryNode<T>>();
        }

        children.add(_children.get(0));

        for (NaryNode<T> node: _children.subList(1, _children.size())){
            _children.get(0).addSibling(node);
        }

    }

    public NaryNode<T> addSibling(T data) {

        if (this == null) {
            throw new IllegalArgumentException("Null n-ary node");
        }

        NaryNode newSibling = new NaryNode(data);

        siblings.add(newSibling);

        return siblings.get(siblings.size() - 1);
    }

    public NaryNode<T> addSibling(NaryNode<T> node) {

        if (this == null) {
            throw new IllegalArgumentException("Null n-ary node");
        }

        siblings.add(node);

        return siblings.get(siblings.size() - 1);
    }

    public int numberOfChildren() {
        if (children == null)
            return 0;
        else
            return children.size();
    }

    public int numberOfSibling() {
        if (siblings == null)
            return 0;
        else
            return siblings.size();
    }

    public T walk(NaryNode<T> node){

        if (node == null)
            return null;

        else {
            if (node.numberOfChildren() > 0){
                walk(node.getChildren().get(0));
            }
            if (node.numberOfSibling() > 0){
                for (NaryNode<T> sibling: node.siblings){
                    walk(sibling);
                }
            }
            return node.getData();
        }
    }

    public void deleteNamedChild(T data) {
        //traverse the nodes and delete the one equals data
        List<NaryNode<T>> children = getChildren();
        if (!children.get(0).getData().equals(data)){
            for (int i = children.get(0).getSiblings().size() - 1; i >= 0; i--){
                NaryNode<T> sibling = children.get(0).getSiblings().get(i);
                if (sibling.getData().equals(data))
                    children.get(0).getSiblings().remove(i);
            }
        }
        else {
            //shift the siblings
//            children.remove(0);
//            children.add()
        }

    }

    public boolean isEmpty(){
        return (children == null) && (siblings.size() == 0);
    }

    public void setData(T data) {
        this.data = data;
    }
}
