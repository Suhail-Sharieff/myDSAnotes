package _6_DynamicProgramming._05_Stocks;

public
// --------------STOCKS WITH COOLDOWN
class _05_part_05 {
    /*
     * Stock Buy and Sell – with Cooldown
     * Difficulty: MediumAccuracy: 47.04%Submissions: 7K+Points: 4
     * You are given the prices of stock for n number of days. every ith day tell
     * the price of the stock on that day. find the maximum profit that you can make
     * by buying and selling stock with the restriction of After you sell your
     * stock, you cannot buy stock on the next day (i.e., cooldown one day).
     * 
     * Example:
     * Input:
     * 5
     * 1 2 3 0 2
     * Output:
     * 3
     * Explanation:
     * You buy on 1st day and sell on the second day then cooldown, then buy on the
     * fourth day and
     * sell on the fifth day and earn a profit of 3.
     * Your Task:
     * You don't have to read input or print anything. Your task is to complete the
     * function maximumProfit() which takes the integer n and array prices and
     * returns the maximum profit that can earn.
     * 
     * Expected Time Complexity: O(n)
     * Expected Space Complexity: O(n)
     * 
     * Constraint:
     * 1<=n<=105
     * 1<=prices[i]<=105
     * 
     * 
     */
    // ans: whenver u sell, do i+2, rather than i+1 as previous problems, meaning
    // that move 2 steps ahead rather than 1 step coz given that after we sold
    // today, we cant buy tomorrow, and also replace == by >= in base case, coz i+2
    // we r doing and it can exceed prices.length
    // --------recursion:
    public long rec(long prices[], int i, boolean in_buying_state) {
        if (i >= prices.length)
            return 0l;
        long max = 0l;
        if (in_buying_state) {
            long buy_now = rec(prices, i + 1, false) - prices[i];
            long dont_buy_now = rec(prices, i + 1, true);
            max = Math.max(buy_now, dont_buy_now);
        } else {
            long sell_now = rec(prices, i + 2, true) + prices[i];// change here
            long dont_sell_now = rec(prices, i + 1, false);
            max = Math.max(sell_now, dont_sell_now);
        }
        return max;
    }

    public long mem(long prices[], int i, int in_buying_state, long dp[][]) {

        if (i >= prices.length)
            return 0l;
        if(dp[i][in_buying_state]!=-1) return dp[i][in_buying_state];
        long max = 0l;
        if (in_buying_state == 1) {
            long buy_now = mem(prices, i + 1, 0, dp) - prices[i];
            long dont_buy_now = mem(prices, i + 1, 1, dp);
            max = Math.max(buy_now, dont_buy_now);
        } else {
            long sell_now = mem(prices, i + 2, 1, dp) + prices[i];
            long dont_sell_now = mem(prices, i + 1, 0, dp);
            max = Math.max(sell_now, dont_sell_now);
        }
        dp[i][in_buying_state]=max;
        return max;
    }

    public long tab(long prices[]){
        long dp[][]=new long[prices.length+2][3];
        for(int i=prices.length-1;i>=0;i--){
            dp[i][1]=Math.max(
                dp[i+1][0]-prices[i],
                dp[i+1][1]
            );
            dp[i][0]=Math.max(
                dp[i+2][1]+prices[i],
                dp[i+1][0]
            );
        }
        return dp[0][1];
    }

   
}
