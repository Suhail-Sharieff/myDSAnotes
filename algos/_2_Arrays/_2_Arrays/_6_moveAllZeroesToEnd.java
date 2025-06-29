package _2_Arrays;

public class _6_moveAllZeroesToEnd {

    public static void moveZeroesToEnd1(int arr[]) {
        int i = 0, j = 0;
        while (i < arr.length && j < arr.length) {
            // Move pointer j to find the next non-zero element
            while (j < arr.length && arr[j] == 0) {
                j++;
            }
            
            // If j exceeds array length, break
            if (j >= arr.length) {
                break;
            }
            
            // Swap arr[i] with arr[j]
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            
            // Move i to the next position
            i++;
            // Move j to find the next non-zero element
            j++;
        }
    }

    //optimal:O(n)
    public static void moveZeroesToEnd2(int arr[]){
        int j=-1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==0) {
                j=i;break;
            }
        }
        if (j==-1) {
            return;
        }
        for (int i = j+1; i < arr.length; i++) {
            if (arr[i]!=0) {
                int temp=arr[i];arr[i]=arr[j];arr[j]=temp;
                j++;
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = {11, 0, 4, 0, 1, 0, 3, 8};

        moveZeroesToEnd1(arr);
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
    
}
