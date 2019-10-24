/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class PathSum {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        final TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(4);

        System.out.println(hasPathSum(root, 22));
    }

    private static boolean hasPathSum(TreeNode root, int sum) {
        return hasResult(root, 0, sum);
    }

    private static boolean hasResult(TreeNode node, int currentSum, int sum) {

        if(node == null) return false;

        return ((currentSum + node.val) == sum && node.left == null && node.right == null) || hasResult(node.left, (currentSum + node.val), sum)
                || hasResult(node.right, (currentSum + node.val), sum);
    }
}
