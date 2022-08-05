package google;

public class LongestPath {


    /**
     * #.##..#
     * #.##..#
     * #..##.#
     * #..##.#
     * #..##.#
     * <p>
     * should return 8
     */

    public static void main(String[] args) {

        char[][] c = new char[][]{
                {'#', '.', '#', '#', '.', '.', '#'},
                {'#', '.', '#', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '#', '#', '.', '#'},
                {'#', '.', '.', '#', '#', '.', '#'}
        };

        String[][] s = new String[][]{
                {"#", ".", "#", "#", ".", ".", "#"},
                {"#", ".", "#", ".", ".", ".", "#"},
                {"#", ".", ".", ".", ".", ".", "#"},
                {"#", ".", ".", "#", "#", ".", "#"},
                {"#", ".", ".", "#", "#", ".", "#"}
        };

        System.out.println(longestPath(c));

        System.out.println(fromCharlie(s));
    }

    private static int longestPath(char[][] maze) {
        int m = maze.length;
        int n = maze[0].length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] r = new int[n];

        left[0] = maze[0][0] == '#' ? 0 : 1;
        right[n - 1] = maze[0][n - 1] == '#' ? 0 : 1;
        for (int i = 1; i < n; i++) {
            if (maze[0][i] == '.') {
                left[i] = 1 + left[i - 1];
            }

            if(maze[0][n - i - 1] == '.') {
                right[n - i - 1] = 1 + right[n - i];
            }
        }

        for(int i = 0; i < n; i++) {
            r[i] = Math.max(left[i], right[i]);
            System.out.print(r[i] + ",");
        }
        System.out.println("");

        int result = 0;
        for (int i = 1; i < m; i++) {
            if(maze[i][0] == '.') {
                left[0] = r[0] + 1;
            } else {
                left[0] = 0;
            }

            if(maze[i][n - 1] == '.') {
                right[n - 1] = r[n - 1] + 1;
            } else {
                right[n - 1] = 0;
            }

            for (int j = 1; j < n; j++) {
                if (maze[i][j] == '#') {
                    left[j] = 0;
                } else {
                    left[j] = 1 + Math.max(left[j - 1], r[j]);
                }

                if(maze[i][n - j - 1] == '#') {
                    right[n - j - 1] = 0;
                } else {
                    right[n - j - 1] = 1 + Math.max(right[n - j], r[n - j - 1]);
                }
            }
            result = 0;
            for (int j = 0; j < n; j++) {
                r[j] = Math.max(left[j], right[j]);
                result = Math.max(result, r[j]);
                System.out.print(r[j] + ",");
            }
            System.out.println("");
        }

        return result;
    }

    private static int fromCharlie(String[][] array) {
        int[] myCount = new int[]{0, 0, 0, 0, 0, 0, 0};
        int[] myNext = new int[]{0, 0, 0, 0, 0, 0, 0};
        int[] mLeftToRight = new int[]{0, 0, 0, 0, 0, 0, 0};
        int[] mRightToLeft = new int[]{0, 0, 0, 0, 0, 0, 0};
        for (String[] row : array) {
            for (int i = 0; i < row.length; i++) {
                String item = row[i];
                myNext[i] = item.equals("#") ? 0 : myCount[i] + 1;
            }

            myCount = myNext;
            for (int i = 0; i < myCount.length; i++) {
                mLeftToRight[i] = myCount[i];
                mRightToLeft[i] = myCount[i];
            }

            int counter = 0;
            for (int i = 0; i < row.length; i++) {
                String item = row[i];
                if (item.equals("#")) {
                    mLeftToRight[i] = 0;
                    counter = 0;
                }
                else {
                    mLeftToRight[i] = Math.max(mLeftToRight[i], counter + 1);
                    counter = mLeftToRight[i];
                }
            }
            for (int i = row.length - 1; i >= 0; i--) {
                String item = row[i];
                if (item.equals("#")) {
                    mRightToLeft[i] = 0;
                    counter = 0;
                }
                else {
                    mRightToLeft[i] = Math.max(mRightToLeft[i], counter + 1);
                    counter = mRightToLeft[i];
                }
            }

            for (int i = 0; i < myCount.length; i++) {
                myCount[i] = Math.max(mLeftToRight[i], mRightToLeft[i]);
            }
        }

        int max = -1;
        for (int count: myCount) {
            System.out.print(count + ", ");
            max = Math.max(max, count);
        }
        System.out.println();
        System.out.println("Answer: " + max);
        return max;
    }
}
