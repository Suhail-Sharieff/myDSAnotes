package some_preq._05_inverse_mod;

/*******BISMILLAHIRRAHMAANIRRAHEEM*******/
import java.io.*;
import java.util.*;
/*
Here is another task for you, prepared by Monk himself. So, this is how it goes :

Given an integer array A of size N, Monk needs you to answer T queries for him. In each query, he gives you 2 integers P and Q. In response to each of these queries, you need to tell Monk the count of numbers in array A. that are either divisible by P, Q, or both.

Can you cope with this ?

Video approach to solve this question: here

Input Format :

The first line contains a single integer N denoting the size of array A. The next line contains N space separated integers, where the 
 integer denotes 
.

The next line contains a single integer T denoting the number of queries Monk poses to you. Each of the next T lines contains 2 space separated integers P and Q.

Output Format :

For each query, print the answer on a new line.
 */

public class _03_monks_corundrum {
    public static void main(String[] args) throws IOException {
        solve();
    }

    public static void solve() throws IOException {
        int n = scanInt();
        int[] arr = scanIntArray(n);
        int qe = scanInt();
        int len = 200000;
        
        // Build frequency array for values in [1, len]
        int[] freq = new int[len + 1];
        for (int e : arr) {
            if (e <= len) {
                freq[e]++;
            }
        }
        
        // Precompute cnt[i] = count of numbers in arr divisible by i
        long[] cnt = new long[len + 1];
        for (int i = 1; i <= len; i++) {
            for (int j = i; j <= len; j += i) {
                cnt[i] += freq[j];
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (qe-- > 0) {
            int p = scanInt();
            int q = scanInt();
            int lcm = LCM(p, q);
            long ans = cnt[p] + cnt[q];
            if (lcm <= len) {
                ans -= cnt[lcm];
            }
            sb.append(ans).append("\n");
        }
        print(sb.toString());
    }

    static int MOD = 1_000_000_007;
    static long fact[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int scanInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    static long scanLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    static String scanString() throws IOException {
        return nextToken();
    }

    static int[] scanIntArray(int size) throws IOException {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanInt();
        }
        return array;
    }

    // Optimized GCD and LCM functions
    static int GCD(int a, int b) {
        return (b == 0) ? a : GCD(b, a % b);
    }

    static int LCM(int a, int b) {
        return (a / GCD(a, b)) * b;  // Avoids potential overflow.
    }

    static String nextToken() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    // Print once at the end to reduce I/O overhead
    static void print(String s) throws IOException {
        bw.write(s);
        bw.flush();
    }

    // Other utility methods from your template...
    // (getPrimeList, getDivisorListOf, printArray, isPrime, getPrimeFactorsListOf, pow, compute_fact, nCr)
}

