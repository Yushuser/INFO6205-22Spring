package edu.northeastern.yushu;
import java.util.*;
import static edu.northeastern.yushu.Main.ListNodes.mergeKLists;

public class Main {

    public static void main(String[] args) {
//  1.Add Two Numbers
        Solve1.ListNode l1 = new Solve1.ListNode(2, new ListNode(4, new Solve1.ListNode(3)) );
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)) );
        Solve1 s1 = new Solve1();
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
        Node res=Solve2.copyRandomList(h);
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
