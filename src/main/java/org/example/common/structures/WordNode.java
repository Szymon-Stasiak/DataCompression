package org.example.common.structures;

import static org.example.enums.Color.RED;

import lombok.Data;
import org.example.enums.Color;
import org.example.service.TreeNode;

@Data
public class WordNode<K extends Comparable<K>> implements Comparable<WordNode<K>>, TreeNode<WordNode<K>> {

    private int counter = 0;
    private K key;
    private String code;
    private Color color;
    private WordNode<K> left;
    private WordNode<K> right;

    public WordNode(K key, int counter) {
        this.key = key;
        this.counter = counter;
        this.color = RED;
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
    public int compareTo(WordNode<K> o) {
        return -key.compareTo(o.getKey());
    }



    //    @Override
    //    public String toString() {
    //        return (int) key + " - " + counter + " - " + code;
    //    }
}
