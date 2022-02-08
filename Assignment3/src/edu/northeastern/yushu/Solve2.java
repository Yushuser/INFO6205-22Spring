package edu.northeastern.yushu;

import java.util.HashMap;
import java.util.Map;

public class Solve2 {
    // Recursion Algorithm, use a map to record the node that we have already seen to avoid duplication
    public static Main.Node copyRandomList(Main.Node head) {
        Map<Main.Node, Main.Node> map = new HashMap<>();
        // base case
        if (head == null) {
            return null;
        }
        if (map.containsKey(head)) {
            return map.get(head);
        }
        // else : make a new copy of the current node
        Main.Node node = new Main.Node(head.val);
        map.put(head, node);
        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);
        return node;
    }
}
