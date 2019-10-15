public class ImplementStackWithLinkedList {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(3);
        stack.push(5);
        stack.push(4);
        stack.push(6);

        System.out.print(stack.pop());
        System.out.print(stack.pop());
        System.out.print(stack.pop());
        System.out.print(stack.pop());
        System.out.print(stack.pop());
    }

    private static class Stack {
        private Node first;

        public void push(int element) {
            Node oldFirst = first;
            first = new Node(element);
            first.next = oldFirst;
        }

        public int pop() {
            int val = first.val;
            first = first.next;
            return val;
        }

        public boolean isEmpty() {
            return first == null;
        }
    }

    private static class Node {
        private int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}