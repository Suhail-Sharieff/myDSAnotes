package _13_Tries;

import java.util.HashMap;
/*
208. Implement Trie (Prefix Tree)
Solved
Medium
Topics
Companies
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
1) void insert(String word) Inserts the string word into the trie.
2) boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
3) boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.

 */
public class _impl_01 {

    public static void main(String[] args) {
        


        Trie trie=new Trie();
        trie.print_dfs();

        trie.insert("hellow");
        trie.insert("help");
        // trie.insert("helping");
        trie.print_dfs();


        System.out.println(trie.contains_word("help"));
       

    }


    static class TrieNode{
        char value;
        boolean isEndOfWord;
        TrieNode ptrs[];
        public TrieNode(char value){
            this.value=value;
            ptrs=new TrieNode[26];
            this.isEndOfWord=false;
        }
    }
    
    static class Trie{
        TrieNode root;
        public Trie(){
            this.root=new TrieNode('#');
        }
        
        public void insert(String s) {
            int i=0;
            TrieNode curr=root;
            while (curr!=null && i<s.length()) {
                TrieNode itsPtrs[]=curr.ptrs;
                int idx=s.charAt(i)-'a';
                if(itsPtrs[idx]!=null){
                    curr=itsPtrs[idx];
                }else{
                    TrieNode newNode=new TrieNode(s.charAt(i));
                    itsPtrs[idx]=newNode;
                    curr=itsPtrs[idx];
                }
                i++;
            }
            curr.isEndOfWord=true;//curr would obviously be pointing to last word
            
        }
        
        public boolean contains_word(String word) {
            int i=0;
            TrieNode curr=root;
            while (i<word.length() && curr!=null) {
                TrieNode itsPtrs[]=curr.ptrs;
                int idx=word.charAt(i)-'a';
                if (itsPtrs[idx]!=null) {
                    curr=itsPtrs[idx];
                    i++;
                    continue;
                }else{
                    return false;
                }
            }
            return  curr.isEndOfWord;
        }
        
        public boolean contains_prefix(String pref) {
            int i=0;
            TrieNode curr=root;
            while (i<pref.length() && curr!=null) {
                TrieNode itsPtrs[]=curr.ptrs;
                int idx=pref.charAt(i)-'a';
                if (itsPtrs[idx]!=null) {
                   curr=itsPtrs[idx];
                   i++;
                   continue;
                }else{
                    return false;
                }
            }
            return true;
        }
        public void print_dfs(){
            HashMap<TrieNode,Boolean >isVis=new HashMap<>();
            dfs(root, isVis);
            System.out.println();
        }
    
        public void dfs(TrieNode root,HashMap<TrieNode,Boolean>isVis){
            isVis.put(root, true);
            System.out.print(root.value+" ");
            for(int i=0;i<root.ptrs.length;i++){
                if(root.ptrs[i]!=null){
                    if(isVis.get(root.ptrs[i])==null) dfs(root.ptrs[i], isVis);
                }
            }
        }
    
    
    }

}
