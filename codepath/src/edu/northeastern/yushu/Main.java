package edu.northeastern.yushu;

import java.util.List;
import java.util.Queue;

public class Main {
    
    public static void main (String[] args) {
        System.out.println(addF(6));
    }
    public static int addF(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 2;
        }
        return addF(n - 1) + addF(n - 2);
    }
    public static List merge(List<Integer> l1, List<Integer> l2) {
        for (int index1 = 0, index2 = 0; index2 < l2.size(); index1++) {
            if (index1 == l1.size() || l1.get(index1) > l2.get(index2)) {
                l1.add(index1, l2.get(index2++));
            }
        }
        return l1;
    }
}
