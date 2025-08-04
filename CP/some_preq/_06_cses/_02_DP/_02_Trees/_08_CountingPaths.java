package some_preq._06_cses._02_DP._02_Trees;
//بِسْمِ ٱللَّهِ ٱلرَّحْمَـٰنِ ٱلرَّحِيمِ
/*You are given a tree consisting of n nodes, and m paths in the tree.
Your task is to calculate for each node the number of paths containing that node.
Input
The first input line contains integers n and m: the number of nodes and paths. The nodes are numbered 1,2,\ldots,n.
Then there are n-1 lines describing the edges. Each line contains two integers a and b: there is an edge between nodes a and b.
Finally, there are m lines describing the paths. Each line contains two integers a and b: there is a path between nodes a and b.
Output
Print n integers: for each node 1,2,\ldots,n, the number of paths containing that node.
Constraints

1 \le n, m \le 2 \cdot 10^5
1 \le a,b \le n

Example
Input:
5 3
1 2
1 3
3 4
3 5
1 3
2 5
1 4

Output:
3 1 3 1 1 */

//Teaches how to apply sum query concept on trees

/*
 * 
Solution: just like that of like that query probwlem where for each query u had to compute the sum in the array from [l,r], here also we treat tree like that,

https://www.youtube.com/watch?v=8Nq3THy2Kw0

for each query [l,r], dp(prefix sum), dp[u]++,dp[v++],dp[parent(lca)]--,since lca was includeed teice, dp[lca]--

 */
import java.io.*;
import java.util.*;
public class _08_CountingPaths{
    public static void main(String[] args) throws IOException {
        int t=scanInt();
        while(t-->0)
        solve();
        reader.close();
        writer.flush();
        writer.close();
    }

    static List<Integer>adj[];
    static int up[][];
    static int level[];
    static int dp[];

    @SuppressWarnings("unchecked")
    public static void solve() throws IOException {
        int nv=scanInt();
        int np=scanInt();
        adj=new ArrayList[nv];
        for(int i=0;i<nv;i++)adj[i]=new ArrayList<>();
        for(int i=1;i<nv;i++){
            int u=scanInt()-1,v=scanInt()-1;
            adj[u].add(v);
            adj[v].add(u);
        }
        up=new int[nv][31];
        for(int row[]:up) Arrays.fill(row, -1);
        level=new int[nv];
        dp=new int[nv];
        build_parents(0,-1,0);
        for(int i=1;i<=30;i++) for(int u=0;u<nv;u++) if(up[u][i-1]!=-1) up[u][i]=up[up[u][i-1]][i-1];


        while (np-->0) {
            int u=scanInt()-1;
            int v=scanInt()-1;
            int lca=gte_lca(u,v);
            dp[u]++;
            dp[v]++;
            int parOfLca=up[lca][0];
            if(parOfLca!=-1) dp[parOfLca]--;
            dp[lca]--;
            // printArray(dp);
        }
        compute_rooted_sum(0,-1);
        println(Arrays.toString(dp));
    }

    static int compute_rooted_sum(int u,int par){
        int val=0;
        for(int v:adj[u]){
            if(v!=par){
                val+=compute_rooted_sum(v, u);//go deep
            }
        }
        return dp[u]+=val;
    }

    static  int  gte_lca(int u,int v) throws IOException{
        if(level[u]<level[v]){int temp=u;u=v;v=temp;}
        int k=level[u]-level[v];
        for(int i=30;i>=0;i--){
            if((k&(1<<i))!=0){
                if(u!=-1){
                    u=up[u][i];
                }
            }
        }
        if(u==v) return u;
        for(int i=30;i>=0;i--){
            if(up[u][i]!=-1){
                if(up[u][i]!=up[v][i]){
                    u=up[u][i];
                    v=up[v][i];
                }
            }
        }
        return up[u][0];
    }

    static void build_parents(int u,int par,int cl){
        level[u]=cl;
        for(int v:adj[u]){
            if(v!=par){
                up[v][0]=u;
                build_parents(v, u,cl+1);
            }
        }
    }


    static int MOD=1_000_000_007;
    static int INF=Integer.MAX_VALUE;
    static StringTokenizer tokenizer = new StringTokenizer("");
    static BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(System.out));
    static String next() throws IOException{while(!tokenizer.hasMoreTokens())tokenizer= new StringTokenizer(reader.readLine());return tokenizer.nextToken();}
    static int scanInt() throws IOException{return Integer.parseInt(next());}
    static long scanLong() throws IOException{return Long.parseLong(next());}
    static char scanChar() throws IOException{return next().charAt(0);}
    static String scanString() throws IOException{return reader.readLine();}
    static int[] scanIntArray(int len) throws IOException{int arr[]=new int[len];for(int i=0;i<len;i++)arr[i]=scanInt();return arr;}
    static long[] scanLongArray(int len) throws IOException{long arr[]=new long[len];for(int i=0;i<len;i++)arr[i]=scanLong();return arr;}
    static void print(Object o) throws IOException{writer.write(o.toString()+" ");}
    static void println(Object o) throws IOException{writer.write(o.toString()+"\n");}
    static int min(int...x){return Arrays.stream(x).min().getAsInt();}
    static int max(int...x){return Arrays.stream(x).max().getAsInt();}
    static int gcd(int a,int b){return (a==0)?b:gcd(b, a%b);}
    static int lcm(int a,int b){return a/(gcd(a, b)*b);}
}
