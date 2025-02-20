package _6_DynamicProgramming._06_Partition_DP;



//explaination: https://www.youtube.com/watch?v=_WncuhSJZyA&ab_channel=AbdulBari
//visulization: https://www.mimuw.edu.pl/~erykk/algovis/mcm.html
//see _03_MCM_Tree.png as well

/*
Given an array arr[] which represents dimensions of sequence of matrices where the ith matrix has the dimensions (arr[i-1] x arr[i]) for i>=1., find the most efficient way to multiply these matrices together. The efficient way is the one that involves the least number of multiplications.

Examples:

Input: arr[] = [2, 1, 3, 4]
Output: 20
Explanation: There are 3 matrices of dimensions 2 × 1, 1 × 3, and 3 × 4, Let the input 3 matrices be M1, M2, and M3. There are two ways to multiply: ((M1 x M2) x M3) and (M1 x (M2 x M3)), note that the result of (M1 x M2) is a 2 x 3 matrix and result of (M2 x M3) is a 1 x 4 matrix. 
((M1 x M2) x M3)  requires (2 x 1 x 3)  + (0) +  (2 x 3 x 4) = 30 
(M1 x (M2 x M3))  requires (0)  + (1 x 3 x 4) +  (2 x 1 x 4) = 20. 
The minimum of these two is 20.
Input: arr[] = [1, 2, 3, 4, 3]
Output: 30
Explanation: There are 4 matrices of dimensions 1 × 2, 2 × 3, 3 × 4, 4 × 3. Let the input 4 matrices be M1, M2, M3 and M4. The minimum number of multiplications are obtained by ((M1 x M2) x M3) x M4). The minimum number is (1 x 2 x 3) + (1 x 3 x 4) + (1 x 4 x 3) = 30.
Input: arr[] = [3, 4]
Output: 0
Explanation: As there is only one matrix so, there is no cost of multiplication.
Constraints: 
2 ≤ arr.size() ≤ 100
1 ≤ arr[i] ≤ 500
 */

public class _03_Matrix_Chain_Multiplication {
    public static void main(String[] args) {
        int arr[] = { 2, 1, 3, 4 };
        /*
         * FORMULA:
         * dp[i][j]=min(dp[i][k]+dp[k+1][j]+{arr[i-1]+arr[k]+arr[j]}), where
         * [1<=i<N],[N>j>=i],[i<=k<j]
         */

        // System.out.println(mem(arr, 1, arr.length - 1,new
        // int[arr.length][arr.length]));
        System.out.println(tab(arr) );
    }

    // -----------------------------recursion:rec(arr, 1, arr.length - 1));
    static int rec(int arr[], int i, int j) {
        if (i == j)
            return 0;
        int min = 1_000__000_000;
        for (int k = i; k < j; k++) {
            int nSteps = (arr[i - 1] * arr[k] * arr[j]) + rec(arr, i, k) + rec(arr, k + 1, j);
            min = Math.min(min, nSteps);
        }
        return min;
    }

    // -----------------------mem
    static int mem(int arr[], int i, int j, int dp[][]) {
        if (i == j)
            return (dp[i][j] = 0);
        if (dp[i][j] != -1)
            return dp[i][j];
        int min = 1_000__000_000;
        for (int k = i; k < j; k++) {
            int nSteps = (arr[i - 1] * arr[k] * arr[j]) + mem(arr, i, k, dp) + mem(arr, k + 1, j, dp);
            min = Math.min(min, nSteps);
        }
        return (dp[i][j] = min);
    }

    // --------------tab
    static int tab(int arr[]) {// O(n^3)
        int n = arr.length;
        int dp[][] = new int[n][n];
        for (int i = n - 1; i > 0; i--) {// in mem, we moved i from 1 to n, so here move revrese
            for (int j = i + 1; j < n; j++) {// in mem, j moved from n-1 to i, so here revrese
                int min = 1_000__000_000;
                dp[i][j] = min;
                for (int k = i; k < j; k++) {
                    int nSteps = (arr[i - 1] * arr[k] * arr[j]) + dp[i][k] + dp[k + 1][j];
                    min = Math.min(min, nSteps);
                }
                dp[i][j] = min;
            }
        }
        // for(int r[]:dp)System.out.println(Arrays.toString(r));
        return dp[1][n - 1];// represents min num of opr to multiply mat1....mat4, so return dp[1][n-1]
    }

    // -------------------print mimimal parenthesised partiton:(See OOPS version for impl), idea: build tree, print tree

    // -----------------------------OPERATIONS USING OOPS:
    protected static class MyMat {
        int nRows;
        int nCols;
        int cost;

        //ONLY for printing chain purpose, we will treat it as tree
        MyMat left;
        MyMat right;

        public MyMat(int nRows, int nCols, int cost,MyMat left,MyMat right) {
            this.nRows = nRows;
            this.nCols = nCols;
            this.cost = cost;
            this.left=left;
            this.right=right;
        }

        public int cost_on_multiplication_with(MyMat other) {
            return (this.cost + other.cost + (this.nRows * this.nCols * other.nCols));
        }

        public MyMat multiply(MyMat other) {
            MyMat newMat = new MyMat(this.nRows, other.nCols, cost_on_multiplication_with(other),null,null);
            return newMat;
        }

        @Override
        public String toString() {
            return ("[" + nRows + "X" + nCols + "]" + " cost to obtain this matrix = " + cost);
        }

        static MyMat get_final_optimal_matrix_recursion(int arr[],int i, int j){

            if(i==j) return new MyMat(arr[i-1], arr[i], 0,null,null);

            MyMat minMat=new MyMat(0, 0, 1_000__000_000,null,null);

            for(int k=i;k<j;k++){
                MyMat first_part=get_final_optimal_matrix_recursion(arr, i, k);
                MyMat second_part=get_final_optimal_matrix_recursion(arr, k+1, j);
                MyMat res=first_part.multiply(second_part);
                if(minMat.cost>res.cost){
                    minMat=res;
                    res.left=first_part;
                    res.right=second_part;
                }
            }
            return minMat;
        }

        static String print_chain_with_dimensions(MyMat m){
            if(m.left==null && m.right==null){
                return(m.nRows+"X"+m.nCols);
            }
            return new StringBuilder()
            .append("(")
            .append(print_chain_with_dimensions(m.left))
            .append("*")
            .append(print_chain_with_dimensions(m.right))
            .append(")")
            .toString();
        }

        private static  char _ch_='A';
        static String print_chain_with_chars(MyMat m){
            if(m.left==null && m.right==null){
                String leaf=_ch_+"";
                _ch_++;
                return leaf;
            }
            return new StringBuilder()
            .append("(")
            .append(print_chain_with_chars(m.left))
            .append("*")
            .append(print_chain_with_chars(m.right))
            .append(")")
            .toString();
        }


        public static void main(String[] args) {    

            int arr[]={2,1,3,4};
            MyMat res=get_final_optimal_matrix_recursion(arr, 1, arr.length-1);
           System.out.println( MyMat.print_chain_with_dimensions(res));
           System.out.println(MyMat.print_chain_with_chars(res));

        }


    }

}
