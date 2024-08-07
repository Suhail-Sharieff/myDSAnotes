/**
 * test
 */
public class test {

    public static int func(int arr[],int price,int fromIdx){
        int max=price,idxOfMax=fromIdx;
        for (int i = fromIdx; i < arr.length; i++) {
            if (arr[i]!=price&&arr[i]>max) {
                max=arr[i];
                idxOfMax=i;
            }
        }
        return idxOfMax;
    }
    public static void main(String[] args) {
        int prices[]={7,1,5,3,6,4};

        int maxProfit=0;

        for (int i = 0; i < prices.length; i++) {
            int idx=func(prices, prices[i],i);
            System.out.print(idx+" ");
        }


    }
}