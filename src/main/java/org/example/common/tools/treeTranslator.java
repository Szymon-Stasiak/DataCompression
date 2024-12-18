package org.example.common.tools;

import java.io.*;
import java.nio.charset.StandardCharsets;
import org.example.common.structures.CharChain;
import org.example.encrypter.structures.HuffmanTreeNode;
import org.example.encrypter.tools.BinaryConverter;

public class treeTranslator<K extends Comparable<K>> {

    public int encryptTreeAndReturnSize(
            HuffmanTreeNode root, FileOutputStream fw, int bytesOfTheBiggestChar, StringBuilder byteCode) {
        int size = 2;
        byteCode.append(BinaryConverter.convertByteSizeToBinValue(bytesOfTheBiggestChar));
        BFSIterator<HuffmanTreeNode> iterator = new BFSIterator<>(root);
        while (iterator.hasNext()) {
            HuffmanTreeNode node = iterator.next();
            if (node.getWordNode().getCode() == null) {
                byteCode.append("0");
                size++;
            } else {
                byteCode.append("1");
                size++;
                CharChain charChain = new CharChain(node.getWordNode().getKey().toString());
                //  for(char c : charChain.getChain()){

            }
        }
        return size;
    }

    public static String convertIntToBinary(int value) {
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

    public static String speaceNeededToWriteCharacter(int bytesOfTheBiggestChar, char c) {
        if (bytesOfTheBiggestChar == 1) {
            return "";
        } else {
            // check how may bytes are needed to write the character
            int bitsNeeded = 0;
        }

        return "";
    }

    public static void main(String[] args) {
        String filePath =
                "C:\\Users\\stszy\\IdeaProjects\\DataCompression\\src\\main\\resources\\dane.txt"; // Ścieżka do pliku

        try (InputStream inputStream = new FileInputStream(filePath);
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            int codePoint;
            int bufferSize = 8192; // Rozmiar bufora (8 KB, można dostosować)
            char[] buffer = new char[bufferSize];
            while (true) {
                int charsRead = reader.read(buffer);
                if (charsRead == -1) {
                    break; // Koniec pliku
                }

                // Przetwarzanie wczytanych znaków
                for (int i = 0; i < charsRead; i++) {
                    codePoint = buffer[i];
                    // Jeśli znak jest częścią pary zastępczej, sprawdzamy drugi znak
                    if (Character.isHighSurrogate((char) codePoint)) {
                        if (i + 1 < charsRead) {
                            int lowSurrogate = buffer[i + 1];
                            codePoint = Character.toCodePoint((char) codePoint, (char) lowSurrogate);
                            i++; // Przechodzimy do następnego znaku
                        }
                    }
                    // Wyświetlamy punkt kodowy
                    System.out.printf("Znak: %c, Punkt kodowy: U+%04X%n", codePoint, codePoint);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
