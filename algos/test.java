import java.util.*;

public class test {

    public static void main(String[] args) {
        String s = "abaaa", t = "baabaca";

        int dp[][] = new int[s.length() + 1][t.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        for (int[] e : dp) {
            System.out.println(Arrays.toString(e));
        }
        HashSet<String>hs=new HashSet<>();
        lcs_subseq(s, t, hs, s.length(), t.length(), dp, new StringBuilder());
        List<String>ans=new ArrayList<>(hs);
        ans.sort(null);
        System.out.println(ans);
        
    }

    public static void lcs_subseq(String s1,String s2,Set<String>ans,int i, int j,int dp[][],StringBuilder sb){
        if(i==0 || j==0){
            ans.add(new String(sb.reverse()));
            System.out.println(sb);
            sb.reverse();
            return;
        }
        if(s1.charAt(i-1)==s2.charAt(j-1)){
            lcs_subseq(s1, s2, ans, i-1, j-1, dp, new StringBuilder(sb).append(s1.charAt(i-1)));
        }else{
            if(i>=1 && dp[i-1][j]==dp[i][j]){
                lcs_subseq(s1, s2, ans, i-1, j, dp, sb);
            }
            if(j>=1 && dp[i][j-1]==dp[i][j]){
                lcs_subseq(s1, s2, ans, i, j-1, dp, sb);
            }
        }
    }
}
