package index.binarytree;

import sulqn.ListNode;
import sulqn.TreeNode;

/*
给定一个单链表的头节点  head ，其中的元素 按升序排序 ，将其转换为
平衡
 二叉搜索树。
 */

public class L109 {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }
        int mid = len / 2;
        TreeNode last = null;
        p = head;
        int id = 0;
        while (id != mid) {
            TreeNode x = new TreeNode(p.val);
            x.left = last;
            last = x;
            id++;
            p = p.next;
        }
        TreeNode root = new TreeNode(p.val);
        root.left = last;
        last = root;
        p = p.next;
        while (p != null) {
            TreeNode x = new TreeNode(p.val);
            last.right = x;
            last = x;
            p = p.next;
        }
        return root;
    }
}
