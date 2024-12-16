package org.example.encrypter;

import java.util.ArrayList;
import java.util.Iterator;
import org.example.common.RedBlackTree;
import org.example.common.WordNode;
import org.example.service.HeapPriorityQueue;

public class HuffmanNodePriorityQueue extends HeapPriorityQueue<HuffmanTreeNode> {

    public HuffmanNodePriorityQueue(RedBlackTree redBlackTree) {
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
