package org.example.service;

public interface Queue<T> {

    boolean isEmpty();

    T poll();

    void add(T element);
}
