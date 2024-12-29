package _6_DynamicProgramming;
//pre-requisites:
//watch:https://www.youtube.com/watch?v=pbXg5EI5t4c&ab_channel=Numberphile 

import java.util.Arrays;

//also:https://www.youtube.com/watch?v=qYAWjIVY7Zw&ab_channel=Numberphile2


//now watch:https://www.youtube.com/watch?v=XeAHpQ8AtvM&ab_channel=ANNAACADEMYKota




//problem:
/*
You are given n balls numbered from 1 to n and there are n baskets numbered from 1 to n in front of you. The ith basket is meant for the ith ball. Calculate the number of ways in which no ball goes into its respective basket.

Note: The answer will always fit into a 32-bit integer.

Examples:

Input: n = 2
Output: 1
Explanation: For two balls {1, 2}, there is only one possible derangement {2, 1}.
Input: n = 3
Output: 2
Explanation: For three balls {1, 2, 3}, there are two possible derangements {3, 1, 2} and {2, 3, 1}.
Constraints:
1 ≤ n ≤ 12
 */



//some sample knowledge:
/*
Say u wanna find the no of permutations where in idx(1 based)  is not equal to 'i'. U represent this as (allPermutations-allPermutationsWhere_i_is_equal_to_idx). Here allPermutations is nothing but 'n!'. Now the challeneg is to find all permutations where idx==i. Say {c1,c2,c3} be some permutation where ci represents ith digit of permuatation of say length n,(here n=3)

Now all possible permuations are : {c1,c2,c3},{c1,c3,c2},{c2,c1,c3},{c2,c3,c1},{c3,c1,c2},{c3,c2,c1}

Now the number of permutations where c1 is fixed at 1st idx  are {c1,c2,c3} & {c1,c3,c2} ie 2, similarly if c2 position is fixed, we have 2 permutations and again 2 permutations for c3 fixed at 3rd position, we represnt as D(k) as the number of permutations where kth character is fixed at its corresponding index, observe that D(c1)=D(c2)=D(c3)=2 which's nothing but (3-1)!=2!=2

So, in general D(ki) where k is the character kept fixed at its coreesponding index 'i', in a permuatation of length n, then number of permutations where k is fixed at i and remaining can change is (n-1)!. 

This was for 1 character fixed, now suppose we keep 2 characters say c1&c2 fixed at place, we find that there's only 1 such permutation is {c1,c2,c3}, ie 1 => (3-2)!....

Observe the pattern, if we keep 1 char constant its (n-1)!, 2 then (n-2)! permutations.....In general, if we keep 'x' places constant, the number of subpermutations of permutation of length n where 'x' places specified are kept constant are : (n-x)!

Now lets say we have permutation {1234}, the number of permuations where idx!=i (or in other words nOfPermutations where theres no place where idx==itself) = totalNOfPermutations-[all permutations where at least there's 1 elemnt which is equal to its idx]

ie n!-[{D(a)+D(b)+D(c)+D(d)}+{D(ab)+D(ac)+D(ad)+D(bc)+D(bd)+D(cd)}+D(abc)+....] = n! -({4D(a)}+{6D(ab)}+...)--using D(a)=D(b)....& D(ab)=D(bc)=D(ad).....
= now D(a)=(n-1)! and D(ab)=(n-2)!...like that as explained previously that for 1 fixed its (n-1)!, for 2 its (n-2)!....here n=4 (coz chosen abcd)
=> ans= n! - [{4(4-1)!} + {6(4-2)!}+....]===> now {4(4-1)! is nothing but (4!/1!)}, also {6(2!) is nothing but 4!/2!...} ie n!-[n!/1! + n!/2!+.......(-1)^n/n!---we must also include some inclusions of set]====>(n!/e) which in terms of recursion D(n)=(n-1)[D(n-2)+D(n-1)]
=>

*/



public class _08_dearrange_balls {

    //brute force: for each permutation, check if all values satisfy idx!=a[i], if yes count such permutaions. TC n*n!---TLE

    //using math: NOTE: this will give approx anwers only and not exact ans:
    //Formula: No. of dearrangements for n didgit permutation is given by (n!)/e derived in that above videos u can see


    //watch those videos especially last one,u will get proofs of the below eqns:
    //Dearrangements: the ith position(1 based) must not be equal to i in permutation from 1 to n

    //D(n) be no of dearrangements among all subsets of permutation from [1 to n]

    //D(n)=(n-1)[D(n-2)+D(n-1)]

    /*
Suppose we have an array [0 1 2] n = 3


Now for 0, we have 2 blocks to place it [ X _ _  ]

if we place 0 at 1st index, then we have n -1 places for 1 to be placed. [ _ 0 _ ], So f(n-1)

if we place 0 at 2nd index, then we will be left with single block for 1 to get placed [ _ X 0 ], So f(n-2)
     */

    //D(n)=(n!)[(1/2!)-(1/3!)+(1/4!)...((-1)^n)/n!] = (n!)/e


    //how D(n)=(n-1)[D(n-2)+D(n-1)]?
    //now watch:https://www.youtube.com/watch?v=NW-BLDQHFXk&list=PLDzeHZWIZsTomOPnCiU3J95WufjE36wsb&index=7&ab_channel=CodeHelp-byBabbar

    static int recursion(int n) 
    { 
       if(n==1) return 0;
       if(n==2) return 1;// like we have 12, only way is 21
       int ifSwapped=(n-1)*(recursion(n-2));
       int notSwapped=(n-1)*(recursion(n-1));
       return (ifSwapped+notSwapped);
    } 
    
    static int memoize(int n,int dp[]){
        //call like:int dp[]=new int[n+1];
    //   Arrays.fill(dp,-1);
    //   return memoize(n,dp);
        
        if(n==1) return 0;
        if(n==2) return 1;
        dp[1]=0;
        dp[2]=1;
        if(dp[n]!=-1) return dp[n];
        int ifSwapped=(n-1)*(memoize(n-2,dp));
        int ifNotSwapped=(n-1)*(memoize(n-1,dp));
        dp[n]=ifSwapped+ifNotSwapped;
        return dp[n];
    }
    
    static long tabulate(int n){
        if(n==1) return 0;
        if(n==2) return 1;
        long dp[]=new long[n+1];
        Arrays.fill(dp,-1);
        dp[1]=0;
        dp[2]=1;
		int MOD=1_000_000_007;
        for(int i=3;i<=n;i++){
            int ifSwapped = (int)(((long)(i - 1) * dp[i - 2]) % MOD);
            int ifNotSwapped = (int)(((long)(i - 1) * dp[i - 1]) % MOD); 
            dp[i] = (ifSwapped + ifNotSwapped) % MOD;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n=100;
        System.out.println(tabulate(n));
    }


}
