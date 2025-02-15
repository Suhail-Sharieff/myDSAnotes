package _6_DynamicProgramming._04_Strings._01_comparision.Subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
Given an integer array nums, return the length of the longest strictly increasing 
subsequence or another way of same question:Given a set of distinct positive integers nums, return the largest subsequnce length as answer such that every pair (nums[i], nums[j] ) of elements in this subset satisfies nums[i]< nums[j] for all i<j
.

 

Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1
 

Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104
 

Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class _05_longest_increasing_subseq {
    public static void main(String[] args) {
        int nums[] = { 10, 9, 2, 5, 3, 7, 101, 18 };
        test(nums, 0, new ArrayList<>(), Integer.MIN_VALUE);
    }

    static void test(int nums[], int i, List<Integer> empty,  int prev) {
        if (i >= nums.length) {
            System.out.println(empty);
            return;
        }
        //pick logic
        if(prev==Integer.MIN_VALUE || nums[i]>prev){
            empty.add(nums[i]);
            test(nums, i+1, empty, nums[i]);
            empty.remove(empty.size()-1);
        }
        test(nums, i+1, empty, prev);
    }


    //----------------recurive:2^n
    public int rec(int nums[],int i,int prev_idx){
        if(i>=nums.length-1) return 0;
        int pick_curr=(prev_idx==-1 || nums[i]>nums[prev_idx])?1+rec(nums, i+1, i):0;
        int dont_pick_curr=rec(nums, i+1, prev_idx);
        return Math.max(pick_curr, dont_pick_curr);
    }


    //----------------brute memoizaton(coz see that prev_idx we r passing as negative)---TLE 
    public int brute_mem(int nums[],int i,int prev_idx,HashMap<String,Integer>dp){
        if(i>=nums.length) return 0;
        if(dp.containsKey(i+"|"+prev_idx)) return dp.get(i+"|"+prev_idx);
        int pick_curr=(prev_idx==-1 || nums[i]>nums[prev_idx])?1+brute_mem(nums, i+1, i,dp):0;
        int dont_pick_curr=brute_mem(nums, i+1, prev_idx,dp);
        dp.put(i+"|"+prev_idx,Math.max(pick_curr, dont_pick_curr));
        return dp.get(i+"|"+prev_idx);
    }


    //------------------smart mem,teaches how u can hadle -ve idx as well, since u kow that prev_idx can vary from -1 to n ie only 1 negative value,instaed of storing precomputed value at dp[idx][prev_idx], store at dp[idx][prev_idx+1], solves preoblem of prev_idx being -ve
    public int top_down_mem(int nums[],int idx,int prev_idx,int dp[][]){//)(n^2)
        if(idx>=nums.length) return 0;
        if(dp[idx][prev_idx+1]!=-1) return dp[idx][prev_idx+1];
        int pick_curr=(prev_idx==-1 || nums[idx]>nums[prev_idx])?1+top_down_mem(nums, idx+1, idx,dp):0;
        int dont_pick_curr=top_down_mem(nums, idx+1, prev_idx,dp);
        dp[idx][prev_idx+1]=Math.max(pick_curr, dont_pick_curr);
        return dp[idx][prev_idx+1];
    }

    //-----tab:
    public int top_down_tab(int nums[]){
        int dp[][]=new int[nums.length][nums.length+1];
        //see in the above recursive solution that we r moving from 0 to end, so in tab we move revrese
        int len=nums.length;
        for(int idx=len-1;idx>=0;idx--){//idx moving from n-1 to 0 , here reverse
            for(int prev_idx=idx-1;prev_idx>=-1;prev_idx--){//prev_idx moving from  idx-1 to -1, revrese------VVVIMP Skill,also mind that for dp[x][y], we need to always take dp[x][y+1]---IMP
                int pick_curr=(prev_idx==-1 || nums[idx]>nums[prev_idx])?1+dp[idx+1][idx+1]:0;//MISTAKE: took as dp[idx+1][idx]
                int dont_pick_curr=dp[idx+1][prev_idx+1];//make sure its prev_idx+1
                dp[idx][prev_idx+1]=Math.max(pick_curr, dont_pick_curr);
            }
        }
        return dp[0][0];
    }

    /////////////--prere to write tab code: read keys.md, hwo to write tab code:
    public int down_top_mem(int nums[],int idx,int prev_idx,int dp[][]){
        if(idx<0) return 0;
        if(dp[idx][prev_idx+1]!=-1) return dp[idx][prev_idx+1];
        int pick=(prev_idx==-1||nums[idx]<nums[prev_idx])?1+down_top_mem(nums,idx-1,idx,dp):0;
        int dont_pick=down_top_mem(nums,idx-1,prev_idx,dp);
        dp[idx][prev_idx+1]=Math.max(pick,dont_pick);
        return dp[idx][prev_idx+1];
    }

   //tabulation is complex for this, just folow above's tabulation

   //------------------apCE OPTIMIZATION{TAKEN WRT TO TOP_DOWN_TAB}
    public int space_optimal_better(int nums[]){//assume dp[idx] as curr, dp[idx+1] as next//O(n^2)-----O(2n)
        int len=nums.length;
        int next[]=new int[len+1];
        for(int idx=len-1;idx>=0;idx--){
            int curr[]=new int[len+1];
            for(int prev_idx=idx-1;prev_idx>=-1;prev_idx--){
                int pick_curr=(prev_idx==-1 || nums[idx]>nums[prev_idx])?1+next[idx+1]:0;
                int dont_pick_curr=next[prev_idx+1];
                curr[prev_idx+1]=Math.max(pick_curr, dont_pick_curr);
            }
            next=curr.clone();
        }
        return next[0];
    }

    //https://www.youtube.com/watch?v=IFfYfonAFGc&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=43&t=235s&ab_channel=takeUforward
    public int space_optimal_best(int nums[]){//O(n^2)----O(n)--------Its also an algorithmc approach
        /*
        Approach:initialize maxLen(ie max_lengths array) to 1. For each i, go from 0 to i, if nums[j]<nums[i], take 1+lenOfThatSegment
         */
        int len=nums.length;
        int max_lengths[]=new int[len];
        Arrays.fill(max_lengths, 1);
        for(int i=1;i<len;i++){
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    max_lengths[i]=Math.max(max_lengths[i], 1+max_lengths[j]);
                }
            }
        }
        //max length can be present anywhere in ans array
        int max=0;
        for(int e:max_lengths) max=Math.max(e,max);
        return max;
    }

    //--------------------printing LIS(uses the spae_optimal_best)
    //idea: if we get that nums[i]>nums[j], we updtate max_lengths, also then we will take parent of that thrgh which its able to increase its length, ie in parent array we store that parent elemnent's idx becozuse of which its max_len incresed
    public void print_LIS(int nums[]){
        int len=nums.length;
        int max_lengths[]=new int[len];

        int parent[]=new int[len];
        for(int i=0;i<len;i++) parent[i]=i;

        Arrays.fill(max_lengths, 1);
        for(int i=1;i<len;i++){
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    if (1+max_lengths[j] > max_lengths[i]) {
                        max_lengths[i]=1+max_lengths[j];
                        parent[i]=j;
                    }
                }
            }
        }
        //get idx where we have max_len
        int max_len_idx=0;
        int max=0;
        for(int i=0;i<len;i++){
            if(max_lengths[i]>max){
                max=max_lengths[i];
                max_len_idx=i;
            }
        }
        List<Integer>lis=new ArrayList<>();
        lis.add(nums[max_len_idx]);
        while (parent[max_len_idx]!=max_len_idx) {
            max_len_idx=parent[max_len_idx];
            int par=nums[max_len_idx];
            lis.add(par);
        }
        System.out.println(lis.reversed());
    }




    //-----------------------------more optimal approach: o(nLogn)-----o(n)
    //Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?

    
    //the below algorithm is intuition behind the optimal approach
    /*
    public static void print_all_increasing_subsequences(int nums[]){
        List<List<Integer>>li=new ArrayList<>();
        li.add(new ArrayList<>());
        li.get(0).add(nums[0]);
        int sz=1;
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<sz;j++){


                int last_elemnt_in_that_list=li.get(j).getLast();
                if(last_elemnt_in_that_list<nums[i]){
                    li.get(j).add(nums[i]);
                }else{
                    List<Integer>new_list=new ArrayList<>();
                    new_list.add(nums[i]);
                    li.add(new ArrayList<>(new_list));
                    sz++;
                }
            }
        }
        System.out.println(li);
        //then to print LIS, return the longest in li
    }
        */

        //but we can't use the above algo coz its extremely heavy operation such that it exceeds Memory limit just for an array size 3,so we can use same approach , but we will create only 1 list, overide the same lsit again and again, note that we cant use this method to print LIS, coz we r replacing elemnts and thus it may not be in subsequnce order, but it always gives corect lenth of subseq
        //prereq: Collections.binarySerch(list,key) will return the idx of key if found, OR else it returns the negative 1 based indexing of the pos where that elemnt must be inserted ie lowerbound=abs(idxReturned)-1;
        public static void most_optimal(int nums[]){
            List<Integer>li=new ArrayList<>();
            li.add(nums[0]);
            for(int i=1;i<nums.length;i++){
                int last_elemnt_in_that_list=li.getLast();
                if(nums[i]>last_elemnt_in_that_list){
                    li.add(nums[i]);
                }else{
                    int idx_to_replace=Collections.binarySearch(li,nums[i]);
                    if(idx_to_replace<0) idx_to_replace=Math.abs(idx_to_replace)-1;
                    li.set(idx_to_replace, nums[i]);
                }
            }
            System.out.println(li);
        }






        //----------------------------------------Follow up: Find number of longest increasing subsequences
        /*
        Given an integer array nums, return the number of longest increasing subsequences.

Notice that the sequence has to be strictly increasing.

 

Example 1:

Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:

Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.
 

Constraints:

1 <= nums.length <= 2000
-106 <= nums[i] <= 106
The answer is guaranteed to fit inside a 32-bit integer.
         */


        // https://www.youtube.com/watch?v=cKVl1TFdNXg&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=49&ab_channel=takeUforward

         //soln is lil modification of LIS implm, we will also maintain a count array, whenevr we see that dp[i]<dp[j]+1, we will normally update dp[i] to dp[j]+1, also  we will take cnt values, cnt[i]=cnt[j], but however whenever we see that dp[i]==dp[j]+1, ie we have encountered another ith elemnt that is smaller than nums[j] and is reusable, we will say cnt[i]+=cnt[j], also then atlast, get pos of max_lenght from dp array, 
         public int count_nOf_LIS(int nums[]){
            int dp[]=new int[nums.length];
            int cnt[]=new int[nums.length];
            int max_length=0;
            for(int i=0;i<nums.length;i++){
                dp[i]=cnt[i]=1;
                for(int j=0;j<i;j++){
                    if (nums[i]>nums[j]) {
                        if(dp[i]<dp[j]+1){
                            dp[i]=dp[j]+1;
                            cnt[i]=cnt[j];
                        }else if(dp[i]==dp[j]+1){//found a resuable eelent
                            cnt[i]+=cnt[j];
                        }
                    }
                }
                if(max_length<dp[i]){
                    max_length=dp[i];
                }
            }
            int ans=0;
            for(int i=0;i<nums.length;i++) if(dp[i]==max_length) ans+=cnt[i];
            return ans;
         }
}
