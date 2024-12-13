package utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import org.example.Structures.Basics.TreeNode;
import org.example.Structures.HeapPriorityQueue;
import org.example.Structures.HuffmanTree;

public class AdvancedGetters {

    public static ArrayList<Comparable> getHeap(HeapPriorityQueue heapPriorityQueue) {
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

    public static TreeNode getRoot(HuffmanTree huffmanTree) {
        String fieldName = "root";
        try {
            TreeNode root = null;

            Field field = huffmanTree.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            root = (TreeNode) field.get(huffmanTree);

            return root;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
