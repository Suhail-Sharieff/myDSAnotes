package some_preq._06_cses._02_DP._01_Arrays;

/*
Time limit: 1.00 s
Memory limit: 512 MB



You know that an array has n integers between 1 and  m, and the absolute difference between two adjacent values is at most 1.
Given a description of the array where some values may be unknown, your task is to count the number of arrays that match the description.
Input
The first input line has two integers n and m: the array size and the upper bound for each value.
The next line has n integers x_1,x_2,\dots,x_n: the contents of the array. Value 0 denotes an unknown value.
Output
Print one integer: the number of arrays modulo 10^9+7.
Constraints

1 \le n \le 10^5
1 \le m \le 100
0 \le x_i \le m

Example
Input:
3 5
2 0 2

Output:
3

Explanation: The arrays [2,1,2], [2,2,2] and [2,3,2] match the description.
 */
/*******BISMILLAHIRRAHMAANIRRAHEEM*******/
import java.io.*;
import java.util.*;

public class _04_array_description {
    public static void main(String[] args) throws IOException {
        // int t = scanInt();
        // while (t-- > 0) {
        solve();
        // }
    }

    public static void solve() throws IOException {
        int len = scanInt();
        int m = scanInt();
        int nums[] = scanIntArray(len);
        int ans = tab(nums, m);
        print(ans);
    }

    //analyze with test case: [0,0,0] expected ans:26

    //intuition: keep track of previously visited elemnts for each zero , build combinations, sum and return

    //call like: rec(nums,nums.length-1,-1,m)
    static int rec(int nums[], int i, int prev, int m) {
        // Base case: if i < 0, we've successfully assigned all elements.
        if (i < 0) return 1;
        
        // If the current element is known.
        if (nums[i] != 0) {
            // If there's a right neighbor (prev != -1) and the difference is too high, it's invalid.
            if (prev != -1 && Math.abs(nums[i] - prev) > 1) return 0;// consider case like [2,0,5]
            return rec(nums, i - 1, nums[i], m);
        }

        //_______________________nums[i] is 0_____________________
        // If the current element is unknown, try every possible value.
        int ans = 0;
        // If there is no right neighbor constraint ie initially we r at end of array, try all values from 1 to m.
        if (prev == -1) {
            for (int k = 1; k <= m; k++) {
                ans += rec(nums, i - 1, k, m);
            }
        } else {
            // If there is a right neighbor, choose only values within [prev-1, prev+1] to satisfy absDiff as 1
            for (int k = prev - 1; k <= prev + 1; k++) {
                if (k >= 1 && k <= m) {
                    ans += rec(nums, i - 1, k, m);
                }
            }
        }
        return ans;
    }
    
    //call like:mem(nums, nums.length , 0, m, dp);
    static int mem(int nums[], int i, int prev, int m, int dp[][]) {
        if (i == 0) return 1;

        if(dp[i][prev]!=-1) return dp[i][prev];

        if (nums[i-1] != 0) {
            if (prev != 0 && Math.abs(nums[i-1] - prev) > 1) return 0;
            return dp[i][prev]=mem(nums, i - 1, nums[i-1], m,dp);
        }
        
        int ans = 0;
        if (prev == 0) {
            for (int k = 1; k <= m; k++) {
                ans += mem(nums, i - 1, k, m,dp);
            }
        } else {
            for (int k = prev - 1; k <= prev + 1; k++) {
                if (k >= 1 && k <= m) {
                    ans += mem(nums, i - 1, k, m,dp);
                }
            }
        }
        return dp[i][prev]=ans;
    }

    static int tab(int nums[],int m){
        int dp[][]=new int[nums.length+1][m+1];
        Arrays.fill(dp[0], 1);
        for(int i=1;i<=nums.length;i++){
            for(int prev=0;prev<=m;prev++){
                if(nums[i-1]!=0){
                    if(prev!=0 && Math.abs(nums[i-1]-prev)>1) continue;
                    dp[i][prev]=dp[i-1][nums[i-1]];
                    continue;
                }
                int ans=0;
                if(prev==0){
                    for(int k=1;k<=m;k++) ans=(ans+dp[i-1][k])%MOD;
                }else{
                    for(int k=prev-1;k<=prev+1;k++) if(k>=1 && k<=m) ans=(ans+dp[i-1][k])%MOD;
                }
                dp[i][prev]=ans;
            }
        }
        return dp[nums.length][0];
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
