package linshen.link;

import sulqn.ListNode;

/*
给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 */

public class L24 {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)return head;
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        ListNode p = head;
        while (p != null && p.next != null){
            ListNode q = p.next;
            ListNode x = p.next.next;
            q.next = null;

            tail.next = q;
            q.next = p;
            tail = p;
            tail.next = null;
            p = x;
        }
        if(p != null) {
            tail.next = p;
        }
        return dummy.next;
    }
}
