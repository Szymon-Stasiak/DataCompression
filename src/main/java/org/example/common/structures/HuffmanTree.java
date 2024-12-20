package org.example.common.structures;

import java.util.Iterator;
import lombok.Getter;
import lombok.NonNull;
import org.example.common.tools.BFSIterator;

public class HuffmanTree<K extends Comparable<K>,V> implements Iterable<HuffmanTreeNode<K,V>> {

    @Getter
    private HuffmanTreeNode<K,V> root;

    private HuffmanNodePriorityQueue<K,V> heap;

    public HuffmanTree(Dictionary<K,V> dictionary) {
        heap = new HuffmanNodePriorityQueue<>(dictionary);
        if (heap.getSize() == 1) {
            handleWhenOnlyOneSequenceExist();
        } else {
            buildTree();
            generateCodes();
        }
    }

    public HuffmanTree(HuffmanTreeNode<K,V> root) {
        this.root = root;
        generateCodes();
    }

    //Todo latter
    public void handleWhenOnlyOneSequenceExist() {
        root = heap.poll();
        root.getWordNode();
    }

    private void buildTree() {
        while (heap.getSize() > 1) {
            HuffmanTreeNode<K,V> left = heap.poll();
            HuffmanTreeNode<K,V> right = heap.poll();
            HuffmanTreeNode<K,V> parent = new HuffmanTreeNode<>(left, right);
            heap.add(parent);
        }
        root = heap.poll();
    }

    private void generateCodes() {
        generateCodes(root, "");
    }

    // TODO delete recursion
    private void generateCodes(HuffmanTreeNode node, String code) {
        if (node.getLeft() == null && node.getRight() == null) {
            node.getWordNode().setValue(code);
            return;
        }
        generateCodes(node.getRight(), code + "1");
        generateCodes(node.getLeft(), code + "0");
    }

    @Override
    @NonNull public Iterator<HuffmanTreeNode<K,V>> iterator() {
        return new BFSIterator<>(root);
    }
}
