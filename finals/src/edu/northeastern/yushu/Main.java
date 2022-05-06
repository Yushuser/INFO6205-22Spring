package edu.northeastern.yushu;

import java.util.*;

public class Main {

    public static void main(String[] args) {
    }
    // Question1:
    public String compress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuffer sb = new StringBuffer();
        int count = 1;
        char current = '1';
        for (int i = 0; i < str.length() - 1; i++) {
            current = str.charAt(i);
            if (str.charAt(i) == str.charAt(i + 1)) {
                count +=1;
            } else {
                sb.append(current);
                sb.append(count);
                count = 1;
            }
        }
        sb.append(current);
        sb.append(count);
        if (sb.length() >= str.length()) {
            return str;
        }
        return sb.toString();
    }
    // Question2:
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length, island = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    island++;
                    dfs(grid, i, j);
                }
            }
        }
        return island;
    }
    public void dfs(char[][] grid, int r, int c) {
        int row = grid.length, col = grid[0].length;
        if (r < 0 || c < 0 || r >= row || c >= col || grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
    // Question3:
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<String, List<String>>();
            for (String str : strs) {
                char[] array = str.toCharArray();
                Arrays.sort(array);
                String key = new String(array);
                List<String> list = map.getOrDefault(key, new ArrayList<String>());
                list.add(str);
                map.put(key, list);
            }
            return new ArrayList<List<String>>(map.values());
        }
    // Question4:
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        if (root.val == p.val || root.val == q.val)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null)
            return root;

        if (left == null && right == null)
            return null;

        if (left != null)
            return left;
        else
            return right;
    }
}
