package _1_recursion.multipleCall;

//the tree continues till 2^n giving TC: O(2^n)
public class _1_fibonacci {
    public static int fib(int limit){
        if (limit==0||limit==1) {
            return limit;
        }
        return (fib(limit-1)+fib(limit-2));
    }
    public static void main(String[] args) {
        int n=4;
       int nthFibNum=fib(n);//stores 4th fib number in seq 0 1 1 2 3 ie "3"
       System.out.println(nthFibNum);
    }
    public static int optimal(int n){
        
        int num1 = 0, num2 = 1;

        for (int i = 0; i < N; i++) {
            // Print the number
            // System.out.print(num1 + " ");

            // Swap
            int num3 = num2 + num1;
            num1 = num2;
            num2 = num3;
        }
        //return nth fibo
        return b;
    }
}
