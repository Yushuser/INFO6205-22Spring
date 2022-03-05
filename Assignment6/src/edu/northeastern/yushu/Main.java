package edu.northeastern.yushu;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
    }
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
  // 1.Path Sum III
    int count = 0;
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        helper(root, sum, 0);
        // iterate to each node
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        return count;
    }
    private void helper(TreeNode root, int sum, int currentSum) {
        if (root == null) {
            return;
        }
        currentSum += root.val;
        //  find the number of solutions that starting at the root
        if (currentSum == sum) {
            count++;
        }
        helper(root.left, sum, currentSum);
        helper(root.right, sum, currentSum);
    }
    // 2.Lowest Common Ancestor of a Binary Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        }
        if(root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null) {
            return root;
        }
        if(left == null && right == null) {
            return null;
        }
        if(left != null) {
            return left;
        } else {
            return right;
        }
    }
    // 3.Longest univalue Path
    public int longestUnivaluePath(TreeNode root) {
        if(root == null){
            return 0;
        }
        int[] res = new int[1];
        helper(root,res);
        return res[0];
    }
    private int helper(TreeNode node,int[] res){
        if(node.left == null && node.right == null){
            return 0;
        }
        int curr = 0;
        int vPath = 0;
        int l = 0;
        int r = 0;
        if(node.left != null){
            l = helper(node.left,res);
            if(node.val == node.left.val){
                curr += l + 1;
                vPath = curr;
            }
        }
        if(node.right != null){
            r = helper(node.right,res);
            if(node.val == node.right.val){
                curr = Math.max(curr,r + 1);
                vPath += r + 1;
            }
        }
        res[0] = Math.max(res[0],vPath);
        return curr;
    }
    // 4.Serialize and Deserialize Binary Tree
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    private void buildString(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#").append(",");
        } else {
            sb.append(root.val).append(",");
            buildString(root.left, sb);
            buildString(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[]  str = data.split(",");
        Queue<String> queue = new LinkedList<>();
        Collections.addAll(queue, str);
        return buildTree(queue);
    }
    private TreeNode buildTree(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String s = queue.poll();
        if (s.equals("#")) {
            return null;
        }
        // to match serialize pre-order traversal
        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = buildTree(queue);
        root.right = buildTree(queue);
        return root;
    }
    // 5.Vertical Order Traversal of a Binary Tree
    // 6.Count Complete Tree Nodes
    // 7.Sum Root to Leaf Numbers
    // 8.Delete Leaves With a Given Value
}
