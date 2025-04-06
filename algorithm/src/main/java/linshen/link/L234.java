package linshen.link;

import sulqn.ListNode;

import java.util.ArrayList;
import java.util.List;

/*
给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。
如果是，返回 true ；否则，返回 false 。
 */

public class L234 {
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode p = head;
        while (p != null){
            list.add(p.val);
            p = p.next;
        }
        int i = 0;
        int j = list.size() - 1;
        while (i <= j) {
            if(list.get(i) != list.get(j))return false;
            i++;
            j--;
        }
        return true;
    }

}
