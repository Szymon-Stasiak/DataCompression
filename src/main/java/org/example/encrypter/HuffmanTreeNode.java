package org.example.encrypter;

import lombok.Data;
import org.example.common.WordNode;
import org.example.enums.Color;

@Data
public class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {

    private HuffmanTreeNode left;
    private HuffmanTreeNode right;
    private WordNode wordNode;
    private int value;
    private Color color;

    public HuffmanTreeNode(WordNode wordNode) {
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
