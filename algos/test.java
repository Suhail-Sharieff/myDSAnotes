/**
 * test
 */
public class test {

   public static void main(String[] args) {
      int bloomday[]={71,10,3,10,2};
      int m=3,k=2;
      System.out.println(brute(bloomday, m, k));
   }
   public static int brute(int nums[],int m,int k){//O(n^2)---passes 84/93 Tc with TLE
      int len=nums.length;

      int ans=Integer.MAX_VALUE;


      for (int i : nums) {
         int nOfPairs=0;
         boolean bloomed[]=new boolean[len];
         for (int j =0;j<len;j++) {
            if (i>=nums[j]) {
               bloomed[j]=true;
            }
         }
         for (boolean j : bloomed) {
            System.out.print(j+" ");
         }
         System.out.println();
         int nOfBloomed=0;
         for (boolean b : bloomed) {
            if (b) {
              nOfBloomed++;
              if (nOfBloomed==k) {
               nOfPairs++;
               nOfBloomed=0;
              }
            }else{
               nOfBloomed=0;
            }
         }
         if (nOfPairs==m) {
            ans=Math.min(ans, i);
         }
         
      }

      if (ans>=Integer.MAX_VALUE) {
         return(-1);
      }

      return ans;

   }
}