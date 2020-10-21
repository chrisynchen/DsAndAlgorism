package leetcode.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFinder {

    public static void main(String[] args) {
        MedianFinder m = new MedianFinder();
        m.addNum(1);
        m.addNum(2);
        System.out.println(m.findMedian());
        m.addNum(3);
        System.out.println(m.findMedian());
    }

    /** initialize your data structure here. */

    Queue<Integer> minHeap, maxHeap;

    //[2,3,4]
    // max add
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());

        if (maxHeap.size() < minHeap.size()) {// maintain size property
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : ((double) minHeap.peek() + (double) maxHeap.peek()) / 2;
    }
}
