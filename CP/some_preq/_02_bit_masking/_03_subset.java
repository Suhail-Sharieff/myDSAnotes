package some_preq._02_bit_masking;

import java.util.ArrayList;
import java.util.List;

public class _03_subset {
    public static void main(String[] args) {
        //Get all subsets of nums in O(n) complexity
        int nums[]={1,2,3};
        List<String>binaries=get_binary(nums);
        for(var str:binaries) System.out.println(get_numbs(nums, str));
    }

    static List<String> get_binary(int nums[]){//O(n)
        List<String>li=new ArrayList<>();
        int till=1<<nums.length;
        for(int i=0;i<till;i++){
            li.add(get_binary(i,nums.length));
        }
        return li;
    }

    static List<Integer> get_numbs(int nums[],String s){//O(1) approx
        List<Integer>ans=new ArrayList<>();
        for (int i = 0; i < s.length(); i++) if (s.charAt(i) == '1') ans.add(nums[i]);
        return ans;
    }

    static String get_binary(int n,int len){
        StringBuilder sb=new StringBuilder();
        for(int i=len-1;i>=0;i--) if((n&(1<<i))!=0) sb.append(1); else sb.append(0);
        return sb.toString();
    }
}
