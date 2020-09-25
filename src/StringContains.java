import java.util.TreeSet;

/**
 * 1)
 * implement contains function
 * input: abcde pattern: bcd => true
 * input: abcde pattern: bd => false
 * <p>
 * 2)
 * implement something like regex:
 * input: abcccccccde pattern: b*d => true
 * input: abcccccccde pattern: b*e => false
 */
public class StringContains {
    public static void main(String[] args) {

        TreeSet<Integer> treeadd = new TreeSet<Integer>();

        // adding in the tree set
        treeadd.add(12);
        treeadd.add(11);
        treeadd.add(16);
        treeadd.add(15);

        // getting the floor value for 13
        System.out.println("Floor value for: "+treeadd.floor(13));
        System.out.println("Ceiling value for: "+treeadd.ceiling(13));

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

        int patternIndex = 0;
        Character starReplacement = null;
        for (int i = 0; i < input.length(); i++) {
            if(pattern.charAt(patternIndex) == '*') {
                if(starReplacement == null) {
                    starReplacement = input.charAt(i);
                } else if(starReplacement - input.charAt(i) != 0) {
                    patternIndex++;
                }
            } else if (input.charAt(i) - pattern.charAt(patternIndex) == 0) {
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
}
