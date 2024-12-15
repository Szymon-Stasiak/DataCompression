package org.example.Structures.Basics;

import lombok.Data;
import org.example.Color;

import static org.example.Color.RED;

@Data
public class Pair<K extends Comparable<K>> {

    private int counter=0;
    private K key;
    private String code;
    private Color color;
    private Pair<K> left;
    private Pair<K> right;


    public Pair(K key, int counter) {
        this.key = key;
        this.counter = counter;
        this.color = RED;
    }

    public Pair(K key) {
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


    //    @Override
    //    public String toString() {
    //        return (int) key + " - " + counter + " - " + code;
    //    }
}
