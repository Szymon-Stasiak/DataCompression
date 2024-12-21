package org.example.common.structures;

// to do posible maybe change to K

import static java.sql.Types.NULL;

import lombok.Getter;

@Getter
public class CharChain implements Comparable<CharChain> {

    private final int[] chain;

    public CharChain(int size) {
        chain = new int[size];
    }

    public void add(int c) {
        for (int i = 0; i < chain.length; i++) {
            if (chain[i] == 0) {
                chain[i] = c;
                break;
            }
        }
    }

    public boolean isFull() {
        for (int c : chain) {
            if (c == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isNotEmpty() {
        for (int c : chain) {
            if (c != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(CharChain o) {
        return -this.toString().compareTo(o.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int c : chain) {
            if (c != NULL) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
