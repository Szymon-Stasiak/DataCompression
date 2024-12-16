package org.example.encrypter;

import org.example.service.Queue;

public class QueueBFS<T> implements Queue<T> {
    // TODO own implementation of Queue
    private java.util.Queue<T> elements = new java.util.LinkedList<>();

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public T poll() {
        return elements.poll();
    }

    @Override
    public void add(T element) {
        elements.add(element);
    }
}
