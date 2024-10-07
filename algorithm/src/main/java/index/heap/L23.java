package index.heap;

import sulqn.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class L23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        ListNode dummy = new ListNode(-1);
        ListNode next = dummy;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode a : lists) {
            if (a != null) {
                queue.add(a);
            }

        }

        while (!queue.isEmpty()) {
            ListNode x = queue.poll();
            next.next = x;
            next = x;
            if (x.next != null) queue.add(x.next);
        }
        return dummy.next;
    }
}
