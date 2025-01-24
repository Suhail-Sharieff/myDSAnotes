package _7_Graph._00_Matrix._02_djikstra;

import java.util.LinkedList;
import java.util.Queue;

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
        int mountain[][]={
            {1,2,2},
            {3,8,2},
            {5,3,5}
        };
        func(mountain);

    }
    public static void func(int mountain[][]){
        int nRows=mountain.length,nCols=mountain[0].length;
        boolean isVis[][]=new boolean[nRows][nCols];
        Queue<int[]>q=new LinkedList<>();
        q.offer(new int[]{0,0});

        int musibath[][]=new int[nRows][nCols];
        musibath[0][0]=0;
        int dirs[][] = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
        isVis[0][0]=true;
        while (!q.isEmpty()) {
            int[] front=q.poll();
            System.out.print("["+front[0]+","+front[1]+"]("+mountain[front[0]][front[1]]+")->");
            for(int dir[]:dirs){
                int x=front[0]+dir[0];
                int y=front[1]+dir[1];
                if(x>=0 && y>-0 && x<nRows && y<nCols){
                    if(!isVis[x][y]){
                        q.offer(new int[]{x,y});
                        isVis[x][y]=true;
                    }
                }
            }
        }
    }
}
