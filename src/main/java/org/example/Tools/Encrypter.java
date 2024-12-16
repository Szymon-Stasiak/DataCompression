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
import org.example.Structures.Basics.CharChain;
import org.example.Structures.Basics.WordNode;
import org.example.Structures.HuffmanTree;

public class Encrypter {

    private RbtMap dictionary;
    private final String inputPath;
    private final String outputPath;
    private int sizeOfEncryptedFile = 3;
    private int additionalZeroes;
    private final int lengthOfSequence;

    public Encrypter(String inputPath, String outputPath, int lengthOfSequence) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.lengthOfSequence = lengthOfSequence;
        makeDictionary();
        SizeOfEncryptedFileForSequences();
        try {
            writeToFileForSequence();
        } catch (IOException e) {
            throw new OutputFIleNotFoundException("File with path '" + outputPath + "' not found");
        }
    }

    private void makeDictionary() {
        dictionary = new RbtMap<CharChain>();
        try {
            FileReader fr = new FileReader(inputPath, StandardCharsets.UTF_8);
            CharChain chain = new CharChain(lengthOfSequence);
            int i;
            while ((i = fr.read()) != -1) {
                chain.add((char) i);
                if (chain.isFull()) {
                    dictionary.addAt(chain);
                    Log.info("Chain added: " + chain);
                    chain = new CharChain(lengthOfSequence);
                }
            }

            fr.close();
        } catch (Exception e) {
            throw new InputFileNotFoundException("File with path '" + inputPath + "' is empty");
        }
        Log.info("Dictionary size:");
        new HuffmanTree(dictionary);
    }

    private void SizeOfEncryptedFileForSequences() {
        WordNode root = dictionary.getRoot();
        countSizeOfEncryptedFileForSequences(root);
        additionalZeroes = (8 - sizeOfEncryptedFile % 8) % 8;
        sizeOfEncryptedFile += additionalZeroes;
    }

    private void countSizeOfEncryptedFileForSequences(WordNode root) {
        if (root == null) {
            return;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            sizeOfEncryptedFile += root.getCounter() * root.getCode().length();
        }
        countSizeOfEncryptedFileForSequences(root.getLeft());
        countSizeOfEncryptedFileForSequences(root.getRight());
    }

    private void writeToFile() throws IOException {
        try (FileOutputStream fw = new FileOutputStream(this.outputPath)) {
            FileReader fr = new FileReader(this.inputPath, StandardCharsets.UTF_8);
            StringBuilder byteCode = new StringBuilder();
            byteCode.append(convertToBin(additionalZeroes));
            int i;
            while ((i = fr.read()) != -1) {

                byteCode.append(dictionary.getPair((char) i).getCode());

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

    public void writeToFileForSequence() throws IOException {
        FileWriter fw = new FileWriter(this.outputPath);
        FileReader fr = new FileReader(this.inputPath, StandardCharsets.UTF_8);
        fw.write(convertToBin(additionalZeroes));
        Log.info("Additional zeroes: " + additionalZeroes);
        int i;
        CharChain chain = new CharChain(lengthOfSequence);
        while ((i = fr.read()) != -1) {
            chain.add((char) i);
            if (chain.isFull()) {
                fw.write(dictionary.getPair(chain).getCode());
                fw.write(dictionary.getPair(chain).getKey().toString());
                chain = new CharChain(lengthOfSequence);
            }
        }
        fr.close();

        for (int k = 0; k < additionalZeroes; k++) {
            fw.write("0");
        }
        fw.close();
    }
}
