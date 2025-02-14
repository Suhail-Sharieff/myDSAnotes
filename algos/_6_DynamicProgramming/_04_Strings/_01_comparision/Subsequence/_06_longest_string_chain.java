package _6_DynamicProgramming._04_Strings._01_comparision.Subsequence;

import java.util.Arrays;

/*



prereq: _05_longest_increasing_subseq(space_optimal_best func)


You are given an array of words where each word consists of lowercase English letters.

wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

Return the length of the longest possible word chain with words chosen from the given list of words.

 

Example 1:

Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
Example 2:

Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
Example 3:

Input: words = ["abcd","dbqca"]
Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
 

Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of lowercase English letters.
 */
public class _06_longest_string_chain {
     public int longestStrChain(String[] words) {
        Arrays.sort(words,(x,y)->x.length()-y.length());
        int dp[]=new int[words.length];
        for(int i=0;i<words.length;i++){
            dp[i]=1;
            for(int j=0;j<i;j++){
                boolean is_good_pair=is_good_pair(words[i],words[j]);
                //words[i] always has greater length than words[j], so check if all chars of words[j] r present in words[i] and words[j] has 1 moe length than words[j]
                if(is_good_pair){
                    dp[i]=Math.max(dp[i],1+dp[j]);
                }
            }
        }
        int max=1;
        for(int e:dp) max=Math.max(max,e);
        return max;
    }
    boolean is_good_pair(String x,String y){//checking if x contains y chars
        int len1=x.length(),len2=y.length();
        if(Math.abs(len1-len2)!=1) return false;
        int nCharsMatched=0;
        int i=0,j=0;
        while(i<len1 && j<len2){
            if(x.charAt(i)==y.charAt(j)){
                nCharsMatched++;
                i++;
                j++;
            }else{
                i++;
            }
        }
        return nCharsMatched==len2;
    }


    //for printitng use same logic as that of prev file
}
