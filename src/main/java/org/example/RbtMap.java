package org.example;

import java.util.Iterator;
import org.example.Structures.Basics.WordNode;

public class RbtMap<K extends Comparable<K>> {

    private final RedBlackTree<K> tree;

    public <K> RbtMap() {
        tree = new RedBlackTree<>();
    }

    public void addAt(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Params (key, value) cannot be null.");
        }
        tree.put(key);
    }

    public WordNode getRoot() {
        return tree.getRoot();
    }

    public WordNode getPair(K key) {
        return tree.getPair(key);
    }

    public int getValue(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot get value by null key.");
        }
        return tree.get(key);
    }

    public Iterator<WordNode<K>> iterator() {
        return tree.iterator();
    }
}
