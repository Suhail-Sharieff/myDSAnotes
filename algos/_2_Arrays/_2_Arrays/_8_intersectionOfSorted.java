package _2_Arrays;

public class _8_intersectionOfSorted {
    public static int[] intersection(int x[],int y[]){
        int ans[]=new int[x.length+y.length];
        int i =0,j=0,k=0;
        while (i<x.length&&j<y.length) {
            if (x[i]==y[j]) {
                ans[k++]=x[i];
                i++;j++;
            }else{
                if (x[i]<y[j]) {
                    i++;
                }else{
                    j++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int x[]={1,2,2,3,3,4,5,6},y[]={2,3,3,4,5,5,7};
        int ans[]=intersection(x, y);

        for (int i : ans) {
            if (i!=0) {
                System.out.print(i+" ");
            }
        }
    }
}
