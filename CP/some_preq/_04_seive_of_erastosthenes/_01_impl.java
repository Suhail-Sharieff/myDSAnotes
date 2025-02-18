package some_preq._04_seive_of_erastosthenes;

import java.util.Arrays;

public class _01_impl {
    //Used to find primes numbers from 2 to n


    public static void main(String[] args) {
        int n=100;
        boolean is_prime[]=new boolean[n];
        seive(n,is_prime);
        int queries[]={23,56,33,9,11};
        for(int q:queries){
            System.out.println("Is "+q+" prime?: "+is_prime[q]);
        }
    }

    static void seive(int n,boolean isPrime[]){//O(Nlog(logN))
        Arrays.fill(isPrime, true);
        isPrime[0]=isPrime[1]=false;
        isPrime[2]=true;
        for(int num=2;num<n;num++){
            if(isPrime[num]){
                // System.out.print(num+"->");
                int i=num+num;
                while(i<n){
                    isPrime[i]=false;
                    i+=num;
                }
            }
        }
    }

}
