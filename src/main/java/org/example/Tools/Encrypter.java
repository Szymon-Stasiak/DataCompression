package org.example.Tools;

import static org.example.Tools.BinaryConventer.convertToBin;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.example.Exceptions.InputFileNotFoundException;
import org.example.Exceptions.OutputFIleNotFoundException;
import org.example.Log;
import org.example.RbtMap;
import org.example.Structures.Alphabet;
import org.example.Structures.Basics.Pair;
import org.example.Structures.HuffmanTree;

public class Encrypter {

    private Alphabet dictionary;
    private RbtMap<Character> dictionaryTxt;
    private final String inputPath;
    private final String outputPath;
    private int sizeOfEncryptedFile;
    private int additionalZeroes;

    public Encrypter(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        makeDictionary();
        countSizeOfEncryptedFile();
        Log.info("Size of encrypted file: " + sizeOfEncryptedFile);
        try {
            writeToFile();
            writeToFileTxt();
            writeToFileRBTTxt();
        } catch (IOException e) {
            throw new OutputFIleNotFoundException("File with path '" + outputPath + "' not found");
        }
    }

    private void makeDictionary() {
        dictionary = new Alphabet();
        dictionaryTxt = new RbtMap<>();
        try {
            FileReader fr = new FileReader(inputPath, StandardCharsets.UTF_8);
            int i;
            while ((i = fr.read()) != -1) {
                dictionary.add(i);
                dictionaryTxt.addAt((char) i);
            }
            fr.close();
        } catch (Exception e) {
            throw new InputFileNotFoundException("File with path '" + inputPath + "' is empty");
        }
        new HuffmanTree(dictionary);
        new HuffmanTree(dictionaryTxt);
    }

    private void countSizeOfEncryptedFile() {
        sizeOfEncryptedFile = 3;
        for (int i = 0; i < 1112064; i++) {
            Pair temp = dictionary.getPairAt(i);
            if (temp != null) {
                sizeOfEncryptedFile += temp.getCounter() * temp.getCode().length();
            }
        }
        System.out.println(sizeOfEncryptedFile);
        additionalZeroes = (8 - sizeOfEncryptedFile % 8) % 8;
        sizeOfEncryptedFile += additionalZeroes;
        System.out.println("size " + sizeOfEncryptedFile);
    }

    private void writeToFile() throws IOException {
        try (FileOutputStream fw = new FileOutputStream(this.outputPath)) {
            FileReader fr = new FileReader(this.inputPath, StandardCharsets.UTF_8);
            StringBuilder byteCode = new StringBuilder();
            byteCode.append(convertToBin(additionalZeroes));
            int i;
            while ((i = fr.read()) != -1) {

                byteCode.append(dictionary.getPairAt(i).getCode());

                while (byteCode.length() >= 8) {
                    String temp = byteCode.substring(0, 8);
                    Log.info("Byte: " + temp);
                    Log.info("Byte in decimal: " + Integer.parseInt(temp, 2));
                    fw.write((byte) Integer.parseInt(temp, 2));
                    System.out.println(((byte) Integer.parseInt(temp, 2)) & 0xFF);
                    byteCode.delete(0, 8);
                }
            }
            fr.close();
            byteCode.append("0".repeat(Math.max(0, additionalZeroes)));
            if (byteCode.length() == 8) {
                fw.write((byte) Integer.parseInt(byteCode.toString(), 2));
            }
        } catch (IOException e) {
            throw new OutputFIleNotFoundException("File with path '" + outputPath + "' not found");
        }
    }

    private void writeToFileRBTTxt() throws IOException {
        FileWriter fw = new FileWriter(this.outputPath + "rbt.txt");
        FileReader fr = new FileReader(this.inputPath, StandardCharsets.UTF_8);
        fw.write(convertToBin(additionalZeroes));
        Log.info("Additional zeroes: " + additionalZeroes);
        int i;
        while ((i = fr.read()) != -1) {
            fw.write(dictionaryTxt.getPair((char) i).getCode());
            fw.write((char) dictionary.getPairAt(i).getKey());
        }
        fr.close();

        for (int k = 0; k < additionalZeroes; k++) {
            fw.write("0");
        }
        fw.close();
    }

    private void writeToFileTxt() throws IOException {
        FileWriter fw = new FileWriter(this.outputPath + ".txt");
        FileReader fr = new FileReader(this.inputPath, StandardCharsets.UTF_8);
        fw.write(convertToBin(additionalZeroes));
        Log.info("Additional zeroes: " + additionalZeroes);
        Log.info("Additional zeroes in binary: " + convertToBin(additionalZeroes));
        int i;
        int sum = 0;
        while ((i = fr.read()) != -1) {
            fw.write(dictionary.getPairAt(i).getCode());
            fw.write((char) dictionary.getPairAt(i).getKey());
            sum += dictionary.getPairAt(i).getCode().length();
            Log.info("Character: " + (char) i + " Code: "
                    + dictionary.getPairAt(i).getCode() + " Length "
                    + dictionary.getPairAt(i).getCode().length() + " Sum: " + sum);
        }
        fr.close();
        int counter = 0;
        for (int k = 0; k < additionalZeroes; k++) {
            fw.write("0");
            counter++;
        }
        Log.info("Additional zeroes written: " + counter);
        fw.close();
    }
}
