package edu.northeastern.yushu;
import java.util.*;

import static edu.northeastern.yushu.Main.ListNodes.mergeKLists;

public class Main {

    public static void main(String[] args) {
//  1.Add Two Numbers
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)) );
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)) );
        Solve s1 = new Solve();
        ListNode result = s1.addTwoNumbers(l1, l2);
        System.out.print("1.Add Two Numbers: ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println();
//  2. Copy List with Random Pointer
        Node h=new Node(1);
        int[] arr=new int[2];
        Node res=copyRandomList(h);
        System.out.println("2.Copy List with Random Pointer : "+res.val);
//  3. Merge k Sorted Lists
        ListNodes[] lists=new ListNodes[3];
        lists[0]=new ListNodes(1,new ListNodes(3, new ListNodes(4)));
        lists[1]=new ListNodes(1,new ListNodes(4, new ListNodes(5)));
        lists[2]=new ListNodes(2,new ListNodes(6));
        ListNodes re= mergeKLists(lists);
        System.out.println(mergeKLists(lists).val);
//  4. Reorder List
//  5. Palindrome Linked List
//  6. Remove Nth Node From End of List
//  7. Odd Even Linked List
//  8. Insert into a Sorted Circular Linked List
//  9. Next Greater Node In Linked List
//  10.Remove Duplicates from Sorted List II
    }

    // 1.Add Two Numbers
    static class ListNode {
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
    static class Solve {
        public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = new ListNode();
            ListNode p = head;
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
                p.next = new ListNode(reminder % 10);
                p = p.next;
                reminder = reminder / 10;
            }
            return head.next;
        }
    }
    //  2. Copy List with Random Pointer
    static class Node {
        int val;
        Node next;
        Node random;
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    // Recursion Algorithm, use a map to record the node that we have already seen to avoid duplication
    public static Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        // base case
        if (head == null) {
            return null;
        }
        if (map.containsKey(head)) {
            return map.get(head);
        }
        // else : make a new copy of the current node
        Node node = new Node(head.val);
        map.put(head, node);
        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);
        return node;
    }
    //3. Merge k Sorted Lists
    static class ListNodes {
        int val;
        ListNodes next;
        ListNodes() {
        }

        ListNodes(int val) {
            this.val = val;
        }

        ListNodes(int val, ListNodes next) {
            this.val = val;
            this.next = next;
        }
        public static ListNodes mergeKLists(ListNodes[] lists) {
            if (lists == null || lists.length == 0) return null;
            PriorityQueue<ListNodes> queue = new PriorityQueue<>(lists.length, new Comparator<ListNodes>() {
                @Override
                public int compare(ListNodes o1, ListNodes o2) {
                    if (o1.val < o2.val) return -1;
                    else if (o1.val == o2.val) return 0;
                    else return 1;
                }
            });
            ListNodes dummy = new ListNodes(0);
            ListNodes p = dummy;
            for (ListNodes node : lists) {
                if (node != null) queue.add(node);
            }
            while (!queue.isEmpty()) {
                p.next = queue.poll();
                p = p.next;
                if (p.next != null) queue.add(p.next);
            }
            return dummy.next;
        }
    }
}
