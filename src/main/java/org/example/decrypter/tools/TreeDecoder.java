package org.example.decrypter.tools;

import static org.example.common.tools.UTF8Converter.convertBinaryToInt;

import java.io.*;
import java.util.Iterator;

import lombok.NonNull;
import org.example.common.structures.CharChain;
import org.example.common.structures.Dictionary;
import org.example.common.structures.HuffmanTree;
import org.example.common.structures.HuffmanTreeNode;
import org.example.common.structures.Queue;
import org.example.common.structures.WordNode;
import org.example.common.tools.BinaryConverter;
import org.example.logger.Log;

// todo cleaning
public class TreeDecoder {

    private static final Queue<HuffmanTreeNode<String,CharChain>> queue = new Queue<>();
    private static HuffmanTreeNode<String,CharChain> root;

    public static void addNodeToQueue(HuffmanTreeNode<String,CharChain> node) {
        queue.add(node);
        if (root == null) {
            root = node;
        } else {
            attachToParent(node);
        }
    }

    public static void attachToParent(HuffmanTreeNode<String,CharChain> node) {
        HuffmanTreeNode<String,CharChain> parent = queue.peek();
        if (parent.getLeft() == null) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
            queue.poll();
        }
    }

    public static Dictionary<String,CharChain> generateDictionary(StringBuilder byteReader, InputStreamReader fr) {
        int current;
        try {

            // Todo
            Dictionary<String,CharChain> dictionary = new Dictionary<>();
            boolean hasOneSequence = fr.read() == '1';
            StringBuilder sequence = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                sequence.append((char) fr.read());
            }
            Log.info("Sequence: " + sequence);
            int sizeOfSequence = BinaryConverter.convertBinToInt(sequence.toString()) + 1;

            if (hasOneSequence) {
                return generateDictionaryFromOneSequence();
            }

            addNodeToQueue(new HuffmanTreeNode<>());
            while (!queue.isEmpty()) {
                current = fr.read();

                char character = (char) current;
               // Log.info("Character: " + character);
                if (character == '0') {
                    addNodeToQueue(new HuffmanTreeNode<>());
                } else {
                    CharChain chain = new CharChain(sizeOfSequence);
                    for (int i = 0; i < sizeOfSequence; i++) {
                        chain.add(convertBinaryToInt(readNextUtf8Char(fr)));
                    }

                    HuffmanTreeNode<String,CharChain> node = new HuffmanTreeNode<>("", chain);
                    attachToParent(node);
                }
            }
            HuffmanTree<String,CharChain>huffmanTree = new HuffmanTree<>(root);

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
        int current;
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            current = fr.read();
            if (current == -1) {
                return null;
            }
            counter++;
            sb.append((char) current);

            if (current == '0') {
                break;
            }
        }
       // Log.info("Counter: " + counter);
        for (int i = counter; i < counter * 8; i++) {
            current = fr.read();
            if (current == -1) {
                return null;
            }
            sb.append((char) current);
        }
        return sb.toString();
    }

    public static Dictionary<String,CharChain> generateDictionaryFromOneSequence() {
        // todo
        return null;
    }
}
