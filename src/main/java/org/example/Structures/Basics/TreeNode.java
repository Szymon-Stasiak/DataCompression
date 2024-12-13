package org.example.Structures.Basics;

import lombok.Data;

@Data
public class TreeNode implements Comparable<TreeNode> {

    private TreeNode left;
    private TreeNode right;
    private Pair pair;
    private int value;

    public TreeNode(Pair pair) {
        this.pair = pair;
        this.value = pair.getCounter();
    }

    public TreeNode(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
        this.value = left.getValue() + right.getValue();
    }

    @Override
    public int compareTo(TreeNode o) {
        return Integer.compare(value, o.getValue());
    }
}
