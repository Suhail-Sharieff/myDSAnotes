package _2_Arrays;

import java.util.Arrays;

public class _7_unionOfSortedArr {

    //brute-->use Set
    
    public static int [] merge(int x[],int y[]){
        int i=0,j=0,k=0;
        int ans[]=new int[x.length+y.length];
        while (i<x.length&&j<y.length) {
            if (x[i]<y[j]) {
                ans[k++]=x[i++];
            }else if (x[i]>y[j]) {
                ans[k++]=y[j++];
            }
            else if(x[i]==y[j]){
                ans[k++]=x[i++];
                ans[k++]=y[j++];
            }
            
        }
        // Add remaining elements from x
        while (i < x.length) {
            ans[k++] = x[i++];
        }
        
        // Add remaining elements from y
        while (j < y.length) {
            ans[k++] = y[j++];
        }
        _3_removeDuplicates.removeDuplicatesFromAnything(ans);
        return ans;
    }

    //optimal:
   public static int[] unionWithoutDupli(int x[], int y[]) {
    int i = 0, j = 0, k = 0;
    int[] ans = new int[x.length + y.length];
    
    while (i < x.length && j < y.length) {
        if (x[i] < y[j]) {
            if (k == 0 || x[i] != ans[k - 1]) { // Check if current x[i] is not a duplicate
                ans[k++] = x[i];
            }
            i++;
        } else if (x[i] > y[j]) {
            if (k == 0 || y[j] != ans[k - 1]) { // Check if current y[j] is not a duplicate
                ans[k++] = y[j];
            }
            j++;
        } else { // x[i] == y[j]
            if (k == 0 || x[i] != ans[k - 1]) { // Check if current x[i] is not a duplicate
                ans[k++] = x[i];
            }
            i++;
            j++;
        }
    }
    
    // Add remaining elements from x
    while (i < x.length) {
        if (k == 0 || x[i] != ans[k - 1]) { // Check if current x[i] is not a duplicate
            ans[k++] = x[i];
        }
        i++;
    }
    
    // Add remaining elements from y
    while (j < y.length) {
        if (k == 0 || y[j] != ans[k - 1]) { // Check if current y[j] is not a duplicate
            ans[k++] = y[j];
        }
        j++;
    }
    
    // Resize ans array to remove unused space
    return Arrays.copyOf(ans, k);
}

    public static void main(String[] args) {
        int x[]={1,1,2,3,4,5},y[]={2,3,4,4,5};
        int ans[]=unionWithoutDupli(x, y);

        for (int i : ans) {
            System.out.print(i+" ");
        }
        
    }
    

    //o(n)--O(1)
    public static ArrayList<Integer> findUnion(int a[], int b[]) {
        int n = a.length;
        int m = b.length;
        int i = 0, j = 0;
        ArrayList<Integer> ans = new ArrayList<Integer>();

        // Using two pointers i and j over the two arrays which helps
        // in storing the smaller element.
        while (i < n && j < m) {
            // Updating the pointer i until we have identical
            // elements at consecutive position in a.
            while (i + 1 < n && a[i] == a[i + 1]) i++;

            // Updating the pointer j until we have identical
            // elements at consecutive position in b.
            while (j + 1 < m && b[j] == b[j + 1]) j++;

            // Comparing element of the arrays a and b at pointers
            // i and j and accordingly storing the smaller element
            // and updating the pointers.
            if (a[i] < b[j])
                ans.add(a[i++]);
            else if (b[j] < a[i])
                ans.add(b[j++]);
            else {
                // If a[i] is the same as b[j], we update both pointers.
                ans.add(b[j++]);
                i++;
            }
        }

        // Storing the remaining elements of first array (if there are any).
        while (i < n) {
            // Updating the pointer i until we have identical
            // elements at consecutive position in a.
            while (i + 1 < n && a[i] == a[i + 1]) i++;

            // Storing the elements.
            ans.add(a[i++]);
        }

        // Storing the remaining elements of second array (if there are any).
        while (j < m) {
            // Updating the pointer j until we have identical
            // elements at consecutive position in b.
            while (j + 1 < m && b[j] == b[j + 1]) j++;

            // Storing the elements.
            ans.add(b[j++]);
        }

        // Returning the list containing the union of the two arrays.
        return ans;
    }
}
