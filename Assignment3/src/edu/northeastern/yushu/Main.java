package edu.northeastern.yushu;
import java.util.*;

public class Main {
    public static void main(String[] args) {
    }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    // 1.Add Two Numbers
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode p = head;
        int reminder = 0;
        while (l1 != null || l2 != null || reminder != 0){
            if (l1 != null) {
                reminder += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                reminder += l2.val;
                l2 = l2.next;
            }
            p.next = new ListNode(reminder%10);
            p = p.next;
            reminder = reminder/10;
        }
        return head.next;
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
        Node() {
        }
        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
    Map<Node, Node> map = new HashMap<>();
    private Node copyRandomList(Node head) {
      // base case
      if (head == null) {
          return null;
      }
      if (this.map.containsKey(head)) {
          return this.map.get(head);
      }
      // else : make a new copy of the current node
      Node node = new Node(head.val);
      this.map.put(head, node);
      node.next = copyRandomList(head.next);
      node.random = copyRandomList(head.random);
      return node;
  }
    //3. Merge k Sorted Lists
      private ListNode mergeKLists(ListNode[] lists) {
          if (lists == null || lists.length == 0) {
              return null;
          }
          PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
              @Override
              public int compare(ListNode o1, ListNode o2) {
                  if (o1.val < o2.val) return -1;
                  else if (o1.val == o2.val) return 0;
                  else return 1;
              }
          });
          ListNode dummy = new ListNode(0);
          ListNode p = dummy;
          for (ListNode node : lists) {
              if (node != null) queue.add(node);
          }
          while (!queue.isEmpty()) {
              p.next = queue.poll();
              p = p.next;
              if (p.next != null) queue.add(p.next);
          }
          return dummy.next;
    }
    //4.Reorder List
    private void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        //Save to list
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            //an even number of nodes will meet earlier
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }
    //5.Palindrome Linked List
    private boolean isPalindrome(ListNode head) {
        if(head==null||head.next==null){
            return true;
        }
        ListNode slow=head;
        ListNode fast=head;
        while(fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode firstHalf=slow;
        ListNode secondHalf=reverse(firstHalf.next);
        ListNode p1=head;
        ListNode p2=secondHalf;
        while(p2!=null){
            if(p1.val!=p2.val){
                return false;
            }
            p1=p1.next;
            p2=p2.next;
        }
        firstHalf.next=reverse(secondHalf);
        return true;
    }
    private ListNode reverse(ListNode head){
        ListNode c=head;
        ListNode p=null;
        ListNode temp;
        while(c!=null){
            temp=c.next;
            c.next=p;
            p=c;
            c=temp;
        }
        return p;
    }
    //6.Remove Nth Node From End of List
    private ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow =dummy;
        for (int i = 1; i<=n+1;i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
    //7.Odd Even Linked List
    private ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        ListNode oddT = head;//Tail of odd
        ListNode evenH = oddT.next;//Head of even
        ListNode evenT = evenH;//Tail of even
        ListNode p = evenH.next;
        while(evenT != null && p != null) {
            ListNode next = p.next;
            oddT.next = p;
            oddT = p;
            oddT.next = evenH;
            evenT.next = next;
            evenT = evenT.next;
            if(evenT != null){
                p = evenT.next;
            }
        }
        return head;
    }
    //8. Insert into a Sorted Circular Linked List
    private Node insert(Node head, int insertVal) {
        if (head == null) {
            Node dummy = new Node(insertVal);
            dummy.next = dummy;
            return dummy;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast != head) {
            // case 1: min <= insertVal <= max
            // case 2: insertVal >= max or insertVal <= min
            if ((slow.val <= insertVal && insertVal <= fast.val) ||
                    (slow.val > fast.val && // slow is max, fast is min
                            (insertVal >= slow.val || insertVal <= fast.val))) {
                // insert the node between fast and slow
                slow.next = new Node(insertVal, fast);
                return head;
            }
            slow = slow.next;
            fast = fast.next;
        }
        // all vals in list are identical
        slow.next = new Node(insertVal, fast);
        return head;
    }
    //9. Next Greater Node In Linked List
    private int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        //Storage linked list
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] res = new int[list.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            int j = i + 1;
            int num = 0;
            if (j < res.length) {
                num = list.get(j);
            }
            while (j < res.length) {
                if (num > list.get(i)) {
                    //If found, stop the while loop
                    res[i] = num;
                    break;
                } else if (num == 0) {
                    break;
                } else {
                    num = res[j++];
                }
            }
        }
        return res;
    }
    //10. Remove Duplicates from Sorted List II
    private ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode p = dummy;
        while (head != null) {
            // When entering the loop, ensures that the head is not the same as the previous node
            if (head.next == null || head.val != head.next.val) {
                p.next = head;
                p = head;
            }
            // If head is the same as the next node, skip the same node
            while (head.next != null && head.val == head.next.val) head = head.next;
            head = head.next;
        }
        p.next = null;
        return dummy.next;
    }
}
