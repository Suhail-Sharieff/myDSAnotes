package _7_Graph._02_Dir_Unweigh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;

/*
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 

Constraints:

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
 */
public class _05_word_ladder {
    
    public static void main(String[] args) {
        String beginWord="hit";
        String endWord="cog";
        List<String>wordList=Arrays.asList("hot","dot","dog","lot","log","cog");

        HashSet<String>set=new HashSet<>(wordList);

        int charTocheck=0;

        Queue<pair>q=new LinkedList<>();
for (String string : set) {
    q.offer(new pair(string, 0));
}

        int ans=0;

        outer:while (!q.isEmpty()) {
            pair top=q.poll();
            ans=top.val;
            String ts=top.s;

            for(char c='a';c<='z';c++){
                String curr=ts.replace(ts.charAt(charTocheck), c);
                if (curr.equals(endWord)) {
                    ans=top.val+1;
                    break outer;
                }
                if(set.contains(curr)){
                    q.offer(new pair(curr, top.val+1));
                    set.remove(curr);
                    charTocheck++;
                }
            }
            System.out.print(q+"->");
        }

        System.out.println(ans);

    }
}
 class pair {
    @Override
    public java.lang.String toString() {
        return "["+s+":"+val+"]";
    }
    String s;
    int val;
    public pair(String s,int val){
        this.s=s;
        this.val=val;
    }
}