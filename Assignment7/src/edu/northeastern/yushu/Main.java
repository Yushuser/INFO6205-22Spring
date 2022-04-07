package edu.northeastern.yushu;

import java.util.*;

public class Main {

    public static void main(String[] args) {
    }
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    // 1. Clone Graph
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    public Node dfs(Node node, Map<Node, Node> map) {

        if (map.containsKey(node)) {
            return map.get(node);
        }

        // create one new node
        Node clonenode = new Node(node.val, new ArrayList<Node>());
        map.put(node, clonenode);

        // copy relationship -> spread
        for (Node neighbor : node.neighbors) {
            clonenode.neighbors.add(dfs(neighbor, map));
        }

        return clonenode;
    }
    // 2. Shortest Path Visiting All Nodes
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        Queue<int[]> queue = new LinkedList<int[]>();
        boolean[][] seen = new boolean[n][1 << n];
        for (int i = 0; i < n; ++i) {
            queue.offer(new int[]{i, 1 << i, 0});
            seen[i][1 << i] = true;
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();
            int u = tuple[0], mask = tuple[1], dist = tuple[2];
            if (mask == (1 << n) - 1) {
                ans = dist;
                break;
            }
            // Search for adjacent nodes
            for (int v : graph[u]) {
                // 将 mask 的第 v 位置为 1
                int maskV = mask | (1 << v);
                if (!seen[v][maskV]) {
                    queue.offer(new int[]{v, maskV, dist + 1});
                    seen[v][maskV] = true;
                }
            }
        }
        return ans;
    }
    // 3. Maximum Path Quality of a Graph
    int max;
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        Map<Integer, Map<Integer, Integer>> g = new HashMap<>();
        for (int[] edge : edges) {
            g.putIfAbsent(edge[0], new HashMap<>());
            g.putIfAbsent(edge[1], new HashMap<>());
            g.get(edge[0]).put(edge[1], edge[2]);
            g.get(edge[1]).put(edge[0], edge[2]);
        }

        int[] visited = new int[values.length];
        dfs(0, 0, values, g, visited, maxTime);
        return max;
    }

    void dfs(int cur, int value, int[] values, Map<Integer, Map<Integer, Integer>> g, int[] visited, int time) {
        visited[cur]++;
        if (visited[cur] <= 1) value += values[cur];
        if (cur == 0) max = Math.max(value, max);

        if (g.containsKey(cur)) {
            for (Map.Entry<Integer, Integer> pair : g.get(cur).entrySet()) {
                int cost = pair.getValue();
                int next = pair.getKey();
                if (cost > time) continue;
                dfs(next, value, values, g, visited, time - cost);
            }
        }

        visited[cur]--;
        return;
    }
}
