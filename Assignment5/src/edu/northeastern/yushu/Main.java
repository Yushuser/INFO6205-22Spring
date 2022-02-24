package edu.northeastern.yushu;

import java.util.ArrayList;
import java.util.List;

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
  // 1. Binary Tree Level Order Traversal II
  public List<List<Integer>> levelOrderBottom(TreeNode root) {
      List<List<Integer>> result = new ArrayList<>();
      Helper(result, root, 0);
      return result;
  }
    public void Helper(List<List<Integer>> result, TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (depth == result.size()) {
            result.add(0, new ArrayList<>());
        }
        Helper(result, root.left, depth + 1);
        Helper(result, root.right, depth + 1);
        result.get(result.size() - 1 - depth).add(root.val);
    }
  // 2. Find Leaves of Binary Tree
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        depth(result, root);
        return result;
    }
    // depth of root (0-indexed)
    private int depth(List<List<Integer>> result, TreeNode root) {
        if (root == null)
            return -1;
        int l = depth(result, root.left);
        int r = depth(result, root.right);
        int h = 1 + Math.max(l, r);
        if (result.size() == h) // meet leaf
            result.add(new ArrayList<>());

        result.get(h).add(root.val);
        return h;
    }
    // 3. Maximum Width of Binary Tree
    int result;
    public int widthOfBinaryTree(TreeNode root) {
        // Store the leftmost node of each level of the binary tree
        List<Integer> left;
        left = new ArrayList();
        dfs(left, root, 0, 0);
        return result;
    }
    public void dfs(List<Integer> left, TreeNode root, int depth, int pos) {
        // exit
        if (root == null){
            return;
        }
        if(left.size() - 1 < depth){
            left.add(pos);
        }
        // Get maximum width
        result = Math.max(result, pos - left.get(depth) + 1);
        dfs(left, root.left, depth + 1, 2 * pos);
        dfs(left, root.right, depth + 1, 2 * pos + 1);
    }
    // 4. Find Largest Value in Each Tree Row
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(list, root,1);
        return list;
    }
    private void dfs(List<Integer> list, TreeNode node,int depth){
        if(node == null){
            return;
        }
        if(depth > list.size()){
            list.add(node.val);
        }else if(depth <= list.size()){
            int max = list.get(depth-1);
            max = node.val > max ? node.val : max;
            list.set(depth-1,max);
        }
        dfs(list, node.left,depth+1);
        dfs(list, node.right,depth+1);
    }
}
