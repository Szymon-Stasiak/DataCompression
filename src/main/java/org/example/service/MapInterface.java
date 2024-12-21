package org.example.service;

public interface MapInterface<K extends Comparable<K>, V> {

    void add(K key);

    V getValue(K key);
}
