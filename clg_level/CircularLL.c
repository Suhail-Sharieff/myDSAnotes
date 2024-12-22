#include <stdio.h>
#include <stdlib.h>

struct NODE
{
    int val;
    struct NODE *next;
};

typedef struct NODE *NODE;

// Function to create a new node
NODE getnode(int val) {
    NODE new = (NODE)malloc(sizeof(struct NODE));
    new->val = val;
    new->next = NULL;
    return new;
}

// Function to display the circular linked list
void display(NODE head) {
    if (head == NULL || head->next == head) {
        printf("Empty Circular Linked List\n");
        return;
    }

    NODE temp = head->next; // Start from the first node (after the header)

    do {
        printf("%d -> ", temp->val);
        temp = temp->next;
    } while (temp != head);

    printf("(back to header)\n");
}

// Function to insert a node at the front (after the header node)
NODE insert_front(NODE head, int val) {
    if (head == NULL) {
        head = getnode(-1);  // Create a header node
        head->next = head;   // Circular reference to itself
    }

    NODE newNode = getnode(val);
    newNode->next = head->next; // New node points to the first actual node
    head->next = newNode;       // Header node now points to the new node

    return head;
}

// Function to insert a node at the rear (before the header node)
NODE insert_rear(NODE head, int val) {
    if (head == NULL) {
        head = getnode(-1);  // Create a header node
        head->next = head;   // Circular reference to itself
    }

    NODE newNode = getnode(val);
    NODE temp = head;

    // Traverse to the last node before the header
    while (temp->next != head) {
        temp = temp->next;
    }

    temp->next = newNode; // Last node now points to the new node
    newNode->next = head; // New node points to the header node

    return head;
}

// Function to delete the front node (node after the header)
NODE delete_front(NODE head) {
    if (head == NULL || head->next == head) {
        printf("Cannot delete from empty list\n");
        return head;
    }

    NODE temp = head->next; // The first actual node
    head->next = temp->next; // Header node now points to the second node

    free(temp); // Free the deleted node

    return head;
}

// Function to delete the rear node (node before the header)
NODE delete_rear(NODE head) {
    if (head == NULL || head->next == head) {
        printf("Cannot delete from empty list\n");
        return head;
    }

    NODE temp = head;
    NODE prev = NULL;

    // Traverse to the last node before the header
    while (temp->next != head) {
        prev = temp;
        temp = temp->next;
    }

    // Now, temp is the last node, and prev is the second last node
    prev->next = head; // Second last node now points to the header
    free(temp); // Free the last node

    return head;
}

int main() {
    NODE head = NULL;

    // Insert nodes at the front and rear and display
    head = insert_front(head, 10);
    head = insert_rear(head, 20);
    head = insert_front(head, 30);
    head = insert_rear(head, 40);
    display(head);  // Display list: 30 -> 10 -> 20 -> 40 -> (back to header)

    // Delete nodes from front and rear and display
    head = delete_front(head);
    display(head);  // Display list: 10 -> 20 -> 40 -> (back to header)

    head = delete_rear(head);
    display(head);  // Display list: 10 -> 20 -> (back to header)

    return 0;
}
