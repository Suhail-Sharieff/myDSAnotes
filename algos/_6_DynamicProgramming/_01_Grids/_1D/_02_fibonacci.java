package _6_DynamicProgramming._01_Grids._1D;


public class _02_fibonacci {
    public static void main(String[] args) {
        /*
        memeoization
        int dp[]=new int[101];//lets call our memoized array as dp , also assume that this can compute upto 100th fib numbre
        // to print all fibo from 1 to 10
        for (int i = 0; i < 10; i++) {
            System.out.print(memo(i,dp)+" ");
        }
        //nth fibo
        System.out.println(memo(3, dp));
        */
        //

        //tabulation
        // tabulation(10);

        //optimal
        optimal(10);

        
    }

    //--------------------memeoization O(n)--O(n)
    public static int memo(int n,int dp[]){
        if (n<=1) {
            dp[n]=n;
           return dp[n];
        }
        if (dp[n]!=0) {
            return dp[n];
        }
        dp[n]=memo(n-1, dp)+memo(n-2, dp);
        return dp[n];
    }

    //------------tabulation:O(n)--O(n)--faster than recursion
    public static void tabulation(int n){
        int dp[]=new int[n+1];

        dp[0]=0;
        dp[1]=1;

        for (int i = 2; i < n; i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }

        //printing n fibo
        for (int i = 0; i <= n; i++) {
            System.out.print(dp[i]+" ");
        }
        
    }


    //---------optimal: O(n)--O(1)
    public static void optimal(int n){
        int n1=0,n2=1;
        for (int i = 2; i <=n; i++) {
            int curr=n1+n2;
            System.out.print(curr+" ");
            n1=n2;
            n2=curr;
        }
    }
    
}
