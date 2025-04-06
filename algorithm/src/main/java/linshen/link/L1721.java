package linshen.link;

import sulqn.ListNode;

/*
给你链表的头节点 head 和一个整数 k 。
交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。
 */

public class L1721 {
    /*
    x -> xx -> xxx
    y -> yy -> yyy
    x -> (xx,y) -> (xxx, yy) -> yyy
      (xx,y) <- (xxx, yy) -> yyy
            x ->
     */
    public ListNode swapNodes(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode x = findFk(dummy, k);
        ListNode y = findLk(dummy, k);
        ListNode xx = x.next;
        ListNode yy = y.next;
        ListNode xxx = xx.next;
        ListNode yyy = yy.next;
        if(x.next == y) {
            x.next = yy;
            yy.next = xx;
            xx.next = yyy;
        } else if(y.next == x) {
            y.next = xx;
            xx.next = yy;
            yy.next = xxx;
        } else {
            x.next = yy;
            yy.next = xxx;
            y.next = xx;
            xx.next = yyy;
        }

        return dummy.next;
    }

    /*
    返回前置节点
     */
    public ListNode findFk(ListNode dummy, int k) {
        ListNode p = dummy;
        k--;
        while (k > 0) {
            p = p.next;
            k--;
        }
        return p;
    }

    private ListNode findLk(ListNode dummy, int k) {
        ListNode p = dummy;
        ListNode q = dummy.next;
        while (k-->0){
            q = q.next;
        }
        while (q != null){
            p = p.next;
            q = q.next;
        }
        return p;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(90);
        ListNode b = new ListNode(100);
        a.next = b;
        System.out.println(new L1721().swapNodes(a, 2));
    }
}
