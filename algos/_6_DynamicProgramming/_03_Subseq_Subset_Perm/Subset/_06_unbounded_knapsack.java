package _6_DynamicProgramming._03_Subseq_Subset_Perm.Subset;

import java.util.Arrays;

/*
 You are given ‘n’ items with certain ‘profit’ and ‘weight’ and a knapsack with weight capacity ‘w’.



You need to fill the knapsack with the items in such a way that you get the maximum profit. You are allowed to take one item multiple times.



Example:
Input: 
'n' = 3, 'w' = 10, 
'profit' = [5, 11, 13]
'weight' = [2, 4, 6]

Output: 27

Explanation:
We can fill the knapsack as:

1 item of weight 6 and 1 item of weight 4.
1 item of weight 6 and 2 items of weight 2.
2 items of weight 4 and 1 item of weight 2.
5 items of weight 2.

The maximum profit will be from case 3 = 11 + 11 + 5 = 27. Therefore maximum profit = 27.


Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
3 15
7 2 4
5 10 20


Expected Answer:
21


Output on console:
21


Explanation of Sample Input 1
The given knapsack capacity is 15. We can fill the knapsack as [1, 1, 1] giving us profit 21 and as [1,2] giving us profit 9. Thus maximum profit will be 21.


Sample Input 2
2 3
6 12
4 17


Expected Answer:
0


Output on console:
0


Explanation of Sample Input 2:
We can clearly see that no item has weight less than knapsack capacity. Therefore we can not fill knapsack with any item.


Expected Time Complexity:
Try to solve this in O(n*w).


Constraints
1 <= n <= 10^3
1 <= w <= 10^3
1 <= profit[i] , weight[i] <= 10^8

Time Limit: 1 sec
 */
public class _06_unbounded_knapsack {
    public static void main(String[] args) {
        int w[] = { 2, 4, 6 };
        int v[] = { 5, 11, 13 };
        int maxCapacity = 10;
        int nWeights = w.length;
        System.out.println(recursion(nWeights, w, v, maxCapacity, nWeights - 1));
        ;
    }

    // ---------------------recursive
    public static int recursion(int n, int w[], int v[], int max, int idx) {

        if (idx == 0)
            return (w[idx] <= max ? v[idx] * (max / w[idx]) : 0);// ----heart of problem, since we have infinite supply
                                                                 // we will try to choose max number of w[0] iff
                                                                 // w[0]<=max such it it fites in tha much max weight

        int x = (w[idx] <= max) ? (v[idx] + recursion(n, w, v, max - w[idx], idx)) : 0;
        int y = recursion(n, w, v, max, idx - 1);

        return Math.max(x, y);
    }

    // ---------------------memoize
    public static int memoize(int n, int w[], int v[], int max, int idx, int dp[][]) {

        if (idx == 0)
            return (w[idx] <= max ? v[idx] * (max / w[idx]) : 0);
        if (dp[idx][max] != -1)
            return dp[idx][max];

        int x = w[idx] <= max ? (v[idx] + memoize(n, w, v, max - w[idx], idx, dp)) : 0;
        int y = memoize(n, w, v, max, idx - 1, dp);

        dp[idx][max] = Math.max(x, y);
        return Math.max(x, y);
    }



    //----------------tabulation
    public static int tabulation(int w[],int v[],int max){
        int n=v.length;
        int dp[][]=new int[n][max+1];
        
        //------VIMP--dont mistake here
        for(int k=0;k<=max;k++){
            if(w[0]<=k){
                dp[0][k]=v[0]*(k/w[0]);
            }
        }

        for(int i=1;i<n;i++){
            for(int currWeight=0;currWeight<=max;currWeight++){
                int x=(w[i]<=currWeight)?(v[i]+dp[i][currWeight-w[i]]):0;
                int y=dp[i-1][currWeight];

                dp[i][currWeight]=Math.max(x, y);
            }
        }

        return dp[n-1][max];

    }
}
