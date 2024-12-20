package org.example.common.tools;

import java.util.Iterator;
import org.example.encrypter.structures.Queue;
import org.example.service.TreeNode;

public class BFSIterator<T extends TreeNode<T>> implements Iterator<T> {

    private final Queue<T> queue;

    public BFSIterator(T root) {
        queue = new Queue<>();
        if (root != null) {
            queue.add(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public T next() {
        T current = queue.poll();
        if (current.getLeft() != null) {
            queue.add(current.getLeft());
        }
        if (current.getRight() != null) {
            queue.add(current.getRight());
        }
        return current;
    }
}
