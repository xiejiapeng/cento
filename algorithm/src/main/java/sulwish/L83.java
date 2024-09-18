package sulwish;

import sulqn.ListNode;

/*
给定一个已排序的链表的头 head ， 删除所有重复的元素，
使每个元素只出现一次 。返回 已排序的链表 。


 */

public class L83 {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)return head;
        else {
            ListNode a = head;
            ListNode b = head.next;
            while (b != null){
                while (b != null && b.val == a.val) {
                    b = b.next;
                }
                a.next = b;
                if(b != null){
                    a = b;
                    b = b.next;
                }
            }
            return head;
        }
    }
}
