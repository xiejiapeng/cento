package sulqn;

/*
给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。

a -> b -> c -> d

a -> c -> d
 b -> c -> d
 */

public class L24 {
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;
        System.out.println(new L24().swapPairs(a));
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        if (head == null || head.next == null) {
            return head;
        } else {
            ListNode a = dummy;
            ListNode b = head;
            ListNode c = head.next;

            while (b != null && c != null) {
                ListNode d = c.next;
                a.next = c;
                c.next = b;
                b.next = d;
                if (d == null) break;
                a = b;
                b = d;
                c = d.next;
            }

            return dummy.next;
        }
    }
}
