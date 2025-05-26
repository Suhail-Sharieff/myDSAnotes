package _14_Tries;
/*
 * Problem statement
You are given an array ‘ARR’ consisting of ‘N’ strings. Your task is to find the longest common prefix among all these strings. If there is no common prefix, you have to return an empty string.

A prefix of a string can be defined as a substring obtained after removing some or all characters from the end of the string.

For Example:
Consider ARR = [“coding”, ”codezen”, ”codingninja”, ”coders”]
The longest common prefix among all the given strings is “cod” as it is present as a prefix in all strings. Hence, the answer is “cod”.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 10
1 <= N <= 3000
1 <= |ARR[i]| <=1000

Each string consists of only lowercase letters.

Time limit: 1 sec
Sample Input 1:
2
4
coding codezen codingninja coder
3
night ninja nil 
Sample Output 1:
cod
ni
Explanation of sample input 1:
For the first test case, 
The longest common prefix among all the given strings is “cod” as it is present as a prefix in all strings. Hence, the answer is “cod”.

For the second test case,
The longest common prefix among all the given strings is “ni” as it is present as a prefix in all strings. Hence, the answer is “ni”.
Sample Input 2:
2
3
applejuice applepie apple
4
car cus cart carat
Sample Output 2:
apple
c
 */


 //read q properly,see test case i have written in main method to understand
public class _01_longest_common_prefix {
    static class TrieNode {
        TrieNode ptrs[];
        int prefCount;

        public TrieNode() {
            this.prefCount = 0;
            this.ptrs = new TrieNode[26];
        }
    }

    static class Trie {
        TrieNode root;
        int n;

        public Trie(int n) {
            this.root = new TrieNode();
            this.n = n;
        }

        public void insert(String s) {
            int i = 0;
            int len = s.length();
            TrieNode curr = root;
            while (curr != null && i < len) {
                int idx = s.charAt(i) - 'a';
                if (curr.ptrs[idx] == null)
                    curr.ptrs[idx] = new TrieNode();
                curr = curr.ptrs[idx];
                curr.prefCount++;
                i++;
            }
        }

        public int get_first_place(TrieNode root) {
            int ans = -1;
            for (int i = 0; i < 26; i++) {
                if (root.ptrs[i] != null) {
                    if (root.ptrs[i].prefCount == n) {
                        ans = i;
                    }
                }
            }
            return ans;
        }

        public String prnt_longest() {
            int idx = get_first_place(this.root);
            if (idx == -1)
                return "";
            TrieNode curr = root.ptrs[idx];
            StringBuilder ans = new StringBuilder();
            while (curr != null && curr.prefCount == n) {
                ans.append((char) (idx + 'a'));
                idx = get_first_place(curr);
                if (idx == -1)
                    break;
                curr = curr.ptrs[idx];
            }
            return ans.toString();
        }
    }

    public static String longestCommonPrefix(String[] arr, int n) {
        Trie t = new Trie(n);
        for (String e : arr)
            t.insert(e);
        return (t.prnt_longest());
    }

    public static void main(String[] args) {
        String arr[] = { "kimrekacsf", "kimreeybfg", "kimrettrwa", "timrewwmnr", "kimretjxjl", "kimrejecjw" };//exprected:"", coz of "timrewwmnr"
        Trie t = new Trie(arr.length);
        System.out.println(t.prnt_longest());
    }
}
