package _2_Arrays;
//https://www.youtube.com/watch?v=oO5uLE7EUlM&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=29
import java.util.*;


/*
 Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

 

Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
 

Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
 */
public class _21_longestConseSeq {
    public static void main(String[] args) {
        int arr[] = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(better(arr));
        System.out.println(optimal(arr));
        

    }

    public static int better(int arr[]) {//O(n)
       //iterate throughout,AFTER SORTING
       //if u encounter ,say of and elemnt e, u encounter e+1, increase coount by one and make that e+1 as new lastSmallest seen so far, if the lastSmallestSeenSoFar is not as same as encountered e,make the e as lastSmallestSeenSofar
       Arrays.sort(arr);
       int lastSmallestSeenSoFar=Integer.MIN_VALUE;
       int count=0;
       int ans=1;
       for (int i = 0; i < arr.length; i++) {
        if (arr[i]-1==lastSmallestSeenSoFar) {
            count++;
            lastSmallestSeenSoFar=arr[i];
        }else if(arr[i]==lastSmallestSeenSoFar){
            //dont do anything (this handles repetitions)
        }else if(arr[i]!=lastSmallestSeenSoFar){
            count=1;
            lastSmallestSeenSoFar=arr[i];
        }
        ans=Math.max(ans, count);
       }
       return ans;
    }

    public static int optimal(int arr[]){//O(1) iterations
        //covert arr to a HashSet, there by removing duplaictes
        //now suppose set be:1,2,3,4,101,102
        //{1,2,3,4} here,1 can never have 1-1 ie 0,so 1 is the starting point in {1,2,3,4},
        // {101,102} there is no 100, so 101 is starting point to check

        HashSet<Integer>se=new HashSet<>();
        for (int e : arr) {
            se.add(e);
        }
        int ans=1;
        for (Integer e : se) {
            if (!se.contains(e-1)) {
                //this is the point where where i need to start looking from e+1...
                int temp=e;
                int cnt=1;
                while (se.contains(temp+1)) {
                    temp=temp+1;
                    cnt++;
                }
                ans=Math.max(ans, cnt);
            }
        }
        return ans;
    }
}
