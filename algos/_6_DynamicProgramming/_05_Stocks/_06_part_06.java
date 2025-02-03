package _6_DynamicProgramming._05_Stocks;
/*
You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

Note:

You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
The transaction fee is only charged once for each stock purchase and sale.
 

Example 1:

Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Example 2:

Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6
 

Constraints:

1 <= prices.length <= 5 * 104
1 <= prices[i] < 5 * 104
0 <= fee < 5 * 104
 */
public class _06_part_06 {
    //----------------rec
    public int rec(int prices[],int i,boolean in_buying_state,int fee){
        if(i==prices.length) return 0;
        if(in_buying_state){
            return Math.max(
                rec(prices,i+1,false,fee)-prices[i],
                rec(prices,i+1,true,fee)
            );
        }
        return Math.max(
            rec(prices,i+1,true,fee)+prices[i]-fee,//change here
            rec(prices,i+1,false,fee)
        );
    }
    //-------------------meme
    public int mem(int prices[],int i,int in_buying_state,int fee,int dp[][]){
        if(i==prices.length) return 0;
        if(dp[i][in_buying_state]!=-1) return dp[i][in_buying_state];
        if(in_buying_state==1){
            dp[i][in_buying_state]= Math.max(
                mem(prices,i+1,0,fee,dp)-prices[i],
                mem(prices,i+1,1,fee,dp)
            );
        }
        else dp[i][in_buying_state] =Math.max(
            mem(prices,i+1,1,fee,dp)+prices[i]-fee,
            mem(prices,i+1,0,fee,dp)
        );

        return dp[i][in_buying_state];
    }
    //--------------tab
    public int tab(int prices[],int fee){
        int dp[][]=new int[prices.length+1][2+1];
        for(int i=prices.length-1;i>=0;i--){
            dp[i][1]= Math.max(
                dp[i+1][0]-prices[i],
                dp[i+1][1]
            );
            dp[i][0]=Math.max(
                dp[i+1][1]+prices[i]-fee,
                dp[i+1][0]
            );
        }
        return dp[0][1];
    }
}
