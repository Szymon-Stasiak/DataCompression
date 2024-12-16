package org.example.Structures.Basics;

public class CharChain implements Comparable<CharChain> {

    char[] chain;

    public CharChain(int size) {
        chain = new char[size];
    }

    public void add(char c) {
        for (int i = 0; i < chain.length; i++) {
            if (chain[i] == 0) {
                chain[i] = c;
                break;
            }
        }
    }

    public boolean isFull() {
        for (char c : chain) {
            if (c == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(CharChain o) {
        return this.toString().compareTo(o.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char c : chain) {
            sb.append(c);
        }
        return sb.toString();
    }
}
