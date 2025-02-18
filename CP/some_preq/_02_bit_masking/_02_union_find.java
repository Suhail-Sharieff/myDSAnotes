package some_preq._02_bit_masking;
//prereq: we can set ith bit to 1 of binary number x using "x|(1<<i)"

import java.util.ArrayList;
import java.util.List;

public class _02_union_find {
    public static void main(String[] args) {
        //Find union of nums1 and nums2 given that Max Number in any array would be 10(to use bit mask i we can get up to 31) in TC:O(n1+n2), traditional way of finding union would be to sort both arrays, use 2 ptr approach and get common elemnts which has TCL nlogn, but using bit maipulation we can do it in O(len1+len2)
        int nums1[]={0,1,2,3,4};
        int nums2[]={3,4,5,6,7};

        int b1=get_binary_form_Of_array(nums1);
        int b2=get_binary_form_Of_array(nums2);

        //now we have got binary strings each in which nums[i]th character is set as 1
        System.out.println(Integer.toBinaryString(b1));
        System.out.println(Integer.toBinaryString(b2));
        
        int and=b1&b2;
        //the position in this string having 1 is the common number among nums1 and nums2
        System.out.println("Commin number in nums1 and nums2 are:"+get_positions_of_ones(and));


    }   

    static int get_binary_form_Of_array(int nums[]){//o(n)
        //given that it an be max size 10
        int x=0;
        for(int e:nums) x|=(1<<e);
        return x;
    }
    static List<Integer>get_positions_of_ones(int x){//~~ O(1)
        String bin=Integer.toBinaryString(x);
        List<Integer>ans=new ArrayList<>();
        for(int i=0;i<bin.length();i++) if(bin.charAt(i)=='1') ans.add(bin.length()-i-1);
        ans.stream().mapToInt(Integer::intValue).toArray();
        return ans;
    }
}
