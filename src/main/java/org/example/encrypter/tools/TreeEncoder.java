package org.example.encrypter.tools;

import java.io.*;

import org.example.common.structures.CharChain;
import org.example.common.tools.BFSIterator;
import org.example.common.tools.BinaryConverter;
import org.example.encrypter.structures.HuffmanTreeNode;

import static org.example.common.tools.UTF8Converter.convertIntToUTF8;


public class TreeEncoder {

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

    public static int encryptTreeAndReturnSize01(HuffmanTreeNode root, boolean hasOneSequence, int lengthOfSequence, FileOutputStream fw) throws IOException {
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
        }

    }
}

