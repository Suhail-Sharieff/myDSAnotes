package _6_Recursion;

public class binarySearch {
    public static void binarySearchUsingRecursion(int arr[],int target,int low,int high){
        int currIdx=(low+high)/2;
        if (arr[currIdx]==target) {
            System.out.println("Ement found at index "+currIdx);
            return;
        }
        else{
            if (currIdx<=0||currIdx>=arr.length-1) {
                System.out.println("Not found");
                return;
            }
            else if (arr[currIdx]<target) {
                binarySearchUsingRecursion(arr, target, currIdx+1, high);
            }
            else if (arr[currIdx]>target) {
                binarySearchUsingRecursion(arr, target, low, currIdx-1);
            }
        }

        
    }
    public static void main(String[] args) {
        int arr[]={1,2,3,4,5,6};
        binarySearchUsingRecursion(arr, 1, 0, arr.length);
    }
}
