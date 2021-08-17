package leetcode.array.kmp;

/**
 * http://jakeboxer.com/blog/2009/12/13/the-knuth-morris-pratt-algorithm-in-my-own-words/
 *
 */
public class Kmp {

    private static void kmpSearch(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        // Preprocess the pattern (calculate lps[]
        // array)
        int[] lps = getLPSArray(pat);

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int j = 0; // index for pat[]

        int i = 0; // index for txt[]
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Found pattern "
                        + "at index " + (i - j));
                j = lps[j - 1];
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
    }

    //abab
    //aba
    private static int[] getLPSArray(String pattern) {
        int[] lps = new int[pattern.length()];

        for (int i = 1; i <= pattern.length(); i++) {
            int longest = 0;
            //ab
            //ababa
            for (int j = 0; j < i; j++) {
                System.out.println("start:" + pattern.substring(0, j) + " ,end:" + pattern.substring(i - j, i));
                if (pattern.substring(0, j).equals(pattern.substring(i - j, i))) {
                    longest++;
                }
            }

            lps[i] = longest;
        }

        return lps;
    }

    // Driver program to test above function
    public static void main(String args[]) {
//        String txt = "ABABDABACDABABCABAB";
//        String pat = "ABABCABAB";
//        kmpSearch(pat, txt);
        int[] lps = getLPSArray("abab");
        for (int e : lps) {
            System.out.println(e);
        }
    }
}
