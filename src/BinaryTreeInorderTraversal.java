import java.util.ArrayList;
import java.util.List;

/**
 * In-order traversal is to traverse the left subtree first. Then visit the root. Finally, traverse the right subtree.
 *
 * Given a binary tree, return the inorder traversal of its nodes' values.
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
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal {
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

        System.out.println(inorderTraversal(node).toString());
    }

    private static List<Integer> inorderTraversal(TreeNode root) {
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

        list.add(node.val);

        if (node.right != null) {
            addNode(node.right, list);
        }
    }
}
