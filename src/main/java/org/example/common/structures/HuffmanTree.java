package org.example.common.structures;

import java.util.Iterator;
import lombok.Getter;
import lombok.NonNull;
import org.example.common.tools.BFSIterator;

public class HuffmanTree implements Iterable<HuffmanTreeNode> {

    @Getter
    private HuffmanTreeNode root;

    private HuffmanNodePriorityQueue heap;

    public HuffmanTree(Dictionary<CharChain> dictionary) {
        heap = new HuffmanNodePriorityQueue(dictionary);
        if (heap.getSize() == 1) {
            handleWhenOnlyOneSequenceExist();
        } else {
            buildTree();
            generateCodes();
        }
    }

    public HuffmanTree(HuffmanTreeNode root) {
        this.root = root;
        generateCodes();
    }

    public void handleWhenOnlyOneSequenceExist() {
        root = heap.poll();
        root.getWordNode().setCode("0");
    }

    private void buildTree() {
        while (heap.getSize() > 1) {
            HuffmanTreeNode left = heap.poll();
            HuffmanTreeNode right = heap.poll();
            HuffmanTreeNode parent = new HuffmanTreeNode(left, right);
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
            node.getWordNode().setCode(code);
            return;
        }
        generateCodes(node.getRight(), code + "1");
        generateCodes(node.getLeft(), code + "0");
    }

    @Override
    @NonNull public Iterator<HuffmanTreeNode> iterator() {
        return new BFSIterator<>(root);
    }
}
