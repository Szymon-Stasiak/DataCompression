package org.example.Structures;

import lombok.Getter;
import org.example.Structures.Basics.Pair;

public class Alphabet {

    private final Pair[] alphabet;

    @Getter
    private int uniqueCharacters = 0;

    public Alphabet() {
        alphabet = new Pair[1112064];
    }

    public Alphabet(int size) {
        alphabet = new Pair[size];
    }

    public void add(int character) {
        if (alphabet[character] == null) {
            alphabet[character] = new Pair((char) character);
            uniqueCharacters++;
        } else {
            alphabet[character].increment();
        }
    }

    public void overwritePair(int counter, Pair pair) {
        alphabet[counter] = pair;
    }

    public Alphabet removeEmpty() {
        Alphabet newAlphabet = new Alphabet(uniqueCharacters);
        int counter = 0;
        for (Pair pair : alphabet) {
            if (pair != null) {
                newAlphabet.overwritePair(counter, pair);
                counter++;
            }
        }
        newAlphabet.uniqueCharacters = counter;
        return newAlphabet;
    }

    public Pair getPairAt(int index) {
        return alphabet[(char) index];
    }
    //
    //    @Override
    //    public String toString() {
    //        StringBuilder sb = new StringBuilder();
    //        for (Pair pair : alphabet) {
    //            if (pair != null) {
    //                sb.append(pair).append("\n");
    //            }
    //        }
    //        return sb.toString();
    //    }

}
