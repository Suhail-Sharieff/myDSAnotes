package _6_DynamicProgramming._04_Strings._01_comparision.Subsequence;

public class _02_longest_common_palindromic_sub {

    public static void main(String[] args) {
        
    }
    public int rec(String s,int i,int j){
        if(i>j) return 0;
        if(i==j) return 1;

        if(s.charAt(i)==s.charAt(j)) return rec(s,i+1,j-1)+2;//+2 coz 1 first and other last caracter
        return Math.max(rec(s,i+1,j),rec(s,i,j-1));
    }

    public int mem(String s,int i, int j,int dp[][]){
        if(i>j) return 0;
        if(i==j) return 1;
        if(dp[i][j]!=-1) return dp[i][j];
        if(s.charAt(i)==s.charAt(j)){
            dp[i][j]=2+mem(s,i+1,j-1,dp);
        }
        else{
            dp[i][j]= Math.max(mem(s,i+1,j,dp),mem(s,i,j-1,dp));
        }
        return dp[i][j];
    }

    public int tab1(String s){
         int[][] dp = new int[s.length()][s.length()];
        
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;//IMP
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;//+2 IMP
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }



    //smart tabulation: apply lcs algo on s and reverse of ss
    //here pass lke: tab2(s,reverse(s))----IMP
    public int tab2(String s1, String s2) {

        int dp[][]=new int[s1.length()+1][s2.length()+1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j]= 1+ dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}