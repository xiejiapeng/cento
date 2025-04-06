package linshen.link;

import sulqn.ListNode;

/*
给定一个已排序的链表的头 head ，
删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 */

public class L82 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(1);
        dummy.next = head;
        ListNode p = dummy;
        ListNode q = head;
        while (q != null) {
            if(q.next != null && q.val == q.next.val) {
                int v = q.val;
                while (q != null && q.val == v) {
                    q = q.next;
                }
            } else {
                //todo 防止里面，用多一轮循环
                p.next = q;
                p = q;
                q = q.next;
            }

        }
        //todo 注意，收尾才算删除
        p.next = q;
        return dummy.next;
    }
}
