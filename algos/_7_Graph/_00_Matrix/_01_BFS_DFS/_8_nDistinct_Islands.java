package _7_Graph._00_Matrix._01_BFS_DFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
Given a boolean 2D matrix grid of size n * m. You have to find the number of distinct islands where a group of connected 1s (horizontally or vertically) forms an island. Two islands are considered to be distinct if and only if one island is not equal to another (not rotated or reflected).

Example 1:

Input:
grid[][] = {{1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 0, 0, 1, 1}}
Output:
1
Explanation:
grid[][] = {{1, 1, 0, 0, 0}, 
            {1, 1, 0, 0, 0}, 
            {0, 0, 0, 1, 1}, 
            {0, 0, 0, 1, 1}}
Same colored islands are equal.
We have 2 equal islands, so we 
have only 1 distinct island.

Example 2:

Input:
grid[][] = {{1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1},
            {1, 1, 0, 1, 1}}
Output:
3
Explanation:
grid[][] = {{1, 1, 0, 1, 1}, 
            {1, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 1}, 
            {1, 1, 0, 1, 1}}
Same colored islands are equal.
We have 4 islands, but 2 of them
are equal, So we have 3 distinct islands.

Your Task:

You don't need to read or print anything. Your task is to complete the function countDistinctIslands() which takes the grid as an input parameter and returns the total number of distinct islands.

Expected Time Complexity: O(n * m)
Expected Space Complexity: O(n * m)

Constraints:
1 ≤ n, m ≤ 500
grid[i][j] == 0 or grid[i][j] == 1
 */
public class _8_nDistinct_Islands {
    public static void main(String[] args) {
        int grid[][] = {{1, 1, 0, 1, 1},
        {1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1},
        {1, 1, 0, 1, 1}};

       
        optimal(grid);

    }


    //solution is to like whenever we find a unvis node, we consider it as base, we will append it say as curr->, then we move iin all dirs, append(xDiff,yDiff) wrt base, observe that it would be same for same shaped islands, so we can use it

     static int  optimal(int grid[][]){
        int nRows=grid.length;
        int nCols=grid[0].length;

        boolean isVis[][]=new boolean[nRows][nCols];

        Set<String>se=new HashSet<>();


        int dirs[][]={{-1,0},{0,1},{-1,0},{0,-1}};


        for(int i=0;i<grid.length;i++){
            for(int j=0;j<nCols;j++){
                if(grid[i][j]==1 && !isVis[i][j]){
                    Queue<int[]>q=new LinkedList<>();
                    StringBuilder sb=new StringBuilder("src->");
                    q.offer(new int[]{i,j});
                    isVis[i][j]=true;
                    int src_x=i;
                    int src_y=j;
                    while (!q.isEmpty()) {
                        int top[]=q.poll();
                        for(int dir[]:dirs){
                            int x=top[0]+dir[0];
                            int y=top[1]+dir[1];
                            if(x>=0 && y>=0 && x<nRows && y<nCols){
                                if(grid[x][y]==1 && !isVis[x][y]){
                                    q.offer(new int[]{x,y});
                                    sb.append("("+(x-src_x)+","+(y-src_y)+")->");
                                    isVis[x][y]=true;
                                } 
                            }
                        }
                    }
                    se.add(sb.toString());
                }
            }
        }   

        System.out.println(se);

        return (se.size());



    }


    //----------WRONG APPROACH: this would help in printling all paths and they would be identified as distinct, but we cant store in set,so use above approach itself

    // public static void dfs(int grid[][],boolean isVis[][],int i, int j,StringBuilder sb,Set<String>se){
    //     int nRows=grid.length;
    //     int nCols=grid[0].length;
    //     isVis[i][j]=true;
    //     if(i+1<nRows && grid[i+1][j]==1 && !isVis[i+1][j]) dfs(grid, isVis, i+1, j, new StringBuilder(sb).append('D'),se);
    //     if(i-1>=0 && grid[i-1][j]==1 && !isVis[i-1][j]) dfs(grid, isVis, i-1, j, new StringBuilder(sb).append('U'),se);
    //     if(j+1<nCols && grid[i][j+1]==1 && !isVis[i][j+1]) dfs(grid, isVis, i, j+1, new StringBuilder(sb).append('R'),se);
    //     if(j-1>=0 && grid[i][j-1]==1 && !isVis[i][j-1]) dfs(grid, isVis, i, j-1, new StringBuilder(sb).append('L'),se);
    //     System.out.print(sb);
    // }

    
}
