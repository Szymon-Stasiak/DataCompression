// package org.example.Structures.Basics;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static utils.Mocks.pairMock;
//
// import org.junit.jupiter.api.Test;
//
// class TreeNodeTest {
//
//    @Test
//    void should_properlyCreateTreeNode() {
//        WordNode pairMock = pairMock('a', 1);
//
//        HuffmanTreeNode treeNode = new HuffmanTreeNode(pairMock);
//
//        assertEquals(1, treeNode.getValue());
//    }
//
//    @Test
//    void should_properlyCreateTreeNodeWithLeftAndRight() {
//        WordNode leftPairMock = pairMock('a', 1);
//        WordNode rightPairMock = pairMock('b', 1);
//
//        HuffmanTreeNode left = new HuffmanTreeNode(leftPairMock);
//        HuffmanTreeNode right = new HuffmanTreeNode(rightPairMock);
//
//        HuffmanTreeNode treeNode = new HuffmanTreeNode(left, right);
//
//        assertEquals(2, treeNode.getValue());
//        assertEquals(left, treeNode.getLeft());
//        assertEquals(right, treeNode.getRight());
//    }
//
//    @Test
//    void should_properlyCompareTo_WhenEqual() {
//        WordNode pairMock1 = pairMock('a', 2);
//        WordNode pairMock2 = pairMock('b', 2);
//
//        HuffmanTreeNode treeNode1 = new HuffmanTreeNode(pairMock1);
//        HuffmanTreeNode treeNode2 = new HuffmanTreeNode(pairMock2);
//
//        assertEquals(0, treeNode1.compareTo(treeNode2));
//    }
//
//    @Test
//    void should_properlyCompareTo_WhenGreater() {
//        WordNode pairMock1 = pairMock('a', 3);
//        WordNode pairMock2 = pairMock('b', 2);
//
//        HuffmanTreeNode treeNode1 = new HuffmanTreeNode(pairMock1);
//        HuffmanTreeNode treeNode2 = new HuffmanTreeNode(pairMock2);
//
//        assertEquals(1, treeNode1.compareTo(treeNode2));
//    }
//
//    @Test
//    void should_properlyCompareTo_WhenLess() {
//        WordNode pairMock1 = pairMock('a', 1);
//        WordNode pairMock2 = pairMock('b', 2);
//
//        HuffmanTreeNode treeNode1 = new HuffmanTreeNode(pairMock1);
//        HuffmanTreeNode treeNode2 = new HuffmanTreeNode(pairMock2);
//
//        assertEquals(-1, treeNode1.compareTo(treeNode2));
//    }
// }
