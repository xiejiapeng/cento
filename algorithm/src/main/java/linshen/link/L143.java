package linshen.link;

import sulqn.ListNode;

/*
给定一个单链表 L 的头节点 head ，单链表 L 表示为：
L0 → L1 → … → Ln - 1 → Ln
请将其重新排列后变为：
L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */

public class L143 {
    public void reorderList(ListNode head) {
        ListNode mid = find(head);
        ListNode tail = reverse(mid);
        ListNode p = head;
        ListNode q = tail;
        while (p != q && p.next != q) {
            ListNode pp = p.next;
            ListNode qq = q.next;
            p.next = q;
            q.next = pp;
            p = pp;
            q = qq;

        }
    }

    private ListNode find(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        if(head == null || head.next == null)return head;
        ListNode x = head.next;
        ListNode y = reverse(x);
        x.next = head;
        head.next = null;
        return y;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;
        new L143().reorderList(a);
    }
}
