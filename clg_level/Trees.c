#include<stdio.h>
#include<stdlib.h>
#include<conio.h>



struct TreeNode{
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

typedef struct TreeNode* TreeNode;

int wannnaMakeOnLeft(TreeNode root){
    printf("Do u want to create left child of %d\n ? YES-1 NO-0 ",root->val);
    int  k=0;
    scanf("%d",&k);
    return k;
}

int wannaMakeOnRight(TreeNode root){
    printf("Do u want to create right child of %d\n? YES-1 NO-0 ",root->val);
    int  k=0;
    scanf("%d",&k);
    return k;
}

TreeNode scanNode(){
    printf("Enter the data of ur new Node : ");
    TreeNode newNode=(TreeNode)malloc(sizeof(struct TreeNode));
    scanf("%d",&newNode->val);
    newNode->left=NULL;
    newNode->right=NULL;
}

void printPreorder(TreeNode root){
    if(root==NULL) return;
    printf("%d->",root->val);
    printPreorder(root->left);
    printPreorder(root->right);
}   
void printInorder(TreeNode root){
    if(root==NULL) return;
    printInorder(root->left);
    printf("%d->",root->val);
    printInorder(root->right);
}   
void printPostorder(TreeNode root){
    if(root==NULL) return;
    printPostorder(root->left);
    printPostorder(root->right);
    printf("%d->",root->val);
}   

void printTraversals(TreeNode root){
    printf("Your Current Tree as per preorder traversal is :");
    printPreorder(root);
    printf("\n");
    printf("Your Current Tree as per inorder traversal is :");
    printInorder(root);
    printf("\n");
    printf("Your Current Tree as per postorder traversal is :");
    printPostorder(root);
    printf("\n");
}

void createTree(TreeNode root){
    if (wannnaMakeOnLeft(root)==1)
    {
        TreeNode newNode=scanNode();
        root->left=newNode;
        createTree(root->left);
    }
    if (wannaMakeOnRight(root)==1)
    {
        TreeNode newNode=scanNode();
        root->right=newNode;
        createTree(root->right);
    }
}

int main()
{   
    TreeNode root=scanNode();
    createTree(root);
    printTraversals(root);
    return 0;
}
