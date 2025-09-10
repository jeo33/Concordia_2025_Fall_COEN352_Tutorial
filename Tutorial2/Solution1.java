class ListNode {
    int val;
    ListNode next;
    
    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }
        
        return dummy.next;
    }
}

public class Solution1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Creating two sorted linked lists
        ListNode list1 = new ListNode(1, new ListNode(3, new ListNode(5, new ListNode(7))));
        ListNode list2 = new ListNode(2, new ListNode(4, new ListNode(6, new ListNode(8))));
        
        // Merging the lists
        ListNode mergedList = solution.mergeTwoLists(list1, list2);
        
        // Printing the merged list
        System.out.print("Merged List: ");
        while (mergedList != null) {
            System.out.print(mergedList.val + " ");
            mergedList = mergedList.next;
        }
    }
}


