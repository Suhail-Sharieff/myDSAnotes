#include<stdio.h>
#include<conio.h>
#include<stdlib.h>

struct NODE 
{ 
int val; 
struct NODE *left; 
struct NODE *right; 
}; 
typedef struct NODE* Node; 


Node getNode(){
    Node newNode=(Node)malloc(sizeof(struct NODE));
    printf("enter data for new Node :");
    scanf("%d",&newNode->val);
    newNode->left=NULL;
    newNode->right=NULL;
    return  newNode;
}

void insert_at()