package leetcode;

/**
 * return it's substring or not
 * <p>
 * ex: abcdedc, bcd => true
 * bcd is substring
 * <p>
 * * ex: abcdedc, bd => false
 * * bd is not substring
 */
public class StringContains {
    public static void main(String[] args) {

        System.out.println(isContains("abcdedc", "bcd"));

        System.out.println(isContains("abcdedc", "bd"));

        System.out.println(isContains("abcdedc", "dcd"));
        System.out.println(isContains("abcdedc", "edc"));
    }

    private static boolean isContains(String text, String pattern) {
        int index = 0;
        while (index < text.length() && (text.length() - index) - pattern.length() >= 0) {
            for (int i = 0; i < pattern.length(); i++) {
                if (text.charAt(i + index) - pattern.charAt(i) != 0) break;

                if (i == pattern.length() - 1) {
                    return true;
                }
            }

            index++;
        }

        return false;
    }
}
