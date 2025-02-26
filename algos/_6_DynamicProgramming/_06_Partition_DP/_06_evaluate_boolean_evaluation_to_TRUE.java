package _6_DynamicProgramming._06_Partition_DP;

/*
You are given an expression 'exp' in the form of a string where operands will be : (TRUE or FALSE), and operators will be : (AND, OR or XOR).



Now you have to find the number of ways we can parenthesize the expression such that it will evaluate to TRUE.



As the answer can be very large, return the output modulo 1000000007.



Note :

‘T’ will represent the operand TRUE.
‘F’ will represent the operand FALSE.
‘|’ will represent the operator OR.
‘&’ will represent the operator AND.
‘^’ will represent the operator XOR.
Example :

Input: 'exp’ = "T|T & F".

Output: 1

Explanation:
There are total 2  ways to parenthesize this expression:
    (i) (T | T) & (F) = F
    (ii) (T) | (T & F) = T
Out of 2 ways, one will result in True, so we will return 1.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
T^T^F    
Sample Output 1 :
0
Explanation For Sample Input 1:
There are total 2  ways to parenthesize this expression:
(i) (T^T)^(F) = F
(ii) (T)^(T^F) = F
Both ways will result in False, so we will return 0.
Sample Input 2 :
F|T^F
Sample Output 2 :
2
Explanation For Sample Input 2:
For the first test case:
There are total 2  ways to parenthesize this expression:
(i) (F|T)^(F) = T
(ii) (F)|(T^F) = T
Both ways will result in True, so we will return 2.
Expected time complexity
The expected time complexity is O(n ^ 3), where 'n' denotes the length of 'exp'.
Constraints:
3 <= |‘exp’| <= 200
Where |'exp'| denotes the length of 'exp'.

Time Limit: 1 sec
 */
public class _06_evaluate_boolean_evaluation_to_TRUE {
    // check for
    // expls:https://takeuforward.org/data-structure/evaluate-boolean-expression-to-true-partition-dp-dp-52/
    /*
     * Divide int subproblms
     * 
     * [subProb1] opr [subProb2]
     * x1 be nWays in which subProb1 yields TRUE
     * x2 be nWays in which subProb1 yields FALSE
     * x3 be nWays in which subProb1 yields TRUE
     * x4 be nWays in which subProb1 yields FALSE
     * 
     * Suppose opr is:
     * "|" then nWays of getting true is: x1*x3 + x1*x4 + x2*x3 ie TT+TF+FT
     * "&" then nWays of getting true is: x1*x3 ie TT
     * "^" then nWays of getting true is: x1*x4 + x2*x3 ie TF+FT
     */

    public static void main(String[] args) {
        String exp = "F|T^F";
        // long ans = rec(exp, 0, exp.length() - 1, true);
        long ans=tab(exp);
        System.out.println(ans);
    }

    static int MOD = 1_000_000_000;

    // -------------------------recursion
    public static long rec(String exp, int i, int j, boolean looking_for) {
        if (i == j) {
            return (looking_for == true) ? (exp.charAt(i) == 'T' ? 1 : 0) : (exp.charAt(i) == 'F' ? 1 : 0);
        }
        if (i > j)
            return 0;
        long nWays = 0l;

        for (int k = i + 1; k < j; k += 2) {
            long left_true = rec(exp, i, k - 1, true);
            long left_false = rec(exp, i, k - 1, false);

            long right_true = rec(exp, k + 1, j, true);
            long right_false = rec(exp, k + 1, j, false);

            switch (exp.charAt(k)) {
                case '&' -> {
                    if (looking_for == true) {
                        nWays = (nWays + (left_true * right_true) % MOD) % MOD;
                    } else {
                        nWays = (nWays + (left_true * right_false) % MOD + (left_false * right_true) % MOD
                                + (left_false * right_false) % MOD) % MOD;
                    }
                }
                case '|' -> {
                    if (looking_for == true) {
                        nWays = (nWays + (left_true * right_true) % MOD + (left_false * right_true) % MOD
                                + (left_true * right_false) % MOD) % MOD;
                    } else {
                        nWays = (nWays + (left_false * right_false) % MOD) % MOD;
                    }
                }
                case '^' -> {
                    if (looking_for == true) {
                        nWays = (nWays + (left_true * right_false) % MOD + (left_false * right_true) % MOD);
                    } else {
                        nWays = (nWays + (left_true * right_true) % MOD + (left_false * right_false) % MOD);
                    }
                }

            }
        }
        return nWays;
    }

    // for mem, use dp[i][j][looking_for]
    // ---------------------tab:
    static long tab(String exp) {
        int len = exp.length();
        int dp[][][] = new int[len][len][2];

        // in mem: i moved [0..n], j moved[n,,i], kmoved[i+1..j], lokking_for moved
        // [true,false]ie[1,0]
        for (int i = len-1; i >=0; i--) {
            for (int j = i; j <len; j++) {
                for (int looking_for = 0; looking_for <= 1; looking_for++) {
                    long nWays = 0;
                    if (i == j) {
                        dp[i][j][1] = (exp.charAt(i) == 'T' ? 1 : 0);
                        dp[i][j][0] = (exp.charAt(i) == 'F' ? 1 : 0);
                        continue; // Move to the next iteration since no further computation needed
                    }
                    for (int k = i + 1; k < j; k++) {
                        long left_true =dp[i][k-1][1];
                        long left_false = dp[i][k-1][0];

                        long right_true = dp[k+1][j][1];
                        long right_false = dp[k+1][j][0];

                        switch (exp.charAt(k)) {
                            case '&' -> {
                                if (looking_for == 1) {
                                    nWays = (nWays + (left_true * right_true) % MOD) % MOD;
                                } else {
                                    nWays = (nWays + (left_true * right_false) % MOD
                                            + (left_false * right_true) % MOD + (left_false * right_false) % MOD) % MOD;
                                }
                            }
                            case '|' -> {
                                if (looking_for == 1) {
                                    nWays = (nWays + (left_true * right_true) % MOD
                                            + (left_false * right_true) % MOD + (left_true * right_false) % MOD) % MOD;
                                } else {
                                    nWays = (nWays + (left_false * right_false) % MOD) % MOD;
                                }
                            }
                            case '^' -> {
                                if (looking_for == 1) {
                                    nWays =  (nWays + (left_true * right_false) % MOD
                                            + (left_false * right_true) % MOD);
                                } else {
                                    nWays = (nWays + (left_true * right_true) % MOD
                                            + (left_false * right_false) % MOD);
                                }
                            }

                        }
                    }
                    dp[i][j][looking_for] = (int)nWays;
                }
            }
        }
        return dp[0][len - 1][1];
    }

}
