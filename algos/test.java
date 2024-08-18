import java.util.Scanner;

/**
 * test
 */
public class test {

   public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int t=sc.nextInt();
      while (t--!=0) {
         int n=sc.nextInt();
         boolean isLit[]=new boolean[n];
         int k=0;
         while (k<=n) {
            for (int i =k; i<n; i+=k) {
               if (isLit[i]) {
                  isLit[i]=false;
               }else{
                  isLit[i]=true;
               }
            }
            k++;
         }

         for (boolean b : isLit) {
            System.out.println(b);
         }
      }
   }
}