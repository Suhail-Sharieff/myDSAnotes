package _10_Sliding_Window._02_dynamic_window;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

 

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
 

Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.
 

Follow up: Could you find an algorithm that runs in O(m + n) time?
 */
public class _07_min_window_substr {


    //-------------brute
    public String brute_force(String s, String t) {//O(n^2 (m+n))
        int len1=s.length(),len2=t.length();
        if(len2>len1) return "";
        int min_len=Integer.MAX_VALUE;
        String ans="";
        for(int i=0;i<len1;i++){
            for(int j=i;j<len1;j++){
                if(isValid(s.substring(i,j+1),t) && j-i+1<min_len){
                    ans=s.substring(i,j+1);
                    min_len=j-i+1;
                    break;
                }
            }
        }
        return ans;
    }

    public boolean isValid(String s1,String s2){
        HashMap<Character,Integer>hs=new HashMap<>();
        for(char c:s1.toCharArray()) hs.put(c,hs.getOrDefault(c,0)+1);
        int count[]=new int[200];//requiered coz they said it duplicates also needs to b considered
        for(char c:s2.toCharArray()) count[c]++;
        for(char c:s2.toCharArray()) if(!hs.containsKey(c) || hs.get(c)<count[c]) return false;
        return true;
    }




    //-----------better:
    static String better(String s,String t){

        HashMap<Character,Integer>hs=get_hs(t);
        Queue<Integer>q=get_indecies(s, hs);


        int len=s.length();
        int min_len=Integer.MAX_VALUE;
        String ans="";

        while (!q.isEmpty()) {
            int start_idx=q.poll();
            int i=start_idx,j=start_idx;
            while (j<len) {
                if (hs.containsKey(s.charAt(j))) {
                    hs.put(s.charAt(j), hs.getOrDefault(s.charAt(j), 0)-1);
                    if(hs.get(s.charAt(j))==0) {
                        hs.remove(s.charAt(j));
                    }
                }
                if (hs.isEmpty()) {
                    if (j-i+1<min_len) {
                        min_len=j-i+1;
                        ans=s.substring(i, j+1);
                    }
                    break;
                }
                j++;
            }
            if (j==len) {
                break;
            }
            hs=get_hs(t);
        }
        return(ans);
    }

    static HashMap<Character,Integer>get_hs(String t){
        HashMap<Character,Integer>hs=new HashMap<>();
        for(char ch:t.toCharArray())
            hs.put(ch, hs.getOrDefault(ch,0)+1);
        return hs;
    }
    static Queue<Integer> get_indecies(String s,HashMap<Character,Integer>hs){
        int len=s.length();
        Queue<Integer>indecies=new LinkedList<>();
        for(int i=0;i<len;i++){
            if(hs.containsKey(s.charAt(i))) indecies.offer(i);
        }
        return indecies;
    }





    //---------------------------optimal
    //https://www.youtube.com/watch?v=WJaij9ffOIY&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=283&t=2s&ab_channel=takeUforward
    static String optimal(String s,String t){
        int len1=s.length(),len2=t.length();
        if(len2>len1) return "";
        int start_idx_of_ans=-1;
        int min_len=Integer.MAX_VALUE;
        int map[]=new int[128];
        for(char c:t.toCharArray()) map[c]++;
        int nChars_obtained=0;
        int left_ptr=0,right_ptr=0;
        while (right_ptr<len1) {
            map[s.charAt(right_ptr)]--;
            if(map[s.charAt(right_ptr)]>=0) nChars_obtained++;

            while (nChars_obtained==len2) {
                if(right_ptr-left_ptr+1<min_len){
                    min_len=right_ptr-left_ptr+1;
                    start_idx_of_ans=left_ptr;
                }
                map[s.charAt(left_ptr)]++;
                if(map[s.charAt(left_ptr)]>0) nChars_obtained--;
                left_ptr++;
            }

            right_ptr++;
        }       
        return min_len == Integer.MAX_VALUE ? "":s.substring(start_idx_of_ans, start_idx_of_ans+min_len);
    }
    

    public static void main(String[] args) {
        String s="ADOBECODEBANC";
        String t="ABC";
        System.out.println(optimal(s, t));;  
    }
}
