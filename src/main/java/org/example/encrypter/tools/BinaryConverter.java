package org.example.encrypter.tools;

public class BinaryConverter {

    public static String convertToBin(int number) {
        int binary = 0;
        int remainder, i = 1;
        while (number != 0) {
            remainder = number % 2;
            number /= 2;
            binary += remainder * i;
            i *= 10;
        }
        String result = String.valueOf(binary);
        while (result.length() < 3) {
            result = "0" + result;
        }
        return result;
    }

    public static String convertByteSizeToBinValue(int size) {
        return switch (size) {
            case 1 -> "00";
            case 2 -> "01";
            case 3 -> "10";
            default -> "11";
        };
    }
}
