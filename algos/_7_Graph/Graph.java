package _7_Graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public List<List<Integer>>adjListFromAdjMat(int mat[][]){
        List<List<Integer>>adj=new ArrayList<>();
        for(int i=0;i<mat.length;i++){
            List<Integer>thatNode=new ArrayList<>();
            for(int j=0;j<mat.length;j++){
                if(mat[i][j]==1 &&i!=j){
                    thatNode.add(j);
                }
            }
            adj.add(thatNode);
        }
        return adj;
    }

    public static int[][] adjMatrixFromAdjList(List<List<Integer>>adj){
        int len=adj.size();
        int mat[][]=new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                mat[i][j]=adj.get(i).get(j);
            }
        }
        return mat;
    }
}
