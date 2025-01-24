package _7_Graph._00_Matrix._01_BFS_DFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

 

Example 1:


Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.
 */
public class _4_rottingOranges {

    public static void main(String[] args) {
        int grid[][] = {
                { 2, 1, 1 },
                { 1, 1, 0 },
                { 0, 1, 1 },
        };
        int ans = optimal(grid);
        boolean stillHasFreshOranges = hasFreshOranges(grid);
        if (stillHasFreshOranges) {
            System.out.println(-1);
            return;
        }
        System.out.println(ans);
        ;

    }


    //solution: we will use same idea that of flood fill algorithm, but in that we had only one source from which we had to spread to neighbours, but here the challenge is we may have multiple rotten oranges at varoious diff locations, so:
    // first find the positions where we have rotten oranges in grid
    // in flood fill u pushed initailly image[sr][sc] into queue, instead of that here since u have multiple sources from which  can corrupt neighbours, u push all those starting points inside queue, where each elemnt of queue will hold {xPos,yPos,currTime}


    private static int optimal(int grid[][]) {
        Queue<int[]> q = new LinkedList<>();
        int nRows = grid.length, nCols = grid[0].length;
        List<int[]> rottenPositions = getRottenPositions(grid);
        boolean isVis[][] = new boolean[nRows][nCols];
        for (int[] eachRottenPos : rottenPositions) {
            q.offer(new int[] { eachRottenPos[0], eachRottenPos[1], 0 });
            isVis[eachRottenPos[0]][eachRottenPos[1]] = true;
        }
        int ans = 0;
        int dirs[][] = { { -1, 0 }, { +1, 0 }, { 0, +1 }, { 0, -1 } };
        while (!q.isEmpty()) {
            int[] topCord = q.poll();
            int top_x = topCord[0];
            int top_y = topCord[1];
            int currSecond = topCord[2];
            for (int[] dir : dirs) {
                int neighBour_x = top_x + dir[0];
                int neighBour_y = top_y + dir[1];
                if (neighBour_x >= 0 && neighBour_x < nRows && neighBour_y >= 0 && neighBour_y < nCols) {
                    if (grid[neighBour_x][neighBour_y] == 1 && !isVis[neighBour_x][neighBour_y]) {
                        // rotten that fresh orange
                        grid[neighBour_x][neighBour_y] = 2;
                        q.offer(new int[] { neighBour_x, neighBour_y, currSecond + 1 });//increment currSecond to trverse next
                    }
                }
            }
            ans = currSecond ;
        }
        return (ans);
    }

    private static List<int[]> getRottenPositions(int grid[][]) {
        List<int[]> rottenPos = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2)
                    rottenPos.add(new int[] { i, j });
            }
        }
        return rottenPos;
    }

    private static boolean hasFreshOranges(int grid[][]) {
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 1)
                    return true;
        return false;
    }

}
