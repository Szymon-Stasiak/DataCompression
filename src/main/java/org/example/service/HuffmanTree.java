package org.example.service;

import java.util.Iterator;
import lombok.Getter;
import lombok.NonNull;
import org.example.common.structures.Dictionary;
import org.example.common.structures.HuffmanNodePriorityQueue;
import org.example.common.structures.HuffmanTreeNode;
import org.example.common.tools.BFSIterator;

public abstract class HuffmanTree<K extends Comparable<K>, V> implements Iterable<HuffmanTreeNode<K, V>> {

    @Getter
    protected HuffmanTreeNode<K, V> root;

    private HuffmanNodePriorityQueue<K, V> heap;

    public HuffmanTree(HuffmanTreeNode<K, V> root) {
        this.root = root;
    }

    public HuffmanTree(Dictionary<K, V> dictionary) {
        heap = new HuffmanNodePriorityQueue<>(dictionary);
        if (heap.getSize() == 1) {
            handleWhenOnlyOneSequenceExist();
        } else {
            buildTree();
        }
    }

    // Todo latter
    public void handleWhenOnlyOneSequenceExist() {
        root = heap.poll();
        root.getWordNode();
    }

    private void buildTree() {
        while (heap.getSize() > 1) {
            HuffmanTreeNode<K, V> left = heap.poll();
            HuffmanTreeNode<K, V> right = heap.poll();
            HuffmanTreeNode<K, V> parent = new HuffmanTreeNode<>(left, right);
            heap.add(parent);
        }
        root = heap.poll();
    }

    @Override
    @NonNull public Iterator<HuffmanTreeNode<K, V>> iterator() {
        return new BFSIterator<>(root);
    }
}
