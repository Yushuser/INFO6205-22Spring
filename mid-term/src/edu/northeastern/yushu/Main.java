package edu.northeastern.yushu;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] arr = {0, 1, 3, 50, 75};
        System.out.println(Question1(arr, 0, 99));
    }
    // Question 1
    public static List<String> Question1(int[] arr, int lower, int upper) {
        List<String> result = new LinkedList<String>();
        if (arr.length == 0) {
            findRange(result, lower, upper);
            return result;
        }
        if (arr[0] != Integer.MIN_VALUE) {
            findRange(result, lower, arr[0] - 1);
        }
        for (int i = 0; i < arr.length - 1; i ++) {
            if (arr[i] == arr[i+1]) {
                continue;
            }
            findRange(result, arr[i] + 1, arr[i + 1] - 1);
        }
        if (arr[arr.length - 1] != Integer.MAX_VALUE) {
            findRange(result, arr[arr.length - 1] + 1, upper);
        }

        return result;
    }
    private static void findRange(List<String> result, int low, int up) {
        if (low > up){
            return;
        }
        if (low == up) {
            result.add((low) + "");
            return;
        }
        result.add(low + "->" + up);
    }
    // Question 2
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
  public static ListNode Question2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        int count = 0;
        while (l1 != null || l2 != null || count != 0) {
            if (l1 != null) {
                count += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                count += l2.val;
                l2 = l2.next;
            }
            p.next = new ListNode(count % 10);
            count = count / 10;
            p = p.next;
        }
        return dummy.next;
    }
    // Question 3
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public TreeNode Question3(int[] preorder, int[] inorder) {
        Map<Integer, Integer> Map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            Map.put(inorder[i], i);
        }
        return newTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, Map);
    }
    public TreeNode newTree(int[] preorder, int preS, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> Map) {
        if (preS > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preS]);
        int inRoot = Map.get(root.val);
        int numsLeft = inRoot - inStart;
        root.left = newTree(preorder, preS + 1, preS + numsLeft, inorder, inStart, inRoot - 1, Map);
        root.right = newTree(preorder, preS + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, Map);
        return root;
    }
    // Question 4
    public int[][] Question4(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        for (int[] interval: intervals) {
            if (interval[0] <= newInterval[1]) {
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                newInterval = interval;
                result.add(newInterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
