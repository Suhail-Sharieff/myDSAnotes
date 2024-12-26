package _6_DynamicProgramming;
/*
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

 

Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 
 */
public class _03_climbing_stairs {
    //we will arrive at the solution following the stes given in keys.md
    /*
- represent data (say array) in terms of index
- try all possible stuff as per problem statement
     */

     //lets say we want to go till n, we represent positions : 0,1,2......n
     //

     //trying all possible shits
     //if n==0 or n==1 thers only 1 way so return 1
     // if n is more 1 way is to jump like from n-1 & other is n-2
     // ie total ways is f(n-1)+f(n-2)
     //wollah its similar to nth fibonacci

     public static int nWays(int n,int dp[]){
        if (n<=1) {
            return n;
        }
        if (dp[n]!=0) {
            return dp[n];
        }
        dp[n]=nWays(n-1, dp)+nWays(n-2, dp);
        return dp[n];
     }
}
