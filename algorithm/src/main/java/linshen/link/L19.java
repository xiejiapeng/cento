package linshen.link;

import sulqn.ListNode;

/*
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */

public class L19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;
        ListNode q = head;
        while (n-- > 0) {
            q = q.next;
        }
        while (q != null) {
            p = p.next;
            q = q.next;
        }
        p.next = p.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(new L19().removeNthFromEnd(new ListNode(1), 1));
    }
}
