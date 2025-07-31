import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) {
        int mat[][]={
            {1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}
        }
        ;
       
        PriorityQueue<int[]>pq=new PriorityQueue<>((x,y)->x[2]-y[2]);
        pq.offer(new int[]{0,0,0});
        int ans[][]=new int[mat.length][mat[0].length];
        for(int i=0;i<ans.length;i++) for(int j=0;j<ans[0].length;j++) ans[i][j]=Integer.MAX_VALUE;
        ans[0][0]=0;
        while (!pq.isEmpty()) {
            int top[]=pq.poll();
            int i=top[0],j=top[1],currMax=top[2];
            if(ans[i][j]<currMax) continue;
            for(int dir[]:dirs){
                int x=dir[0]+i;
                int y=dir[1]+j;
                if(x>=0&&y>=0&&x<mat.length&&y<mat[0].length){
                    int abs=Math.max(Math.abs(mat[i][j]-mat[x][y]),currMax);
                    if(ans[x][y]>abs){
                        ans[x][y]=abs;
                        pq.offer(new int[]{x,y,abs});
                    }
                }
            }
        }

        for(var e:ans) System.out.println(Arrays.toString(e));
        
    }
    static  int dirs[][]={{-1,0},{1,0},{0,-1},{0,1}};
    // static int rec(int i,int j,int mat[][]){

    // }
}