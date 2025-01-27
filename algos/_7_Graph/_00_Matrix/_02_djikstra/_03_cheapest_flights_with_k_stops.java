package _7_Graph._00_Matrix._02_djikstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Medium
Topics
Companies
There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.

 

Example 1:


Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
Output: 700
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
Example 2:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
Output: 200
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
Example 3:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
Output: 500
Explanation:
The graph is shown above.
The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
 

Constraints:

1 <= n <= 100
0 <= flights.length <= (n * (n - 1) / 2)
flights[i].length == 3
0 <= fromi, toi < n
fromi != toi
1 <= pricei <= 104
There will not be any multiple flights between two cities.
0 <= src, dst, k < n
src != dst
 */
public class _03_cheapest_flights_with_k_stops {
    

    public static void main(String[] args) {
        //n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
        int nLocations=4;
        int flights[][]={
            {0,1,100},
            {1,2,100},
            {2,0,100},
            {1,3,600},
            {2,3,200}
        };
        int src=0,dst=3;
        int maxNumOfStops=1;
        System.out.println(using_djkistra(nLocations, flights, src, dst, maxNumOfStops));
    }

    public static int using_djkistra(int n,int flights[][],int src,int dst,int k){//O(v+Eloge)
         int cost[] = new int[n];
        Queue<int[]> pq = new LinkedList<>();//DONT USE PQ, it may not pass all TCs, if u use PQ, compare using k and not w, Queue works better coz we r moving by dist 1 for neighbour and we dont have any varying lenghts
        List<List<int[]>> adj = get_adj(flights, n);
        // for(List<int[]>p:adj){
        //     for(int []x:p) System.out.print(Arrays.toString(x)+" ");
        //     System.out.println();
        // }
        pq.offer(new int[] { 0, src, 0 });//stores in format {wt,nodeNumber,distTravelledToReachItFromSrc}
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        while (!pq.isEmpty()) {
            int front[] = pq.poll();
            // System.out.println("Front:"+Arrays.toString(front));
            int w = front[0];
            int nodeNumber = front[1];
            int distTravelled = front[2];
            for (int[] pair : adj.get(nodeNumber)) {
                // System.out.println("Encountered"+Arrays.toString(pair));
                if (w + pair[1] < cost[pair[0]]) {
                    if (distTravelled < k) {//then update cost and push it to Q
                        cost[pair[0]] = w + pair[1];
                        pq.offer(new int[] { cost[pair[0]], pair[0], distTravelled+1});
                    }
                }
            }
        }
        // System.out.println(Arrays.toString(cost));
       return cost[dst]==Integer.MAX_VALUE?-1:cost[dst];
    }
    public static List<List<int[]>> get_adj(int graph[][], int n) {
        List<List<int[]>> adj = new ArrayList<>();
        while (n-- != 0)
            adj.add(new ArrayList<>());
        for (int set[] : graph) {
            int from = set[0], to = set[1], w = set[2];
            adj.get(from).add(new int[] { to, w });
        }
        return adj;
    }
}
