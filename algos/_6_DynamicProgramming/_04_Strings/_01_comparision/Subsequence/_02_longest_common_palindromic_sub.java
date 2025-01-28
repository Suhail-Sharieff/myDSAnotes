package _6_DynamicProgramming._04_Strings._01_comparision.Subsequence;

public class _02_longest_common_palindromic_sub {

    public static void main(String[] args) {
        
    }
    public int rec(String s,int i,int j){
        if(i>j) return 0;
        if(i==j) return 1;//VVIMP, coz if u dont write this, suppose u have word="a", then ans is 1 ryt, it would return 2

        if(s.charAt(i)==s.charAt(j)) return rec(s,i+1,j-1)+2;//+2 coz 1 first and other last caracter
        return Math.max(rec(s,i+1,j),rec(s,i,j-1));
    }

    public int mem(String s,int i, int j,int dp[][]){
        if(i>j) return 0;
        if(i==j) return 1;
        if(dp[i][j]!=-1) return dp[i][j];
        if(s.charAt(i)==s.charAt(j)){
            dp[i][j]=2+mem(s,i+1,j-1,dp);
        }
        else{
            dp[i][j]= Math.max(mem(s,i+1,j,dp),mem(s,i,j-1,dp));
        }
        return dp[i][j];
    }

    public int tab1(String s){
         int[][] dp = new int[s.length()][s.length()];
        
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;//IMP
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;//+2 IMP
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }



    //smart tabulation: apply lcs algo on s and reverse of ss
    //here pass lke: tab2(s,reverse(s))----IMP
    public int tab2(String s1, String s2) {

        int dp[][]=new int[s1.length()+1][s2.length()+1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j]= 1+ dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }





    ///////////////////////FOLOOW UP:
    /*
    Given a string s. In one step you can insert any character at any index of the string.

Return the minimum number of steps to make s palindrome.

A Palindrome String is one that reads the same backward as well as forward.

 

Example 1:

Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we do not need any insertions.
Example 2:

Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".
Example 3:

Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
     */
    //the below is ans coz if we calculate lcs for plaindromic string, we will get max possible value of string which is palindrome, so to make it 100% palindrome we just need to subtract length and that paindrome lcs. General idea is we try to keep palindromic portion (longest) asit is, and replace others, we we need (len-longest_ppalindromic_subseq) operations to make it complete palindrome
     public int minOprToMakeStrPalindrome(String s) {
        return s.length()-tab1(s);
    }


    //---------------------FOLOOW UP:
    /*
     * 
     Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.

 

Example 1:

Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Example 2:

Input: word1 = "leetcode", word2 = "etco"
Output: 4
 

Constraints:

1 <= word1.length, word2.length <= 500
word1 and word2 consist of only lowercase English letters.
     */

    public int minOprToMakeStrsEqaul(String x,String y){
        int dp[][]=new int[x.length()+1][y.length()+1];
        for(int i=1;i<=x.length();i++){
            for(int j=1;j<=y.length();j++){
                if(x.charAt(i-1)==y.charAt(j-1)){
                    dp[i][j]=1+dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        int lcs= dp[x.length()][y.length()];

        int ans=(x.length()-lcs)+(y.length()-lcs);

        return ans;
    }
}