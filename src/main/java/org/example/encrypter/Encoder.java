package org.example.encrypter;

import static org.example.common.tools.BinaryConverter.convertToBin3Signs;
import static org.example.encrypter.tools.TreeEncoder.encryptTreeAndReturnSize01;

import java.io.*;
import java.nio.charset.StandardCharsets;
import org.example.common.structures.CharChain;
import org.example.common.structures.Dictionary;
import org.example.common.structures.HuffmanTree;
import org.example.common.structures.WordNode;
import org.example.exceptions.InputFileNotFoundException;
import org.example.exceptions.OutputFIleNotFoundException;
import org.example.logger.Log;

public class Encoder {

    private Dictionary<CharChain, String> dictionary;
    private final HuffmanTree huffmanTree;
    private final String inputPath;
    private final String outputPath;
    private int sizeOfEncryptedFile = 3;
    private int additionalZeroes;
    private final int lengthOfSequence;

    public Encoder(String inputPath, String outputPath, int lengthOfSequence) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.lengthOfSequence = lengthOfSequence;
        makeDictionary();
        huffmanTree = new HuffmanTree(dictionary);
        try {
            writeEncryptCodeToFile();
        } catch (IOException e) {
            throw new OutputFIleNotFoundException("File with path '" + outputPath + "' not found");
        }
    }

    public void writeEncryptCodeToFile() throws IOException {
        FileOutputStream fw = new FileOutputStream(outputPath);
        writeEncryptedTreeToFile(fw);
        countSizeOfEncryptedFileForSequences();
        writeToFileForSequence(fw);
        fw.close();
    }

    private void makeDictionary() {
        dictionary = new Dictionary<>();
        try {
            FileInputStream fr = new FileInputStream(inputPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fr, StandardCharsets.UTF_8));
            int codePoint;
            CharChain chain = new CharChain(lengthOfSequence);
            char[] buffer = new char[8192];
            while (true) {
                int charsRead = reader.read(buffer);
                if (charsRead == -1) {
                    break;
                }
                for (int i = 0; i < charsRead; i++) {
                    codePoint = buffer[i];
                    if (Character.isHighSurrogate((char) codePoint)) {
                        if (i + 1 < charsRead) {
                            int lowSurrogate = buffer[i + 1];
                            codePoint = Character.toCodePoint((char) codePoint, (char) lowSurrogate);
                            i++;
                        }
                    }
                    chain.add(codePoint);
                    if (chain.isFull()) {
                        dictionary.addAt(chain);
                        chain = new CharChain(lengthOfSequence);
                    }
                }
            }
            if (chain.isNotEmpty()) {
                dictionary.addAt(chain);
            }
            fr.close();
        } catch (IOException e) {
            throw new InputFileNotFoundException("File with path '" + inputPath + "' not found");
        }
    }

    private void writeEncryptedTreeToFile(FileOutputStream fw) throws IOException {
        int treeSize = encryptTreeAndReturnSize01(huffmanTree.getRoot(), false, lengthOfSequence, fw);
        Log.info("Encrypted Tree size: " + treeSize);
        sizeOfEncryptedFile += treeSize;
    }

    private void countSizeOfEncryptedFileForSequences() {
        for (WordNode<CharChain, String> node : dictionary) {
            sizeOfEncryptedFile += node.getCounter() * node.getValue().length();
        }
        additionalZeroes = (8 - sizeOfEncryptedFile % 8) % 8;
        sizeOfEncryptedFile += additionalZeroes;
        Log.info("Additional zeroes: " + additionalZeroes);
    }

    // TODO bytes
    //    private void writeToFile() throws IOException {
    //        try (FileOutputStream fw = new FileOutputStream(this.outputPath)) {
    //            InputStream fr = new FileInputStream(this.inputPath);
    //            StringBuilder byteCode = new StringBuilder();
    //            byteCode.append(convertToBin3Signs(additionalZeroes));
    //            CharChain chain = new CharChain(lengthOfSequence);
    //            int i;
    //            while ((i = fr.read()) != -1) {
    //                chain.add(i);
    //                if (chain.isFull()) {
    //                    byteCode.append(dictionary.getCode(chain));
    //                    chain = new CharChain(lengthOfSequence);
    //                    while (byteCode.length() >= 8) {
    //                        String temp = byteCode.substring(0, 8);
    //                        fw.write((byte) Integer.parseInt(temp, 2));
    //                        byteCode.delete(0, 8);
    //                    }
    //                }
    //            }
    //            if (chain.isNorEmpty()) {
    //                byteCode.append(dictionary.getCode(chain));
    //            }
    //            fr.close();
    //            byteCode.append("0".repeat(Math.max(0, additionalZeroes)));
    //            if (byteCode.length() == 8) {
    //                fw.write((byte) Integer.parseInt(byteCode.toString(), 2));
    //            }
    //        } catch (IOException e) {
    //            throw new OutputFIleNotFoundException("File with path '" + outputPath + "' not found");
    //        }
    //    }

    public void writeToFileForSequence(FileOutputStream fw) throws IOException {
        FileInputStream fr = new FileInputStream(inputPath);
        for (int i = 0; i < 3; i++) {
            fw.write(convertToBin3Signs(additionalZeroes).charAt(i));
        }
        int codePoint;
        BufferedReader reader = new BufferedReader(new InputStreamReader(fr, StandardCharsets.UTF_8));
        CharChain chain = new CharChain(lengthOfSequence);
        char[] buffer = new char[8192];
        while (true) {
            int charsRead = reader.read(buffer);
            if (charsRead == -1) {
                break;
            }
            for (int i = 0; i < charsRead; i++) {
                codePoint = buffer[i];
                if (Character.isHighSurrogate((char) codePoint)) {
                    if (i + 1 < charsRead) {
                        int lowSurrogate = buffer[i + 1];
                        codePoint = Character.toCodePoint((char) codePoint, (char) lowSurrogate);
                        i++;
                    }
                }
                chain.add(codePoint);
                if (chain.isFull()) {
                    String toWrite = dictionary.getValue(chain);
                    for (int j = 0; j < toWrite.length(); j++) {
                        fw.write(toWrite.charAt(j));
                    }
                    chain = new CharChain(lengthOfSequence);
                }
            }
        }
        if (chain.isNotEmpty()) {
            String toWrite = dictionary.getValue(chain);
            for (int j = 0; j < toWrite.length(); j++) {
                fw.write(toWrite.charAt(j));
            }
        }
        for (int k = 0; k < additionalZeroes; k++) {
            fw.write('0');
        }
        Log.info("File encrypted successfully, size: " + sizeOfEncryptedFile);
    }
}
