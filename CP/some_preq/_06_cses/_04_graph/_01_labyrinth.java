package some_preq._06_cses._04_graph;
/*
You are given a map of a labyrinth, and your task is to find a path from start to end. You can walk left, right, up and down.
Input
The first input line has two integers n and m: the height and width of the map.
Then there are n lines of m characters describing the labyrinth. Each character is . (floor), # (wall), A (start), or B (end). There is exactly one A and one B in the input.
Output
First print "YES", if there is a path, and "NO" otherwise.
If there is a path, print the length of the shortest such path and its description as a string consisting of characters L (left), R (right), U (up), and D (down). You can print any valid solution.
Constraints

1 \le n,m \le 1000

Example
Input:
5 8
########
#.A#...#
#.##.#B#
#......#
########

Output:
YES
9
LDDRRRRRU
 */

/*******BISMILLAHIRRAHMAANIRRAHEEM*******/
import java.io.*;
import java.util.*;

public class _01_labyrinth {
    public static void main(String[] args) throws IOException {
        // int t = scanInt();
        // while (t-- > 0) {
        solve();
        // }
    }

    public static void solve() throws IOException {
        int nRows = scanInt();
        int nCols = scanInt();
        char mat[][] = new char[nRows][nCols];
        for (int i = 0; i < nRows; i++)
            mat[i] = scanString().toCharArray();
        
        // BRUTE_FORCE(mat);

        optimal(mat);

    }

    //---------------------BRUTE_FORCE
    static void BRUTE_FORCE(char mat[][]) throws IOException {
        int[] sd = get_src_dest(mat);
        shortest_path = null;
        rec(mat, sd[0], sd[1], sd[2], sd[3], new boolean[mat.length][mat[0].length], new StringBuilder());

        if (shortest_path != null) {
            print("YES" + "\n" + shortest_path.length() + "\n" + shortest_path);
        } else {
            print("NO");
        }
    }
    static int[][] dirs = {
        {0, -1, 'L'}, // left
        {1, 0, 'D'},  // down
        {0, 1, 'R'},  // right
        {-1, 0, 'U'}  // up
    };
    
    static String shortest_path;

    static void rec(char mat[][], int i, int j, int dest_x, int dest_y, boolean isVis[][], StringBuilder sb) {
        if (i == dest_x && j == dest_y) {
            if (shortest_path == null || shortest_path.length() > sb.length())
                shortest_path = sb.toString();
            return;
        }
        isVis[i][j] = true;
        for (int dir[] : dirs) {
            int x = dir[0] + i, y = dir[1] + j;
            if (x >= 0 && y >= 0 && x < mat.length && y < mat[0].length) {
                if (!isVis[x][y] && (mat[x][y] == '.' || mat[x][y] == 'B')) {
                    sb.append((char) (dir[2]));
                    rec(mat, x, y, dest_x, dest_y, isVis, sb);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        isVis[i][j] = false;
    }
    static int[] get_src_dest(char mat[][]) {
        int ans[] = new int[4];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 'A') {
                    ans[0] = i;
                    ans[1] = j;
                } else if (mat[i][j] == 'B') {
                    ans[2] = i;
                    ans[3] = j;
                }
            }
        }
        return ans;
    }



    //------------------------------OPTIMAL: Use BFS inplace of DFS
    /*
How does this code ensure we get the shortest path?

✅ The Answer:
This code guarantees the shortest path because it uses BFS, not DFS.

📘 Why BFS Guarantees Shortest Path:
BFS explores nodes in layers — meaning:

It first explores all nodes at distance 1 from the start.

Then all nodes at distance 2.

Then distance 3, and so on...

So, the first time we reach the destination (B), it’s via the shortest path possible. That’s why:

java
Copy
Edit
if (x == endX && y == endY) {
    found = true;
    break;
}
As soon as we reach B, we break — no need to go deeper. Any path found after would be longer.
     */

     static void optimal(char mat[][]) throws IOException {//make sure the firs are given :  LDRU---------------IMP
        int[] sd = get_src_dest(mat);
        int startX = sd[0], startY = sd[1], endX = sd[2], endY = sd[3];
    
        boolean[][] isVis = new boolean[mat.length][mat[0].length];
        boolean reached_dest = false;
    
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startX, startY});
        isVis[startX][startY] = true; // ✅ Fix here
    
        int[][] parX = new int[mat.length][mat[0].length];
        int[][] parY = new int[mat.length][mat[0].length];
        char[][] parDir = new char[mat.length][mat[0].length];
    
        while (!q.isEmpty()) {
            int[] top = q.poll();
    
            if (top[0] == endX && top[1] == endY) {
                reached_dest = true;
                break;
            }
    
            for (int[] dir : dirs) {
                int x = top[0] + dir[0];
                int y = top[1] + dir[1];
    
                if (x >= 0 && y >= 0 && x < mat.length && y < mat[0].length &&
                    !isVis[x][y] && (mat[x][y] == '.' || mat[x][y] == 'B')) {
    
                    isVis[x][y] = true;
                    parX[x][y] = top[0];
                    parY[x][y] = top[1];
                    parDir[x][y] = (char) dir[2];
    
                    q.offer(new int[]{x, y});
                }
            }
        }
    
        if (!reached_dest) {
            print("NO");
            return;
        }
    
        StringBuilder ans = new StringBuilder();
        int x = endX, y = endY;
    
        while (x != startX || y != startY) {
            ans.append(parDir[x][y]);
            int tempX = parX[x][y];
            int tempY = parY[x][y];
            x = tempX;
            y = tempY;


            /*MISTAKE i did:--------directly modified x without using temp, see that then it will change for y as well------IMP
                 ans.append(parDir[x][y]);
                 x=parX[x][y];
                 y=parY[x][y];
             */
        }
    
        print("YES");
        print(ans.length());
        print(ans.reverse().toString());
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
