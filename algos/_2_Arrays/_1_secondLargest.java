package _2_Arrays;


public class _1_secondLargest {

    public static int findLargest(int arr[]){//o(N)
        int max=Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>max) {
                max=arr[i];
            }
        }
        return max;
    }

    public static int findSecondLargest1(int largest,int arr[]){//O(2N)
        int ans=Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>ans&&arr[i]!=largest) {
                ans=arr[i];
            }
        }
        return ans;

    }

    //optimal soln:
    public static int findSecondLargest2(int arr[]){
        int largest=arr[0];
        int sLargest=Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]>largest) {
                sLargest=largest;
                largest=arr[i];
            }else if(arr[i]<largest&&arr[i]>sLargest){
                sLargest=arr[i];
            }
        }
        return sLargest;
    }



    
}
