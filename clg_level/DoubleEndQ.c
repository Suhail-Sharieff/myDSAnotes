#include <stdio.h>
#include <stdlib.h> 

// Define the Node structure
struct Node {
    int val;
    struct Node* next;
};

typedef struct Node* Node;

// Function to create a new node and input a value
Node getNode() {
    Node newNode = (Node)malloc(sizeof(struct Node)); // Allocate memory
    if (newNode == NULL) {
        printf("Memory allocation failed!\n");
        exit(1); // Exit if memory allocation fails
    }
    printf("Enter value: ");
    scanf("%d", &newNode->val);
    newNode->next = NULL;
    return newNode; // Return the newly created node
}

// Function to display the linked list
void display(Node first) {
    Node temp = first;
    while (temp != NULL) {
        printf("%d -> ", temp->val);
        temp = temp->next;
    }
    printf("NULL\n");
}

// Function to insert a node at the front
Node insert_front(Node first) {
    Node newNode = getNode();  // Create a new node
    newNode->next = first;     // Make the new node's next point to the old first node
    first = newNode;           // Update first to the new node
    return first;
}

// Function to insert a node at the rear
Node insert_rear(Node first) {
    Node newNode = getNode();  // Create a new node
    if (first == NULL) {       // If the list is empty, newNode will be the first node
        return newNode;
    }
    Node temp = first;
    while (temp->next != NULL) { // Traverse to the end of the list
        temp = temp->next;
    }
    temp->next = newNode;   // Insert the new node at the end
    return first;
}

// Function to delete the first node
Node delete_first(Node first) {
    if (first == NULL) {
        printf("The list is empty!\n");
        return first;
    }
    Node temp = first;
    first = first->next;   // Make first point to the second node
    free(temp);            // Free the memory of the old first node
    return first;
}

// Function to delete the rear node
Node delete_rear(Node first) {
    if (first == NULL) {
        printf("The list is empty!\n");
        return first;
    }
    if (first->next == NULL) {  // If there is only one node
        free(first);             // Free the memory and return NULL
        return NULL;
    }
    Node temp = first;
    while (temp->next != NULL && temp->next->next != NULL) {
        temp = temp->next;  // Traverse to the second-last node
    }
    free(temp->next);  // Free the last node
    temp->next = NULL;  // Set the second-last node's next to NULL
    return first;
}

int main() {
    Node first = NULL; // Start with an empty list
    while (1) {
        printf("1> Insert Front 2> Insert Rear 3> Delete Front 4> Delete Rear 5> Exit\n");
        int op;
        scanf("%d", &op);
        switch (op) {
        case 1:
            first = insert_front(first);
            display(first);
            break;
        case 2:
            first = insert_rear(first);
            display(first);
            break;
        case 3:
            first = delete_first(first);
            display(first);
            break;
        case 4:
            first = delete_rear(first);
            display(first);
            break;
        case 5:
            exit(0);
        default:
            printf("Invalid option! Please try again.\n");
        }
    }

    return 0;
}
