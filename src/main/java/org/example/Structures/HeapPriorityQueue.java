package org.example.Structures;

import java.util.ArrayList;
import lombok.Getter;
import org.example.Structures.Basics.TreeNode;

public class HeapPriorityQueue {
    @Getter
    private int size;

    private final ArrayList<TreeNode> heap;

    public HeapPriorityQueue(Alphabet alphabet) {
        heap = new ArrayList<>();
        size = alphabet.getUniqueCharacters();
        for (int i = 0; i < size; i++) {
            heap.add(new TreeNode(alphabet.getPairAt(i)));
        }
        buildHeap();
    }

    private void swap(int i, int j) {
        if (i != j) {
            TreeNode temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }

    protected void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(i);
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

    public TreeNode poll() {
        if (size == 0) {
            return null;
        }
        TreeNode result = heap.getFirst();
        heap.removeFirst();
        size--;
        buildHeap();
        return result;
    }

    public void add(TreeNode node) {
        heap.add(node);
        size++;
        buildHeap();
    }

    //    public void writeHeap() {
    //        for (int i = 0; i < size; i++) {
    //            System.out.println(heap.get(i));
    //        }
    //    }

}
