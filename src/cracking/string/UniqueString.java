package cracking.string;

import java.util.HashSet;
import java.util.Set;

public class UniqueString {
    public static void main(String[] args) {
        System.out.println(isUnique("jnsw%pq"));
        System.out.println(isUnique("a%pqa"));
    }

    //time: O(n)
    //space: O(n)
    private static boolean isUnique(String s) {
        Set<Character> set = new HashSet<>();

        for(int i = 0; i< s.length();i++) {
            if(!set.add(s.charAt(i))) return false;
        }

        return true;
    }

    //time: O(n)
    //space: O(n)
    // good because no use extra data structure
    private static boolean isUnique_solution(String s) {
        if(s.length() > 128) return false;

        boolean[] charSet = new boolean[128];

        for(int i = 0; i< s.length();i++) {
            if(charSet[s.charAt(i)]) return false;

            charSet[s.charAt(i)] = true;
        }

        return true;
    }
}
