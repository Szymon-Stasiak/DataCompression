package org.example.common.tools;

import java.io.*;
import java.nio.charset.StandardCharsets;

import org.example.common.structures.CharChain;
import org.example.encrypter.structures.HuffmanTreeNode;
import org.example.encrypter.tools.BinaryConverter;
import org.example.logger.Log;

public class treeTranslator<K extends Comparable<K>> {

    private static int size = 5;

    public int encryptTreeAndReturnSize(
            HuffmanTreeNode root, FileOutputStream fw, int bytesOfTheBiggestChar, StringBuilder byteCode) {
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
                CharChain charChain = node.getWordNode().getKey();
                for (int c : charChain.getChain()) {

                }

            }
        }
        return size;
    }

    public static int encryptTreeAndReturnSize01(HuffmanTreeNode root, boolean hasOneSequence, int lengthOfSequence) throws IOException {
        FileOutputStream fw = new FileOutputStream("C:\\Users\\stszy\\IdeaProjects\\DataCompression\\src\\main\\resources\\tree.txt"); // Ścieżka do pliku


        fw.write(hasOneSequence ? '1' : '0');
        String binSizeOfSequence = BinaryConverter.convertToBin4Signs(lengthOfSequence - 1);
        for (int i = 0; i < binSizeOfSequence.length(); i++) {
            fw.write(binSizeOfSequence.charAt(i));
        }
        BFSIterator<HuffmanTreeNode> iterator = new BFSIterator<>(root);
        iterator.next();
        while (iterator.hasNext()) {
            HuffmanTreeNode node = iterator.next();
            if (node.getWordNode() == null) {
                fw.write('0');
                size++;
            } else {
                writeNodeKeyValue(fw, node);

            }
        }
        fw.close();
        return size;
    }

    private static void writeNodeKeyValue(FileOutputStream fw, HuffmanTreeNode node) throws IOException {
        String character;
        size++;
        fw.write('1');
        CharChain charChain = node.getWordNode().getKey();
        for (int i : charChain.getChain()) {
            character = convertIntToUTF8(i);
            for (int j = 0; j < character.length(); j++) {
                fw.write(character.charAt(j));
            }
            size += character.length();
            Log.info(charChain.toChars() + " added as " + character);
        }
    }


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
                    } else if (length == 4) {
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
