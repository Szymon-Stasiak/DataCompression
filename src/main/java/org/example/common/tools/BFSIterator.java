package org.example.common.tools;

import java.util.Iterator;
import org.example.encrypter.structures.QueueBFS;
import org.example.service.TreeNode;

public class BFSIterator<T extends TreeNode<T>> implements Iterator<T> {

    private final QueueBFS<T> queue;
    private T current;

    public BFSIterator(T root) {
        queue = new QueueBFS<>();
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
        current = queue.poll();
        if (current.getLeft() != null) {
            queue.add(current.getLeft());
        }
        if (current.getRight() != null) {
            queue.add(current.getRight());
        }
        return current;
    }
}
