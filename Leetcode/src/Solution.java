public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    //2046
    public ListNode sortLinkedList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null)
            if (fast.val < 0) {
                slow.next = fast.next;
                fast.next = head;
                head = fast;
                fast = slow.next;
            } else {
                slow = fast;
                fast = fast.next;
            }

        return head;
    }

}
