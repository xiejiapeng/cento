package linshen.link;

import sulqn.ListNode;

/*
给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，
使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
你应当 保留 两个分区中每个节点的初始相对位置。
 */

public class L86 {
    public ListNode partition(ListNode head, int x) {
        ListNode lh = new ListNode(-1);
        ListNode lt = lh;
        ListNode rh = new ListNode(-1);
        ListNode rt = rh;
        ListNode p = head;
        while (p != null) {
            ListNode q = p.next;
            p.next = null;
            if(p.val < x) {
                lt.next = p;
                lt = p;
            } else {
                rt.next = p;
                rt = p;
            }
            p = q;
        }
        lt.next = rh.next;
        return lh.next;
    }
}
