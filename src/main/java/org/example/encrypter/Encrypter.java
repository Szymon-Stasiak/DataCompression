package org.example.encrypter;

import static org.example.encrypter.tools.BinaryConverter.convertToBin;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import org.example.common.structures.CharChain;
import org.example.common.structures.RedBlackTree;
import org.example.common.structures.WordNode;
import org.example.encrypter.structures.HuffmanTree;
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
            FileInputStream fr = new FileInputStream(inputPath);
            CharChain chain = new CharChain(lengthOfSequence);
            int i;
            while ((i = fr.read()) != -1) {
                chain.add( i);
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
            InputStream fr = new FileInputStream(this.inputPath);
            StringBuilder byteCode = new StringBuilder();
            byteCode.append(convertToBin(additionalZeroes));
            CharChain chain = new CharChain(lengthOfSequence);
            int i;
            while ((i = fr.read()) != -1) {
                chain.add(i);
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
        FileInputStream fr = new FileInputStream(inputPath);
        FileOutputStream fw = new FileOutputStream(outputPath+"zeroOnes.txt");
        for (int i = 0; i < 3; i++) {
            fw.write(convertToBin(sizeOfEncryptedFile).charAt(i) );
        }
        Log.info("Additional zeroes: " + additionalZeroes);
        int i;
        CharChain chain = new CharChain(lengthOfSequence);
        while ((i = fr.read()) != -1) {
            chain.add(i);
            Log.info("Chain: " + chain);
            if (chain.isFull()) {
                 String toWrite = dictionary.getCode(chain);
                 CharChain temp = (CharChain) dictionary.getNode(chain).getKey();
                 for (int j = 0; j < toWrite.length(); j++) {
                     fw.write(toWrite.charAt(j));
                 }
                 fw.write(temp.getChain()[0]);
//                fw.write(dictionary.getCode(chain));
//                fw.write(dictionary.getNode(chain).getKey().toString());
                chain = new CharChain(lengthOfSequence);
            }
        }
        if (chain.isNorEmpty()) {
            String toWrite = dictionary.getCode(chain);
            for (int j = 0; j < toWrite.length(); j++) {
                fw.write(toWrite.charAt(j));
            }
        }
        fr.close();

        for (int k = 0; k < additionalZeroes; k++) {
            fw.write('0');
        }
        fw.close();
    }
}
