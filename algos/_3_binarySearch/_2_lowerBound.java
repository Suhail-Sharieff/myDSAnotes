package _3_binarySearch;
//Lower Bound:
//its the SMALLEST index of the elemnt in the SORTED array which is greater than or equal to the given number "x"
//for ex: in sorted arr: 1 2 3 4 5 6 if x=3, the lower bound is 2 coz at index 2,3>=3
// now suppose that if x was given as 7, since there is no such elent in array which is >=7, we would return ans as 6,ie the last index+1 ie 5+1=6, so we basically instatntiate asn as nums.length ie lastIdx+1


//finding lower bound in an array takes O(n) time TC using linear search, but we can do it in TC logn using BS


//SUMMARY:
//lowerBound:smallestIdx where number > = x
//upperBound:smallestIdx where number > x ie strictly greater than x
public class _2_lowerBound {
    public static void main(String[] args) {
        int sortedNums[]={0,1,2,3,4,5,6,7,8,9};
        //smallest index where number is greater than or equal to x(say 3) is 3 ,proof:
        System.out.println(findLowerBoundUsingBS(sortedNums, 3));
        //similarly upper bound we need to get idx 4 since 4>3(assumed x)

    }
    public static int findLowerBoundUsingBS(int nums[],int x){
        int ans=nums.length;//instantiated with lastIdx+1,coz what if there's no such value in nums
        int lowIdx=0,highIdx=nums.length-1;
        while (lowIdx<=highIdx) {
            int midIdx=lowIdx+(highIdx-lowIdx)/2;//this is eq to (low+high)/2, but handles exception which may occur when arr size is very large
            if (nums[midIdx]>=x) {
                ans=x;//this will store x in ans
                //but since we need smallest value we still go beyond and check if any smaller idx value is possible,so:
                highIdx=midIdx-1;                
            }else{
                lowIdx=midIdx+1;
            }
        }
        return ans;
    }
    public static int findUpperBoundUsingBS(int nums[],int x){
        int ans=nums.length;//instantiated with lastIdx+1,coz what if there's no such value in nums
        int lowIdx=0,highIdx=nums.length-1;
        while (lowIdx<=highIdx) {
            int midIdx=lowIdx+(highIdx-lowIdx)/2;//this is eq to (low+high)/2, but handles exception which may occur when arr size is very large
            if (nums[midIdx]>x) {//no equalt osign unlike lowerBound
                ans=x;//this will store x in ans
                //but since we need smallest value we still go beyond and check if any smaller idx value is possible,so:
                highIdx=midIdx-1;                
            }
            else if(nums[midIdx]==x){//then u need a greater elemnt,so search further ie greter idxes
                lowIdx=midIdx+1;//ie same as the nextcsase
            }
            else{
                lowIdx=midIdx+1;
            }
        }
        return ans;
    }

    
}
