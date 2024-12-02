package others;

import java.util.HashMap;
import java.util.Map;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * For example, given
 * <p>
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class ConstructBinaryTreeInorderPostorderTraversal {

    private static final Map<Integer, Integer> map = new HashMap<>();

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        //inorder postorder
        System.out.println(buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3}));
    }

    private static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) return null;

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        final int rootValue = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootValue);

        final int inorderRootIndex = map.get(rootValue);

        return root;
    }
}
