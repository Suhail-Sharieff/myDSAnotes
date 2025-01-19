package _7_Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Given a sorted dictionary of an alien language having some words dict and k starting alphabets of a standard dictionary. Find the order of characters in the alien language. If no valid ordering of letters is possible, then return an empty string.

Note: Many orders may be possible for a particular test case, thus you may return any valid order and output will be "true" if the order of string returned by the function is correct else "false" denotes incorrect string returned.

Examples:

Input: dict[] = ["baa","abcd","abca","cab","cad"], k = 4
Output: true
Explanation: Here order of characters is 'b', 'd', 'a', 'c' Note that words are sorted and in the given language "baa" comes before "abcd", therefore 'b' is before 'a' in output.
Similarly we can find other orders.
Input: dict[] = ["caa","aaa","aab"], k = 3
Output: true
Explanation: Here order of characters is 'c', 'a', 'b' Note that words are sorted and in the given language "caa" comes before "aaa", therefore 'c' is before 'a' in output.
Similarly we can find other orders.
Input: dict[] = ["dhhid","dahi","cedg","fg","gdah","i","gbdei","hbgf","e","ddde"], k = 9 
Output: false
Constraints:
1 ≤ dict.size()≤ 104
1 ≤ k ≤ 26
1 ≤ Length of words ≤ 50
 */
public class _14_alien_dictionary {


    //solution, u just need to figre out which charatcer comes brfore which, liek if a comes before b, make a->b, ie create an edge FROM a to b, create a graph of all k characters, the answer will be topo sort of that one

/*
 * How shuld a human think to solve this without cmputer?
 
    Consider if words are given in sorted dictionary as:
    ba
    dac
    dad

    Start from top, see that "ba" is bfr "dac", means "b" comes bfr "d", so adj:[[b->d]]
    Now consider "dac" and "dad", observe first 2 chars in both r same, so compare with the char not equal ie "c" and "d", since "dac" is bfr "dad", "c" would come bfr "d" so now adj is [[b->d],[c->d]], now form a graph bbyy using this adj list,do topo sorts thats all
 */

    
    

    public static void main(String[] args) {
        String dict[]={
            "baa",
            "abcd",
            "abca",
            "cab",
            "cad"
        };
        int k=4;

        List<char[]>adj=getAdjListOfCharacters(dict);
        //each char[] in adj represents [fronNode,toNode]
        /*this looks like this
        [b, a] ->means b comes before a
        [d, a] ->means d comes before a
        [a, c] ->means a comes before c
        [b, d] ->means b comes before d
         */

        int in_degree[]=new int[k];//here we would store in_degree value for each caharcter, like in the form in_degree[a] would mean in_degree[0].. ie we would convert a->0, b->1, c->2....z->26, by doing 'char'-97  

        for(char e[]:adj) {
            int to=e[1]-97;
            in_degree[to]++;
        }

        Queue<Character>q=new LinkedList<>();

        for(char curr='a';curr<'a'+k;curr++){
            int to=curr-97;
           if(in_degree[to]==0) q.offer(curr);
        }

        StringBuilder ans=new StringBuilder();

        while (!q.isEmpty()) {
            char top=q.poll();
            ans.append(top);
            for(char[]pair:adj){
                int from=pair[0]-97;
                int to=pair[1]-97;
                if(from==(top-97)){
                    in_degree[to]--;
                    if(in_degree[to]==0) q.offer(pair[1]);
                }
            }
        }

        System.out.println(ans.toString().length()!=k?"INVALID DICTIONARY":ans.toString());
       
    }

    public static List<char[]> getAdjListOfCharacters(String dict[]){
        int lenOfDict=dict.length;

        List<char[]>adj=new ArrayList<>();

        for(int m=0;m<lenOfDict-1;m++){
            String currWord=dict[m];
            String nextWord=dict[m+1];

            int i=0,j=0;

            while (i<currWord.length() && j<nextWord.length()) {
                if(currWord.charAt(i)!=nextWord.charAt(j)){//means ith char in first comes bfr jth in nextword, so add adge first[i]->next[j]
                    adj.add(new char[]{currWord.charAt(i),nextWord.charAt(j)});
                    break;
                }
                i++;j++;
            }
        }
        return adj;
    }
    
}
