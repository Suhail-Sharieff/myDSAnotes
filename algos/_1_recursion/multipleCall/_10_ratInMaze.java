package _1_recursion.multipleCall;

import java.util.ArrayList;
import java.util.List;

/*
Rat in a Maze Problem - I
https://www.youtube.com/watch?v=bLGZhJlt4y0&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=20

Consider a rat placed at (0, 0) in a square matrix of order N * N. It has to reach the destination at (N - 1, N - 1). Find all possible paths that the rat can take to reach from source to destination. The directions in which the rat can move are 'U'(up), 'D'(down), 'L' (left), 'R' (right). Value 0 at a cell in the matrix represents that it is blocked and rat cannot move to it while value 1 at a cell in the matrix represents that rat can be travel through it.
Note: In a path, no cell can be visited more than one time. If the source cell is 0, the rat cannot move to any other cell.

Example 1:

Input:
N = 4
m[][] = {{1, 0, 0, 0},
         {1, 1, 0, 1}, 
         {1, 1, 0, 0},
         {0, 1, 1, 1}}
Output:
DDRDRR DRDDRR
Explanation:
The rat can reach the destination at 
(3, 3) from (0, 0) by two paths - DRDDRR 
and DDRDRR, when printed in sorted order (lexicographically ie D>L>R>U)
we get DDRDRR DRDDRR.
Example 2:
Input:
N = 2
m[][] = {{1, 0},
         {1, 0}}
Output:
-1
Explanation:
No path exists and destination cell is 
blocked.
Your Task:  
You don't need to read input or print anything. Complete the function printPath() which takes N and 2D array m[ ][ ] as input parameters and returns the list of paths in lexicographically increasing order. 
Note: In case of no path, return an empty list. The driver will output "-1" automatically.


//my notes: in a 2D matrix movement from a point at (i,j) varies as
                    (i-1,j)
                       ↑
            (i,j-1) ← (i,j) → (i,j+1)
                       ↓
                    (i+1,j)
*/
public class _10_ratInMaze {
    public static void searchPath(int xPos,int yPos,StringBuilder sb,List<String>ans,boolean visited[][],int grid[][],int n){

        

        //if reached last cell ie cordinate (n-1,n-1) for a nXn grid
        if (xPos==n-1&&yPos==n-1) {
            ans.add(new String(sb));
            return;
        }
        //move lexicographically ie DOWN-->LEFT-->RIGHT-->UP ie alphabatically,at each step check if that cell can be visisted,is not visisted and can be visisted(ie 1) all 3 rules

        //mark currentpos as visisted
        visited[xPos][yPos]=true;

        //downward
        if (xPos+1<n&&!visited[xPos+1][yPos]&&grid[xPos+1][yPos]==1) {
            //now move down from that cell and from there again search entire path
            searchPath(xPos+1, yPos, new StringBuilder(sb).append("D"), ans, visited, grid, n);
        }
        //left
        if(yPos-1>=0&&!visited[xPos][yPos-1]&&grid[xPos][yPos-1]==1){
            searchPath(xPos, yPos-1,new StringBuilder(sb).append("L"), ans, visited, grid, n);
        }
        //right
        if(yPos+1<n&&!visited[xPos][yPos+1]&&grid[xPos][yPos+1]==1){
            searchPath(xPos, yPos+1, new StringBuilder(sb).append("R"), ans, visited, grid, n);
        }
        //up
        if(xPos-1>=0&&!visited[xPos-1][yPos]&&grid[xPos-1][yPos]==1){
            searchPath(xPos-1, yPos, new StringBuilder(sb).append("U"), ans, visited, grid, n);
        }
       
        //mark currPos as false again
        visited[xPos][yPos]=false;
    }
    public static void main(String[] args) {
        //soln 1:
        List<String>li=new ArrayList<>();
        int n = 4;
int grid[][] 
      = {{1, 0, 0, 0},
         {1, 1, 0, 1}, 
         {1, 1, 0, 0},
         {0, 1, 1, 1}};
         searchPath(0, 0, new StringBuilder(), li, new boolean[n][n], grid, n);
         if (!li.isEmpty()) {
            System.out.println(li);
         }else{
            System.out.println(-1);
         }


         //soln 2:
         List<String>li2=new ArrayList<>();
         int x[]={+1,+0,+0,-1};
         int y[]={+0,-1,+1,+0};
         searchPath2(0, 0, n, new StringBuilder(), li2, x, y, new boolean[n][n], grid);

         System.out.println(li2);
         
    }

    //for interviews use this short soln
    public static void searchPath2(int xPos,int yPos,int n,StringBuilder sb,List<String>ans,int x[],int y[],boolean visisted[][],int grid[][]){
        if(xPos==n-1&&yPos==n-1){
            ans.add(new String(sb));
            return;
        }

        visisted[xPos][yPos]=true;

        String format="DLRU";//lexicographical order

        for (int idx = 0; idx < format.length(); idx++) {
            int nextXPos=xPos+x[idx];
            int nextYPos=yPos+y[idx];
            if (nextXPos>=0&&nextYPos>=0&&nextXPos<n&&nextYPos<n&&!visisted[nextXPos][nextYPos]&&grid[nextXPos][nextYPos]==1) {
                searchPath(nextXPos, nextYPos, sb.append(format.charAt(idx)), ans, visisted, grid, n);
            }
        }

        visisted[xPos][yPos]=false;

    }
}
