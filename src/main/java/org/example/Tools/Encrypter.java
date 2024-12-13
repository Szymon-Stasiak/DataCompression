package org.example.Tools;

import static org.example.Tools.BinaryConventer.convertToBin;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.example.Exceptions.InputFileNotFoundException;
import org.example.Exceptions.OutputFIleNotFoundException;
import org.example.Log;
import org.example.Structures.Alphabet;
import org.example.Structures.Basics.Pair;
import org.example.Structures.HuffmanTree;

public class Encrypter {

    private Alphabet dictionary;
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
        } catch (IOException e) {
            throw new OutputFIleNotFoundException("File with path '" + outputPath + "' not found");
        }
    }

    private void makeDictionary() {
        dictionary = new Alphabet();
        try {
            FileReader fr = new FileReader(inputPath);
            boolean fileIsEmpty = fr.read() == -1;
            if (fileIsEmpty) {
                throw new InputFileNotFoundException("File with path '" + inputPath + "' is empty");
            }
            int i;
            while ((i = fr.read()) != -1) {
                dictionary.add((char) i);
            }
            fr.close();
        } catch (Exception e) {
            throw new InputFileNotFoundException("File with path '" + inputPath + "' not found");
        }
        new HuffmanTree(dictionary);
    }

    private void countSizeOfEncryptedFile() {
        sizeOfEncryptedFile = 0;
        for (int i = 0; i < 128; i++) {
            Pair temp = dictionary.getPairAt(i);
            if (temp != null) {
                sizeOfEncryptedFile += temp.getCounter() * temp.getCode().length();
            }
        }
        sizeOfEncryptedFile += 3;
        additionalZeroes = 8 - sizeOfEncryptedFile % 8;
        sizeOfEncryptedFile += additionalZeroes;
    }

    private void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(this.outputPath);
        FileReader fr = new FileReader(this.inputPath);
        fw.write(convertToBin(additionalZeroes));
        int i;
        while ((i = fr.read()) != -1) {
            fw.write(dictionary.getPairAt(i).getCode());
        }
        fr.close();
        for (int k = 0; k < additionalZeroes; k++) {
            fw.write("0");
        }
        fw.close();
    }
}
