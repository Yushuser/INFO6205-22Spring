package edu.northeastern.yushu;

public class Solve1 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
        public static ListNode addTwoNumbers(Main.ListNode l1, Main.ListNode l2) {
            Main.ListNode head = new Main.ListNode();
            Main.ListNode p = head;
            int reminder = 0;
            while (l1 != null || l2 != null || reminder != 0) {
                if (l1 != null) {
                    reminder += l1.val;
                    l1 = l1.next;
                }
                if (l2 != null) {
                    reminder += l2.val;
                    l2 = l2.next;
                }
                p.next = new Main.ListNode(reminder % 10);
                p = p.next;
                reminder = reminder / 10;
            }
            return head.next;
        }
    }
