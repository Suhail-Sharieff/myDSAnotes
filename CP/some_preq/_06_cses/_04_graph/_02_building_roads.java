package some_preq._06_cses._04_graph;

/*Byteland has n cities, and m roads between them. The goal is to construct new roads so that there is a route between any two cities.
Your task is to find out the minimum number of roads required, and also determine which roads should be built.
Input
The first input line has two integers n and m: the number of cities and roads. The cities are numbered 1,2,\dots,n.
After that, there are m lines describing the roads. Each line has two integers a and b: there is a road between those cities.
A road always connects two different cities, and there is at most one road between any two cities.
Output
First print an integer k: the number of required roads.
Then, print k lines that describe the new roads. You can print any valid solution.
Constraints

1 \le n \le 10^5
1 \le m \le 2 \cdot 10^5
1 \le a,b \le n

Example
Input:
4 2
1 2
3 4

Output:
1
2 3 */


/*******BISMILLAHIRRAHMAANIRRAHEEM*******/
import java.io.*;
import java.util.*;





public class _02_building_roads {
    public static void main(String[] args) throws IOException {
        // int t = scanInt();
        // while (t-- > 0) {
            solve();
        // }
    }

    public static void solve() throws IOException {//ans: if there r n connected componets, we need min (n-1) roads so that we can connect all those components, so basically pick any one node from each connected component and just print them, also make sure that if we have only 1 island, just print 0
        int nCities=scanInt();
        int nRoads=scanInt();
        int graph[][]=new int[nRoads][2];
        for(int i=0;i<nRoads;i++) graph[i]=scanIntArray(2);
        List<List<Integer>>adj=get_adj(graph,nCities);
        List<Integer>ans=new ArrayList<>();
        boolean isVis[]=new boolean[nCities+1];
        int n=0;
        for(int cn=1;cn<=nCities;cn++){
            if(!isVis[cn]){
                ans.add(cn);
                n++;
                dfs(adj, isVis, cn);
            }
        }
        if (n!=1) {
            print(ans.size()-1);
            int len=ans.size();
            for(int i=0;i<len-1;i++){
                print(ans.get(i)+" "+ans.get(i+1));
            }
        }else{
            print(0);
        }
    }

    static void dfs(List<List<Integer>>adj,boolean isVis[],int cn){
        isVis[cn]=true;
        for(int e:adj.get(cn)){
            if(!isVis[e]) dfs(adj, isVis, e);
        }
    }

    static List<List<Integer>>get_adj(int graph[][],int nCities){
        List<List<Integer>>adj=new ArrayList<>();
        for(int i=0;i<=nCities;i++) adj.add(new ArrayList<>());
        for(int con[]:graph){
            adj.get(con[0]).add(con[1]);
            adj.get(con[1]).add(con[0]);
        }
        return adj;
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
