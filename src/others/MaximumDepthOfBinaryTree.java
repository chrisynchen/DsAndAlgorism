package others;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, find its maximum depth.
 * <p>
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Given binary tree [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its depth = 3.
 */
public class MaximumDepthOfBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        final TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);

        System.out.println(findMaxDepth(node));
    }

    private static int findMaxDepth(TreeNode root) {
        if (root == null) return 0;

        return findMaxDepth(0, root);
    }

    private static int findMaxDepth(int level, TreeNode node) {
        int count = ++level;
        if (node.left != null) {
            count = Math.max(count, findMaxDepth(level, node.left));
        }

        if (node.right != null) {
            count = Math.max(count, findMaxDepth(level, node.right));
        }

        return count;
    }
}
