package org.example.common.structures;

import lombok.Data;
import org.example.enums.Color;
import org.example.service.TreeNode;

@Data
public class HuffmanTreeNode<K extends Comparable<K>, V>
        implements Comparable<HuffmanTreeNode<K, V>>, TreeNode<HuffmanTreeNode<K, V>> {

    private HuffmanTreeNode<K, V> left;
    private HuffmanTreeNode<K, V> right;
    private WordNode<K, V> wordNode;
    private int value;
    private Color color;

    public HuffmanTreeNode() {}

    public HuffmanTreeNode(WordNode<K, V> wordNode) {
        this.wordNode = wordNode;
        this.value = wordNode.getCounter();
    }

    public HuffmanTreeNode(K key, V value) {
        this.wordNode = new WordNode<>(key, value);
    }

    public HuffmanTreeNode(HuffmanTreeNode<K, V> left, HuffmanTreeNode<K, V> right) {
        this.left = left;
        this.right = right;
        this.value = left.getValue() + right.getValue();
    }

    @Override
    public int compareTo(HuffmanTreeNode o) {
        return Integer.compare(value, o.getValue());
    }
}
