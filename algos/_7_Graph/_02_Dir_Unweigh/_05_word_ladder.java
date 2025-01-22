package _7_Graph._02_Dir_Unweigh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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

How?
 
hit:, we can choose any words in sets { [ait,bit...zit] ie changingFirstChar, [hat,hbt.....hzt] ie changingSecondChar, [hia,hib.....hiz] ie changingThirdChar }, we find that we get {hit(from changing first char), hot(by changingSecondChar), noWordByChangingThirdChar}, whcih r also in word list , now again we repeat this procedure for each of the words we got just now ie for "hot" ......and so on, we get that shortest path to transform to cog is 5


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

    // solution:
    /*
     * Observe in explaination that we traverse by changing each char of each word,
     * and check if any such is present in wordsList, if yes, we can put in queue to
     * check it later, ie we r doing BFS, but since we need to find shortest way, we
     * put in queue a pair(commonStringThatWeGot,level), as soon as we find any
     * sequence that is equal to endWord, we will return that level as answer.
     */

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        // know_one_such_short_sequence(beginWord, endWord, wordList);
        follow_up_brute(beginWord, endWord, wordList);

    }

    public static int bruteForce(String beginWord, String endWord, List<String> wordList) {// TC:
                                                                                           // sizeOfWordList*(beginWordLength*26)-----O(wordListLength)
        int ans = 0;
        Queue<Pair> q = new LinkedList<>();
        HashSet<String> set = new HashSet<>(wordList);
        q.offer(new Pair(beginWord, 1));//start from 1
        while (!q.isEmpty()) {
            Pair front = q.poll();
            if (front.s.equals(endWord)) {
                ans = front.val;
                break;
            }
            String curr = front.s;
            int v = front.val;
            List<String> trans = getTransformationsOf(curr, set);

            for (String t : trans) {
                q.offer(new Pair(t, v + 1));
                set.remove(t);
            }

        }

        return ans;
    }

    // --------------------know one such shortest sequence:
    public static List<String> know_one_such_short_sequence(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        set.remove(beginWord);
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(beginWord, 1));

        List<String> li = new ArrayList<>();

        while (!q.isEmpty()) {
            Pair top = q.poll();
            String curr = top.s;
            int val = top.val;

            if (curr.equals(endWord)) {
                break;
            }

            for (int idx = 0; idx < curr.length(); idx++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char[] arr = curr.toCharArray();
                    arr[idx] = c;
                    String temp = new String(arr);
                    if (set.contains(temp)) {
                        set.remove(temp);
                        q.offer(new Pair(temp, val + 1));
                        li.add(temp);// add to list
                    }
                }
            }
        }
        return (li);
    }

    ///------------------------------------------------------------FOLLOW UP(VVIMP)-------VERY HARD:
    /*
     * A transformation sequence from word beginWord to word endWord using a
     * dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk
     * such that:
     * 
     * Every adjacent pair of words differs by a single letter.
     * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to
     * be in wordList.
     * sk == endWord
     * Given two words, beginWord and endWord, and a dictionary wordList, return all
     * the shortest transformation sequences from beginWord to endWord, or an empty
     * list if no such sequence exists. Each sequence should be returned as a list
     * of the words [beginWord, s1, s2, ..., sk].
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: beginWord = "hit", endWord = "cog", wordList =
     * ["hot","dot","dog","lot","log","cog"]
     * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
     * Explanation: There are 2 shortest transformation sequences:
     * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
     * "hit" -> "hot" -> "lot" -> "log" -> "cog"
     * Example 2:
     * 
     * Input: beginWord = "hit", endWord = "cog", wordList =
     * ["hot","dot","dog","lot","log"]
     * Output: []
     * Explanation: The endWord "cog" is not in wordList, therefore there is no
     * valid transformation sequence.
     * 
     * 
     * Constraints:
     * 
     * 1 <= beginWord.length <= 5
     * endWord.length == beginWord.length
     * 1 <= wordList.length <= 500
     * wordList[i].length == beginWord.length
     * beginWord, endWord, and wordList[i] consist of lowercase English letters.
     * beginWord != endWord
     * All the words in wordList are unique.
     * The sum of all shortest transformation sequences does not exceed 105.
     */

    // watch
    // logic:https://www.youtube.com/watch?v=DREutrv2XD0&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=30&ab_channel=takeUforward

    public static List<List<String>> follow_up_brute(String beginWord, String endWord, List<String> wordList) {

        HashSet<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) {
            return new ArrayList<>();
        }

        List<List<String>> ans = new ArrayList<>();
        Queue<List<String>> q = new LinkedList<>();
        q.offer(new ArrayList<>(List.of(beginWord)));

        boolean foundShortest = false; // Flag to track if shortest paths are found

        while (!q.isEmpty() && !foundShortest) {

            int size = q.size();
            HashSet<String> visited = new HashSet<>();

            for (int i = 0; i < size; i++) {// just like level order traversal of tree

                List<String> front = q.poll();
                String last_word = front.get(front.size() - 1);

                List<String> possible_transformations = getTransformationsOf(last_word, set);

                for (String transform : possible_transformations) {

                    List<String> copy = new ArrayList<>(front);
                    copy.add(transform);

                    if (transform.equals(endWord)) {
                        ans.add(copy);
                        foundShortest = true; // Stop processing further levels----iMP
                    } else {
                        visited.add(transform);
                        q.offer(copy);
                    }
                }
            }

            // Remove visited words after processing the level
            set.removeAll(visited);
        }
        System.out.println(ans);
        return (ans);
    }

    private static List<String> getTransformationsOf(String currrWord, HashSet<String> wordList) {
        List<String>li=new ArrayList<>();
        for(int i=0;i<currrWord.length();i++){
            for(char c='a';c<='z';c++){
                char arr[]=currrWord.toCharArray();
                arr[i]=c;
                String s=new String(arr);
                if(wordList.contains(s)) li.add(s);
            }
        }
        return li;
    }
}

class Pair {
    String s;
    int val;

    public Pair(String s, int val) {
        this.s = s;
        this.val = val;
    }
}