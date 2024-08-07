package _2_Arrays;

import java.util.ArrayList;
import java.util.List;

public class _19_leadersInArr {
    // u r given an array , u have to return a list of integers in the array which are greater than all elemnts on its right(they r called as leaders)
    public static void main(String[] args) {
        System.out.println(optimal(new int[]{10,22,12,3,0,6}));

    }
    //brute force is to for each elent in the array,trvaerse on right from its index and check if its greater till the right end,--O(n^2)
    public static List<Integer> optimal(int arr[]){
        int MaxOfAllOnRight=Integer.MIN_VALUE;
        List<Integer>ans=new ArrayList<>();
        for (int i = arr.length-1; i >=0; i--) {
            if (MaxOfAllOnRight<arr[i]) {
                ans.add(arr[i]);
            }
            MaxOfAllOnRight=Math.max(MaxOfAllOnRight, arr[i]);
        }
        return ans;
    }
}
