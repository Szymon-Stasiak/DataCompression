package org.example;

import lombok.Getter;
import org.example.Structures.Basics.Pair;

import static org.example.Color.RED;
import static org.example.Color.BLACK;

public class RedBlackTree<K extends Comparable<K>> {

    @Getter
    private Pair<K> root;

    public Pair<K> getPair(K key) {
        validateKey(key);
        Pair<K> pair = root;

        while (pair != null) {

            if (shouldCheckOnTheLeft(key, pair)) {
                pair = pair.getLeft();

            } else if (shouldCheckOnTheRight(key, pair)) {
                pair = pair.getRight();

            } else {
                break;
            }
        }
        return pair;
    }

    public int get(K key) {
        validateKey(key);
        Pair<K> pair = root;

        int result = 0;

        while (pair != null) {

            if (shouldCheckOnTheLeft(key, pair)) {
                pair = pair.getLeft();

            } else if (shouldCheckOnTheRight(key, pair)) {
                pair = pair.getRight();

            } else {
                result = pair.getCounter();
                break;
            }
        }
        return result;
    }

    public void put(K key) {
        validateKey(key);
        root = put(root, key);
        root.setColor(BLACK);
    }


    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private boolean shouldCheckOnTheLeft(K key, Pair<K> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, Pair<K> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void validateParams(K key, int value) {
        if (key == null ) {
            throw new IllegalArgumentException("Input params (key, value) cannot be null.");
        }
    }

    private Pair<K>  put(Pair<K> node, K key) {

        if (node == null) {
            return new Pair<>(key);
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

    private boolean isKeyBiggerThanNode(K key, Pair<K> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(Pair<K> node, K key) {
        Pair<K> rightChild = put(node.getRight(), key);
        node.setRight(rightChild);
    }

    private boolean isKeySmallerThanNode(K key, Pair<K> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(Pair<K> node, K key) {
        Pair<K> leftChild = put(node.getLeft(), key);
        node.setLeft(leftChild);
    }

    private Pair<K> reorganizeTree(Pair<K> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private Pair<K> rotateLeftIfNeeded(Pair<K> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private Pair<K> rotateLeft(Pair<K> node) {
        Pair<K> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private Pair<K> rotateRightIfNeeded(Pair<K> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private Pair<K> rotateRight(Pair<K> node) {
        Pair<K> head = node.getLeft();
        node.setLeft(head.getRight());
        head.setRight(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private void changeColorsIfNeeded(Pair<K> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(Pair<K> node) {
        swapColor(node);
        swapColor(node.getLeft());
        swapColor(node.getRight());
    }

    private void swapColor(Pair<K> node) {
        if (node.isRed()) {
            node.setColor(BLACK);
        } else {
            node.setColor(RED);
        }
    }

    private boolean isBlack(Pair<K> node) {
        return !isRed(node);
    }

    private boolean isRed(Pair<K> node) {
        return node != null && node.isRed();
    }
}
