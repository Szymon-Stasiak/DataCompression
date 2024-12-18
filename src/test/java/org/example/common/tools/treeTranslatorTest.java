package org.example.common.tools;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class treeTranslatorTest {

    @Test
    void should_properly_convert1byteCharacterToBinary() {
        String result = treeTranslator.convertIntToBinary(65);
        assertEquals("01000001", result);
    }

    @Test
    void should_properly_convert2byteCharacterToBinary() {
        String result = treeTranslator.convertIntToBinary(317);
        assertEquals("1100010010111101", result);
    }

    @Test
    void should_properly_convert3byteCharacterToBinary() {
        String result = treeTranslator.convertIntToBinary(3407);
        assertEquals("111000001011010110001111", result);
    }

    @Test
    void should_properly_convert4byteCharacterToBinary() {
        String result = treeTranslator.convertIntToBinary(66376);
        assertEquals("11110000100100001000110110001000", result);
    }
}
