package org.example.encrypter.structures;

public class Queue<T> implements org.example.service.QueueInterface<T> {
    // TODO own implementation of Queue
    private final java.util.Queue<T> elements = new java.util.LinkedList<>();

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

    public T peek() {
        return elements.peek();
    }
}
