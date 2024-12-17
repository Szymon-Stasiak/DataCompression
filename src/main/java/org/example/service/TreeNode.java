package org.example.service;

public interface TreeNode<T> {
    T getLeft();

    T getRight();

    void setLeft(T left);

    void setRight(T right);
}
