package _6_DynamicProgramming._01_1D;

public class _10_best_time_to_buy_and_sell_stocks {

    public static void main(String[] args) {
        // Part1.maxProfit(new int[]{7,1,5,3,6,4});
        Part2.printTransactions(new int[]{7,1,5,3,6,4});
    }
}

//----------------------------ONLY 1 STOCK
class Part1{
    /*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

 

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
 

Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 104
     */

    static int maxProfit(int[] prices) {
        int min=Integer.MAX_VALUE,ans=Integer.MIN_VALUE;
        for(int e:prices){
            min=Math.min(min,e);
            ans=Math.max(ans,e-min);
        }
        System.out.println(ans);
        return ans;
    }
}

//--------------------------------MULTIPLE STOCKS
class Part2{
    /*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.

 

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 

Constraints:

1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104

     */
        //------------------METHOD 1: GREEDY
     public int greedy(int nums[]){
        int sum=0;
        for(int i=1;i<nums.length;i++){
            sum+=Math.max(0,nums[i]-nums[i-1]);
        }
        return sum;
     }
     //------------------------METHOD 2: 2 pointer solution(my soln)

     //---------------recursion 0...n , initially keep i at 0 and j at 1, try selling stock or not selling stock, if u obtain max value by selling, mark that day as chosen [coz given that we can hold max 1 stock at a time, so to avoid multiple stocks using same day to sell i have used isChosen array], other option is to moe forward without choosing
    //call: rec(nums,0,1,isChosen[nums.length])
    public int rec_2_pointer(int nums[],int i,int j,boolean isChosen[]){
        if(j==nums.length-1) return Math.max(nums[j]-nums[i],0);
        int take=(!isChosen[j])?rec_2_pointer(nums,i+1,j+1,isChosen)+Math.max(0,nums[j]-nums[i]):0;
        int dont_take=rec_2_pointer(nums,i,j+1,isChosen);
        int max = Math.max(take,dont_take);
        if(max==take) isChosen[j]=true;
        return max;
    }
     //----------------mem 0...n : 2 variables
    //call: mem3(nums,0,nums.length-1,sChosen[nums.length],dp[nums.length][nums.length])
    public int mem1(int nums[],int i,int j,boolean isChosen[],int dp[][]){
        if(j==nums.length-1) return Math.max(nums[j]-nums[i],0);
        if(dp[i][j]!=-1) return dp[i][j];
        int take=(!isChosen[j])?mem1(nums,i+1,j+1,isChosen,dp)+Math.max(0,nums[j]-nums[i]):0;
        int dont_take=mem1(nums,i,j+1,isChosen,dp);
        int max = Math.max(take,dont_take);
        if(max==take) isChosen[j]=true;
        return max;
    }
    //------------memo 0....n : 1 variable
    //call: mem(nums,0,nums.length-1,isChosen[nums.length],dp[nums.length])
    public int mem2(int nums[],int i,int j,boolean isChosen[],int dp[]){
        if(j==nums.length-1) return Math.max(nums[j]-nums[i],0);
        if(dp[i]!=-1) return dp[i];
        int take=(!isChosen[j])?mem2(nums,i+1,j+1,isChosen,dp)+Math.max(0,nums[j]-nums[i]):0;
        int dont_take=mem2(nums,i,j+1,isChosen,dp);
        int max = Math.max(take,dont_take);
        if(max==take) isChosen[j]=true;
        dp[i]=max;
        return max;
    }


    //-------------memo n....0 : 1 variable
    //call : mem2(nums,nums.length,nums.length-1,isChosen[nums.length],dp[nums.length+1])
    public int mem3(int nums[],int i,int j,boolean isChosen[],int dp[]){
        if(j==1) return Math.max(nums[i-1]-nums[j-1],0);
        if(dp[j]!=-1) return dp[j];
        int take=(!isChosen[i-1])?mem3(nums,i-1,j-1,isChosen,dp)+Math.max(0,nums[i-1]-nums[j-1]):0;
        int dont_take=mem3(nums,i,j-1,isChosen,dp);
        int max = Math.max(take,dont_take);
        if(max==take) isChosen[i-1]=true;
        dp[j]=max;
        return max;
    }
   
    //---------------mem n.....0 : 2 variables
    //call: mem4(nums,nums.length,nums.length-1,isChosen[nums.length],dp[nums.length+1][nums.length+1])
     public int mem4(int nums[],int i,int j,boolean isChosen[],int dp[][]){
        if(j==1) return Math.max(nums[i-1]-nums[j-1],0);
        if(dp[i][j]!=-1) return dp[i][j];
        int take=(!isChosen[i-1])?mem4(nums,i-1,j-1,isChosen,dp)+Math.max(0,nums[i-1]-nums[j-1]):0;
        int dont_take=mem4(nums,i,j-1,isChosen,dp);
        int max = Math.max(take,dont_take);
        if(max==take) isChosen[i-1]=true;
        dp[i][j]=max;
        return max;
    }
    //--tabulation is hard for this , im moving to next method:

    //----------------------------------------------------------METHOD 3 : just 1 pointer
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
    //call:rec(prices,0,true);
    public int rec_1_pointer(int prices[],int i,boolean in_buying_state){
        if(i==prices.length) return 0;
        int max=0;
        if(in_buying_state){
            int buy_now=rec_1_pointer(prices,i+1,false)-prices[i];//ur money will reduce by prices[i]
            int dont_buy_now=rec_1_pointer(prices,i+1,true);
            max=Math.max(buy_now,dont_buy_now);
        }else{
            int sell_now=rec_1_pointer(prices,i+1,true)+prices[i];//ur seling, so ur mmoney will increase by price[i]
            int dont_sell_now=rec_1_pointer(prices,i+1,false);
            max=Math.max(sell_now,dont_sell_now);
        }
        return max;
    }
    //------------memo(just made boolean as int since i will use dp array)
    public int mem5(int prices[],int i,int in_buying_state,int dp[][]){
        if(i==prices.length) return 0;
        if(dp[i][in_buying_state]!=-1) return dp[i][in_buying_state];
        int max=0;
        if(in_buying_state==1){
            int buy_now=mem5(prices,i+1,0,dp)-prices[i];//ur money will reduce by prices[i]
            int dont_buy_now=mem5(prices,i+1,1,dp);
            max=Math.max(buy_now,dont_buy_now);
        }else{
            int sell_now=mem5(prices,i+1,1,dp)+prices[i];//ur seling, so ur mmoney will increase by price[i]
            int dont_sell_now=mem5(prices,i+1,0,dp);
            max=Math.max(sell_now,dont_sell_now);
        }
        dp[i][in_buying_state]=max;
        return max;
    }
    //-------------------------------tab
    public int tab(int prices[]){//observe that we move from last row
        int dp[][]=new int[prices.length+1][2];
        for(int i=prices.length-1;i>=0;i--){
            int max=0;
            for(int in_buying_state=0;in_buying_state<=1;in_buying_state++){
                if(in_buying_state==1){
                    int buy_now=dp[i+1][0]-prices[i];
                    int dont_buy_now=dp[i+1][1];
                    max=Math.max(buy_now,dont_buy_now);
                }else{
                    int sell_now=dp[i+1][1]+prices[i];
                    int dont_sell_now=dp[i+1][0];
                    max=Math.max(sell_now,dont_sell_now);
                }
                dp[i][in_buying_state]=max;
            }
        }
        return dp[0][1];
        //alternate loop:
        /*
         for (int i = n - 1; i >= 0; i--) {
            dp[i][1] = Math.max(-prices[i] + dp[i+1][0], dp[i+1][1]);
            dp[i][0] = Math.max(prices[i] + dp[i+1][1], dp[i+1][0]);
        }
         */
    }
    //---------------printOperations,very easy
    static void printTransactions(int prices[]){
        //build dp table:
        //(im using alternate for loop as described above)
        int dp[][]=new int[prices.length+1][2];
        for(int i=prices.length-1;i>=0;i--){
            //in selling state
            dp[i][0]=Math.max(dp[i+1][1]+prices[i], dp[i+1][0]);
            //in buying state
            dp[i][1]=Math.max(dp[i+1][0]-prices[i], dp[i+1][1]);
        }
        // // Start at day 0 with the in_buying_state "can buy" (i.e. not holding any stock).
        int i=0;
        int in_buying_state=1;
        while (i<prices.length) {
            if (in_buying_state==1) {
                if(dp[i+1][0]-prices[i]>=dp[i+1][1]){
                    System.out.print("Buy at day " + i + " (price: " + prices[i] + ")->");
                    in_buying_state = 0;  // after buying, switch to holding in_buying_state.
                    i++;      // move to the next day after action.
                    continue;
                }
            }else{
                if(dp[i+1][1]+prices[i]>=dp[i+1][0]){
                    System.out.println("Sell at day " + i + " (price: " + prices[i] + ")");
                    in_buying_state = 1;  // after buying, switch to holding in_buying_state.
                    i++;      // move to the next day after action.
                    continue;
                }
            }
            // If no action was taken at day i, move to the next day.
            i++;
        }
        System.out.println("NET PROFIT :"+dp[0][1]);
    }
    //-----------spcae optimization, replace dp[i+1] with  prev and dp[i] as curr
    public int space_optimal(int prices[]){
        int prev[]=new int[2];
        for(int i=prices.length-1;i>=0;i--){
            int max=0;
            int curr[]=new int[2];
            for(int in_buying_state=0;in_buying_state<=1;in_buying_state++){
                if(in_buying_state==1){
                    int buy_now=prev[0]-prices[i];
                    int dont_buy_now=prev[1];
                    max=Math.max(buy_now,dont_buy_now);
                }else{
                    int sell_now=prev[1]+prices[i];
                    int dont_sell_now=prev[0];
                    max=Math.max(sell_now,dont_sell_now);
                }
                curr[in_buying_state]=max;
            }
            prev=curr.clone();
        }
        return prev[1];
    }

}