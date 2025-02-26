package _10_Sliding_Window._02_dynamic_window;

import java.util.HashMap;

/*
Given a string s, find the length of the longest substring without duplicate characters.

 

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 

Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
 */
public class _02_longest_substring_without_repeatng_chars {
    public static void main(String[] args) {
        String s="dvdf";
        System.out.println(brute_force(s));
    }

    //-------------------O(N^2)---O(N)
    static int brute_force(String s) {
        if(s.length()==0) return 0;

        int len=s.length(),ans=1;
        for(int i=0;i<len;i++){
            HashMap<Character,Boolean> isSeen=new HashMap<>();
            isSeen.put(s.charAt(i),true);
            int j=i+1;
            int cnt=1;
            while(j<len){
                char ch=s.charAt(j);
                if(isSeen.get(ch)==null){
                    cnt++;
                    isSeen.put(ch,true);
                    ans=Math.max(ans,cnt);
                }else{
                    break;
                }
                j++;
            } 
            isSeen.clear();
        }
        return ans;
    }

    //---------optimal:
    public void optimal(String s){
        HashMap<Character,Integer>hs=new HashMap<>();
        int i=0,j=1;
        int len=s.length(),ans=0;
        while (j!=len) {
            char c1=s.charAt(i),c2=s.charAt(j);
            hs.put(c1, hs.getOrDefault(c1, 0)+1);
            hs.put(c2, hs.getOrDefault(c2, 0)+1);
            if (c1!=c2) {
                j++;
                ans=Math.max(ans, j-i);
            }else{
                while (c1!=c2) {
                    i++;
                }
            }
        }
    }
}
