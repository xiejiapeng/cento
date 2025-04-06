package linshen.link;

import sulqn.ListNode;

/*
链表中的 临界点 定义为一个 局部极大值点 或 局部极小值点 。
如果当前节点的值 严格大于 前一个节点和后一个节点，那么这个节点就是一个 局部极大值点 。
如果当前节点的值 严格小于 前一个节点和后一个节点，那么这个节点就是一个 局部极小值点 。
注意：节点只有在同时存在前一个节点和后一个节点的情况下，才能成为一个 局部极大值点 / 极小值点 。
给你一个链表 head ，返回一个长度为 2 的数组 [minDistance, maxDistance] ，
其中 minDistance 是任意两个不同临界点之间的最小距离，maxDistance 是任意两个不同临界点之间的最大距离。
如果临界点少于两个，则返回 [-1，-1] 。
 */

public class L2058 {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1;
        int last = -1;
        int min = Integer.MAX_VALUE;
        int id = 1;
        ListNode l = head;
        if(head == null || head.next == null)return new int[]{-1,-1};
        ListNode p = head.next;
        while (p != null) {
            if(marginal(l,p)) {
                if(first == -1)first = id;
                else {
                    min = Math.min(min, id - last);
                }
                last = id;
            }
            p = p.next;
            l = l.next;
            id++;
        }
        if(first == -1 || first == last) {
            return new int[]{-1,-1};
        } else {
            return new int[]{min, last - first};
        }
    }

    private boolean marginal(ListNode last, ListNode cur) {
        if(cur.next == null)return false;
        if(cur.val > last.val && cur.val > cur.next.val)return true;
        return cur.val < last.val && cur.val < cur.next.val;
    }
}
