package org.example.common.tools;

public class UTF8Converter {

    public static String convertIntToUTF8(int value) {
        if (value <= 0x7F) {
            return String.format("%8s", Integer.toBinaryString(value)).replace(' ', '0');
        } else if (value <= 0x7FF) {
            int byte1 = 0xC0 | (value >> 6);
            int byte2 = 0x80 | (value & 0x3F);
            return String.format("%8s", Integer.toBinaryString(byte1)).replace(' ', '0')
                    + String.format("%8s", Integer.toBinaryString(byte2)).replace(' ', '0');
        } else if (value <= 0xFFFF) {
            int byte1 = 0xE0 | (value >> 12);
            int byte2 = 0x80 | ((value >> 6) & 0x3F);
            int byte3 = 0x80 | (value & 0x3F);
            return String.format("%8s", Integer.toBinaryString(byte1)).replace(' ', '0')
                    + String.format("%8s", Integer.toBinaryString(byte2)).replace(' ', '0')
                    + String.format("%8s", Integer.toBinaryString(byte3)).replace(' ', '0');
        } else if (value <= 0x10FFFF) {
            int byte1 = 0xF0 | (value >> 18);
            int byte2 = 0x80 | ((value >> 12) & 0x3F);
            int byte3 = 0x80 | ((value >> 6) & 0x3F);
            int byte4 = 0x80 | (value & 0x3F);
            return String.format("%8s", Integer.toBinaryString(byte1)).replace(' ', '0')
                    + String.format("%8s", Integer.toBinaryString(byte2)).replace(' ', '0')
                    + String.format("%8s", Integer.toBinaryString(byte3)).replace(' ', '0')
                    + String.format("%8s", Integer.toBinaryString(byte4)).replace(' ', '0');
        } else {
            throw new IllegalArgumentException("Value out of range for UTF-8");
        }
    }

    public static int convertBinaryToInt(String binary) {
        if (binary.length() % 8 != 0) {
            throw new IllegalArgumentException("Invalid UTF-8 binary string length");
        }
        int length = binary.length() / 8;
        if (length == 1) {
            return Integer.parseInt(binary, 2);
        } else if (length > 1 && length <= 4) {
            int value = 0;
            for (int i = 0; i < length; i++) {
                String byteStr = binary.substring(i * 8, (i + 1) * 8);
                int currentByte = Integer.parseInt(byteStr, 2);
                if (i == 0) {
                    if (length == 2) {
                        value = currentByte & 0x1F;
                    } else if (length == 3) {
                        value = currentByte & 0x0F;
                    } else {
                        value = currentByte & 0x07;
                    }
                } else {
                    if ((currentByte & 0xC0) != 0x80) {
                        throw new IllegalArgumentException("Invalid UTF-8 continuation byte");
                    }
                    value = (value << 6) | (currentByte & 0x3F);
                }
            }
            return value;
        } else {
            throw new IllegalArgumentException("Invalid UTF-8 binary string length");
        }
    }
}
