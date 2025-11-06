package linshen.link;

import sulqn.ListNode;

/*
给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

链表中的节点数目为 n
1 <= k <= n <= 5000
0 <= Node.val <= 1000
 */

public class L25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tail = new ListNode(-1);
        ListNode ans = head;
        boolean first = true;
        tail.next = head;
        // tail -> p -> ...
        while (true) {
            ListNode p = tail.next;
            int x = 0;
            ListNode tt = null;
            while (p != null) {
                x++;
                tt = p;
                p = p.next;
                if(x >= k)break;
            }
            if(x < k)break;
            else {
                //todo 不要提前定义太多变量，可以后面补，避免脑子弄晕
                /*
                    tail -> p -> ... -> tt -> ...
                    tail -> tt -> ... -> p -> ...
                 */
                p = tail.next;
                ListNode y = p;
                ListNode q = p.next;
                p.next = null;
                while (p != tt) {
                    ListNode tm = q.next;
                    q.next = p;
                    p = q;
                    q = tm;
                }
                y.next = q;
                tail.next = tt;
                tail = y;
                if(first){
                    ans = tt;
                    first = false;
                }
            }
        }
        return ans;

    }
}
