package some_preq._01_Queries;

import java.util.Arrays;

public class _02_query_GCD {

    public static void main(String[] args) {
        //For each query [l,r] output GCD of all numbers except the numbers at indexes(1 based)[l,r](inclusive)
        int nums[]={1,2,3,4,5};
        int queries[][]={{1,2},{3,4}};//0 based indexing

        //solution:
        /*
        Maintain 2 prefixes, forwards(say f) and backward(say b), f[i] we will store GCD(0...i) and b[i] will store GCD(n...i)(ie equivalent to GCD(i...n)) 

        As per the question we want to calculte GCD of all numbers except in the range[l,r](inclusive), so the answer would be GCD(GCD(0..l-1),GCD(r+1...n)) ie GCD(f[L-1],b[R+1])
         */

         int forward[]=new int[nums.length];
         forward[0]=nums[0];
         for(int i=1;i<nums.length;i++){
            forward[i]=GCD(nums[i], forward[i-1]);
         }
         System.out.println("Forward GCD "+Arrays.toString(forward));

         int backward[]=new int[nums.length];
         backward[nums.length-1]=nums[nums.length-1];
         for(int i=nums.length-2;i>=0;i--){
            backward[i]=GCD(nums[i], backward[i+1]);
         }
         System.out.println("Backward GCD "+Arrays.toString(backward));

         for(int query[]:queries){
            int l=query[0];
            int r=query[1];
            if(l==0){
                System.out.println(backward[r+1]);
                continue;
            }
            if(r==nums.length-1){
                System.out.println(forward[l-1]);
                continue;
            }
            System.out.println("GCD of all nums except in range "+Arrays.toString(query)+" is "+GCD(forward[l-1], backward[r+1]));
         }

        


    }

    static int GCD(int a,int b){
        if(b==0) return a;
        return GCD(b, a%b);
    }
}