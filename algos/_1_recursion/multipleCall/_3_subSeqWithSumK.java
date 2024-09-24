package _1_recursion.multipleCall;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

/**
 * subSeqWithSumK
 */
public class _3_subSeqWithSumK {
    //func1 and func2 and func5are for subSeq only from array and u cannot repeat arrayElemnts more than their freqency in original array/vector

    public static void func1(Vector<Integer>full,Vector<Integer>empty,int start,int targetSum){///////////TLE
        if (start>=full.size()) {
            if (findSum(empty, targetSum)) {
                System.out.println(empty+" ");
            }
            return;
        }
        empty.add(full.get(start));
        func1(full, empty, start+1, targetSum);
        empty.remove(empty.size()-1);
        func1(full, empty, start+1, targetSum);
    }
    public static boolean findSum(Vector<Integer>empty,int targetSum){
        long sum=0;
        for (Integer e : empty) {
            sum+=e;
        }
        return sum==targetSum;
    }
    //better way:
    public static void func2(Vector<Integer>full,Vector<Integer>empty,int startSum,int startIndex,int targetSum){/////////////no TLE
        if (startIndex>=full.size()) {
            if (startSum==targetSum) {
                System.out.print(empty+" ");
            }
            return;
        }
        empty.add(full.get(startIndex));
        startSum+=full.get(startIndex);//----------------------------^
        func2(full, empty,startSum, startIndex+1, targetSum);//      |
                                                            //      EXTRA
        empty.remove(empty.size()-1);                       //       |
        startSum-=full.get(startIndex);//----------------------------^
        func2(full, empty, startSum, startIndex+1, targetSum);

    
    }
    public static void func5(int nums[],int target,List<Integer>empty,List<List<Integer>>ans,int startIdx){//IF ARR IS SORTED
        if (target==0) {
            ans.add(new ArrayList<>(empty));
            return;
        }
        for (int i =startIdx ; i < nums.length; i++) {
            if (i>startIdx&&nums[i]==nums[i-1]) {//handle first case where start=0 && avoid dupli also
                continue;
            }
            if (nums[i]>target) {
                break;
            }
            empty.add(nums[i]);
            func5(nums, target-nums[i], empty, ans, i+1);
        }
    }


    //func3 and func4 and func6would be for same as func1 and func2 but u can repeat unlimited no if times the same elemnt from orignal array in a subsequence which would sum to given target

    public static void func3(Integer arr[], List<Integer> empty, int start, int target, int sum, HashSet<List<Integer>> hs) {//returns unique subseq list with allowed repitions/////////////////TLE
//ALSO NOTE THAT THIS WOULD WORK IF ARR PASSED HERE HAS MAX POSSIBLE REPETITIONS OF EACH ELEMENT IN IT
        if (start>=arr.length) {
            if (sum==target) {
                hs.add(new ArrayList<>(empty));//not just 'hs.add(empty)'
                return;
            }
            return;
        }

        empty.add(arr[start]);
        sum+=arr[start];
        func3(arr, empty, start + 1, target, sum, hs);

        empty.remove(empty.size() - 1);
        sum-=arr[start];
        func3(arr, empty, start + 1, target, sum, hs);
    }
    //better way:
    public static void func4(int nums[],int target,int start,List<List<Integer>>ans,List<Integer>empty){
        if (start>=nums.length) {
            if (target==0) {
                ans.add(new ArrayList<>(empty));
            }
            return;
        }
        if (nums[start]<=target) {
            empty.add(nums[start]);
            func4(nums, target-nums[start], start, ans, empty);
            empty.remove(empty.size()-1);
        }
        func4(nums, target, start+1, ans, empty);
    }
    //IMP coz remaining may not pass all TC
    public static void func6(int nums[],int target,List<Integer>empty,List<List<Integer>>ans,int startIdx){
        if (target==0) {
            ans.add(new ArrayList<>(empty));
            return;
        }
        for (int i =0 ; i < nums.length; i++) {
            if (i>startIdx&&nums[i]==nums[i-1]) {//handle first case where start=0 && avoid dupli also
                continue;
            }
            if (nums[i]>target) {
                break;
            }
            empty.add(nums[i]);
            func5(nums, target-nums[i], empty, ans, i+1);
        }
    }
   
    public static void main(String[] args) {
        //no repitition allowed:
        // func1(new Vector<>(List.of(1,2,3,4,5,6,7,8,9,10)), new Vector<>(), 0, 5);--TLE
        func2(new Vector<>(List.of(1,2,3,4,5,6,7,8,9,10)), new Vector<>(), 0, 0 , 12);//--no TLE
        //pass nums to func5 only after sorting



        //allowed same elment repetion
        Integer arr[]={1,2,3,4,5};
        int target=12;
        List<Integer> v = new ArrayList<>();
        int maxCanRepeat[] = new int[arr.length];//in this i would store max no of times each elemnt could possible occur in some subsequence that would sum upto target, this value would be obviously (target/thatElemnt)
        
        for (int i = 0; i < arr.length; i++) {
            maxCanRepeat[i] = (target / arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            while (maxCanRepeat[i]-- != 0) {
                v.add(arr[i]);//now v would contain max no of each element required to form target summing subseq
            }
        }
        Integer newArr[] = new Integer[v.size()];
        v.toArray(newArr);
        HashSet<List<Integer>> hs = new HashSet<>();
        func3(newArr, new ArrayList<>(), 0, target, 0, hs);
        List<List<Integer>> result = new ArrayList<>(hs);
        System.out.println(result);

        //better than func3:
        List<List<Integer>> ans = new ArrayList<>();
        int nums[]={2,3,6,7};
        int targetSum=7;
        func4(nums, targetSum, 0, ans, new ArrayList<>());
        System.out.println(ans);


        
    }
}