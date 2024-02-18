package sulwish;

import sulqn.ListNode;

public class L142 {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode p = head;
        ListNode q = head;
        while (true) {
            p = p.next;
            if (q.next == null || q.next.next == null) return null;
            q = q.next.next;
            if (p == q) break;
        }
        p = head;
        while (p != q) {
            p = p.next;
            q = q.next;
        }
        return p;
    }
}
