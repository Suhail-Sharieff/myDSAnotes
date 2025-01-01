import java.util.*;

public class test {

    public static void main(String[] args) {
        HashSet<List<Integer>>hs=new HashSet<>();
        int mat[][]={
            {1,2},
            {1,3},
            // {3,3},
        };
        boolean isVis[]=new boolean[mat.length];
        dfs(mat, new boolean[]{false}, 0);
       
    }

    static void dfs(int adj[][],boolean isVis[],int nodeNumber){
        isVis[nodeNumber]=true;
        System.out.println(nodeNumber+1);
        for(int  e : adj[nodeNumber]){
            if (!isVis[nodeNumber]) {
                dfs(adj, isVis, e);
            }
        }
    }
}