package amazon;

import java.util.Random;

public class RandomSampling {
    public static void main(String[] args) {
        sampling(new Node[]{new Node(5), new Node(9), new Node(7), new Node(11)}, 0);
    }

    private static Node[] sampling(Node[] raw, int k) {
        if (k <= 0) return new Node[0];
        int index = raw.length;
        final Random random = new Random();
        while (index > 0 && k > 0) {
            int r = random.nextInt(index);
            swap(raw, r, index - 1);
            index--;
            k--;
        }
        Node[] result = new Node[raw.length - index];
        System.out.println(result.length);
        int resultIndex = 0;
        for (int i = index; i < raw.length; i++) {
            System.out.println(raw[i].val);
            result[resultIndex++] = raw[i];
        }
        return result;
    }

    private static void swap(Node[] raw, int i, int j) {
        Node temp = raw[i];
        raw[i] = raw[j];
        raw[j] = temp;
    }

    static class Node {
        public Node(int val) {
            this.val = val;
        }

        int val;
    }
}
