package linshen.link;

import sulqn.ListNode;

import java.util.LinkedList;
import java.util.Stack;

/*
给定一个长度为 n 的链表 head

对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，
找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。

返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。
如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。
 */

public class L1019 {
    public int[] nextLargerNodes(ListNode head) {
        LinkedList<Integer> l = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        ListNode p = reverse(head);
        while (p != null) {
            while (!stack.isEmpty() && stack.peek() <= p.val)stack.pop();
            if(stack.isEmpty())l.addFirst(0);
            else l.addFirst(stack.peek());
            stack.add(p.val);
            p = p.next;
        }
        return l.stream().mapToInt(i -> i).toArray();
    }

    private ListNode reverse(ListNode head) {
        if(head == null || head.next == null)return head;
        else {
            ListNode next = head.next;
            head.next = null;
            ListNode p = reverse(next);
            next.next = head;
            return p;
        }
    }
}
