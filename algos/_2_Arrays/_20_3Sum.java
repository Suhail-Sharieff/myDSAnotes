package _2_Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

//given an array ,return all possible list, where sum of all elemnts of the list is 0(same elemnt should not be taken more than once) return UNique triplets
public class _20_3Sum {
    public static void main(String[] args) {
        int arr[] = { -1, 0, 1, 2, -1, 4 };
        List<List<Integer>> ans = new ArrayList<>();
        HashSet<List<Integer>> hs = new HashSet<>();
        rec(hs, new Vector<>(), 0, 0, arr, 0);

        System.out.println(brute(arr, ans, new HashSet<>()));
        ans.clear();
        rec(hs, new Vector<>(), 0, 0, arr, 0);
        System.out.println(hs);
        hs.clear();
        better(arr, hs);
        System.out.println(optimal(arr));

    }

    public static List<List<Integer>> brute(int arr[], List<List<Integer>> ans, HashSet<List<Integer>> hs) {// O(n3)
        int len = arr.length;// since elemnts cant be taken again the same once chosen
        // to avoid duplictas,we pass arr after sorting it and storing all possile lists
        // into HashSet
        Arrays.sort(arr);
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (arr[i] + arr[j] + arr[k] == 0) {
                        hs.add(Arrays.asList(arr[i], arr[j], arr[k]));
                    }
                }
            }
        }
        ans.addAll(hs);
        return ans;
    }

    public static void rec(HashSet<List<Integer>> ans, Vector<Integer> empty, int startIdx, int currSum, int arr[],
            int targetSum) {
        if (startIdx >= arr.length) {
            if (empty.size() == 3 && currSum == targetSum) {
                ans.add(new ArrayList<>(empty));
            }
            return;
        }
        empty.add(arr[startIdx]);
        currSum += arr[startIdx];
        rec(ans, empty, startIdx + 1, currSum, arr, targetSum);
        empty.remove(empty.size() - 1);
        currSum -= arr[startIdx];
        rec(ans, empty, startIdx + 1, currSum, arr, targetSum);
    }

    public static void better(int nums[], HashSet<List<Integer>> hs) {// O(n^2 *longn)
        // intution is to check if nums[k] exists in a hashset whuch created for every
        // index i of nums such that nums[k]==-(nums[i]+nums[j]) that ie if there exists
        // -(nums[i]+nums[j]) in created hashset(of eelmnts between i+1 to j) herer i
        // have used list coz hashset in java doent have find method then
        // nums[i]+nums[j]+nums[k] will sum to 0

        Arrays.sort(nums);
        List<Integer> li = new ArrayList<>();
        for (int e : nums) {
            li.add(e);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {// i+1 to avoid rep of same elemnt
                if (!li.isEmpty() && li.contains(-(nums[i] + nums[j]))) {
                    List<Integer> temp = Arrays.asList(nums[i], nums[j], -(nums[i] + nums[j]));
                    Collections.sort(temp);// ro avoid duplicates
                    hs.add(temp);
                }
            }
        }
        System.out.println(hs);
    }
    public static List<List<Integer>> optimal(int arr[]){
        //use 3 ponter approach
        //for every idx  i we move j &k AFTER SORTING ARR,all duplicates come adjacet
        Arrays.sort(arr);
        List<List<Integer>>ans=new ArrayList<>();
        int n=arr.length;
        //to avoid duplicates,check if prev elemnt i same
        for (int i = 0; i < n; i++) {
            if (i>0&&arr[i]==arr[i-1]) {//since the first elemnt ie i=0 doent have prev elemnt no need to check for it its duplicarte
                continue;
            }
            if (arr[i]>0) {
                break;
            }
            //else:
            int j=i+1;
            int k=n-1;
            while (j<k) {
                int currSum=arr[i]+arr[j]+arr[k];
                if (currSum<0) {//means need some greater elemnt toadd to 0
                    j++;
                }else if(currSum>0){//since k is pointing out to largest elemnt (being in sorted array) bring k towards smaller elemnt to  try reduce it to 0
                    k--;
                }else{//sum is 0}
                    ans.add(Arrays.asList(arr[i],arr[j],arr[k]));
                    j++;
                    //what if arr[j]==arr[j-1]
                    while (j<k&&arr[j]==arr[j-1]) {//unless we come out of duplicates
                        j++;
                    }
                   
                }

            }
          
        }
        return ans;
    }
}
