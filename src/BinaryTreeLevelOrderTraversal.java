import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *   3
 *  / \
 * 9  20
 *   /  \
 *  15   7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class BinaryTreeLevelOrderTraversal {
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

        System.out.println(levelOrder(node).toString());
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        final List<List<Integer>> list = new ArrayList<>();
        if (root != null) {
            addLevelOrder(0, root, list);
        }

        return list;
    }

    private static void addLevelOrder(int level, TreeNode node, List<List<Integer>> list) {
        if (list.size() <= level) {
            final List<Integer> subList = new ArrayList<>();
            subList.add(node.val);
            list.add(subList);
        } else {
            final List<Integer> subList = list.get(level);
            subList.add(node.val);
        }

        if (node.left != null) {
            addLevelOrder(++level, node.left, list);
        }

        if (node.right != null) {
            addLevelOrder(++level, node.right, list);
        }
    }
}
