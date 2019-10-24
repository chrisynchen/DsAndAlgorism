/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * <p>
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 * <p>
 *      1
 *     / \
 *    2   2
 *   / \ / \
 *  3  4 4  3
 * <p>
 * <p>
 * But the following [1,2,2,null,3,null,3] is not:
 * <p>
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * <p>
 * <p>
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 */
public class SymmetricTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        final TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println(isSymmetric(root));
    }

    private static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        if (root.left == null && root.right == null) return true;

        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;

        return isMirror(leftNode, rightNode);
    }

    private static boolean isMirror(TreeNode leftNode, TreeNode rightNode) {

        if (leftNode == null || rightNode == null) {
            return leftNode == null && rightNode == null;
        }

        return leftNode.val == rightNode.val && isMirror(leftNode.left, rightNode.right) && isMirror(leftNode.right, rightNode.left);
    }
}
