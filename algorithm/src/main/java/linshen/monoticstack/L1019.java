package linshen.monoticstack;

import sulqn.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class L1019 {
    public int[] nextLargerNodes(ListNode head) {
        ListNode x = reverse(head);
        LinkedList<Integer> ans = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        while (x != null) {
            while (!stack.isEmpty() && stack.peek() <= x.val) stack.pop();
            if(stack.isEmpty())ans.addFirst(0);
            else ans.addFirst(stack.peek());
            stack.add(x.val);
            x = x.next;
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }

    private ListNode reverse(ListNode head) {
        if(head.next == null)return head;
        else {
            ListNode x = head.next;
            ListNode y = reverse(x);
            x.next = head;
            head.next = null;
            return y;
        }
    }
}
