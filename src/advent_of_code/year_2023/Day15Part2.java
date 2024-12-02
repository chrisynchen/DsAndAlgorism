package advent_of_code.year_2023;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15Part2 {
    public static void main(String[] args) {
        Path path = Paths.get("src/advent_of_code/year_2023/test_data/day15part2.txt");
        String[] arr = null;
        try {
            final List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
            arr = lines.toArray(new String[lines.size()]);
            long start = System.currentTimeMillis();
            System.out.println(solve(arr));
            long runTime = System.currentTimeMillis() - start;
            System.out.println("Run Time: " + (runTime));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //double linked list with map
    private static long solve(String[] examples) {
        String[] steps = examples[0].split(",");
        long result = 0;
        //name vs node
        final Map<String, Node> nameMap = new HashMap<>();
        //hash vs linkedlist
        final Map<Long, Node[]> hashMap = new HashMap<>();
        for(String s: steps) {
            //ex: rn=1,cm-
            if(s.contains("-")) {
                String current = s.substring(0, s.length() - 1);
//                System.out.println(current);
                if(nameMap.containsKey(current)) {
                    final Node currentNode = nameMap.get(current);
                    currentNode.prev.next = currentNode.next;
                    currentNode.next.prev = currentNode.prev;
                    nameMap.remove(current);
                }
            } else {
                final String[] sp = s.split("=");
                String name = sp[0];
                int val = Integer.parseInt(sp[1]);

                if(nameMap.containsKey(name)) {
                    nameMap.get(name).val = val;
                    continue;
                }

                long hash = hash(name);
                if(!hashMap.containsKey(hash)) {
                    final Node head = new Node();
                    final Node tail = new Node();
                    head.next = tail;
                    tail.prev = head;
                    hashMap.put(hash, new Node[]{head, tail});
                }
                final Node currentNode = new Node();
                currentNode.val = val;
                final Node last = hashMap.get(hash)[1];
                final Node originalPrev = last.prev;
                originalPrev.next = currentNode;
                currentNode.prev = originalPrev;
                currentNode.next = last;
                last.prev = currentNode;
                nameMap.put(name, currentNode);
            }
        }
        for(Map.Entry<Long, Node[]> e: hashMap.entrySet()) {
            //head
            Node current = e.getValue()[0];
            long box = e.getKey();
            int index = 0;
            // head and tail case can feasible
            while (current != null) {
                result += (box + 1) * index * current.val;
//                System.out.println("box:" + box + ", index:" + index + ", current:"+ current.val);
                index++;
                current = current.next;
            }
        }

        return result;
    }

    private static long hash(String s) {
        long temp = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            temp += c;
            temp *= 17;
            temp %= 256;
        }
        return temp;
    }

    static class Node {
        Node next;
        Node prev;
        int val = 0;
    }
}
