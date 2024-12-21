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

    protected HuffmanNodePriorityQueue<K, V> heap;

    public HuffmanTree(HuffmanTreeNode<K, V> root) {
        this.root = root;
        generateHuffmanCodes();
    }

    public HuffmanTree(Dictionary<K, V> dictionary) {
        heap = new HuffmanNodePriorityQueue<>(dictionary);
        if (heap.getSize() == 1) {
            root = handleWhenOnlyOneSequenceExist();
        } else {
            buildTree();
            generateHuffmanCodes();
        }
    }

    protected abstract void generateHuffmanCodes();

    public HuffmanTreeNode<K, V> handleWhenOnlyOneSequenceExist() {
        return null;
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
