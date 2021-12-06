import java.util.Random;

public class KMines {
    public static void main(String[] args) {
        Game g = new Game(4,3,2);
        print(g.generate());
    }

    private static void print(char[][] cArray) {
        for(char[] cRow: cArray) {
            for(char c: cRow) {
                System.out.print(c);
            }
            System.out.print("\n");
        }
    }

    static class Game {
        char[][] result;
        int mines;
        public Game(int row, int column, int mines) {
            this.mines = mines;
            result = new char[row][column];
        }

        public char[][] generate() {
            int row = result.length;
            int column = result[0].length;
            int[] indexRef = new int[row * column];
            for(int i = 0; i < indexRef.length; i++) {
                indexRef[i] = i;
            }

            Random random = new Random();

            int remaining = mines;
            int lastAvailable = indexRef.length - 1;
            while (remaining > 0) {
                int index = random.nextInt(lastAvailable + 1);
                swap(indexRef, lastAvailable, index);
                remaining--;
                lastAvailable--;
            }

            for(int i = 0; i < indexRef.length; i++) {
                int[] c = indexOf(indexRef[i], column);
                if(i >= indexRef.length - mines) {
                    result[c[0]][c[1]] = '*';
                } else {
                    result[c[0]][c[1]] = '.';
                }
            }

            return result;
        }

        private int[] indexOf(int a, int column) {
            return new int[]{a / column, a % column};
        }

        private void swap(int[] indexRef, int a, int b) {
            int temp = indexRef[a];
            indexRef[a] = indexRef[b];
            indexRef[b] = temp;
        }
    }
}
