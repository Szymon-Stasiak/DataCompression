package org.example.Structures;

import static utils.Mocks.pairMock;

import org.example.Structures.Basics.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AlphabetTest {

    Alphabet alphabet;

    @BeforeEach
    void setUp() {
        alphabet = new Alphabet();
    }

    @Test
    void should_properAdd() {
        alphabet.add('a');
        assert (alphabet.getPairAt('a').getCounter() == 1);
        assert (alphabet.getUniqueCharacters() == 1);
    }

    @Test
    void should_properAdd_WhenAdding2SameObject() {
        alphabet.add('a');
        alphabet.add('a');
        assert (alphabet.getPairAt('a').getCounter() == 2);
        assert (alphabet.getUniqueCharacters() == 1);
    }

    @Test
    void should_properAdd_WhenAdding2DifferentObjects() {
        alphabet.add('a');
        alphabet.add('b');
        assert (alphabet.getPairAt('a').getCounter() == 1);
        assert (alphabet.getPairAt('b').getCounter() == 1);
        assert (alphabet.getUniqueCharacters() == 2);
    }

    @Test
    void should_properAdd_WhenAdding128DifferentObjects() {
        for (int i = 0; i < 128; i++) {
            alphabet.add((char) i);
        }
        assert (alphabet.getUniqueCharacters() == 128);
    }

    @Test
    void should_properRemoveEmpty() {
        for (int i = 20; i < 60; i++) {
            alphabet.add((char) i);
        }
        alphabet.removeEmpty();
        assert (alphabet.getUniqueCharacters() == 40);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120})
    void should_properRemoveEmpty(int quantity) {
        for (int i = 0; i < quantity; i++) {
            alphabet.add((char) i);
        }
        alphabet.removeEmpty();
        assert (alphabet.getUniqueCharacters() == quantity);
    }

    @Test
    void should_properOverwritePair() {
        Pair pairMock = pairMock('a', 129);

        Alphabet alphabet = new Alphabet();
        alphabet.add('a');

        alphabet.overwritePair('a', pairMock);

        assert (alphabet.getPairAt('a') == pairMock);
        assert (alphabet.getPairAt('a').getCounter() == 129);
    }

    @Test
    void should_properGetPairsAt() {
        Pair pairMockA = pairMock('a', 1);
        Pair pairMockB = pairMock('b', 3);

        alphabet.add('a');
        alphabet.add('b');
        alphabet.add('b');
        alphabet.add('b');
        assert (alphabet.getPairAt('a').getCounter() == (pairMockA.getCounter()));
        assert (alphabet.getPairAt('b').getCounter() == (pairMockB.getCounter()));
        assert (alphabet.getPairAt('a').getCharacter() == (pairMockA.getCharacter()));
        assert (alphabet.getPairAt('b').getCharacter() == (pairMockB.getCharacter()));
    }
}
