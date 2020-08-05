package com.ethercis.nary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by christian on 3/29/2017.
 */
public class NaryTreeTest {

    @Test
    public void testNary(){

        //create root
        NaryTree tree = new NaryTree("ROOT");

        //add some children

        NaryNode child = new NaryNode("1.1");
        tree.addChild(child);

        child.addSibling("1.2");
        NaryNode sibling12 = child.addSibling("1.3");
        child.addSibling("1.4");
        child.addSibling("1.5");

        NaryNode sibling12child = sibling12.addChild("1.3.1");
        sibling12child.addSibling("1.3.2");
        sibling12child.addSibling("1.3.3");

        assertNotNull(tree);

        Walk walk = new Walk(tree).prettyPrint();

        String expression = walk.inOrder();

        System.out.print(expression);
    }

    @Test
    public void testAddList(){

        //create root
        NaryTree tree = new NaryTree("ROOT");

        //add some children

        List<String> strings = new ArrayList<>();
        strings.add("1.1");
        strings.add("1.2");
        strings.add("1.3");
        strings.add("1.4");
        strings.add("1.5");
        strings.add("1.6");

        tree.addAllChild(strings);

        assertNotNull(tree);

        Walk walk = new Walk(tree).prettyPrint();

        String expression = walk.inOrder();

        System.out.print(expression);
    }

    @Test
    public void testAddListNode(){

        //create root
        NaryTree tree = new NaryTree("ROOT");

        //add some children

        List<NaryNode<String>> nodes = new ArrayList<>();
        nodes.add(new NaryNode("1.1"));
        nodes.add(new NaryNode("1.2"));
        nodes.add(new NaryNode("1.3"));
        nodes.add(new NaryNode("1.4"));
        nodes.add(new NaryNode("1.5"));
        nodes.add(new NaryNode("1.6"));

        tree.addAllChildren(nodes);

        assertNotNull(tree);

        Walk walk = new Walk(tree).prettyPrint();

        String expression = walk.inOrder();

        System.out.print(expression);
    }

}