
import java.util.*;

public class Solution {



    public static void main(String[] args) {
        Solution segT=new Solution(new int[]{3,1,2,7,3});
        segT.printSegTree();
        // segT.update(2, 23);
        segT.printSegTree();
        System.out.println(segT.getSum(1, 3));
    }

    int n;
    int seg[];
    int nums[];
    boolean used[];//to avoid printing uneccassary nodes 

    public Solution(int nums[]){
        this.nums=nums;
        this.n=nums.length;
        seg=new int[(n<<1)+1];//max 2n nodes
        used=new boolean[seg.length];
        build(0, 0, n-1);
    }


    public TreeNode getSegTreeRoot(int i){
        if(i>=seg.length ||!used[i]) return null;
        TreeNode root=new TreeNode(seg[i]);
        root.left=getSegTreeRoot(2*i+1);
        root.right=getSegTreeRoot(2*i+2);
        return root;
    }

    public void printSegTree(){
        TreeNode.prettyPrintTree(getSegTreeRoot(0), "", true);
    }

    private TreeNode build(int i,int l,int r){
        used[i]=true;
        if(l==r) return new TreeNode(seg[i]=nums[l]);
        int mid=(l+r)>>1;
        TreeNode root=new TreeNode(nums[mid]);
        root.left=build(2*i+1, l, mid);
        root.right=build(2*i+2, mid+1, r);
        seg[i]=seg[2*i+1]+seg[2*i+2];
        return root;
    }

    public int getSum(int from,int to){
        return _getSum(from, to, 0, 0, n-1);
    }
    private int _getSum(int from,int to,int i,int l,int r){
        if(l>to||r<from) return 0;
        if(l>=from && r<=to) return seg[i];
        int mid=(l+r)>>1;
        return _getSum(from, to, 2*i+1, l, mid)+_getSum(from, to, 2*i+2, mid+1, r);
    }

    public void update(int targetIdx,int newVal){//logN
        _update(0, 0, n-1, targetIdx, newVal);
    }
    private void _update(int i,int l,int r,int targetIdx,int newVal){
        if(l==r){
            seg[i]=newVal;
            return;
        }
        int mid=(l+r)>>1;
        if(targetIdx<=mid) _update(2*i+1,l,mid,targetIdx,newVal);//lies left
        else _update(2*i+2,mid+1,r,targetIdx,newVal);//lies right
        seg[i]=seg[2*i+1]+seg[2*i+2];
    }



    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return (this.val+"");
        }
        public static TreeNode constructTree(Integer[] arr) {
            if (arr.length == 0 || arr[0] == null)
                return null;

            TreeNode root = new TreeNode(arr[0]);
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            int index = 1;
            while (!queue.isEmpty() && index < arr.length) {
                TreeNode current = queue.poll();

                if (index < arr.length && arr[index] != null) {
                    current.left = new TreeNode(arr[index]);
                    queue.add(current.left);
                }
                index++;

                if (index < arr.length && arr[index] != null) {
                    current.right = new TreeNode(arr[index]);
                    queue.add(current.right);
                }
                index++;
            }

            return root;
        }
        public static void prettyPrintTree(TreeNode node, String prefix, boolean isLeft) {
            if (node == null)  return;
            if (node.right != null) prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
            System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node);
            if (node.left != null) prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }
}
