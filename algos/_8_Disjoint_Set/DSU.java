package _8_Disjoint_Set;

import java.util.Arrays;

class Disjoint_Set {

    private int nNodes;
    private int parent[];
    private int size[];

    public Disjoint_Set(int nNodes) {
        this.nNodes = nNodes;
        this.parent = new int[this.nNodes];
        this.size = new int[this.nNodes];
        for (int i = 0; i < nNodes; i++) {
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }

    private int get_parent_of(int nodeNumber) {
        if (parent[nodeNumber] == nodeNumber)
            return nodeNumber;
        parent[nodeNumber]=get_parent_of(parent[nodeNumber]);
        return parent[nodeNumber];
    }

    public void join(int u, int v) {
        if(u!=v){
            int root_of_u = get_parent_of(u);
            int root_of_v = get_parent_of(v);
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
    return get_parent_of(u)==get_parent_of(v);
   }

   public int[][] get_adj(){
    int adj[][]=new int[nNodes][nNodes];
    for (int node = 0; node < nNodes; node++) {
        int root = get_parent_of(node);
        if (root != node) {  // Avoid self-loops
            adj[node][root] = 1;
            adj[root][node] = 1;  // Bidirectional connection---comment this if DG
        }
    }
    for(int row[]:adj)System.out.println(Arrays.toString(row));
    return adj;
   }

}

public class DSU {

    public static void main(String[] args) {
        Disjoint_Set graph = new Disjoint_Set(5);
        // graph.displayDSU();
        graph.join(0, 3);
        // graph.displayDSU();
        graph.join(3, 4);
        graph.displayDSU();
        graph.get_adj();
        System.out.println("Are 0 and 4 connected? " + graph.belong_to_same_component(0, 4));
        System.out.println("Are 1 and 2 connected? " + graph.belong_to_same_component(1, 2));
        System.out.println("Number of connected components: " + graph.get_number_of_connected_components());
    }
}