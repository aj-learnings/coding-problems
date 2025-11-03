/**
 * Given a Linked List, Reverse it using iteration as well as recursion
 */

#include <bits/stdc++.h>
using namespace std;

class Node {
public:
    int data;
    Node* next;

    Node(): data(0), next(nullptr) {}
    Node(int data): data(data), next(nullptr) {}
    Node(int data, Node* next): data(data), next(next) {}
};

Node* createLinkedList(int size = 10, int range = 100) {
    Node* head = new Node(rand()%range);
    Node* curr = head;
    for (int i=0; i<size-1; i++) {
        curr->next = new Node(rand()%range);
        curr = curr->next;
    }
    return head;
}

void printLinkedList(Node* head) {
    Node* curr = head;
    while (curr) {
        cout << curr->data << " ";
        curr = curr->next;
    }
    cout << "\n";
}

Node* reverseLinkedListIterative(Node* head) {
    Node *curr = head, *prev = nullptr;
    while (curr) {
        Node* next = curr->next;
        curr->next = prev;
        prev = curr;
        curr = next;
    }
    return prev;
}

Node* reverseLinkedListRecursive(Node* head) {
    if (head == nullptr || head->next == nullptr) {
        return head;
    }
    Node* newNode = reverseLinkedListRecursive(head->next);
    head->next->next = head;
    head->next = nullptr;
    return newNode;
}

int main () {
    
    Node* head = createLinkedList();
    printLinkedList(head);
    head = reverseLinkedListIterative(head);
    printLinkedList(head);
    head = reverseLinkedListRecursive(head);
    printLinkedList(head);
    
    cin.get();
    return 0;
}