package solutions;

public class L25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode pre = dummy;

        while(pre != null){
            ListNode a = pre.next;
            ListNode b = pre.next;
            for(int i = 0; i < k - 1; i++){
                if(b == null)break;
                else{
                    b = b.next;
                }
            }
            if(b == null)break;
            if(a == b)break;

            ListNode tmp = b.next;
            ListNode x = a;

            ListNode y = a.next;
            a.next = null;
            while(true){
                ListNode z = y.next;
                y.next = x;
                x = y;
                y = z;
                if(x == b)break;
            }
            a.next = tmp;
            pre.next = b;
            pre = a;

        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        ListNode ans = new L25().reverseKGroup(l1, 4);
        while (ans != null){
            System.out.println(ans.val);
            ans = ans.next;
        }
    }
}
