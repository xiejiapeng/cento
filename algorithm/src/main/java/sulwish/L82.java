package sulwish;

import sulqn.ListNode;

/*
给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，
只留下不同的数字 。返回 已排序的链表 。

1 2 2 3 3 4
1 4
 */

public class L82 {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        } else {
             ListNode dummy = new ListNode(-300);
             dummy.next = null;

             ListNode a = dummy;
             ListNode b = head;
             while (b != null){
                 boolean add = true;
                 ListNode bb = b.next;
                 while (bb != null){
                     if(bb.val != b.val) {
                         break;
                     } else {
                         bb = bb.next;
                         add = false;
                     }
                 }
                 if(add){
                     a.next = b;
                     a = b;
                     b = bb;
                 } else {
                     b = bb;
                     continue;
                 }
                 a.next = null;
             }
             return dummy.next;
        }
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(3);
        ListNode b = new ListNode(3);
        ListNode c = new ListNode(4);
        ListNode d = new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;
        System.out.println(new L82().deleteDuplicates(a));
    }
}
