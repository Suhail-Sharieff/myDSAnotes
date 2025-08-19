    /*Problem Statement

You are given N servers, initially all disconnected from each other.

You can use each server in one of two ways:

Microservice instance:
The cost is taken from array A, where A[i] is the cost of using the i-th server as a microservice.

Monolith instance:
Two or more adjacent servers can be grouped into a monolith. The cost of a monolith is the sum of the corresponding values in array B.

Your task is to determine the minimum cost to use all servers for implementing the client-server system.

Notes

A monolith architecture requires at least 2 adjacent servers.

A single server can only be used as a microservice.

Every server must be used exactly once, either as a microservice or as part of a monolith.

Input Format

The first line contains an integer T — the number of test cases.

For each test case:

The first line contains an integer N — the number of servers.

The second line contains N integers A[i] — cost of using each server as a microservice.

The third line contains N integers B[i] — cost of using each server in a monolith.

Output Format

For each test case, print a single integer — the minimum total cost to use all servers.

Constraints

1 ≤ T ≤ 10

1 ≤ N ≤ 10^5

1 ≤ A[i], B[i] ≤ 10^4

Example
Input
1
5
3 2 5 2 9
1 10 5 3 7

Output
12

Explanation

Use servers 1 and 2 as a monolith → cost = 1 + 1 = 2.

Use server 3 as a microservice → cost = 2.

Use servers 4 and 5 as a monolith → cost = 5 + 3 = 8.

Total = 2 + 2 + 8 = 12.

More Test Cases
Input
2
4
5 6 3 4
2 2 2 2
3
4 7 2
5 1 3

Output
9
8

Explanation

Test case 1:

Servers 1, 2 as monolith → cost = 2 + 2 = 4.

Servers 3, 4 as monolith → cost = 2 + 2 = 4.

Minimum = 8, but if we take 3 as microservice (3) and 4 as microservice (4), cost = 4 + 3 + 4 = 11.

Best choice: monolith(1,2) + microservice(3) + microservice(4) = 4 + 3 + 4 = 11.

Correction → Actually best is microservice(1) + monolith(2,3) + microservice(4) = 5 + (2+2) + 4 = 11.
Wait — need careful DP here (explains why problem is tricky).
(We’ll compute properly when solving).

Test case 2:

Best split gives cost 8. */
//بِسْمِ ٱللَّهِ ٱلرَّحْمَـٰنِ ٱلرَّحِيمِ
package _6_DynamicProgramming._06_Partition_DP;
import java.io.*;
import java.util.*;
public class _13_google_oa_q{
    //https://www.youtube.com/watch?v=jb1iTagAgtE&list=PLIp-xrYmLruIuBdyw-_wrRqsIEot3GDZf&index=16
    public static void main(String[] args) throws IOException {
        init_IO();
        solve();
        reader.close();
        writer.flush();
        writer.close();
    }
    static void solve() throws IOException{
        int n=scanInt();
        int a[]=scanIntArray(n);
        int b[]=scanIntArray(n);
        int prefSum[]=new int[n];
        prefSum[0]=b[0];
        for(int i=1;i<n;i++)prefSum[i]=prefSum[i-1]+b[i];
        // int ans=rec(0, a, b,new StringBuilder(),0,prefSum);
        // println(ans);
        int dp[]=new int[n+1];
        for(int i=n-1;i>=0;i--){
            int x=a[i]+dp[i+1];
            int y=INF;
            for(int j=i+1;j<b.length;j++){
                y=min(prefSum[j]-((i>0)?prefSum[i-1]:0)+dp[j+1],y);
            }
            dp[i]=min(x,y);
        }
        println(dp[0]);
    }
    static int rec(int i,int a[],int b[],StringBuilder sb,int cost,int prefSum[]) throws IOException{
        if(i>=a.length){
            println(sb+" ==> "+cost);
            return 0;
        }
        //pick microservice
        int x=a[i]+rec(i+1, a, b,new StringBuilder(sb).append(a[i]).append("->"),cost+a[i],prefSum);
        //pick monolist cluster
        int y=INF;
        for(int j=i+1;j<b.length;j++){
            y=min(prefSum[j]-((i>0)?prefSum[i-1]:0)+rec(j+1, a, b,new StringBuilder(sb).append("{").append(b[i]).append(",").append(b[j]).append("}").append("->"),cost+prefSum[j]-((i>0)?prefSum[i-1]:0),prefSum),y);
        }
        return Math.min(x, y);
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
