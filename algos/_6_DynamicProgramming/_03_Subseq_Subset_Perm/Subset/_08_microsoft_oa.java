package _6_DynamicProgramming._03_Subseq_Subset_Perm.Subset;
//بِسْمِ ٱللَّهِ ٱلرَّحْمَـٰنِ ٱلرَّحِيمِ
/*Problem Statement

You are given an array A consisting of N numbers.

In one move you can:

Delete the first two elements of A, or

Delete the last two elements of A, or

Delete the first and last elements of A.

The result of a move is the sum of the deleted elements.
No move can be performed if the length of A is smaller than 2.

You need to perform moves such that all results are equal, and return the maximum number of moves possible.

Function Signature
int solution(vector<int> &A);

Input

A vector A of N integers.

Output

Return the maximum number of moves that can be performed on A, such that all performed moves have the same result.

Examples
Example 1
A = [3, 1, 5, 3, 3, 4, 2]


First move: delete last two → (4 + 2 = 6) → A = [3, 1, 5, 3, 3]

Second move: delete first and last → (3 + 3 = 6) → A = [1, 5, 3]

Third move: delete first two → (1 + 5 = 6) → A = [3]

Answer = 3

Example 2
A = [4, 1, 4, 3, 3, 2, 5, 2]


It is possible to delete first and last elements repeatedly:

(4 + 2 = 6) → A = [1, 4, 3, 3, 2, 5]

(1 + 5 = 6) → A = [4, 3, 3, 2]

(4 + 2 = 6) → A = [3, 3]

(3 + 3 = 6) → A = []

Answer = 4

✅ So the task is to maximize the number of moves such that the sum of removed elements in every move is the same. */
import java.io.*;
import java.util.*;
public class _08_microsoft_oa{
    public static void main(String[] args) throws IOException {
        init_IO();
        solve();
        reader.close();
        writer.flush();
        writer.close();
    }
    static void solve() throws IOException{
        int len=scanInt();
        int arr[]=scanIntArray(len);
        int n=arr.length;
        int t1=arr[0]+arr[1];
        int t2=arr[n-1]+arr[n-2];
        int t3=arr[0]+arr[n-1];
        // int ans=max(
        //     f(arr, 0, n-1, t1,""),
        //     f(arr, 0, n-1, t2,""),
        //     f(arr, 0, n-1, t3,"")
        // );
        // println(ans);
        // f(arr,0,n-1,t3,"");
        int ans=max(tab(arr,t1),tab(arr, t2),tab(arr, t3));
        println(ans);
    }
    static int tab(int arr[],int target){
        int n=arr.length;
        int dp[][]=new int[n+2][n+1];
        for(int i=n-1;i>=0;i--){
            for(int j=i+1;j<n;j++){
                int fs=(arr[i]+arr[i+1]==target && i+1<n)?1+dp[i+2][j]:0;
                int lls=(arr[j]+arr[j-1]==target && j-2>=0)?1+dp[i][j-2]:0;
                int fl=(arr[i]+arr[j]==target)?1+dp[i+1][j-1]:0;
                dp[i][j]=max(fs,lls,fl);
            }
        }
        return dp[0][n-1];
    }
    static int f(int arr[],int i,int j,int target,String s) throws IOException{
        if(i>=j){
            return 0;
        }
        // print(arr[i]+"&"+arr[j]+" ");
        int fs=(arr[i]+arr[i+1]==target)?1+f(arr, i+2, j, target,s+"{"+arr[i]+","+arr[i+1]+"}->"):0;
        int lls=(arr[j]+arr[j-1]==target)?1+f(arr, i, j-2, target,s+"{"+arr[j-1]+","+arr[j]+"}->"):0;
        int fl=(arr[i]+arr[j]==target)?1+f(arr, i+1, j-1, target,s+"{"+arr[i]+","+arr[j]+"}->"):0;
        // if(fs!=0) return fs;
        // if(lls!=0) return lls;
        // return fl;
        return max(fs,lls,fl);
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
