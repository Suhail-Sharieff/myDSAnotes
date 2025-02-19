//there's a common Misconception that (a/b)%m =[(a%m)/(b%m)]%m---this is Not true
//instead (a/b)%m = [(a%m) * (inverse(b)%m)] %m, but how to find this inverse(b)?---Using Fermat Little Theorm

/*
FERMAT LiTTLe Theorm:


It says that:

[A^(m-1)] % m = 1
Given that m&a r coprimes and a is not div by m(m is prime)&(In most cases m&a r given as coprimes, if not just use extended Euclid algo)

then 
[b^(m-1)] %m =1
also,
[b^(m-2)] %m = b^(-1) ie we multiplied b^(-1) on BS
ie [b^(m-2)]%m =inverse(b)-----------------VIMP




LHS we can easily calculte in logM time using Euclid Algo to get inverse(m-2)
 */


 package some_preq._05_inverse_mod;


public class _01_inverse_mod_appl {

    //Problem: Given n and k, calculate nCk, number can be large, return ans%m where m=1e5+7
    static int mod=(int)(1e5);
    public static void main(String[] args) {
        int n=3;
        int k=2;
        get_fact();

        //nCk = (n!)/(n-k)!k!
        int nr=fact[n]%mod;
        int dr=((fact[n-k]%mod) * (fact[k]%mod)) % mod;
        long inverse_of_dr=inverse(dr);
        long final_ans=((nr%mod)*(inverse_of_dr%mod))%mod;

        System.out.println(final_ans);
    }
    static int fact[]=new int[mod];
    static void get_fact() {
        fact[0] = fact[1] = 1;
        for (int i = 2; i < fact.length; i++) {
            fact[i] = (int) ((1L * fact[i - 1] * i) % mod);  // Apply mod
        }
    }
    

    static long inverse(int x){
        long ans=euclid_pow(x, mod-2)%mod;
        return ans;
    }
    static long euclid_pow(long x, long n) {
        long ans = 1L;
        while (n > 0) {
            if ((n & 1) != 0) ans = (ans * x) % mod;  // Apply mod at each step
            x = (x * x) % mod;  // Square x and apply mod
            n >>= 1;
        }
        return ans;
    }
    

 }