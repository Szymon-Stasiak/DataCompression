package org.example.Structures.Basics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Mocks.pairMock;

import org.junit.jupiter.api.Test;

class TreeNodeTest {

    @Test
    void should_properlyCreateTreeNode() {
        Pair pairMock = pairMock('a', 1);

        TreeNode treeNode = new TreeNode(pairMock);

        assertEquals(1, treeNode.getValue());
    }

    @Test
    void should_properlyCreateTreeNodeWithLeftAndRight() {
        Pair leftPairMock = pairMock('a', 1);
        Pair rightPairMock = pairMock('b', 1);

        TreeNode left = new TreeNode(leftPairMock);
        TreeNode right = new TreeNode(rightPairMock);

        TreeNode treeNode = new TreeNode(left, right);

        assertEquals(2, treeNode.getValue());
        assertEquals(left, treeNode.getLeft());
        assertEquals(right, treeNode.getRight());
    }

    @Test
    void should_properlyCompareTo_WhenEqual() {
        Pair pairMock1 = pairMock('a', 2);
        Pair pairMock2 = pairMock('b', 2);

        TreeNode treeNode1 = new TreeNode(pairMock1);
        TreeNode treeNode2 = new TreeNode(pairMock2);

        assertEquals(0, treeNode1.compareTo(treeNode2));
    }

    @Test
    void should_properlyCompareTo_WhenGreater() {
        Pair pairMock1 = pairMock('a', 3);
        Pair pairMock2 = pairMock('b', 2);

        TreeNode treeNode1 = new TreeNode(pairMock1);
        TreeNode treeNode2 = new TreeNode(pairMock2);

        assertEquals(1, treeNode1.compareTo(treeNode2));
    }

    @Test
    void should_properlyCompareTo_WhenLess() {
        Pair pairMock1 = pairMock('a', 1);
        Pair pairMock2 = pairMock('b', 2);

        TreeNode treeNode1 = new TreeNode(pairMock1);
        TreeNode treeNode2 = new TreeNode(pairMock2);

        assertEquals(-1, treeNode1.compareTo(treeNode2));
    }
}
