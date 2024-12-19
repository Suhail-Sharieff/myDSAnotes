#include <stdio.h>
#include <stdlib.h>
#include <conio.h>

#define SIZE 10

int Queue[SIZE];
int front = -1;
int rear = -1;

void display()
{
    if (front==-1 && rear==-1)
    {
        printf("Queue empty\n");
        return;
    }
    
    printf("Current Queue: ");
    printf("Front --> ");
    for (int i = front; i <= rear; i++)
        printf("%d ", Queue[i]);
    printf(" <-- Rear\n");
}


void push(int e){
    if (rear==SIZE-1)
    {
        printf("Queue full\n");
        return;
    }
    
    if (front==-1 && rear==-1)
    {
        front=0;
    }
    Queue[++rear]=e;
    display();
}


void poll(){
    if (rear==-1)
    {
        printf("Nothing to poll");
        return;
    }
    if (front==rear)
    {
        printf("%d deleted successfully\n",Queue[front]);
        front=rear=-1;
        return;
    }
    
    int rem=Queue[front++];
    display();
}


int main()
{
    display();
    push(34);
    push(45);
    poll();
    return 0;
}
