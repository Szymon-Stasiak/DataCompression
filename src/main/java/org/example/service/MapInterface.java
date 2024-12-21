package org.example.service;

public interface MapInterface<K extends Comparable<K>,V> {

    void addAt(K key);

    V getValue(K key);
}
