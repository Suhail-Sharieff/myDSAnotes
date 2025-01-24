package _7_Graph._00_Matrix._02_djikstra;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
//prereq: disktra algo as in _03_Undir_weigh
/*
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

 

Example 1:


Input: grid = [[0,1],[1,0]]
Output: 2
Example 2:


Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
 */
public class _01_shortest_path_in_matrix {

    public static void main(String[] args) {
        int mat[][] = {
                { 0, 0, 0 },
                { 1, 1, 0 },
                { 1, 1, 0 }
        };
        Point src=new Point(0, 0);
        Point dest=new Point(mat.length-1, mat[0].length-1);
        func(mat,src,dest);
    }

    public static int func(int grid[][],Point src,Point dest) {



        int nRows = grid.length, nCols = grid[0].length;
        if (grid[src.x][src.y] == 1 || grid[dest
        .x][dest.y] == 1)
            return -1;

        int dis[][] = new int[nRows][nCols];
        for (int r[] : dis)
            Arrays.fill(r, Integer.MAX_VALUE);

        dis[src.x][src.y] = 1;

        Queue<int[]> q = new LinkedList<>();// each elemnt in queue will store [xPos,yPos,distFrom(0,0)]
        q.offer(new int[] { src.x, src.y, 1 });

        boolean isVis[][] = new boolean[nRows][nCols];// if we dont maintain a !isVisArray, we will run into infinite
                                                      // addition in queue
        isVis[src.x][src.y] = true;

        // we need all 8 dirs as given
        int dirs[][] = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

        while (!q.isEmpty()) {
            int[] top = q.poll();
            for (int dir[] : dirs) {

                int x = top[0] + dir[0];
                int y = top[1] + dir[1];

                if (x >= 0 && y >= 0 && x < nRows && y < nCols) {

                    if (dis[x][y] > top[2] + 1) {
                        q.offer(new int[] { x, y, top[2] + 1 });
                        dis[x][y] = top[2] + 1;
                        isVis[x][y] = true;
                    }
                }
            }
        }
        for (int r[] : dis)
            System.out.println(Arrays.toString(r));
        return (dis[dest.x][dest.y] != Integer.MAX_VALUE) ? dis[dest.x][dest.y] : -1;
    }
}
