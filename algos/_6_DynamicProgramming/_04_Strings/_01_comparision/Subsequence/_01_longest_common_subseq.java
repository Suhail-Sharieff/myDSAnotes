package _6_DynamicProgramming._04_Strings._01_comparision.Subsequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

/*
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

 

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
 

Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
 */
public class _01_longest_common_subseq {
    // -----------------------------brute force: generate subsequences ofboth and
    // compare each
    public int brute(String s1, String s2) {
        ArrayList<String> x = subsequences(s1);
        ArrayList<String> y = subsequences(s2);
        int cnt = 0;
        for (var e : y) {
            if (x.contains(e))
                cnt = Math.max(cnt, e.length());
        }
        return cnt;
    }

    public ArrayList<String> subsequences(String str) {
        // Write your code here
        ArrayList<String> ans = new ArrayList<>();
        func(str, new StringBuilder(), 0, ans);
        Collections.sort(ans);
        return ans;
    }

    public void func(String str, StringBuilder sb, int idx, ArrayList<String> ans) {
        if (idx == str.length()) {
            if (!sb.toString().isEmpty())
                ans.add(new String(sb));
            return;
        }
        sb.append(str.charAt(idx));
        func(str, sb, idx + 1, ans);
        sb.deleteCharAt(sb.length() - 1);
        func(str, sb, idx + 1, ans);
    }

    // ---------------recursion2
    // f(i,j) represents what is the max length of common subseq in s1[0..i] and
    // s2[0...j]
    // lets meaintain i and j pointers which would point initially to rightmost of
    // str1 and str2 respectively, if s1.charAt(i)==s2.charAt(j) we found 1 longest
    // substring,so check next, ie return 1+func(i-1,j-1), if they r not identical,
    // heres what important is, its possible that we may have common charatcters on
    // either left or s1 or left of s2, nut whom do we decrement i or j? the answer
    // is try both, ie first decrement i pointer , then try j pointer, return max of
    // both ways, exmaple it u have say s1=ce and s2=ec, e!=c, but e(at first of s2)
    // is as same as last of s1, so we try both ways

    public int recurion2(String s1, String s2, int i, int j) {
        if (i < 0 || j < 0)
            return 0;
        if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + recurion2(s1, s2, i - 1, j - 1);
        }else{
            return Math.max(
                    recurion2(s1, s2, i - 1, j),
                    recurion2(s1, s2, i, j - 1)
                    );
        }
        
    }

    // -------------memoize
    public int memo(String s1, String s2, int i, int j, int dp[][]) {
        if (i < 0 || j < 0)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            dp[i][j] = 1 + memo(s1, s2, i - 1, j - 1, dp);
        } else {//else keyword is VVIMP 
            dp[i][j] = Math.max(memo(s1, s2, i - 1, j, dp), memo(s1, s2, i, j - 1, dp));
        }

        return dp[i][j];
    }

    // -----------------tabulation:
    public int tab(String s1, String s2) {

        int dp[][]=new int[s1.length()+1][s2.length()+1];
        //dp[i][j] represents max number of charaters from str1[0..i) and str2[0...j)

        for (int i = 1; i <= s1.length(); i++) {//note eqaul sign
            for (int j = 1; j <= s2.length(); j++) {//note here too
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j]= 1+ dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }


    //----------------------------------FOLLOW UP: print all those LCS(Longest common subseq)----VIMP
    //https://www.youtube.com/watch?v=-zI4mrF2Pb4&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=27&ab_channel=takeUforward
    //since we r appending to sb by rtversing from end of each of s1 and s2, we need to reverse it thats all
    public static void lcs_subseq(String s1,String s2,Set<String>ans,int i, int j,int dp[][],StringBuilder sb){
        if(i==0 || j==0){//see the change
            ans.add(new String(sb.reverse()));
            // System.out.println(sb);
            sb.reverse();
            return;
        }
        if(s1.charAt(i-1)==s2.charAt(j-1)){
            lcs_subseq(s1, s2, ans, i-1, j-1, dp, new StringBuilder(sb).append(s1.charAt(i-1)));
        }else{
            if(i>=1 && dp[i-1][j]==dp[i][j]){
                lcs_subseq(s1, s2, ans, i-1, j, dp, sb);
            }
            if(j>=1 && dp[i][j-1]==dp[i][j]){
                lcs_subseq(s1, s2, ans, i, j-1, dp, sb);
            }
        }
    }

}




