package linshen.link;

import sulqn.ListNode;

public class L206 {
    public ListNode reverseList(ListNode head) {
        if(head == null)return null;
        ListNode tail = head;
        ListNode cur = head.next;
        tail.next = null;
        while (cur != null) {
            ListNode x = cur.next;
            cur.next = tail;
            tail = cur;
            cur = x;
        }
        return tail;
    }
}
