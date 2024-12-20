package org.example.decrypter.tools;

import static org.example.common.tools.UTF8Converter.convertBinaryToInt;

import java.io.*;
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

    private static final Queue<HuffmanTreeNode> queue = new Queue<>();
    private static HuffmanTreeNode root;
    private static int sizeOfSequence;

    public static void addNodeToQueue(HuffmanTreeNode node) {
        queue.add(node);
        if (root == null) {
            root = node;
        } else {
            attachToParent(node);
        }
    }

    public static void attachToParent(HuffmanTreeNode node) {
        HuffmanTreeNode parent = queue.peek();
        if (parent.getLeft() == null) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
            queue.poll();
        }
    }

    public static Dictionary<CharChain> generateDictionary() {
        int current;
        try {
            InputStreamReader fr = new InputStreamReader(
                    new FileInputStream(
                            "C:\\Users\\stszy\\IdeaProjects\\DataCompression\\src\\main\\resources\\tree.txt"),
                    "UTF-8");
            // Todo
            Dictionary<CharChain> dictionary = new Dictionary<>();
            boolean hasOneSequence = fr.read() == '1';
            StringBuilder sequence = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                sequence.append((char) fr.read());
            }
            Log.info("Sequence: " + sequence);
            sizeOfSequence = BinaryConverter.convertBinToInt(sequence.toString()) + 1;

            if (hasOneSequence) {
                return generateDictionaryFromOneSequence();
            }

            addNodeToQueue(new HuffmanTreeNode());
            while (!queue.isEmpty()) {
                current = fr.read();

                char character = (char) current;
                Log.info("Character: " + character);
                if (character == '0') {
                    addNodeToQueue(new HuffmanTreeNode());
                } else {
                    CharChain chain = new CharChain(sizeOfSequence);
                    for (int i = 0; i < sizeOfSequence; i++) {
                        chain.add(convertBinaryToInt(readNextUtf8Char(fr)));
                    }
                    WordNode<CharChain> word = dictionary.addAt(new WordNode<>(chain));
                    HuffmanTreeNode node = new HuffmanTreeNode(word);
                    attachToParent(node);
                }
            }
            Log.info("Queue is empty: " + queue.isEmpty());
            new HuffmanTree(root);
            fr.close();
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
        Log.info("Counter: " + counter);
        for (int i = counter; i < counter * 8; i++) {
            current = fr.read();
            if (current == -1) {
                return null;
            }
            sb.append((char) current);
        }
        Log.info("Utf8: " + sb.toString());
        return sb.toString();
    }

    public static Dictionary<CharChain> generateDictionaryFromOneSequence() {
        // todo
        return null;
    }
}