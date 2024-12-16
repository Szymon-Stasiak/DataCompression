package org.example.Structures;

import org.example.RbtMap;
import org.example.Structures.Basics.HuffmanTreeNode;

public class HuffmanTree {

    private HuffmanTreeNode root;
    private final HuffmanHeapPriorityQueue heap;

    public HuffmanTree(RbtMap redBlackTree) {
        heap = new HuffmanHeapPriorityQueue(redBlackTree);
        buildTree();
        generateCodes();
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

    private void generateCodes(HuffmanTreeNode node, String code) {
        if (node.getLeft() == null && node.getRight() == null) {
            node.getWordNode().setCode(code);
            return;
        }
        generateCodes(node.getRight(), code + "1");
        generateCodes(node.getLeft(), code + "0");
    }
}
