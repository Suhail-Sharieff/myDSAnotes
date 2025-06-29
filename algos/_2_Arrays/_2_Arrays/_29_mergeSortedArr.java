package _2_Arrays;

/*
 You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

 

Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
Example 2:

Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].
Example 3:

Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
 

Constraints:

nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
-109 <= nums1[i], nums2[j] <= 109
 

Follow up: Can you come up with an algorithm that runs in O(m + n) time?
 */


public class _29_mergeSortedArr {
    //2 pointers appraoch usinf  extra space
    public static void brute(int nums1[], int m, int nums2[], int n) {//O(m+n)----O(m+n)
        int a = 0, b = 0;
        int extra[] = new int[m + n];
        int i = 0;
        
        while (a < m && b < n) {
            if (nums1[a] <= nums2[b]) {
                extra[i++] = nums1[a++];
            } else {
                extra[i++] = nums2[b++];
            }
        }
        //if nums1[] has some larger elemnts
        while (a < m) {
            extra[i++] = nums1[a++];
        }
        //if nums1=2[] has some larger elemnts
        while (b < n) {
            extra[i++] = nums2[b++];
        }
        
        for (int j = 0; j < m + n; j++) {
            nums1[j] = extra[j];
        }
    }


    public static void optimal(int nums1[],int nums2[],int m,int n){//withou t using eextra space
        //intution:
        //basically we need all smaller towards left ie nums1 side and all bigger on right,
        // start checking the ement at rightmost part of nums1 ie (largest of nums1) with (smallest of nums2)....to make the condition meet if we somehow ensure that last of nums1 ie (largest in nums1)< first of nums2 ie (smallest of nums2)...means that the largest of nums1 is smaller than first elemnt of nums2...ie just joining two arrays,we would obtain ans

        int i = m - 1; // last element of nums1
        int j = n - 1; // last element of nums2
        int k = m + n - 1; // last position of merged array

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }

        
    }



    public static void gapMethod(int nums1[],int nums2[],int m,int n){
        // int gap=(int)Math.ceil((m+n)/2f);..or
        int gap=((m+n)/2)+((m+n)%2);
        int len=m+n;
        while (gap>0) {
            int i=0;
            int j=i+gap;
            //till right crosss boundary
            while (j<len) {
                if (i<m&&j>=m) {//if left is on nums1 && right is on nums2
                    swapIfGreater(nums1, nums2, i, j-m);//IMP-->(suppose right is on idx p of right side how do u get this p when u know length of nums1? ie nums1.length-p)
                }else if(i>=m){//if left and right both on nums2
                    swapIfGreater(nums2, nums2, i-m, j-m);
                }else{//both on nums1 side
                    swapIfGreater(nums1, nums1, i, j);
                }
                i++;j++;
            }
            if (gap==1) {
                break;
            }
            gap=((gap)/2)+((gap)%2);
        }
    }
    public static void swapIfGreater(int nums1[],int nums2[],int i,int j){
        if (nums1[i]>nums2[j]) {
            int temp=nums1[i];
            nums1[i]=nums2[j];
            nums2[j]=temp;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        // brute(nums1, m, nums2, n);
        // optimal(nums1, nums2, m, n);
        
        gapMethod(nums1, nums2, m, n);
        
        for (int e : nums1) {
            System.out.print(e + " ");
        }
    }
    
}
