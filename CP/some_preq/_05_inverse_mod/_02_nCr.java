package some_preq._05_inverse_mod;

public class _02_nCr {
    //Application of Fermat little ie ([b^(m-2)]%m =inverse(b)):



    /*
    WKT nCr=n! / (n-r)! r!


    When asked in question:(say at some M where M is prime like 1e9+7)

    say Nr=fact[n]
    say Dr=fact[n-r]*1l*fact[r]
    nCr=Nr/dr ie Nr*inverse(Dr),
    but inverse(Dr) = pow(Dr,M-2) as per Fermat little theorm,
    so say inv=pow(Dr,M-2)

    nCr= ans = (Nr*1l*inv)%M  which is the required answer, the thing is that we  could have done ans=(Nr/Dr) directly, but just think, what if Nr<Dr or Nr%Dr!=0 it reuslts in some rubbish double value which is also unaccurate, so we make use of Fermat little theorm by computing inverse(Dr) and multiplying it to Nr coz (Nr/Dr) is as same as (Nr*inverse(Dr))

     */
    
     static int MOD = 1_000_000_007;
     static long fact[];

    public static void main(String[] args) {
   
        compute_fact();//coz we need to initilaize fact array first
        int queries[][]={{10,2},{34,5},{10000,8}};
        for(int q[]:queries){
            long start=System.currentTimeMillis();
            System.out.println(q[0]+"C"+q[1]+" = "+nCr(q[0], q[1]));
            long end=System.currentTimeMillis();
            System.out.println("["+"Finished in "+(end-start)+"]");
        }
        
    }

    static void compute_fact(){
        fact=new long[100001];
        fact[0]=fact[1]=1;
        for(int i=2;i<=100000;i++){
            fact[i]=(i*1l*fact[i-1])%MOD;
        }
    }

    static long nCr(int n,int r){//O(N)
        long nr=fact[n];
        long dr=(fact[n-r]*1l*fact[r])%MOD;
        long inv=pow(dr,MOD-2);//using fermat little theorm, inverse(x)=pow(x,m-2) given m is prime
        long ans=(nr*1l*inv)%MOD;
        return ans;
    }
    static long pow(long base, long exp) {//O(logN)
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

}
