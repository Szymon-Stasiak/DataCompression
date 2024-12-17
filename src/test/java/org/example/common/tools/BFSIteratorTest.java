package org.example.common.tools;

import org.example.common.structures.WordNode;
import org.junit.jupiter.api.Test;

class BFSIteratorTest {

    @Test
    void should_properly_checkIfHasNext() {
        WordNode<String> wordNode = new WordNode<>("word", 1);
        BFSIterator<WordNode<String>> bfsIterator = new BFSIterator<>(wordNode);

        assert (bfsIterator.hasNext());
        bfsIterator.next();
        assert (!bfsIterator.hasNext());
    }

    @Test
    void should_properly_goThroughEveryNodeBFS() {

        WordNode<String> wordNode = new WordNode<>("word", 1);
        WordNode<String> wordNode2 = new WordNode<>("word2", 2);
        WordNode<String> wordNode3 = new WordNode<>("word3", 3);
        WordNode<String> wordNode4 = new WordNode<>("word4", 4);
        WordNode<String> wordNode5 = new WordNode<>("word5", 5);

        wordNode.setLeft(wordNode2);
        wordNode.setRight(wordNode3);
        wordNode2.setLeft(wordNode4);
        wordNode2.setRight(wordNode5);

        BFSIterator<WordNode<String>> bfsIterator = new BFSIterator<>(wordNode);

        assert (bfsIterator.next() == wordNode);
        assert (bfsIterator.next() == wordNode2);
        assert (bfsIterator.next() == wordNode3);
        assert (bfsIterator.next() == wordNode4);
        assert (bfsIterator.next() == wordNode5);
        assert (!bfsIterator.hasNext());
    }

    @Test
    void should_properly_goThroughEveryNodeBFS_whenGoOnlyToLeft() {

        WordNode<String> wordNode = new WordNode<>("word", 1);
        WordNode<String> wordNode2 = new WordNode<>("word2", 2);
        WordNode<String> wordNode3 = new WordNode<>("word3", 3);
        WordNode<String> wordNode4 = new WordNode<>("word4", 4);
        WordNode<String> wordNode5 = new WordNode<>("word5", 5);

        wordNode.setLeft(wordNode2);
        wordNode2.setLeft(wordNode3);
        wordNode3.setLeft(wordNode4);
        wordNode4.setLeft(wordNode5);

        BFSIterator<WordNode<String>> bfsIterator = new BFSIterator<>(wordNode);

        assert (bfsIterator.next() == wordNode);
        assert (bfsIterator.next() == wordNode2);
        assert (bfsIterator.next() == wordNode3);
        assert (bfsIterator.next() == wordNode4);
        assert (bfsIterator.next() == wordNode5);
        assert (!bfsIterator.hasNext());
    }

    @Test
    void should_properly_goThroughEveryNodeBFS_whenGoOnlyToRight() {

        WordNode<String> wordNode = new WordNode<>("word", 1);
        WordNode<String> wordNode2 = new WordNode<>("word2", 2);
        WordNode<String> wordNode3 = new WordNode<>("word3", 3);
        WordNode<String> wordNode4 = new WordNode<>("word4", 4);
        WordNode<String> wordNode5 = new WordNode<>("word5", 5);

        wordNode.setRight(wordNode2);
        wordNode2.setRight(wordNode3);
        wordNode3.setRight(wordNode4);
        wordNode4.setRight(wordNode5);

        BFSIterator<WordNode<String>> bfsIterator = new BFSIterator<>(wordNode);

        assert (bfsIterator.next() == wordNode);
        assert (bfsIterator.next() == wordNode2);
        assert (bfsIterator.next() == wordNode3);
        assert (bfsIterator.next() == wordNode4);
        assert (bfsIterator.next() == wordNode5);
        assert (!bfsIterator.hasNext());
    }

    @Test
    void should_properly_goThroughEveryNodeBFS10() {

        WordNode<String> wordNode = new WordNode<>("word", 1);
        WordNode<String> wordNode2 = new WordNode<>("word2", 2);
        WordNode<String> wordNode3 = new WordNode<>("word3", 3);
        WordNode<String> wordNode4 = new WordNode<>("word4", 4);
        WordNode<String> wordNode5 = new WordNode<>("word5", 5);
        WordNode<String> wordNode6 = new WordNode<>("word6", 6);
        WordNode<String> wordNode7 = new WordNode<>("word7", 7);
        WordNode<String> wordNode8 = new WordNode<>("word8", 8);
        WordNode<String> wordNode9 = new WordNode<>("word9", 9);
        WordNode<String> wordNode10 = new WordNode<>("word10", 10);

        wordNode.setLeft(wordNode2);
        wordNode.setRight(wordNode3);
        wordNode2.setLeft(wordNode4);
        wordNode2.setRight(wordNode5);
        wordNode3.setLeft(wordNode6);
        wordNode3.setRight(wordNode7);
        wordNode4.setLeft(wordNode8);
        wordNode4.setRight(wordNode9);
        wordNode5.setLeft(wordNode10);

        BFSIterator<WordNode<String>> bfsIterator = new BFSIterator<>(wordNode);

        assert (bfsIterator.next() == wordNode);
        assert (bfsIterator.next() == wordNode2);
        assert (bfsIterator.next() == wordNode3);
        assert (bfsIterator.next() == wordNode4);
        assert (bfsIterator.next() == wordNode5);
        assert (bfsIterator.next() == wordNode6);
        assert (bfsIterator.next() == wordNode7);
        assert (bfsIterator.next() == wordNode8);
        assert (bfsIterator.next() == wordNode9);
        assert (bfsIterator.next() == wordNode10);
        assert (!bfsIterator.hasNext());
    }
}
