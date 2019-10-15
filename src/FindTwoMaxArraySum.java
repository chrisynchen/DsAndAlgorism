import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an array apple tree, you have A and B. A can collection m apple tree
 * and B can collection n apple tree. Each of them cannot collect same tree.
 * Please find the max sum of A collection apples
 * and B collection apples. If cannot find valid tree. Please return -1.
 * <p>
 * For instance1, given m = 3, n = 2 and array = [1,2,3,2,1,1,1,5,7]
 * the answer will be  A = 2 + 3 + 2, B = 5 + 7, sum = 7 + 12 = 19.
 * <p>
 * For instance2, given m = 2, n = 2 and array = [1,2,1]
 * the answer will be -1, since they will collect overlapped tree.
 *
 * @author chenchris on 2019/4/28.
 */
public class FindTwoMaxArraySum {
    public static void main(String[] args) {
        int[] appleTrees = {1, 2, 3, 2, 1, 1, 1, 5, 7};
        System.out.println(findMaxSumApple(3, 2, appleTrees));
    }

    private static int findMaxSumApple(int m, int n, int[] appleTrees) {

        final Queue<Integer> queue = new LinkedList<>();

        int mIndex = 0;
        int nIndex = 0;

        while (mIndex < appleTrees.length || nIndex < appleTrees.length) {

        }
        return 0;
    }
}
