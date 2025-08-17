package some_preq._06_cses._02_DP._01_Arrays;

/*
Time limit: 1.00 s
Memory limit: 512 MB



There is a list of n numbers and two players who move alternately. On each move, a player removes either the first or last number from the list, and their score increases by that number. Both players try to maximize their scores.
What is the maximum possible score for the first player when both players play optimally?
Input
The first input line contains an integer n: the size of the list.
The next line has n integers x_1,x_2,\ldots,x_n: the contents of the list.
Output
Print the maximum possible score for the first player.
Constraints

1 \le n \le 5000
-10^9 \le x_i \le 10^9

Example
Input:
4
4 5 1 3

Output:
8
 */
/*******BISMILLAHIRRAHMAANIRRAHEEM*******/
import java.io.*;
import java.util.*;


//--------------------teaches how u take mins and maxs  both in dp

public class _08_removal_game {
    public static void main(String[] args) throws IOException {
        // int t = scanInt();
        // while (t-- > 0) {
        solve();
        // }
    }

    public static void solve() throws IOException {
        int len = scanInt();
        int arr[] = scanIntArray(len);
        // int ans = rec(arr, 0, arr.length - 1);
        long ans=tab(arr);
        print(ans);
    }


    //idea is we will try to choose all possible scores for A and min score for B, so that A's score is maximized, A has 2 possiblities ie choose first and choose last, for each we will try to assign B a minimal score, return max score of A
    static int rec(int nums[], int i, int j) {// we will favour game for A , coz the question is asking max score for A
        if(i>j){//no more elements left
            return 0;
        }
        int a_chooses_first=nums[i]+Math.min(
            rec(nums, i+1, j-1),//further B will choose last element
            rec(nums,i+2,j)//further B will also choose first elemnt
        );
        int a_chooses_last=nums[j]+Math.min(
            rec(nums, i+1, j-1),//further B will choose first elemnt
            rec(nums, i, j-2)//further B will also choose last element
        );
        return Math.max(a_chooses_first, a_chooses_last);
    }


    static long tab(int nums[]){
       //i moved from 0 till n, j from n to i
       long dp[][]=new long[nums.length][nums.length];
       for(int i=nums.length-1;i>=0;i--){
            for(int j=i;j<nums.length;j++){
                //the shit like ()?_:_ i did to make sure it satisies i<=j,  coz the recurisive code returns zero upon i>j, ie we need i<=j for all cases
                long a_chooses_first=nums[i]+Math.min(
                    (i+1<=j-1)?dp[i+1][j-1]:0,//further B will choose last element
                    (i+2<=j)?dp[i+2][j]:0//further B will also choose first elemnt
                );
                long a_chooses_last=nums[j]+Math.min(
                    (i+1<=j-1)?dp[i+1][j-1]:0,//further B will choose first elemnt
                    (i<=j-2)?dp[i][j-2]:0//further B will also choose last element
                );
                dp[i][j]=Math.max(a_chooses_first, a_chooses_last);
            }
       }
       return dp[0][nums.length-1];
    }


    /*using 3d dp: 
     * long dp[][][]=new long[n+2][n+1][2];
        for(int i=n;i>=1;i--){
            for(int j=i;j<=n;j++){
                // for(int turn=1;turn>=0;turn--){
                    dp[i][j][1]=Math.max(arr[i-1]+dp[i+1][j][0],arr[j-1]+dp[i][j-1][0]);
                    dp[i][j][0]=Math.min(dp[i+1][j][1],dp[i][j-1][1]);
                // }
            }
        }
            //rec:
            static int f(int i,int j,int arr[],boolean first){
        if(i>j) return 0;
        if(first) return Math.max(
            arr[i-1]+f(i+1, j, arr, false),
            arr[j-1]+f(i, j-1, arr, false)
        );
        return Math.min(f(i+1, j, arr, true), f(i, j-1, arr, true));
    }
    */

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

    static void compute_fact() {
        fact = new long[100001];
        fact[0] = fact[1] = 1;
        for (int i = 2; i <= 100000; i++) {
            fact[i] = (i * 1l * fact[i - 1]) % MOD;
        }
    }

    static long nCr(int n, int r) {
        long nr = fact[n];
        long dr = (fact[n - r] * 1l * fact[r]) % MOD;
        long inv = pow(dr, MOD - 2);// using fermat little theorm, inverse(x)=pow(x,m-2) given m is prime
        long ans = (nr * 1l * inv) % MOD;
        return ans;
    }

    static void print(Object o) throws IOException {
        bw.write(o.toString());
        bw.newLine();
        bw.flush();
    }

}
