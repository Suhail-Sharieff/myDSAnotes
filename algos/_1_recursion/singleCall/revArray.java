package _1_recursion.singleCall;

/**
 * revArray
 */
public class revArray {
    public static void rev1(int arr[],int startIndex,int endIndex){
        if (startIndex>endIndex) {
            return ;
        }
        int temp=arr[startIndex];arr[startIndex]=arr[endIndex];arr[endIndex]=temp;
        rev1(arr, startIndex+1, endIndex-1);
    }
    public static void rev2(int nums[],int startIndex){
        if (startIndex>nums.length-startIndex-1) {
            return;
        }
        int temp=nums[startIndex];nums[startIndex]=nums[nums.length-startIndex-1];nums[nums.length-startIndex-1]=temp;
        rev2(nums, startIndex+1);
    }
    public static void main(String[] args) {
        int nums[]={1,2,3,4,-5,7};
        rev1(nums,0, nums.length-1);
        rev2(nums, 0);
        printArr(nums);

        
    }
    public static void printArr(int nums[]){
        for (int i : nums) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
}