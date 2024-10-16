package _1_recursion.multipleCall;

import java.util.List;
import java.util.Vector;

public class _4_countSubSeqWithSumK {
    public static int  func(Vector<Integer>full,Vector<Integer>empty,int start,int targetSum,int sum){
        if (start>=full.size()) {
            if (sum==targetSum) {
                return 1;//return 1 if statisfied
            }
            return 0;//return 0 if not satisfied
        }
        empty.add(full.get(start));
        sum+=full.get(start);
        int x=func(full, empty, start+1, targetSum, sum);
        empty.remove(empty.size()-1);
        sum-=full.get(start);
        int y=func(full, empty, start+1, targetSum, sum);

        return x+y;

    }
    public static void main(String[] args) {
        int ans=func(new Vector<>(List.of(1,2,3,4,5,6,7,8,9,10)), new Vector<>(), 0, 12, 0);
        System.out.println(ans);//no of subSeqWithSum 12

    }


    //check if atlast one such subsequence exists or not:
    public static boolean help(int[] nums,int sum, int i){
        if(sum == 0) return true;
        if(sum < 0 || i == nums.length) return false;
        
        boolean pick = false;
        if(nums[i] <= sum){
            pick = help(nums,sum - nums[i],i + 1);
            if(pick) return true;
        }
        boolean notpick = help(nums,sum,i + 1);
        if(notpick) return true;
        return (notpick || pick);
    }
}
