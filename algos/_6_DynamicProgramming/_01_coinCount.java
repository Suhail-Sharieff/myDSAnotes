package _6_DynamicProgramming;

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
        int arr[] = { 1, 3,4 };
       
        
        showWhichCoinsweUse(arr, target,new HashMap<>());
        System.out.println();
        //nOf such ways for ex for above target and arr [[1,1,1,1,1],[1,1,3],[1,3,1],[3,1,1],[1,4],[4,1]]
        System.out.println(cnt(arr, target, new int[target+1], new int[target+1]));

    }

    public static void brute(int nums[], int target, int start, List<Integer> empty, int ans[]) {// but still it may not
                                                                                                 // pass 1 or 2 TC
        if (start >= nums.length) {
            if (target == 0) {
                ans[0] = Math.min(ans[0], empty.size());
            }
            return;
        }
        if (nums[start] <= target) {
            empty.add(nums[start]);
            brute(nums, target - nums[start], start, empty, ans);
            empty.remove(empty.size() - 1);
        }
        brute(nums, target, start + 1, empty, ans);
    }

    public static int rec(int coins[],int target,int dp[]){
        //call like: int dp[]=new int[amount+1];return rec(coins,amount,dp);
        if(target==0) return 0;
        if(target<0) return -1;
        if(dp[target]!=0) return dp[target];
        int min=Integer.MAX_VALUE;
        for(int coin : coins){
            int subRes=rec(coins,target-coin,dp);
            if(subRes!=-1){
                min=Math.min(min,subRes+1);
            }
        }
        dp[target]=(min==Integer.MAX_VALUE)?(-1):(min);
        return dp[target];
    }

    // iterative solutions

    public int tabulation(int coins[],int target){
        if(target==0) return 0;
        int dp[]=new int[target+1];
        //dp[i] represnts minimum number of coins needed to reach target i
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int currTarget=1 ; currTarget<=target ; currTarget++ ){
            for(int coin : coins){
               if(currTarget-coin>=0 && dp[currTarget-coin]!=Integer.MAX_VALUE){
                 dp[currTarget]=Math.min(dp[currTarget],1+dp[currTarget-coin]);
               }
            }
            
        }
        return dp[target]==Integer.MAX_VALUE?-1:dp[target];
    }

    // now suppose we also wwant what coins we r choosing
    static  int showWhichCoinsweUse(int coins[],int target,HashMap<Integer,Integer>hp){
        //call like: System.out.println(showWhichCoinsweUse(coins, target,new HashMap<>()));
        if(target==0) return 0;
        int dp[]=new int[target+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int currTar=1;currTar<=target;currTar++){
            int ans=Integer.MAX_VALUE;
            for(int coinChosen : coins){
                if(currTar-coinChosen>=0 && dp[currTar-coinChosen]!=Integer.MAX_VALUE){
                    ans=Math.min(ans,dp[currTar-coinChosen]+1);
                    hp.put(currTar, coinChosen);
                }
            }
            dp[currTar]=ans;
        }
        while (target > 0) {
            System.out.print("For "+target+" we use coin:"+hp.get(target) + ", remaining is "+(target-hp.get(target))+" -> ");
            target -= hp.get(target);
        }
        return dp[target];
    }


//------------------TASK2: Count noOf Ways to of such solutions-------------------------------------


public static int cnt(int coins[], int target, int values[],int cnt[]) {// o(target*nCoins)---O(target)
    Arrays.fill(values, Integer.MAX_VALUE);
    values[0] = 0;
    cnt[0]=1;//IMP
    for (int tar = 1; tar <= target; tar++) {
        for (int coin : coins) {
           if (tar - coin >= 0) {
                values[tar] = Math.min(values[tar], values[tar - coin] + 1);
                cnt[tar]+=cnt[tar-coin];
            }
        }
    }
    if (values[target] == Integer.MAX_VALUE) {
        return 0;//no  ways
    }
   
    return cnt[target];
}

    










}




