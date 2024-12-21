package org.example.common.structures;

import org.example.service.HuffmanTree;

public class HuffmanTreeCodesForValues<K extends Comparable<K>> extends HuffmanTree<K, String> {

    public HuffmanTreeCodesForValues(Dictionary<K, String> dictionary) {
        super(dictionary);
    }

    @Override
    protected void generateHuffmanCodes() {
        generateCodes(super.root, "");
    }

    @Override
    public HuffmanTreeNode<K, String> handleWhenOnlyOneSequenceExist() {
        HuffmanTreeNode<K, String> newRoot = new HuffmanTreeNode<>();
        HuffmanTreeNode<K, String> fakeNode = new HuffmanTreeNode<>();
        HuffmanTreeNode<K, String> node = super.heap.poll();
        newRoot.setLeft(fakeNode);
        newRoot.setRight(node);
        node.getWordNode().setValue("1");
        fakeNode.setWordNode(new WordNode<>(null, "0"));
        return newRoot;
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
