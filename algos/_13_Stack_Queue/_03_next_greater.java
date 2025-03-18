package _13_Stack_Queue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class _03_next_greater {
    public int[] bruet(int[] nums1, int[] nums2) {//O(n^2)
        HashMap<Integer,Integer>hs=new HashMap<>();
        int idx=0;
        for(int e:nums2) hs.put(e,idx++);
        int ans[]=new int[nums1.length];
        Arrays.fill(ans,-1);
        idx=0;
        for(int e:nums1){
            int i=hs.get(e);
            for(int j=i+1;j<nums2.length;j++){
                if(nums2[j]>e) {
                    ans[idx]=nums2[j];
                    break;
                }
            }
            idx++;
        }
        return ans;
    }
    public int[] optimal(int[] nums1, int[] nums2) {//O(N)
        HashMap<Integer,Integer>hs=new HashMap<>();
        int idx=nums2.length-1;
        Stack<Integer>st=new Stack<>();
        while(idx>=0){
            while(!st.isEmpty() && st.peek()<nums2[idx]) st.pop();
            if(st.isEmpty()) hs.put(nums2[idx],-1);
            else hs.put(nums2[idx],st.peek());
            st.push(nums2[idx]);
            idx--;
        }
        System.out.println(hs);
        int ans[]=new int[nums1.length];
        idx=0;
        for(int e:nums1){
            ans[idx++]=hs.get(e);
        }
        return ans;
    }
}
