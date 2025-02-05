package _7_Graph._04_Dir_weigh;

import java.util.Arrays;

/*
Thia algo is different fromt that of dikstra or bell man(which is used to find the shortest path from single src to all nodes). This alog cn determine all shortest distances from all nodes ie multiple srcs and also detect cycles as well
 */
/*
The problem is to find the shortest distances between every pair of vertices in a given edge-weighted directed graph. The graph is represented as an adjacency matrix. mat[i][j] denotes the weight of the edge from i to j. If mat[i][j] = -1, it means there is no edge from i to j.
Note: Modify the distances for every pair in place.

Examples :

Input: mat = [[0, 25], [-1, 0]]

Output: [[0, 25], [-1, 0]]

Explanation: The shortest distance between every pair is already given(if it exists).
Input: mat = [[0, 1, 43],[1, 0, 6], [-1, -1, 0]]

Output: [[0, 1, 7], [1, 0, 6], [-1, -1, 0]]

Explanation: We can reach 2 from 0 as 0->1->2 and the cost will be 1+6=7 which is less than 43.
Constraints:
1 <= mat.size() <= 100
-1 <= mat[ i ][ j ] <= 1000

Company Tags
Samsung
Topic Tags
Related Articles
 */
public class _04_floyd_warshall {
    public static void main(String[] args) {
        int graph[][]={
            {0,1,45},//meaning[{0 to 0 =0, 0 to 1 =1 , 0 to 2 = 45}]
            {1,0,6},//meaning [{1 to 0 =1 , 1 to 1 =0, 1 to 2=6 }]
            {-1,-1,0}//meaning [{2 to 0 =INF, 2 to1 =INF, 2 to 2 =0}]
        };
       // Code here
        get_distances_mat(graph);
      
    }

    //Floyd warshall: just like brute force, for each assumed via node, check and take min of [from][to], using mat[from][to]+min(mat[from][to],mat[from][via],mat[via][to]:)  https://www.youtube.com/watch?v=YbY8cVwWAvw&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=42&ab_channel=takeUforward
    public static int[][] get_distances_mat(int graph[][]){//O(n^3)
        int nNodes=graph.length;

        for(int i=0;i<nNodes;i++) for(int j=0;j<nNodes;j++) if(graph[i][j]==-1) graph[i][j]=(int)(1e9);//NOTE: dont use Integer.MAX_VALUE, coz later n loop u will be taking min by adding some other number to it whivh mayy result in integer overflow

        for(int via=0;via<nNodes;via++){
            for(int i=0;i<nNodes;i++){
                for(int j=0;j<nNodes;j++){
                    graph[i][j]=Math.min(graph[i][j], graph[i][via]+graph[via][j]);
                }
            }
        }
         for(int i=0;i<nNodes;i++) for(int j=0;j<nNodes;j++) if(graph[i][j]==(int)(1e9)) graph[i][j]=-1;
       
         Arrays.stream(graph).forEach(row->System.out.println(Arrays.toString(Arrays.stream(row).map(e->(e==Integer.MAX_VALUE)?-1:e).toArray())));
         return graph;
    }
    public boolean hasCycles(int graph[][]){
        int distances[][]=get_distances_mat(graph);
        for(int i=0;i<distances.length;i++) if(distances[i][i]<0) return true;
        return false;
    }
}
