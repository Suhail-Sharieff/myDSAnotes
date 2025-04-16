package some_preq._02_bit_masking;


//https://codeforces.com/contest/2094/problem/E

/*
E. Boneca Ambalabu
time limit per test2 seconds
memory limit per test256 megabytes
Boneca Ambalabu gives you a sequence of n
 integers a1,a2,…,an
.

Output the maximum value of (ak⊕a1)+(ak⊕a2)+…+(ak⊕an)
 among all 1≤k≤n
. Note that ⊕
 denotes the bitwise XOR operation.

Input
The first line contains an integer t
 (1≤t≤104
) – the number of independent test cases.

The first line of each test case contains an integer n
 (1≤n≤2⋅105
) – the length of the array.

The second line of each test case contains n
 integers a1,a2,…,an
 (0≤ai<230
).

It is guaranteed that the sum of n
 over all test cases does not exceed 2⋅105
.

Output
For each test case, output the maximum value on a new line.

Example
InputCopy
5
3
18 18 18
5
1 2 4 8 16
5
8 13 4 5 15
6
625 676 729 784 841 900
1
1
OutputCopy
0
79
37
1555
0
Note
In the first test case, the best we can do is (18⊕18)+(18⊕18)+(18⊕18)=0
.

In the second test case, we choose k=5
 to get (16⊕1)+(16⊕2)+(16⊕4)+(16⊕8)+(16⊕16)=79
.



 */




 /*
🧠 The Big Idea:
You have some numbers. Let’s say:

csharp
Copy
Edit
[8, 13, 4, 5, 15]
Now you can pick any one number from the list — let’s say you pick 8.

Then you do something fun: you XOR 8 with all the numbers in the list — like a magic game!

So you do:

Copy
Edit
8 ^ 8
8 ^ 13
8 ^ 4
8 ^ 5
8 ^ 15
Each ^ gives you a number (the XOR result). Then you add up all those numbers. That’s your "score" for picking 8.

You do this for each number:

Try picking 8 → get a score

Try picking 13 → get a score

Try picking 4 → get a score

... Then, finally, you keep the biggest score. That’s the answer!

🎩 But What’s XOR?
Think of numbers as made of lights (bits): on or off.

8 in binary = 1000

13 in binary = 1101

When you XOR two numbers:

If the lights are different → turn on the light (1)

If they are same → turn off the light (0)

So 8 ^ 13 = 0101 = 5 (in decimal)

😲 But Wait… Doing this for every number in a big list is SLOW!
So we do a magic trick: We count how many numbers have each light (bit) ON.

For example:

At bit position 0 → how many numbers have it ON?

At bit position 1 → how many have it ON?

... We do this for all 30 lights (because numbers are not bigger than 2³⁰).

Now, for each number in the list:

You go bit-by-bit and say:

“If I have this light ON, I will score points from others who have it OFF.” “If I have this light OFF, I will score points from others who have it ON.”

Add up all the points — that’s your smart score for picking that number.

Then finally — you pick the number that gives the most score! 🎉

🧸 Final Thought
Imagine it like a game where:

Every number wears a hat made of lights.

You can pick one number to be your champion.

Your champion fights with all others using XOR (light-matching magic).

The more mismatches (differences), the more points you get.

You want to pick the champion who wins the most mismatches!

Let me know if you want a cartoon-style diagram or a simpler animation explanation—I’d love to help!







You said:
yes


ChatGPT said:
Image created




Rate this image




You said:
can u explain using expale array:[1,2,3,]


ChatGPT said:
Absolutely! Let's explain the XOR game step-by-step using the array [1, 2, 3], just like a story:

🎮 You’re given the array:
csharp
Copy
Edit
[1, 2, 3]
You can pick any number from the list. Then, you’ll XOR it with every number in the list (including itself), and add up the results.

Let’s try each number one by one!

👉 Try picking 1:
We do:

1 ^ 1 = 0

1 ^ 2 = 3

1 ^ 3 = 2

Now add them:

Copy
Edit
0 + 3 + 2 = 5
🎯 Score = 5 when picking 1

👉 Try picking 2:
We do:

2 ^ 1 = 3

2 ^ 2 = 0

2 ^ 3 = 1

Now add them:

Copy
Edit
3 + 0 + 1 = 4
🎯 Score = 4 when picking 2

👉 Try picking 3:
We do:

3 ^ 1 = 2

3 ^ 2 = 1

3 ^ 3 = 0

Now add:

Copy
Edit
2 + 1 + 0 = 3
🎯 Score = 3 when picking 3

✅ Final Answer:
The maximum score is 5, which you get when you pick 1 as the main number.

🔁 Summary:
You try all numbers one by one. Each time:

Do XOR with all other numbers.

Add the results.

Keep the biggest total.

Simple and fun like a magic points game! ✨
  */


/*******BISMILLAHIRRAHMAANIRRAHEEM*******/
import java.io.*;
import java.util.*;





public class _04_boneca_ambalabu {
    public static void main(String[] args) throws IOException {
        int t = scanInt();
        while (t-- > 0) {
            solve();
        }
    }

    public static void solve() throws IOException {
        int len=scanInt();
        int arr[]=scanIntArray(len);


        int total_number_of_numbers_with_ith_bit_as_one[]=new int[31];
        int total_number_of_numbers_with_ith_bit_as_zero[]=new int[31];


        for(int e:arr){
            for(int i=0;i<=30;i++){
                boolean ith_bit_of_curr_is_set=((e&(1<<i))!=0);
                if(ith_bit_of_curr_is_set) total_number_of_numbers_with_ith_bit_as_one[i]++;
                else total_number_of_numbers_with_ith_bit_as_zero[i]++;
            }
        }

        long ans=0l;

        for(int e:arr){
            long sum=0;
            for(int i=0;i<=30;i++){
                boolean ith_bit_of_curr_is_set=((e&(1<<i))!=0);
                if (ith_bit_of_curr_is_set) {
                    //then other numbers with ith bit 0 will xor with 1 to produce some good value
                    sum+=total_number_of_numbers_with_ith_bit_as_zero[i]*1l*(1<<i);
                }else{
                    sum+=total_number_of_numbers_with_ith_bit_as_one[i]*1l*(1<<i);
                }
            }
            ans=Math.max(ans, sum);
        }

        print(ans);
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

    static List<List<Integer>>get_adj(int graph[][],int nNodes){
        List<List<Integer>>adj=new ArrayList<>();
        for(int i=0;i<=nNodes;i++) adj.add(new ArrayList<>());
        for(int con[]:graph){
            adj.get(con[0]).add(con[1]);
            adj.get(con[1]).add(con[0]);
        }
        return adj;
    }

}
