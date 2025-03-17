package _12_Greedy;



/*
Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 

Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "(*)"
Output: true
Example 3:

Input: s = "(*))"
Output: true
 

Constraints:

1 <= s.length <= 100
s[i] is '(', ')' or '*'.
 */

public class _08_valid_parenthesis {
    public static void main(String[] args) {
        
    }

    //https://www.youtube.com/watch?v=cHT6sG_hUZI&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=294&t=156s&ab_channel=takeUforward
    //-------------recursion..3^n
    public boolean rec(String s,int i,int cnt,int len){
        if(cnt<0) return false;
        if(i==len) return (cnt==0);
        if(s.charAt(i)=='(') return rec(s,i+1,cnt+1,len);
        if(s.charAt(i)==')') return rec(s,i+1,cnt-1,len);
        return rec(s,i+1,cnt,len)||rec(s,i+1,cnt+1,len)||rec(s,i+1,cnt-1,len);// for a '*'
    }
    public boolean tab(String s,int len){
        boolean[][] dp = new boolean[len + 1][len + 1];
        dp[len][0] = true; // Base case: an empty string is valid when cnt == 0

        for (int i = len - 1; i >= 0; i--) { // Iterate backwards
            for (int cnt = 0; cnt <= i; cnt++) { // cnt should be within [0, i]
                char ch = s.charAt(i);
                if (ch == '(') {
                    dp[i][cnt] = (cnt + 1 <= len && dp[i + 1][cnt + 1]);
                } else if (ch == ')') {
                    dp[i][cnt] = (cnt - 1 >= 0 && dp[i + 1][cnt - 1]);
                } else { // When '*'
                    dp[i][cnt] = dp[i + 1][cnt] || 
                                 (cnt + 1 <= len && dp[i + 1][cnt + 1]) || 
                                 (cnt - 1 >= 0 && dp[i + 1][cnt - 1]);
                }
            }
        }
        return dp[0][0];
    }


    //----------------optimal
    public boolean checkValidString(String s) {//see vid for soln
        int min=0,max=0;
        int len=s.length();
        for(int i=0;i<len;i++){
            char c=s.charAt(i);
            if(c=='('){
                min++;
                max++;
            }else if(c==')'){
                min--;
                max--;
            }else if(c=='*'){
                min--;
                max++;
            }
            if(min<0) min=0;
            if(max<0) return false;
        }
        return min==0;
    }
}
