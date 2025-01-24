package _7_Graph._00_Matrix._01_BFS_DFS;
//shows how we can spread from (i,j) in a matrix to its neighbours
import java.util.LinkedList;
import java.util.Queue;
/*
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
 */

public class _2_count_Islands {
   //intutuion: 
   /*
    we go through evry elemnt in matrix, if its not visited and is 1, we push all its neighbours by looping ith row from i-1 to i+1 and col j from j-1 to j+1 handling cases to avoid neg ie we will spread in 8 directions from that point, and push all its unvisited neighbours   to the queue(ie perform dfs), ie we get 1 node of any island, use queue(bfs) to spread and mark all its surrounding (i,j)th 1s as visited, further increment counter if found any unvisited 1
    */


    public static void perFormBFS(Cordinate unVisCord,char[][]grid,boolean isVis[][]){

        Queue<Cordinate>q=new LinkedList<>();

        q.offer(new Cordinate(unVisCord.x, unVisCord.y));
        isVis[unVisCord.x][unVisCord.y]=true;
        //we got the cordinate that is 1 and unvisited, spread to its cordinates in the matrix and mark them as visited
        int nRows=grid.length,nCols=grid[0].length;

        while (!q.isEmpty()) {
            Cordinate top=q.poll();
            int x=top.x;
            int y=top.y;
            //spread circularly in all 8 dirs from [-1,1] for both col and row
            for(int i=-1;i<=1;i++){
                for(int j=-1;j<=1;j++){
                    //handle cases where (row+i can become negative and also exceed nRows) and same handle for col
                    int curr_X_of_neigh=x+i;
                    int curr_Y_of_neigh=y+j;
                    if(
                        (curr_X_of_neigh<nRows && curr_X_of_neigh>=0)&&

                        (curr_Y_of_neigh<nCols && curr_Y_of_neigh>=0)
                    ){
                        //if its 1 and unvisited then we mark it as visited and push to queue so that we can further spread from that cord
                        if(grid[curr_X_of_neigh][curr_Y_of_neigh]=='1' && !isVis[curr_X_of_neigh][curr_Y_of_neigh]){
                            //mark as visited
                            isVis[curr_X_of_neigh][curr_Y_of_neigh]=true;
                            //push to queue so that we further spread from that point
                            q.offer(new Cordinate(curr_X_of_neigh, curr_Y_of_neigh));
                        }
                    }

                }
            }
        }

    }


    public int optimalUsing_BFS(char grid[][]){//more efficient than DFS which uses recursion
        int nRows=grid.length,nCols=grid[0].length;
        boolean isVis[][]=new boolean[nRows][nCols];

        int ans=0;

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if(grid[i][j]=='1' && !isVis[i][j]){
                    ans++;
                    //spread
                    Cordinate unVisCord=new Cordinate(i, j);
                    perFormBFS(unVisCord, grid, isVis);
                }
            }
        }

        return ans;
    }



    public int optimalUsing_DFS(char grid[][]){//can result in stack overflow, so use BFS apprach better
        int nRows=grid.length,nCols=grid[0].length;

        int ans=0;

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if(grid[i][j]=='1'){
                    ans++;
                    performDFS(grid, new Cordinate(i, j));
                }
            }
        }

        return ans;
    }

    public void performDFS(char grid[][],Cordinate unVisCord){
        int x=unVisCord.x;
        int y=unVisCord.y;
        int nRows=grid.length,nCols=grid[0].length;
        if(x>nRows || x<0 || y>nCols || y<0) return;

        //we change '1' to '0' instaed of using isVis to mark
        grid[x][y]='0';
        performDFS(grid, new Cordinate(x+1, y));//move right
        performDFS(grid, new Cordinate(x-1,y));//move left
        performDFS(grid, new Cordinate(x, y+1));//move up
        performDFS(grid, new Cordinate(x, y-1));//move down
    }
}


class Cordinate{
    int x;
    int y;
     Cordinate(int x,int y){
        this.x=x;
        this.y=y;
    }
}
