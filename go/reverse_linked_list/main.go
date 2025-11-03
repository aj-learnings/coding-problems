/**
 * Given a Linked List, Reverse it using iteration as well as recursion
 */

package main;

import (
	"fmt"
	"math/rand"
	"time"
)

type Node struct {
	data int;
	next *Node;
}

func reverseLinkedListIteratively(head *Node) *Node {
	curr := head;
	var prev *Node;
	for curr != nil {
		next := curr.next;
		curr.next = prev;
		prev = curr;
		curr = next;
	}
	return prev;
}

func reverseLinkedListReursively(head *Node) *Node {
	if head == nil || head.next == nil {
		return head;
	}
	newNode := reverseLinkedListReursively(head.next);
	head.next.next = head;
	head.next = nil;
	return newNode;
}

func createLinkedList(size int, limit int) *Node {
	head := &Node{ data: rand.Intn(limit), next: nil };
	curr := head;
	for i := 2; i<=size; i++ {
		curr.next = &Node{ data: rand.Intn(limit), next: nil };
		curr = curr.next;
	}
	return head;
}

func printLinkedList(head *Node) {
	curr := head;
	for curr != nil {
		fmt.Printf("%d ", curr.data);
		curr = curr.next;
	}
	fmt.Println();
}

func main() {
	rand.Seed(time.Now().UnixNano());
	head := createLinkedList(10, 100);
	printLinkedList(head);
	head = reverseLinkedListIteratively(head);
	printLinkedList(head);
	head = reverseLinkedListReursively(head);
	printLinkedList(head);

}