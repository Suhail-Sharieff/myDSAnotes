package _6_DynamicProgramming._05_Stocks;


//----------------------MAX 2 STOCKS ALLOWED
public class _03_part_03{
    /*
 You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 

Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 105   
 */
//-------method which i inially thought of , using the printing transactions logic, getting all [buy,sell] pairs possible , sorting them ac to diff in some listand returning sum of max 2 ie https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/submissions/1528909531/
//but it wn pass all Tcs coz for ex:
/*
for ex: prices;[1,2,4,2,5,7,2,4,9,0]
If there won't be the two buy/sale pairs constrain, one can do:(as per my logic )
[1,2,4,2,5,7,2,4,9,0]
Buy 1 sell 4 Profit 3
Buy 2 sell 7 profit 5
buy 2 sell 9 profit 7
Total profit: 15

But correct logic:
With two buy/sell pairs best is:
Buy 1 sell 7 profit 6
buy 2 sell 9 profit 7
Total: 13

The text is amendable. Two transactions means one buy and one sell. Not necessary in the same order, as there is a "short sell" transaction too. [10,1] would give me a profit of 9 by doing: short sell first day, price 10, buy back day 1 price 1.
I would write it as: "at most two buy and two sell transactions".
 */
//-------------------recursion:
//call:return rec(prices,0,true,0);
    static int rec(int prices[],int i,boolean in_buying_state,int nChosen){
        if(i==prices.length) return 0;
        int max=0;
       if ( nChosen<=2) {
         if (in_buying_state) {
             int buy_now=rec(prices, i+1, false, nChosen+1)-prices[i];
             int dont_buy_now=rec(prices, i+1, true, nChosen);
             max=Math.max(buy_now, dont_buy_now);
         }else{
             int sell_now=rec(prices, i+1, true, nChosen)+prices[i];
             int dont_sell_now=rec(prices, i+1, false, nChosen);
             max=Math.max(sell_now, dont_sell_now);
         }
       }
        // System.out.print(nChosen+"->");
        return max;
    }
    //-------------mem 3 changing states, so use 3d dp
    public int mem(int prices[], int i, int in_buying_state, int nChosen, int dp[][][]) {
        if (i == prices.length)
            return 0;

        int max = 0;
        if (nChosen <= 2) {
            if (dp[i][in_buying_state][nChosen] != -1)
                return dp[i][in_buying_state][nChosen];
            if (in_buying_state == 1) {
                int buy_now = mem(prices, i + 1, 0, nChosen + 1, dp) - prices[i];
                int dont_buy_now = mem(prices, i + 1, 1, nChosen, dp);
                max = Math.max(buy_now, dont_buy_now);
            } else {
                int sell_now = mem(prices, i + 1, 1, nChosen, dp) + prices[i];
                int dont_sell_now = mem(prices, i + 1, 0, nChosen, dp);
                max = Math.max(sell_now, dont_sell_now);
            }
            dp[i][in_buying_state][nChosen] = max;
        }
        // System.out.print(nChosen+"->");

        return max;
    }
}

