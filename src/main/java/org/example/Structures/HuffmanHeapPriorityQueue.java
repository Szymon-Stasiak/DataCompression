package org.example.Structures;

import java.util.ArrayList;
import java.util.Iterator;
import org.example.RbtMap;
import org.example.Structures.Basics.HuffmanTreeNode;
import org.example.Structures.Basics.WordNode;
import org.example.service.HeapPriorityQueue;

public class HuffmanHeapPriorityQueue extends HeapPriorityQueue<HuffmanTreeNode> {

    public HuffmanHeapPriorityQueue(RbtMap redBlackTree) {
        heap = new ArrayList<>();
        Iterator iterator = redBlackTree.iterator();
        while (iterator.hasNext()) {
            WordNode node = (WordNode) iterator.next();
            heap.add(new HuffmanTreeNode(node));
            size++;
        }
        buildHeap();
    }
}
