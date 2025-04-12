package linshen.link;

import sulqn.ListNode;

/*
给你一个链表的头节点 head ，该链表包含由 0 分隔开的一连串整数。链表的 开端 和 末尾
的节点都满足 Node.val == 0 。
对于每两个相邻的 0 ，请你将它们之间的所有节点合并成一个节点，其值是所有已合并节点的值之和。
然后将所有 0 移除，修改后的链表不应该含有任何 0 。

返回修改后链表的头节点 head 。
 */

public class L2181 {
    public ListNode mergeNodes(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        int cur = 0;
        boolean see = false;
        ListNode p = head;
        while (p != null) {
            if(p.val != 0) {
                cur += p.val;
                see = true;
            } else {
                if(see) {
                    ListNode x = new ListNode(cur);
                    tail.next = x;
                    tail = x;
                    cur = 0;
                }

            }
            p = p.next;
        }
        return dummy.next;
    }
}
