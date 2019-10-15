public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        //3
//        System.out.println(lengthOfLongestSubstring("abcabcbb"));

        //3
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {

        int finalRepeatTimes = 1;
        char[] chars = s.toCharArray();

        for(int i = 0; i< s.length(); i++) {
            int minusCount = 1;
            int startIndex = i - minusCount;
            int endIndex = i + minusCount;

            while(endIndex < s.length() && startIndex >= 0) {
                if(chars[startIndex] == chars[endIndex]) break;

                if(minusCount > finalRepeatTimes) {
                    finalRepeatTimes = minusCount;
                }

                minusCount++;
                startIndex--;
                endIndex++;
            }
        }

        return finalRepeatTimes;
    }
}