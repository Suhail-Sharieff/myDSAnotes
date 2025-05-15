package _1_recursion.multipleCall;

import java.util.Arrays;

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

        for (int i = 0; i < n ;i++) {
            // Print the number
            // System.out.print(num1 + " ");

            // Swap
            int num3 = num2 + num1;
            num1 = num2;
            num2 = num3;
        }
        //return nth fibo
        return num2;
    }


    static 
/*
 * 
 * Calculating nth fi suing binary exp
 * 
Binary Exponentiation:

To calculate nth fib number,

Wkt F(n) = F(n-1)+F(n-2)

What if i can some how find F(n) using 2 prev values ie F(n-1) and F(n-2) ie 2 degrees

[                                   [
F(n)    =   some_2X2_matrix_say_T *  F(n-1)
F(n-1)                              F(n-2)                       
]2X1                                   ]2X1

sat T =[{a,b},{c,d}]

on multiplyting T with RHS, we get [{aF(n-1)+bF(n-2)} , {cF(n-1)+dF(n-2)}]

=> [{F(n)},{F(n-1)}] = [{aF(n-1)+bF(n-2)} , {cF(n-1)+dF(n-2)}]

Equating matrixes

ie F(n) = aF(n-1)+bF(n-2) => F(n-1)+F(n-2) = aF(n-1)+bF(n-2) => {a=1 & b=1}//upon compairng coeff
also F(n-1) = cF(n-1)+dF(n-2) => {c=1 & d=0} upon comparing coeff

sO therefor T can be replaced as [{a,b},{c,d}] ie [{1,1},{1,0}] for any value of n

In general[{F(n)},{F(n-1)}]= (T^(n-1)) * [{F(1)},{F(0)}]
 ie [{F(n)},{F(n-1)}] = (T^(n-1)) * [{1},{0}]



`



 */
    class bin_exp {
    public static void main(String[] args) {
        Matrix t=new Matrix(new int[][]{
            {1,1},
            {1,0}
        });
        int n=10;//0,1,1,2,3,5,8,13,21,34,55
        //nth fib is t^(n-1) * [{1},{0}]


        Matrix t_pow_n=calpb(t, n-1);
        Matrix lhs=Matrix.multiply(t_pow_n,  new Matrix(new int[][]{{1},{0}}));
        int ans=lhs.mat[0][0];


        System.err.println(ans);


    }
    
    //  static int calp(int x,int n){
    //     int ans=1;
    //     while(n!=0){
    //         if(n%2!=0){
    //             ans=ans*x;
    //         }
    //         x*=x;
    //         n/=2;
    //     }
    //     return ans;
    // }

    static Matrix calpb(Matrix base,int n){
        Matrix ans=new Matrix(new int[][]{{1,0},{0,1}});
        while (n!=0) {
            if(n%2!=0) ans=Matrix.multiply(ans, base);
            base=Matrix.multiply(base, base);
            n/=2;
        }
        return ans;
    }
    

}

static class Matrix{
    int nRows;
    int nCols;
    int mat[][];
    public Matrix(int arr[][]){
        this.nRows=arr.length;
        this.nCols=arr[0].length;
        this.mat=new int[nRows][nCols];
        for(int i=0;i<nRows;i++) mat[i]=arr[i].clone();
    }

    static  Matrix multiply(Matrix a,Matrix b){
        if(a.nCols!=b.nRows) return null;
        Matrix ans=new Matrix(new int[a.nRows][b.nCols]);
        for(int i=0;i<a.nRows;i++) for(int j=0;j<b.nCols;j++) for(int k=0;k<b.nRows;k++) ans.mat[i][j]+=a.mat[i][k]*b.mat[k][j];
        return ans;
    }


    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(int row[]:mat) sb.append(Arrays.toString(row)).append('\n');
        return sb.toString();
    }
}

}
