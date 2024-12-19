#include<stdio.h>
#include<stdlib.h>
#include<conio.h>

//this is implementaion using arrays
#define SIZE 10
int Stack[SIZE];
int top=-1;

void display(){
    if (top==-1)
    {
        printf("Empty stack\n");
        return;
    }
    printf("Current Stack: ");
    for(int i=top;i>=0;i--)
    printf("%d ",Stack[i]);
    printf("\n");
}

void push(int e){
    if (top==SIZE)
    {
       printf("Stack is full\n");
       return;
    }
    Stack[++top]=e;
    display();
}

void pop(){
    if (top==-1)
    {
        printf("Nothing to pop\n");
        return;
    }
    int rem=Stack[top--];
    printf("%d is poped successfully\n",rem);
    display();
}



int main()
{
    push(34);
    push(45);
    push(12);
    pop();
    return 0;
}




