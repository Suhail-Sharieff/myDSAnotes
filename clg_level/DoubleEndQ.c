#include<stdio.h> 
#include<conio.h> 
#include<stdlib.h> 


struct Node {
    int val;
    struct Node* next;
};

typedef struct  Node* Node;
Node first=NULL;
Node last=NULL;

Node addNode(){
    printf("Enter the value of new Node u want to insert :");
    Node newNode=(Node)malloc(sizeof(struct Node));
    scanf("%d",&newNode->val);
    newNode->next=NULL;
    return newNode;
}

void displayQ(){
    Node temp=first;
    printf("Current queue is : Front --> ");
    while (temp!=NULL)
    {
        printf("%d ,",temp->val);
        temp=temp->next;
    }

    printf(" <-- Last\n");
    
}

void insertFront(){
    Node newNode=addNode();
    if (first==NULL && last==NULL)
    {
        first=last=newNode;
        displayQ();
        return;
    }
    newNode->next=first;
    first=newNode;
    displayQ();
}
void insertLast(){
    Node newNode=addNode();
    if (first==NULL && last==NULL)
    {
        first=last=newNode;
        displayQ();
        return;
    }
    last->next=newNode;
    last=newNode;
    displayQ();
}



int main()
{
    displayQ();
    insertFront();
    insertFront();
    insertLast();
    return 0;
}
