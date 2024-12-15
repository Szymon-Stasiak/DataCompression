package org.example;

import org.example.Structures.Basics.Pair;
import org.example.service.MapInterface;

import java.util.Iterator;

public class RbtMap<K extends Comparable<K>>  {

    private final RedBlackTree<K> tree;

    public <K> RbtMap() {
        tree = new RedBlackTree<>();
    }


    public void addAt(K key) {
        if (key == null ) {
            throw new IllegalArgumentException("Params (key, value) cannot be null.");
        }
        tree.put(key);
    }

    public Pair getRoot() {
        return  tree.getRoot();
    }

    public Pair getPair(K key) {
        return tree.getPair(key);
    }

    public int getValue(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot get value by null key.");
        }
        return tree.get(key);
    }


}
