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
        int dis[] = new int[nNodes];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[src] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);

        while (!q.isEmpty()) {
            int front = q.poll();
            if (dis[front] != Integer.MAX_VALUE) {
                for (int n : adj[front]) {
                    if (dis[front] + 1 < dis[n]) {//MISTAKE CODE: i didnt write this if statement, i wrote directly Math.min(dis[n],dis[front]+1);q.offer(n);, which means im updating the distance array, but see that we r pushng it in queue also everytime,which is wrong, so only push into queue when u get a smaller value, else not need
                        dis[n] = Math.min(dis[n], 1 + dis[front]);
                        q.offer(n);
                    }
                }
            }
        }

        return Arrays.stream(dis).map((e) -> (e != Integer.MAX_VALUE ? e : -1)).toArray();
    }
}
