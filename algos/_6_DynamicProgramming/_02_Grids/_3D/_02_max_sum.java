package _6_DynamicProgramming._02_Grids._3D;



/*There are two arrays , 'B' and 'C' ; size of both the arrays is 'N' ; -> At each index you are supposed to pick up an element either from array 'C' or array 'B' ->You have to maximize the final sum of all elements ->Condition : You cannot select 3 or more than 3 element consecutively from the same array. Example : 3 5 10 3 10 4 10 Output : 25 1<=N<=100000 1<=B[i];C[i]<=10000000000 , */

import java.util.Scanner;

public class _02_max_sum {
    /*Problem Restatement

We have two arrays B and C, each of size N.
At index i, we must choose either B[i] or C[i].
Constraint: Cannot pick 3 or more consecutively from the same array.
Goal: Maximize total sum.

Example
N = 3
B = [5, 3, 4]
C = [10, 10, 10]


Choices:

Pick C[0], C[1], B[2] → 10 + 10 + 4 = 24

Pick C[0], B[1], C[2] → 10 + 3 + 10 = 23

Pick B[0], C[1], C[2] → 5 + 10 + 10 = 25 ✅ (best)

Answer = 25.

DP Formulation

Let’s define DP states:

dp[i][0] → max sum till index i if we pick B[i].

dp[i][1] → max sum till index i if we pick C[i].

But we also need to prevent 3 consecutive picks.
So we extend with consecutive count:

dp[i][0][1] → max sum till i, picking B[i], and it's the 1st consecutive B.

dp[i][0][2] → max sum till i, picking B[i], and it's the 2nd consecutive B.

dp[i][1][1] → max sum till i, picking C[i], and it's the 1st consecutive C.

dp[i][1][2] → max sum till i, picking C[i], and it's the 2nd consecutive C.

No state for 3, since that’s not allowed.

Transitions

If we take B[i]:

As 1st B → must come after C[i-1]:

dp[i][0][1] = max(dp[i-1][1][1], dp[i-1][1][2]) + B[i]


As 2nd B → must come after a single B:

dp[i][0][2] = dp[i-1][0][1] + B[i]


If we take C[i]:

As 1st C → must come after B[i-1]:

dp[i][1][1] = max(dp[i-1][0][1], dp[i-1][0][2]) + C[i]


As 2nd C → must come after a single C:

dp[i][1][2] = dp[i-1][1][1] + C[i]

Base Case

At i = 0:

dp[0][0][1] = B[0]
dp[0][1][1] = C[0]
dp[0][0][2] = dp[0][1][2] = -∞ (invalid)

Final Answer
max(
  dp[N-1][0][1], dp[N-1][0][2],
  dp[N-1][1][1], dp[N-1][1][2]
)

Time & Space

Time: O(N) (just 4 states per index).

Space: O(1) if we roll arrays (only previous index needed). */

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int a[]=new int[n+1];
		int b[]=new int[n+1];
		for(int i=1;i<=n;i++) a[i]=sc.nextInt();
		for(int i=1;i<=n;i++) b[i]=sc.nextInt();

		
// 		System.out.println(rec(0,a,b,0));

        int dp[][][]= new int[n+1][3][3];
        int inf=Integer.MAX_VALUE;
        //from a
        dp[1][0][1]=a[1];
        dp[1][0][2]=-inf;
        //from b
        dp[1][1][1]=b[1];
        dp[1][1][2]=-inf;
        
        for(int i=2;i<=n;i++){
            dp[i][0][1]=a[i]+Math.max(dp[i-1][1][1],dp[i-1][1][2]);
            dp[i][0][2]=a[i]+dp[i-1][0][1];
            dp[i][1][1]=b[i]+Math.max(dp[i-1][0][1],dp[i-1][0][2]);
            dp[i][1][2]=b[i]+dp[i-1][1][1];
        }
        System.out.println(Math.max(
            Math.max(dp[n][0][1],dp[n][0][2]),
            Math.max(dp[n][1][1],dp[n][1][2])
            ));
            sc.close();
	}
	
	
}

}
