package leetcode.sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * [5, 2], [5,0], [4,0], [4,1], [2,3], [3,1]
 */
public class TopologicalSort {
    public static void main(String[] args) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] tasks = new int[]{0, 1, 2, 3, 4, 5};

        for (int i = 0; i < tasks.length; ++i)
            adj.add(new ArrayList<>());

        addEdge(adj, 5, 2);
        addEdge(adj, 5, 0);
        addEdge(adj, 4, 0);
        addEdge(adj, 4, 1);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 1);

        topologicalSort(adj, 6);
    }

    private static void addEdge(List<List<Integer>> adj, int v, int w) {
        adj.get(v).add(w);
    }

    private static void topologicalSortUtil(List<List<Integer>> adj, int v, boolean[] visited,
                                    Stack<Integer> stack) {
        // Mark the current node as visited.
        visited[v] = true;
        Integer i;

        // Recur for all the vertices adjacent
        // to thisvertex
        Iterator<Integer> it = adj.get(v).iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(adj, i, visited, stack);
        }

        // Push current vertex to stack
        // which stores result
        stack.push(v);
    }

    // The function to do Topological Sort.
    // It uses recursive topologicalSortUtil()
    static void topologicalSort(List<List<Integer>> adj, int n) {
        Stack<Integer> stack = new Stack<>();

        // Mark all the vertices as not visited
        boolean[] visited = new boolean[n];

        // Call the recursive helper
        // function to store
        // Topological Sort starting
        // from all vertices one by one
        for (int i = 0; i < n; i++)
            if (!visited[i])
                topologicalSortUtil(adj, i, visited, stack);

        // Print contents of stack
        while (!stack.empty())
            System.out.print(stack.pop() + " ");
    }
}
