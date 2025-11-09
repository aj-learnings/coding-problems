/**
 * Given a Doubly Linked List, Reverse it using iteration as well as recursion
 */

import java.util.Random;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

class ReverseDoublyLinkedList {
    public static void main(String[] args) {
        Node head = createDoublyLinkedList(10, 100);
        printDoublyLinkedList(head);
        head = reverseDoublyLinkedListIteratively(head);
        printDoublyLinkedList(head);
        head = reverseDoublyLinkedListRecursively(head);
        printDoublyLinkedList(head);
    }

    private static Node createDoublyLinkedList(int size, int limit) {
        Random rand = new Random();
        Node head = new Node(rand.nextInt(limit));
        Node curr = head;
        for (int i=1; i<size; i++) {
            curr.right = new Node(rand.nextInt(limit), curr, null);
            curr = curr.right;
        }
        return head;
    }

    private static void printDoublyLinkedList(Node head) {
        Node curr = head;
        while (curr.right != null) {
            System.out.print(curr.data + " ");
            curr = curr.right;
        }
        System.out.println(curr.data);
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.left;
        }
        System.out.println();
    }

    private static Node reverseDoublyLinkedListIteratively(Node head) {
        Node curr = head, prev = null;
        while (curr != null) {
            var next = curr.right;
            curr.right = prev;
            if (prev != null) {
                prev.left = curr;
            }
            prev = curr;
            curr = next;
        }
        prev.left = null;
        return prev;
    }

    private static Node reverseDoublyLinkedListRecursively(Node head) {
        if (head == null || head.right == null) {
            return head;
        }
        var newNode = reverseDoublyLinkedListRecursively(head.right);
        head.right.right = head;
        head.left = head.right;
        head.right = null;
        newNode.left = null;
        return newNode;
    }
}