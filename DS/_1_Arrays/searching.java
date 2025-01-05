import java.util.*;

public class searching {
    public static boolean isSorted(int arr[]){
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i]>arr[i+1]){
                return false;
            }
        }
        return true;
    }
    public static void binarySearch(int arr[],int e){
        int low=0,high=arr.length-1,mid=(low+high)/2,pos=0;
        boolean found =false;
        while (low<=high) {
            mid=(low+high)/2;
            if(arr[mid]==e){
                found=true;
                pos=mid+1;
                break;
            }
            else if(arr[mid]<e){
                low=mid+1;
            }
            else if(arr[mid]>e){
                high=mid-1;
            }
        }
        if (found) {
            System.out.println(e+" is found at index "+pos);
        }else{
            System.out.println("not found");
        }
    }
    public static void linearSearch(int arr[],int e){
        int pos=0;
        boolean found =false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==e) {
                found=true;
                pos=i+1;
                break;
            }
        }
        if (found) {
            System.out.println(e+" is found at index "+pos);
        }else{
            System.out.println("not found");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter max size of Array your computer can hold: ");
        int maxSize = sc.nextInt();
       
        System.out.println("Enter no of elemnts u wish to insert:");
        int size=sc.nextInt();
        if (size>maxSize) {
            System.out.println("No of elemnts u r tryin to insert is exceeding your computer's max capacity !");sc.close();return ;
        }
        int arr[]=new int[size];
        System.out.println("Enter "+size+" elements:");
        for (int i = 0; i < size; i++) {
            arr[i]=sc.nextInt();
        } 
        System.out.println("Your array is: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i]+" ");
        } 
        System.out.println();
        if (isSorted(arr)) {
            System.out.println("Binary Search for sorted array:");
            System.out.println("Enter elemnt to be searched:");
            int e=sc.nextInt();
            binarySearch(arr, e);
        }else{
            System.out.println("Search using linear search");
            System.out.println("Enter elemnt to be searched:");
            int e=sc.nextInt();
            linearSearch(arr, e);
        }





        sc.close();
    }
    }
