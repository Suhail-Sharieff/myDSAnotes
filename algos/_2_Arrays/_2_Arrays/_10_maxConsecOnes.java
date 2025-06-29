package _2_Arrays;

public class _10_maxConsecOnes {
    public static void main(String[] args) {
        int arr[]=new int[]{1,1,0,1,1,1,0,1,1};
        System.out.println(optimal(arr));

    }
    public static int optimal(int arr[]){
        int c=0,max=0;
        for (int i = 0; i < arr.length; i++) {
           if (arr[i]==1) {
            c++;
            max=Math.max(max, c);
           }else{
            c=0;
           }
        }
        return max;
    }

}
