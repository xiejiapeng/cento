package linshen.link;

import sulqn.ListNode;

/*
给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */

public class L2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1;
        ListNode q = l2;
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        int rdx = 0;
        while (p != null || q != null) {
            if(p == null) {
                int t = q.val + rdx;
                ListNode x = new ListNode(t % 10);
                rdx = t / 10;
                tail.next = x;
                tail = x;
                q = q.next;
            } else if(q == null) {
                int t = p.val + rdx;
                ListNode x = new ListNode(t % 10);
                rdx = t / 10;
                tail.next = x;
                tail = x;
                p = p.next;
            } else {
                int t = p.val + q.val + rdx;
                ListNode x = new ListNode(t % 10);
                rdx = t / 10;
                tail.next = x;
                tail = x;
                p = p.next;
                q = q.next;
            }

        }
        if(rdx > 0) {
            ListNode x = new ListNode(rdx);
            tail.next = x;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        if(head == null || head.next == null)return head;
        else {
            ListNode x = head.next;
            head.next = null;
            ListNode p = reverse(x);
            x.next = head;
            return p;
        }
    }
}
