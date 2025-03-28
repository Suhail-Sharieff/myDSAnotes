package some_preq._06_cses;
/*
Time limit: 1.00 s
Memory limit: 512 MB



Your task is to divide the numbers 1,2,\ldots,n into two sets of equal sum.
Input
The only input line contains an integer n.
Output
Print "YES", if the division is possible, and "NO" otherwise.
After this, if the division is possible, print an example of how to create the sets. First, print the number of elements in the first set followed by the elements themselves in a separate line, and then, print the second set in a similar way.
Constraints

1 \le n \le 10^6

Example 1
Input:
7

Output:
YES
4
1 2 4 7
3
3 5 6
Example 2
Input:
6

Output:
NO
 */



/*******BISMILLAHIRRAHMAANIRRAHEEM*******/
import java.io.*;
import java.util.*;





public class _01_divide_sets_of_same_sum {
    public static void main(String[] args) throws IOException {
        // int t = scanInt();
        // while (t-- > 0) {
            solve();
        // }
    }

    public static void solve() throws IOException {
        int n=scanInt();
        long sum=(n*1l*(n+1))/2;
        if(sum%2!=0){
            print("NO");
        }else{
            int req_tar=(int)sum/2;
            print("YES");
            StringBuilder s1=new StringBuilder(),s2=new StringBuilder();
            int n1=0,n2=0;
            while (req_tar>0) {
                if(req_tar>=n){
                    s1.append(n+" ");
                    n1++;
                    req_tar-=n;
                }else{
                    s2.append(n+" ");
                    n2++;
                }
                n--;
            }
            print(n1+"\n"+s1+"\n"+n2+"\n"+s2);
        }
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

