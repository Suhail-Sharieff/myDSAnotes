package _13_Tries;
/*
Implement Trie ll
Moderate
0/80
Average time to solve is 30m
Contributed by
242 upvotes
Asked in companies
Problem statement
Ninja has to implement a data structure ”TRIE” from scratch. Ninja has to complete some functions.

1) Trie(): Ninja has to initialize the object of this “TRIE” data structure.

2) insert(“WORD”): Ninja has to insert the string “WORD”  into this “TRIE” data structure.

3) countWordsEqualTo(“WORD”): Ninja has to return how many times this “WORD” is present in this “TRIE”.

4) countWordsStartingWith(“PREFIX”): Ninjas have to return how many words are there in this “TRIE” that have the string “PREFIX” as a prefix.

5) erase(“WORD”): Ninja has to delete one occurrence of the string “WORD” from the “TRIE”.
Note:

1. If erase(“WORD”) function is called then it is guaranteed that the “WORD” is present in the “TRIE”.

2. If you are going to use variables with dynamic memory allocation then you need to release the memory associated with them at the end of your solution.
Can you help Ninja implement the "TRIE" data structure?

Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= “T” <= 50
1 <= “N” <= 10000
 “WORD” = {a to z}
1 <= | “WORD” | <= 1000

Where “T” is the number of test cases, “N” denotes how many times the functions(as discussed above) we call, “WORD” denotes the string on which we have to perform all the operations as we discussed above, and | “WORD” | denotes the length of the string “WORD”.

Time limit: 1 sec.
Sample Input 1:
1
5
insert coding
insert ninja
countWordsEqualTo coding
countWordsStartingWith nin
erase coding
Sample Output 1:
1
1   
Explanation of sample input 1:
After insertion of “coding” in “TRIE”:

After insertion of “ninja” in “TRIE”:

Count words equal to “coding” :

Count words those prefix is “nin”:

After deletion of the word “coding”, “TRIE” is:

Sample Input 2:
1
6
insert samsung
insert samsung
insert vivo
erase vivo
countWordsEqualTo samsung
countWordsStartingWith vi
Sample Output 2:
2
0
Explanation for sample input 2:
insert “samsung”: we are going to insert the word “samsung” into the “TRIE”.

insert “samsung”: we are going to insert another “samsung” word into the “TRIE”.

insert “vivo”: we are going to insert the word “vivo” into the “TRIE”.

erase “vivo”: we are going to delete the word “vivo” from the “TRIE”.

countWordsEqualTo “samsung”: There are two instances of “sumsung” is present in “TRIE”.

countWordsStartingWith “vi”: There is not a single word in the “TRIE” that starts from the prefix “vi”.
 */


 class TrieNode {
     // 26 lowercase English letters
     TrieNode[] children = new TrieNode[26];
 
     // How many words pass through this node
     int prefixCount = 0;
 
     // How many times this exact node is the end of a word
     int wordCount = 0;
 }
 
 class Trie {
     private final TrieNode root;
 
     public Trie() {
         root = new TrieNode();
     }
 
     // Insert word into the trie
     public void insert(String word) {
         TrieNode curr = root;
         for (char c : word.toCharArray()) {
             int idx = c - 'a';
             if (curr.children[idx] == null) {
                 curr.children[idx] = new TrieNode();
             }
             curr = curr.children[idx];
             curr.prefixCount++;      // one more word passes through here
         }
         curr.wordCount++;            // word terminates here
     }
 
     // Count exact matches of word
     public int countWordsEqualTo(String word) {
         TrieNode curr = root;
         for (char c : word.toCharArray()) {
             int idx = c - 'a';
             if (curr.children[idx] == null) {
                 return 0;
             }
             curr = curr.children[idx];
         }
         return curr.wordCount;
     }
 
     // Count words that start with the given prefix
     public int countWordsStartingWith(String prefix) {
         TrieNode curr = root;
         for (char c : prefix.toCharArray()) {
             int idx = c - 'a';
             if (curr.children[idx] == null) {
                 return 0;
             }
             curr = curr.children[idx];
         }
         return curr.prefixCount;
     }
 
     // Erase one occurrence of word (guaranteed to exist)
     public void erase(String word) {
         if (countWordsEqualTo(word) == 0) {
             // Or throw IllegalArgumentException if you prefer
             return;
         }
 
         TrieNode curr = root;
         for (char c : word.toCharArray()) {
             int idx = c - 'a';
             TrieNode next = curr.children[idx];
             next.prefixCount--;       // one fewer word uses this prefix
 
             // Optional node pruning:
             if (next.prefixCount == 0) {
                 // No words pass through next anymore?unlink it and stop
                 curr.children[idx] = null;
                 return;
             }
             curr = next;
         }
         curr.wordCount--;             // one fewer exact match
         // Optionally: if (curr.wordCount == 0) { /* clear a marker, if you had one */ }
     }
 }
 
public class _impl_02 {
    
}
