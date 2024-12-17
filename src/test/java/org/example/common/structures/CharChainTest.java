package org.example.common.structures;

import static org.junit.jupiter.api.Assertions.*;
import static utils.advancedGetters.CharChainAdvancedGetter.getCharChain;

import org.junit.jupiter.api.Test;

class CharChainTest {

    @Test
    void should_properlyCreateCharChain() {
        CharChain charChain = new CharChain(3);
        assertEquals(3, getCharChain(charChain).length);
    }

    @Test
    void should_properlyAddChar() {
        CharChain charChain = new CharChain(3);
        charChain.add('a');
        assertEquals('a', getCharChain(charChain)[0]);
    }

    @Test
    void should_properlyAddChar_WhenAddingTwice() {
        CharChain charChain = new CharChain(3);
        charChain.add('a');
        charChain.add('b');
        assertEquals('a', getCharChain(charChain)[0]);
        assertEquals('b', getCharChain(charChain)[1]);
        assertEquals(3, getCharChain(charChain).length);
    }

    @Test
    void should_properlyAddChar_WhenAddingThreeTimes() {
        CharChain charChain = new CharChain(3);
        charChain.add('a');
        charChain.add('b');
        charChain.add('c');
        assertEquals('a', getCharChain(charChain)[0]);
        assertEquals('b', getCharChain(charChain)[1]);
        assertEquals('c', getCharChain(charChain)[2]);
        assertEquals(3, getCharChain(charChain).length);
    }

    @Test
    void should_properlyAddChar_WhenAddingFourTimes() {
        CharChain charChain = new CharChain(3);
        charChain.add('a');
        charChain.add('b');
        charChain.add('c');
        charChain.add('d');
        assertEquals('a', getCharChain(charChain)[0]);
        assertEquals('b', getCharChain(charChain)[1]);
        assertEquals('c', getCharChain(charChain)[2]);
        assertEquals(3, getCharChain(charChain).length);
    }

    @Test
    void should_properlyProvideIsFull() {
        CharChain charChain = new CharChain(3);
        charChain.add('a');
        charChain.add('b');
        charChain.add('c');
        assertTrue(charChain.isFull());
    }

    @Test
    void should_properlyProvideIsFull_WhenNotFull() {
        CharChain charChain = new CharChain(3);
        charChain.add('a');
        charChain.add('b');
        assertFalse(charChain.isFull());
    }

    @Test
    void should_properlyProvideIsNorEmpty() {
        CharChain charChain = new CharChain(3);
        charChain.add('a');
        assertTrue(charChain.isNorEmpty());
    }

    @Test
    void should_properlyProvideIsNorEmpty_WhenEmpty() {
        CharChain charChain = new CharChain(3);
        assertFalse(charChain.isNorEmpty());
    }

    @Test
    void should_properlyCompareTo() {
        CharChain charChain1 = new CharChain(3);
        charChain1.add('a');
        CharChain charChain2 = new CharChain(3);
        charChain2.add('b');
        charChain2.add('c');
        charChain2.add('d');
        assertEquals(1, charChain1.compareTo(charChain2));
    }

    @Test
    void should_properlyCompareTo_WhenEqual() {
        CharChain charChain1 = new CharChain(3);
        charChain1.add('a');
        charChain1.add('b');
        charChain1.add('c');
        CharChain charChain2 = new CharChain(3);
        charChain2.add('a');
        charChain2.add('b');
        charChain2.add('c');
        assertEquals(0, charChain1.compareTo(charChain2));
    }

    @Test
    void should_properlyCompareTo_WhenGreater() {
        CharChain charChain1 = new CharChain(3);
        charChain1.add('a');
        charChain1.add('b');
        charChain1.add('c');
        CharChain charChain2 = new CharChain(3);
        charChain2.add('z');
        charChain2.add('b');
        charChain2.add('d');
        assert (charChain1.compareTo(charChain2) > 0);
    }

    @Test
    void should_properlyCompareTo_WhenLess() {
        CharChain charChain1 = new CharChain(3);
        charChain1.add('z');
        charChain1.add('b');
        charChain1.add('c');
        CharChain charChain2 = new CharChain(3);
        charChain2.add('a');
        charChain2.add('b');
        charChain2.add('b');
        assert (charChain1.compareTo(charChain2) < 0);
    }

    @Test
    void should_properlyProvideToString() {
        CharChain charChain = new CharChain(3);
        charChain.add('a');
        charChain.add('b');
        charChain.add('c');
        assertEquals("abc", charChain.toString());
    }

    @Test
    void should_properlyProvideToString_whenHasEmptyFields() {
        CharChain charChain = new CharChain(56);
        charChain.add('a');
        charChain.add('c');
        assertEquals("ac", charChain.toString());
    }
}
