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
}