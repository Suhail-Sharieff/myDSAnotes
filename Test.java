
// User functioa Template for Java
import java.util.*;


class Test {
    List<List<Integer>> adj;
    boolean isv[];

    public static void main(String[] args) {
        new Test().criticalConnections(6, List.of(
            List.of(1,2),
            List.of(0,2),
            List.of(0,1,3),
            List.of(2,4,5),
            List.of(3),
            List.of(3,4)
        ));
    }

    public List<List<Integer>> criticalConnections(
            int v, List<List<Integer>> x) {

        id = 0;
        adj = x;
        isv = new boolean[x.size()];
        at = new int[x.size()];
        oa = new int[x.size()];
        // System.out.println(adj);

        dfs(0,-1);

        System.out.println(Arrays.toString(at));
        System.out.println(Arrays.toString(oa));

        return new ArrayList<>();
    }

    int id;
    int at[];
    int oa[];

    void dfs(int u,int par) {
        isv[u] = true;
        at[u] = oa[u] = id++;
        for (int v : adj.get(u)) {
            if(v==par ){
                continue;
            }
            if (!isv[v]) {
                dfs(v,u);
                oa[u]=Math.min(oa[u],oa[v]);
                if(at[u]<oa[v]){
                    System.err.println(u+" "+v);
                }
                
            } else {
                oa[u] = Math.min(oa[u], at[v]);
            }
            
        }
        
    }
}