package org.example.common.structures;

import java.util.ArrayList;
import org.example.service.HeapPriorityQueue;

public class HuffmanNodePriorityQueue extends HeapPriorityQueue<HuffmanTreeNode> {

    public HuffmanNodePriorityQueue(Dictionary<CharChain> dictionary) {
        heap = new ArrayList<>();
        for (WordNode<CharChain> charChainWordNode : dictionary) {
            heap.add(new HuffmanTreeNode(charChainWordNode));
            size++;
        }
        buildHeap();
    }
}
