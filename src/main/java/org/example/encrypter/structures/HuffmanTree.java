package org.example.encrypter.structures;

import java.util.Iterator;
import lombok.Getter;
import org.example.common.structures.RedBlackTree;
import org.example.common.tools.BFSIterator;

public class HuffmanTree implements Iterable<HuffmanTreeNode> {

    @Getter
    private HuffmanTreeNode root;

    private final HuffmanNodePriorityQueue heap;

    public HuffmanTree(RedBlackTree redBlackTree) {
        heap = new HuffmanNodePriorityQueue(redBlackTree);
        if (heap.getSize() == 1) {
            handleWhenOnlyOneSequenceExist();
        } else {
            buildTree();
            generateCodes();
        }
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
    public Iterator<HuffmanTreeNode> iterator() {
        return new BFSIterator<>(root);
    }
}
