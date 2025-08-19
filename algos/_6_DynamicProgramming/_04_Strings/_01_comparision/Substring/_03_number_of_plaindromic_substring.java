package _6_DynamicProgramming._04_Strings._01_comparision.Substring;
/*Rubrik OA 

---

### Problem

You are given a string $s = s_1 s_2 \ldots s_{|s|}$ of length $|s|$, consisting of lowercase English letters.

There are $q$ queries, each described by two integers $l_i, r_i$ $(1 \leq l_i \leq r_i \leq |s|)$.
The answer to each query is the **number of palindromic substrings** in the substring $s[l_i \ldots r_i]$.

---

#### Definitions

* A substring $s[l \ldots r] = s_l s_{l+1} \ldots s_r$ $(1 \leq l \leq r \leq |s|)$ is defined as the sequence of characters from index $l$ to index $r$, inclusive.
* A string $t$ is considered a palindrome if it reads the same from left to right and from right to left.
  Formally, if $t = t_1 t_2 \ldots t_{|t|}$, then $t_i = t_{|t| - i + 1}$ for all $1 \leq i \leq |t|$.

---

### Input Format

1. The first line contains a string $s$ $(1 \leq |s| \leq 5000)$.
2. The second line contains an integer $q$ $(1 \leq q \leq 10^6)$, the number of queries.
3. Each of the next $q$ lines contains two space-separated integers $l_i, r_i$ $(1 \leq l_i \leq r_i \leq |s|)$, describing the substring range for the $i$-th query.

It is guaranteed that the string consists only of lowercase English letters.

---

### Output Format

Print $q$ integers — the answers to the queries.
Print them in the order the queries are given, separated by whitespaces.

---

✅ Example (made-up for clarity):

**Input**

```
ababa
3
1 3
2 5
1 5
```

**Output**

```
4 4 9
```

Explanation:

* Substring `aba` (positions 1–3) has palindromes: `a`, `b`, `a`, `aba` → 4.
* Substring `baba` (2–5) has: `b`, `a`, `b`, `a`, `bab`, `aba` → 6 (but depends on actual count).
* Whole string `ababa` has 9 palindromic substrings.

---

Do you want me to also give you the **efficient solution approach** (precompute palindromic substrings + prefix sums to answer each query in O(1))?
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.youtube.com/watch?v=gA0cANzLzgg&list=PLIp-xrYmLruIuBdyw-_wrRqsIEot3GDZf&index=21

public class _03_number_of_plaindromic_substring {
    /*
     * Some preeq:
     * 
     * How to efficirnyly find out if substring from i to j is a palindrom:
     * public static boolean checkPalindrome(String str) {
     * int n=str.length();
     * if(n==0) return true;
     * boolean[][] dp = new boolean[n][n];
     * //all strings of len=1 are paldrime
     * for (int i = 0; i < n; i++) dp[i][i] = true;
     * //makr for len=2
     * for (int i = 0; i + 1 < n; i++)dp[i][i+1] = (str.charAt(i)==
     * str.charAt(i+1));
     * //then for 3 and above
     * for (int len = 3; len <= n; len++) {
     * for (int i = 0; i + len - 1 < n; i++) {
     * int j = i + len - 1;
     * dp[i][j] = (dp[i+1][j-1] && str.charAt(i) == str.charAt(j));
     * }
     * }
     * 
     * return dp[0][n-1];
     * }
     * 
     */

    // now u know whether each substirng from i to j is a palinfrom or not
    // بِسْمِ ٱللَّهِ ٱلرَّحْمَـٰنِ ٱلرَّحِيمِ
    public static void main(String[] args) throws IOException {
        init_IO();
        solve();
        reader.close();
        writer.flush();
        writer.close();
    }

    static void solve() throws IOException {
        String s=scanString();
        char arr[]=s.toCharArray();
        int n=s.length();
        boolean dp[][]=new boolean[n+1][n];
        for(int i=0;i<n;i++) dp[i][i]=true;
        for(int i=0;i<n-1;i++) dp[i][i+1]=(arr[i]==arr[i+1]);
        for(int len=3;len<=n;len++){
            for(int i=0;i+len-1<n;i++){
                int j=i+len-1;
                dp[i][j]=(arr[i]==arr[j] && dp[i+1][j-1]);
            }
        }
        //most imp part
        // Step 2: Fill count table
        int count[][]=new int[n][n];//count[i][j] tells number of plain subs from i to j
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) count[i][j] = 1;
                else if (len == 2) count[i][j] = (dp[i][j] ? 1 : 0) + count[i+1][j] + count[i][j-1] - count[i+1][j-1];
                else count[i][j] = (dp[i][j] ? 1 : 0) + count[i+1][j] + count[i][j-1] - count[i+1][j-1];
            }
        }
        int nq=scanInt();
        while (nq-->0) {
            int l=scanInt();
            int r=scanInt();
            println(count[l][r]);
        }
    }

    static boolean debug = false;
    static int MOD = 1_000_000_007;
    static int INF = Integer.MAX_VALUE;
    static StringTokenizer tokenizer = new StringTokenizer("");
    static BufferedReader reader;
    static BufferedWriter writer;

    static void init_IO() throws IOException {
        if (debug) {
            reader = new BufferedReader(new FileReader("input.txt"));
            writer = new BufferedWriter(new FileWriter("output.txt"));
        } else {
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new BufferedWriter(new OutputStreamWriter(System.out));
        }
    }

    static String next() throws IOException {
        while (!tokenizer.hasMoreTokens())
            tokenizer = new StringTokenizer(reader.readLine());
        return tokenizer.nextToken();
    }

    static int scanInt() throws IOException {
        return Integer.parseInt(next());
    }

    static long scanLong() throws IOException {
        return Long.parseLong(next());
    }

    static char scanChar() throws IOException {
        return next().charAt(0);
    }

    static String scanString() throws IOException {
        return reader.readLine();
    }

    static int[] scanIntArray(int len) throws IOException {
        int arr[] = new int[len];
        for (int i = 0; i < len; i++)
            arr[i] = scanInt();
        return arr;
    }

    static long[] scanLongArray(int len) throws IOException {
        long arr[] = new long[len];
        for (int i = 0; i < len; i++)
            arr[i] = scanLong();
        return arr;
    }

    static void print(Object o) throws IOException {
        writer.write(o.toString() + " ");
    }

    static void println(Object o) throws IOException {
        writer.write(o.toString() + "\n");
    }

    static int min(int... x) {
        return Arrays.stream(x).min().getAsInt();
    }

    static int max(int... x) {
        return Arrays.stream(x).max().getAsInt();
    }

    static int gcd(int a, int b) {
        return (a == 0) ? b : gcd(b, a % b);
    }

    static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}
