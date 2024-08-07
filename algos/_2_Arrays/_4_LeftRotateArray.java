package _2_Arrays;

/**
 * _4_rotateArray
 */
public class _4_LeftRotateArray {

    public static void leftRotatateByOnePlace(int arr[]){//O(n)--O(1)
        int first=arr[0];
        for (int i = 1; i < arr.length; i++) {
            int temp=arr[i];
            arr[i]=arr[i-1];
            arr[i-1]=temp;
        }
        arr[arr.length-1]=first;
    }

    //brute--repeat to rotate by 1 place k times
    public static void leftRotatateByKPlaces1(int arr[],int k){
        while (k--!=0) {
            leftRotatateByOnePlace(arr);
        }
    }
    //better--instaed o frepeating k times,do it only for k%len times co for every len times it becomes as same as otiginal array
    public static void leftRotatateByKPlaces2(int arr[],int k){
        k=k%arr.length;
        while (k--!=0) {
            leftRotatateByOnePlace(arr);
        }
    }

    //normal optimal way:
    //first put first k elemnts into some temp list,then from idx k start putting elemnts to first,then again put temp elemnts at last
    public static void leftRotateByKPlaces3(int arr[], int k) {//O((k)+(len-k)+(k))==O(len+k)
        k = k % arr.length; // Normalize k to handle cases where k >= arr.length
        
        int temp[] = new int[k]; // Create a temporary array to store elements to be shifted
        
        // Store the first `k` elements in the temporary array `temp`
        for (int i = 0; i < k; i++) {
            temp[i] = arr[i];
        }
        
        // Shift elements in `arr` to the left by `k` positions
        for (int i = k; i < arr.length; i++) {
            arr[i - k] = arr[i];
        }
        
        // Copy elements from `temp` back to the end of `arr`
        for (int i = arr.length - k, j = 0; i < arr.length; i++, j++) {
            arr[i] = temp[j];
        }
    }

    //most optimal soln:O(2n)---O(1)
    /*suppoe int arr[]={11,3,4,2,1,2,3,8}; and k=3
      take first 3 elemnts ie{11,3,4}-->rev it ==>{4,3,11}
      then take rest ie {2,1,2,3,8}--->rev it {8,3,2,1,2}

      join both rev arrays (join second one at first ie {8,3,2,1,2,4,3,11}
      ow again rev this array to get ans

      psudocode:
      rev(from i to i+k)-->rev(i+k,i+len)-->rev(i,i+len)
     
     */
    public static void leftRotateByKPlaces4(int[] arr, int k) {
        int n = arr.length;
        // Handle case where k is larger than array length
        k = k % n;
        // Reverse the first k elements
        reverse(arr, 0, k - 1);
        // Reverse the remaining elements
        reverse(arr, k, n - 1);
        // Reverse the entire array
        reverse(arr, 0, n - 1);
        
    }
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    
    public static void main(String[] args) {
        int arr[]={11,3,4,2,1,2,3,8};
        // leftRotatateByOnePlace(arr);
        // leftRotatateByKPlaces1(arr, 2);
        // leftRotatateByKPlaces2(arr, 2);
        // leftRotateByKPlaces3(arr, 2);
        leftRotateByKPlaces4(arr, 2);

      
        

        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
}