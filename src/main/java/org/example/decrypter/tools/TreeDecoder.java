package org.example.decrypter.tools;

import static org.example.common.tools.UTF8Converter.convertBinaryToInt;
import static org.example.utils.utils.BREAK_CHAIN;

import java.io.*;
import java.util.Objects;

import org.example.common.structures.*;
import org.example.common.tools.BinaryConverter;
import org.example.logger.Log;
import org.example.service.HuffmanTree;

// todo cleaning
public class TreeDecoder {

    private static final Queue<HuffmanTreeNode<String, CharChain>> queue = new Queue<>();
    private static HuffmanTreeNode<String, CharChain> root;

    public static void addNodeToQueue(HuffmanTreeNode<String, CharChain> node) {
        queue.add(node);
        if (root == null) {
            root = node;
        } else {
            attachToParent(node);
        }
    }

    public static void attachToParent(HuffmanTreeNode<String, CharChain> node) {
        HuffmanTreeNode<String, CharChain> parent = queue.peek();
        if (parent.getLeft() == null) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
            queue.poll();
        }
    }

    public static Dictionary<String, CharChain> generateDictionary(StringBuilder byteReader, InputStreamReader fr) {
        int current;
        try {

            // Todo
            Dictionary<String, CharChain> dictionary = new Dictionary<>();
            StringBuilder sequence = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                sequence.append((char) fr.read());
            }
            int sizeOfSequence = BinaryConverter.convertBinToInt(sequence.toString()) + 1;
            Log.info("Size of sequence: " + sizeOfSequence);

            addNodeToQueue(new HuffmanTreeNode<>());
            while (!queue.isEmpty()) {
                current = fr.read();

                char character = (char) current;
                if (character == '0') {
                    addNodeToQueue(new HuffmanTreeNode<>());
                } else {
                    CharChain chain = new CharChain(sizeOfSequence);
                    for (int i = 0; i < sizeOfSequence; i++) {
                        String readNextUtf8Char = readNextUtf8Char(fr);
                        if (Objects.equals(readNextUtf8Char, BREAK_CHAIN)) {
                            chain = null;
                            break;
                        }
                        Log.info("Read next UTF-8 char: " + readNextUtf8Char);
                        chain.add(convertBinaryToInt(readNextUtf8Char));
                    }

                    HuffmanTreeNode<String, CharChain> node = new HuffmanTreeNode<>("", chain);
                    attachToParent(node);
                }
            }
            HuffmanTree<String, CharChain> huffmanTree = new HuffmanTreeCodesForKeys<>(root);

            for (HuffmanTreeNode<String, CharChain> stringCharChainHuffmanTreeNode : huffmanTree) {
                WordNode<String, CharChain> node = stringCharChainHuffmanTreeNode.getWordNode();
                if (node != null) {
                    dictionary.addAt(node);
                }
            }

            dictionary.writeTree();

            Log.info("Queue is empty: " + queue.isEmpty());
            return dictionary;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readNextUtf8Char(InputStreamReader fr) throws IOException {
        StringBuilder sb = new StringBuilder();
        int current = fr.read();
        sb.append((char) current);
        int counter = 1;
        if (current == '1') {
            while (true){
                current = fr.read();
                counter++;
                sb.append((char) current);
                if (current == '0') {
                    break;
                }
            }
            if (sb.toString().equals(BREAK_CHAIN)) {
                Log.info("BREAK_CHAIN found");
                return sb.toString();
            }
        }


        for (int i = counter; i < (counter==1?counter:counter-1) * 8; i++) {
            current = fr.read();
            sb.append((char) current);
        }
        return sb.toString();
    }
}
