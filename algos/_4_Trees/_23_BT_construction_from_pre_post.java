package _4_Trees;

public class _23_BT_construction_from_pre_post {
        public int[] pre; 
        public int[] post; 

        //https://www.youtube.com/watch?v=6JDV3kIFyjU
        
        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            pre=preorder;//root left right
            post=postorder;//left right root
            return build(0,pre.length-1,0);
        }
        TreeNode build(int preStart,int preEnd,int postStart){
            if(preStart>preEnd) return null;
            if(preStart==preEnd) return new TreeNode(pre[preStart]);//important to handle coz down we have used pre[preStart+1], for this we need min 2 nodes, so handle case when only 1 node here
            TreeNode root=new TreeNode(pre[preStart]);
            int leftSubTreeRootVal=pre[preStart+1];
            int j=postStart;
            while(post[j]!=leftSubTreeRootVal) j++;
            int nNodesOnLeftSubTree=j-postStart+1;
            root.left=build(preStart+1,preStart+nNodesOnLeftSubTree,postStart);
            root.right=build(preStart+nNodesOnLeftSubTree+1,preEnd,postStart+nNodesOnLeftSubTree);
            return root;
        }
}
