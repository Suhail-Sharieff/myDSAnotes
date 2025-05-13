
package _4_Trees;
/*You are given a tree with n nodes numbered from 0 to n - 1 in the form of a parent array parent where parent[i] is the parent of ith node. The root of the tree is node 0. Find the kth ancestor of a given node.

The kth ancestor of a tree node is the kth node in the path from that node to the root node.

Implement the TreeAncestor class:

TreeAncestor(int n, int[] parent) Initializes the object with the number of nodes in the tree and the parent array.
int getKthAncestor(int node, int k) return the kth ancestor of the given node node. If there is no such ancestor, return -1.
 

Example 1:


Input
["TreeAncestor", "getKthAncestor", "getKthAncestor", "getKthAncestor"]
[[7, [-1, 0, 0, 1, 1, 2, 2]], [3, 1], [5, 2], [6, 3]]
Output
[null, 1, 0, -1]

Explanation
TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);
treeAncestor.getKthAncestor(3, 1); // returns 1 which is the parent of 3
treeAncestor.getKthAncestor(5, 2); // returns 0 which is the grandparent of 5
treeAncestor.getKthAncestor(6, 3); // returns -1 because there is no such ancestor
 

Constraints:

1 <= k <= n <= 5 * 104
parent.length == n
parent[0] == -1
0 <= parent[i] < n for all 0 < i < n
0 <= node < n
There will be at most 5 * 104 queries. */
import java.util.Arrays;


//Binary Upifting


//https://www.youtube.com/watch?v=oib-XsjFa-M

public class _29_kth_ancestor_in_tree {
    static class TreeAncestor {

    int logn;
    int _2_power_j_th_Ances[][];

    public TreeAncestor(int n, int[] parent) {
        logn=log(n);
        _2_power_j_th_Ances=new int[n][logn];
        //no parent of 0th
        for(int row[]:_2_power_j_th_Ances)Arrays.fill(row,-1);
        
        for (int node = 0; node < n; node++) {
            _2_power_j_th_Ances[node][0] = parent[node];
        }
        
        for(int j=1;j<logn;j++){//very big mistake, revresed pos of outer and inner loop, just use common sense how on earth wil u get crt ans if u compute all jth for n nodes first, so compute step by step
            for(int node=1;node<n;node++){
                if(_2_power_j_th_Ances[node][j-1]!=-1)_2_power_j_th_Ances[node][j]= _2_power_j_th_Ances[_2_power_j_th_Ances[node][j-1]][j-1];
                else _2_power_j_th_Ances[node][j]=-1;
            }
        }


    }
    
    public int getKthAncestor(int node, int k) {
        for(int bit_number=0;bit_number<logn;bit_number++){//just leftmost 1 such that 2^thatPos<maxDepth
            if((k&(1<<bit_number))!=0) {
                node=_2_power_j_th_Ances[node][bit_number];
                if(node==-1) return -1;
            }
        }
        return node;
    }


    public int log(int n){
        int log=0;
        while((1<<log) <= n) log++;
        return log;
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
}
