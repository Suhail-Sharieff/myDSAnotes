package _2_Arrays;
//https://codeforces.com/problemset/problem/1921/D



/*D. Very Different Array
time limit per test2 seconds
memory limit per test256 megabytes
Petya has an array ai
 of n
 integers. His brother Vasya became envious and decided to make his own array of n
 integers.

To do this, he found m
 integers bi
 (m≥n
), and now he wants to choose some n
 integers of them and arrange them in a certain order to obtain an array ci
 of length n
.

To avoid being similar to his brother, Vasya wants to make his array as different as possible from Petya's array. Specifically, he wants the total difference D=∑ni=1|ai−ci|
 to be as large as possible.

Help Vasya find the maximum difference D
 he can obtain.

Input
Each test consists of multiple test cases. The first line contains a single integer t
 (1≤t≤100
) — the number of test cases. This is followed by a description of the test cases.

The first line of each test case contains two integers n
 and m
 (1≤n≤m≤2⋅105
).

The second line of each test case contains n
 integers ai
 (1≤ai≤109
). The third line of each test case contains m
 integers bi
 (1≤bi≤109
).

It is guaranteed that in a test, the sum of m
 over all test cases does not exceed 2⋅105
.

Output
For each test case, output a single integer — the maximum total difference D
 that can be obtained.

Example
InputCopy
9
4 6
6 1 2 4
3 5 1 7 2 3
3 4
1 1 1
1 1 1 1
5 5
1 2 3 4 5
1 2 3 4 5
2 6
5 8
8 7 5 8 2 10
2 2
4 1
9 6
4 6
8 10 6 4
3 10 6 1 8 9
3 5
6 5 2
1 7 9 7 2
5 5
9 10 6 3 7
5 9 2 3 9
1 6
3
2 7 10 1 1 5
OutputCopy
16
0
12
11
10
23
15
25
7
Note
In the first example, Vasya can, for example, create the array (1,5,7,2)
. Then the total difference will be D=|6−1|+|1−5|+|2−7|+|4−2|=5+4+5+2=16
.

In the second example, all the integers available to Vasya are equal to 1, so he can only create the array (1,1,1)
, for which the difference D=0
.

In the third example, Vasya can, for example, create the array (5,4,3,2,1)
. Then the total difference will be D=|1−5|+|2−4|+|3−3|+|4−2|+|5−1|=4+2+0+2+4=12
.


 */

//بِسْمِ ٱللَّهِ ٱلرَّحْمَـٰنِ ٱلرَّحِيمِ
import java.io.*;
import java.util.*;
public class _38_very_different_array{
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
        int n=scanInt();
        int m=scanInt();
        int arr[]=scanIntArray(n);
        int store[]=scanIntArray(m);

        Arrays.sort(arr);
        Arrays.sort(store);

        // dp=new int[m+1];
        // Arrays.fill(dp, -1);


        // int ans=f(0,m-1, arr, store);

        long ans=0;

        int i1=0,j1=n-1,i2=0,j2=m-1;


        while (i1<=j1) {
            int op1=Math.abs(arr[i1]-store[j2]);
            int op2=Math.abs(arr[j1]-store[i2]);
            if(op1>=op2){
              i1++;j2--;
            }else{
              j1--;i2++;
            }
            ans+=max(op1,op2);
        }


        println(ans);


    }

    static int dp[];

    static int f(int i,int j,int arr[],int store[]){
      if(i>=arr.length) return 0;
      if(j<0) return Integer.MIN_VALUE;
      // if(dp[i]!=-1) return dp[i];
      int take=Math.abs(arr[i]-store[j])+f(i+1, j-1, arr, store);
      int dont=f(i, j-1, arr, store);
      return dp[i]=max(take,dont);
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
