package _7_Graph._03_Undir_weigh;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//prereq: _04_Dir_weigh/_01_shortest_path
/*
You are given an adjacency list, adj of Undirected Graph having unit weight of the edges, find the shortest path from src to all the vertex and if it is unreachable to reach any vertex, then return -1 for that vertex.

Examples :

Input: adj[][] = [[1, 3], [0, 2], [1, 6], [0, 4], [3, 5], [4, 6], [2, 5, 7, 8], [6, 8], [7, 6]], src=0
Output: [0, 1, 2, 1, 2, 3, 3, 4, 4]

Input: adj[][]= [[3], [3], [], [0, 1]], src=3
Output: [1, 1, -1, 0]

Input: adj[][]= [[], [], [], [4], [3], [], []], src=1
Output: [-1, 0, -1, -1, -1, -1, -1] 
Constraint:
1<=adj.size()<=104
0<=edges<=adj.size()-1
 */
public class _01_shortest_path {
    public static void main(String[] args) {
        System.out.println("Bismillah");
        int adj[][] = {
                { 1, 3 },
                { 0, 2 },
                { 1, 6 },
                { 0, 4 },
                { 3, 5 },
                { 4, 6 },
                { 2, 5, 7, 8 },
                { 6, 8 },
                { 7, 6 },
        };
        int src = 0;

        System.out.println(Arrays.toString(bfs(adj, src)));

    }

    static int[] bfs(int adj[][], int src) {
        int nNodes = adj.length;
        boolean isVis[] = new boolean[adj.length];

        int dis[] = new int[adj.length];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[src] = 0;
        
        Queue<Integer> q = new LinkedList<>();

        int distance_from_src[] = new int[nNodes];
        Arrays.fill(distance_from_src, Integer.MAX_VALUE);
        distance_from_src[src] = 0;

        q.offer(src);
        isVis[src] = true;

        while (!q.isEmpty()) {
            int top = q.poll();
            if (distance_from_src[top] != Integer.MAX_VALUE) {//----NOTE: dont place this inside for loop to check, u will get TLE. This if means that if there's a node that is diconnected,(so its encountered with IntMAx value), then waste to run loop for it
                for (int neighbour : adj[top]) {
                    if (!isVis[neighbour]) {
                        q.offer(neighbour);
                        isVis[neighbour] = true;
                        distance_from_src[neighbour] = Math.min(distance_from_src[neighbour],
                                1 + distance_from_src[top]);//coz given that edges have unit weight, we add 1
                    }
                }
            }

        }

        return distance_from_src;
    }
}
