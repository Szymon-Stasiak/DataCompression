package org.example.encrypter.tools;

import static org.example.common.tools.UTF8Converter.convertIntToUTF8;
import static org.example.utils.utils.BREAK_CHAIN;

import java.io.*;
import org.example.common.structures.CharChain;
import org.example.common.structures.HuffmanTreeNode;
import org.example.common.tools.BFSIterator;
import org.example.common.tools.BinaryConverter;
import org.example.logger.Log;

public class TreeEncoder {

    private static int size = 4;

    public int encryptTreeAndReturnSize(
            HuffmanTreeNode<CharChain, String> root,
            FileOutputStream fw,
            int bytesOfTheBiggestChar,
            StringBuilder byteCode) {
        byteCode.append(BinaryConverter.convertByteSizeToBinValue(bytesOfTheBiggestChar));
        BFSIterator<HuffmanTreeNode<CharChain, String>> iterator = new BFSIterator<>(root);
        while (iterator.hasNext()) {
            HuffmanTreeNode<CharChain, String> node = iterator.next();
            if (node.getWordNode().getValue() == null) {
                byteCode.append("0");
                size++;
            } else {
                byteCode.append("1");
                size++;
                CharChain charChain = node.getWordNode().getKey();
                for (int c : charChain.getChain()) {}
            }
        }
        return size;
    }

    public static int encryptTreeAndReturnSize01(
            HuffmanTreeNode<CharChain, String> root, int lengthOfSequence, FileOutputStream fw) throws IOException {
        String binSizeOfSequence = BinaryConverter.convertToBin4Signs(lengthOfSequence - 1);
        for (int i = 0; i < binSizeOfSequence.length(); i++) {
            fw.write(binSizeOfSequence.charAt(i));
        }
        BFSIterator<HuffmanTreeNode<CharChain, String>> iterator = new BFSIterator<>(root);
        iterator.next();
        while (iterator.hasNext()) {
            HuffmanTreeNode<CharChain, String> node = iterator.next();
            if (node.getWordNode() == null) {
                fw.write('0');
                size++;
            } else {
                writeNodeKeyValue(fw, node);
            }
        }
        return size;
    }

    private static void writeNodeKeyValue(FileOutputStream fw, HuffmanTreeNode<CharChain, String> node)
            throws IOException {
        size++;
        fw.write('1');
        CharChain charChain = node.getWordNode().getKey();
        if (charChain == null) {
            writeUTF8Character(fw, BREAK_CHAIN);
            Log.info("BREAK_CHAIN added");
            return;
        }

        for (int i : charChain.getChain()) {
            writeUTF8Character(fw, convertIntToUTF8(i));
        }
    }

    public static void writeUTF8Character(FileOutputStream fw, String character) throws IOException {
        Log.info("Character: " + character);
        for (int j = 0; j < character.length(); j++) {
            fw.write(character.charAt(j));
        }
        size += character.length();
    }
}
