import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {


 public static void main(String[] args) {
        int price[]={1, 10, 3, 1, 3, 1, 5, 9};//len1=>5,len2=>10
        int lenOfRod=price.length;

        // recursion(price, 0, 0, new ArrayList<>());
    }

    //--------------------------recursion, i have converted preices array into 1 based indexing for clarity to call like:
    /*
     int len=price.length;
        int v[]=new int [len+1];
        for(int i=1;i<=len;i++){
            v[i]=price[i-1];
        }
        return recurion(v,len,len);
     */

    public static int recursion_1(int priceOfLen[],int length_of_cut_part,int totalLength){
        if(totalLength==0) return 0;
        if(totalLength==1) return totalLength*priceOfLen[1];


        int cut=(totalLength>=length_of_cut_part)?(priceOfLen[length_of_cut_part]+recursion_1(priceOfLen, length_of_cut_part, totalLength-length_of_cut_part)):0;
        int dont_cut=recursion_1(priceOfLen, length_of_cut_part-1, totalLength);

        return Math.max(cut, dont_cut);
    }

    //---------------recursion2
    public static int recursion_2(int priceOfLen[],int totalLength){
        if(totalLength==0) return 0;
        if(totalLength==1) return totalLength*priceOfLen[1];

        int max=0;
        for(int chosenLen=1;chosenLen<=totalLength;chosenLen++){
            int subRes=priceOfLen[chosenLen]+recursion_2(priceOfLen, totalLength-chosenLen);
            max=Math.max(max, subRes);
        }

        return max;
    }


    //------------memoization
    public static int memoize(int priceOfLen[],int totalLength,int dp[]){
        if(totalLength==0) return 0;
        if(totalLength==1) return totalLength*priceOfLen[1];
        
        if(dp[totalLength]!=-1) return dp[totalLength];

        int max=0;
        for(int chosenLen=1;chosenLen<=totalLength;chosenLen++){
            int subRes=priceOfLen[chosenLen]+memoize(priceOfLen, totalLength-chosenLen,dp);
            max=Math.max(max, subRes);
        }
        dp[totalLength]=max;

        return max;
    }
   
}