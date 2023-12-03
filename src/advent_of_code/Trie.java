package advent_of_code;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    final Node root;

    public Trie(String[] examples) {
        root = new Node();
        for (int k = 0; k < examples.length; k++) {
            Node temp = root;
            String s = examples[k];
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!temp.map.containsKey(c)) {
                    temp.map.put(c, new Node());
                }
                temp = temp.map.get(c);
            }
            temp.endResult = s;
        }
    }

    public String prefix(String s, int startIndex) {
        Node temp = root;
        for (int i = startIndex; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!temp.map.containsKey(c)) {
                return null;
            }
            temp = temp.map.get(c);
            if(temp.endResult != null) return temp.endResult;
        }
        return null;
    }

    private static class Node {
        final Map<Character, Node> map = new HashMap<>();
        String endResult = null;
    }
}
