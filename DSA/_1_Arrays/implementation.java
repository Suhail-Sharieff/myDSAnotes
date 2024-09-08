import java.util.Scanner;
//implements insertion,deletion
class Array {

    private int arr[];
    private int noOfElements;
    private int maxSize;

    public Array(int maxSize) {
        arr = new int[maxSize];
        noOfElements = 0;
        this.maxSize=maxSize;
    }

    public void insert(int toInsert) {
        if (noOfElements==maxSize) {
            System.out.println("cant insert");
            return;
        }
        arr[noOfElements++] = toInsert;
    }

    public void displayArray() {
        System.out.println("Array Elements:");
        for (int i = 0; i < noOfElements; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    public void deleteElement(int elementToDelete) {
        int j;
        for (j = 0; j < noOfElements; j++) {
            if (elementToDelete == arr[j]) {
                break;
            }
        }
        if (j == noOfElements) { // If element not found
            System.out.println("Elemnt not found to delete");
        } else {
            for (int k = j; k < noOfElements - 1; k++) {
                arr[k] = arr[k + 1];
            }
            noOfElements--;
            System.out.println("Elemnt deleted successfully. Modified Array is:");
            displayArray();
        }
    }

}
public class implementation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter max size of Array your computer can hold: ");
        int maxSize = sc.nextInt();
        Array suhail = new Array(maxSize);
        System.out.println("Enter no of elemnts u wish to insert:");
        int size=sc.nextInt();
        if (size>maxSize) {
            System.out.println("No of elemnts u r tryin to insert is exceeding your computer's max capacity !");sc.close();return ;
        }
        System.out.println("Enter "+size+" elements: ");
        for (int i = 0; i < size; i++) {
            int scan=sc.nextInt();
            suhail.insert(scan);
        }
        suhail.displayArray();
        
        System.out.println("Enter element to delete: ");
        int e = sc.nextInt();
        suhail.deleteElement(e);

        sc.close();
    
    }
}
