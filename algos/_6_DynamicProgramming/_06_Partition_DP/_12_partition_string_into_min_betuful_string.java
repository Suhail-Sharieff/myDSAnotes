package _6_DynamicProgramming._06_Partition_DP;

import java.util.Arrays;


//this was asked in Goldman Sachs

//https://www.youtube.com/watch?v=07bO3TPHD1M&list=PLIp-xrYmLruIuBdyw-_wrRqsIEot3GDZf&index=15

/*2767. Partition String Into Minimum Beautiful Substrings
Solved
Medium
Topics
premium lock icon
Companies
Hint
Given a binary string s, partition the string into one or more substrings such that each substring is beautiful.

A string is beautiful if:

It doesn't contain leading zeros.
It's the binary representation of a number that is a power of 5.
Return the minimum number of substrings in such partition. If it is impossible to partition the string s into beautiful substrings, return -1.

A substring is a contiguous sequence of characters in a string.

 

Example 1:

Input: s = "1011"
Output: 2
Explanation: We can paritition the given string into ["101", "1"].
- The string "101" does not contain leading zeros and is the binary representation of integer 51 = 5.
- The string "1" does not contain leading zeros and is the binary representation of integer 50 = 1.
It can be shown that 2 is the minimum number of beautiful substrings that s can be partitioned into.
Example 2:

Input: s = "111"
Output: 3
Explanation: We can paritition the given string into ["1", "1", "1"].
- The string "1" does not contain leading zeros and is the binary representation of integer 50 = 1.
It can be shown that 3 is the minimum number of beautiful substrings that s can be partitioned into.
Example 3:

Input: s = "0"
Output: -1
Explanation: We can not partition the given string into beautiful substrings.
 

Constraints:

1 <= s.length <= 15
s[i] is either '0' or '1'.
 */
public class _12_partition_string_into_min_betuful_string {
    int dp[][]=new int[16][16];
    public int minimumBeautifulSubstrings(String s) {
        int n=s.length();
        for(int row[]:dp) Arrays.fill(row,inf);
        for(int i=n-1;i>=0;i--){
            for(int j=i;j<n;j++){
                if(valid(get_substring(s,i,j))){
                    dp[i][j]=0;
                    continue;
                }
                int ans=inf;
                for(int k=i;k<j;k++){
                    int left=dp[i][k];
                    int right=dp[k+1][j];
                    ans=Math.min(ans,1+left+right);
                }
                dp[i][j]=ans;
            }
        }
        if(dp[0][n-1]==inf) return -1;
        return dp[0][n-1]+1;
    }
    int inf=1000;
    int rec(String s,int i,int j){
        if(i>j) return inf;
        if(dp[i][j]!=-1) return dp[i][j];
        if(valid(get_substring(s,i,j))) return 0;
        int ans=inf;
        for(int k=i;k<j;k++){
            int left=rec(s,i,k);
            int right=rec(s,k+1,j);
            ans=Math.min(ans,1+left+right);
        }
        return dp[i][j]=ans;
    }

    boolean valid(String s){
        if(s.isEmpty()) return true;
        if(s.charAt(0)=='0') return false;
        return is_power_of_5(Integer.valueOf(s,2));
    }

    String get_substring(String s,int i,int j){
        return s.substring(i,j+1);
    }

    boolean is_power_of_5(int x){
        return 390625%x==0;
    }
}
