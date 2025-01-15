package _6_DynamicProgramming._01_1D;

//https://www.youtube.com/watch?v=H9bfqozjoqs&t=448s&ab_channel=NeetCode
import java.util.*;
/*
Consider a money system consisting of n coins. Each coin has a positive integer value. Your task is to produce a sum of money x using the available coins in such a way that the number of coins is minimal.
For example, if the coins are \{1,5,7\} and the desired sum is 11, an optimal solution is 5+5+1 which requires 3 coins.
Input
The first input line has two integers n and x: the number of coins and the desired sum of money.
The second line has n distinct integers c_1,c_2,....,c_n: the value of each coin.
Output
Print one integer: the minimum number of coins. If it is not possible to produce the desired sum, print -1.
Constraints

1 <=n <= 100
1 <= x <= 10^6
1 <= c_i <= 10^6

Example
Input:
3 11
1 5 7

Output:
3
 */

public class _01_coinCount {

    public static void main(String[] args) {
        // int nOfCoins=3;
        int target = 5;
        int arr[] = { 1, 3, 4 };

        showWhichCoinsweUse(arr, target, new HashMap<>());
        System.out.println();
        // nOf such ways for ex for above target and arr
        // [[1,1,1,1,1],[1,1,3],[1,3,1],[3,1,1],[1,4],[4,1]]
        System.out.println(cnt(arr, target));

    }

    //*********************************************METHOD 1 : USING 2D DP */

    //-------------------recursion (method1):
    public static int recursion1(int coins[],int target,int idx){
        if(target<0 || idx<0) return Integer.MAX_VALUE;
        if(target==0) return 0;

        int pick=recursion1(coins,target-coins[idx],idx);///VVVVVVVIMP, observe that we havent done idx-1 here coz then it would mean that we cant reuse that coin again and again-----MOST IMP
        int dontPick=recursion1(coins,target,idx-1);
        if(pick!=Integer.MAX_VALUE) pick++;//that con+pick value ie 1+pick needs to be done
        
        return Math.min(dontPick,pick);
    }

    //----------------memoization for above code: uses 2D DP, since only idx and target states are changing
    public static int memo1(int coins[],int target,int dp[][],int idx){
        if(target<0 || idx<0) return Integer.MAX_VALUE;
        if(target==0) return 0;

        if(dp[idx][target]!=-1) return dp[idx][target];

        int pick=memo1(coins, target-coins[idx], dp, idx);
        int dontPick=memo1(coins, target, dp, idx-1);
        if(pick!=Integer.MAX_VALUE) pick++;

        return dp[idx][target]=Math.min(pick, dontPick);
    }

    //-----------------------tabulation for above code:
    public static int tabulate1(int coins[],int target){
        int dp[][]=new int[coins.length][target+1];
        for(int row[]:dp) Arrays.fill(row,Integer.MAX_VALUE);

        for(int row[]:dp) row[0]=0;

        //handling 1st row, where we can use only  coin number 1, then target  can be met iff target%thatCoin is 0---------------VVIMP
        for(int tar=1;tar<=target;tar++){
            if(tar%coins[0]==0) dp[0][tar]=tar/coins[0];
        }

        for(int idx=1;idx<coins.length;idx++){
            for(int currTar=1;currTar<=target;currTar++){
                int pick=(currTar>=coins[idx])?dp[idx][currTar-coins[idx]]:Integer.MAX_VALUE;
                int dontPick=dp[idx-1][currTar];
                if(pick!=Integer.MAX_VALUE) pick++;
                dp[idx][currTar]=Math.min(pick,dontPick);
            }    
        }    
        int ans=dp[coins.length-1][target];
        return (ans!=Integer.MAX_VALUE)?ans:-1;
    }



     //*********************************************METHOD 2 : USING 1D DP */

    //---------------------recursion(method 2)
    public static int recursion2(int coins[],int target){
        if(target<0) return Integer.MAX_VALUE;
        if(target==0) return 0;
        int min=Integer.MAX_VALUE;
        for(int coin:coins){
            int subRes=recursion2(coins,target-coin);
            if(subRes!=Integer.MAX_VALUE) min=Math.min(min,1+subRes);
        }
        return min;
    }
    //----------------memoization for above code: uses 1D DP, since only target value is changing:
    public static int memo2(int coins[],int target,int dp[]){
        if(target<0) return Integer.MAX_VALUE;
        if(target==0) return 0;
        if(dp[target]!=-1) return dp[target];
        int min=Integer.MAX_VALUE;
        for(int coin:coins){
            int subRes=memo2(coins,target-coin,dp);
            if(subRes!=Integer.MAX_VALUE) min=Math.min(min,1+subRes);
        }
        dp[target]=min;
        return min;
    }
    //----------------tabulation for above code:
    public static int tabulate2(int coins[],int target){
        int dp[]=new int[target+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        
        dp[0]=0;

        for(int currTar=1;currTar<=target;currTar++){
            int min=Integer.MAX_VALUE;
            for(int coin:coins){
                int subRes=(currTar-coin>=0)?dp[currTar-coin]:Integer.MAX_VALUE;
                if(subRes!=Integer.MAX_VALUE) min=Math.min(min,subRes+1);
            }
            dp[currTar]=min;
        }
        int ans=dp[target];

        return (ans!=Integer.MAX_VALUE)?ans:-1;
    }




    // now suppose we also wwant what coins we r choosing
    static int showWhichCoinsweUse(int coins[], int target, HashMap<Integer, Integer> hp) {
        // call like: System.out.println(showWhichCoinsweUse(coins, target,new
        // HashMap<>()));
        if (target == 0)
            return 0;
        int dp[] = new int[target + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int currTar = 1; currTar <= target; currTar++) {
            int ans = Integer.MAX_VALUE;
            for (int coinChosen : coins) {
                if (currTar - coinChosen >= 0 && dp[currTar - coinChosen] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, dp[currTar - coinChosen] + 1);
                    hp.put(currTar, coinChosen);
                }
            }
            dp[currTar] = ans;
        }
        while (target > 0) {
            System.out.print("For " + target + " we use coin:" + hp.get(target) + ", remaining is "
                    + (target - hp.get(target)) + " -> ");
            target -= hp.get(target);
        }
        return dp[target];
    }

    // ------------------TASK2: Count noOf Ways to of such
    // solutions-------------------------------------

    public int rec(int coins[], int target, int idx) {
        if (target == 0)
            return 1;
        if (target < 0 || idx == coins.length)
            return 0;
        // Below line is vvvvvimp, observe that we dont have to incremnet idx by 1 if
        // choosed
        return rec(coins, target - coins[idx], idx) + rec(coins, target, idx + 1);
    }

    public static int cnt(int coins[], int sum) {
        // Create a DP array to store the number of ways to make each sum
        int dp[] = new int[sum + 1];

        // Base case: There's 1 way to make a sum of 0 (by using no coins)
        dp[0] = 1;

        // Iterate over each coin
        for (int coin : coins) {
            // Update dp array for all sums >= coin
            for (int currTar = coin; currTar <= sum; currTar++) {
                dp[currTar] += dp[currTar - coin];
            }
        }

        // The result is the number of ways to make the target sum
        return dp[sum];
    }

}
