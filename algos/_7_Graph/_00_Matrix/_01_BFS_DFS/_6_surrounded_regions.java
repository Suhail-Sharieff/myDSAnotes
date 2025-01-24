package _7_Graph._00_Matrix._01_BFS_DFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Given a matrix mat where every element is either 'O' or 'X'. Replace all 'O' or a group of 'O' with 'X' that are surrounded by 'X'.

A 'O' (or a set of 'O') is considered to be surrounded by 'X' if there are 'X' at locations just below, just above, just left and just right of it.

Examples:

Input: mat = 
[['X', 'X', 'X', 'X'], 
['X', 'O', 'X', 'X'], 
['X', 'O', 'O', 'X'], 
['X', 'O', 'X', 'X'], 
['X', 'X', 'O', 'O']]
Output: 
[['X', 'X', 'X', 'X'], 
['X', 'X', 'X', 'X'], 
['X', 'X', 'X', 'X'], 
['X', 'X', 'X', 'X'], 
['X', 'X', 'O', 'O']]
Explanation: We only changed those 'O' that are surrounded by 'X'
Input: mat = 
[['X', 'O', 'X', 'X'], 
['X', 'O', 'X', 'X'], 
['X', 'O', 'O', 'X'], 
['X', 'O', 'X', 'X'], 
['X', 'X', 'O', 'O']]
Output: 
[['X', 'O', 'X', 'X'], 
['X', 'O', 'X', 'X'], 
['X', 'O', 'O', 'X'], 
['X', 'O', 'X', 'X'], 
['X', 'X', 'O', 'O']]
Explanation: There's no 'O' that's surround by 'X'.
Input: mat = 
[['X', 'X', 'X'], 
['X', 'O', 'X'], 
['X', 'X', 'X']]
Output: 
[['X', 'X', 'X'], 
['X', 'X', 'X'], 
['X', 'X', 'X']]
Explanation: There's only one 'O' that's surround by 'X'.
Constraints:
1 ≤ mat.size() ≤ 100
1 ≤ mat[0].size() ≤ 100
 */

public class _6_surrounded_regions {


    //solution: we need to mark all 'O's which r surrounded by 'X' ie those 'O's and all thier relatives dont have any relation with boundary, ie that region will not have any memember touching boundary and hence safe to convert to 'X'. So we first corrupt 'O's on all 4 boundaries of grid by marking them and all thier relatives as visisted. Next, we get remaining 0's within matrix tat r Os and not visited, do normal bfs on them and thier relatives and mark them as 'X'



    public void optimal(char[][] board) {
        boolean isVis[][]=new boolean[board.length][board[0].length];
        mark_Os_on_Border_as_visited(board,isVis);

        List<int[]>li=getAll_Os_pos(board,board.length,board[0].length,isVis);
        Queue<int[]>q=new LinkedList<>();


        for(int pos[]:li){
            q.offer(new int[]{pos[0],pos[1]});
            board[pos[0]][pos[1]]='X';
        }
        
        int dirs[][]={{-1,0},{1,0},{0,1},{0,-1}};
        while(!q.isEmpty()){
            int top[]=q.poll();
            int tx=top[0];
            int ty=top[1];
            for(int dir[]:dirs){
                int x=dir[0]+tx;
                int y=dir[1]+ty;
                if(x>0 && y>0 && x<board.length-1 && y<board[0].length-1&& board[x][y]=='O' && !isVis[x][y]){
                    q.offer(new int[]{x,y});
                    board[x][y]='X';
                    isVis[x][y]=true;
                }
            }
        }
    }

    public List<int[]>getAll_Os_pos(char board[][],int nRows,int nCols,boolean isVis[][]){
        List<int[]>li=new ArrayList<>();
        for(int i=1;i<nRows-1;i++){
            for(int j=1;j<nCols-1;j++){
                if(!isVis[i][j] && board[i][j]=='O') li.add(new int[]{i,j});
            }
        }
        return li;
    }
    public void mark_Os_on_Border_as_visited(char grid[][],boolean isVis[][]){
        int nRows=grid.length,nCols=grid[0].length;
        //down wards
        for(int i=0;i<nRows;i++){
            if(!isVis[i][0] && grid[i][0]=='O') bfs(grid,isVis,i,0);
            if(!isVis[i][nCols-1] && grid[i][nCols-1]=='O') bfs(grid,isVis,i,nCols-1);
        }
        //hrizontal
        for(int j=0;j<nCols;j++){
            if(!isVis[0][j] && grid[0][j]=='O') bfs(grid,isVis,0,j);
            if(!isVis[nRows-1][j] && grid[nRows-1][j]=='O') bfs(grid,isVis,nRows-1,j);
        }
    }

    public void bfs(char grid[][],boolean isVis[][],int i,int j){
        int dirs[][]={{1,0},{-1,0},{0,-1},{0,1}};
        Queue<int[]>q=new LinkedList<>();
        int nRows=grid.length,nCols=grid[0].length;
        isVis[i][j]=true;
        q.offer(new int[]{i,j});
        while(!q.isEmpty()){
            int []top=q.poll();
            for(int dir[]:dirs){
                int x=top[0]+dir[0];
                int y=top[1]+dir[1];
                if(x>=0 && x<nRows && y>=0 && y<nCols){
                   if(grid[x][y]=='O' && !isVis[x][y]){
                    q.offer(new int[]{x,y});
                    isVis[x][y]=true;
                   }
                }
            }
        }
    }
    
}


