package linshen.structure.heap;

/*
给你一个链表数组，每个链表都已经按升序排列。
请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */

import sulqn.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class L23 {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });
        for (ListNode l : lists) {
            if(l != null)queue.add(l);
        }
        while (!queue.isEmpty()) {
            ListNode x = queue.poll();
            tail.next = x;
            tail = x;
            if(x.next != null)queue.add(x.next);
            tail.next = null;
        }
        return dummy.next;
    }
}
