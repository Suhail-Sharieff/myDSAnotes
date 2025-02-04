package _7_Graph._03_Undir_weigh;

/*
1976. Number of Ways to Arrive at Destination
Medium
Topics
Companies
Hint
You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.

You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.

Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.

 

Example 1:


Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
Output: 4
Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
The four ways to get there in 7 minutes are:
- 0 ➝ 6
- 0 ➝ 4 ➝ 6
- 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
- 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
Example 2:

Input: n = 2, roads = [[1,0,10]]
Output: 1
Explanation: There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.
 

Constraints:

1 <= n <= 200
n - 1 <= roads.length <= n * (n - 1) / 2
roads[i].length == 3
0 <= ui, vi <= n - 1
1 <= timei <= 109
ui != vi
There is at most one road connecting any two intersections.
You can reach any intersection from any other intersection.

 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _03_number_of_ways_to_arrive_destination {

    public static void main(String[] args) {
        int n=7;
        int roads[][]={
            {0,6,7},
            {0,1,2},
            {1,2,3},
            {1,3,3},
            {6,3,3},
            {3,5,1},
            {6,5,1},
            {2,5,1},
            {0,4,5},
            {4,6,2},
        };
        System.out.println(countPaths(n, roads, 0, n-1));

    }
    //watch to understand:https://www.youtube.com/watch?v=_-0mx0SmYxA&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=40&t=1s&ab_channel=takeUforward
    //logic: a lil modification of dikstra, whenever the same min distance is encontered and when encountered for first time, handle cases
    static int countPaths(int n, int[][] roads,int src,int dest) {
        int mod=1_000_000_007;
        List<List<int[]>> adj = get_adj(n, roads);
        PriorityQueue<int[]> q = new PriorityQueue<>((x,y)->x[1]-y[1]);///VVVVIMp: Queue will not work
        q.offer(new int[] { src, 0 });
        int dis[] = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[src] = 0;
        int ways[]=new int[n];
        ways[src]++;
        while (!q.isEmpty()) {
            int[] front = q.poll();
            int node = front[0];
            for (int[] neigh : adj.get(node)) {
                int neighBourNode = neigh[0];
                int wt = neigh[1];
                //visiting that neighbour for first time
                if (wt + front[1] < dis[neighBourNode]) {
                    dis[neighBourNode] =( wt + front[1])%mod;
                    q.offer(new int[] { neighBourNode, dis[neighBourNode] });
                    ways[neighBourNode]=ways[node];
                }else if(wt+front[1]==dis[neighBourNode]){// we have visited that earlier also
                    ways[neighBourNode]=(ways[neighBourNode]+ways[node])%mod;
                }
            }
        }

        return (ways[dest]%mod);
    }

    static List<List<int[]>> get_adj(int nNodes, int roads[][]) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < nNodes; i++)
            adj.add(new ArrayList<>());
        for (int each[] : roads) {
            int from = each[0];
            int to = each[1];
            int wt = each[2];
            adj.get(from).add(new int[] { to, wt });
            adj.get(to).add(new int[] { from, wt });
        }
        return adj;
    }

}