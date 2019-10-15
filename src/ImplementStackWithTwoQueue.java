import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackWithTwoQueue {
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
        private final Queue<Integer> queue1 = new LinkedList<>();
        private final Queue<Integer> queue2 = new LinkedList<>();

        // check which queue is empty and add the first value to that queue.
        // Then add the rest of element in from another queue
        private void push(int value) {
            if (queue1.isEmpty()) {
                queue1.offer(value);
                while (!queue2.isEmpty()) {
                    queue1.offer(queue2.poll());
                }
            } else {
                queue2.offer(value);
                while (!queue1.isEmpty()) {
                    queue2.offer(queue1.poll());
                }
            }
        }

        private int pop() {
            return !queue1.isEmpty() ? queue1.poll() : queue2.poll();
        }
    }
}