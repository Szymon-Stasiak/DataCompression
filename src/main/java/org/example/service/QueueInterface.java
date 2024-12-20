package org.example.service;

public interface QueueInterface<T> {


    boolean isEmpty();

    T poll();

    void add(T element);
}


