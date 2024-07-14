package _1_recursion.multipleCall;

import java.util.*;

public class _5_PrintPermutation {
    // the basec difference between subsequence and permutation is that the
    // permutation doesnt follow order and has size ==orig n=size whereas a
    // subsequence can be of any size and follows order of arrangement in original
    // list
    // ex: for nums=[1,2,3]
    // subseq(ordered)=[[1, 2, 3], [1, 2], [1, 3], [1], [2, 3], [2], [3], []]
    // permutation:[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]


    //using for loop coz the first elemnt to be picked can be any amounfg those n elemnts
    //check if picked or unpicked and then proceed
    //https://www.youtube.com/watch?v=YK78FU5Ffjw&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=14


    // TC: O(n*n!)
    public static void func(int nums[], Vector<Integer> empty, List<List<Integer>> ans, boolean isPicked[]) {
        if (nums.length == empty.size()) {
            ans.add(new ArrayList<>(empty));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!isPicked[i]) {
                isPicked[i] = true;
                empty.add(nums[i]);
                func(nums, empty, ans, isPicked);

                empty.remove(empty.size() - 1);
                // func(nums, start + 1, empty, ans, isPicked);---NOT REQ
                isPicked[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> ans = new ArrayList<>();
        int nums[] = { 1, 2, 3 };
        func(nums,  new Vector<>(), ans, new boolean[nums.length]);
        System.out.println(ans);

        //ex: find 4th permuatation:
        System.out.println(kThPermuation(3, 4));
    }



    //VIMP:


    //find kth permutation:
    /*
    The set [1, 2, 3, ..., n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

 

Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"
Example 3:

Input: n = 3, k = 1
Output: "123"
*/
    //https://www.youtube.com/watch?v=wT7gcXLYoao&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=21

    //TC:O(n^2)
    public static String kThPermuation(int n,int k){
        //find factroial :
        int fact=1;
        List<Integer>numbers=new ArrayList<>();
        for (int i = 1; i < n; i++) {
            fact=fact*i;
            numbers.add(i);
        }
        numbers.add(n);
        //now fact has value (n-1)!

        String ans="";
        k=k-1;//coz if asked 17th permuation we need 16th as per 0 indexing
        while (true) {
            ans=ans+numbers.get(k/fact);
            numbers.remove(k/fact);
            if (numbers.size()==0) {
                break;
            }
            k=k%fact;
            fact=fact/numbers.size();
        }
        return ans;
    }



}
