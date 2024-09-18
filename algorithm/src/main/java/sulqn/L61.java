package sulqn;

/*
给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
1 2 3(a) 4(b) 5
3 2 1
5 4
 */

public class L61 {
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
//        ListNode f = new ListNode(6);
//        ListNode g = new ListNode(7);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
//        e.next = f;
//        f.next = g;

        System.out.println(new L61().rotateRight(a, 2));
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        int len = 0;
        ListNode p = head;

        while (p.next != null) {
            p = p.next;
            len++;
        }
        len++;
        ListNode tail = p;
        k %= len;
        if (k == 0) return head;
        else {
            int r = k;
            ListNode dummy = new ListNode(-1);
            dummy.next = head;

            ListNode a = dummy;
            ListNode b = head;
            ListNode c = head;
            while (r-- > 0) {
                c = c.next;
            }
            while (c != null) {
                a = a.next;
                b = b.next;
                c = c.next;

            }
            //1 2 3 4 5(a) 6(b) 7
            //3 2 1
            //5 4
            a.next = null;
            ListNode x = reverse(head);
            ListNode y = reverse(b);
            head.next = tail;
            return reverse(x);
        }
    }

    private ListNode reverse(ListNode x) {
        if (x == null || x.next == null) return x;
        else {
            //... <- a) (-> b -> c ...)
            ListNode a = null;
            ListNode b = x;
            while (b != null) {
                ListNode c = b.next;
                b.next = a;
                a = b;
                b = c;
            }
            return a;
        }
    }
}
