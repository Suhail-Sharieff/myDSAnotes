package some_preq._06_cses._02_DP;

/*
You have n coins with certain values. Your task is to find all money sums you can create using these coins.
Input
The first input line has an integer n: the number of coins.
The next line has n integers x_1,x_2,\dots,x_n: the values of the coins.
Output
First print an integer k: the number of distinct money sums. After this, print all possible sums in increasing order.
Constraints

1 \le n \le 100
1 \le x_i \le 1000

Example
Input:
4
4 2 5 2

Output:
9
2 4 5 6 7 8 9 11 13
 */
/*******BISMILLAHIRRAHMAANIRRAHEEM*******/
import java.io.*;
import java.util.*;

public class _07_Money_Sums {
    public static void main(String[] args) throws IOException {
        // int t = scanInt();
        // while (t-- > 0) {
        solve();
        // }
    }

    public static void solve() throws IOException {
        int len = scanInt();
        int arr[] = scanIntArray(len);
        using_observation(arr);
    }

    // -------------brute:
    private static Set<Integer> set;

    static Set<Integer> rec(int nums[], int i, int sum) {
        if (i == nums.length) {
            if (sum != 0)
                set.add(sum);
            return set;
        }
        rec(nums, i + 1, sum + nums[i]);
        rec(nums, i + 1, sum);
        return set;
    }

    static void better(int coins[]) {
        // Calculate the sum of the all coins
        int sum = 0;
        int N = coins.length;
        for (int coin : coins) {
            sum += coin;
        }
        boolean[][] dp = new boolean[N + 1][sum + 1];
        // Base case: When no coins are selected and sum = 0 is possible
        dp[0][0] = true;
        for (int i = 1; i <= N; i++) {
            // Iterate over each sum j from the 0 to sum
            for (int j = 0; j <= sum; j++) {
                if (dp[i - 1][j]) {
                    dp[i][j] = true;
                } else if (j - coins[i - 1] >= 0 && dp[i - 1][j - coins[i - 1]]) {
                    dp[i][j] = true;
                }
            }
        }
        // Initialize a list to store the possible sums
        List<Integer> possibleSums = new ArrayList<>();
        for (int j = 1; j <= sum; j++) {
            if (dp[N][j]) {
                possibleSums.add(j);
            }
        }
        // Print the number of the possible sums
        System.out.println(possibleSums.size());
        // Print the possible sums separated by the space
        for (int i = 0; i < possibleSums.size(); i++) {
            System.out.print(possibleSums.get(i) + " ");
        }
    }

    // https://www.youtube.com/watch?v=PXdOyrbNr78&ab_channel=NeatlyStructured
    static void using_observation(int nums[]) {
        int maxSum = Arrays.stream(nums).sum();
        boolean ans[] = new boolean[maxSum + 1];
        ans[0] = true;
        for (int e : nums) {
            for (int i = maxSum; i >= 0; i--) {
                if (i >= e)
                    if (ans[i - e])
                        ans[i] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 1; i <= maxSum; i++) {
            if (ans[i]) {
                sb.append(i + " ");
                cnt++;
            }
        }
        System.out.println(cnt);
        System.out.println(sb);
    }

    // ----------using bitset:
    /*
     * void using_observation(const std::vector<int>& nums) {
     * int maxSum = std::accumulate(nums.begin(), nums.end(), 0);
     * std::bitset<MAX_SUM> ans;
     * ans[0] = 1;
     * 
     * for (int e : nums) {
     * ans |= (ans << e); // shift and OR
     * }
     * 
     * int cnt = 0;
     * std::string result;
     * for (int i = 1; i <= maxSum; ++i) {
     * if (ans[i]) {
     * ++cnt;
     * result += std::to_string(i) + " ";
     * }
     * }
     * 
     * std::cout << cnt << std::endl;
     * std::cout << result << std::endl;
     * }
     * 
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