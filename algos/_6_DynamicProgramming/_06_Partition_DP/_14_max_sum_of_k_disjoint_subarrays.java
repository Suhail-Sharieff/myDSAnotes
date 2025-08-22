package _6_DynamicProgramming._06_Partition_DP;

import java.util.Arrays;

public class _14_max_sum_of_k_disjoint_subarrays {
    long dp[][][];
    static final long NEG_INF = (long) -1e18;

    public long maximumStrength(int[] arr, int k) {
        return tab2(arr, k);
    }

    
    long rec(int i, int k, int arr[], boolean isEven, long prefSum[],String s,long v) {
        if (i >= arr.length) {
            System.out.println(s+"===>"+v);
            if (k == 0)
                return 0;
            return NEG_INF;
        }
        if (dp[i][k][isEven ? 1 : 0] != -1)
            return dp[i][k][isEven ? 1 : 0];
        long op2 = NEG_INF;
        if (k > 0)//IMP
            for (int z = i; z < arr.length; z++) {
                // i..z & z+1...j
                long cost = (isEven ? k : -k) * (prefSum(prefSum, i, z));
                long res = rec(z + 1, k - 1, arr, !isEven, prefSum,s+"{"+arr[i]+","+arr[z]+"}->",v+cost);
                if (res != NEG_INF)
                    op2 = max(op2,
                            cost + res);
            }
        long op1 = rec(i + 1, k, arr, isEven, prefSum,s,v);
        return dp[i][k][isEven ? 1 : 0] = max(op1, op2);
    }
    public long tab1(int[] arr, int k) {
        int n = arr.length;
        long prefSum[] = new long[n];
        prefSum[0] = arr[0];
        for (int i = 1; i < n; i++)
            prefSum[i] = prefSum[i - 1] + arr[i];
        dp = new long[n + 1][k + 1][2];
        for (long e[][] : dp)
            for (long row[] : e)
                Arrays.fill(row, NEG_INF);
        // long ans=rec(0,k, arr, true,prefSum);
        dp[n][0][0] = dp[n][0][1] = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int gk = k; gk >= 0; gk--) {
                for (int x = 0; x <= 1; x++) {
                    boolean isEven = (x == 1);
                    long op1 = dp[i + 1][gk][x];
                    long op2 = NEG_INF;
                    if (gk > 0)
                        for (int z = i; z < arr.length; z++) {
                            // i..z & z+1...j
                            long cost = (isEven ? gk : -gk) * (prefSum(prefSum, i, z));
                            long res = dp[z + 1][gk - 1][x ^ 0 ^ 1];
                            if (res != NEG_INF)
                                op2 = max(op2, cost + res);
                        }
                    dp[i][gk][x] = max(op1, op2);
                }
            }
        }

        return dp[0][k][1];
    }

    long prefSum(long prefSum[], int i, int j) {
        if (i - 1 < 0)
            return prefSum[j];
        return prefSum[j] - prefSum[i - 1];
    }

    long max(long x, long y) {
        return Math.max(x, y);
    }


    //https://www.youtube.com/watch?v=vGI75BQhDVI
    long rec2(int i, int k, int arr[], boolean start_new) {
        if (k == 0)
            return 0;
        if (i >= arr.length)
            return NEG_INF;

        long op1 = NEG_INF;

        if (start_new) {
            op1 = rec2(i + 1, k, arr, true);
        }
        long take_and_continue = ((k % 2 != 0) ? k : -k) * arr[i] + rec2(i + 1, k, arr, false);
        long take_and_form_new_next = ((k % 2 != 0) ? k : -k) * arr[i] + rec2(i + 1, k - 1, arr, true);
        long op2 = Math.max(take_and_continue, take_and_form_new_next);
        return Math.max(op1, op2);
    }

    long tab2(int arr[],int k){
        int n=arr.length;
        dp = new long[n + 1][k + 1][2];
        for (long e[][] : dp)
            for (long row[] : e)
                Arrays.fill(row, NEG_INF);

        for (int i = 0; i <= n; i++) {
            dp[i][0][0] = dp[i][0][1] = 0;
        }
        for (int i = n - 1; i >= 0; i--) {
            // dp[i][0][0]=dp[i][0][1]=0;
            for (int gk = k; gk > 0; gk--) {
                for (int x = 0; x <= 1; x++) {
                    boolean start_new = (x == 1);
                     long op1 = NEG_INF;

                    if (start_new) {
                        op1 = dp[i+1][gk][1];
                    }
                    long take_and_continue = ((gk % 2 != 0) ? gk : -gk) *1l* arr[i] + dp[i+1][gk][0];
                    long take_and_form_new_next = ((gk % 2 != 0) ? gk : -gk) *1l* arr[i] + dp[i+1][gk-1][1];
                    long op2 = Math.max(take_and_continue, take_and_form_new_next);
                    dp[i][gk][x]=max(op1, op2);
                    
                        
                }
            }
        }
        return dp[0][k][1];
    }
}
