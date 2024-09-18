package sulwish;

import sulqn.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class L23 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });
        for (ListNode head : lists) {
            if(head != null){
                queue.add(head);
            }

        }

        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        while (!queue.isEmpty()) {
            ListNode x = queue.poll();
            tail.next = x;
            if(x.next != null){
                queue.add(x.next);
            }
            tail = x;
        }

        return dummy.next;


    }
}
