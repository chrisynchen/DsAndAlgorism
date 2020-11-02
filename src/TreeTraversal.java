import java.util.LinkedList;
import java.util.Queue;

/**
 * 1
 * /   \
 * 2    3
 * /\
 * 4 5
 * <p>
 * Depth First Traversals:
 * - Inorder (Left, Root, Right) : 4 2 5 1 3
 * - Preorder (Root, Left, Right) : 1 2 4 5 3
 * - Postorder (Left, Right, Root) : 4 5 2 3 1
 * <p>
 * Breadth First or Level Order Traversal : 1 2 3 4 5
 */
public class TreeTraversal {
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
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        dfsInOrder(root);
        System.out.print("\n");
        dfsPreOrder(root);
        System.out.print("\n");
        dfsPostOrder(root);
        System.out.print("\n");

        int height = getTreeHeight(root);
        for (int i = 1; i <= height; i++) {
            bfsRecursively(root, i);
        }

        System.out.print("\n");
        bfsIteratively(root);
    }

    private static void dfsInOrder(TreeNode node) {
        if (node == null) return;

        dfsInOrder(node.left);
        System.out.print(node.val);
        dfsInOrder(node.right);
    }

    private static void dfsPreOrder(TreeNode node) {
        if (node == null) return;

        System.out.print(node.val);
        dfsPreOrder(node.left);
        dfsPreOrder(node.right);
    }

    private static void dfsPostOrder(TreeNode node) {
        if (node == null) return;

        dfsPostOrder(node.left);
        dfsPostOrder(node.right);
        System.out.print(node.val);
    }

    private static void bfsRecursively(TreeNode node, int level) {
        if (node == null)
            return;

        if (level == 1) {
            System.out.print(node.val);
        } else if (level > 1) {
            bfsRecursively(node.left, level - 1);
            bfsRecursively(node.right, level - 1);
        }
    }

    private static int getTreeHeight(TreeNode node) {
        if (node == null) return 0;

        return 1 + Math.max(getTreeHeight(node.left), getTreeHeight(node.right));
    }

    private static void bfsIteratively(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val);

            if (node.left != null)
                queue.add(node.left);

            if (node.right != null)
                queue.add(node.right);
        }

    }
}
