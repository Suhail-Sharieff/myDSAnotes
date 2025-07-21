package _14_Segment_Tree;

import java.util.LinkedList;
import java.util.Queue;


public class SegmentTree {


    //implementation using pointers, observe that its hecksome and memory consuming, so we will use of SegmntTree2 for clairty, chanfe is that we dont include start and end for each node,instaed use i,l,r on the fly

    static class SegmentTree_01 {

        public static void main(String[] args) {
            // SegmentTree_01 tree=new SegmentTree_01(new int[]{1,2,3,4,5});
            // System.out.println(tree);
            // System.out.println(tree.getRangeSum(tree.root, 1, 3));
            // tree.update(tree.root, 3, 23);
            // System.out.println(tree);
            SegmentTree_02 tree=new SegmentTree_02(new int[]{1,2,3,4,5});
            tree.printSegTree();
            System.out.println(tree.getRangeSum(0,0,5,1, 3));
            tree.update(0,0,5,3, 23);
            tree.printSegTree();
        }

        int n;
        int arr[];
        SegTreeNode root;

        public SegmentTree_01(int arr[]){
            this.arr=arr;
            this.n=arr.length;
            this.root=build( 0, n-1);
        }

        private SegTreeNode build(int l,int r){
            if(l==r) return new SegTreeNode(arr[l], l, r);
            int mid=(l+r)>>1;
            SegTreeNode root=new SegTreeNode(arr[mid], l, r);
            root.left=build(l, mid);
            root.right=build(mid+1, r);
            root.val=root.left.val+root.right.val;
            return root;
        }

        public int getRangeSum(SegTreeNode curr,int from,int to){
            if(curr==null || curr.end<from || to<curr.start) return 0;
            if(from<=curr.start && curr.end<=to) return curr.val;
            return getRangeSum(curr.left, from, to)+getRangeSum(curr.right,from, to);
        }

        public void update(SegTreeNode root,int idx,int newVal){
            if(root.start==root.end){root.val=newVal;return;}
            int mid=(root.start+root.end)>>1;
            if(idx<=mid) update(root.left, idx, newVal);
            else update(root.right, idx, newVal);
            root.val=root.left.val+root.right.val;
        }

        @Override
        public String toString() {
            printSegTree(root, "", true);
            return "";
        }

        private void printSegTree(SegTreeNode node, String prefix, boolean isLeft) {
            if (node == null)  return;
            if (node.right != null) printSegTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
            System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.val);
            if (node.left != null) printSegTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }


        static class SegTreeNode{
            int val;
            SegTreeNode left;
            SegTreeNode right;
            int start;
            int end;
            public SegTreeNode(int val,int start,int end){
                this.val=val;
                this.start=start;
                this.end=end;
            }
            @Override
            public String toString() {
                return "["+val+":("+start+","+end+")]";
            }
        }
    }


    //this donesnt use pointers instead play with idxs
    static class SegmentTree_02{    
        int n;
        int arr[];
        int seg[];
        public SegmentTree_02(int arr[]){
            this.arr=arr;
            this.n=arr.length;
            this.seg=new int[n<<2];
            build(0, 0, n-1);
        }

        public void build(int i,int l,int r){
            if(l==r){seg[i]=arr[l];return;}
            int mid=(l+r)>>1;
            build(2*i+1, l, mid);
            build(2*i+2, mid+1, r);
            seg[i]=seg[2*i+1]+seg[2*i+2];
        }

        public int getRangeSum(int i,int l,int r,int from,int to){
            if(r<from||to<l) return 0;
            if(from<=l&&r<=to) return seg[i];
            int mid=(l+r)>>1;
            return getRangeSum(2*i+1, l, mid, from, to)+getRangeSum(2*i+2, mid+1, r, from, to);
        }

        public void update(int i,int l,int r,int idx,int newVal){
            if(l==r){seg[i]=arr[idx]=newVal; return;}
            int mid=(l+r)>>1;
            if(mid<=idx) update(2*i+1, l, mid, idx, newVal);
            else update(2*i+2, mid+1, r, idx, newVal);
            seg[i]=seg[2*i+1]+seg[2*i+2];
        }



       public TreeNode getSegTreeRoot(int i){
            if(i>=seg.length || seg[i] == 0) return null;
            TreeNode root=new TreeNode(seg[i]);
            root.left=getSegTreeRoot(2*i+1);
            root.right=getSegTreeRoot(2*i+2);
            return root;
        }

        public void printSegTree(){
            TreeNode.prettyPrintTree(getSegTreeRoot(0), "", true);
        }

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