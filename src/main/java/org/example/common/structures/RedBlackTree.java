package org.example.common.structures;

import static org.example.enums.Color.BLACK;
import static org.example.enums.Color.RED;

import java.util.Iterator;
import org.example.encrypter.structures.QueueBFS;
import org.example.service.MapInterface;

public class RedBlackTree<K extends Comparable<K>> implements MapInterface<K>, Iterable<WordNode<K>> {

    private WordNode<K> root;

    public WordNode<K> getNode(K key) {
        validateKey(key);
        WordNode<K> node = root;

        while (node != null) {

            if (shouldCheckOnTheLeft(key, node)) {
                node = node.getLeft();

            } else if (shouldCheckOnTheRight(key, node)) {
                node = node.getRight();

            } else {
                break;
            }
        }
        return node;
    }

    public String getCode(K key) {
        validateKey(key);
        WordNode<K> node = root;

        String result = "";

        while (node != null) {

            if (shouldCheckOnTheLeft(key, node)) {
                node = node.getLeft();

            } else if (shouldCheckOnTheRight(key, node)) {
                node = node.getRight();

            } else {
                result = node.getCode();
                break;
            }
        }
        return result;
    }

    public void addAt(K key) {
        validateKey(key);
        root = put(root, key);
        root.setColor(BLACK);
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private boolean shouldCheckOnTheLeft(K key, WordNode<K> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, WordNode<K> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private WordNode<K> put(WordNode<K> node, K key) {

        if (node == null) {
            return new WordNode<>(key);
        }

        if (isKeyBiggerThanNode(key, node)) {
            putOnTheRight(node, key);

        } else if (isKeySmallerThanNode(key, node)) {
            putOnTheLeft(node, key);

        } else {
            node.increment();
        }

        node = reorganizeTree(node);

        return node;
    }

    private boolean isKeyBiggerThanNode(K key, WordNode<K> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(WordNode<K> node, K key) {
        WordNode<K> rightChild = put(node.getRight(), key);
        node.setRight(rightChild);
    }

    private boolean isKeySmallerThanNode(K key, WordNode<K> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(WordNode<K> node, K key) {
        WordNode<K> leftChild = put(node.getLeft(), key);
        node.setLeft(leftChild);
    }

    private WordNode<K> reorganizeTree(WordNode<K> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private WordNode<K> rotateLeftIfNeeded(WordNode<K> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private WordNode<K> rotateLeft(WordNode<K> node) {
        WordNode<K> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private WordNode<K> rotateRightIfNeeded(WordNode<K> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private WordNode<K> rotateRight(WordNode<K> node) {
        WordNode<K> head = node.getLeft();
        node.setLeft(head.getRight());
        head.setRight(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private void changeColorsIfNeeded(WordNode<K> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(WordNode<K> node) {
        swapColor(node);
        swapColor(node.getLeft());
        swapColor(node.getRight());
    }

    private void swapColor(WordNode<K> node) {
        if (node.isRed()) {
            node.setColor(BLACK);
        } else {
            node.setColor(RED);
        }
    }

    private boolean isBlack(WordNode<K> node) {
        return !isRed(node);
    }

    private boolean isRed(WordNode<K> node) {
        return node != null && node.isRed();
    }

    @Override
    public Iterator<WordNode<K>> iterator() {
        return new BFSIterator();
    }

    private class BFSIterator implements Iterator<WordNode<K>> {
        private final QueueBFS<WordNode<K>> queue;

        public BFSIterator() {
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
        public WordNode<K> next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements in the tree");
            }

            WordNode<K> currentNode = queue.poll();

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }

            return currentNode;
        }
    }
}
