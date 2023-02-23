package solutions;

public class L147 {
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null)return head;
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = head;
        ListNode q = head.next;
        p.next = null;
        while(q != null){
            ListNode qq = q.next;
            ListNode pp = q;

            q.next = null;
            ListNode x = dummy;
            ListNode y = dummy.next;
            while(y != null) {
                if(y.val >= q.val){
                    x.next = q;
                    q.next = y;
                    break;
                } else {
                    x = x.next;
                    y = y.next;
                }

            }
            if(y == null){
                x.next = q;
            }
            q = qq;
            p = pp;

        }
        return dummy.next;
    }

    private ListNode merge(ListNode a, ListNode b) {
        if(a == null && b == null)return null;
        else if(a == null)return b;
        else if(b == null)return a;
        else {
            if(a.val <= b.val){
                a.next = merge(a.next, b);
                return a;
            } else {
                b.next = merge(a, b.next);
                return b;
            }
        }
    }





    public static void main(String[] args) {
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(1);
        a.next = b;
        System.out.println(new L147().insertionSortList(a));
    }
}
