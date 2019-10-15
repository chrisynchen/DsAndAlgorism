import java.util.ArrayList;
import java.util.List;

/**
 *Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [3,2,1]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePostorderTraversal {
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

        System.out.println(postorderTraversal(node).toString());
    }

    private static List<Integer> postorderTraversal(TreeNode root) {
        final List<Integer> result = new ArrayList<>();
        if (root != null) {
            addNode(root, result);
        }
        return result;
    }

    private static void addNode(TreeNode node, List<Integer> list) {
        if (node.left != null) {
            addNode(node.left, list);
        }

        if (node.right != null) {
            addNode(node.right, list);
        }
        list.add(node.val);
    }
}
