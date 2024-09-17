package sulqn;

public class L19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode a = dummy;
        ListNode b = head;
        ListNode c = head;
        for (int i = 0; i < n; i++) {
            c = c.next;
        }

        while (c != null) {
            a = a.next;
            b = b.next;
            c = c.next;
        }
        a.next = b.next;
        return dummy.next;
    }
}
