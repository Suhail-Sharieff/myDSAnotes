package _6_DynamicProgramming._04_Strings._01_comparision.Subsequence;

import java.util.Arrays;

/*
Given two strings s and t, return the number of distinct subsequences of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.

 

Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabb it
ra bbit
rab bit


Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag
 

Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.
 */
public class _04_distinct_subseq {

    // solution:
    /*
     * https://www.youtube.com/watch?v=nVG7eTiD2bY&list=
     * PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=33&ab_channel=takeUforward
     * How to think?(ALWAYS THINK BASE CASE AT LAST)
     * - we r searching for all occurances of s2[0..j] in s1[0...i]
     * - design a function that would return number of subsequences of s1[0..i] in
     * s2[0..j]
     * - if s1.charAt(i) matches s2.charAt(j) we can either movre left wards in both
     * s1 and s2 ie [i-1,j-1], or also, its possible that more subsequences of s2
     * for same char at j we may get in s1, so we can just move [i-1,j], return sum
     * of these possibilies
     * - but what if they dont match, obviosuly we will move left wards from both
     * strings from that point
     * - now searching searching we if we have travserse all chars of s2 in s1 ie
     * when [i<0] we found 1 such subseq, return 1, but but but iff [j<0], we were
     * not able to search even [0,0] of s2 in s1, return 0
                           /* For ex: x=babag, y=bag ans=3
                                    [babag, bag]
                                    /            \
                                [baba, ba]         [baba, bag]
                                /         \               \
                                [bab, b]   [bab, ba]     [bab, bag]
                                /   \           \               \
                              [ba,_] [ba,b]   [ba, ba]         [ba, bag]
                                       /       /  \              \
                                    [b,b]  [b,b] [b,ba]       [b, bag]
                                    /|       /       \           \
                               [_,_][_,b] [_,_]      [_,ba]      [_, bag] ---observe overlapping
*/

            
            
    public static void main(String[] args) {
        String x="rabbbit",y="rabbit";
        tab(x, y);
    }
                                                        
    static int rec(String x, String y, int i, int j) {
        // base case:VIMP: write j's consition before i's condition
        if (j < 0)
            return 1;// got all chars of y in x
        if (i < 0)
            return 0;// we reached end of x searching for y, didnt get any

        if (x.charAt(i) == y.charAt(j)) {
            int searchForMorePossibilitiesWithThatChar = rec(x, y, i - 1, j);
            int moveForFurtherChecking = rec(x, y, i - 1, j - 1);
            return searchForMorePossibilitiesWithThatChar + moveForFurtherChecking;
        }
        return rec(x, y, i - 1, j);// chars not matching mve left in ONLY IN first
    }
    static int mem(String x,String y,int i,int j,int dp[][]){
        if(j<0) return 1;
        if(i<0) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        if(x.charAt(i)==y.charAt(j)){
            dp[i][j]=mem(x, y, i-1, j, dp)+mem(x, y, i-1, j-1, dp);
        }else{
            dp[i][j]=mem(x, y, i-1, j, dp);
        }
        return dp[i][j];
    }

    //for deriving tabulation solution eailt, just convert mem soln above to 1 based indexing, i have marked changes, call like mem_to_derive_tab(x,y,s.length(),y.length()), no -1 for x. and y.length()
    static int mem_to_derive_tab(String x,String y,int i,int j,int dp[][]){
            if(j==0) return 1;//changed here
            if(i==0) return 0;//changed here
            if(dp[i][j]!=-1) return dp[i][j];
            if(x.charAt(i-1)==y.charAt(j-1)){//chenged here
                dp[i][j]=mem(x, y, i-1, j-1, dp)+mem(x, y, i-1, j, dp);
            }else{
                dp[i][j]=mem(x, y, i-1, j, dp);
            }
            return dp[i][j];
    }

    static int tab(String x,String y){
        int dp[][]=new int[x.length()+1][y.length()+1];
        for(int r[]:dp) r[0]=1;//coz if j==0, return 1
        for(int i=1;i<=x.length();i++){
            for(int j=1;j<=y.length();j++){
                if(x.charAt(i-1)==y.charAt(j-1)){
                    dp[i][j]=dp[i-1][j]+dp[i-1][j-1];
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        for(int []r:dp) System.out.println(Arrays.toString(r));
        return dp[x.length()][y.length()];
    }   


    //space optimization: why we can do it? observe that be the char match or not we always have [i-1], so we can conert the solution in terms of state of j, by storing dp[i-1] into single array
    public static int space_optimal(String x,String y){
        int m=x.length(),n=y.length();
        int dp[]=new int[n+1];
        dp[0]=1;
        for(int i=1;i<=m;i++){
            int prev[]=dp.clone();
            for(int j=1;j<=n;j++){
                if(x.charAt(i-1)==y.charAt(j-1)){
                    dp[j]=prev[j]+prev[j-1];
                }else{
                    dp[j]=prev[j];
                }
            }
        }
        return dp[n];
    }

    /* if u are allowed to change atmost k chars of first string:
    public class CountSubseqWithKChanges {
    // If counts may be huge and you want modulo, change USE_MOD = true and set MOD.
    static final boolean USE_MOD = false;
    static final long MOD = 1_000_000_007L;

    // count ways where for each chosen subsequence positions we may change up to K chars
    public static long count(String s, String t, int K) {
        int n = s.length(), m = t.length();
        if (m == 0) return 1;           // empty target
        if (n == 0) return 0;

        long[][] dp = new long[m + 1][K + 1];
        dp[0][0] = 1; // form empty prefix with 0 changes

        for (int i = 1; i <= n; i++) {
            char cs = s.charAt(i - 1);
            int upto = Math.min(i, m);
            // j descending to avoid using updated dp[j-1] from current i
            for (int j = upto; j >= 1; j--) {
                char ct = t.charAt(j - 1);
                // iterate c from 0..K (we read dp[j-1][c] and dp[j-1][c-1], which are from previous i)
                for (int c = 0; c <= K; c++) {
                    long add = 0;
                    if (cs == ct) {
                        add = dp[j - 1][c];           // match without change
                    } else if (c >= 1) {
                        add = dp[j - 1][c - 1];       // use one change here
                    }
                    if (add == 0) continue;
                    dp[j][c] += add;
                    if (USE_MOD) dp[j][c] %= MOD;
                }
            }
            // dp[0][*] stays (only dp[0][0] == 1, others 0) — skipping s[i-1] is automatic
        }

        long ans = 0;
        for (int c = 0; c <= K; c++) {
            ans += dp[m][c];
            if (USE_MOD) ans %= MOD;
        }
        return ans;
    }

    // quick test
    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";
        System.out.println(count(s, t, 0)); // usual answer: 3
        // allow 1 change: this will at least be >= 3 (may count subsequences that needed 1 mismatch)
        System.out.println(count(s, t, 1));
    }
}

    
    
    */

}
