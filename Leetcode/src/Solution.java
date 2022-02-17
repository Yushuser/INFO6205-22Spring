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
    //2046将一个链表按照原本的值的大小排序，不是绝对值。eg：0, 2, -5, 5, -10 排序后：-10, -5, 0, 2, 5
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
    //369.给单链表加一,用一个 非空 单链表来表示一个非负整数，然后将这个整数加一。可以假设这个整数除了 0 本身，没有任何前导的 0。
    //这个整数的各个数位按照 高位在链表头部、低位在链表尾部 的顺序排列。
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        dummy.next = head;
        while (head != null) {
            if (head.val != 9) {
                p = head;
                head = head.next;
            }
        }
            p.val++;
            while (p.next != null) {
                p.next.val = 0;
                p = p.next;
            }
            return dummy.val == 0 ? dummy.next : dummy;
        }
}
