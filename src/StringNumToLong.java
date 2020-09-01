public class StringNumToLong {

    public static void main(String[] args) {
        System.out.println(getLong("1234567"));
    }

    private static long getLong(String s) {
        char[] charArray = s.toCharArray();
        int sum = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            sum += (charArray[i] - '0') * Math.pow(10,s.length() - i - 1);
        }

        return sum;
    }
}
