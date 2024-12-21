package org.example.common.structures;

import static org.example.enums.Color.RED;

import lombok.Data;
import org.example.enums.Color;
import org.example.service.TreeNode;

@Data
public class WordNode<K extends Comparable<K>, V> implements Comparable<WordNode<K, V>>, TreeNode<WordNode<K, V>> {

    private int counter = 0;
    private K key;
    private V value;
    private Color color;
    private WordNode<K, V> left;
    private WordNode<K, V> right;

    public WordNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.color = RED;
        counter += 1;
    }

    public WordNode(K key) {
        this.key = key;
        this.counter += 1;
        this.color = RED;
    }

    public void increment() {
        counter++;
    }

    public boolean isRed() {
        return RED.equals(color);
    }

    @Override
    public int compareTo(WordNode<K, V> o) {
        return -key.compareTo(o.getKey());
    }

    //    @Override
    //    public String toString() {
    //        return (int) key + " - " + counter + " - " + code;
    //    }
}
