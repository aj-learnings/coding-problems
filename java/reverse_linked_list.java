/**
 * Given a Linked List, Reverse it using iteration as well as recursion
 */

import java.util.Random;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}


class ReverseLinkedList {
    public static void main(String[] args) {
        Node head = createLinkedList(10, 100);
        printLinkedList(head);
        head = reverseLinkedListIteratively(head);
        printLinkedList(head);
        head = reverseLinkedListRecursively(head);
        printLinkedList(head);
    }

    private static Node createLinkedList(int size, int range) {
        Random rand = new Random();
        Node head = new Node(rand.nextInt(range));
        Node curr = head;
        for(int i=1; i<size; i++) {
            curr.next = new Node(rand.nextInt(range));
            curr = curr.next;
        }
        return head;
    }

    private static void printLinkedList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    private static Node reverseLinkedListIteratively(Node head) {
        Node curr = head, prev = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    private static Node reverseLinkedListRecursively(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node newNode = reverseLinkedListRecursively(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }
}
