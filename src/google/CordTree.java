package google;

public class CordTree {

//    A cord tree is a binary tree of strings.
//    A node in this tree can be a leaf node or an internal node.
//    An internal node has two children, a left child and a right child. It also has a length of all the children under it
//    A leaf nodes have a value and a length
//
//            #      InternalNode, 26
//            #      /              \
//            #     /                \
//            #    /                  \
//            # Leaf(5, ABCDE)      InternalNode, 21
//            #                       /           \
//            #                      /             \
//            #                     /               \
//            #                    /                 \
//            #         Leaf(10, FGHIJKLMNO)     Leaf(11, PQRSTUVWXYZ)
//    Q1: Define a Data Structure that represents a Cord tree.
//    Q2: Define a function that takes in a tree and an index and returns the character at that index.

    public static void main(String[] args) {
        final InternalNode root = new InternalNode(new LeafNode("ABCDE"),
                new InternalNode(new LeafNode("FGHIJKLMNO"),
                        new LeafNode("PQRSTUVWXYZ")));
        for(int i = 0; i < 26; i++) {
            root.insert(25 - i, (char)('a' + i));
        }
        for (int i = 0; i < root.getLength(); i++) {
            System.out.println(root.getCharacter(i));
        }
    }
}

interface Node {
    int getLength();

    char getCharacter(int index);

    String getVal();

    void insert(int position, char c);
}

class LeafNode implements Node {

    LeafNode(String val) {
        this.val = val;
    }

    private String val;
    //O(1)
    @Override
    public int getLength() {
        return val.length();
    }

    //O(1)
    @Override
    public char getCharacter(int index) {
        if (index < 0 || index >= val.length()) return 0;
        return val.charAt(index);
    }

    //O(1)
    @Override
    public String getVal() {
        return val;
    }

    //O(k), k = val size
    @Override
    public void insert(int position, char c) {
        StringBuilder sb = new StringBuilder();
        if (position == val.length()) {
            val = sb.append(val).append(c).toString();
        } else {
            val = sb.append(val, 0, position).append(c).append(val, position, val.length()).toString();
        }
    }
}

class InternalNode implements Node {
    private final Node left;
    private final Node right;

    InternalNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    //O(h)
    @Override
    public int getLength() {
        final int leftSize = left == null ? 0 : left.getLength();
        final int rightSize = right == null ? 0 : right.getLength();
        return leftSize + rightSize;
    }

    //index 3
    //left:[a,b,c]
    //right:[d,e]
    @Override
    public char getCharacter(int index) {
        if (index < 0 || index > getLength()) return 0;
        if (index < left.getLength()) {
            return left.getCharacter(index);
        } else {
            return right.getCharacter(index - left.getLength());
        }
    }

    @Override
    public String getVal() {
        final String leftVal = left == null ? "" : left.getVal();
        final String rightVal = right == null ? "" : right.getVal();
        return leftVal + rightVal;
    }

    @Override
    public void insert(int position, char c) {
        if (position < 0 || position > getLength()) return;
        if (position < left.getLength()) {
            left.insert(position, c);
        } else {
            right.insert(position - left.getLength(), c);
        }
    }
}
