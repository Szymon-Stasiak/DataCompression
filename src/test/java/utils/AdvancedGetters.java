package utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import org.example.encrypter.HuffmanNodePriorityQueue;
import org.example.encrypter.HuffmanTree;
import org.example.encrypter.HuffmanTreeNode;

public class AdvancedGetters {

    public static ArrayList<Comparable> getHeap(HuffmanNodePriorityQueue heapPriorityQueue) {
        String fieldName = "heap";
        try {
            ArrayList<Comparable> heap = null;

            Field field = heapPriorityQueue.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            heap = (ArrayList<Comparable>) field.get(heapPriorityQueue);

            return heap;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static HuffmanTreeNode getRoot(HuffmanTree huffmanTree) {
        String fieldName = "root";
        try {
            HuffmanTreeNode root = null;

            Field field = huffmanTree.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            root = (HuffmanTreeNode) field.get(huffmanTree);

            return root;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
