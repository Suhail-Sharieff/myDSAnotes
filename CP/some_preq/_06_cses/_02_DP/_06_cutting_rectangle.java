package some_preq._06_cses._02_DP;




/*******BISMILLAHIRRAHMAANIRRAHEEM*******/
import java.io.*;
import java.util.*;
/*
Given an a \times b rectangle, your task is to cut it into squares. On each move you can select a rectangle and cut it into two rectangles in such a way that all side lengths remain integers. What is the minimum possible number of moves?
Input
The only input line has two integers a and b.
Output
Print one integer: the minimum number of moves.
Constraints

1 \le a,b \le 500

Example
Input:
3 5

Output:
3
 */

//this problem is an application of Partition DP


public class _06_cutting_rectangle {
    public static void main(String[] args) throws IOException {
        // int t = scanInt();
        // while (t-- > 0) {
            solve();
        // }
    }

    public static void solve() throws IOException {
        int length=scanInt();
        int breadth=scanInt();
       
        print(tab(length, breadth));
    }

    static int rec(int curr_length,int curr_breadth){

        if(curr_length==curr_breadth) return 0;//i already got a sqaure, why will i cut further

        int if_i_cut_horizontally=(int)(1e5);//i cut horizontally somewhere at k between [0,i], i will be left with [kXj] and [i-k X j]
        for(int cutting_point=1;cutting_point<curr_length;cutting_point++){
            if_i_cut_horizontally=Math.min(
                if_i_cut_horizontally,
                1+rec( cutting_point, curr_breadth)+rec(curr_length-cutting_point, curr_breadth)
            );
        }

        int if_i_cut_vertically=(int)(1e5);//if i cut vertically somewhere at k between [1,j], i will be left with peices with [iXk] and [i X j-k]
        for(int cutting_point=1;cutting_point<curr_breadth;cutting_point++){
            if_i_cut_vertically=Math.min(
                if_i_cut_vertically,
                1+rec( curr_length, cutting_point)+rec( curr_length, curr_breadth-cutting_point)
            );
        }
    
        return Math.min(if_i_cut_horizontally,if_i_cut_vertically);
    }

    static int tab(int total_length,int total_breadth){
        int dp[][]=new int[total_length+1][total_breadth+1];
        for(int row[]:dp) Arrays.fill(row, (int)1e5);
        for(int curr_length=1;curr_length<=total_length;curr_length++){
            for(int curr_breadth=1;curr_breadth<=total_breadth;curr_breadth++){
                if(curr_length==curr_breadth) dp[curr_length][curr_breadth]=0;
                else{
                    int if_i_cut_horizontally=(int)(1e5);
                    for(int cutting_point=1;cutting_point<curr_length;cutting_point++){
                        if_i_cut_horizontally=Math.min(
                            if_i_cut_horizontally,
                            1+dp[cutting_point][curr_breadth]+dp[curr_length-cutting_point][curr_breadth]
                        );
                    }
                    int if_i_cut_vertically=(int)(1e5);
                    for(int cutting_point=1;cutting_point<curr_breadth;cutting_point++){
                        if_i_cut_vertically=Math.min(
                            if_i_cut_vertically,
                            1+dp[curr_length][cutting_point]+dp[curr_length][curr_breadth-cutting_point]
                        );
                    }
                    dp[curr_length][curr_breadth]=Math.min(if_i_cut_horizontally, if_i_cut_vertically);
                }
            }
        }
        return dp[total_length][total_breadth];
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
