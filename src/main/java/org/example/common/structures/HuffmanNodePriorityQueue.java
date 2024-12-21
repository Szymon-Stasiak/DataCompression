package org.example.common.structures;

import java.util.ArrayList;
import org.example.service.HeapPriorityQueue;

public class HuffmanNodePriorityQueue<K extends Comparable<K>, V> extends HeapPriorityQueue<HuffmanTreeNode<K, V>> {

    public HuffmanNodePriorityQueue(Dictionary<K, V> dictionary) {
        heap = new ArrayList<>();
        for (WordNode<K, V> charChainWordNode : dictionary) {
            heap.add(new HuffmanTreeNode<K, V>(charChainWordNode));
            size++;
        }
        buildHeap();
    }
}
