package _6_DynamicProgramming._05_Stocks;
//preq: part3

//-------------------ATMOST K Transactions
public class _04_part_04{
    /*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 

Constraints:

1 <= k <= 100
1 <= prices.length <= 1000
0 <= prices[i] <= 1000
     */
    //logic as same as for atmost 2 transactions:
    // return rec(prices, 0, 1, 0,k);
    public int rec(int prices[],int i,boolean in_buying_state,int nChosen,int k){
        if(i==prices.length) return 0;
        int max=0;
       if ( nChosen<=k) {
         if (in_buying_state) {
             int buy_now=rec(prices, i+1, false, nChosen+1,k)-prices[i];
             int dont_buy_now=rec(prices, i+1, true, nChosen,k);
             max=Math.max(buy_now, dont_buy_now);
         }else{
             int sell_now=rec(prices, i+1, true, nChosen,k)+prices[i];
             int dont_sell_now=rec(prices, i+1, false, nChosen,k);
             max=Math.max(sell_now, dont_sell_now);
         }
       }
        return max;
    }
    //-----------mem..make sure dp[prices.lenght][2][k+1]
    public int mem(int prices[], int i, int in_buying_state, int nChosen, int dp[][][],int k) {
        if (i == prices.length)
            return 0;

        int max = 0;
        if (nChosen <= k) {
            if (dp[i][in_buying_state][nChosen] != -1)
                return dp[i][in_buying_state][nChosen];
            if (in_buying_state == 1) {
                int buy_now = mem(prices, i + 1, 0, nChosen + 1, dp,k) - prices[i];
                int dont_buy_now = mem(prices, i + 1, 1, nChosen, dp,k);
                max = Math.max(buy_now, dont_buy_now);
            } else {
                int sell_now = mem(prices, i + 1, 1, nChosen, dp,k) + prices[i];
                int dont_sell_now = mem(prices, i + 1, 0, nChosen, dp,k);
                max = Math.max(sell_now, dont_sell_now);
            }
            dp[i][in_buying_state][nChosen] = max;
        }
        return max;
    }
    //---------------tab:MISTAKE I DID: reversed outer and ineer loops
    public int tab(int k, int[] prices) {
       int dp[][][]=new int[prices.length+1][2][k+2];
       for(int i=prices.length-1;i>=0;i--){//make sure this is OUTER LOOP____VVVIMP
        for(int nChosen=k;nChosen>=0;nChosen--){
            dp[i][1][nChosen]=Math.max(
                dp[i+1][0][nChosen+1]-prices[i],
                dp[i+1][1][nChosen]
            );
            dp[i][0][nChosen]=Math.max(
                dp[i+1][1][nChosen]+prices[i],
                dp[i+1][0][nChosen]
            );
        }
    }
       // for(int e:dp[0][1])System.out.println(e);
       return dp[0][1][0];
   }
   //-----------space optimize
   public int space_optimal(int prices[],int k){
    int prev [][]=new int[2][k+2];//dp[i+1]
    for(int i=prices.length-1;i>=0;i--){
        int curr[][]=new int[2][k+2];
        for(int nChosen=k;nChosen>=0;nChosen--){
            curr[1][nChosen]=Math.max(
                prev[0][nChosen+1]-prices[i],
                prev[1][nChosen]
            );
            curr[0][nChosen]=Math.max(
                prev[1][nChosen]+prices[i],
                prev[0][nChosen]
            );
        }
        prev=curr.clone();
    }
    // for(int r[]:prev)System.out.println(Arrays.toString(r));
    return prev[1][0];
   }
}

