package _6_DynamicProgramming._04_Strings._01_comparision.Subsequence;




//prereq: learn how to print a single lcs:
//IMP watch:
//https://www.youtube.com/watch?v=xElxAuBcvsU&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=34&ab_channel=takeUforward
public class _03_shortest_common_superseq {
    public static String lcs(String x,String y){
        int dp[][]=new int[x.length()+1][y.length()+1];
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=x.length();i++){
            for(int j=1;j<=y.length();j++){
               if(x.charAt(i-1)==y.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        int i=x.length(),j=y.length();
        while(i>0 && j>0){
            if(x.charAt(i-1)==y.charAt(j-1)){
                sb.append(x.charAt(i-1));
                i--;j--;
            }else{
                if(dp[i-1][j]>dp[i][j-1]){
                    //then we need to move up
                    sb.append(x.charAt(i-1));
                    i--;
                }else{
                    //then we need to move left
                    sb.append(y.charAt(j-1));
                    j--;
                }
            }
        }
        // System.out.println(i+" "+j);
        //since we may not always reach 0,0, then some chars my remain in both, so append them
        while(i-->0) sb.append(x.charAt(i));
        while(j-->0) sb.append(y.charAt(j));
        return(sb.reverse().toString());
    }
}
