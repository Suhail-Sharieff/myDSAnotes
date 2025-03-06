package _10_Sliding_Window._02_dynamic_window;
/*
Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.

 

Example 1:

Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 
Example 2:

Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb". 
Example 3:

Input: s = "abc"
Output: 1
 

Constraints:

3 <= s.length <= 5 x 10^4
s only consists of a, b or c characters.
 */
public class _05_number_of_substr_containing_all_3_chars {


    //--------------brute force
    public int numberOfSubstrings(String s) {
        int len=s.length(),c=0;
        for(int i=0;i<len;i++){
            for(int j=i;j<len;j++){
                if(isValid(s.substring(i,j+1))) c++;
            }
        }
        return c;
    }

    public boolean isValid(String s) {
        int a=0,b=0,c=0;
        for (char ch : s.toCharArray()) {
            if (ch == 'a') a++; 
            else if(ch=='b') b++;
            else c++;
            if(a>=1 && b>=1 && c>=1) return true;
        }
        return false;
    }



    //----------------better
    public int better(String s) {
        int cnt=0,len=s.length();
        for(int i=0;i<len;i++){
            int a=0,b=0,c=0;
            for(int j=i;j<len;j++){
                char ch=s.charAt(j);
                if(ch=='a') a++;
                if(ch=='b') b++;
                if(ch=='c') c++;
                if(a>=1 && b>=1 && c>=1){
                     cnt+=len-j;// we can take one char forward at a time till len from j, so its len-j
                     break;
                }
            }
        }
        return cnt;
    }



    //--------------optimal:https://www.youtube.com/watch?v=xtqN4qlgr8s&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=278&ab_channel=takeUforward

    //logic: try having a window of least size such that it has 1 char each of a b and c,then if u get min_idx of any char in that window, bviously, chars bfr that we can form substring with each of chars, so we can move smartly
    public static int optimal(String s){
        int len=s.length();
        int last_idx_of_a=-1;
        int last_idx_of_b=-1;
        int last_idx_of_c=-1;
        int ans=0;
        for(int i=0;i<len;i++){
            char ch=s.charAt(i);
            if(ch=='a') last_idx_of_a=i;
            else if(ch=='b') last_idx_of_b=i;
            else if(ch=='c') last_idx_of_c=i;
            if(last_idx_of_a!=-1 && last_idx_of_b!=-1 && last_idx_of_c!=-1){
                int min_idx=min(last_idx_of_a, last_idx_of_b, last_idx_of_c);
                ans+=min_idx+1;
            }
        }
        return ans;
    }
    static int min(int x,int y,int z){
        return Math.min(x,Math.min(y, z));
    }
}