#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>

struct treenode{
    struct treenode* left;
    struct treenode* right;
    int val;
    int has_right_thread;
};

typedef struct treenode* TreeNode;

TreeNode create_left(TreeNode currNode,int val){
    TreeNode newNode=(struct treenode*)malloc(sizeof(struct treenode));
    newNode->left=newNode->right=NULL;newNode->val=val;newNode->has_right_thread=0;
    
    newNode->right=currNode;//form a thread to parent
    newNode->has_right_thread=1;

    currNode->left=newNode;
    return newNode;
}

TreeNode create_right(TreeNode currNode,int val,TreeNode curr_s_right_threaded_node){
    TreeNode newNode=(struct treenode*)malloc(sizeof(struct treenode));
    newNode->left=newNode->right=NULL;newNode->val=val;newNode->has_right_thread=0;

    currNode->right=newNode;
    currNode->has_right_thread=0;

    newNode->right=curr_s_right_threaded_node;
    newNode->has_right_thread=1;

    return newNode;
    
}

TreeNode get_left_most_node(TreeNode root){
    if(root->left==NULL) return root;
    return get_left_most_node(root->left);
}


void traverse_in_right_BT(TreeNode root){
    TreeNode curr=get_left_most_node(root);
    while (curr!=NULL)
    {
        printf("%d => ",curr->val);
        curr=curr->right;
    }
    printf("\n");
}


void insert_into_right_t_BT(int val,TreeNode root){
    TreeNode curr=root;
    while (curr!=NULL)
    {
        if(curr->val < val){
            //must be placed on right
            if(curr->right==NULL){  
                curr->right=create_right(curr,val,NULL);
                break;
            }else if(curr->has_right_thread){//it has right child
                //curr node has a thread on its right
                TreeNode next_node_via_thread=curr->right;
                curr->right=create_right(curr,val,next_node_via_thread);
                break;
            }else{//curr has right child that is not a thread, just move forawrd
                curr=curr->right;
            }
        }else{
            //must be placed on left
            if(curr->left==NULL){
                //place new node there and form a thread from that newNode to curr as well
                curr->left=create_left(curr,val);
                break;
            }else{
                curr=curr->left;
            }
        }
    }

    printf("Traversal of our BT :");
    traverse_in_right_BT(root);
}






int main(int argc, char const *argv[])
{
    TreeNode root=(struct treenode*)malloc(sizeof(struct treenode));
    root->left=root->right=NULL;root->val=34;root->has_right_thread=0;

    int val,choice;
    while (1)
    {
        printf("1:Insert 0:Cancel");
        scanf("%d",&choice);
        if(choice==1){
            printf("Eneter value: ");
            scanf("%d",&val);
            insert_into_right_t_BT(val,root);
        }
    }
    

    return 0;
}
