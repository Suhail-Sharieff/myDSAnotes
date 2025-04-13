/*
You have n coins with positive integer values. What is the smallest sum you cannot create using a subset of the coins?
Input
The first input line has an integer n: the number of coins.
The second line has n integers x_1,x_2,\dots,x_n: the value of each coin.
Output
Print one integer: the smallest coin sum.
Constraints

1 \le n \le 2 \cdot 10^5
1 \le x_i \le 10^9

Example
Input:
5
2 9 1 2 7

Output:
6
 */

 //https://www.youtube.com/watch?v=PAyxrf4FMw0&ab_channel=BinaryMagic


 
 
 
 /*******BISMILLAHIRRAHMAANIRRAHEEM*******/
 import java.io.*;
 import java.util.*;
 
 
 
 
 
 public class _02_minimum_missing_sum {
     public static void main(String[] args) throws IOException {
         int t = scanInt();
         while (t-- > 0) {
             solve();
         }
     }
 
     /*Basic Idea
Goal: Find the smallest sum that cannot be formed with any subset of the coins.

Setup:

Start with a variable ans = 1. This represents the smallest sum that we currently believe we cannot form.

Sort the coins in ascending order.

The intuition is that if you can form every sum from 1 up to ans - 1 and you have a coin with a value at most ans, then you can extend the range of sums you can form.` */
     public static void solve() throws IOException {
         int ans=0;
         int len=scanInt();
         int coins[]=scanIntArray(len);
         Arrays.sort(coins);
         for(int coin:coins){
            if(coin>ans) break;
            ans+=coin;
         }
         print(ans);
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
 
     static int GCD(int a, int b) {
         return (b == 0) ? (a) : GCD(b, a % b);
     }
 
     static int LCM(int a, int b) {
         return ((a * b) / GCD(a, b));
     }
 
     static String nextToken() throws IOException {
         if (st == null || !st.hasMoreTokens()) {
             st = new StringTokenizer(br.readLine());
         }
         return st.nextToken();
     }
 
     static List<Integer> getPrimeList(int from, int tillWhere) {
         boolean isPrime[] = new boolean[tillWhere + 1];
         List<Integer> primesList = new ArrayList<>();
         Arrays.fill(isPrime, true);
         for (int i = 2; i <= tillWhere; i++) {
             if (isPrime[i]) {
                 if (i >= from) {
                     primesList.add(i);
                 }
                 for (int j = i * i; j <= tillWhere; j += i) {
                     isPrime[j] = false;
                 }
             }
         }
         return primesList;
     }
 
     static List<Integer> getDivisorListOf(int num) {
         List<Integer> divisorList = new ArrayList<>();
         for (int i = 1; i * i <= num; i++) {
             if (num % i == 0) {
                 divisorList.add(i);
                 if (num / i != i) {
                     divisorList.add(num / i);
                 }
             }
         }
         return divisorList;
     }
 
     static void printArray(int arr[]) throws IOException {
         StringBuilder sb = new StringBuilder();
         for (int e : arr) {
             sb.append(e + " ");
         }
         bw.write(sb.toString().trim());
         bw.newLine();
         bw.flush();
     }
 
     static boolean isPrime(int n) {
         if (n <= 1) {
             return false;
         }
         for (int i = 2; i * i <= n; i++) {
             if ((n % i) == 0) {
                 return false;
             }
         }
         return true;
     }
 
     static List<Integer> getPrimeFactorsListOf(int num) {
         if (num <= 1) {
             return new ArrayList<>();
         }
         List<Integer> primefactorsList = new ArrayList<>();
         for (int i = 2; i * i <= num; i++) {
             if (num % i == 0) {
                 primefactorsList.add(i);
                 while (num % i == 0) {
                     num /= i;
                 }
             }
         }
         if (num != 1) {
             primefactorsList.add(num);
         }
         return primefactorsList;
     }
 
     static long pow(long base, long exp) {
         long ans = 1l;
         boolean isNegativeExponent = exp < 0;
         exp = Math.abs(exp);
         while (exp > 0) {
             if ((exp & 1) == 1) {
                 ans = (ans * base * 1l) % MOD;
             }
             base = (base * base * 1l) % MOD;
             exp >>= 1;
         }
         return isNegativeExponent ? (1l / ans) : ans;
     }
 
     static void compute_fact(){
         fact=new long[100001];
         fact[0]=fact[1]=1;
         for(int i=2;i<=100000;i++){
             fact[i]=(i*1l*fact[i-1])%MOD;
         }
     }
 
     static long nCr(int n,int r){
         long nr=fact[n];
         long dr=(fact[n-r]*1l*fact[r])%MOD;
         long inv=pow(dr,MOD-2);//using fermat little theorm, inverse(x)=pow(x,m-2) given m is prime
         long ans=(nr*1l*inv)%MOD;
         return ans;
     }
 
     static void print(Object o) throws IOException {
         bw.write(o.toString());
         bw.newLine();
         bw.flush();
     }
 
 }
 