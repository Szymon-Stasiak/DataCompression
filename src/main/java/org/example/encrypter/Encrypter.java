package org.example.encrypter;

import static org.example.encrypter.BinaryConverter.convertToBin;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import org.example.common.RedBlackTree;
import org.example.common.WordNode;
import org.example.enums.CharChain;
import org.example.exceptions.InputFileNotFoundException;
import org.example.exceptions.OutputFIleNotFoundException;
import org.example.logger.Log;

public class Encrypter {

    private RedBlackTree dictionary;
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
        countSizeOfEncryptedFileForSequences();
        try {
            writeToFile();
            writeToFileForSequence();
        } catch (IOException e) {
            throw new OutputFIleNotFoundException("File with path '" + outputPath + "' not found");
        }
    }

    private void makeDictionary() {
        dictionary = new RedBlackTree<CharChain>();
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
            if (chain.isNorEmpty()) {
                dictionary.addAt(chain);
                Log.info("Chain added: " + chain);
            }
            fr.close();
        } catch (Exception e) {
            throw new InputFileNotFoundException("File with path '" + inputPath + "' is empty");
        }
        new HuffmanTree(dictionary);
    }

    private void countSizeOfEncryptedFileForSequences() {
        Iterator iterator = dictionary.iterator();
        while (iterator.hasNext()) {
            WordNode node = (WordNode) iterator.next();
            sizeOfEncryptedFile += node.getCounter() * node.getCode().length();
        }
        additionalZeroes = (8 - sizeOfEncryptedFile % 8) % 8;
        sizeOfEncryptedFile += additionalZeroes;
        Log.info("Size of encrypted file: " + sizeOfEncryptedFile + " Additional zeroes: " + additionalZeroes);
    }

    private void writeToFile() throws IOException {
        try (FileOutputStream fw = new FileOutputStream(this.outputPath)) {
            FileReader fr = new FileReader(this.inputPath, StandardCharsets.UTF_8);
            StringBuilder byteCode = new StringBuilder();
            byteCode.append(convertToBin(additionalZeroes));
            CharChain chain = new CharChain(lengthOfSequence);
            int i;
            while ((i = fr.read()) != -1) {
                chain.add((char) i);
                if (chain.isFull()) {
                    byteCode.append(dictionary.getCode(chain));
                    chain = new CharChain(lengthOfSequence);
                    while (byteCode.length() >= 8) {
                        String temp = byteCode.substring(0, 8);
                        fw.write((byte) Integer.parseInt(temp, 2));
                        byteCode.delete(0, 8);
                    }
                }
            }
            if (chain.isNorEmpty()) {
                byteCode.append(dictionary.getCode(chain));
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
        FileWriter fw = new FileWriter(this.outputPath + "zeroOne.txt");
        FileReader fr = new FileReader(this.inputPath, StandardCharsets.UTF_8);
        fw.write(convertToBin(additionalZeroes));
        Log.info("Additional zeroes: " + additionalZeroes);
        int i;
        CharChain chain = new CharChain(lengthOfSequence);
        while ((i = fr.read()) != -1) {
            chain.add((char) i);
            if (chain.isFull()) {
                fw.write(dictionary.getCode(chain));
                fw.write(dictionary.getNode(chain).getKey().toString());
                chain = new CharChain(lengthOfSequence);
            }
        }
        if (chain.isNorEmpty()) {
            fw.write(dictionary.getCode(chain));
            fw.write(dictionary.getNode(chain).getKey().toString());
        }
        fr.close();

        for (int k = 0; k < additionalZeroes; k++) {
            fw.write("0");
        }
        fw.close();
    }
}
