package _2_Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
//find all subarrays of size 2, which sum upto the give target sub
public class _12_2Sum_type1 {
    


    public static List<List<Integer>> brute(int arr[],List<List<Integer>>ans,int targetSum){//for each elemnt iterate again via each 
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i]+arr[j]==targetSum) {
                    ans.add(new ArrayList<>(Arrays.asList(arr[i],arr[j])));
                }
            }
        }
        return ans;
    }
    public static List<List<Integer>> better(int arr[],List<List<Integer>>ans,int targetSum){//using hashing--O(n)---O(n)
        HashMap<Integer,Integer>hs=new HashMap<>();
        for (int e : arr) {
            hs.put(e, hs.getOrDefault(e, 0)+1);
        }
        for (int e : arr) {
            int currSum = e;
            int stillRequiredSum = targetSum - currSum;
            
            if (hs.containsKey(stillRequiredSum) && hs.get(stillRequiredSum) > 0) {
                // Check if this is a valid pair
                if (currSum == stillRequiredSum && hs.get(stillRequiredSum) < 2) {
                    continue; // Skip if both elements are the same and there is only one occurrence to avoid suplicates tike [8,6]&[6,8]
                }
                
                ans.add(Arrays.asList(currSum, stillRequiredSum));
                
                // Decrement the frequency in hashmap to avoid duplicates
                hs.put(currSum, hs.get(currSum) - 1);
                hs.put(stillRequiredSum, hs.get(stillRequiredSum) - 1);
            }
        }
        return ans;
    }
    public static List<List<Integer>> using2Pointer(int arr[], List<List<Integer>> ans, int targetSum) {
        Arrays.sort(arr); // Sort the array (O(n log n))
    
        int left = 0;
        int right = arr.length - 1;
    
        while (left < right) {
            int sum = arr[left] + arr[right];
    
            if (sum == targetSum) {
                ans.add(Arrays.asList(arr[left], arr[right]));
                left++;
                right--;
                // To avoid duplicate pairs like [2, 6] and [6, 2]
                while (left < right && arr[left] == arr[left - 1])
                    left++;
                while (left < right && arr[right] == arr[right + 1])
                    right--;
            } else if (sum < targetSum) {
                left++;
            } else { // sum > targetSum
                right--;
            }
        }
    
        return ans;
    }
    

    public static void main(String[] args) {
        List<List<Integer>>ans=new ArrayList<>();
        brute(new int[]{2,6,5,8,11}, ans, 14);
        System.out.println(ans);
        ans.clear();
        better(new int[]{2,6,5,8,11}, ans, 14);
        System.out.println(ans);
        ans.clear();
        using2Pointer(new int[]{2,6,5,8,11}, ans, 14);
        System.out.println(ans);
    }
}
