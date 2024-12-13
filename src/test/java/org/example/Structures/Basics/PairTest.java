package org.example.Structures.Basics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PairTest {

    @Test
    void should_properlyCreatePair() {
        Pair pair = new Pair('a');
        assertEquals(1, pair.getCounter());
    }

    @Test
    void should_properlyCreatePairWithCounter() {
        Pair pair = new Pair('a', 5);
        assertEquals(5, pair.getCounter());
    }

    @Test
    void should_properlyIncrement() {
        Pair pair = new Pair('a');
        pair.increment();
        assertEquals(2, pair.getCounter());
    }

    @Test
    void should_properlyIncrement_WhenIncrementingTwice() {
        Pair pair = new Pair('a');
        pair.increment();
        pair.increment();
        assertEquals(3, pair.getCounter());
    }
}
