package some_preq._02_bit_masking;

public class _04_binary_exp {


    static int MOD=(int)(1e9+7);

    static long pow1(int x,int n){//compute x^n in logN time, works fine for n&x <10^5
        long ans=1l;
        while(n>0){
            if((n&1)!=0) ans*=1l*x;
            x*=1l*x;
            n>>=1;
        }
        return ans;
    }

    static long pow2(long x,long n){//More efficient for numbers upto 10^18, we add repetedly insted of multiplying to avoid overflow, (LogN)^2
        long ans=1l;
        while(n>0){
            if((n&1)!=0) ans=bin_multiply(ans, x);
            x=bin_multiply(x, x);
            n>>=1;
        }
        return ans;
    }
    static long bin_multiply(long x,long n){//supoose 2^3 is passed here it returns 2+2+2=6
        long ans=0l;
        while (n>0) {
            if((n&1)!=0) ans=(ans+x)%MOD;
            x=(x+x)%MOD;
            n>>=1;
        }
        return ans;
    }

    public static void main(String[] args) {
        
        System.out.println(bin_multiply(2, 3));
        System.out.println(pow2(2, 1000));
    }
}




