package linshen.link;

import sulqn.ListNode;

/*
给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 */

public class L61 {
    public ListNode rotateRight(ListNode head, int k) {
        int len = 0;
        ListNode p = head;
        ListNode tail = null;
        while (p != null) {
            len++;
            tail = p;
            p = p.next;
        }
        k %= len;
        if(k == 0)return head;
        else {
            int l = len - k;
            p = head;
            l--;
            while (l > 0) {
                p = p.next;
                l--;
            }
            tail.next = head;
            ListNode ans = p.next;
            p.next = null;
            return ans;

        }
    }
}
