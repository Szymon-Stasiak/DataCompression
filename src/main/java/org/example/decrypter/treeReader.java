package org.example.decrypter;

import org.example.common.structures.CharChain;
import org.example.common.structures.RedBlackTree;
import org.example.common.structures.WordNode;
import org.example.encrypter.structures.HuffmanTree;
import org.example.encrypter.structures.HuffmanTreeNode;
import org.example.logger.Log;
import org.example.encrypter.structures.Queue;

import java.io.*;

import static org.example.common.tools.treeTranslator.convertBinaryToInt;

public class treeReader {

    private static final Queue<HuffmanTreeNode> queue = new Queue<>();
    private static HuffmanTreeNode root;
    private static int sizeOfSequence;

    public static void addNodeToQueue(HuffmanTreeNode node){
        queue.add(node);
        if(root==null){
            root=node;
        }else{
            attachToParent(node);
        }

    }

    public static void attachToParent(HuffmanTreeNode node){
        HuffmanTreeNode parent = queue.peek();
        if(parent.getLeft()==null){
            parent.setLeft(node);
        }else{
            parent.setRight(node);
            queue.poll();
        }
    }


    public static RedBlackTree<CharChain> generateDictionary(){
        int current;
        try {
            InputStreamReader fr = new InputStreamReader(new FileInputStream( "C:\\Users\\stszy\\IdeaProjects\\DataCompression\\src\\main\\resources\\tree.txt"),"UTF-8");
            //Todo
            RedBlackTree<CharChain> dictionary = new RedBlackTree<>();
            current=  fr.read();
            current=  fr.read();
            sizeOfSequence=1;
            while((current=fr.read())!=-1){
                char character=(char)current;
                if(character=='0') {
                    addNodeToQueue(new HuffmanTreeNode());
                }else {
                    CharChain chain = new CharChain(sizeOfSequence);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < sizeOfSequence; i++) {
                        for (int j = 0; j < 8; j++) {
                            current = fr.read();
                            sb.append((char) current);
                        }
                        chain.add(convertBinaryToInt(sb.toString()));
                    }
                    Log.info("Chain: " + chain.toChars());
                    WordNode<CharChain> word = dictionary.addAt(new WordNode<>(chain));
                    HuffmanTreeNode node = new HuffmanTreeNode(word);
                    attachToParent(node);

                }
            }
            Log.info("Queue is empty: " + queue.isEmpty());
            new HuffmanTree(root);
            return dictionary;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
