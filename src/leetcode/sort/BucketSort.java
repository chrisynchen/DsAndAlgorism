package leetcode.sort;

import java.util.Collections;
import java.util.Vector;

/**
 * usually use in a range sort like each values between [0,1]
 */
public class BucketSort {
    public static void main(String[] args) {
        float[] temp = new float[]{0.5f, 0.9f, 0.3f, 0.1f, 0.2f, 0.4f};
        bucketSort(temp, temp.length);
        for (float element : temp) {
            System.out.println(element);
        }
    }

    private static void bucketSort(float arr[], int n) {
        if (n <= 0)
            return;

        // 1) Create n empty buckets
        @SuppressWarnings("unchecked")
        Vector<Float>[] buckets = new Vector[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new Vector<>();
        }

        // 2) Put array elements in different buckets
        for (int i = 0; i < n; i++) {
            float idx = arr[i] * n;
            buckets[(int) idx].add(arr[i]);
        }

        // 3) Sort individual buckets
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }

        // 4) Concatenate all buckets into arr[]
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }
    }
}
