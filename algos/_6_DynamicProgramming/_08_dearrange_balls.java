package _6_DynamicProgramming;
//pre-requisites:
//watch:https://www.youtube.com/watch?v=pbXg5EI5t4c&ab_channel=Numberphile 

import java.util.Arrays;

//also:https://www.youtube.com/watch?v=qYAWjIVY7Zw&ab_channel=Numberphile2


//now watch:https://www.youtube.com/watch?v=XeAHpQ8AtvM&ab_channel=ANNAACADEMYKota




//problem:
/*
You are given n balls numbered from 1 to n and there are n baskets numbered from 1 to n in front of you. The ith basket is meant for the ith ball. Calculate the number of ways in which no ball goes into its respective basket.

Note: The answer will always fit into a 32-bit integer.

Examples:

Input: n = 2
Output: 1
Explanation: For two balls {1, 2}, there is only one possible derangement {2, 1}.
Input: n = 3
Output: 2
Explanation: For three balls {1, 2, 3}, there are two possible derangements {3, 1, 2} and {2, 3, 1}.
Constraints:
1 ≤ n ≤ 12
 */



public class _08_dearrange_balls {

    //using math: NOTE: this will give approx anwers only and not exact ans:
    //Formula: No. of dearrangements for n didgit permutation is given by (n!)/e derived in that above videos u can see


    //watch those videos especially last one,u will get proofs of the below eqns:
    //Dearrangements: the ith position(1 based) must not be equal to i in permutation from 1 to n

    //D(n) be no of dearrangements among all subsets of permutation from [1 to n]

    //D(n)=(n-1)[D(n-2)+D(n-1)]

    //D(n)=(n!)[(1/2!)-(1/3!)+(1/4!)...((-1)^n)/n!] = (n!)/e


    //how D(n)=(n-1)[D(n-2)+D(n-1)]?
    //now watch:https://www.youtube.com/watch?v=NW-BLDQHFXk&list=PLDzeHZWIZsTomOPnCiU3J95WufjE36wsb&index=7&ab_channel=CodeHelp-byBabbar

    static int recursion(int n) 
    { 
       if(n==1) return 0;
       if(n==2) return 1;// like we have 12, only way is 21
       int ifSwapped=(n-1)*(recursion(n-2));
       int notSwapped=(n-1)*(recursion(n-1));
       return (ifSwapped+notSwapped);
    } 
    
    static int memoize(int n,int dp[]){
        //call like:int dp[]=new int[n+1];
    //   Arrays.fill(dp,-1);
    //   return memoize(n,dp);
        
        if(n==1) return 0;
        if(n==2) return 1;
        dp[1]=0;
        dp[2]=1;
        if(dp[n]!=-1) return dp[n];
        int ifSwapped=(n-1)*(memoize(n-2,dp));
        int ifNotSwapped=(n-1)*(memoize(n-1,dp));
        dp[n]=ifSwapped+ifNotSwapped;
        return dp[n];
    }
    
    static long tabulate(int n){
        if(n==1) return 0;
        if(n==2) return 1;
        long dp[]=new long[n+1];
        Arrays.fill(dp,-1);
        dp[1]=0;
        dp[2]=1;
		int MOD=1_000_000_007;
        for(int i=3;i<=n;i++){
            int ifSwapped = (int)(((long)(i - 1) * dp[i - 2]) % MOD);
            int ifNotSwapped = (int)(((long)(i - 1) * dp[i - 1]) % MOD); 
            dp[i] = (ifSwapped + ifNotSwapped) % MOD;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n=100;
        System.out.println(tabulate(n));
    }


}
