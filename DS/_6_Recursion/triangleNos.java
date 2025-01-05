package _6_Recursion;

public class triangleNos {
    //simple example to show recursion works in triangle nos like 1,3,6.....of units in units lined up in series 1,2,3,4,....
    public static int triangle(int n){
        System.out.println("Entering n= "+n);
        if (n==1) {
            System.out.println("Returning 1");
            return 1;
        }
        int temp=n+triangle(n-1);
        System.out.println("Returning "+temp);
        return temp;
    }
    public static void main(String[] args) {
        triangle(5);
    }
}
