/**
 * Given a Doubly Linked List, Reverse it using iteration as well as recursion
*/

package main;

import (
	"fmt"
	"math/rand"
	"time"
)

type Node struct {
	data int;
	left *Node;
	right *Node;
}

func createDoublyLinkedList(size int, limit int) *Node {
	head := &Node{ data: rand.Intn(limit), left: nil, right: nil };
	curr := head
	for i:=2; i<=size; i+=1 {
		curr.right = &Node{ data: rand.Intn(limit), left: curr, right: nil };
		curr = curr.right;
	}
	return head;
}

func printDoublyLinkedList(head *Node) {
	curr := head;
	for curr.right != nil {
		fmt.Printf("%d ", curr.data);
		curr = curr.right;
	}
	fmt.Println(curr.data);
	for curr != nil {
		fmt.Printf("%d ", curr.data);
		curr = curr.left;
	}
	fmt.Println();
}

func reverseDoublyLinkedListIteratively(head *Node) *Node {
	curr := head;
	var prev *Node;
	for curr != nil {
		next := curr.right;
		curr.right = prev;
		if prev != nil {
			prev.left = curr;
		}
		prev = curr;
		curr = next;
	}
	prev.left = nil;
	return prev;
}

func reverseDoublyLinkedListReursively(head *Node) *Node {
	if head.right == nil {
		return head;
	}
	newNode := reverseDoublyLinkedListReursively(head.right);
	head.right.right = head;
	head.left = head.right;
	head.right = nil;
	newNode.left = nil;
	return newNode;
}

func main() {
	rand.Seed(time.Now().UnixNano());
	head := createDoublyLinkedList(10, 100);
	printDoublyLinkedList(head);
	head = reverseDoublyLinkedListIteratively(head);
	printDoublyLinkedList(head);
	head = reverseDoublyLinkedListReursively(head);
	printDoublyLinkedList(head);
}

