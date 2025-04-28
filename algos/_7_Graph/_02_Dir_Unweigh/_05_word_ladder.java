package _7_Graph._02_Dir_Unweigh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        // follow_up_brute(beginWord, endWord, wordList);

        optimalFunc(beginWord, endWord, wordList);


    }

    //-------------------recursion
    public int rec(String src, String dest, List<String> words, HashSet<String> hs) {
        if (src.equals(dest))
            return 0;
        if (!hs.contains(dest))
            return Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < src.length(); j++) {
            List<String> possibles = getWord(src, j, hs);
            for (String poss : possibles) {
                hs.remove(poss);
                int x = rec(poss, dest, words, hs);
                if (x != Integer.MAX_VALUE)
                    min = Math.min(min, 1 + x);
                hs.add(poss);
            }
        }
        return min;
    }
    public List<String> getWord(String curr, int i, HashSet<String> hs) {
        char arr[] = curr.toCharArray();
        List<String> ans = new ArrayList<>();
        char orig = arr[i];
        for (char ch = 'a'; ch <= 'z'; ch++) {
            arr[i] = ch;
            String temp = new String(arr);
            if (hs.contains(temp))
                ans.add(temp);
            arr[i] = orig;
        }
        return ans;
    }


    //------------better
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

    // --------------------optimal and also know one such shortest sequence:(uses BFS's beauty of reaching dest within shortest time)
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
    static class Brute {
        public List<List<String>> findLadders(String src, String dest, List<String> words) {
             HashSet<String> hs = new HashSet<>(words);
            ans = new ArrayList<>();
            List<String>temp=new ArrayList<>();
            temp.add(src);
            rec(src, dest, temp, hs);
    
            return(ans);
    
        }
    
        static List<List<String>> ans;
    
        static void rec(String src, String dest, List<String> li, HashSet<String> hs) {
            if (src.equals(dest)) {
                if (ans.isEmpty() || li.size() == ans.get(ans.size()-1).size()) {
                    ans.add(new ArrayList<>(li));
                } else if (li.size() < ans.get(ans.size()-1).size()) {
                    ans.clear();
                    ans.add(new ArrayList<>(li));
                }
                return;
            }
            for (int j = 0; j < src.length(); j++) {
                List<String> poss = getPossiblitites(src, j, hs);
                for (var p : poss) {
                    li.add(p);
                    hs.remove(p);
                    rec(p, dest, li, hs);
                    hs.add(p);
                    li.remove(li.size() - 1);
                }
            }
        }
    
        static List<String> getPossiblitites(String curr, int idx, HashSet<String> hs) {
            List<String> poss = new ArrayList<>();
            char arr[] = curr.toCharArray();
            char orig = arr[idx];
            for (char ch = 'a'; ch <= 'z'; ch++) {
                arr[idx] = ch;
                String k = new String(arr);
                if (hs.contains(k))
                    poss.add(k);
                arr[idx] = orig;
            }
            return poss;
        }


        //-------------iterative version of brute:
        public List<List<String>> iterative(String src, String dest, List<String> words) {
            ans = new ArrayList<>();
            List<String>temp=new ArrayList<>();
    
    
            Queue<Pair>q=new LinkedList<>();
    
            q.offer(new Pair(temp, src,new HashSet<>(words)));
    
            while (!q.isEmpty()) {
                Pair top=q.poll();
                if (top.curr.equals(dest)) {
                    top.li.add(dest);
                    if (ans.isEmpty() || top.li.size() == ans.get(ans.size()-1).size()) {
                        ans.add(new ArrayList<>(top.li));
                    } else if (top.li.size() < ans.get(ans.size()-1).size()) {
                        ans.clear();
                        ans.add(new ArrayList<>(top.li));
                    }
                    continue;
                }
                for(int j=0;j<top.curr.length();j++){
                    List<String>poss=getPossiblitites(top.curr, j, top.hs);
                    for(var p:poss){
                        List<String>newList=new ArrayList<>(top.li); newList.add(top.curr);
                        HashSet<String>newHashSet=new HashSet<>(top.hs); newHashSet.remove(p);
                        q.offer(new Pair(newList, p,newHashSet));
                    }
                }
            }
    
            return(ans);
        }
    }


    //---------------------OPTIMAL: like in finding min sequence to reach targget, we will Make a map<string,int>, where int is number of steps (shrtest) we took to reach that word, once done, we ill back track from the final word to top, instaed of cecking the entire wordList we check the words in hashMap only since they r convertable

    public static List<List<String>> optimalFunc(String beginWord,String endWord,List<String>wordList){
        if(!wordList.contains(endWord)) return new ArrayList<>();//IMP, othere wise hashmap will have null exception
        HashMap<String,Integer>hs=(word_minStepsToMakeIt(beginWord, endWord, wordList));
        List<String>curr=new ArrayList<>();curr.add(endWord);
        List<List<String>>ans=new ArrayList<>();
        dfs(hs, endWord, beginWord, curr,ans);
        System.out.println(ans);
        return ans;
    }

    public static HashMap<String,Integer>word_minStepsToMakeIt(String beginWord,String endWord,List<String>wordList){
        HashSet<String>set=new HashSet<>(wordList);
        Queue<Pair>q=new LinkedList<>();
        HashMap<String,Integer>hs=new HashMap<>();
        hs.put(beginWord, 1);
        q.offer(new Pair(beginWord, 1));
        while (!q.isEmpty()) {

            Pair front=q.poll();

            if(front.s.equals(endWord)) break;

            String curr=front.s;
            int val=front.val;
            List<String>transformations=getTransformationsOf(curr, set);
            for(String e:transformations){
                q.offer(new Pair(e, val+1));
                hs.putIfAbsent(e, val+1);//ONLY PUT IF ABSENT,OR ELSE IT MAY OVERIDE PRE EXISTING VAL AND GIVE WRONG ANS---IMP
                set.remove(e);
            }
        }
        return hs;
    }

    public static void dfs(HashMap<String,Integer>word_minStepsToReachIt,String currWord,String beginWord,List<String>currList,List<List<String>>ans){
        if(currWord.equals(beginWord)) {
            ans.add(new ArrayList<>(currList.reversed()));
            return;
        }
        for(int i=0;i<currWord.length();i++){
            for(char c='a';c<='z';c++){
                char arr[]=currWord.toCharArray();
                arr[i]=c;
                String newString=new String(arr);
                if (word_minStepsToReachIt.containsKey(newString) && word_minStepsToReachIt.get(newString)<word_minStepsToReachIt.get(currWord)) {
                    // System.out.println(currWord+" matches "+newString);
                    currList.add(newString);
                    dfs(word_minStepsToReachIt, newString, beginWord, currList,ans);
                    currList.remove(currList.size()-1);
                }
            }
        }
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