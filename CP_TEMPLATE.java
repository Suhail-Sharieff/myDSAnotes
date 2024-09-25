import java.io.*;
import java.util.*;

public class CP_TEMPLATE {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int t = scanInt(); // number of test cases
        while (t-- > 0) {
            solve(); // solve function for each test case
        }
    }

    public static int scanInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public static String scanString() throws IOException {
        return nextToken();
    }

    public static int[] scanIntArray() throws IOException {
        int n = scanInt(); // Read the size of the array
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanInt(); // Read each integer
        }
        return array;
    }

    public static int GCD(int a, int b) {
        return (b == 0) ? (a) : GCD(b, a % b);
    }

    public static int LCM(int a, int b) {
        return ((a * b) / GCD(a, b));
    }

    private static String nextToken() throws IOException {
        if (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    // Function to solve the problem
    public static void solve() throws IOException {
        // Your problem-solving logic goes here
    }

}
