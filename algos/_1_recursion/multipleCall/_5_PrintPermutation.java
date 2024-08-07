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
        int nums[] = { 1, 2, 3 };Arrays.sort(nums);//for topo
        func(nums,  new Vector<>(), ans, new boolean[nums.length]);
        System.out.println(ans);

        //ex: find 4th permuatation:
        System.out.println(kThPermuation(3, 4));


        //find next Permuation of ex : [3,1,2]--brute
        //sort it first : [1,2,3]
        System.out.println(bruteNextPermutation(Arrays.asList(3,1,2), ans));


        //find next Permuation of ex : [3,1,2]--optimal
        //sort it first : [1,2,3]
        System.out.println(optimalNextPermutation(Arrays.asList(1,3,2)));
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

  
    //find next permutation -----IMP
    /*
     You are given an array ‘a’ of ‘n’ integers.



You have to return the lexicographically next to greater permutation.



Note:

If such a sequence is impossible, it must be rearranged in the lowest possible order.


Example:

Input: 'a' = [1, 3, 2]

Output: 2 1 3

Explanation: All the permutations of [1, 2, 3] are [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1], ]. Hence the next greater permutation of [1, 3, 2] is [2, 1, 3].


Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
3
3 1 2


Sample Output 1:
3 2 1


Explanation Of Sample Input 1:
Input:
A = [3, 1, 2]
Output:
3 2 1

Explanation: All the permutations of [1, 2, 3] are [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1], ]. Hence the next greater permutation of [3, 1, 2] is [3, 2, 1].


Sample Input 2:
3
3 2 1


Sample Output 2:
1 2 3
     */
    public static List<Integer> bruteNextPermutation(List<Integer>whoseNext,List<List<Integer>>reference){
        if (reference.indexOf(whoseNext)==reference.size()-1) {
            return reference.get(0);
        }
        return(reference.get(1+reference.indexOf(whoseNext)));
    }
    public static List<Integer> optimalNextPermutation(List<Integer>question){
         /*Consider a permutation [2,1,3] , it has [2,3,1] on next
            let x=[2,1,3]
            idx:   0 1 2

            observe that the next permuation will have something common and since we r ding it in topological order the next permutation wil, obviously greater than given x


            STEP1:start iterating throughout x to find the index so called the "breakPointIdx" where arr[i]<arr[i+1]
            STEP2:let e=arr[i]{here breakPointIdx=1}
            now iterare throught from thi breakIdx to fing the elemnt which is graeter than elemnt at breakIdx and the smallest (coz in topo it will be obviously SMALL NEARER), here its 3
            STEP3:swap elemnt at breakPointidx and this SMALLest NEAR elemnt
            STEP4:sort all elemnts from breakPointIdx till ed in REV order
            
          */
          int n = question.size();
        
        // Step 1: Find the rightmost break point
        int breakPointIdx = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (question.get(i) < question.get(i + 1)) {
                breakPointIdx = i;
                break;
            }
        }

        // If no break point is found, it means we are at the last permutation
        if (breakPointIdx == -1) {
            Collections.reverse(question);
            return question;
        }

        // Step 2: Find the smallest element in the suffix that is greater than question.get(breakPointIdx)
        int smallestGreaterIdx = n - 1;
        for (int i = n - 1; i > breakPointIdx; i--) {
            if (question.get(i) > question.get(breakPointIdx)) {
                smallestGreaterIdx = i;
                break;
            }
        }

        // Step 3: Swap the elements at breakPointIdx and smallestGreaterIdx
        Collections.swap(question, breakPointIdx, smallestGreaterIdx);

        // Step 4: Reverse the suffix starting from breakPointIdx + 1
        Collections.reverse(question.subList(breakPointIdx + 1, n));

        return question;
        
          
    }

}
