package org.example.common.structures;

import static org.junit.jupiter.api.Assertions.*;
import static utils.advancedGetters.RedBlackTreeGetter.getRootOfTree;
import static utils.reusableTests.CheckIfRedBlackTree.checkTree;
import static utils.reusableTests.CheckIfRedBlackTree.countSizeOfTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RedBlackTreeTest {
    Dictionary<String> tree = new Dictionary<>();

    @BeforeEach
    public void setUp() {
        tree = new Dictionary<>();
    }

    @Test
    void shouldProperlyCreateTree() {
        assertNotNull(tree);
    }

    @Test
    void shouldProperlyInsert() {
        tree.addAt("a");
        tree.addAt("b");
        tree.addAt("c");
        tree.addAt("d");
        tree.addAt("e");
        tree.addAt("f");
        tree.addAt("g");
        tree.addAt("h");
        tree.addAt("i");
        tree.addAt("j");
        assertNotNull(tree);
        assert (checkTree(tree));
        assert (!getRootOfTree(tree).isRed());
        assert (countSizeOfTree(tree) == 10);
    }

    @Test
    void shouldProperlyInsert_WhenInsertingSameElement() {
        tree.addAt("a");
        tree.addAt("a");
        tree.addAt("a");
        tree.addAt("a");
        tree.addAt("a");
        tree.addAt("a");
        tree.addAt("a");
        tree.addAt("a");
        tree.addAt("a");
        assertNotNull(tree);
        assert (checkTree(tree));
        assert (!getRootOfTree(tree).isRed());
        assert (countSizeOfTree(tree) == 1);
    }

    //    @Test
    //    void should_ThrowException_WhenInsertingNull() {
    //        try {
    //            tree.addAt(null);
    //        } catch (IllegalArgumentException e) {
    //            assert (e.getMessage().equals("Key cannot be null."));
    //            assert (true);
    //        }
    //    }

    @Test
    void shouldProperlyInsert_WhenInsertingInOrder() {
        tree.addAt("a");
        tree.addAt("b");
        tree.addAt("c");
        tree.addAt("d");
        tree.addAt("e");
        tree.addAt("f");
        tree.addAt("g");
        tree.addAt("h");
        tree.addAt("i");
        tree.addAt("j");
        assertNotNull(tree);
        assert (checkTree(tree));
        assert (!getRootOfTree(tree).isRed());
        assert (countSizeOfTree(tree) == 10);
    }

    @Test
    void shouldProperlyInsert_WhenInsertingInReverseOrder() {
        tree.addAt("j");
        tree.addAt("i");
        tree.addAt("h");
        tree.addAt("g");
        tree.addAt("f");
        tree.addAt("e");
        tree.addAt("d");
        tree.addAt("c");
        tree.addAt("b");
        tree.addAt("a");
        assertNotNull(tree);
        assert (checkTree(tree));
        assert (!getRootOfTree(tree).isRed());
        assert (countSizeOfTree(tree) == 10);
    }

    @Test
    public void should_CorrectlyBuildTreeFromFiveObject() {
        tree.addAt("a");
        tree.addAt("a");
        tree.addAt("a");
        tree.addAt("a");
        tree.addAt("b");
        tree.addAt("c");
        tree.addAt("c");
        tree.addAt("c");
        tree.addAt("d");
        tree.addAt("e");
        assertNotNull(tree);
        assert (checkTree(tree));
        assert (!getRootOfTree(tree).isRed());
        assert (countSizeOfTree(tree) == 5);
        WordNode<String> root = getRootOfTree(tree);
        assert (root.getKey().equals("d"));
        assert (root.getRight().getKey().equals("e"));
        assert (root.getLeft().getKey().equals("b"));
        root = root.getRight();
        assert (root.getKey().equals("e"));
        root = getRootOfTree(tree).getLeft();
        assert (root.getLeft().getKey().equals("a"));
        assert (root.getRight().getKey().equals("c"));
    }

    @ParameterizedTest
    @ValueSource(
            strings = {
                "GARWOLIN",
                "BRUKSELA",
                "ALGORYTM",
                "SOCZEWKA",
                "KOMPUTER",
                "ABECADLA",
                "?OD?",
                "KOT",
                "PROGRAMISTA",
                "SAMOCHOD",
                "ELEKTRONIKA",
                "PODRO?E",
                "SZKO?A",
                "Z?OTY",
                "PRZYSZ?O??",
                "NAUKA",
                "PRZYK?AD"
            })
    public void should_ReorganizeTreeCorrectly_When_Put_With_GivenCharArray(String word) {
        Dictionary<Character> tree = new Dictionary<>();

        for (char c : word.toCharArray()) {
            tree.addAt(c);
        }

        assert (checkTree(tree));
    }

    @Test
    public void should_properHandleGetNodeWhenRootIsNull() {
        assert (tree.getNode("a") == null);
    }

    //    @Test
    //    public void should_properHandleGetCodeWhenRootIsNull() {
    //        Class<?> wordTreeClass = null; // Zmie? nazw? pakietu na w?a?ciwy
    //        try {
    //            wordTreeClass = Class.forName("com.example.commonstructures.RedBlackTree");
    //
    //        Constructor<?> constructor = wordTreeClass.getDeclaredConstructor();
    //
    //        constructor.setAccessible(true);
    //
    //        Object wordTreeInstance = constructor.newInstance();
    //
    //        Field rootField = wordTreeClass.getDeclaredField("root");
    //        rootField.setAccessible(true);
    //
    //        Class<?> wordNodeClass = Class.forName("com.example.WordNode");
    //        Constructor<?> wordNodeConstructor = wordNodeClass.getDeclaredConstructor(Object.class);
    //        wordNodeConstructor.setAccessible(true);
    //
    //        Object rootNode = wordNodeConstructor.newInstance("root");
    //        rootField.set(wordTreeInstance, rootNode);
    //
    //        Method iteratorMethod = wordTreeClass.getDeclaredMethod("iterator");
    //        Iterator<?> iterator = (Iterator<?>) iteratorMethod.invoke(wordTreeInstance);
    //
    //        assertTrue(iterator.hasNext());
    //        Object firstNode = iterator.next();
    //        assertNotNull(firstNode);
    //
    //        assertFalse(iterator.hasNext());
    //        } catch (ClassNotFoundException e) {
    //            throw new RuntimeException(e);
    //        } catch (NoSuchFieldException e) {
    //            throw new RuntimeException(e);
    //        } catch (InvocationTargetException e) {
    //            throw new RuntimeException(e);
    //        } catch (NoSuchMethodException e) {
    //            throw new RuntimeException(e);
    //        } catch (InstantiationException e) {
    //            throw new RuntimeException(e);
    //        } catch (IllegalAccessException e) {
    //            throw new RuntimeException(e);
    //        }
    //    }

}
