package _6_DynamicProgramming._06_Partition_DP;

import java.util.*;

/*
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

 

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]
 

Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.

 */
public class _07_palindrome_partitioning {

    public static void main(String[] args) {
        String s = "abbab";
        List<List<String>> ans = new ArrayList<>();
        rec1(s, 0, new ArrayList<>(), ans);
        System.out.println(ans);

    }


    static int min(int...x){return Arrays.stream(x).min().getAsInt();}
    static int f(String s,int i,int j){
        if(i>=j || isPalin(s,i,j)) return 0;
        int min=Integer.MAX_VALUE;
        for(int  k=i;k<=j;k++){
            if(isPalin(s,i,k)){
                min=min(min,1+f(s,k+1,j));
            }
        }
        return min;
    }
    
    

    static void rec1(String s, int i, List<String> li, List<List<String>> ans) {
        if (i == s.length()) {
            ans.add(new ArrayList<>(li));
            return;
        }
        for (int k = i; k < s.length(); k++) {
            String sub = s.substring(i, k + 1);
            if (isPalin(sub)) {
                li.add(sub);
                rec1(s, k + 1, li, ans);
                li.remove(li.size() - 1);
            }
        }
    }

    static boolean isPalin(String s) {
        int left = 0, right = s.length() - 1;
    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }
    return true;

    }
    static boolean isPalin(String s,int i,int j) {
        int left = i, right = j;
    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }
    return true;

    }

    // -------------------FOLLOW UP:
    /*
     * Hard
     * Topics
     * Companies
     * Given a string s, partition s such that every substring of the partition is a
     * palindrome.
     * 
     * Return the minimum cuts needed for a palindrome partitioning of s.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: s = "aab"
     * Output: 1
     * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1
     * cut.
     * Example 2:
     * 
     * Input: s = "a"
     * Output: 0
     * Example 3:
     * 
     * Input: s = "ab"
     * Output: 1
     * 
     * 
     * Constraints:
     * 
     * 1 <= s.length <= 2000
     * s consists of lowercase English letters only.
     */


     //---------------call like rec2(s,0)-1;----------------------------- -1 IMP coz our prgm alos does partiton at end
    public int rec2(String s, int i) {
        if (i == s.length())
            return 0;
        int ans = Integer.MAX_VALUE;
        for (int k = i; k < s.length(); k++) {
            String sub = s.substring(i, k + 1);
            if (isPalin(sub)) {
                ans = Math.min(ans, 1 + rec2(s, k + 1));
            }
        }
        return ans;
    }

    public int mem2(String s, int i, int dp[]) {
        if (i == s.length())
            return 0;
        if (dp[i] != -1)
            return dp[i];
        int ans = Integer.MAX_VALUE;
        for (int k = i; k < s.length(); k++) {
            String sub = s.substring(i, k + 1);
            if (isPalin(sub)) {
                ans = Math.min(ans, 1 + mem2(s, k + 1, dp));
            }
        }
        dp[i] = ans;
        return ans;
    }

    public int tab2(String s){
        int len=s.length();
       if(len==1) return 0;
        int dp[]=new int[len+1];
        for(int i=len-1;i>=0;i--){
            int ans=Integer.MAX_VALUE;
            for(int k=i;k<len;k++){
                String sub = s.substring(i, k + 1);
                if (isPalin(sub)) {
                    ans=Math.min(ans, 1+dp[k+1]);
                }
            }
            dp[i]=ans;
        }
        return dp[0]-1;
    }
}
