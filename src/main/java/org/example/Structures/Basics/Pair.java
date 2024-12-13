package org.example.Structures.Basics;

import lombok.Data;

@Data
public class Pair {

    private int counter;
    private char character;
    private String code;

    public Pair(char character, int counter) {
        this.character = character;
        this.counter = counter;
    }

    public Pair(char character) {
        this.character = character;
        this.counter += 1;
    }

    public void increment() {
        counter++;
    }

    //    @Override
    //    public String toString() {
    //        return (int) character + " - " + counter + " - " + code;
    //    }
}
