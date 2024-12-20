package org.example.common.tools;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinaryConventerTest {

    @Test
    void convertToBin_zero() {
        assertEquals("000", BinaryConverter.convertToBin3Signs(0));
    }

    @Test
    void convertToBin_one() {
        assertEquals("001", BinaryConverter.convertToBin3Signs(1));
    }

    @Test
    void convertToBin_two() {
        assertEquals("010", BinaryConverter.convertToBin3Signs(2));
    }

    @Test
    void convertToBin_three() {
        assertEquals("011", BinaryConverter.convertToBin3Signs(3));
    }

    @Test
    void convertToBin_four() {
        assertEquals("100", BinaryConverter.convertToBin3Signs(4));
    }

    @Test
    void convertToBin_five() {
        assertEquals("101", BinaryConverter.convertToBin3Signs(5));
    }

    @Test
    void convertToBin_six() {
        assertEquals("110", BinaryConverter.convertToBin3Signs(6));
    }

    @Test
    void convertToBin_seven() {
        assertEquals("111", BinaryConverter.convertToBin3Signs(7));
    }

    @Test
    void createInstance() {
        BinaryConverter bc = new BinaryConverter();
        assertNotNull(bc);
    }

    @Test
    void convertByteSizeToBinValue_one() {
        assertEquals("00", BinaryConverter.convertByteSizeToBinValue(1));
    }

    @Test
    void convertByteSizeToBinValue_two() {
        assertEquals("01", BinaryConverter.convertByteSizeToBinValue(2));
    }

    @Test
    void convertByteSizeToBinValue_three() {
        assertEquals("10", BinaryConverter.convertByteSizeToBinValue(3));
    }

    @Test
    void convertByteSizeToBinValue_four() {
        assertEquals("11", BinaryConverter.convertByteSizeToBinValue(4));
    }

    @Test
    void convertBinToByteSize_one() {
        assertEquals(1, BinaryConverter.convertBinToByteSize(0, 0));
    }

    @Test
    void convertBinToByteSize_two() {
        assertEquals(2, BinaryConverter.convertBinToByteSize(0, 1));
    }

    @Test
    void convertBinToByteSize_three() {
        assertEquals(3, BinaryConverter.convertBinToByteSize(1, 0));
    }

    @Test
    void convertBinToByteSize_four() {
        assertEquals(4, BinaryConverter.convertBinToByteSize(1, 1));
    }

    @Test
    void convertBinToInt() {
        assertEquals(0, BinaryConverter.convertBinToInt("000"));
        assertEquals(1, BinaryConverter.convertBinToInt("001"));
        assertEquals(2, BinaryConverter.convertBinToInt("010"));
        assertEquals(3, BinaryConverter.convertBinToInt("011"));
        assertEquals(4, BinaryConverter.convertBinToInt("100"));
        assertEquals(5, BinaryConverter.convertBinToInt("101"));
        assertEquals(6, BinaryConverter.convertBinToInt("110"));
        assertEquals(7, BinaryConverter.convertBinToInt("111"));
        assertEquals(8, BinaryConverter.convertBinToInt("1000"));
        assertEquals(9, BinaryConverter.convertBinToInt("1001"));
        assertEquals(10, BinaryConverter.convertBinToInt("1010"));
        assertEquals(11, BinaryConverter.convertBinToInt("1011"));
        assertEquals(12, BinaryConverter.convertBinToInt("1100"));
        assertEquals(13, BinaryConverter.convertBinToInt("1101"));
        assertEquals(14, BinaryConverter.convertBinToInt("1110"));
        assertEquals(15, BinaryConverter.convertBinToInt("1111"));
    }
}
