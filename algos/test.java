/*******BISMILLAHIRRAHMAANIRRAHEEM*******/
import java.io.*;
import java.util.*;


public class test {
    public static void main(String[] args) throws IOException {
        int t = scanInt();
        while (t-- > 0) {
            solve();
        }
    }

    

    public static void solve() throws IOException {
        int n=scanInt();
        int arr[]=scanIntArray(n);
        long ans[]={0l};
        func(arr, 0, 0, ans);
        System.out.println(ans[0]);

    }
    public static void func(int arr[],int idx,long sum,long ans[]){
        if (idx>=arr.length) {
            ans[0]=Math.max(ans[0], sum);
            return;
        }
        sum+=arr[idx];
        func(arr, idx+1, sum, ans);
        sum-=arr[idx];
        func(arr, idx+1, sum, ans);
        
    }   






















    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int scanInt() throws IOException {return Integer.parseInt(nextToken()); }
    static long scanLong() throws IOException {return Long.parseLong(nextToken());}
    static String scanString() throws IOException {return nextToken();}
    static int[] scanIntArray(int size) throws IOException {int[] array = new int[size];for (int i = 0; i < size; i++) {array[i] = scanInt();}return array;}
    static int GCD(int a, int b) {return (b == 0) ? (a) : GCD(b, a % b);}
    static int LCM(int a, int b) {return ((a * b) / GCD(a, b));}
    static String nextToken() throws IOException {if (st == null || !st.hasMoreTokens()) {st = new StringTokenizer(br.readLine());}return st.nextToken();}
    static List<Integer> getPrimeList(int from,int tillWhere){boolean isPrime[] = new boolean[tillWhere + 1];List<Integer> primesList = new ArrayList<>();Arrays.fill(isPrime, true); for (int i = 2; i <= tillWhere; i++) {if (isPrime[i]) {if(i>=from){primesList.add(i);};for (int j = i * i; j <= tillWhere; j += i) {isPrime[j] = false;}}}return primesList;}// seive of eratosthenes--O(nlog(logn))
    static List<Integer>getDivisorListOf(int num){List<Integer>divisorList=new ArrayList<>();for (int i = 1; i*i <=num; i++) {if (num%i==0) {divisorList.add(i);if (num/i!=i) {divisorList.add(num/i);}}}return divisorList;}
    static void printArray(int arr[]) throws IOException {StringBuilder sb = new StringBuilder();for (int e : arr) {sb.append(e+" ");}bw.write(sb.toString().trim());bw.newLine();bw.flush();}
    static boolean isPrime(int n){if(n<=1){return false;}for(int i=2;i*i<=n;i++){if((n%i)==0){return false;}}return true;}
    static List<Integer> getPrimeFactorsListOf(int num) {if (num <= 1) {return new ArrayList<>();}List<Integer> primefactorsList = new ArrayList<>();for (int i = 2; i*i <= num; i++){if (num%i==0){primefactorsList.add(i);while (num%i==0) {num/=i;}}}if(num!=1){primefactorsList.add(num);}return primefactorsList;}
    static double pow(double base, double exp) {double ans = 1d;boolean isNegativeExponent = exp < 0;exp = Math.abs(exp);while (exp > 0){if (exp % 2 == 1) {ans *= base;}base *= base;exp /= 2;}return isNegativeExponent ? (1d / ans) : ans;}
    static void print(Object o) throws IOException{bw.write(o.toString());bw.newLine();bw.flush();}
    
}
