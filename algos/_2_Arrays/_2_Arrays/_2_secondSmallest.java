package _2_Arrays;

/**
 * _2_secondSmallest
 */
public class _2_secondSmallest {
    //optimal:
    public static int secondSmallest(int arr[]){
        int smallest=arr[0];
        int secondSmallest=Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]<smallest) {
                secondSmallest=smallest;
                smallest=arr[i];
            }else if(arr[i]!=smallest&&arr[i]<secondSmallest){
                secondSmallest=arr[i];
            }
        }
        return secondSmallest;
    }
}