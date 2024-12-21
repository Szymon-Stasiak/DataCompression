package org.example.common.tools;

import static org.example.common.tools.UTF8Converter.convertBinaryToInt;
import static org.example.common.tools.UTF8Converter.convertIntToUTF8;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UTF8ConverterTest {

    @Test
    void should_properly_convert1byteCharacterToBinary() {
        String result = convertIntToUTF8(65);
        assertEquals("01000001", result);
    }

    @Test
    void should_properly_convert2byteCharacterToBinary() {
        String result = convertIntToUTF8(317);
        assertEquals("1100010010111101", result);
    }

    @Test
    void should_properly_convert3byteCharacterToBinary() {
        String result = convertIntToUTF8(3407);
        assertEquals("111000001011010110001111", result);
    }

    @Test
    void should_properly_convert4byteCharacterToBinary() {
        String result = convertIntToUTF8(66376);
        assertEquals("11110000100100001000110110001000", result);
    }

    @Test
    void should_properly_convertSmileCharacterToBinary() {
        String result = convertIntToUTF8(128578);
        assertEquals("11110000100111111001100110000010", result);
    }

    @Test
    void should_throwErrorWhenIntIsGreaterThanMaxValue() {
        assertThrows(IllegalArgumentException.class, () -> convertIntToUTF8(1114112));
    }

    @Test
    void should_properly_convert1ByteStringToIntToUTF8() {
        int result = convertBinaryToInt("01000001");
        assertEquals(65, result);
    }

    @Test
    void should_properly_convert2ByteStringToIntToUTF8() {
        int result = convertBinaryToInt("1100010010111101");
        assertEquals(317, result);
    }

    @Test
    void should_properly_convert3ByteStringToIntToUTF8() {
        int result = convertBinaryToInt("111000001011010110001111");
        assertEquals(3407, result);
    }

    @Test
    void should_properly_convert4ByteStringToIntToUTF8() {
        int result = convertBinaryToInt("11110000100100001000110110001000");
        assertEquals(66376, result);
    }

    @Test
    void should_properly_convertSmileStringToIntToUTF8() {
        int result = convertBinaryToInt("11110000100111111001100110000010");
        assertEquals(128578, result);
    }

    @Test
    void should_throwErrorWhenStringRepresentNotExistingChar() {
        assertThrows(IllegalArgumentException.class, () -> convertBinaryToInt("1111111111"));
    }

    @Test
    void should_throwErrorWhenStringRepresentInvalidContinuationByte() {
        assertThrows(IllegalArgumentException.class, () -> convertBinaryToInt("11060000"));
    }

    @Test
    void should_throwErrorWhenBinaryStringHasTooManyBytes() {
        assertThrows(IllegalArgumentException.class, () -> convertBinaryToInt("111100001001000010001101100010001"));
    }

    @Test
    void should_properly_convertEdgeCase1ByteCharacterToBinary() {
        String result = convertIntToUTF8(0x7F);
        assertEquals("01111111", result);
    }

    @Test
    void should_properly_convertEdgeCase2ByteCharacterToBinary() {
        String result = convertIntToUTF8(0x7FF);
        assertEquals("1101111110111111", result);
    }

    @Test
    void should_properly_convertEdgeCase3ByteCharacterToBinary() {
        String result = convertIntToUTF8(0xFFFF);
        assertEquals("111011111011111110111111", result);
    }

    @Test
    void should_properly_convertEdgeCase4ByteCharacterToBinary() {
        String result = convertIntToUTF8(0x10FFFF);
        assertEquals("11110100100011111011111110111111", result);
    }

    @Test
    void should_throwErrorForNonUTF8Character() {
        assertThrows(IllegalArgumentException.class, () -> convertIntToUTF8(0x110000));
    }

    @Test
    void should_throwErrorWhenBiggerThan32() {
        assertThrows(IllegalArgumentException.class, () -> convertIntToUTF8(888888888));
    }

    @Test
    void should_throwErrorWhenContinuationByteIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> convertBinaryToInt("1100000000000000"));
    }

    @Test
    void should_throwErrorWhenBinaryStringLengthIsNotMultipleOf8() {
        assertThrows(IllegalArgumentException.class, () -> convertBinaryToInt("110000011201"));
    }

    @Test
    void should_throwErrorWhenBinaryStringLengthIs0() {
        assertThrows(IllegalArgumentException.class, () -> convertBinaryToInt(""));
    }

    @Test
    void createInstance() {
        UTF8Converter converter = new UTF8Converter();
        assertNotNull(converter);
    }
}
