package edu.northeastern.yushu;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
    }
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    //1.Merge in between LinkedList
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode left = list1;
        for (int i = 0;i < a - 1; i++) {
            left = left.next;
        }
        ListNode right = left.next;
        for (int i = a; i < b + 1;i++) {
            right = right.next;
        }
        ListNode p = list2;
        while (p.next != null) {
            p = p.next;
        }
        left.next = list2;
        p.next = right;
        return list1;
    }
    //2.Partition List
    public ListNode partition(ListNode head, int x) {
        ListNode before = new ListNode(0);
        ListNode b = before;
        ListNode after = new ListNode(0);
        ListNode a = after;
        while (head != null) {
            if (head.val < x) {
                b.next = head;
                b = b.next;
            } else {
                a.next = head;
                a = a.next;
            }
            head = head.next;
        }
        a.next = null;
        b.next = after.next;
        return before.next;
    }
    //3.Reverse Nodes in even Length Groups
    public ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode p = head;
        ListNode cur = head;
        int count = 0;
        int base = 1;
        ListNode pre = head;
        ListNode start = head;
        ListNode next = head;
        while (cur != null) {
            count++;
            if (count == base) {
                if (count % 2 == 0) {
                    next = cur.next;
                    cur.next = null;
                    start = pre.next;
                    pre.next = reverse(start);
                    start.next = next;
                    cur = start;
                }
                pre = cur;
                base++;
                count = 0;
            }
            cur = cur.next;
        }
        if (count % 2 == 0) {
            pre.next = reverse(pre.next);
        }
        return head;
    }
    public ListNode reverse(ListNode head) {
        ListNode slow = null, fast = head;
        while (fast != null) {
            ListNode p = fast.next;
            fast.next = slow;
            slow = fast;
            fast = p;
        }
        return slow;
    }
    //4.Find the Minimum and Maximum Number of Nodes Between Critical Points
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode p = head;
        ArrayList<Integer> list = new ArrayList<>();
        int index = 1;
        while (p.next.next != null) {
            if ((p.next.val > p.val && p.next.val > p.next.next.val) ||(p.next.val < p.val && p.next.val < p.next.next.val)) {
                list.add(index);
            }
            p = p.next;
            index++;
        }
        int[] cpoint = new int[2];
        if (list.size() < 2) {
            cpoint[0] = -1;
            cpoint[1] = -1;
            return cpoint;
        }
        cpoint[0] = Integer.MAX_VALUE;
        cpoint[1] = list.get(list.size() - 1) - list.get(0);
        for (int i = 0; i < list.size() - 1;i++) {
            cpoint[0] = Math.min(cpoint[0], list.get(i + 1) - list.get(i));
        }
        return cpoint;
    }
    //5.Sort List
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode mid = getMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return merge(left, right);
    }

    ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
                tail = tail.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
                tail = tail.next;
            }
        }
        tail.next = (list1 != null) ? list1 : list2;
        return dummyHead.next;
    }

    ListNode getMid(ListNode head) {
        ListNode midPrev = null;
        while (head != null && head.next != null) {
            midPrev = (midPrev == null) ? head : midPrev.next;
            head = head.next.next;
        }
        ListNode mid = midPrev.next;
        midPrev.next = null;
        return mid;
    }
    //6.Linked List Random Node
    class Solution {
        ListNode head;
        Random random;

        public Solution(ListNode head) {
            this.head = head;
            random = new Random();
        }

        public int getRandom() {
            int count = 1, select = 0;
            ListNode p = this.head;
            while (p != null) {
                if (random.nextInt(count) == 0) {
                    select = p.val;
                }
                count++;
                p = p.next;
            }
            return select;
        }
    }
    //7. Reverse Linked List II
    public ListNode reverseBetween(ListNode head, int m, int n ) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        for (int i = 1; i < m; i++) {
            slow = slow.next;
        }
        ListNode fast = slow.next;
        for (int i = m; i < n;i++) {
            ListNode p = fast.next;
            fast.next = p.next;
            p.next = slow.next;
            slow.next = p;
        }
        return dummy.next;
    }
    //8.Split Linked List in Parts
    public ListNode[] splitListToParts(ListNode head, int k) {
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }
        int q = len / k;
        int r = len % k;
        ListNode[] parts = new ListNode[k];
        ListNode c = head;
        for (int i = 0;i < k && c != null; i++) {
            parts[i] = c;
            int size = q + (i < r ? 1 : 0);
            for (int j = 1; j < size;j++) {
                c = c.next;
            }
            ListNode next = c.next;
            c.next = null;
            c = next;
        }
        return parts;
    }
    //9.Linked List Components
    public int numComponents(ListNode head, int[] nums) {
        boolean[] has = new boolean[10001];
        for(int i : nums){
            has[i] = true;
        }
        int count = 0;
        while(head != null){
            if(has[head.val]){
                while(head.next != null && has[head.val]){
                    head = head.next;
                }
                count++;
            }
            head = head.next;
        }
        return count;
    }
    //10.Maximum Twin Sum of a Linked List
    public int pairSum(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode middle = slow.next;
        slow.next = null;
        ListNode p = middle;
        ListNode c = null;
        while (p != null) {
            ListNode next = p.next;
            p.next = c;
            c = p;
            p = next;
        }
        int sum = 0;
        while (head != null) {
            sum = Math.max(c.val + head.val, sum);
            head = head.next;
            c = c.next;
        }
        return sum;
    }
}
