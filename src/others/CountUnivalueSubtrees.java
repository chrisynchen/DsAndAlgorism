package others;

/**
 * Given a binary tree, count the number of uni-value subtrees.
 * <p>
 * A Uni-value subtree means all nodes of the subtree have the same value.
 * <p>
 * Example :
 * <p>
 * Input:  root = [5,1,5,5,5,null,5]
 * <p>
 * 5
 * /  \
 * 1    5
 * / \    \
 * 5   5   5
 * <p>
 * Output: 4
 *
 * Time Complexity: O(N)
 * Space Complexity: O(H), H is tree height
 */
public class CountUnivalueSubtrees {

    static int sum;

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
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(5);

        System.out.println(countUnivalSubtrees(root));
    }

    private static int countUnivalSubtrees(TreeNode root) {
        isUnivalSubtrees(root, 0);
        return sum;
    }

    //DFS solution
    private static boolean isUnivalSubtrees(TreeNode node, int parentValue) {

        if(node == null) return true;

        // two of them need run
        if(!isUnivalSubtrees(node.left, node.val) | !isUnivalSubtrees(node.right, node.val)) return false;

        sum++;

        return node.val == parentValue;
    }
}
