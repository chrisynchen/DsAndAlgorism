package cracking.string;

public class ReplaceSpace {
    public static void main(String[] args) {
        System.out.println(replaceSpace("nsg sf s f "));
        System.out.println(replaceSpace(" aaa vb"));
    }

    private static String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            String temp = "" + s.charAt(i);
            if(temp.contains(" ")) {
                temp = "%20";
            }
            sb.append(temp);
        }

        return sb.toString();
    }
}
