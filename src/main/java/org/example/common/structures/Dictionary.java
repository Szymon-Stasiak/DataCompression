package org.example.common.structures;

import static org.example.enums.Color.BLACK;
import static org.example.enums.Color.RED;

import java.util.Iterator;
import lombok.NonNull;
import org.example.common.tools.BFSIterator;
import org.example.service.MapInterface;

public class Dictionary<K extends Comparable<K>,V> implements MapInterface<K,V>, Iterable<WordNode<K,V>> {

    private WordNode<K,V> root;
    private WordNode<K,V> toAdd;

    public WordNode<K,V> getNode(K key) {
        validateKey(key);
        WordNode<K,V> node = root;

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


    public V getValue(K key) {
        validateKey(key);
        WordNode<K,V> node = root;

        V result = null;

        while (node != null) {

            if (shouldCheckOnTheLeft(key, node)) {
                node = node.getLeft();

            } else if (shouldCheckOnTheRight(key, node)) {
                node = node.getRight();

            } else {
                result = node.getValue();
                break;
            }
        }
        return result;
    }

    public void addAt(K key) {
        validateKey(key);
        toAdd = new WordNode<>(key);
        root = put(root, key);
        root.setColor(BLACK);
    }

    public void addAt(WordNode<K,V> node) {
        validateKey(node.getKey());
        toAdd = node;
        root = put(root, node.getKey());
        root.setColor(BLACK);
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private boolean shouldCheckOnTheLeft(K key, WordNode<K,V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, WordNode<K,V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    // TODO delete Iteration
    private WordNode<K,V> put(WordNode<K,V> node, K key) {

        if (node == null) {
            return toAdd;
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

    private boolean isKeyBiggerThanNode(K key, WordNode<K,V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(WordNode<K,V> node, K key) {
        WordNode<K,V> rightChild = put(node.getRight(), key);
        node.setRight(rightChild);
    }

    private boolean isKeySmallerThanNode(K key, WordNode<K,V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(WordNode<K,V> node, K key) {
        WordNode<K,V> leftChild = put(node.getLeft(), key);
        node.setLeft(leftChild);
    }

    private WordNode<K,V> reorganizeTree(WordNode<K,V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private WordNode<K,V> rotateLeftIfNeeded(WordNode<K,V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private WordNode<K,V> rotateLeft(WordNode<K,V> node) {
        WordNode<K,V> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private WordNode<K,V> rotateRightIfNeeded(WordNode<K,V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private WordNode<K,V> rotateRight(WordNode<K,V> node) {
        WordNode<K,V> head = node.getLeft();
        node.setLeft(head.getRight());
        head.setRight(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private void changeColorsIfNeeded(WordNode<K,V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(WordNode<K,V> node) {
        swapColor(node);
        swapColor(node.getLeft());
        swapColor(node.getRight());
    }

    private void swapColor(WordNode<K,V> node) {
        if (node.isRed()) {
            node.setColor(BLACK);
        } else {
            node.setColor(RED);
        }
    }

    private boolean isBlack(WordNode<K,V> node) {
        return !isRed(node);
    }

    private boolean isRed(WordNode<K,V> node) {
        return node != null && node.isRed();
    }

    @Override
    @NonNull public Iterator<WordNode<K,V>> iterator() {
        return new BFSIterator<>(root);
    }

    public void writeTree() {
        for (WordNode<K,V> node : this) {
            System.out.println(node.getKey() + " - " + node.getValue());
        }
    }
}
