package others;

import java.util.HashMap;
import java.util.Map;

/**
 * Take an input string parameter and determine: For all pairs of digits where there are exactly 3 question marks between them, do all pairings add up to 10.
 * console.log(solve("arrb6???4xxbl5???eee5")) // true
 * console.log(solve("acc?7??sss?3rr1??????5")) // true
 * console.log(solve("5??aaaaaaaaaaaaaaaaaaa?5?5")) // true
 * console.log(solve("9???1???9???1???9")) // true
 * console.log(solve("aa6?9")) // false
 * console.log(solve("8???2???9")) // false
 * console.log(solve("10???0???10")) // false
 * console.log(solve("aa3??oiuqwer?7???2")) // false
 */


public class AddUpBetweenQuestionMark {

    public static void main(String[] args) {
        System.out.println(solve("arrb6???4xxbl5???eee5"));
        System.out.println(solve("acc?7??sss?3rr1??????5"));
        System.out.println(solve("5??aaaaaaaaaaaaaaaaaaa?5?5"));
        System.out.println(solve("9???1???9???1???9"));
        System.out.println(solve("aa6?9"));
        System.out.println(solve("8???2???9"));
        System.out.println(solve("10???0???10"));
        System.out.println(solve("aa3??oiuqwer?7???2"));
    }

    private static boolean solve(String s) {
        Map<Integer, Integer> map = new HashMap<>();

        int[] presum = new int[s.length()];
        presum[0] = s.charAt(0) == '?' ? 1 : 0;
        for(int i = 1; i < s.length(); i++) {
            presum[i] += (presum[i - 1] + (s.charAt(i) == '?' ? 1 : 0));
        }

        for(int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - '0';
            if(c >= 0 && c <= 9){
                if(map.containsKey(10 - c)) {
                    if(presum[i] - presum[map.get(10 - c)] >= 3) {
                        return true;
                    }
                }
                map.putIfAbsent(c, i);
            }
        }

        return false;
    }
}
