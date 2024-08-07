package _2_Arrays;

import java.util.Vector;

/**
 * _18_rearrangeBySign
 */

 //u r given an array Of size 2N having N +ve and N -ve elemnts, arrange the elements of array such that the +ves are in even index and -ves r in odd index BY MAINTAINING GIVEN ORDER
public class _18_rearrangeBySign {//O(n)---O(n)

    //if n+ves==n_ves
    public static int[] func(int [] arr){
        int pos=0,neg=1;
        int nums[]=new int[arr.length];
        for (int i : arr) {
           if (Math.max(pos, neg)<arr.length) {
             if (i*-1>0) {
                 //-ve num
                 nums[neg]=i;
                 neg+=2;
             }else if(i*-1<=0){
                 //pos num
                 nums[pos]=i;
                 pos+=2;
             }
           }else{
            int temp=Math.min(pos, neg);
            nums[temp++]=i;
           }
        }
        return nums;
    }


    //VIMP follow up Q
    //if nPos!=nNeg,then we push all remaining unpaired elmnts at last of array in order
    public static int[] func2(int arr[]){//O(2N)---O(N)
        Vector<Integer>pos=new Vector<>();
        Vector<Integer>neg=new Vector<>();
        for (int e : arr) {
            if (e<0) {
                neg.add(e);
            }else{
                pos.add(e);
            }
        }
        if (pos.size()>neg.size()) {
            //start pairing negatives sinece they r less in numberand then add all positives at last
            for (int i = 0; i < neg.size(); i++) {
                arr[2*i]=pos.get(i);//at even places
                arr[2*i+1]=neg.get(i);//add odd places
            }
            //start filling from idx neg.size()*2 since before elemnts r paired ,so *2
            int idx=neg.size()*2;
            for (int i = neg.size(); i <pos.size(); i++) {
                arr[idx++]=pos.get(i);
            }

        }else{
            for (int i = 0; i < pos.size(); i++) {
                arr[2*i]=pos.get(i);//at even places
                arr[2*i+1]=neg.get(i);//add odd places
            }
            int idx=pos.size()*2;
            for (int i = neg.size(); i <pos.size(); i++) {
                arr[idx++]=neg.get(i);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        // int nums[]={1, 2, -3, -1, -2, 3};
        int nums[]={-1,2,3,4,-3,1};

        func2(nums);
        
        for (int i : func(nums)) {
            System.out.print(i+" ");
        }
    }
}