package leetcode.array;

/**
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
 *
 *
 *
 * Example 1:
 *
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 * Example 2:
 *
 * Input: "aba"
 * Output: False
 * Example 3:
 *
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */

class RepeatedSubstring {

    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern("abab"));
        System.out.println(repeatedSubstringPattern("aba"));
        System.out.println(repeatedSubstringPattern("abcabcabcabc"));
    }

    private static boolean repeatedSubstringPattern(String s) {

        if(s.length() <= 1) return false;

        //abab
        for(int i = 1; i <= s.length() / 2; i++ ) {
            if(s.length() % i == 0 && isRepeatedSubstring(s, s.substring(0, i), 0, i)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isRepeatedSubstring (String s, String expectResult, int startIndex, int endIndex) {
        if(startIndex >= s.length()) return true;

        if(!expectResult.equals(s.substring(startIndex, endIndex))) {
            return false;
        }

        return isRepeatedSubstring(s, expectResult, endIndex, endIndex + (endIndex - startIndex));
    }
}
