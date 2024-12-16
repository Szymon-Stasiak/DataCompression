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
import org.example.Structures.Basics.CharChain;
import org.example.Structures.Basics.Pair;
import org.example.Structures.HuffmanTree;

public class Encrypter {

    private RbtMap dictionary;
    private Alphabet oldDictionary;
    private final String inputPath;
    private final String outputPath;
    private int sizeOfEncryptedFile;
    private int additionalZeroes;
    private int lengthOfSequence;

    public Encrypter(String inputPath, String outputPath, int lengthOfSequence) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.lengthOfSequence = lengthOfSequence;
        //makeDictionary();
        makeDictionaryForSequences();
        Log.info("Dictionary created");
        //countSizeOfEncryptedFileForCharacters();
        SizeOfEncryptedFileForSequences();
        Log.info("Size of encrypted file: " + sizeOfEncryptedFile);
        try {
           // writeToFile();
          //  writeToFileTxt();
          //  writeToFileRBTTxt();
            writeToFileForSequence();
        } catch (IOException e) {
            throw new OutputFIleNotFoundException("File with path '" + outputPath + "' not found");
        }
    }

    private void makeDictionaryForChars() {
        dictionary = new RbtMap<Character>();
        oldDictionary = new Alphabet();
        try {
            FileReader fr = new FileReader(inputPath, StandardCharsets.UTF_8);
            int i;
            while ((i = fr.read()) != -1) {
                dictionary.addAt((char) i);
                oldDictionary.add(i);
            }
            fr.close();
        } catch (Exception e) {
            throw new InputFileNotFoundException("File with path '" + inputPath + "' is empty");
        }
        new HuffmanTree(dictionary);
        new HuffmanTree(oldDictionary);
    }

    private void makeDictionaryForSequences() {
        dictionary= new RbtMap<CharChain>();
        try {
            FileReader fr = new FileReader(inputPath, StandardCharsets.UTF_8);
            CharChain chain = new CharChain(lengthOfSequence);
            int i;
            while ((i = fr.read()) != -1) {
                chain.add((char) i);
                if(chain.isFull()) {
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
        //new HuffmanTree(oldDictionary);
    }


    private void countSizeOfEncryptedFileForCharacters() {
        sizeOfEncryptedFile = 3;
        for (int i = 0; i < 1112064; i++) {
            Pair temp = dictionary.getPair((char) i);
            if (temp != null) {
                sizeOfEncryptedFile += temp.getCounter() * temp.getCode().length();
            }
        }
        System.out.println(sizeOfEncryptedFile);
        additionalZeroes = (8 - sizeOfEncryptedFile % 8) % 8;
        sizeOfEncryptedFile += additionalZeroes;
        System.out.println("size " + sizeOfEncryptedFile);
    }

    private void SizeOfEncryptedFileForSequences() {

        sizeOfEncryptedFile = 3;

        Pair root = dictionary.getRoot();
        countSizeOfEncryptedFileForSequences(root);

        System.out.println(sizeOfEncryptedFile);
        additionalZeroes = (8 - sizeOfEncryptedFile % 8) % 8;
        sizeOfEncryptedFile += additionalZeroes;
        System.out.println("size " + sizeOfEncryptedFile);
    }

    private void countSizeOfEncryptedFileForSequences(Pair root) {
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

    private void writeToFileRBTTxt() throws IOException {
        FileWriter fw = new FileWriter(this.outputPath + "madeFromRBT");
        FileReader fr = new FileReader(this.inputPath, StandardCharsets.UTF_8);
        fw.write(convertToBin(additionalZeroes));
        Log.info("Additional zeroes: " + additionalZeroes);
        int i;
        while ((i = fr.read()) != -1) {
            fw.write(dictionary.getPair((char) i).getCode());
            fw.write((char) dictionary.getPair((char)i).getKey());
        }
        fr.close();

        for (int k = 0; k < additionalZeroes; k++) {
            fw.write("0");
        }
        fw.close();
    }

    public void writeToFileForCharacter() throws IOException {
        FileWriter fw = new FileWriter(this.outputPath + "madeFromRBT");
        FileReader fr = new FileReader(this.inputPath, StandardCharsets.UTF_8);
        fw.write(convertToBin(additionalZeroes));
        Log.info("Additional zeroes: " + additionalZeroes);
        int i;
        while ((i = fr.read()) != -1) {
            fw.write(dictionary.getPair((char) i).getCode());
            fw.write((char) dictionary.getPair((char)i).getKey());
        }
        fr.close();

        for (int k = 0; k < additionalZeroes; k++) {
            fw.write("0");
        }
        fw.close();
    }

    public void writeToFileForSequence() throws IOException {
        FileWriter fw = new FileWriter(this.outputPath + "madeFromRBT");
        FileReader fr = new FileReader(this.inputPath, StandardCharsets.UTF_8);
        fw.write(convertToBin(additionalZeroes));
        Log.info("Additional zeroes: " + additionalZeroes);
        int i;
        CharChain chain = new CharChain(lengthOfSequence);
        while ((i = fr.read()) != -1) {
            chain.add((char) i);
            if(chain.isFull()) {
                fw.write(dictionary.getPair(chain).getCode());
                fw.write( dictionary.getPair(chain).getKey().toString());
                chain = new CharChain(lengthOfSequence);
            }

        }
        fr.close();

        for (int k = 0; k < additionalZeroes; k++) {
            fw.write("0");
        }
        fw.close();
    }

    private void writeToFileTxt() throws IOException {
        FileWriter fw = new FileWriter(this.outputPath + "madeFromAlphabet");
        FileReader fr = new FileReader(this.inputPath, StandardCharsets.UTF_8);
        fw.write(convertToBin(additionalZeroes));
        Log.info("Additional zeroes: " + additionalZeroes);
        Log.info("Additional zeroes in binary: " + convertToBin(additionalZeroes));
        int i;
        int sum = 0;
        while ((i = fr.read()) != -1) {
            fw.write(oldDictionary.getPairAt(i).getCode());
            fw.write((char) oldDictionary.getPairAt(i).getKey());
            sum += oldDictionary.getPairAt(i).getCode().length();
            Log.info("Character: " + (char) i + " Code: "
                    + oldDictionary.getPairAt(i).getCode() + " Length "
                    + oldDictionary.getPairAt(i).getCode().length() + " Sum: " + sum);
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
