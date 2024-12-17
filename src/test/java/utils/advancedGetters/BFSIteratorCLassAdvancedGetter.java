package utils.advancedGetters;

public class BFSIteratorCLassAdvancedGetter {
    // create instacne of private class BFSIterator from RedBlackTree class
    public static <K extends Comparable<K>> Object getBFSIterator(Object tree) {
        try {
            Class<?> innerClass = Class.forName("org.example.common.structures.RedBlackTree$BFSIterator");
            return innerClass.getDeclaredConstructor(tree.getClass()).newInstance(tree);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
