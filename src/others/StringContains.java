package others;

/**
 * 1)
 * implement contains function
 * input: abcde pattern: bcd => true
 * input: abcde pattern: bd => false
 * <p>
 * 2)
 * implement something like regex:
 * input: abcccccccdeeeef pattern: b*d*f => true
 * input: abcccccccde pattern: b*e => false
 */
public class StringContains {
    public static void main(String[] args) {
        System.out.println(contains("abcde", "bc"));

        System.out.println(regexContains("abcccccccde", "b*d"));
        System.out.println(regexContains("abcccccccde", "b*e"));
        System.out.println(regexContains("abcccccccdeeeef", "b*d*f"));
        System.out.println(regexContains("abcccccccdeeeef", "b*d*e"));
    }

    private static boolean contains(String input, String pattern) {

        if (input == null || pattern == null) return false;

        if (input.isEmpty() && pattern.isEmpty()) return true;

        int patternIndex = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) - pattern.charAt(patternIndex) == 0) {
                patternIndex++;
            } else {
                patternIndex = 0;
            }

            if (patternIndex >= pattern.length()) {
                return true;
            }
        }

        return false;
    }

    private static boolean regexContains(String input, String pattern) {
        if (input == null || pattern == null) return false;

        if (input.isEmpty() && pattern.isEmpty()) return true;

        return findPattern(input, pattern, 0, 0, null);
    }

    private static boolean findPattern(String input, String pattern, int currentInputIndex, int currentPatternIndex, Character c) {
        if (currentPatternIndex >= pattern.length()) return true;

        if (currentInputIndex >= input.length()) return false;

        if (c == null) {
            if (pattern.charAt(currentPatternIndex) == '*') {
                c = input.charAt(currentInputIndex);
                return findPattern(input, pattern, currentInputIndex + 1, currentPatternIndex, c);
            } else if (input.charAt(currentInputIndex) == pattern.charAt(currentPatternIndex)) {
                return findPattern(input, pattern, currentInputIndex + 1, currentPatternIndex + 1, c);
            } else {
                return findPattern(input, pattern, currentInputIndex + 1, 0, c);
            }
        } else if (c == input.charAt(currentInputIndex)) {
            return findPattern(input, pattern, currentInputIndex + 1, currentPatternIndex, c);
        } else {
            return findPattern(input, pattern, currentInputIndex, currentPatternIndex + 1, null);
        }
    }
}
