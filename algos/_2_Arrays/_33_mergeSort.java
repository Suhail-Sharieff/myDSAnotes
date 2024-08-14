package _2_Arrays;

public class _33_mergeSort {
    public static void main(String[] args) {
        
        int nums[]={12,3,1,-1,8,3,2,4,1};
        sort(nums, 0, nums.length-1);

        for (int i : nums) {
            System.out.println(i);
        }

    }

    public static void sort(int nums[],int lowIdx,int highIdx){
        if (lowIdx>=highIdx) {//actually when lowIdx==highIdx==nums.length==1
            return;
        }
        //hypothetically (playing with index and not actuallly) divide tht array into 2,sort them and merge
        int midIdx=(lowIdx+highIdx)/2;
        sort(nums, lowIdx, midIdx);//sort first part from lowIdx to id
        sort(nums, midIdx+1, highIdx);//sort next part nowmerge both
        merge(nums, lowIdx, midIdx, highIdx);
    }
    public static void merge(int nums[],int lowIdx,int midIdx,int highIdx){
        int temp[]=new int[highIdx-lowIdx+1];
        int left=lowIdx,right=midIdx+1;
        int idx=0;
        while (left<=midIdx&&right<=highIdx) {
            if (nums[left]<=nums[right]) {
                temp[idx++]=nums[left++];
            }else if(nums[left]>nums[right]){
                temp[idx++]=nums[right++];
            }
        }
        while (left<=midIdx) {
            temp[idx++]=nums[left++];
        }
        while (right<=highIdx) {
            temp[idx++]=nums[right++];
        }
        for (int i = 0; i <temp.length; i++) {
            int k=temp[i];
            nums[lowIdx+i]=k;//MISTAKE: not just nums[i]=k ---X------
        }
    }
}
