package _6_DynamicProgramming._04_Strings._01_comparision.Substring;

public class _01_longest_common_substring {

    public static void main(String[] args) {
        String x="abcjklp",y="acjkp";

        System.out.println(lcs(x, y));
    }



    public int brute_force(String str1, String str2) {//go throgh all subtrings of shorter lenghted string among two string, for each check if it exists in longer strng, take max lenght among all such subtrings// TC: Min(len(str1,str2)^2)
        String longer_string=(str1.length()>=str2.length())?str1:str2;
        String shorter_string=(str1.length()<str2.length())?str1:str2;
        int max=0;
        for(int i=0;i<shorter_string.length();i++){
            for(int j=shorter_string.length();j>=i;j--){
                String currSubtring=shorter_string.substring(i,j);
                if(longer_string.contains(currSubtring)) max=Math.max(max, currSubtring.length());
            }
        }
        return max;
    }

    //dp[i][j] indicates number of characters common in strings x and y from x[0..i] abd y[0..j]. The idea is we do assame tabulation as that of subsequence, but with some modification: whenver a same char is found, then  at that pos we store 1+nOfConsequtiveCharsCommoninx[0,,i-1]andy[0..j-1], else it had broke consecutive property, so make it 0, take max value in grid
    public static int lcs(String str1, String str2){
        // Write your code here.
        //if the chars match 1+i-1,j-1
        int max=0;
        int dp[][]=new int[str1.length()+1][str2.length()+1];
        for(int i=1;i<=str1.length();i++){
            for(int j=1;j<=str2.length();j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j]=1+dp[i-1][j-1];
                }else{//see the change here
                    dp[i][j]=0;
                }
                max=Math.max(dp[i][j],max);
            }
        }
        // int max=0;
        // for(int r[]:dp) for(int e:r) max=Math.max(max,e);
        return max;
    }
}
