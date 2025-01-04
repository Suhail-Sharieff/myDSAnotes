package _6_DynamicProgramming._2D;
/*
Problem statement
Ninja is planing this ‘N’ days-long training schedule. Each day, he can perform any one of these three activities. (Running, Fighting Practice or Learning New Moves). Each activity has some merit points on each day. As Ninja has to improve all his skills, he can’t do the same activity in two consecutive days. Can you help Ninja find out the maximum merit points Ninja can earn?

You are given a 2D array of size N*3 ‘POINTS’ with the points corresponding to each day and activity. Your task is to calculate the maximum number of merit points that Ninja can earn.

For Example
If the given ‘POINTS’ array is [[1,2,5], [3 ,1 ,1] ,[3,3,3] ],the answer will be 11 as 5 + 3 + 3.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 10
1 <= N <= 100000.
1 <= values of POINTS arrays <= 100 .

Time limit: 1 sec
Sample Input 1:
2
3
1 2 5 
3 1 1
3 3 3
3
10 40 70
20 50 80
30 60 90
Sample Output 1:
11
210
Explanation of sample input 1:
For the first test case,
One of the answers can be:
On the first day, Ninja will learn new moves and earn 5 merit points. 
On the second day, Ninja will do running and earn 3 merit points. 
On the third day, Ninja will do fighting and earn 3 merit points. 
The total merit point is 11 which is the maximum. 
Hence, the answer is 11.

For the second test case:
One of the answers can be:
On the first day, Ninja will learn new moves and earn 70 merit points. 
On the second day, Ninja will do fighting and earn 50 merit points. 
On the third day, Ninja will learn new moves and earn 90 merit points. 
The total merit point is 210 which is the maximum. 
Hence, the answer is 210.
Sample Input 2:
2
3
18 11 19
4 13 7
1 8 13
2
10 50 1
5 100 11
Sample Output 2:
45
110
 */
public class _2_ninjaTraining {


    public static void main(String[] args) {
        int points[][]={
            {10,50,1},
            {5,100,11},
            // {1,8,13},
        };
        // System.out.println(re(points));
    }





    /*
    WRONG CODE THAT I WROTE AT FIRST:

    USE TC:

    10 50 1
    5 100 11

    Your output:61
    Expected output:110


    public static int func(int grid[][]){
        int ans=0;
        int prev=-1;
        int nRows=grid.length,nCols=3;
        for(int i=0;i<nRows;i++){
            int max=0;
            for(int j=0;j<nCols;j++){
                int max_idx[]=max_idx(grid[i],prev);
                max=max_idx[0];
                prev=max_idx[1];
            }
            ans+=max;
        }
        return ans;
    }

    public static int[] max_idx(int row[],int prev){
        int max=Integer.MIN_VALUE;
        int idx=-1;
        for(int i=0;i<3;i++){
            if(row[i]>max && i!=prev){
                max=row[i];
                idx=i;
            }
        }
        return new int[]{max,idx};
    }
     */






     //---------recursive: 0 to top approach,call like: return recursive(points, 0, -1);
     public static int recursive(int points[][],int dayNumber,int taskNumberChosenOnPrevDay){//TLE
        if(dayNumber==points.length-1){
            int ans=Integer.MIN_VALUE;
            int n=points.length-1;
            for(int currTaskNumber=0;currTaskNumber<3;currTaskNumber++){
                if(currTaskNumber!=taskNumberChosenOnPrevDay){
                    ans=Math.max(ans,points[n][currTaskNumber]);
                }
            }
            return ans;
        }
        
        int chosenDay[]=points[dayNumber];
        int max=Integer.MIN_VALUE;
        for(int currTaskNumber=0;currTaskNumber<3;currTaskNumber++){
            if(currTaskNumber!=taskNumberChosenOnPrevDay){
                int scoreIfChosenThatTask=chosenDay[currTaskNumber]+recursive(points, dayNumber+1, currTaskNumber);
                max=Math.max(max, scoreIfChosenThatTask);
            }
        }
        return max;
    }

    //-------------------tabulation:
    // why do we need 2d array here: observe that we have to deal with 2 changing states ie both dayNumber and currTaskNumber
    //call like:
    /*
    int dp[][] = new int[n][4]; // 4 represents tasks (0, 1, 2, and 3 for "no task chosen")
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Call the recursive function starting from day 0 with no previous task (as 3)
        return tabulate(points, 0, 3, dp);//IMP 3
     */
    public static int memoized(int points[][],int dayNumber,int taskNumberChosenOnPrevDay,int dp[][]){//STack overflow
        if(dayNumber==points.length-1){
            int ans=Integer.MIN_VALUE;
            int n=points.length-1;
            for(int currTaskNumber=0;currTaskNumber<3;currTaskNumber++){
                if(currTaskNumber!=taskNumberChosenOnPrevDay){
                    ans=Math.max(ans,points[n][currTaskNumber]);
                }
            }
            dp[dayNumber][taskNumberChosenOnPrevDay]=ans;
            return ans;
        }

        //dp[i][j] represents maximum sum till ith day if prevTaskNumber chosen was j---IMP
        //dp[dayNumber][prevTaskNumber]

        if(dp[dayNumber][taskNumberChosenOnPrevDay]!=-1) return dp[dayNumber][taskNumberChosenOnPrevDay];
        int chosenDay[]=points[dayNumber];

        int max=Integer.MIN_VALUE;
        for (int currTaskNumber = 0; currTaskNumber < 3; currTaskNumber++) {
            if (currTaskNumber!=taskNumberChosenOnPrevDay) {
                int scoreIfChosenThatTask=chosenDay[currTaskNumber]+memoized(points, dayNumber+1, currTaskNumber, dp);
                max=Math.max(max, scoreIfChosenThatTask);
            }
        }
        dp[dayNumber][taskNumberChosenOnPrevDay]=max;
        return dp[dayNumber][taskNumberChosenOnPrevDay];
    }


    //-------tabulated solution keeping in mind that dp[i][j] repersents maximum points that could be earned till ith day if we had chosen jth task previously. We will ove from 0 to top
    public static int tabulate(int points[][]){
        int dp[][]=new int[points.length][3];
        //handle case when 0
        //dp[i][j] represents on ih day what was maximum points earned when the previously chosen taskNumber was j
        dp[0][0]=Math.max(points[0][1], points[0][2]);// prev day,taskNumber chosen was 0, so choose max amonng remining 2
        dp[0][1]=Math.max(points[0][0], points[0][2]);/// prev day,taskNumber chosen was 1, so choose max amonng remining 2
        dp[0][2]=Math.max(points[0][0], points[0][1]);/// prev day,taskNumber chosen was 2, so choose max amonng remining 2

        ////dp[dayNumber][prevTaskNumber]
        for(int dayNumber=1;dayNumber<points.length;dayNumber++){
            for(int taskNumberChosenOnPrevDay=0;taskNumberChosenOnPrevDay<3;taskNumberChosenOnPrevDay++){
                int max=0;
                for(int currTaskNumber=0;currTaskNumber<3;currTaskNumber++){
                    if (currTaskNumber!=taskNumberChosenOnPrevDay) {
                        int scoreIfChosenThatTask=points[dayNumber][currTaskNumber]+dp[dayNumber-1][currTaskNumber];
                        max=Math.max(max, scoreIfChosenThatTask);
                    }
                }
                dp[dayNumber][taskNumberChosenOnPrevDay]=max;
            }
        }
        int ans=0;
       //now any 3 values in last row of dp will be having max ans as per the task chosen at last
       for(int e : dp[points.length-1]) ans=Math.max(ans, e);
       return ans;

    }

}
