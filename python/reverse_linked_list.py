# Given a Linked List, Reverse it using iteration as well as recursion

import random
from typing import Optional

class Node:
    def __init__(self, newData):
        self.data: int = newData
        self.next: Optional[Node] = None

def createLinkedList(size, limit):
    head = Node(random.randint(1, limit))
    curr = head
    for i in range(1,size) :
        curr.next = Node(random.randint(1, limit))
        curr = curr.next
    return head

def printLinkedList(head):
    curr = head
    while curr is not None:
        print(curr.data, end = " ")
        curr = curr.next
    print()

def reverseLinkedListIterative(head):
    curr, prev = head, None
    while curr is not None:
        next = curr.next
        curr.next = prev
        prev = curr
        curr = next
    return prev

def reverseLinkedListRecursively(head):
    if head is None or head.next is None:
        return head
    newNode = reverseLinkedListRecursively(head.next)
    head.next.next = head
    head.next = None
    return newNode

if __name__ == "__main__":

    head = createLinkedList(10, 100)
    printLinkedList(head)
    head = reverseLinkedListIterative(head)
    printLinkedList(head)
    head = reverseLinkedListRecursively(head)
    printLinkedList(head)