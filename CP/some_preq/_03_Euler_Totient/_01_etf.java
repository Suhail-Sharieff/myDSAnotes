package some_preq._03_Euler_Totient;

import java.util.HashMap;


//prereq:https://cp-algorithms.com/algebra/phi-function.html
public class _01_etf {
    //ETF(Euler's Totient funtion repr by ⌀(n)) for a number n is the count of number of integers from 1 to n(inclusive) such that n and that integer r coprimes(ir gcd(thatNumber,n)==1). 
    /*
    Its easily observable that for a prime number 'p' , ⌀(p)=p-1, those (p-1) numbers r nothing but [1...(n-1)]. 

    But now for a prime number 'p' and any constant 'k'(k>=1), its a good observation that there r obviosuly (p^k) integeers from [1..p^k] where in ((p^k)/p) ie p^(k-1) numbers r divisible by that p, ie out of those p^k numbers there r p^(k-1) numbers that r divisible by p, hence the GCD of p and each of those p^(k-1) numbers can never be 1 ie thay cant be coprime with p. So we can concude that ⌀(p^k) = totalNumsFrom1to(p^k)-numbersNotCoPrimeWithP = p^k - p^(k-1)


    Now since we can represent any number n with primeNumber^k, by using the above property we can calculate easily the ⌀(n) by finding prime factors of that n say [a,b,c,d] such that n=(a^k1)*(b^(k2))...(d^(k4))

    Using ⌀(ab) where a and b both r relatively prime, ⌀(ab)=⌀(a)*⌀(b)*[GCD(a,b)/⌀(GCD(a,b))] 
    So ⌀(n)=⌀(a^k1 - a^(k1-1))*(b^k2 - b^(k2-1))....(c^kn - c^(kn-1))*1  --- 1 coz GCD(primes)=1

    On taking a^k1,b^k2... they all prod to n, so ⌀(n) = n*(1-1/a)(1-1/b)...(1-1/c)....

     */
    public static void main(String[] args) {
        int n=1337;

        var hs=prime_factorization(n);

        long ans=1l;
        for(var e:hs.entrySet()){//uses the formula: ⌀(n) = ⌀(p1^k1)*⌀(p2^k2)....⌀(pn^kn)
            int primeNumber=e.getKey();
            int k=e.getValue();
            ans*=ETF(primeNumber, k);
        }
        //uses the formula: ⌀(n) = ⌀(p1^k1)*⌀(p2^k2)....⌀(pn^kn)
        System.out.println("ETF of "+n+" is "+ans);


        //on solving that formula furtter ny putting each values of uses the formula: ⌀(i), taking term common which multiplies to n, we get uses the formula: ⌀(n) = n * (1-1/p1) * ....(1-1/pn)
        long nr=1l,dr=1l;
        for(var e:hs.keySet()){
            nr*=(e-1)*1l;
            dr*=e*1l;
        }
        ans=(n*nr)/dr;
        System.out.println("ETF of "+n+" is "+ans);
    }

    static int ETF(int prime_number,int k){
        //a pime number p^k  can divide p^(k-1) numbers from 1...p^k
        //p^k - p^(k-1) ie p^k (1 - 1/p) ie p^k(p-1)/p ie [ p^(k-1) ] * (p-1)
        return (int)(Math.pow(prime_number, k-1)*(prime_number-1));
    }

    static HashMap<Integer,Integer> prime_factorization(int n){//O(sqrt(n))
        HashMap<Integer,Integer>p_n=new HashMap<>();
        int num=2;
        while (n>1) {
            if(n%num!=0) while(n%num!=0) num++;
            while(n>1 && n%num==0){
                p_n.put(num, p_n.getOrDefault(num, 0)+1);
                n/=num;
            }
        }
        return(p_n);
    }   
}
