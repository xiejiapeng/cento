package index.hashtable;

import sulqn.ListNode;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/*
给定链表头结点 head，该链表上的每个结点都有一个 唯一的整型值 。同时给定列表 nums，该列表是上述链表中整型值的一个子集。

返回列表 nums 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 nums 中）构成的集合。
 */

public class L817 {
    public int numComponents(ListNode head, int[] nums) {
        int ans = 0;
        Set<Integer> ns = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        ListNode x = null;
        ListNode y = head;
        while (y != null) {
            if (ns.contains(y.val)) {
                if (x == null) x = y;
            } else {
                if (x != null) ans++;
                x = null;
            }
            y = y.next;
        }
        if (x != null) ans++;
        return ans;
    }
}
