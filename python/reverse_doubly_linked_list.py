# Given a Doubly Linked List, Reverse it using iteration as well as recursion

import random
from typing import Optional

class Node:
    def __init__(self, newData):
        self.data: int = newData
        self.left: Optional[Node] = None
        self.right: Optional[Node] = None

def createDoublyLinkedList(size: int, limit: int): 
    head = Node(random.randint(1, limit))
    curr = head
    for i in range(1,size) :
        curr.right = Node(random.randint(1, limit))
        curr.right.left = curr
        curr = curr.right
    return head

def printDoublyLinkedList(head: Node) :
    curr = head
    while curr.right is not None :
        print(curr.data, end = " ")
        curr = curr.right
    print(curr.data)
    while curr is not None:
        print(curr.data, end = " ")
        curr = curr.left
    print()

def reverseDoublyLinkedListIterative(head: Node) :
    curr, prev = head, None
    while curr is not None :
        next = curr.right
        curr.right = prev
        if prev is not None :
            prev.left = curr
        prev = curr
        curr = next
    prev.left = None
    return prev

def reverseDoublyLinkedListRecursively(head: Node) :
    if head is None or head.right is None :
        return head
    newNode = reverseDoublyLinkedListRecursively(head.right)
    head.right.right = head
    head.left = head.right
    head.right = None
    newNode.left = None
    return newNode

if __name__ == "__main__":

    head = createDoublyLinkedList(10, 100)
    printDoublyLinkedList(head)
    head = reverseDoublyLinkedListIterative(head)
    printDoublyLinkedList(head)
    head = reverseDoublyLinkedListRecursively(head)
    printDoublyLinkedList(head)