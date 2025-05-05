package _6_DynamicProgramming._01_1D;

//https://leetcode.com/problems/domino-and-tromino-tiling/description/?envType=daily-question&envId=2025-05-05

/* You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.


Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.

In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.

 

Example 1:


Input: n = 3
Output: 5
Explanation: The five different ways are show above.
Example 2:

Input: n = 1
Output: 1
 

Constraints:

1 <= n <= 1000*/
public class _10_domino_tromino_tilling {
    //https://leetcode.com/problems/domino-and-tromino-tiling/solutions/6716200/beats-super-easy-beginners-java-c-c-python-javascript-dart/?envType=daily-question&envId=2025-05-05
    //soln:https://www.youtube.com/watch?v=Iwmn-gFL3c0
    //mainly based on obs that using domino patter repaetas but for i>=4, tromino combos which r unique r just 2
    public int numTilings(int n) {
        int mod=1_000_000_007;
        // int x=1,y=2,z=5;
        //f(n)=2*f(n-1)+f(n-3)
        int dp[]=new int[n+3];
        dp[0]=1;
        dp[1]=2;
        dp[2]=5;
        for(int i=3;i<=n;i++){
            dp[i]=(2*dp[i-1]%mod+dp[i-3]%mod)%mod;
        }
        return dp[n-1]%mod;
    }
}
