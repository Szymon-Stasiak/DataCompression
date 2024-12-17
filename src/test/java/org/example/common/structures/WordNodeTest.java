package org.example.common.structures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WordNodeTest {

    @Test
    void should_properlyCreateNode() {
        WordNode node = new WordNode('a');
        assertEquals(1, node.getCounter());
    }

    @Test
    void should_properlyCreateNodeWithCounter() {
        WordNode node = new WordNode('a', 5);
        assertEquals(5, node.getCounter());
    }

    @Test
    void should_properlyIncrement() {
        WordNode node = new WordNode('a');
        node.increment();
        assertEquals(2, node.getCounter());
    }

    @Test
    void should_properlyIncrement_WhenIncrementingTwice() {
        WordNode node = new WordNode('a');
        node.increment();
        node.increment();
        assertEquals(3, node.getCounter());
    }

    @Test
    void should_properlyCompareTo_WhenEqual() {
        WordNode node1 = new WordNode('a', 2);
        WordNode node2 = new WordNode('a', 2);
        assertEquals(0, node1.compareTo(node2));
    }

    @Test
    void should_properlyCompareTo_WhenGreater() {
        WordNode node1 = new WordNode('a', 3);
        WordNode node2 = new WordNode('b', 2);
        assertEquals(1, node1.compareTo(node2));
    }

    @Test
    void should_properlyCompareTo_WhenLess() {
        WordNode node1 = new WordNode('a', 1);
        WordNode node2 = new WordNode('b', 2);
        assertEquals(1, node1.compareTo(node2));
    }

    @Test
    void should_properlyCheckIfRed() {
        WordNode node = new WordNode('a');
        assertTrue(node.isRed());
    }
}
