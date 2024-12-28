import java.util.Arrays;

public class test {

    public static void main(String[] args) {
        sum(new int[]{-5,-1});
        
    }
    static  int sum(int arr[]){
        if(arr.length==1) return arr[0];
        else if(arr.length==2) return Math.max(arr[0], arr[1]);
        int dp[]=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            int max=arr[i];
            for(int j=2;j<arr.length;j++){
                if(i-j>=0){
                    max=Math.max(max, dp[i-j]+arr[i]);
                }
                System.out.println(i+" "+j);
            }
            
            dp[i]=max;
        }
        System.out.println(Arrays.toString(dp));
        return dp[arr.length-1];
    }
}