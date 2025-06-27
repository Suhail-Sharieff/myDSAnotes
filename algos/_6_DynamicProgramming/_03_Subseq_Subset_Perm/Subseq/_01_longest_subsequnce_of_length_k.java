package _6_DynamicProgramming._03_Subseq_Subset_Perm.Subseq;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//teaches bfs on strings
/*
 * 2014. Longest Subsequence Repeated k Times
Solved
Hard
Topics
premium lock icon
Companies
Hint
You are given a string s of length n, and an integer k. You are tasked to find the longest subsequence repeated k times in string s.

A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.

A subsequence seq is repeated k times in the string s if seq * k is a subsequence of s, where seq * k represents a string constructed by concatenating seq k times.

For example, "bba" is repeated 2 times in the string "bababcba", because the string "bbabba", constructed by concatenating "bba" 2 times, is a subsequence of the string "bababcba".
Return the longest subsequence repeated k times in string s. If multiple such subsequences are found, return the lexicographically largest one. If there is no such subsequence, return an empty string.

 

Example 1:

example 1
Input: s = "letsleetcode", k = 2
Output: "let"
Explanation: There are two longest subsequences repeated 2 times: "let" and "ete".
"let" is the lexicographically largest one.
Example 2:

Input: s = "bb", k = 2
Output: "b"
Explanation: The longest subsequence repeated 2 times is "b".
Example 3:

Input: s = "ab", k = 2
Output: ""
Explanation: There is no subsequence repeated 2 times. Empty string is returned.
 

Constraints:

n == s.length
2 <= n, k <= 2000
2 <= n < k * 8
s consists of lowercase English letters.
 */
public class _01_longest_subsequnce_of_length_k {
    public static void main(String[] args) {

        String orig = "letsleetcode";
        int k = 2;
        ans = new String("");
        // rec(orig, 0, new StringBuilder(), k);
        
        System.out.println(longestSubsequenceRepeatedK(orig, k));

    }

    static String ans;

    // ----------brute force, just generate all possible subsequnces and check if
    // each subsequce repeats k times in original string
    static void rec(String orig, int i, StringBuilder sb, int k) {
        if (i == orig.length()) {
            if (contains_k_such_subsequences(orig, sb.toString(), k)) {
                if (ans.length() < sb.length() && ans.compareTo(sb.toString()) < 0) {//we need lexicographiccaly largest
                    ans = sb.toString();
                }
            }
            return;
        }
        rec(orig, i + 1, new StringBuilder(sb).append(orig.charAt(i)), k);
        rec(orig, i + 1, new StringBuilder(sb), k);
    }

    // ----optimal, we know that if i need a subsequence k times, each char in it should also be k times in original string, then we can smarly avoid those chars which r apprearing less than k times in original string, coz taking them would be useless, do smarly do a bfs using Queue building 1 char at a time
    static String longestSubsequenceRepeatedK(String s, int k) {
        Queue<String> q = new LinkedList<>();
        int f[] = new int[26];
        for (char c : s.toCharArray())
            f[c - 'a']++;
        List<String> possibles = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            if (f[c - 'a'] >= k) {
                q.offer(Character.toString(c));
                possibles.add(Character.toString(c));
            }
        }
        ans = new String("");
        while (!q.isEmpty()) {
            String top = q.poll();
            if (top.length() >= ans.length())//we need lexicographiccaly largest
                ans = top;
            for (var e : possibles) {
                if (contains_k_such_subsequences(s, top + e, k))
                    q.offer(top + e);
            }
        }
        return ans;
    }

    static boolean contains_k_such_subsequences(String orig, String pattern, int k) {
        int len1 = orig.length();
        int len2 = pattern.length();
        int i = 0, j = 0;
        int cnt = 0;
        while (i < len1 && j < len2) {
            if (orig.charAt(i) != pattern.charAt(j)) {
                i++;
                continue;
            }
            i++;
            j++;
            if (j == len2) {
                j = 0;
                cnt++;
                if (cnt == k)
                    return true;
            }
        }
        return false;
    }

}
