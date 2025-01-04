package _6_DynamicProgramming._2D;


/*
You are given the weights and values of items, and you need to put these items in a knapsack of capacity capacity to achieve the maximum total value in the knapsack. Each item is available in only one quantity.

In other words, you are given two integer arrays val[] and wt[], which represent the values and weights associated with items, respectively. You are also given an integer capacity, which represents the knapsack capacity. Your task is to find the maximum sum of values of a subset of val[] such that the sum of the weights of the corresponding subset is less than or equal to capacity. You cannot break an item; you must either pick the entire item or leave it (0-1 property).

Examples :

Input: capacity = 4, val[] = [1, 2, 3], wt[] = [4, 5, 1] 
Output: 3
Explanation: Choose the last item, which weighs 1 unit and has a value of 3.
Input: capacity = 3, val[] = [1, 2, 3], wt[] = [4, 5, 6] 
Output: 0
Explanation: Every item has a weight exceeding the knapsack's capacity (3).
Input: capacity = 5, val[] = [10, 40, 30, 50], wt[] = [5, 4, 6, 3] 
Output: 50
Explanation: Choose the second item (value 40, weight 4) and the fourth item (value 50, weight 3) for a total weight of 7, which exceeds the capacity. Instead, pick the last item (value 50, weight 3) for a total value of 50.
Expected Time Complexity: O(n*capacity).
Expected Auxiliary Space: O(n*capacity)

Constraints:
2 ≤ val.size() = wt.size() ≤ 103
1 ≤ capacity ≤ 103
1 ≤ val[i] ≤ 103
1 ≤ wt[i] ≤ 103
 */

public class _1_knapSack {

    public static void main(String[] args) {
        // int weights[]={4,5,1},values[]={1,2,3};
        // int maxCapacity=4;

        // int dp[][]=new int[weights.length+1][maxCapacity+1];

        //ans:max(values[i]+weights[])

    }


    public static int recursion(int weights[],int values[],int maxCapacity,int idx){
        //we reached from last to first
        if(idx==0){
            if(weights[idx]<=maxCapacity) return values[idx];
            return 0;
        }
        int includeCurrWeight=(weights[idx]<=maxCapacity)?(values[idx]+recursion(weights, values, maxCapacity-weights[idx], idx-1)):0;//include curr and move back AFTER UPDATING MAX_CAPACITY
        int excludeCurrWeight=recursion(weights, values, maxCapacity, idx-1);//just move back otherwise

        return Math.max(includeCurrWeight,excludeCurrWeight);

    }


    //here we create 2D dp coz observe in recursive solution that there r two parameter changing: idx(we have used idx-1) and maxCapacity(we have updated maxCapacity to maxCapacity--weights[idx]). Idx lies in [0,weights.length] & capacity can vary from [0,capacity]
    //call kie this
    /*
    int dp[][]=new int[weight.length+1][maxWeight+1];
            for(int [] e: dp) Arrays.fill(e, -1);
            return memoize(weight, value, maxWeight, n-1, dp);
     */
    public static int memoize(int weights[],int values[],int maxCapacity,int idx,int dp[][]){
        if(idx==0){
            if(weights[idx]<=maxCapacity){
                dp[idx][maxCapacity]=values[idx];
                return dp[idx][maxCapacity];
            }
            return 0;
        }
        if(dp[idx][maxCapacity]!=-1) return dp[idx][maxCapacity];

        int includeCurrWeight=(weights[idx]<=maxCapacity)?(values[idx]+memoize(weights, values, maxCapacity-weights[idx], idx-1, dp)):0;
        int excludeCurrWeight=memoize(weights, values, maxCapacity, idx-1, dp);

        dp[idx][maxCapacity]=Math.max(includeCurrWeight, excludeCurrWeight);
        return dp[idx][maxCapacity];
    }
    

    //------------------tabulation://
    //analyse usuing:https://www.youtube.com/watch?v=PfkBS9qIMRE&ab_channel=Jenny%27sLecturesCSIT
    public static int tabulate(int weights[],int values[],int maxCapacity){
        int dp[][]=new int[weights.length+1][maxCapacity+1];

        //base case:
        for(int w=weights[0];w<=maxCapacity;w++){
            if(weights[0]<=maxCapacity){
                dp[0][w]=values[0];
            }
        }
        
        //dp[idx][maxCapacity] will give us values[idx], means dp[idx][maxCapacity] tells us for that idx,
        for (int idx = 1; idx < weights.length; idx++) {
            int weightChosen=weights[idx];
            for(int currCapacity=0;currCapacity<=maxCapacity;currCapacity++){
                int includeCurrWeight=(weightChosen<=maxCapacity && currCapacity>=weightChosen)?(values[idx]+dp[idx-1][currCapacity-weightChosen]):0;
                int excludeCurrWeight=dp[idx-1][currCapacity];

                dp[idx][currCapacity]=Math.max(includeCurrWeight,excludeCurrWeight);
            }
        } 
        return dp[weights.length-1][maxCapacity];
    }
}
