package edu.northeastern.yushu;

import java.util.*;

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
    Map<TreeNode, int[]> map = new HashMap<>(); // col, row, val
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        map.put(root, new int[]{0, 0, root.val});
        dfs(root);
        List<int[]> list = new ArrayList<>(map.values());
        Collections.sort(list, (a, b)->{
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });
        int n = list.size();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; ) {
            int j = i;
            List<Integer> tmp = new ArrayList<>();
            while (j < n && list.get(j)[0] == list.get(i)[0]) tmp.add(list.get(j++)[2]);
            ans.add(tmp);
            i = j;
        }
        return ans;
    }
    void dfs(TreeNode root) {
        if (root == null) return ;
        int[] info = map.get(root);
        int col = info[0], row = info[1], val = info[2];
        if (root.left != null) {
            map.put(root.left, new int[]{col - 1, row + 1, root.left.val});
            dfs(root.left);
        }
        if (root.right != null) {
            map.put(root.right, new int[]{col + 1, row + 1, root.right.val});
            dfs(root.right);
        }
    }
    // 6.Count Complete Tree Nodes
    public int countNodes(TreeNode root) {
        // exit
        if (root == null) {
            return 0;
        }
        // divide
        int l = countNodes(root.left);
        int r = countNodes(root.right);
        // merge
        return l + r + 1;
    }
    // 7.Sum Root to Leaf Numbers
    public int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }

    public int helper(TreeNode root, int i){
        if (root == null) {
            return 0;
        }
        int p = i * 10 + root.val;
        if (root.left == null && root.right == null) {
            return p;
        }
        return helper(root.left, p) + helper(root.right, p);
    }
    // 8.Delete Leaves With a Given Value
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if(root == null) {
            return null;
        }

        if(root.left == null && root.right == null && root.val == target) {
            return null;
        }

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        if(root.left == null && root.right == null && root.val == target) {
            root = null;
        }

        return root;
    }
}
