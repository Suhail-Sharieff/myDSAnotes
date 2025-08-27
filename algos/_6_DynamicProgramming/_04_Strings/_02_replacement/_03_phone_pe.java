package _6_DynamicProgramming._04_Strings._02_replacement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*Lights Arrangement

Problem Statement

Imagine you're organizing a series of lights for a festive event. Each light in the series can either be turned on or off, which we can represent using a binary system where 1 denotes a light being on and 0 denotes a light being off. These lights are arranged on a long string, and your task is to create different possible arrangements for these lights.

Given an integer N, representing the number of lights in the series, you need to determine how many unique ways you can arrange the lights so that there is exactly one occurrence where two consecutive lights are off.
In other words, exactly one pair of consecutive 0s (00) should appear somewhere in the series of length N.

Input Format:

The first line of the input contains T, the number of test cases.

T lines follow, each containing a single integer N, representing the number of lights.
exmaple:

n=2
answer =1 ie {00}

n=3
answer=2 ie {001,100}

n=4
answer=4
answer=5 ie{0010,0011,0100,1001,1100}

n=5 
answer =10 
*/
public class _03_phone_pe {
     public static void main(String[] args) throws IOException {
        init_IO();
        solve();
        reader.close();
        writer.flush();
        writer.close();
    }
    static void solve() throws IOException{
        int n=scanInt();
        int _=f(n-1, "0", false, 0)+f(n-1, "1", false, 1);


        int dp[][][]=new int[n+1][2][2];
        Arrays.fill(dp[0][1],1);
        
        for(int i=1;i<=n;i++){
            for(int done=1;done>=0;done--){
                for(int p=1;p>=0;p--){
                    if(p==0){
                        if(done==0){
                            dp[i][done][p]=dp[i-1][1][0]+dp[i-1][0][1];
                        }else{
                            dp[i][done][p]=dp[i-1][1][1];
                        }
                    }else{
                        dp[i][done][p]=dp[i-1][done][0]+dp[i-1][done][1];
                    }
                }
            }
        }
        println(dp[n-1][0][0]+dp[n-1][0][1]);
    }

    static int f(int n,String s,boolean condition_achived,int prev) throws IOException{
        if(n==0){
            if(condition_achived){
                println(s);
                return 1;
            }
            return 0;
        }

        if(prev==0){
            if(!condition_achived){
                return f(n-1, s+"0", true, 0)+f(n-1, s+"1", false, 1);
            }
            return f(n-1, s+"1", true, 1);
        }
        return f(n-1, s+"0", condition_achived, 0)+f(n-1, s+"1", condition_achived, 1);
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
