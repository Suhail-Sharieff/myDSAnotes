#include<stdio.h>
#include<conio.h>
#include<stdlib.h>

struct TREENODE {
    int val;
    struct TREENODE* left;
    struct TREENODE* right;
};

typedef struct TREENODE* TreeNode;


TreeNode createNode(int val){
    TreeNode newNode=(TreeNode)malloc(sizeof(struct TREENODE));
    newNode->val=val;
    newNode->left=newNode->right=NULL;
    return newNode;
}


void inorder(TreeNode root){
    if(root==NULL) return;
    printf(" %d ->",root->val);
    inorder(root->left);
    inorder(root->right);
}


void insertNode(TreeNode root,int val){
    TreeNode prev=NULL;
    while (root!=NULL)
    {
        prev=root;
        if(root->val<val){
            root=root->right;
        }else{
            root=root->left;
        }
    }
    if (prev->val<val)
    {
        prev->right=createNode(val);
    }else{
        prev->left=createNode(val);
    }
    
}

TreeNode inorderSucc(TreeNode root){
    //succ is found on left most node of right part of the node we wanna delete
    root=root->right;
    while (root!=NULL && root->left!=NULL)
    {
        root=root->left;
    }
    return root;
}

TreeNode deleteNode(TreeNode root,int val){
    if(root==NULL) return NULL;
    //at each sucessive deletions u neded to find successor, suppose u reach at leaf, just remove without doing anything
    if (root->left==NULL && root->right==NULL)
    {
        root=NULL;
        free(root);
        return NULL;
    }
    

    if (val==root->val)
    {
        TreeNode inorSucc=inorderSucc(root);
        root->val=inorSucc->val;
        root->right=deleteNode(root->right,val);//coz we now need to handle succ's deletion too since it may have childrenn and is on right
    }
    

    if (val<root->val
    )
    {
        //find on left
        root->left=deleteNode(root->left,val);
    }else{
        root->right=deleteNode(root->right,val);
    }
    
    return root;

}


int main()
{
    TreeNode root=createNode(5);
    root->left=createNode(3);
    root->right=createNode(6);
    root->left->left=createNode(1);
    root->left->right=createNode(4);
    insertNode(root,-1);
    deleteNode(root,3);
    inorder(root);
    free(root);
    return 0;
}

