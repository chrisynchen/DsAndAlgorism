package leetcode;

/**
 * 849. Maximize Distance to Closest Person
 * Easy
 * <p>
 * Share
 * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
 * <p>
 * There is at least one empty seat, and at least one person sitting.
 * <p>
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 * <p>
 * Return that maximum distance to closest person.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,0,0,0,1,0,1]
 * Output: 2
 * Explanation:
 * If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
 * If Alex sits in any other open seat, the closest person has distance 1.
 * Thus, the maximum distance to the closest person is 2.
 * Example 2:
 * <p>
 * Input: [1,0,0,0]
 * Output: 3
 * Explanation:
 * If Alex sits in the last seat, the closest person is 3 seats away.
 * This is the maximum distance possible, so the answer is 3.
 */
public class MaximizeDistanceToClosestPerson {
    public static void main(String[] args) {
        System.out.println(maxDistToClosest(new int[]{1, 0, 0, 0, 1, 0, 1}));
    }

    // O(N)
    public static int maxDistToClosest(int[] seats) {
        int firstPersonIndex = -1;
        int lastPersonIndex = -1;
        int max = 1;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                if (firstPersonIndex == -1) {
                    firstPersonIndex = i;
                } else {
                    firstPersonIndex = lastPersonIndex;
                }
                lastPersonIndex = i;
            } else {
                if (firstPersonIndex == -1) {
                    max = Math.max(max, i + 1);
                } else {
                    max = Math.max(max, (i + 1 - lastPersonIndex) / 2);
                }
            }
        }

        max = Math.max(max, seats.length - 1 - lastPersonIndex);

        return max;
    }
}
