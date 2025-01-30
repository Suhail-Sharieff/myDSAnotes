package _6_DynamicProgramming._04_Strings._02_replacement;

public class _01_edit_distance {

    public static void main(String[] args) {
        String x = "horse";
        String y = "ros";
        System.out.println(rec(x, y, x.length() - 1, y.length() - 1));
        /*
        //---------------output of recursion: read from bottom to top(coz this is DFS call)
        Using delete: comparing: [,]={1} ->Using replace: comparing: [rorse,r]={0} ->Using insert: comparing: [horser,]={1} ->
Using delete: comparing: [,]={1} ->Using replace: comparing: [roose,r]={0} ->Using insert: comparing: [hooser,]={1} ->
Using delete: comparing: [h,]={1} ->Using replace: comparing: [hrose,r]={1} ->Using insert: comparing: [hooser,]={2} ->
Using delete: comparing: [ho,r]={1} ->Using replace: comparing: [hoose,ro]={2} ->Using insert: comparing: [horseo,r]={2} ->
Using delete: comparing: [,]={1} ->Using replace: comparing: [rorss,r]={0} ->Using insert: comparing: [horssr,]={1} ->
Using delete: comparing: [,]={1} ->Using replace: comparing: [rooss,r]={0} ->Using insert: comparing: [hoossr,]={1} ->
Using delete: comparing: [h,]={1} ->Using replace: comparing: [hross,r]={1} ->Using insert: comparing: [hoossr,]={2} ->
Using delete: comparing: [ho,r]={1} ->Using replace: comparing: [hooss,ro]={2} ->Using insert: comparing: [horsso,r]={2} ->
Using delete: comparing: [hor,]={2} ->Using replace: comparing: [horrso,r]={3} ->Using insert: comparing: [horssor,]={4} ->
Using delete: comparing: [hor,r]={2} ->Using replace: comparing: [horos,ro]={2} ->Using insert: comparing: [horsso,r]={3} ->
Using delete: comparing: [,]={1} ->Using replace: comparing: [rorses,r]={0} ->Using insert: comparing: [horsesr,]={1} ->
Using delete: comparing: [,]={1} ->Using replace: comparing: [rooses,r]={0} ->Using insert: comparing: [hoosesr,]={1} ->
Using delete: comparing: [h,]={1} ->Using replace: comparing: [hroses,r]={1} ->Using insert: comparing: [hoosesr,]={2} ->
Using delete: comparing: [ho,r]={1} ->Using replace: comparing: [hooses,ro]={2} ->Using insert: comparing: [horseso,r]={2} ->
Using delete: comparing: [hor,]={2} ->Using replace: comparing: [horreso,r]={3} ->Using insert: comparing: [horsesor,]={4} ->
Using delete: comparing: [hor,r]={2} ->Using replace: comparing: [horoes,ro]={2} ->Using insert: comparing: [horseso,r]={3} ->
Using delete: comparing: [hor,]={2} ->Using replace: comparing: [horros,r]={3} ->Using insert: comparing: [horsosr,]={4} ->
Using delete: comparing: [hor,]={2} ->Using replace: comparing: [horreso,r]={3} ->Using insert: comparing: [horsesor,]={4} ->
Using delete: comparing: [hors,]={3} ->Using replace: comparing: [horsrso,r]={4} ->Using insert: comparing: [horsesor,]={5} ->
Using delete: comparing: [hors,r]={3} ->Using replace: comparing: [horsos,ro]={3} ->Using insert: comparing: [horseso,r]={4} ->
Using delete: comparing: [hors,ro]={2} ->Using replace: comparing: [horss,ros]={3} ->Using insert: comparing: [horses,ro]={4} ->
         */
    }
    //--------------------------------recursion---use this to understand steps, rec2 will work as same as rec1
    static int rec(String x, String y, int i, int j) {//base cases r imp thats all
        if (j < 0)
            return i + 1;//we have exhausted cheking y,ie y is now empty, means we need (i+1) operations(deletions) ie remaining chars of x ie x[0..i] must be deleted to get currently empty string y , +1 coz its 0 based indexing 
        if (i < 0)
            return j + 1;////we have exhausted cheking x,ie x is now empty, means we need (j+1) operations(deletions) ie remaining chars of y ie y[0..j] must be deleted to get currently empty string x , +1 coz its 0 based indexing 

        if (x.charAt(i) == y.charAt(j)) {
            return rec(x, y, i - 1, j - 1);

        } else {// ie ith and jth chars dont match

            // option1: dont consider ith char,move left of x
            int del = rec(x, y, i - 1, j);

            // option2: replace ith char with jth char
            char[] curr = x.toCharArray();
            curr[i] = y.charAt(j);
            int replace = rec(new String(curr), y, i - 1, j - 1);

            // option3: insert jth char to right of x and move left in both for further  check
            
            int insert = rec(new String(x + y.charAt(j)), y, i, j - 1);
            System.out.println(
                "Using delete: comparing: ["+x.substring(0, i)+","+y.substring(0, j)+"]={"+del+"} ->"+
                "Using replace: comparing: ["+new String(curr)+","+y.substring(0, j+1)+"]={"+replace+"} ->"+
                "Using insert: comparing: ["+new String(x+""+y.charAt(j))+","+y.substring(0, j)+"]={"+insert+"} ->"
            );
            return 1 + Math.min(del, Math.min(replace, insert));
        }
    }
    //----------------------------------rec2: observe that i have removed that creatin of string and all, works exactly similar
    static int rec2(String x, String y, int i, int j) {//base cases r imp thats all
        if (j < 0)
            return i + 1;
        if (i < 0)
            return j + 1;

        if (x.charAt(i) == y.charAt(j)) {
            return rec2(x, y, i - 1, j - 1);

        } else {

            int del = rec2(x, y, i - 1, j);
            int replace = rec2(x, y, i - 1, j - 1);
            int insert = rec2(x, y, i, j - 1);
            
            return 1 + Math.min(del, Math.min(replace, insert));
        }
    }
    //---------------------------memoization
    //i have used 1 based indexing for below memoization so that writing tabuation solution is easy, so call mem(x,y,x.length(),y.length()) and dp array must be dp[x.length+1][y.length+1] not just x.length and y.length
    static int mem(String x,String y,int i,int j,int dp[][]){
        if(j==0) return i;
        if(i==0) return j;
        int ans=0;
        if(dp[i][j]!=-1) return dp[i][j];
        if(x.charAt(i-1)==y.charAt(j-1)){
            ans= mem(x,y,i-1,j-1,dp);
        }else{
            int del=mem(x,y,i-1,j,dp);
            char[] curr=x.toCharArray();
            curr[i-1]=y.charAt(j-1);
            int replace=mem(new String(curr),y,i-1,j-1,dp);
            int insert=mem(new String(x+y.charAt(j-1)),y,i,j-1,dp);
            ans= 1+Math.min(del,Math.min(replace,insert));
        }
        dp[i][j]=ans;
        return ans;
    }

    //--------------------------------------tabulation
    static int tab(String  x,String y){
        int len1=x.length(),len2=y.length();
        if(x.isEmpty()) return len2;
        if(y.isEmpty()) return len1;
        int dp[][]=new int[len1+1][len2+1];
        //base case
        for(int i=0;i<=len1;i++){
            for(int j=0;j<=len2;j++){
                if(i==0) dp[i][j]=j;
                if(j==0) dp[i][j]=i;
            }
        }


        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(x.charAt(i-1)==y.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    int del=dp[i-1][j];
                    int replace=dp[i-1][j-1];
                    int insert=dp[i][j-1];
                    dp[i][j]=1+Math.min(del,Math.min(replace,insert));
                }
            }
        }
        return dp[len1][len2];
    }
    /*
    dp Array looks like this:
  _   r  o  s
_:[0, 1, 2, 3]
h:[1, 1, 2, 3]
o:[2, 2, 1, 2]
r:[3, 2, 2, 2]
s:[4, 3, 3, 2]
e:[5, 4, 4, 3]
     */

     public static int space_optimized(String x,String y){//observe the obove dp array and see 
        int len1=x.length(),len2=y.length();
        int prevRow[]=new int[len2+1];
        for(int i=0;i<=len2;i++) prevRow[i]=i;
        for(int i=1;i<=len1;i++){
            int currRow[]=new int[len2+1];
            currRow[0]=i;//_----IMP, dont forget this
            for(int j=1;j<=len2;j++){
                if(x.charAt(i-1)==y.charAt(j-1)){
                    currRow[j]=prevRow[j-1];
                }else{
                    int delete=prevRow[j];
                    int replace=prevRow[j-1];
                    int insert=currRow[j-1];
                    currRow[j]=1+Math.min(delete, Math.min(replace, insert));
                }
            }
            prevRow=currRow.clone();
        }
        return prevRow[len2];
     }
}