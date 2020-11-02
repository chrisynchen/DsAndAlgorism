package cracking.string;

public class StringPermutation {
    public static void main(String[] args) {
        System.out.println(isStringPermutation("abcd", "cabd"));
        System.out.println(isStringPermutation("abcd", "abde"));
    }

    private static boolean isStringPermutation(String a, String b) {

        if(a.length() != b.length()) return false;

        int[] aCheck = new int[128];
        int[] bCheck = new int[128];

        for(int i = 0; i< a.length();i++) {
            aCheck[a.charAt(i)]++;
            bCheck[b.charAt(i)]++;
        }

        for(int j = 0; j< aCheck.length;j++) {
            if(aCheck[j] != bCheck[j]) return false;
        }

        return true;
    }

    private static boolean isStringPermutation_solution(String a, String b) {

        if(a.length() != b.length()) return false;

        int[] check = new int[128];

        for(int i = 0; i< a.length();i++) {
            check[a.charAt(i)]++;
        }

        for(int j = 0; j< b.length();j++) {
            check[b.charAt(j)]--;
            if(check[b.charAt(j)] < 0) return false;
        }

        return true;
    }
}
