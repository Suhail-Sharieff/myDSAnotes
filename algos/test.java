
public class test {

    public static void main(String[] args) {
        // HashSet<List<Integer>>hs=new HashSet<>();
        int mat[][]={
            {1,2,3},
            {4,5,6},
            // {7,8,9},
        };
       
        int m=mat.length,n=mat[0].length;
        int ans[]={Integer.MAX_VALUE};
        func(mat,m, n, 0, 0, new StringBuilder(),0,ans);
        System.out.println(ans[0]);
       
    }

  static void func(int mat[][],int m,int n,int i,int j,StringBuilder sb,int sum,int ans[]){
    if(i==m-1 && j==n-1){
        // System.out.println(new String(mat[0][0]+"->"+sb+" => "+(sum+mat[0][0])));
        ans[0]=Math.min(ans[0], sum+mat[0][0]);
        return;
    }
    if (j+1<n) {
        func(mat,m, n, i, j+1, new StringBuilder(sb).append(mat[i][j+1]+"->"),sum+mat[i][j+1],ans);
    };
    if (i+1<m) {
        func(mat,m, n, i+1, j, new StringBuilder(sb).append(mat[i+1][j]+"->"),sum+mat[i+1][j],ans);
    }
  }
}