package linshen.link;

import sulqn.ListNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
给你一个整数数组 nums 和一个链表的头节点 head。
从链表中移除所有存在于 nums 中的节点后，返回修改后的链表的头节点。
 */

public class L3217 {
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> rm = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;
        ListNode q = head;
        while (q != null) {
            if(rm.contains(q.val)) {
                p.next = q.next;
            } else {
                p = p.next;
            }
            q = q.next;
        }
        return dummy.next;
    }
}
