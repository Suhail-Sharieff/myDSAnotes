package _3_binarySearch;


public class _7_noOfRot {
    //given a rotated sorted array,find number of rotations it had undergone

    public static void main(String[] args) {
        //ANS: If we take a closer look at examples, we can notice that the number of rotations is equal to the index of the minimum element. A simple linear solution is to find the minimum element and returns its index. Using binary search algo of find min elemnt,we just modify it
        int arr[] = {15, 18, 2, 3, 6, 12};
        System.out.println(noOfrotations(arr));
    }
    
    //this is just modification of previous file algo
    public static int noOfrotations(int nums[]){
        int low=0,high=nums.length-1;
        int min=Integer.MAX_VALUE;
        int idxOfmin_ie_noOfRotations=-1;
        while (low<=high) {
            int mid=(low+high)/2;
            if (nums[mid]<=nums[high]) {//ie arr is correclty sorted withouth any rotation
                //so our min elemnt may be present bfr mid
                // min=Math.min(min, nums[mid]); instead of this we write
                if (min>nums[mid]) {
                    min=nums[mid];
                    idxOfmin_ie_noOfRotations=mid;
                }
                high=mid-1;
            }else if(nums[mid]>nums[high]){//ir its rotated by some k, and we would get min elemnt on right of mid
                low=mid+1;
                // min=Math.min(min, nums[high]); instead of this we write
                if (min>nums[high]) {
                    min=nums[high];
                    idxOfmin_ie_noOfRotations=high;
                }
            }
        }
        return idxOfmin_ie_noOfRotations;
    }
}
