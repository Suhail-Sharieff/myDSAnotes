package _8_Disjoint_Set;

import java.util.Arrays;
//https://www.youtube.com/watch?v=zEAmQqOpfzM&t=1275s&ab_channel=Luv
public class DSU {//O(4alpha)---revrese akkerman function

    private int nNodes;
    private int parent[];
    private int size[];

    public DSU(int nNodes) {
        this.nNodes = nNodes;
        this.parent = new int[this.nNodes];
        this.size = new int[this.nNodes];
        for (int i = 0; i < nNodes; i++) {
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }

    public int get_parent_of_with_path_compression(int nodeNumber) {
        if (parent[nodeNumber] == nodeNumber)
            return nodeNumber;
        parent[nodeNumber]=get_parent_of_with_path_compression(parent[nodeNumber]);//path compression
        return parent[nodeNumber];
    }

    @SuppressWarnings("unused")
    private int get_parent_of_without_path_compression(int nodeNumber) {
        if (parent[nodeNumber] == nodeNumber)
            return nodeNumber;
        return get_parent_of_without_path_compression(parent[nodeNumber]);
    }


    public void join(int u, int v) {
        if(u!=v){
            int root_of_u = get_parent_of_with_path_compression(u);
            int root_of_v = get_parent_of_with_path_compression(v);
            if(size[root_of_u]>=size[root_of_v]){
                parent[root_of_v]=root_of_u;
                size[root_of_u]+=size[root_of_v];
            }else{
                parent[root_of_u]=root_of_v;
                size[root_of_v]+=size[root_of_u];
            }
            
        }
    }

    public int get_number_of_connected_components() {
        int cnt = 0;
        for (int n = 0; n < nNodes; n++)
            if (parent[n] == n)
                cnt++;
        return cnt;
    }

    public void displayDSU() {
        for (int n = 0; n < nNodes; n++)
            System.out.println(parent[n] + "<-" + n);
    }

   public boolean belong_to_same_component(int u,int v){
    return get_parent_of_with_path_compression(u)==get_parent_of_with_path_compression(v);
   }

   public int[][] get_path_compressed_adj(){
    int adj[][]=new int[nNodes][nNodes];
    for (int node = 0; node < nNodes; node++) {
        int root = get_parent_of_with_path_compression(node);
        if (root != node) {  // Avoid self-loops
            adj[node][root] = 1;
            adj[root][node] = 1;  // Bidirectional connection---comment this if DG
        }
    }
    for(int row[]:adj)System.out.println(Arrays.toString(row));
    return adj;
   }



   public static void main(String[] args) {
    DSU graph = new DSU(5);
    // graph.displayDSU();
    graph.join(0, 3);
    // graph.displayDSU();
    graph.join(3, 4);
    graph.displayDSU();
    graph.get_path_compressed_adj();
    System.out.println("Are 0 and 4 connected? " + graph.belong_to_same_component(0, 4));
    System.out.println("Are 1 and 2 connected? " + graph.belong_to_same_component(1, 2));
    System.out.println("Number of connected components: " + graph.get_number_of_connected_components());
}


}

