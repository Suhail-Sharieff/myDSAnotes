package _6_DynamicProgramming._01_1D;

import java.util.Arrays;

/*
Given a fence with n posts and k colours, find out the number of ways of painting the fence so that not more than two consecutive posts have the same colours.
Answers are guaranteed to be fit into a 32 bit integer.

Examples:

Input: n = 3, k = 2 
Output: 6
Explanation: Let the 2 colours be 'R' and 'B'. We have following possible combinations:
1. RRB
2. RBR
3. RBB
4. BRR
5. BRB
6. BBR
Input: n = 2, k = 4 
Output: 16
Explanation: After coloring first post with 4 possible combinations, you can still color next posts with all 4 colors. Total possible combinations will be 4x4=16
Constraints:
1 ≤ n ≤ 300
1 ≤ k ≤ 105


 */
public class _09_painting_fence {

    //IMP Watch:https://www.youtube.com/watch?v=XFXBnuS6DPU&ab_channel=GeeksforGeeksPractice


    public static void main(String[] args) {
        int nPosts=3;
        int nColorsAvailable=2;
        int dp[]=new int[nPosts+3];//coz we have dp[2] in func
        Arrays.fill(dp, -1);
        int ans=memoize(nPosts, nColorsAvailable, dp);
        System.out.println(ans);

    }
    

    //to avoid integer overflow:
    public static int mul(int x,int y){
        int MOD=1_000_000_007;
        return ((x%MOD)*(y%MOD))%MOD;
    }
    public static int add(int x,int y){
        int MOD=1_000_000_007;
        return ((x%MOD)+(y%MOD));
    }


    //suppose u have only 1 fence and given k colors, then u have k ways to paint that one fence
    //now suppose u have 2 fences and given say k colors, u will have k^2 ways to paint those, for ex u have 2 colors, say RG u can have possible ways:[RR,RG,GG,GR], ie 2^2 ie 4 ways ie k^2 ways

    //if chosen same color for consecutuve => (k-1)*(f(n-2)) {ie i & i-1 have same color, left with k-1 chices for remaining each of 0 to i-2 fences}, if chosen different for consecutive =>(k-1)*(f(n-1)) {ie i & i-1 have differenct colors, left with k-1 choices for remaining 0 to i except ith's color }

    //-----------------------recursion

    public static int recursion(int nPostsRemaining,int nColorsAvailable){
        if(nPostsRemaining==1) return nColorsAvailable;
        if(nPostsRemaining==2) return (nColorsAvailable*nColorsAvailable);

        //chose to color i&i-1th as same,k-1 choices for each of remaining [0,i-2] posts
        int way1=mul(recursion(nPostsRemaining-2, nColorsAvailable), (nColorsAvailable-1));
        //chose to color i & i-1 as differenct colors, now k-1 options, for each of reaminng [0,i-1] posts
        int way2=mul(recursion(nPostsRemaining-1, nColorsAvailable), (nColorsAvailable-1));

        return way1+way2;
    }


    //-----------------------memoize

    public static int memoize(int nPostsRemaining,int nColorsAvailable,int dp[]){
        dp[1]=nColorsAvailable;
        dp[2]=mul(nColorsAvailable, nColorsAvailable);//to avoid int overflow
        if(dp[nPostsRemaining]!=-1) return dp[nPostsRemaining];
        
        int way1=mul(memoize(nPostsRemaining-2, nColorsAvailable, dp), (nColorsAvailable-1));
        int way2=mul(memoize(nPostsRemaining-1, nColorsAvailable, dp), (nColorsAvailable-1));
        dp[nPostsRemaining]=add(way1, way2);

        return dp[nPostsRemaining];
    }


    //-------------------------tabulate

    public static int tabulate(int nPostsRemaining,int nColorsAvailable){
        int dp[]=new int[nPostsRemaining+3];
        dp[1]=nColorsAvailable;
        dp[2]=mul(nColorsAvailable, nColorsAvailable);
        //dp[i] represents the total number of ways to paint till ith post in both ways
        for (int chosenPost = 3; chosenPost <= nPostsRemaining; chosenPost++) {//indexing all colors
            int way1=mul(dp[chosenPost-2], (nColorsAvailable-1));
            int way2=mul(dp[chosenPost-1], (nColorsAvailable-1));
            dp[chosenPost]=add(way1, way2);
        }
        return dp[nPostsRemaining];
    }


}
