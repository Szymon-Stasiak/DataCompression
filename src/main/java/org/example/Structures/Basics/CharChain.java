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

//    @Override
//    public int compareTo( Object o) {
//
//        //check if no null
//        if (o == null) {
//            throw new IllegalArgumentException("Cannot compare with null.");
//        }
//        CharChain other = (CharChain) o;
//        for (int i = 0; i < chain.length; i++) {
//            if (chain[i] != other.chain[i]) {
//                return chain[i] - other.chain[i];
//            }
//        }
//        return 0;
//    }

    @Override
    public int compareTo(CharChain o) {
        // compare as a stings alphabetically
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
