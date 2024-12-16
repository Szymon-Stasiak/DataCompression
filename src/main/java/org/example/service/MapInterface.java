package org.example.service;

public interface MapInterface<K extends Comparable<K>> {

    void addAt(K key);

    String getCode(K key);
}
