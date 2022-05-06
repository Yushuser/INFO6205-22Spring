package edu.northeastern.yushu;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Question1:
        System.out.println("==================== Question 1 ====================");
        System.out.println(compress("abc"));
        System.out.println(compress("aabccddddd"));
        // Question2:
        System.out.println("==================== Question 2 ====================");
        char[][] grid1 = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        System.out.println(numIslands(grid1));

        char[][] grid2 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println(numIslands(grid2));
        // Question3:
        System.out.println("==================== Question 3 ====================");
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(groupAnagrams(new String[]{""}));
        System.out.println(groupAnagrams(new String[]{"a"}));
        // Question4:
        System.out.println("==================== Question 4 ====================");
        TreeNode root1 = new TreeNode(6);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(4);
        System.out.println(lowestCommonAncestor(root1, root1.left, root1.right).val);
        System.out.println(lowestCommonAncestor(root1, root1.left, root1.left.right).val);

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        System.out.println(lowestCommonAncestor(root2, root2.left, root2).val);
    }
    // Question1:
    public static String compress(String str) {
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
    public static int numIslands(char[][] grid) {
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
    public static void dfs(char[][] grid, int r, int c) {
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
        public static List<List<String>> groupAnagrams(String[] strs) {
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
    public static class TreeNode {
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
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
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
