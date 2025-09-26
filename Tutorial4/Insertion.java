package Tutorial4;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class Insertion {

    public static ListNode sortList(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        ListNode curr = head;
        while (curr != null) {
            ListNode prev = dummy;
            ListNode nextNode = curr.next;
            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;
            }
            curr.next = prev.next;
            prev.next = curr;
            curr = nextNode;
            printList(head);
        }
        return dummy.next;
    }

    // Helper to print list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    // Example usage
    public static void main(String[] args) {
        // Input: [-1, 5, 3, 4, 0]
        ListNode head = new ListNode(-1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(0);

        System.out.println("Original List:");
        printList(head);

        ListNode sortedHead = sortList(head);

        System.out.println("Sorted List (Insertion Sort):");
        printList(sortedHead);
    }
}
