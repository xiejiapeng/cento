package sulwish;

import sulqn.ListNode;

public class L92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode a = null;
        ListNode aa = null;
        ListNode b = null;
        ListNode p = head;
        ListNode q = dummy;
        int pos = 1;
        while (p != null){
            if(pos == left) {
                a = p;
                aa = q;
            }
            if(pos == right) {
                b = p;
            }
            p = p.next;
            q = q.next;
            pos++;
        }

        aa.next = null;
        ListNode tail = b.next;
        b.next = null;

        reverse(a);

        aa.next = b;
        a.next = tail;
        return dummy.next;


    }

    public void reverse(ListNode x) {
        if(x == null || x.next == null)return;
        else {
            ListNode a = x;
            ListNode b = x.next;
            a.next = null;
            //... a<-b c->...
            while (b != null) {
                ListNode c = b.next;
                b.next = a;
                a = b;
                b = c;
            }
        }
    }
}
