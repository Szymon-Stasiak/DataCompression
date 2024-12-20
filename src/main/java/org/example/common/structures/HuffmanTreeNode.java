package org.example.common.structures;

import lombok.Data;
import org.example.enums.Color;
import org.example.service.TreeNode;

@Data
public class HuffmanTreeNode implements Comparable<HuffmanTreeNode>, TreeNode<HuffmanTreeNode> {

    private HuffmanTreeNode left;
    private HuffmanTreeNode right;
    private WordNode<CharChain> wordNode;
    private int value;
    private Color color;

    public HuffmanTreeNode() {}

    public HuffmanTreeNode(WordNode<CharChain> wordNode) {
        this.wordNode = wordNode;
        this.value = wordNode.getCounter();
    }

    public HuffmanTreeNode(HuffmanTreeNode left, HuffmanTreeNode right) {
        this.left = left;
        this.right = right;
        this.value = left.getValue() + right.getValue();
    }

    @Override
    public int compareTo(HuffmanTreeNode o) {
        return Integer.compare(value, o.getValue());
    }
}
