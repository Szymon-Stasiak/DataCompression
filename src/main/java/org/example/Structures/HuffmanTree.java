package org.example.Structures;

import org.example.RbtMap;
import org.example.RedBlackTree;
import org.example.Structures.Basics.Pair;
import org.example.Structures.Basics.TreeNode;

public class HuffmanTree {

    private TreeNode root;
    private final HeapPriorityQueue heap;

    public HuffmanTree(Alphabet alphabet) {
        heap = new HeapPriorityQueue(alphabet.removeEmpty());
        buildTree();
        generateCodes();
    }

    public HuffmanTree(RbtMap<Character> redBlackTree) {
        heap = new HeapPriorityQueue(redBlackTree);
        buildTree();
        generateCodes();
    }

    private void buildTree() {
        while (heap.getSize() > 1) {
            TreeNode left = heap.poll();
            TreeNode right = heap.poll();
            TreeNode parent = new TreeNode(left, right);
            heap.add(parent);
        }
        root = heap.poll();
    }

    private void generateCodes() {
        generateCodes(root, "");
    }
//nie ustawiony root dla rbt
    private void generateCodes(TreeNode node, String code) {
        if (node.getLeft() == null && node.getRight() == null) {
            node.getPair().setCode(code);
            return;
        }
        generateCodes(node.getRight(), code + "1");
        generateCodes(node.getLeft(), code + "0");
    }
}
