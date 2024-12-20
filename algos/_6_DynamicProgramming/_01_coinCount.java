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
        int ans = recursive(arr, target, new boolean[target + 1], new int[target + 1]);
        System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans);
        System.out.println(soln1(arr, target, new int[target + 1]));
        soln2(arr, target, new int[target + 1], new int[target + 1]);
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

    public static int recursive(int arr[], int target, boolean ready[], int values[]) {// just memoize the recursion
                                                                                       // O(nk),where n is the target
                                                                                       // sum and k is the number of
                                                                                       // coins.

        if (target < 0) {
            return Integer.MAX_VALUE;
        }
        if (target == 0) {
            return 0;
        }
        if (ready[target]) {
            return values[target];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int subResult = recursive(arr, target - arr[i], ready, values);
            if (subResult != Integer.MAX_VALUE) {// VIMP
                ans = Math.min(ans, subResult + 1); // Take one coin and add the result for the remaining target
            }
        }
        ready[target] = true;
        values[target] = ans;
        return ans;
    }

    // iterative solutions

    public static int soln1(int coins[], int target, int values[]) {// o(target*nCoins)---O(target)
        Arrays.fill(values, Integer.MAX_VALUE);
        values[0] = 0;// VVVVIMP or else wrong ans
        for (int tar = 1; tar <= target; tar++) {
            for (int coin : coins) {
                if (tar - coin >= 0) {
                    values[tar] = Math.min(values[tar], values[tar - coin] + 1);
                }
            }
        }
        if (values[target] == Integer.MAX_VALUE) {
            return -1;
        }
        return values[target];
    }

    // now suppose we also wwant what coins we r choosing
    public static void soln2(int coins[], int target, int values[], int choosed[]) {
        Arrays.fill(values, Integer.MAX_VALUE);
        values[0] = 0;
        for (int tar = 1; tar <= target; tar++) {
            for (int coin : coins) {
                if (tar - coin >= 0) {
                    if (values[tar - coin] + 1 < values[tar]) {
                        values[tar] = values[tar - coin] + 1;
                        choosed[tar] = coin;
                    }
                }
            }
        }
        if (values[target] == Integer.MAX_VALUE) {
            System.out.println("Not possible");
            return;
        }
        System.out.println("choosed coins are:");
        while (target > 0) {
            System.out.print(choosed[target] + " ");
            target -= choosed[target];
        }
        System.out.println();
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




