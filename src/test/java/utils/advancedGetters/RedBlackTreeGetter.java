package utils.advancedGetters;

import java.lang.reflect.Field;
import org.example.common.structures.Dictionary;
import org.example.common.structures.WordNode;

public class RedBlackTreeGetter {
    public static <K extends Comparable<K>> WordNode<K> getRootOfTree(Dictionary<K> tree) {
        String fieldName = "root";
        try {
            WordNode<K> node = null;

            Field field = tree.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            node = (WordNode<K>) field.get(tree);

            return node;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
