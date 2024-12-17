package org.example.encrypter.structures;

import lombok.Getter;
import org.example.common.structures.RedBlackTree;
import org.example.common.structures.WordNode;

import java.util.Iterator;


public class HuffmanTree implements Iterable<HuffmanTreeNode> {

    @Getter
    private HuffmanTreeNode root;
    private final HuffmanNodePriorityQueue heap;

    public HuffmanTree(RedBlackTree redBlackTree) {
        heap = new HuffmanNodePriorityQueue(redBlackTree);
        if (heap.getSize() == 1) {
            handleWhenOnlyOneSequenceExist();
        } else {
            buildTree();
            generateCodes();
        }
    }

    public void handleWhenOnlyOneSequenceExist() {
        root = heap.poll();
        root.getWordNode().setCode("0");
    }

    private void buildTree() {
        while (heap.getSize() > 1) {
            HuffmanTreeNode left = heap.poll();
            HuffmanTreeNode right = heap.poll();
            HuffmanTreeNode parent = new HuffmanTreeNode(left, right);
            heap.add(parent);
        }
        root = heap.poll();
    }

    private void generateCodes() {
        generateCodes(root, "");
    }

    // TODO delete recursion
    private void generateCodes(HuffmanTreeNode node, String code) {
        if (node.getLeft() == null && node.getRight() == null) {
            node.getWordNode().setCode(code);
            return;
        }
        generateCodes(node.getRight(), code + "1");
        generateCodes(node.getLeft(), code + "0");
    }

    @Override
    public Iterator<HuffmanTreeNode> iterator() {
        return new BFSIterator();
    }

    private class BFSIterator implements Iterator<HuffmanTreeNode> {
        private final QueueBFS<HuffmanTreeNode> queue;
        private HuffmanTreeNode current;

        public BFSIterator() {
            queue = new QueueBFS<>();
            if (root != null) {
                queue.add(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public HuffmanTreeNode next() {
            current = queue.poll();
            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
            return current;
        }
    }


}


