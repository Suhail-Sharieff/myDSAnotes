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
    
}
