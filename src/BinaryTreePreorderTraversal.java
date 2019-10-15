import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * <p>
 * Example:
 * <p>
 * Input: [1,null,2,3]
 * 1
 *  \
 *   2
 *  /
 * 3
 * <p>
 * Output: [1,2,3]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePreorderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        final TreeNode node = new TreeNode(1);
        node.right = new TreeNode(2);
        node.right.left = new TreeNode(3);

        System.out.println(preorderTraversal(node).toString());
    }

    private static List<Integer> preorderTraversal(TreeNode root) {
        final List<Integer> result = new ArrayList<>();
        if (root != null) {
            addNode(root, result);
        }
        return result;
    }

    private static void addNode(TreeNode node, List<Integer> list) {
        list.add(node.val);
        if (node.left != null) {
            addNode(node.left, list);
        }

        if (node.right != null) {
            addNode(node.right, list);
        }
    }
}
