package org.example.common.structures;

import org.example.logger.Log;
import org.example.service.HuffmanTree;

public class HuffmanTreeCodesForKeys<V> extends HuffmanTree<String, V> {

    public HuffmanTreeCodesForKeys(HuffmanTreeNode<String, V> HuffmanTreeNode) {
        super(HuffmanTreeNode);
    }

    @Override
    protected void generateHuffmanCodes() {
        generateKeys(root, "");
    }

    private void generateKeys(HuffmanTreeNode<String, V> node, String code) {
        Log.info("Generating keys");
        if (node.getLeft() == null && node.getRight() == null) {
            node.getWordNode().setKey(code);
            return;
        }
        generateKeys(node.getRight(), code + "1");
        generateKeys(node.getLeft(), code + "0");
    }
}
