package _4_Trees;

public class test {

    public static void main(String[] args) {
        int preOrder[]={3,9,20,15,7};
        int inorder[]={9,3,15,20,7};

        int preIdx[]={0};
        TreeNode root=build(preOrder, inorder, 0, inorder.length-1,preIdx);

        // TreeNode.displayLevelByLevel(root);



    }

    public static TreeNode build(int preOrder[],int inorder[],int inStart,int inEnd,int preIdx[]){
        if (preIdx[0]>=preOrder.length||inStart>inEnd) {
            return null;
        }
        //preorder's leftmost is root
        int idxofRoot=idxOfRoot(preOrder[preIdx[0]], inorder);
        TreeNode root=new TreeNode(preOrder[preIdx[0]++]);
        TreeNode leftPart=build(preOrder, inorder, inStart  , idxofRoot-1,preIdx);
        TreeNode rightPart=build(preOrder, inorder, idxofRoot+1, inEnd,preIdx);


        root.left=leftPart;
        root.right=rightPart;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("--------------");
        TreeNode.displayLevelByLevel(root);
        System.out.println("--------------");
        return root;



    }

    //preorder start will obviosly be the root, return idxof that root from inorder
    public static int idxOfRoot(int targetFromPreorder,int inorder[]){
        for(int i=0;i<inorder.length;i++){
            if (inorder[i]==targetFromPreorder) {
                return i;
            }
        }
        return -1;
    }
}