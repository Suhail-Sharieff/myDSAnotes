
/*
 * 
402. Remove K Digits
Solved
Medium
Topics
Companies
Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

 

Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 

Constraints:

1 <= k <= num.length <= 105
num consists of only digits.
num does not have any leading zeros except for the zero itself.
 */

import java.util.Stack;

public class _10_remove_k_digits {
//intutuion:
//suppose u had "1432219", first i thought like lets remove all larger k digits to get 1221, but see that 1219 is ans, from there its intutive that as for every cahr in str, i we will start poping digits greter than curr,magically we will get ans


  public String removeKdigits(String num, int k) {//
        Stack<Character> st = new Stack<>();
        for (char c : num.toCharArray()) {
            while (!st.isEmpty() && st.peek() > c && k != 0) {//mistake: >=
                st.pop();
                k--;
            }
            st.push(c);
        }
        while(!st.isEmpty() && k--!=0) st.pop();//consider case like str='9' and k=1
        StringBuilder sb=new StringBuilder();
        for(char c:st) sb.append(c);
        String ans= sb.toString().replaceFirst("^0+", "");//IMP
        if(ans.isEmpty()) return "0";
        return ans;
    }   
}
