package utils.reusableTests;

import static utils.advancedGetters.RedBlackTreeGetter.getRootOfTree;

import java.util.LinkedList;
import java.util.Queue;
import org.example.common.structures.RedBlackTree;
import org.example.common.structures.WordNode;

public class CheckIfRedBlackTree {
    public static boolean checkTree(RedBlackTree<?> tree) {
        WordNode<?> root = getRootOfTree(tree);
        if (root == null) return true;
        if (root.isRed()) return false;

        Queue<WordNode<?>> queue = new LinkedList<>();

        WordNode<?> node = root;
        while (node != null) {
            if (node.getLeft() != null && node.getLeft().isRed() && node.isRed()) {
                return false;
            }

            if (node.getRight() != null && node.getRight().isRed()) {
                return false;
            }

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }

            node = queue.poll();
        }
        return true;
    }

    public static long countSizeOfTree(RedBlackTree<?> tree) {
        WordNode<?> head = getRootOfTree(tree);
        if (head == null) return 0;
        long count = 0;
        Queue<WordNode<?>> queue = new LinkedList<>();
        while (head != null) {
            if (head.getLeft() != null) queue.add(head.getLeft());
            if (head.getRight() != null) queue.add(head.getRight());

            count++;
            head = queue.poll();
        }

        return count;
    }
}
