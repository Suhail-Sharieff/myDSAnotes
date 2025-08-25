package _6_DynamicProgramming._02_Grids._3D;

import java.util.Arrays;
import java.util.HashMap;

/*3144. Minimum Substring Partition of Equal Character Frequency
Solved
Medium
Topics
premium lock icon
Companies
Hint
Given a string s, you need to partition it into one or more balanced substrings. For example, if s == "ababcc" then ("abab", "c", "c"), ("ab", "abc", "c"), and ("ababcc") are all valid partitions, but ("a", "bab", "cc"), ("aba", "bc", "c"), and ("ab", "abcc") are not. The unbalanced substrings are bolded.

Return the minimum number of substrings that you can partition s into.

Note: A balanced string is a string where each character in the string occurs the same number of times.

 

Example 1:

Input: s = "fabccddg"

Output: 3

Explanation:

We can partition the string s into 3 substrings in one of the following ways: ("fab, "ccdd", "g"), or ("fabc", "cd", "dg").

Example 2:

Input: s = "abababaccddb"

Output: 2

Explanation:

We can partition the string s into 2 substrings like so: ("abab", "abaccddb").

 

Constraints:

1 <= s.length <= 1000
s consists only of English lowercase letters.
 
Seen this question in a real interview before?
1/5
Yes
No
Accepted
16,573/42.2K
Acceptance Rate
39.3%
Topics
icon
Companies
Hint 1 */
public class _04_google_oa {

    //--------------optimal, nothing but, we tied to optimize isBalanced [i,j] from O(n^3) to O(n^2) using prefix sum
    public int minimumSubstringsInPartition(String s) {
        // int ans=f(s,0,"",0);
        // return ans+1;
        int n=s.length();
        int dp[]=new int[n+1];
        boolean yea[][]=new boolean[n][n];
        for(var row:yea) Arrays.fill(row,true);
        int pref[][]=new int[n][26];
        pref[0][s.charAt(0)-'a']++;
        for(int i=1;i<n;i++){
            pref[i]=pref[i-1].clone();
            pref[i][s.charAt(i)-'a']++;
        }
        
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int prev=-1;
                for(char c='a';c<='z';c++){
                   int cnt=pref[j][c-'a']-((i>0)?pref[i-1][c-'a']:0);
                   if(cnt==0) continue;
                   if(prev==-1) prev=cnt;
                   else if(prev!=cnt){
                       yea[i][j]=false;
                       break;
                   }
                }
            }
        }
        for(int i=n-1;i>=0;i--){
            if(yea[i][n-1]) dp[i]=0;
            else{
                int min=inf;
                for(int k=i;k<n;k++){
                    if(yea[i][k]){
                        min=min(min,1+dp[k+1]);
                    }
                }
                dp[i]=min;
            }
        }
        return dp[0]+1;
    }

    //---------------bruet
    int inf=Integer.MAX_VALUE;
    int min(int...x){return Arrays.stream(x).min().getAsInt();}
    int f(String s,int i,String t,int n){
        if(i>=s.length()){
            return 0;
        }
        if(isB(s,i,s.length()-1)){
            // System.out.println(t+s.substring(i,j+1)+" takes "+n);
            return 0;
        }
        int min=inf;
        for(int k=i;k<s.length();k++){
            if(isB(s,i,k)){
                min=min(min,1+f(s,k+1,t+s.substring(i,k+1)+"$",n+1));
                // System.out.println(s.substring(i,k));
            }
        }
        return min;
    }
    HashMap<Character,Integer>hs=new HashMap<>();
    boolean isB(String s,int i,int j){
        if(s.substring(i,j+1).length()==0) return true;
        hs.clear();
        for(char c:s.substring(i,j+1).toCharArray()) hs.put(c,hs.getOrDefault(c,0)+1);
        int f=hs.get(s.substring(i,j+1).charAt(0));
        for(int e:hs.values()) if(e!=f) return false;
        // System.out.println(s.substring(i,j+1)+"->"+hs);
        return true;
    }
}
