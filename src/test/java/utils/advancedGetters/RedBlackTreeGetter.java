package utils.advancedGetters;

import java.lang.reflect.Field;
import org.example.common.structures.Dictionary;
import org.example.common.structures.WordNode;

public class RedBlackTreeGetter {
    public static <K extends Comparable<K>, V> WordNode<K, V> getRootOfTree(Dictionary<K, V> tree) {
        String fieldName = "root";
        try {
            WordNode<K, V> node = null;

            Field field = tree.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            node = (WordNode<K, V>) field.get(tree);

            return node;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
