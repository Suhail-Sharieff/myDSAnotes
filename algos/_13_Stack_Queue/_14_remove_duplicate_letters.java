/*316. Remove Duplicate Letters
Solved
Medium
Topics
premium lock icon
Companies
Hint
Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

 

Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc"
Output: "acdb"
 

Constraints:

1 <= s.length <= 104
s consists of lowercase English letters. */
import java.util.Stack;
public class _14_remove_duplicate_letters {

    public String removeDuplicateLetters(String s) {
        int f[] = new int[26];
        for (char c : s.toCharArray())
            f[c - 'a']++;
        StringBuilder ans = new StringBuilder();
        Stack<Character> st = new Stack<>();
        char arr[] = s.toCharArray();
        boolean isVis[] = new boolean[26];
        for (int i = 0; i < arr.length; i++) {
            char curr = arr[i];
            f[curr - 'a']--;
            if (isVis[curr - 'a'])//already wo low position par mil chuke the
                continue;
            while (!st.isEmpty() && curr < st.peek() && f[st.peek() - 'a'] > 0)
                isVis[st.pop() - 'a'] = false;
            st.push(curr);
            isVis[curr - 'a'] = true;
        }

        while (!st.isEmpty()) {
            ans.append(st.pop());
        }
        return ans.reverse().toString();
    }

}