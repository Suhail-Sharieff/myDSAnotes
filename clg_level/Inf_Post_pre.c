#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define MAXLEN 20

char Stack[MAXLEN];
int top = -1;

void push(char ch)
{
    if (top == MAXLEN - 1)
    {
        printf("Stack full");
        return;
    }
    Stack[++top] = ch;
}

char pop()
{
    if (top == -1)
    {
        printf("Empty\n");
        return '\0';
    }

    return Stack[top--];
}

bool isEmpty()
{
    return (top == -1);
}

char peek()
{
    if (top == -1)
    {
        printf("Empty\n");
        return '\0';
    }
    return Stack[top];
}

void displayStack()
{
    if (isEmpty())
    {
        printf("Empty\n");
        return;
    }

    for (int i = top; i >= 0; i--)
        printf("%c", Stack[i]);
    printf("\n");
}

bool isOperator(char ch)
{
    return (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^');
}

int precedence(char ch)
{
    switch (ch)
    {
    case '+':
    case '-':
        return 1;

    case '*':
    case '/':
        return 2;

    case '^':
        return 3;

    default:
        return -1;
    }
}

void displayArr(char arr[], int len)
{
    for (int i = 0; i < len; i++)
        printf("%c", arr[i]);
    printf("\n");
}

void reverseArr(char arr[], int len)
{
    for (int i = 0; i < len / 2; i++)
    {
        char t = arr[i];
        arr[i] = arr[len - i - 1];
        arr[len - i - 1] = t;
    }
}

void infix_to_postfix(char infix[], int len, char postfix[])
{
    int j = 0;
    for (int i = 0; i < len; i++)
    {
        char curr = infix[i];
        if (curr == '(')
        {
            push(curr);
        }
        else if (curr == ')')
        {
            while (!isEmpty() && peek() != '(')
            {
                postfix[j++] = pop();
            }
            pop(); // Pop '('
        }
        else if (isOperator(curr))
        {
            while (!isEmpty() && precedence(curr) <= precedence(peek()))
            {
                postfix[j++] = pop();
            }
            push(curr);
        }
        else
        {
            postfix[j++] = curr;
        }
    }

    // remaining:
    while (!isEmpty())
    {
        postfix[j++] = pop();
    }
    postfix[j] = '\0'; // Null-terminate the string
}

void infix_to_prefix(char infix[], int len)
{
    // Step 1: Reverse the infix expression
    reverseArr(infix, len);

    // Step 2: Replace '(' with ')' and vice versa
    for (int i = 0; i < len; i++)
    {
        if (infix[i] == '(')
            infix[i] = ')';
        else if (infix[i] == ')')
            infix[i] = '(';
    }

    // Step 3: Convert to postfix
    char postfix[MAXLEN];
    infix_to_postfix(infix, len, postfix);

    // Step 4: Reverse the postfix expression to get the prefix expression
    reverseArr(postfix, strlen(postfix));

    // Step 5: Print the result
    printf("Prefix exp: ");
    displayArr(postfix, strlen(postfix));
}

int main()
{
    char exp[] = "(1+2)+3";
    int len = strlen(exp);
    printf("Infix exp: %s\n", exp);

    printf("\nInfix to Postfix conversion:\n");
    char postfix[MAXLEN];
    infix_to_postfix(exp, len, postfix);
    printf("Postfix exp: ");
    displayArr(postfix, strlen(postfix));

    printf("\nInfix to Prefix conversion:\n");
    infix_to_prefix(exp, len);

    return 0;
}
