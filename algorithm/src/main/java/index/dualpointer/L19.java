package index.dualpointer;

import sulqn.ListNode;

/*
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */

public class L19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode a = dummy;
        ListNode b = head;
        ListNode c = head;
        for (int i = 0; i < n; i++){
            if(c != null)c = c.next;
        }
        while (c != null){
            a = a.next;
            b = b.next;
            c = c.next;
        }
        a.next = b.next;
        return dummy.next;

    }

    public static void main(String[] args) {
        ListNode x = new ListNode(1);
        System.out.println(new L19().removeNthFromEnd(x, 1));
    }
}
