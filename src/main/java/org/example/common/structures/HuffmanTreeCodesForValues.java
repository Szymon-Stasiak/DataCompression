package org.example.common.structures;

import org.example.service.HuffmanTree;

public class HuffmanTreeCodesForValues<K extends Comparable<K>> extends HuffmanTree<K, String> {

    public HuffmanTreeCodesForValues(Dictionary<K, String> dictionary) {
        super(dictionary);
        generateCodes();
    }

    private void generateCodes() {
        generateCodes(super.root, "");
    }

    private void generateCodes(HuffmanTreeNode<K, String> node, String code) {
        if (node.getLeft() == null && node.getRight() == null) {
            node.getWordNode().setValue(code);
            return;
        }
        generateCodes(node.getRight(), code + "1");
        generateCodes(node.getLeft(), code + "0");
    }
}
