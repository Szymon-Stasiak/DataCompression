package org.example.common.structures;

import static java.sql.Types.NULL;

public class CharChain implements Comparable<CharChain> {

    private int[] chain;

    public CharChain(int size) {
        chain = new int[size];
    }

    public CharChain(String s) {
        chain = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            chain[i] = s.charAt(i);
        }
    }

    public  int[] getChain(){
        return chain;
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

    public boolean isNorEmpty() {
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
