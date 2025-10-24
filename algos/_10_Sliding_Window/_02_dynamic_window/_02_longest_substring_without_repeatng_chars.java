package _10_Sliding_Window._02_dynamic_window;

import java.util.HashMap;

/*

THIS Q IS ASKED IN MANY INTERVIEWS



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
        System.out.println(optimal(s));
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
    static int optimal(String s){
        HashMap<Character,Integer>hs=new HashMap<>();//hs will store [char,its_prev_seen_idx]
        int left_ptr=0,right_ptr=0,ans=0;
        int len=s.length();
        while (right_ptr<len) {
           if(hs.containsKey(s.charAt(right_ptr))){
                left_ptr=Math.max(left_ptr, hs.get(s.charAt(right_ptr))+1);//// Ensure left_ptr moves forward but never backward, so we take max, analyze using "abba"
           }
           hs.put(s.charAt(right_ptr), right_ptr);
           ans = Math.max(ans, right_ptr - left_ptr + 1);
            right_ptr++;

        }
        return ans;
    }
}
