package _6_DynamicProgramming._06_Partition_DP;

import java.util.ArrayList;
import java.util.List;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
 

Constraints:

1 <= n <= 8
 */
public class _01_generate_parenthesis {
    public static void main(String[] args) {
        rec(0,0,3,"");
        System.out.println(li);
    }
    static List<String>li=new ArrayList<>();
    static void rec(int left,int right,int n,String s){
        if(n*2==s.length()){
            li.add(s);
            return;
        }
        if(left<n){
            rec(left+1,right,n,s+"(");
        }
        if(right<left){
            rec(left,right+1,n,s+")");
        }
    }



    
}
