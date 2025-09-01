//بِسْمِ ٱللَّهِ ٱلرَّحْمَـٰنِ ٱلرَّحِيمِ
package _14_Tries;


//soln:https://codeforces.com/blog/entry/123404



/*Maximum XOR subarray
Difficulty: HardAccuracy: 11.03%Submissions: 33K+Points: 8
Given an array arr[] of size, N. Find the subarray with maximum XOR. A subarray is a contiguous part of the array.


Example 1:

Input:
N = 4
arr[] = {1,2,3,4}
Output: 7
Explanation: 
The subarray {3,4} has maximum xor 
value equal to 7.

Example 2:

Input:
N = 3
arr[] = {1,4,3}
Output: 7
Explanation: 
 There are 6 possible subarrays:
 subarray            XOR value
 [1]                     1
 [4]                     4
 [3]                     3
 [1, 4]                  5 (1^4)
 [4, 3]                  7 (4^3)
 [1, 4, 3]               6 (1^4^3)

 [4, 3] subarray has maximum XOR value. So, return 7.
Your Task:  
You don't need to read input or print anything. Your task is to complete the function maxSubarrayXOR() which takes the array arr[], its size N as input parameters and returns the maximum xor of any subarray.
 

Expected Time Complexity: O(N)
Expected Auxiliary Space: O(N)

 

Constraints:
1 <= N <= 105
1 <= arr[i] <= 105

 */
import java.io.*;
import java.util.*;
public class _02_maximum_subarray_xor{
    public static void main(String[] args) throws IOException {
        init_IO();
        int t=scanInt();
        while(t-->0)
        solve();
        reader.close();
        writer.flush();
        writer.close();
    }

    static void solve() throws IOException{
        int arr[]=scanIntArray(scanInt());
        Trie t=new Trie();
        t.insert(0);
        int pref_xor=0;
        int ans=0;
        for(int i=0;i<arr.length;i++){
            pref_xor^=arr[i];
            t.insert(pref_xor);
            ans=max(ans,t.getMaxXor(pref_xor));
        }
        println("max subarray xor is "+ans);
    }
     static class TrieNode{
        int val;
        TrieNode ptr[];
        public TrieNode(){
            val=0;
            ptr=new TrieNode[2];
            ptr[0]=ptr[1]=null;
        }
    }

    static class Trie{
        TrieNode root;
        public Trie(){this.root=new TrieNode();}

        void insert(int pref_xor){
            TrieNode temp=root;
            for(int i=31;i>=0;i--){
                int bit=((pref_xor)&(1<<i))!=0?1:0;
                if(temp.ptr[bit]==null) temp.ptr[bit]=new TrieNode();
                temp=temp.ptr[bit];
            }
            temp.val=pref_xor;
        }

        int getMaxXor(int pref_xor){
            TrieNode temp=root;
            for(int i=31;i>=0;i--){
                int bit=((pref_xor)&(1<<i))!=0?1:0;
                if(temp.ptr[bit^0^1]!=null) temp=temp.ptr[bit^0^1];
                else if(temp.ptr[bit]!=null) temp=temp.ptr[bit];
            }
            return pref_xor^temp.val;
        }

    }

    static boolean debug=false;
    static int MOD=1_000_000_007;
    static int INF=Integer.MAX_VALUE;
    static StringTokenizer tokenizer = new StringTokenizer("");
    static BufferedReader reader;
    static BufferedWriter writer;
    static void init_IO() throws IOException{if(debug){reader = new BufferedReader(new FileReader("input.txt"));writer = new BufferedWriter(new FileWriter("output.txt"));}else{reader=new BufferedReader(new InputStreamReader(System.in));writer=new BufferedWriter(new OutputStreamWriter(System.out));}}
    static String next() throws IOException{while(!tokenizer.hasMoreTokens())tokenizer= new StringTokenizer(reader.readLine());return tokenizer.nextToken();}
    static int scanInt() throws IOException{return Integer.parseInt(next());}
    static long scanLong() throws IOException{return Long.parseLong(next());}
    static char scanChar() throws IOException{return next().charAt(0);}
    static String scanString() throws IOException{return reader.readLine();}
    static int[] scanIntArray(int len) throws IOException{int arr[]=new int[len];for(int i=0;i<len;i++)arr[i]=scanInt();return arr;}
    static long[] scanLongArray(int len) throws IOException{long arr[]=new long[len];for(int i=0;i<len;i++)arr[i]=scanLong();return arr;}
    static void print(Object o) throws IOException{writer.write(o.toString()+" ");}
    static void println(Object o) throws IOException{writer.write(o.toString()+"\n");}
    static int min(int...x){return Arrays.stream(x).min().getAsInt();}
    static int max(int...x){return Arrays.stream(x).max().getAsInt();}
    static int gcd(int a,int b){return (a==0)?b:gcd(b, a%b);}
    static int lcm(int a,int b){return a/gcd(a, b)*b;}
}
