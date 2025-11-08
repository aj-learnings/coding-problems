/**
 * Given a Doubly Linked List, Reverse it using iteration as well as recursion
 */

#include <bits/stdc++.h>
using namespace std;

class Node {
public:
    int data;
    Node *left, * right;

    Node() : data(0), left(nullptr), right(nullptr) {}
    Node(int data): data(data), left(nullptr), right(nullptr) {}
    Node(int data, Node *left, Node *right): data(data), left(left), right(right) {}
};

Node* createDoublyLinkedList(int size = 10, int range = 100) {
    Node* head = new Node(rand()%range);
    Node *curr = head, *prev = head;
    for (int i=0; i<size-1; i++) {
        curr->right = new Node(rand()%range);
        curr = curr->right;
        curr->left = prev;
        prev = curr;
    }
    return head;
}

void printDoublyLinkedList(Node* head) {
    Node* curr = head;
    while (curr->right != nullptr) {
        cout << curr->data << " ";
        curr = curr->right;
    }
    cout << curr->data << "\n";
    while(curr) {
        cout << curr->data << " ";
        curr = curr->left;
    }
    cout << "\n";
}

Node* reverseDoublyLinkedListIterative(Node* head) {
    Node *curr = head, *prev = nullptr;
    while (curr) {
        Node* next = curr->right;
        curr->right = prev;
        if (prev) {
            prev->left = curr;
        }
        prev = curr;
        curr = next;
    }
    prev->left = nullptr;
    return prev;
}

Node* reverseDoublyLinkedListRecursive(Node* head) {
    if (head->right == nullptr) {
        return head;
    }
    Node *newNode = reverseDoublyLinkedListRecursive(head->right);
    head->right->right = head;
    head->left = head->right;
    head->right = nullptr;
    newNode->left = nullptr;
    return newNode;
}

int main() {

    Node* head = createDoublyLinkedList();
    printDoublyLinkedList(head);
    head = reverseDoublyLinkedListIterative(head);
    printDoublyLinkedList(head);
    head = reverseDoublyLinkedListRecursive(head);
    printDoublyLinkedList(head);
}