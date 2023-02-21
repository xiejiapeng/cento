package solutions;

public class L92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == right)return head;
        ListNode dummy = new ListNode();
        dummy.next = head;

        int idx = 1;
        ListNode p = head;
        ListNode last = dummy;
        while(p != null){
            if(idx == left) {
                last.next = null;
                ListNode x = p;
                ListNode y = p.next;
                x.next = null;
                while(y != null){
                    ListNode tmp = y.next;
                    y.next = x;
                    x = y;
                    y = tmp;
                    if(idx + 1 == right)break;
                    idx++;
                }
                last.next = x;
                p.next = y;
                break;
            }  else {
                last.next = p;
                last = p;
                p = p.next;
                idx++;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(3);
        ListNode b = new ListNode(5);
        a.next = b;
        System.out.println(new L92().reverseBetween(a,1,1));
    }
}
