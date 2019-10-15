import java.util.Stack;

public class ImplementQueueWithTwoStack {
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
        private final Stack stack1 = new Stack();
        private final Stack stack2 = new Stack();

        private void enqueue(int value) {
            stack1.push(value);
        }

        private int dequeue() {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }

            int dequeueValue = (int) stack2.pop();
            while (!stack2.empty()) {
                stack1.push(stack2.pop());
            }

            return dequeueValue;
        }
    }
}