package org.example.Structures;

import static org.junit.jupiter.api.Assertions.*;
import static utils.ReadyAlphabet.getAlphabet;

import org.example.Structures.Basics.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.AdvancedGetters;

class HuffmanTreeTest {

    private HuffmanTree huffmanTree;

    @BeforeEach
    void setUp() {
        Alphabet alphabet = getAlphabet();
        huffmanTree = new HuffmanTree(alphabet);
    }

    @Test
    void shouldProperlyCreateHuffmanTree() {
        assertNotNull(huffmanTree);
    }

    @Test
    void shouldProperlyBuildTree() {
        assert (checkBuildTree(AdvancedGetters.getRoot(huffmanTree)));
    }

    boolean checkBuildTree(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (node.getLeft() == null && node.getRight() != null) {
            fail("Node has only left child");
        }
        if (node.getLeft() != null && node.getRight() == null) {
            fail("Node has only right child");
        }
        if (node.getLeft() != null) {
            return checkBuildTree(node.getLeft());
        }
        if (node.getRight() != null) {
            return checkBuildTree(node.getRight());
        }
        return true;
    }

    @Test
    void shouldProperlyGenerateCodes() {
        assert (checkCodes(AdvancedGetters.getRoot(huffmanTree).getRight()));
        assert (checkCodes(AdvancedGetters.getRoot(huffmanTree).getLeft()));
    }

    boolean checkCodes(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (node.getLeft() == null && node.getRight() == null && node.getPair().getCode() == null) {
            fail("Code is null");
        }
        if (node.getLeft() != null) {
            return checkCodes(node.getLeft());
        }
        if (node.getRight() != null) {
            return checkCodes(node.getRight());
        }
        return true;
    }
}
