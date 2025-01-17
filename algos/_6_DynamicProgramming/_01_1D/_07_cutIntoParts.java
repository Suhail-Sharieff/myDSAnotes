package _6_DynamicProgramming._01_1D;


/*
You are given an integer ‘N’ denoting the length of the rod. You need to determine the maximum number of segments you can make of this rod provided that each segment should be of the length 'X', 'Y', or 'Z'.

Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
2
7 5 2 2
8 3 3 3
Sample Output 1:
2
0
Explanation For Sample Input 1:

In the first test case, cut it into 2 parts of 5 and 2.

In the second case, there is no way to cut into segments of 3 length only as the length of the rod is less than the given length. 
Sample Input 2:
2
7 3 2 2
8 1 4 4
Sample Output 2:
3
8
Explanation For Sample Input 2:
In the first test case, cut it into 3 parts of 3, 2 and 2.

In the second case, cut it into 8 parts of length 1.
 */


import java.util.Arrays;

public class _07_cutIntoParts {//this problem is similar to that of coins problem, here denominations r given as X,Y,Z and the target sum we need to form is given as n...and we just neeed to find max instaed of min...thats all
    public static void main(String[] args) {
        int n=7;//len of rod
        int x=3,y=2,z=2;//lens of segments we can use to form x

         int arr[]={x,y,z};
        //recursive:
        // int ans=rec(arr,n);

        //memoized:
        // int dp[]=new int[n+1];
        // int ans=memoized(arr, n, dp);

        //tabulated:
        int ans=tabulate(arr, n);
        

        System.out.println((ans<0)?0:ans);
    }
    //its similar to coin problem
    public static int rec(int arr[],int target){
        if(target==0) return 0;
        if(target<0) return -1;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            int subRes=rec(arr, target-arr[i]);
            if(subRes!=-1){
                max=Math.max(max, subRes+1);
            }
        }
        return max;
    }
    public static int memoized(int arr[],int target,int dp[]){
        if(target==0) return 0;
        if(target<0) return -1;
        if(dp[target]!=0) return dp[target]; 
        int max=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            int subRes=memoized(arr, target-arr[i],dp);
            if(subRes!=-1){
                max=Math.max(max, subRes+1);
            }
        }
        dp[target]=max;
        return max;
    }

    public static int tabulate(int arr[],int target){
        if(target==0) return 0;
        int dp[]=new int[target+1];
        Arrays.fill(dp, -1);
        dp[0]=0;
        for(int lenChosen=1;lenChosen<=target;lenChosen++){
            int max=-1;
            for(int segment : arr){
                if(lenChosen-segment>=0 && dp[lenChosen-segment]!=-1){
                    max=Math.max(max, dp[lenChosen-segment]+1);
                }
            }
            dp[lenChosen]=max;
        }
        return (dp[target]==-1)?0:dp[target];
    }



//----------------------------------------------------FOLlow up:
/*
Given a rod of length n(size of price) inches and an array of prices, price. price[i] denotes the value of a piece of length i. Determine the maximum value obtainable by cutting up the rod and selling the pieces.

Example:

Input: price[] = [1, 5, 8, 9, 10, 17, 17, 20]
Output: 22
Explanation: The maximum obtainable value is 22 by cutting in two pieces of lengths 2 and 6, i.e., 5+17=22.
Input: price[] = [3, 5, 8, 9, 10, 17, 17, 20]
Output: 24
Explanation: The maximum obtainable value is 24 by cutting the rod into 8 pieces of length 1, i.e, 8*price[1]= 8*3 = 24.
Input: price[] = [1, 10, 3, 1, 3, 1, 5, 9]
Output: 40
Constraints:
1 ≤ price.size() ≤ 103
1 ≤ price[i] ≤ 106
 */
 //--------------------------recursion, i have converted preices array into 1 based indexing for clarity to call like:
    /*
     int len=price.length;
        int v[]=new int [len+1];
        for(int i=1;i<=len;i++){
            v[i]=price[i-1];
        }
        return recurion(v,len,len);
     */

     public static int recursion_1(int priceOfLen[],int length_of_cut_part,int totalLength){
        if(totalLength==0) return 0;
        if(totalLength==1) return totalLength*priceOfLen[1];


        int cut=(totalLength>=length_of_cut_part)?(priceOfLen[length_of_cut_part]+recursion_1(priceOfLen, length_of_cut_part, totalLength-length_of_cut_part)):0;
        int dont_cut=recursion_1(priceOfLen, length_of_cut_part-1, totalLength);

        return Math.max(cut, dont_cut);
    }

    //---------------recursion2
    public static int recursion_2(int priceOfLen[],int totalLength){
        if(totalLength==0) return 0;
        if(totalLength==1) return totalLength*priceOfLen[1];

        int max=0;
        for(int chosenLen=1;chosenLen<=totalLength;chosenLen++){
            int subRes=priceOfLen[chosenLen]+recursion_2(priceOfLen, totalLength-chosenLen);
            max=Math.max(max, subRes);
        }

        return max;
    }


    //------------memoization
    public static int memoize(int priceOfLen[],int totalLength,int dp[]){
        if(totalLength==0) return 0;
        if(totalLength==1) return totalLength*priceOfLen[1];
        
        if(dp[totalLength]!=-1) return dp[totalLength];

        int max=0;
        for(int chosenLen=1;chosenLen<=totalLength;chosenLen++){
            int subRes=priceOfLen[chosenLen]+memoize(priceOfLen, totalLength-chosenLen,dp);
            max=Math.max(max, subRes);
        }
        dp[totalLength]=max;

        return max;
    }

    //----------------tabulation:
    public static int tab(int prices[]){
        int totalLength=prices.length;
        int priceOfLen[]=new int[totalLength+1];//for 1 based indexing
        for(int i=1;i<=totalLength;i++) priceOfLen[i]=prices[i-1];

        int dp[]=new int[totalLength+1];
        dp[0]=totalLength*priceOfLen[0];

        for(int len=1;len<=totalLength;len++){
            int max=0;
            for(int chosenLen=1;chosenLen<=len;chosenLen++){
                int subRes=priceOfLen[chosenLen]+dp[len-chosenLen];
                max=Math.max(max, subRes);
            }
            dp[len]=max;
        }

        return dp[totalLength];
    }

}