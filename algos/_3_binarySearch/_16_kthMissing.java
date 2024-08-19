package _3_binarySearch;

import java.util.ArrayList;
import java.util.List;

/*
Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

Return the kth positive integer that is missing from this array.

 

Example 1:

Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
Example 2:

Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 

Constraints:

1 <= arr.length <= 1000
1 <= arr[i] <= 1000
1 <= k <= 1000
arr[i] < arr[j] for 1 <= i < j <= arr.length
 

Follow up:

Could you solve this problem in less than O(n) complexity?
 */
public class _16_kthMissing {

    public int brute(int[] nums, int k) {
        List<Integer> missing = new ArrayList<>();
        int i = 1;
        int j = 0;
        while (j < nums.length) {
            if (nums[j] != i) {
                missing.add(i);
            } else {
                j++;
            }
            i++;
        }
        for (int z = i; z <= 1000 + k; z++) {// coz what if k is very high
            missing.add(z);
        }

        return missing.get(k - 1);
    }

    public static int better(int arr[], int k) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= k) {
                k++;
            } else {
                break;
            }
        }
        return k;
    }

    public static int optimal(int arr[],int k){
        //WATCH THIS MIND BLOWING SOLN:
        //https://www.youtube.com/watch?v=uZ0N_hZpyps&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=60

        // int howManyMissingTillThatIndex[]=new int[arr.length];
        // for (int i = 0; i < arr.length; i++) {
        //     howManyMissingTillThatIndex[i]=arr[i]-(i+1);
        // }

        int low=0,high=arr.length-1;
        while (low<=high) {
            int mid=(low+high)/2;
            int nOfMissingTillThere=arr[mid]-(mid+1);
            if (nOfMissingTillThere<k) {
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return high+1+k;
    }
}
