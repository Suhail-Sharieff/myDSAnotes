package some_preq._01_Queries;

//https://www.youtube.com/watch?v=nZe7P674xZo&list=PLauivoElc3ggagradg8MfOZreCMmXMmJ-&index=20&ab_channel=Luv

public class _01_query_SUM {

    protected class _1_D{
         //Q: Given a 1D array, given "Q" queries each has 'L' and 'R', for each Query return Sum(a[l]+a[l+1]...a[r])
        public  void main(String[] args) {
            int nums[]={1,2,3,4,5,6,7,8};
            int queries[][]={{1,4},{2,3}};//1 based indexing
            //precompute prefix sums
            int dp[]=new int[nums.length+1];
            for(int i=1;i<=nums.length;i++){
                dp[i]=dp[i-1]+nums[i-1];//use 1 based indexing always
            }
            for(int q[]:queries){
                System.out.println("Sum from "+q[0]+" to "+q[1]+" is "+(dp[q[1]]-dp[q[0]-1]));
            }
        }
    }

    static class _2_D{
        //Q: Given a 2D array, given "Q" queries each has (a,b) and (c,d), for each Query return SumRectangle formed between the points (a,b) and (c,d)
        public static void main(String[] args) {
            int nums[][]={
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
            };
            int queries[][]={{1,1,2,2},{2,1,3,2}};
            //precompute
            //pref[i][j] has sum from (0,0) to (i,j) rectangle
            int dp[][]=new int[nums.length+1][nums[0].length+1];//always use 1 based to avoid edge case handling
            for(int i=1;i<=nums.length;i++){
                for(int j=1;j<=nums[0].length;j++){
                    dp[i][j]=nums[i-1][j-1]+dp[i-1][j]+dp[i][j-1]-dp[i-1][j-1];
                }
            }
            // Arrays.stream(pref).forEach(x->System.out.println(Arrays.toString(x)));
            for(int q[]:queries){
                int a=q[0],b=q[1];
                int c=q[2],d=q[3];
                System.out.println("Sum of rectangle formed between "+"["+q[0]+","+q[1]+"] and ["+q[2]+","+q[3]+"] is "+
                    (dp[c][d] - dp[a-1][d] - dp[c][b-1] + dp[a-1][b-1])
                );
            }
        }
    }

    public static void main(String[] args) {
        
    }

   
}