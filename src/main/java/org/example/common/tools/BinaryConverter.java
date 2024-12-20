package org.example.common.tools;

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
        return String.valueOf(binary);
    }


    public static String convertToBin3Signs(int number) {
        StringBuilder result = new StringBuilder(convertToBin(number));
        while (result.length() < 3) {
            result.insert(0, "0");
        }
        return result.toString();
    }

    public static String convertToBin4Signs(int number) {
        StringBuilder result = new StringBuilder(convertToBin(number));
        while (result.length() < 4) {
            result.insert(0, "0");
        }
        return result.toString();
    }

    public static int convertBinToInt(String binary) {
        return Integer.parseInt(binary, 2);
    }

    public static String convertByteSizeToBinValue(int size) {
        return switch (size) {
            case 1 -> "00";
            case 2 -> "01";
            case 3 -> "10";
            default -> "11";
        };
    }

    public static int convertBinToByteSize(int first, int second) {
        String bin = String.valueOf(first) + second;
        return switch (bin) {
            case "00" -> 1;
            case "01" -> 2;
            case "10" -> 3;
            default -> 4;
        };
    }
}
