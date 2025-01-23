package _7_Graph._03_Undir_weigh;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/* 
Given a weighted, undirected and connected graph where you have given adjacency list adj. You have to find the shortest distance of all the vertices from the source vertex src, and return a list of integers denoting the shortest distance between each node and source vertex src.

Note: The Graph doesn't contain any negative weight edge.//VVVIMP: coz then the distances will continuius made negative ie min, we wil fall in inf loop

Examples:

Input: adj = [[[1, 9]], [[0, 9]]], src = 0
Output: [0, 9]
Explanation:

The source vertex is 0. Hence, the shortest distance of node 0 is 0 and the shortest distance from node 0 to 1 is 9.
Input: adj = [[[1, 1], [2, 6]], [[2, 3], [0, 1]], [[1, 3], [0, 6]]], src = 2
Output: [4, 3, 0]
Explanation:

For nodes 2 to 0, we can follow the path 2-1-0. This has a distance of 1+3 = 4, whereas the path 2-0 has a distance of 6. So, the Shortest path from 2 to 0 is 4.
The shortest distance from 0 to 1 is 1 .
Constraints:
1 ≤ no. of vertices ≤ 1000
0 ≤ adj[i][j] ≤ 1000
0 ≤ src < no. of vertices
 */
public class _02_djikstra {
    public static void main(String[] args) {

        List<List<int[]>> adj = List.of(
                List.of(new int[] { 1, 1 }, new int[] { 2, 6 }), // neighbours of 0
                List.of(new int[] { 2, 3 }, new int[] { 0, 1 }), // neghbour of 1
                List.of(new int[] { 1, 3 }, new int[] { 0, 6 })// neighbour of 2
        );
        System.out.println(dijkstra_using_queue(adj, 2));
        System.out.println(dijkstra_using_PriorityQueue(adj, 2));
    }

    // -------------------USING QUEUE: u just put nodeNumber in queue and do normal
    // bfs
    static List<Integer> dijkstra_using_queue(List<List<int[]>> adj, int src) {// TC : e log(v), where e is number
                                                                                    // of edges and v is number of
                                                                                    // verticies
        int nNodes = adj.size();
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        int dis[] = new int[nNodes];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[src] = 0;

        while (!q.isEmpty()) {
            int front = q.poll();
            List<int[]> curr = adj.get(front);
            if (dis[front] != Integer.MAX_VALUE) {
                for (int[] pair : curr) {//for each neighbour of front
                    int node = pair[0];
                    int weightWithThatNode = pair[1];
                    if (dis[node] > dis[front] + weightWithThatNode) {
                        dis[node] = dis[front] + weightWithThatNode;
                        q.offer(node);
                    }
                }
            }
        }
        return Arrays.stream(dis).boxed().toList();
    }

    ////i have used PQ in below algo, we can also implement it using set:pls watch for interview perspective since the interviewer may ask how set can impact on performance: https://www.youtube.com/watch?v=PATgNiuTP20&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=33&ab_channel=takeUforward

    // in short if we use use set, when we encounter a smaller distance, we can just
    // remove the larger distance(logn time) and insert the smller one thus we can
    // safely skip that larger by removing it, but observe that while using q/pq, we
    // r ot doing same , though we find smaller, we push that smller after updating
    // dis[], and that larger pair will still retain in queue, ie when we encounter
    // that larger, its just waste coz we know already that we already have smaller
    // value why on earth we will chnge it, so using set can avoid some unneccasy
    // iterations, but disadv is set.remove() still takes extra logn time... so we
    // would generally perfer set or PQ based on how graph it, but ususllly
    // implement using PQ itself


    //do watch why using PQ>>Q :https://www.youtube.com/watch?v=3dINsjyfooY&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=34&ab_channel=takeUforward

    //coz if we store elemnts in pq in format [wt,nodeNumber] against [nodeNumber] in q, we will deal with lower weights first directly, thus skippin to check higher wt, saves time
    public static List<Integer> dijkstra_using_PriorityQueue(List<List<int[]>> adj, int src){
        int nNodes=adj.size();
        PriorityQueue<int[]>pq=new PriorityQueue<>((x,y)->x[0]-y[0]);//arrange based on wts
        //MOST IMP THING is in pq we store in format: [wt,nodeNumber] so that we can access the nodes in order of weightsOfEdges
        pq.offer(new int[]{0,src});//in format [wt,nodeNumber]
        int dis[] = new int[nNodes];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[src] = 0;

        while (!pq.isEmpty()) {
            int[] front = pq.poll();
            List<int[]> curr = adj.get(front[1]);//nodeNumber is in front[1] and wt in front[0] as per our formaat
            if (dis[front[1]] != Integer.MAX_VALUE) {
                for (int[] pair : curr) {//for each neighbour of front
                    int node = pair[0];
                    int weightWithThatNode = pair[1];
                    if (dis[node] > dis[front[1]] + weightWithThatNode) {
                        dis[node] = dis[front[1]] + weightWithThatNode;
                        pq.offer(new int[]{dis[node],node});//store [updatedWt,nodeNumber]
                    }
                }
            }
        }
        return Arrays.stream(dis).boxed().toList();
    }


}
