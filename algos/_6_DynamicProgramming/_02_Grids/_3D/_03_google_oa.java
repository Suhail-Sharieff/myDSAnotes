package _6_DynamicProgramming._02_Grids._3D;
/*Question: Modulo subsequence

You are given the following:

An array A of size N

A positive integer K

Let B be a subsequence of A whose elements are b₁, b₂, …, bₘ and size is m (m > 1).
B also satisfies the following condition:

(
𝑏
1
+
𝑏
2
)
%
𝐾
  
=
  
(
𝑏
2
+
𝑏
3
)
%
𝐾
  
=
  
…
  
=
  
(
𝑏
𝑚
−
1
+
𝑏
𝑚
)
%
𝐾
(b
1
	​

+b
2
	​

)%K=(b
2
	​

+b
3
	​

)%K=…=(b
m−1
	​

+b
m
	​

)%K

for 
1
<
𝑖
≤
𝑚
1<i≤m.

Task

Determine the maximum length of subsequence B.

Notes

A subsequence of length 2 always satisfies the above condition.

A subsequence is a sequence obtained from deleting zero or more numbers (not necessarily consecutive) from the original sequence without changing the order of the remaining elements.

1-based indexing is followed. 

ex:
n=5  k=5
3 1 2 1 8
sequence= [3,1,8] ie 3
explanation: [(3+1)%5 == (8+1)%5]



*/







//بِسْمِ ٱللَّهِ ٱلرَّحْمَـٰنِ ٱلرَّحِيمِ
import java.io.*;
import java.util.*;
public class _03_google_oa{
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
        int k=scanInt();
        int arr[]=scanIntArray(n);

        int ans=f(0, -1, -1, arr, k, "");

        print(ans);

    }

    static int f(int i,int p,int currMod,int arr[],int k,String s) throws IOException{
        if(i>=arr.length){
            println(s+" with "+currMod);
            return (p!=-1)?1:0;
        }
        int pick=0;
        if(p==-1){
            pick=1+f(i+1, i,-1, arr, k,s+arr[i]+"->");
        }else{
            if(currMod==-1){
                pick=1+f(i+1, i, (arr[i]+arr[p])%k, arr, k, s+arr[i]+"->");
            }else{
                if((arr[i]+arr[p])%k==currMod) pick=1+f(i+1, i, currMod, arr, k, s+arr[i]+"->");
            }
        }
        int dont=f(i+1, p, currMod, arr, k,s);
        return max(pick,dont);
    }

    static boolean debug=true;
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

