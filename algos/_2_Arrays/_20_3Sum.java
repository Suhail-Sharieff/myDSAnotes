package _2_Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

//given an array ,return all possible list, where sum of all elemnts of the list is 0(same elemnt should not be taken more than once) return UNique triplets
public class _20_3Sum {
    public static void main(String[] args) {
        int arr[]={-1,0,1,2,-1,4};
        List<List<Integer>>ans=new ArrayList<>();
        // rec(ans, new Vector<>(), 0, 0, arr, 0);
        
        //to avoid duplictas,we pass arr after sorting it and storing all possile lists into HashSet
        Arrays.sort(arr);
        System.out.println(brute(arr, ans,new HashSet<>()));
    }

    public static List<List<Integer>> brute(int arr[],List<List<Integer>>ans,HashSet<List<Integer>>hs){
        int len=arr.length;//since elemnts cant be taken again the same once chosen
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                for (int k = j+1; k < len; k++) {
                    if (arr[i]+arr[j]+arr[k]==0) {
                        hs.add(Arrays.asList(arr[i],arr[j],arr[k]));
                    }
                }
            }
        }
        ans.addAll(hs);
        return ans;
    }
    public static void rec(List<List<Integer>>ans,Vector<Integer>empty,int startIdx,int currSum,int arr[],int targetSum){
        if (startIdx>=arr.length) {
            if (empty.size()==3&&currSum==targetSum) {
                ans.add(new ArrayList<>(empty));
            }
            return;
        }
        empty.add(arr[startIdx]);
        currSum+=arr[startIdx];
        rec(ans, empty, startIdx+1, currSum, arr, targetSum);
        empty.remove(empty.size()-1);
        currSum-=arr[startIdx];
        rec(ans, empty, startIdx+1, currSum, arr, targetSum);
    }
}
