package _6_Recursion;

public class factorial {
    public static int  fact(int n){
        System.out.print("Fetching factorial of "+n+" --> ");
        if (n==1||n==0) {
            System.out.println("Factorial of  0 is 1");
            return 1;
        }
        int temp=n*fact(n-1);
        System.out.println("Storing  "+temp);
        return temp;
    }
    public static void main(String[] args) {
        fact(4);
    }
}
