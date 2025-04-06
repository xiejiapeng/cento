package linshen.link;

import sulqn.ListNode;

/*
给你单链表的头指针 head 和两个整数 left 和 right ，
其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
1a 2p 3 4q 5tail
1 5 4 3 2
1 5 2 3 4
1 4 3 2 5
 */

public class L92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        int id = 0;
        ListNode prev = dummy;
        ListNode a = null;
        ListNode p = null;
        ListNode q = null;
        while (prev != null) {
            if(id == left-1){
                a = prev;
                p=prev.next;
            }
            if(id == right-1){
                q = prev.next;
            }
            prev = prev.next;
            id++;
        }

        ListNode x = q.next;
        if(x == null) {
            a.next = reverseList(p);
        } else {
            ListNode tail = reverseList(p);
            x.next = reverseList(q);
            a.next = reverseList(tail);
        }


        return dummy.next;
    }

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

    public static void main(String[] args) {
        ListNode a = new ListNode(3);
        ListNode b = new ListNode(5);
        a.next = b;
        ListNode x = new L92().reverseBetween(a,1,2);
        System.out.println(x);
    }
}
