public class GameOfLife {

    static int[][] ref = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1},
            {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public static void main(String[] args) {
        int[][] input = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife(input);
        for(int i = 0; i<input.length;i++) {
            for(int j = 0; j<input[0].length;j++) {
                System.out.println(input[i][j]);
            }
        }
    }

    private static void gameOfLife(int[][] board) {
        int[][] newBoard = new int[board.length][board[0].length];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[0].length; j++) {
                newBoard[i][j] = isLive(board, i, j) ? 1 : 0;
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = newBoard[i][j];
            }
        }
    }

    private static boolean isLive(int[][] board, int i, int j) {
        int neighborLiveCount = 0;
        for (int k = 0; k < ref.length; k++) {
            if (i + ref[k][0] < 0 || j + ref[k][1] < 0
                    || i + ref[k][0] >= board.length
                    || j + ref[k][1] >= board[0].length) continue;
            neighborLiveCount += board[i + ref[k][0]][j + ref[k][1]];
        }

        if (board[i][j] == 0) {
            return neighborLiveCount == 3;
        } else {
            return neighborLiveCount >= 2 &&
                    neighborLiveCount <= 3;
        }
    }
}