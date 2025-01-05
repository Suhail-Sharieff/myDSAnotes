## Introdcution:
- Infix format: ```<num><operator><num> ``` ex: x+y
- Prefix format: ```<operator><num><num> ``` ex: +xy
- Postfix format: ```<num><num><opeator> ``` ex: xy+


## Examples:(start after parenthesizing)

### 1. Convert A+(B*C) to
- prefix:  A+[ * BC ] -> +A*BC
- postfix: A+[ BC * ] -> ABC*+

### 2. Convert P-Q-R/A
- parenthesize: (P-Q)-(R/A)
- prefix: [-PQ]-[ / RA]  -> --PQ/RA
- postfix: [PQ-]-[RA/] -> PQ-RA/- 

### 3. Convert (M-N)*(P+Q)
- prefix: [-MN]*[+PQ] -> -MN+PQ *
- postfix: [MN-]*[PQ+] -> MN-PQ+ *

### 4. Convert X-Y/Z-K*D
- prenthesize: (X-(Y/Z))-(K*D)
- prefix: (X-[/YZ])-[* KD]-> [-X/YZ]-[* KD] -> --X/YZ*KD
- postfix: (X-[YZ/])-[KD*] -> [XYZ/-]-[KD*] -> XYZ/-KD*-



## IMP rules while INFIX TO POSTFIX:
- if found char append to ans String
- if found any operator :
  - push to top of stack only if the current peek element has less precedence that the operator to be pushed and  if its not the case(the current peek element has more precedence), pop until u find that peek has less predence than operator to push and append the popped out stuff to ans string
- at last some oprator may be present in stack, so just start popping until empty and appending it



# IMP:
Is Prefix the Reverse of Postfix?
While prefix and postfix notations are related, one is not just the reverse of the other. The structure of the expressions is different. However, you can convert between infix, prefix, and postfix through specific algorithms.

Conversion Between Prefix and Postfix
To convert between postfix and prefix:

Convert Infix to Postfix (like you already did in your code).
Convert Postfix to Prefix by reversing the process used in postfix to infix conversion. This can be done by:
Reversing the postfix expression.
Reversing the position of the operands and operators in each sub-expression.
Reversing the final result to get the desired prefix expression.
Algorithm to Convert Postfix to Prefix
Here’s a general outline of the steps to convert a postfix expression to a prefix expression:

Reverse the Postfix Expression: Reverse the postfix expression first.

Apply the Infix to Postfix Algorithm: Treat the reversed postfix as an infix expression and convert it back to postfix.

Reverse the Result: Reverse the resulting expression again to get the final prefix expression.

Example Conversion:
Let’s take an example:

1. Postfix Expression:
ABC+*D/

The postfix expression represents:
A * (B + C) / D
2. Step 1 - Reverse the Postfix Expression:
Reversed postfix:
/D*+CBA

3. Step 2 - Apply Infix to Postfix on the Reversed Expression:
Convert /D*+CBA to prefix:

Treat it as an infix expression:
D / (A * (B + C))
The corresponding prefix notation is:
/ * A + B C D

4. Resulting Prefix Expression:
The final prefix expression is:
/ * A + B C D


# Full code in C
```C
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

int top = -1;
char stack[20];

// Check if stack is empty
bool isEmpty() {
    return top == -1;
}

// Check if character is an operator
bool isOperator(char ch) {
    return (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^');
}

// Return the precedence of operators
int precedence(char ch) {
    switch (ch) {
        case '^': return 3;
        case '*':
        case '/': return 2;
        case '+':
        case '-': return 1;
        default: return -1;
    }
}

// Push an element to the stack
void push(char ch) {
    stack[++top] = ch;
}

// Pop an element from the stack
char pop() {
    return stack[top--];
}

// Convert infix expression to postfix expression
void infixToPostFix(char infix[], char postfix[]) {
    int len = strlen(infix);
    int j = 0;
    
    for (int i = 0; i < len; i++) {
        char curr = infix[i];
        
        if (curr == '(') {
            push(curr);
        } else if (curr == ')') {
            while (!isEmpty() && stack[top] != '(') {
                postfix[j++] = pop();
            }
            pop(); // Discard '('
        } else if (isOperator(curr)) {
            while (!isEmpty() && precedence(stack[top]) >= precedence(curr)) {
                postfix[j++] = pop();
            }
            push(curr);
        } else {
            postfix[j++] = curr;  // Operand
        }
    }
    
    while (!isEmpty()) {
        postfix[j++] = pop();
    }
    postfix[j] = '\0';  // Null-terminate the string
    
    printf("Postfix: %s\n", postfix);
}

// Convert postfix expression to prefix expression
void postfixToPrefix(char postfix[], char prefix[]) {
    int len = strlen(postfix);
    char tempStack[len];  // Temporary stack to hold operands during conversion
    int topTemp = -1;
    int j = 0;

    // Traverse the postfix expression from left to right
    for (int i = 0; i < len; i++) {
        char curr = postfix[i];
        
        if (!isOperator(curr)) {
            // If operand, push it to the temporary stack
            tempStack[++topTemp] = curr;
        } else {
            // If operator, pop two operands, apply operator, and push the result
            char operand2 = tempStack[topTemp--];
            char operand1 = tempStack[topTemp--];
            tempStack[++topTemp] = curr;  // Push the operator
            tempStack[++topTemp] = operand1;  // Push the first operand
            tempStack[++topTemp] = operand2;  // Push the second operand
        }
    }

    // The prefix expression will be at the top of the stack
    for (int i = 0; i <= topTemp; i++) {
        prefix[i] = tempStack[i];
    }
    prefix[topTemp + 1] = '\0';  // Null-terminate the string
    
    printf("Prefix: %s\n", prefix);
}

int main() {
    char infix[] = "A-(B+C)/D";
    char postfix[strlen(infix) + 1];  // Postfix expression buffer
    char prefix[strlen(infix) + 1];   // Prefix expression buffer

    // Convert infix to postfix
    infixToPostFix(infix, postfix);
    
    // Convert postfix to prefix
    postfixToPrefix(postfix, prefix);
    
    return 0;
}


```