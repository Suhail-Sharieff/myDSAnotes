//https://www.youtube.com/watch?v=EgG3jsGoPvQ&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=4&ab_channel=takeUforward

/*
There is a frog on the '1st' step of an 'N' stairs long staircase. The frog wants to reach the 'Nth' stair. 'HEIGHT[i]' is the height of the '(i+1)th' stair.If Frog jumps from 'ith' to 'jth' stair, the energy lost in the jump is given by absolute value of ( HEIGHT[i-1] - HEIGHT[j-1] ). If the Frog is on 'ith' staircase, he can jump either to '(i+1)th' stair or to '(i+2)th' stair. Your task is to find the minimum total energy used by the frog to reach from '1st' stair to 'Nth' stair.

For Example
If the given ‘HEIGHT’ array is [10,20,30,10], the answer 20 as the frog can jump from 1st stair to 2nd stair (|20-10| = 10 energy lost) and then a jump from 2nd stair to last stair (|10-20| = 10 energy lost). So, the total energy lost is 20.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 10
1 <= N <= 100000.
1 <= HEIGHTS[i] <= 1000 .

Time limit: 1 sec
Sample Input 1:
2
4
10 20 30 10
3
10 50 10
Sample Output 1:
20
0
Explanation of sample input 1:
For the first test case,
The frog can jump from 1st stair to 2nd stair (|20-10| = 10 energy lost).
Then a jump from the 2nd stair to the last stair (|10-20| = 10 energy lost).
So, the total energy lost is 20 which is the minimum. 
Hence, the answer is 20.

For the second test case:
The frog can jump from 1st stair to 3rd stair (|10-10| = 0 energy lost).
So, the total energy lost is 0 which is the minimum. 
Hence, the answer is 0.
Sample Input 2:
2
8
7 4 4 2 6 6 3 4 
6
4 8 3 10 4 4 
Sample Output 2:
7
2


Hints:
1. Think about all the possibilities at each stair.
2. Using recursion, try to divide the problem into subproblems and calculate the answer for each subproblem only once - store it for reusing in the future.
3. The above can also be done iteratively.

 */


 //we will same procedure as discussed:
 //main thing is we check from last to first, we return the min value of cost jumping from (i to i-1) and (i to i-2)
 package _6_DynamicProgramming;
 
 public class _04_frog_jump_1 {
 
    public static void main(String[] args) {
        int heights[]={10 ,20, 30, 10
        };
        int dp[]=new int[heights.length+1];
        // System.out.println(memoize(heights, heights.length-1, dp));//start from last
        // System.out.println(tabulate(heights, 0, dp));
        // System.out.println(nonRecursive(heights, dp));
        // System.out.println(spaceOptimized(heights));

        //-----foloww up question
        System.out.println(func(heights, dp, 2));

    }
    //optimal1-------------move from (bottom to up) of rec tree
    public static int memoize(int heights[],int idx,int dp[]){//O(n)--O(n)
        //base case jump from 0 to 0, no energy required
        if(idx==0) return 0;
        //if precomputed ans present ie memoized, return that
        if(dp[idx]!=0) return dp[idx];
        int way1=memoize(heights, idx-1, dp)+Math.abs(heights[idx]-heights[idx-1]);//first way is he could have jumped 1 step from prev stair
        int way2=(idx>1)?(memoize(heights, idx-2, dp)+Math.abs(heights[idx]-heights[idx-2])):(Integer.MAX_VALUE);//second way is he could have jumped 2 steps from previous's previous step & also handle idx>1 since idx-2 is negative
        dp[idx]=Math.min(way1, way2);
        return dp[idx];
    }


    //optimal2------------using tabulation, move from (top to bottom) of rec tree
    public static int tabulate(int height[],int idx,int dp[]){
        if(idx==height.length-1) return 0;
        if(dp[idx]!=0) return dp[idx];
        int way1=tabulate(height, idx+1, dp)+Math.abs(height[idx]-height[idx+1]);
        int way2=(idx+2<height.length)?(tabulate(height, idx+2, dp)+Math.abs(height[idx]-height[idx+2])):(Integer.MAX_VALUE);
        dp[idx]=Math.min(way1, way2);
        return dp[idx];
    }

    //optimal3------------usin for loop
    public static int nonRecursive(int heights[],int dp[]){//fron l to right of array
        dp[0]=0;
        for(int i=1;i<heights.length;i++){
            int way1=dp[i-1]+Math.abs(heights[i]-heights[i-1]);
            int way2=(i>1)?(dp[i-2]+Math.abs(heights[i]-heights[i-2])):(Integer.MAX_VALUE);
            dp[i]=Math.min(way1, way2);
        }
        //return last value
        return dp[heights.length-1];
    }

    //-------------space optimized solution for non recursive solution:
    //observe the non recursive soltoion, we track d[i-2],dp[i-1] and dp[i], so instaed of array we can use 3 variables
    public static int spaceOptimized(int heights[]){
        int prev2=0;
        int prev1=0;
        for(int i=1;i<heights.length;i++){
            int currIdx=i;
            int way1=prev1+Math.abs(heights[currIdx]-heights[i-1]);
            int way2=(i>1)?(prev2+Math.abs(heights[currIdx]-heights[i-2])):Integer.MAX_VALUE;
            //set prev states:
            prev2=prev1;
            prev1=Math.min(way1, way2);
        }
        return prev1;
    }













    //----------------FOLLOW UP:in the previous case , the frog was allowed to jump say from i to (i+1 or i+2), now what if the frog is allowed to jump from say from i+1 to i+k, ie from can jump [i+1 or i+2 or ....i+k] ?
    //this is extension of nonRecursive solution
    public static int func(int heights[],int dp[],int k){
        dp[0]=0;
        for(int i=1;i<heights.length;i++){
           int currMin=Integer.MAX_VALUE;
           for(int j=1;j<=k;j++){
            if (i-j>=0) {
                int way=dp[i-j]+Math.abs(heights[i]-heights[i-j]);
                currMin=Math.min(currMin, way);
            }else{
                break;
            }
           }
           dp[i]=currMin;
        }
        return dp[heights.length-1];
    }








 }
