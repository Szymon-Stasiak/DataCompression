package org.example.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PairTest {

    @Test
    void should_properlyCreatePair() {
        WordNode pair = new WordNode('a');
        assertEquals(1, pair.getCounter());
    }

    @Test
    void should_properlyCreatePairWithCounter() {
        WordNode pair = new WordNode('a', 5);
        assertEquals(5, pair.getCounter());
    }

    @Test
    void should_properlyIncrement() {
        WordNode pair = new WordNode('a');
        pair.increment();
        assertEquals(2, pair.getCounter());
    }

    @Test
    void should_properlyIncrement_WhenIncrementingTwice() {
        WordNode pair = new WordNode('a');
        pair.increment();
        pair.increment();
        assertEquals(3, pair.getCounter());
    }
}
