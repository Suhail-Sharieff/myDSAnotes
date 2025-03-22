

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

 

Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9
 

Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105

 */


public class _05_trapping_rain_water {
    //brute force:just sum up the amount of water that u can store on terace of each buiding
    public int brute(int[] nums) {
        int[] pref=get_pref_max(nums);
        int[] suff=get_suff_max(nums);
        int ans=0;

        for(int i=0;i<nums.length;i++){
            int water_can_be_stored_only_above_that_building=Math.min(pref[i],suff[i])-nums[i];
            ans+=water_can_be_stored_only_above_that_building;
        }

        return ans;
    }

    public int[] get_pref_max(int nums[]){
        int pref[]=new int[nums.length];
        pref[0]=nums[0];
        for(int i=1;i<nums.length;i++) pref[i]=Math.max(pref[i-1],nums[i]);
        return pref;
    }
    public int[] get_suff_max(int nums[]){
        int suff[]=new int[nums.length];
        suff[nums.length-1]=nums[nums.length-1];
        for(int i=nums.length-2;i>=0;i--) suff[i]=Math.max(suff[i+1],nums[i]);
        return suff;
    }



    //-----------------optimal
    //https://www.youtube.com/watch?v=ZI2z5pq0TqA&ab_channel=NeetCode
    /*
A basic idea comes into peoples mind if two walls are there and we want want to fill water within that wall we just have to fill the water till min of two wall right if it exceeds the value ,it will overflow.

So, we can set 2 pointers left_max,right_max and check which side is greater,if one side is greater iterate on other side because at the min side the water will be filled at its limit. and iterate till pointer is lower than the other one.

ex.height = [0,1,0,2,1,0,1,3,2,1,2,1]

left_max=0,right_max=1,count=0
amount of water is calculate using subtractring wall length with min(right_max,left_max);

iterate left_side.
count=0,left_max=0,right_max=1

iterate left_side.
count=0,left_max=1,right_max=1

iterate left_side.
count=1,left_max=1,right_max=1

iterate left_side.
count=2,left_max=2,right_max=1

iterate right_side.
count=2,left_side=2,right_side=2

iterate left_side.
count=3,left_side=2,right_side=2

iterate left_side.
count=5,left_side=2,right_side=2

iterate left_side.
count=5,left_side=3,right_side=2

iterate right_side.
count=6,left_side=3,right_side=2

iterate right_side.
count=6,left_side=3,right_side=2

so thats the amount of water is found.
     */
    public int trap(int[] nums) {
        int ans=0;
        int l_max=nums[0],r_max=nums[nums.length-1];
        int l_ptr=0,r_ptr=nums.length-1;

        while(l_ptr<r_ptr){
            if(l_max<=r_max){
                ans+=(l_max-nums[l_ptr]);
                l_ptr++;
                l_max=Math.max(l_max,nums[l_ptr]);
            }else{
                ans+=(r_max-nums[r_ptr]);
                r_ptr--;
                r_max=Math.max(r_max,nums[r_ptr]);
            }
        }
        return ans;
    }

}
