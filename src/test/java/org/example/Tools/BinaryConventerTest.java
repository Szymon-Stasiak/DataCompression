package org.example.Tools;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinaryConventerTest {

    @Test
    void convertToBin_zero() {
        assertEquals("000", BinaryConventer.convertToBin(0));
    }

    @Test
    void convertToBin_one() {
        assertEquals("001", BinaryConventer.convertToBin(1));
    }

    @Test
    void convertToBin_two() {
        assertEquals("010", BinaryConventer.convertToBin(2));
    }

    @Test
    void convertToBin_three() {
        assertEquals("011", BinaryConventer.convertToBin(3));
    }

    @Test
    void convertToBin_four() {
        assertEquals("100", BinaryConventer.convertToBin(4));
    }

    @Test
    void convertToBin_five() {
        assertEquals("101", BinaryConventer.convertToBin(5));
    }

    @Test
    void convertToBin_six() {
        assertEquals("110", BinaryConventer.convertToBin(6));
    }

    @Test
    void convertToBin_seven() {
        assertEquals("111", BinaryConventer.convertToBin(7));
    }

    @Test
    void createInstance() {
        BinaryConventer bc = new BinaryConventer();
        assertNotNull(bc);
    }
}
