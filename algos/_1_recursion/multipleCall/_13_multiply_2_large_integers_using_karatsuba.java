package _1_recursion.multipleCall;

//Karatsuba Algo: see pic for expl

public class _13_multiply_2_large_integers_using_karatsuba {

    public static void main(String[] args) {
        long ans = mul(12, 12);

        System.out.println(ans);
    }

    static long mul(long x, long y) {
        if (x < 10 || y < 10)
            return x * y;

        int nDigits = Math.max(Long.toString(x).length(), Long.toString(y).length());

        long pow = (long) Math.pow(10, nDigits / 2);
        long a = x / pow;
        long b = x % pow;
        long c = y / pow;
        long d = y % pow;

        long ac = mul(a, c);
        long bd = mul(b, d);
        long ad_plus_bc = (a + b) * (c + d) - ac - bd;

        long ans = ac * pow * pow + ad_plus_bc * pow + bd;//instead of ac*(pow(10,n)), i have written pow*pow since pow(10,n/2) * pow(10,n/2) = pow(10,n)

        return ans;

    }

}