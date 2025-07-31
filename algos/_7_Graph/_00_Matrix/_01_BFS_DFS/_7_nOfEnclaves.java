package _7_Graph._00_Matrix._01_BFS_DFS;
public class _7_nOfEnclaves {
    public static void main(String[] args) {
        
    }

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

        static class Solution {
            int nR;
            int nC;
            int mat[][];
            boolean isVis[][];
            public int numEnclaves(int[][] g) {
                mat=g;
                nR=mat.length;
                nC=mat[0].length;
                isVis=new boolean[nR][nC];
                corrupt_from_borders();
                int cnt=0;
                for(int i=1;i<nR-1;i++){
                    for(int j=1;j<nC-1;j++){
                        if(mat[i][j]==1 && !isVis[i][j]){
                            cnt++;
                        }
                    }
                }
                return cnt;
            }
            void corrupt_from_borders(){
                for(int j=0;j<nC;j++) if(mat[0][j]==1) dfs(0,j);//first row
                for(int i=0;i<nR;i++) if(mat[i][nC-1]==1) dfs(i,nC-1);//last column
                for(int i=0;i<nR;i++) if(mat[i][0]==1) dfs(i,0);//forst column
                for(int j=0;j<nC;j++) if(mat[nR-1][j]==1) dfs(nR-1,j);//last row
            }
            int dirs[][]={{-1,0},{1,0},{0,1},{0,-1}};
            void dfs(int i,int j){
                isVis[i][j]=true;
                for(int dir[]:dirs){
                    int x=dir[0]+i;
                    int y=dir[1]+j;
                    if(x>=0&&y>=0&&x<nR&&y<nC&&mat[x][y]==1&&!isVis[x][y]) dfs(x,y);
                }
            }
    }
}
