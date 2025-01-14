package _7_Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _8_nOfEnclaves {
    public static void main(String[] args) {
        
    }


    public static int optimal(int mat[][]){
        int nRows=mat.length,nCols=mat[0].length;
        boolean isVis[][]=new boolean[nRows][nCols];
        mark_enclavs_touching_border_as_visited(mat, isVis);

        

        //follow up: if asked to count number of island where each island is a cluster of 1s which follow given condition, ie doesnt touch border:
        /*
        List<int[]>li=get_unvisited_enclave_landCell_pos(mat, isVis);
        int ans=0;
         for(int pos[]:li){
            int i=pos[0],j=pos[1];
            if(!isVis[i][j] && mat[i][j]==1){
                //perform bfs and mark all that points relatives as visited
                bfs(mat,isVis,i,j);
                ans++;
            }
         }
            return ans;
         */


        int nLandCells=0;

        for(int i=0;i<nRows;i++){
            for(int j=0;j<nCols;j++){
                if(!isVis[i][j] && mat[i][j]==1) nLandCells++;
            }
        }

       return(nLandCells);

    }


    public static void mark_enclavs_touching_border_as_visited(int mat[][],boolean isVis[][]){
        int nRows=mat.length,nCols=mat[0].length;
        for(int i=0;i<nRows;i++){
            if(mat[i][0]==1 && !isVis[i][0]) bfs(mat, isVis, i, 0);
            if(mat[i][nCols-1]==1 && !isVis[i][nCols-1]) bfs(mat, isVis, i, nCols-1);
        }
        for(int j=0;j<nCols;j++){
            if(mat[0][j]==1 && !isVis[0][j]) bfs(mat, isVis, 0, j);
            if(mat[nRows-1][j]==1 && !isVis[nRows-1][j]) bfs(mat, isVis, nRows-1, j);
        }
    }

    public static void bfs(int mat[][],boolean isVis[][],int i, int j){
        isVis[i][j]=true;
        int dirs[][]={{-1,0},{1,0},{0,1},{0,-1}};
        int nRows=mat.length,nCols=mat[0].length;
        Queue<int[]>q=new LinkedList<>();
        q.offer(new int[]{i,j});
        while (!q.isEmpty()) {
            int top[]=q.poll();
            for(int dir[]:dirs){
                int x=top[0]+dir[0];
                int y=top[1]+dir[1];
                if(x<nRows && x>=0 && y<nCols && y>=0){
                    if(mat[x][y]==1 && !isVis[x][y]){
                        q.offer(new int[]{x,y});
                        isVis[x][y]=true;
                    }
                }
            }
        }
    }


    public static List<int[]>get_unvisited_enclave_landCell_pos(int mat[][],boolean isVis[][]){
        List<int[]>li=new ArrayList<>();
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length;j++){
                if(!isVis[i][j] && mat[i][j]==1) li.add(new int[]{i,j});
            }
        }
        return li;
    }
}
