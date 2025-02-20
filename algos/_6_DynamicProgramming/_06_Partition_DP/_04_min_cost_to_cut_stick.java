package _6_DynamicProgramming._06_Partition_DP;

import java.util.Arrays;

public class _04_min_cost_to_cut_stick {
    public int minCost(int n, int[] cuts) {

        // int arr[]=new int[cuts.length+2];
        // System.arraycopy(cuts,0,arr,1,cuts.length);
        // arr[cuts.length+1]=n;
        // Arrays.sort(arr);
        // int dp[][]=new int[cuts.length+2][cuts.length+2];
        // for(int r[]:dp) Arrays.fill(r,-1);
        // return mem(arr,1,cuts.length,dp);
        return tab(n,cuts);
    }


    public int rec(int cuts[],int i,int j){
        if(i>j) return 0;
        int min=1_000_000_000;
        for(int k=i;k<=j;k++){
            int cost=rec(cuts,i,k-1)+rec(cuts,k+1,j)+cuts[j+1]-cuts[i-1];
            min=Math.min(min,cost);
        }
        Integer.toBinaryString(i).format("%0"+3+"d");
        return min;
    }
    public int mem(int cuts[],int i,int j,int dp[][]){
        if(i>j) return (dp[i][j]=0);
        if(dp[i][j]!=-1) return dp[i][j];
        int min=1_000_000_000;
        for(int k=i;k<=j;k++){
            int cost=mem(cuts,i,k-1,dp)+mem(cuts,k+1,j,dp)+cuts[j+1]-cuts[i-1];
            min=Math.min(min,cost);
        }
        return dp[i][j]=min;
    }
    public int tab(int n,int cuts[]){
        int dp[][]=new int[cuts.length+2][cuts.length+2];
         int arr[]=new int[cuts.length+2];
        System.arraycopy(cuts,0,arr,1,cuts.length);
        arr[cuts.length+1]=n;
        Arrays.sort(arr);
        for(int i=arr.length-2;i>0;i--){
            for(int j=i;j<=arr.length-2;j++){
                int min=1_000_000_000;
                for(int k=i;k<=j;k++){
                    int cost=dp[i][k-1]+dp[k+1][j]+arr[j+1]-arr[i-1];
                    min=Math.min(min,cost);
                }
                dp[i][j]=min;
            }
        }
        // for(int r[]:dp) System.out.println(Arrays.toString(r));
        return dp[1][arr.length-2];
    }
}
