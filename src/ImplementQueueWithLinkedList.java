public class ImplementQueueWithLinkedList {
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(3);
        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(6);

        System.out.print(queue.dequeue());
        System.out.print(queue.dequeue());
        System.out.print(queue.dequeue());
        System.out.print(queue.dequeue());
        System.out.print(queue.dequeue());
    }

    private static class Queue {
        private Node first;
        private Node last;

        void enqueue(int element) {
            if (first == null) {
                first = new Node(element);
                first.next = last;
            } else {
                if (last == null) {
                    last = new Node(element);
                    first.next = last;
                    return;
                }
                last.next = new Node(element);
                last = last.next;
            }
        }

        int dequeue() {
            int oldFirstValue = first.val;
            first = first.next;
            return oldFirstValue;
        }

        private boolean isEmpty() {
            return first == null && last == null;
        }
    }

    private static class Node {
        private int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }
}