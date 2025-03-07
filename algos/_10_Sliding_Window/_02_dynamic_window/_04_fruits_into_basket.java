package _10_Sliding_Window._02_dynamic_window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

/*


preq: prev file



904. Fruit Into Baskets
Medium
Topics
Companies
You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.

 

Example 1:

Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.
Example 2:

Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
Example 3:

Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].
 

Constraints:

1 <= fruits.length <= 105
0 <= fruits[i] < fruits.length
 */
public class _04_fruits_into_basket {
    public static void main(String[] args) {
        int fruits[]={1,2,3,2,2};
        int ans=0;
        int i=0,j=0;
        Set<Integer>set=new HashSet<>();
        while (j<fruits.length) {
            if (set.size()<=2) {
                set.add(fruits[j]);
            }else{
                while (i<fruits.length && set.size()>2) {
                    set.remove(fruits[i++]);
                }
            }
            ans=Math.max(ans, j-i+1);
            j++;
        }
        System.out.println(ans);

    }


    //-----------------brute force
    public int brute_force(int[] fruits) {
        int ans = 0;
        for (int i = 0; i < fruits.length; i++) {
            HashSet<Integer> hs = new HashSet<>();
            int idx = i;
            for (int j = i; j < fruits.length; j++) {
                hs.add(fruits[j]);
                if (hs.size() > 2) {
                    break;
                }
                idx = j;
            }
            ans = Math.max(ans, idx - i + 1);
        }

        return (ans);
    }



    //------------------optimal 
    public int optimal(int fruits[]){
        int ans=0;
        int i=0,j=0;
        HashMap<Integer,Integer>hs=new LinkedHashMap<>();//why used hashmap and not set, coz when u say set.remove(fruits[i]), it removes all occursnces of that instead of just one
        while (j<fruits.length) {
            hs.put(fruits[j],hs.getOrDefault(fruits[j],0)+1);//MISTAKE: added this under if(hs.size()<2) which is wrong, coz the elemnts may repeat despite of having hashmap size more than 2 which is allowed in question, so first put and then check
            if(hs.size()>2){
                while (hs.size()>2) {
                    hs.put(fruits[i],hs.getOrDefault(fruits[i],0)-1);
                    if(hs.get(fruits[i])==0) hs.remove(fruits[i]);
                    i++;//MISTAKE: added this i++ outside while loop
                }
            }
            ans=Math.max(ans, j-i+1);
            j++;
        }
        return (ans);
    }



    //------------------FOLLOW UP:
    /*
992. Subarrays with K Different Integers
Solved
Hard
Topics
Companies
Hint
Given an integer array nums and an integer k, return the number of good subarrays of nums.

A good array is an array where the number of different integers in that array is exactly k.

For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.

 

Example 1:

Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
Example 2:

Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 

Constraints:

1 <= nums.length <= 2 * 104
1 <= nums[i], k <= nums.lengths
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        return good_subarray_with_max_k_distincts(nums,k)-good_subarray_with_max_k_distincts(nums,k-1);
    }
    public int good_subarray_with_max_k_distincts(int nums[],int k){
        HashMap<Integer,Integer>hs=new HashMap<>();
        int left_ptr=0,right_ptr=0,ans=0;
        while(right_ptr<nums.length){
            hs.put(nums[right_ptr],hs.getOrDefault(nums[right_ptr],0)+1);
            while(hs.size()>k){
                hs.put(nums[left_ptr],hs.getOrDefault(nums[left_ptr],0)-1);
                if(hs.get(nums[left_ptr])==0) hs.remove(nums[left_ptr]);
                left_ptr++;
            }
            ans+=right_ptr-left_ptr+1;
            right_ptr++;
        }
        return ans;
    }
}
