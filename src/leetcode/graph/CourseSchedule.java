package leetcode.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 207. Course Schedule (Medium)
 * <p>
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should
 * also have finished course 1. So it is impossible.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * 1 <= numCourses <= 10^5
 */
public class CourseSchedule {

    public static void main(String[] args) {
        System.out.println(canFinishByDfs(7, new int[][]{
                {1, 0}, {0, 3}, {0, 2}, {3, 2}, {2, 5}, {4, 5}, {5, 6}, {2, 4}
        }));
    }

    private static boolean canFinishByDfs(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        boolean[] isVisited = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int start = prerequisites[i][0];
            int end = prerequisites[i][1];
            adj.get(start).add(end);
        }

        for (int i = 0; i < adj.size(); i++) {
            if (!dfs(adj, isVisited, i)) return false;
        }

        return true;
    }

    private static boolean dfs(List<List<Integer>> adj, boolean[] isVisited, int course) {
        if (isVisited[course]) return false;

        isVisited[course] = true;
        for (int i = 0; i < adj.get(course).size(); i++) {
            if (!dfs(adj, isVisited, adj.get(course).get(i))) return false;
        }
        isVisited[course] = false;
        return true;
    }
}
