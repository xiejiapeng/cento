package sulwish;

import sulqn.ListNode;

public class L19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode p = dummy;
        ListNode r = head;
        for (int i = 0; i < n; i++) {
            r = r.next;
        }
        while (r != null) {
            p = p.next;
            r = r.next;
        }
        p.next = p.next.next;
        return dummy.next;
    }
}
