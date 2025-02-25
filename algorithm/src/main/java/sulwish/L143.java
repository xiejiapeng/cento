package sulwish;

public class L143 {
    public void reorderList(ListNode head) {
        ListNode a = head;
        ListNode b = head;
        while(b != null && b.next != null) {
            a = a.next;
            b = b.next.next;
        }
        print(a);
        ListNode rev = reverse(a);
        System.out.println("----");
        print(rev);
        ListNode p = head;
        ListNode q = rev;

        while(true) {
            ListNode tail = q;
            ListNode tmp1 = p.next;
            if(tmp1 == a)tmp1 = null; //todo 注意这里的死循环
            ListNode tmp2 = q.next;
            p.next = q;
            q.next = tmp1;
            p = tmp1;
            q = tmp2;

            if(tmp1 == null) {
                if(tmp2 != null) {
                    tail.next = tmp2;
                }
                break;
            }
        }
        print(a);

    }

    public ListNode reverse(ListNode head) {
        ListNode tail = null;
        ListNode a = head;
        ListNode ret = head;
        while(a != null) {
            ret = a;
            ListNode b = a.next;
            a.next = tail;
            tail = a;
            a = b;
        }
        return ret;
    }

    public void print(ListNode a) {
        while(a != null) {
            System.out.println(a.val + ",");
            a = a.next;
        }
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);

        a.next = b;
        b.next = c;
        c.next = d;
        new L143().reorderList(a);

    }
}
