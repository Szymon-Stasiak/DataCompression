package org.example.encrypter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinaryConventerTest {

    @Test
    void convertToBin_zero() {
        assertEquals("000", BinaryConverter.convertToBin(0));
    }

    @Test
    void convertToBin_one() {
        assertEquals("001", BinaryConverter.convertToBin(1));
    }

    @Test
    void convertToBin_two() {
        assertEquals("010", BinaryConverter.convertToBin(2));
    }

    @Test
    void convertToBin_three() {
        assertEquals("011", BinaryConverter.convertToBin(3));
    }

    @Test
    void convertToBin_four() {
        assertEquals("100", BinaryConverter.convertToBin(4));
    }

    @Test
    void convertToBin_five() {
        assertEquals("101", BinaryConverter.convertToBin(5));
    }

    @Test
    void convertToBin_six() {
        assertEquals("110", BinaryConverter.convertToBin(6));
    }

    @Test
    void convertToBin_seven() {
        assertEquals("111", BinaryConverter.convertToBin(7));
    }

    @Test
    void createInstance() {
        BinaryConverter bc = new BinaryConverter();
        assertNotNull(bc);
    }
}
