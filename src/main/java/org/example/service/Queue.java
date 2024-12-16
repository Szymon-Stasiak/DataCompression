package org.example.service;

public interface Queue<T> {

    public boolean isEmpty();

    public T poll();

    public void add(T element);
}
