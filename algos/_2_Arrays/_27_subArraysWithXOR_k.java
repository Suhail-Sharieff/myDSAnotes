import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/*
Properties of XOR include:
XOR is commutative. This means, a ^ b = b ^ a.
XOR is associative. This means, a ^ (b ^ c) = (a ^ b)^ c = (a ^ c)^ b.
XOR is self-inverse. This means, any number XOR’ed with itself evaluates to 0.
XOR is true if and only if the inputs differ (one is true, one is false)2.
XOR is true if and only if the number of true inputs is odd.
XOR of anything with 0 is that elemnt itsel a^0=a.

For ex:
let a=10--->1010 in binary
let b=6---->0110 in binary
applying rules for corresponding places of a & b
we get a^b =1100 in binary ie 12

 */
public class _27_subArraysWithXOR_k {

    public static boolean XOR(List<Integer>li,int target){
        int xor=0;
        for (int  e : li) {
            xor^=e;
        }
        return xor==target;
    }

    //brute is to generate all subarrays, check if each of it has xor of all ements equal to target
    public static void brute1(List<List<Integer>> ans, Vector<Integer> empty,  int target, int nums[], int start) {
        if (start >= nums.length) {
            if (XOR(empty, target)) {
                ans.add(new ArrayList<>(empty));
                return;
            }
            return;
        }
        empty.add(nums[start]);
        brute1(ans, empty, target, nums, start + 1);
        empty.removeLast();
        brute1(ans, empty,target, nums, start + 1);
    }

    public static void brute2(int nums[],int target){//O(n^3)
        List<List<Integer>>ans=new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                //compue the xor of that subarray from i to j
                int xor_from_i_j=0;
                for (int k = i; k <=j; k++) {
                    xor_from_i_j^=nums[k];
                }
                //for that subarray if its equal to target
                if (xor_from_i_j==target) {
                    List<Integer>thatSubArr=new ArrayList<>();
                    for (int k = i; k <=j; k++) {
                        thatSubArr.add(nums[k]);
                    }
                    ans.add(thatSubArr);
                }
            }

        }
        System.out.println(ans);
    }
    public static void better(int nums[],int target){//O(n^2)
        List<List<Integer>>ans=new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int xor=0;
            for (int j = i; j < nums.length; j++) {
               xor^=nums[j];
               if (xor==target) {
                List<Integer>thatSubArr=new ArrayList<>();
                for (int k = i; k <=j; k++) {
                    thatSubArr.add(nums[k]);
                }
                ans.add(thatSubArr);
               }
            }

        }
        System.out.println(ans);
    }
    //COMPULSORY TO UNDERSTAND:
    //https://www.youtube.com/watch?v=eZr-6p0B7ME
    public static void optimal(int nums[],int target){//but  this soln u cant just count the number of such subarrays n not print them---O(n)---O(n)
        HashMap<Integer,Integer>hs=new HashMap<>();
        int currXOR=0;
        int cnt=0;
        hs.put(0,1);//coz we start with xor=0
        for (int i = 0; i < nums.length; i++) {
            currXOR=currXOR^nums[i];
            int requiredXORtomakecurrXORasTarget=currXOR^target;
            cnt+=hs.getOrDefault(requiredXORtomakecurrXORasTarget, 0);
            hs.put(currXOR, hs.getOrDefault(currXOR, 0)+1);
            
        }
        System.out.println(cnt);
    }
    public static void main(String[] args) {
        //brute force
        List<List<Integer>> ans = new ArrayList<>();
        brute1(ans, new Vector<>(), 6, new int[] { 4,3,3,6,4}, 0);
        System.out.println(ans);
        brute2(new int[] { 4,3,3,6,4}, 6);
        better(new int[] { 4,3,3,6,4}, 6);
        optimal(new int[] { 1,2,3,2}, 2);
    }
}
