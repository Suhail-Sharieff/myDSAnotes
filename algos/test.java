import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * test
 */
public class test {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int  t=sc.nextInt();
        while (t--!=0) {
            int arr[]=new int[16];
            int greater[]=new int[16];
            int ans[]=new int[16];
            List<Integer>li=new ArrayList<>();
            for (int i = 0; i < 16; i++) {
                arr[i]=sc.nextInt();
                li.add(arr[i]);
            }

            Arrays.sort(arr);
            int an2s[]=new int[16];
            for (int i = 0; i < 16; i++) {
                if (i==0) {
                    an2s[li.indexOf(arr[i])]=0;
                }
                else if(i>=1&&i<=2){
                    an2s[li.indexOf(arr[i])]=1;
                }
                else if(i>2&&i<=6){
                    an2s[li.indexOf(arr[i])]=2;
                }
                else if(i>6&&i<=14){
                    an2s[li.indexOf(arr[i])]=3;
                }
                else if(i>14&&i<=15){
                    an2s[li.indexOf(arr[i])]=4;
                }
            }




            for (int i : an2s) {
                System.out.print(i+" ");
            }
            System.out.println();
            // for (int i : ans) {
            //     System.out.print(i+" ");
            // }
            // System.out.println();

        }

    }
}