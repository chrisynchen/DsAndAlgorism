package others;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 460. LFU Cache (Hard)
 *
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 *
 * Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to zero when the item is removed.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 */
public class LFUCache {
    //  [key, value, frequency]
    private final Queue<Cache> queue;
    private int capacity;
    private int time;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        time = 0;
        queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.frequency == o2.frequency) {
                return o1.time - o2.time;
            } else {
                return o1.frequency - o2.frequency;
            }
        });
    }

    //["others.LFUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
    //[[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(3);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4); // remain 234
        System.out.println(cache.get(4)); //4
        System.out.println(cache.get(3)); //3
        System.out.println(cache.get(2)); //2
        System.out.println(cache.get(1)); //-1
        cache.put(5, 5); // remain 234
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(2)); // -1
        System.out.println(cache.get(3)); // 3
        System.out.println(cache.get(4)); // 4
        System.out.println(cache.get(5)); // 5
    }

    public int get(int key) {
        for (Cache cache : queue) {
            if (cache.key == key) {
                queue.remove(cache);
                queue.offer(new Cache(key, cache.value, cache.frequency + 1, time++));
                return cache.value;
            }
        }

        return -1;
    }

    public void put(int key, int value) {
        for (Cache cache : queue) {
            if (cache.key == key) {
                queue.remove(cache);
                queue.offer(new Cache(key, value, cache.frequency + 1, time++));
                return;
            }
        }

        if (queue.size() >= capacity) {
            queue.poll();
        }
        queue.offer(new Cache(key, value, 0, time++));
    }

    class Cache {
        Cache(int key, int value, int frequency, int time) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
            this.time = time;
        }

        int key;
        int value;
        int frequency;
        int time;
    }
}
