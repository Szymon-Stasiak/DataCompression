package org.example.service;

import java.util.ArrayList;
import lombok.Getter;

public class HeapPriorityQueue<K extends Comparable<K>> implements Queue<K> {
    @Getter
    protected int size = 0;

    protected ArrayList<K> heap;

    protected void swap(int i, int j) {
        if (i != j) {
            K temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }

    protected void heapify(int parentId) {
        int leftChildId = 2 * parentId + 1;
        int rightChildId = 2 * parentId + 2;
        int smallestValId = parentId;

        if (leftChildId < size && heap.get(leftChildId).compareTo(heap.get(smallestValId)) < 0) {
            smallestValId = leftChildId;
        }
        if (rightChildId < size && heap.get(rightChildId).compareTo(heap.get(smallestValId)) < 0) {
            smallestValId = rightChildId;
        }

        if (smallestValId != parentId) {
            swap(parentId, smallestValId);
            heapify(smallestValId);
        }
    }

    protected void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    public K poll() {
        if (size == 0) {
            return null;
        }
        K result = heap.getFirst();
        heap.removeFirst();
        size--;
        buildHeap();
        return result;
    }

    public void add(K node) {
        heap.add(node);
        size++;
        buildHeap();
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
//    public void writeHeap() {
//        for (int i = 0; i < size; i++) {
//            System.out.println(heap.get(i));
//        }
//    }
