package _7_Graph._00_Matrix._02_djikstra;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

 

Example 1:



Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
Example 2:



Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
Output: 1
Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
Example 3:


Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
Output: 0
Explanation: This route does not require any effort.
 

Constraints:

rows == heights.length
columns == heights[i].length
1 <= rows, columns <= 100
1 <= heights[i][j] <= 106
 */
public class _02_path_with_min_effort {
    public static void main(String[] args) {
        int heights[][]={
            {1,2,2},
            {3,8,2},
            {5,3,5}
        };
        System.out.println(djikstra(heights));

    }

    static int dirs[][]={{-1,0},{1,0},{0,-1},{0,1}};
    public int rec(int mat[][],int i,int j,boolean isVis[][],int currMax){//using recursion
        if(i==mat.length-1 && j==mat[0].length-1) return currMax;
        isVis[i][j]=true;
        int min=Integer.MAX_VALUE;
        for(int dir[]:dirs){
            int x=i+dir[0];
            int y=j+dir[1];
            if(x>=0 && y>=0 && x<mat.length && y<mat[0].length){
                if(!isVis[x][y]){
                    int diff=Math.abs(mat[i][j]-mat[x][y]);
                    int res=rec(mat,x,y,isVis,Math.max(currMax,diff));
                    min=Math.min(min,res);
                }
            }
        }
        isVis[i][j]=false;
        return  min;
    }


    //why dp wont work: becoz we have backtracking here.
    // why not just bfs via Q with isVis?: coz here we dont need shortest path, instead we need min of all max diffs, so use PQ and we may get WA if we also maintain isVis array, coz wt if we encounter a neighbour that was visted, but we visit again with still less cost, so use PQ instead

    public static int djikstra(int heights[][]){
        int nRows = heights.length, nCols = heights[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y)->x[0]-y[0]);
        int dis[][] = new int[nRows][nCols];
        for (int r[] : dis)
        Arrays.fill(r, Integer.MAX_VALUE);
        dis[0][0] = 0;
        pq.offer(new int[] { 0, 0, 0 });//store elemnts in pq in format [weight,xPos,yPos]
        int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        while (!pq.isEmpty()) {
            int[] front = pq.poll();
            for (int dir[] : dirs) {
                int curr_max_effort=front[0];
                int x = dir[0] + front[1];
                int y = dir[1] + front[2];
                if (x >= 0 && y >= 0 && x < nRows && y < nCols) {
                    int abs_diff=Math.abs(heights[front[1]][front[2]] - heights[x][y]);
                    int new_max_effort_needed = Math.max(abs_diff,curr_max_effort);
                    if (new_max_effort_needed < dis[x][y]) {
                        dis[x][y] =  new_max_effort_needed;
                        pq.offer(new int[]{dis[x][y],x,y});
                    }
                }
            }
        }
        // for(int r[]:dis) System.out.println(Arrays.toString(r));
        return dis[nRows-1][nCols-1];
    }
}

