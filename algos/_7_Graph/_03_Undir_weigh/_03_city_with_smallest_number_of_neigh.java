package _7_Graph._03_Undir_weigh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//prereq: floyd warshall as described in _04_Dir_weigh/_04_floyd----application of floyd waeshall
/*
There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.

Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.

Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.

 

Example 1:



Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
Output: 3
Explanation: The figure above describes the graph. 
The neighboring cities at a distanceThreshold = 4 for each city are:
City 0 -> [City 1, City 2] 
City 1 -> [City 0, City 2, City 3] 
City 2 -> [City 0, City 1, City 3] 
City 3 -> [City 1, City 2] 
Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
Example 2:



Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
Output: 0
Explanation: The figure above describes the graph. 
The neighboring cities at a distanceThreshold = 2 for each city are:
City 0 -> [City 1] 
City 1 -> [City 0, City 4] 
City 2 -> [City 3, City 4] 
City 3 -> [City 2, City 4]
City 4 -> [City 1, City 2, City 3] 
The city 0 has 1 neighboring city at a distanceThreshold = 2.
 

Constraints:

2 <= n <= 100
1 <= edges.length <= n * (n - 1) / 2
edges[i].length == 3
0 <= fromi < toi < n
1 <= weighti, distanceThreshold <= 10^4
All pairs (fromi, toi) are distinct.
 */
public class _03_city_with_smallest_number_of_neigh {

    static class Brute {//for each pair i,j we keep i as src and j as dest, find how many cities r reachable from i to j under threshHold, cnt and return min of such
    public int findTheCity(int n, int[][] g, int limit) {
        List<List<int[]>>adj=getAdj(n,g);

        int ans=-1;
        int minCnt=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(j!=i){
                    int cnt=f(i,j,adj,limit,n);
                    if(cnt<=minCnt){
                        minCnt=cnt;
                        ans=Math.max(ans,i);
                    }
                }
            }
        }
        return ans;

    }


    public int f(int src,int dest,List<List<int[]>>adj,int limit,int n){
        int dis[]=new int[n];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[src]=0;
        Queue<Integer>q=new LinkedList<>();
        q.offer(src);
        while(!q.isEmpty()){
            int top=q.poll();
            for(int pair[]:adj.get(top)){
                int to=pair[0],w=pair[1];
                if(w+dis[top]<dis[to]){
                    dis[to]=w+dis[top];
                    q.offer(to);
                }
            }
        }
        int cnt=0;
        for(int e:dis) if(e<=limit) cnt++;
        return cnt;
    }


    public List<List<int[]>> getAdj(int n,int g[][]){
        List<List<int[]>>adj=new ArrayList<>();
        while(n--!=0) adj.add(new ArrayList<>());
        for(int e[]:g){
            adj.get(e[0]).add(new int[]{e[1],e[2]});
            adj.get(e[1]).add(new int[]{e[0],e[2]});
        }
        return adj;
    }
}

    //the idea of fl;oyd warshall comes from the bvrute force, observbe in brute force that we repeatedly calcuate cnt(i,j) , instaed of this if we precomute how much is mion cost to movce from i to j in one go, then same mat we can use again for all n cities, hence floyd warshall since it has power to form a mat that could give us minWeight to mvbe gtrom i to j after one pass

    //this can also be solved using djikstra, where for every nodeNumber from 0..n, get distance array for each nodeNumber as src, then for each of that distance array check how many citites r <=threshhol and return ans, here i will be using floyd_warshall---N^2 * (N+M)---TLE


    //since WKT floyd warshall fives us the matrix having mat[i][j] as min cost to reach from i to j, we can make use of it to return answer
    public int floyd_warshall(int n, int[][] edges, int distanceThreshold) {
        int dp[][]=get_mat(edges,n);
        for(int nodeNumber=0;nodeNumber<n;nodeNumber++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    dp[i][j]=Math.min(dp[i][j],dp[i][nodeNumber]+dp[nodeNumber][j]);
                }
            }
        }
        int min=n;
        int ans=-1;
        for(int i=0;i<n;i++){
            int cnt=0;
            for(int j=0;j<n;j++){
                if(dp[i][j]<=distanceThreshold ) cnt++;
            }
            // System.out.println("from city "+i+" we can reach "+cnt+" cities");
            if(cnt<=min){
                min=cnt;
                ans=i;
            }
        }
        return ans;
    }

    public int[][] get_mat(int edges[][],int n){
        int mat[][]=new int[n][n];
        for(int row[]:mat) Arrays.fill(row,(int)(1e9));
        for(int each[]:edges){
            mat[each[0]][each[0]]=0;
            mat[each[1]][each[1]]=0;
            mat[each[0]][each[1]]=each[2];
            mat[each[1]][each[0]]=each[2];
        }
        return mat;
    }
}
