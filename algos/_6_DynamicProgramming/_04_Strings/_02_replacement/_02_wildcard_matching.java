package _6_DynamicProgramming._04_Strings._02_replacement;

import java.util.Arrays;

/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

 

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 

Constraints:

0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters, '?' or '*'.
 */
public class _02_wildcard_matching {
    public static void main(String[] args) {
        String x="adceb";
        String y="*a*b";
        System.out.println(x.matches(y));
    }
    //main idea: we can easily deal if chars match or y[j]='?', but main is to handle '*'. If we get j pointing at '*', we have 2 options, either we consider it as empty, ie neglect and  move further it ie [i,j-1] or consider it as the ith char and move next ie[i-1,j]:
    //must watch: https://www.youtube.com/watch?v=ZmlQ3vgAOMo&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=35&ab_channel=takeUforward
    //------------------------recursive
    public boolean rec(String x,String y,int i,int j){
        if(i<0){
            for(char c:y.substring(0,j+1).toCharArray()) if(c!='*') return false;
            return true;
        }
        if(j<0) return false;
        if(x.charAt(i)==y.charAt(j) || y.charAt(j)=='?') return rec(x,y,i-1,j-1);
        if(y.charAt(j)=='*') return rec(x,y,i,j-1)||rec(x,y,i-1,j);
        return false;
    }
    //--------------------------memoized
    public boolean mem(String x,String y,int i,int j,Boolean dp[][]){
        if(i==0){
            for(char c:y.substring(0,j).toCharArray()) if(c!='*') return false;
            return true;
        }
        if(j==0) return false;
        if(dp[i][j]!=null) return dp[i][j];
        if(x.charAt(i-1)==y.charAt(j-1) || y.charAt(j-1)=='?') dp[i][j]= mem(x,y,i-1,j-1,dp);
        else if (y.charAt(j-1)=='*') dp[i][j]= mem(x,y,i,j-1,dp)||mem(x,y,i-1,j,dp);
        else dp[i][j]=false;
        return dp[i][j];
    }
    //--------------------------tbaulate:
     public boolean tab(String x, String y) {
        int len1=x.length(),len2=y.length();
        boolean dp[][]=new boolean[x.length()+1][y.length()+1];
        Arrays.fill(dp[0],true);
        for(int j=0;j<=len2;j++){
            for(char c:y.substring(0,j).toCharArray()) if(c!='*') dp[0][j]=false;
        }

        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                dp[i][0]=false;
                if(x.charAt(i-1)==y.charAt(j-1) || y.charAt(j-1)=='?') dp[i][j]=dp[i-1][j-1];
                else if(y.charAt(j-1)=='*') dp[i][j]=dp[i-1][j]||dp[i][j-1];
                else dp[i][j]=false;
            }
        }
        return dp[len1][len2];
    }
    //-------------space optimal:
    public boolean space_optimal(String x,String y){
        int len1=x.length(),len2=y.length();
        boolean prev[]=new boolean[len2+1];
        Arrays.fill(prev, true);
        for(int j=0;j<=len2;j++){
            for(char c:y.substring(0,j).toCharArray()) if(c!='*') prev[j]=false;
        }
        for(int i=1;i<=len1;i++){
            boolean curr[]=new boolean[len2+1];
            for(int j=1;j<=len2;j++){
                curr[0]=false;
                if(x.charAt(i-1)==y.charAt(j-1) || y.charAt(j-1)=='?') curr[j]=prev[j-1];
                else if(y.charAt(j-1)=='*') curr[j]=prev[j]||curr[j-1];
                else curr[j]=false;
            }
            prev=curr.clone();
        }
        return prev[len2];
    }
}
