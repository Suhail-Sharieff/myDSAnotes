package _6_DynamicProgramming._03_Subseq_Subset_Perm.Subset;
/*Problem Statement

You are given an array, and you have to make some delete operations on it:

Delete any p elements from it.

Then delete q groups of 2 consecutive elements from the remaining array.

Then delete r groups of 3 consecutive elements from the remaining array.

After all these operations, the sum of elements of the resultant array should be the maximum possible.
Return this maximum value.

Example Test Case

Input:

arr = [3, 1, 0, 5, 1, 6, 5, -1, -100]  
p = 1, q = 1, r = 1


Output:

16


Explanation:

Delete [1], [-1], [-100] and [3,0,1].

Remaining array: [5, 6, 5].

Sum = 5 + 6 + 5 = 16.

If you delete differently, the sum will be smaller.
For example: deleting [3], [1,0], [5,1,6] leaves [5, -1, -100], sum = -96, which is less than 16. */
//بِسْمِ ٱللَّهِ ٱلرَّحْمَـٰنِ ٱلرَّحِيمِ
import java.io.*;
import java.util.*;
public class _10_uber_oa{
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
        int arr[]=scanIntArray(n);
        int p=scanInt(),q=scanInt(),r=scanInt(); 


        //------------recrive debug
        
        // maxv=NEG_INF;
        // fans="";
        // int ans=f(0, p, q, r, arr, "", 0);
        // println(fans);

       
        println(tab(arr, p, q, r));



    }

    static int maxv;
    static String fans;
    static int f(int i,int p,int q,int r,int arr[],String s,int sum) throws IOException{
        if(i>=arr.length){
            // println(s+" "+sum);
            if( p==0 && r==0 && q==0){
                if(sum>maxv ){
                    maxv=sum;
                    fans=s+" with sum="+sum;
                }
                return 0;
            }
            return NEG_INF;
        }
        int x=f(i+1, p, q, r, arr, s+" "+arr[i], sum+arr[i]);
        if(x!=NEG_INF) x+=arr[i];
        int a=NEG_INF;
        int b=NEG_INF;
        int c=NEG_INF;
        if(p>0) a=f(i+1, p-1, q, r, arr, s, sum);//skip 1 elemt
        if(q>0 && i+1<arr.length) b=f(i+2, p, q-1, r, arr, s, sum);//skip 2 elemts, mistkae: wrote i+2<arr.length
        if(r>0 && i+2<arr.length) c=f(i+3, p, q, r-1, arr, s, sum);//skip 3 elemts, mistake: wrote i+3<arr.length
        return max(x,a,b,c);
    }


    static int tab(int arr[],int p,int q,int r){
        int n=arr.length;
        int dp[][][][]=new int[n+1][p+1][q+1][r+1];
        for(var a:dp) for(var b:a) for(var c:b) Arrays.fill(c, NEG_INF);
        dp[n][0][0][0]=0;
        for(int i=n-1;i>=0;i--){
            for(int pp=p;pp>=0;pp--){
                for(int qq=q;qq>=0;qq--){
                    for(int rr=r;rr>=0;rr--){
                        int x=dp[i+1][pp][qq][rr];
                        if(x!=NEG_INF) x+=arr[i];
                        int a=(pp>0)?dp[i+1][pp-1][qq][rr]:NEG_INF;
                        int b=(i+1<n && qq>0)?(dp[i+2][pp][qq-1][rr]):NEG_INF;
                        int c=(i+2<n && rr>0)?(dp[i+3][pp][qq][rr-1]):NEG_INF;
                        dp[i][pp][qq][rr]=max(x,a,b,c);
                    }
                }
            }
        }
        return (dp[0][p][q][r]);
    }

    static boolean debug=true;
    static int MOD=1_000_000_007;
    static int NEG_INF=Integer.MIN_VALUE;
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
