package sulqn;

/*
给定一个单链表的头节点  head ，其中的元素 按升序排序 ，将其转换为平衡二叉搜索树。
 */

public class L109 {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;

        ListNode p = head;
        int len = 0;
        while (p != null) {
            len++;
            p = p.next;
        }
        int[] arr = new int[len];
        p = head;
        int id = 0;
        while (p != null) {
            arr[id++] = p.val;
            p = p.next;
        }

        return construct(arr, 0, len - 1);
    }

    public TreeNode construct(int[] arr, int left, int right) {
        if (left > right) {
            return null;
        } else if (left == right) {
            TreeNode node = new TreeNode(arr[left]);
            return node;
        } else {
            int mid = (left + right) / 2;
            TreeNode l = construct(arr, left, mid - 1);
            TreeNode r = construct(arr, mid + 1, right);
            TreeNode node = new TreeNode(arr[mid]);
            node.left = l;
            node.right = r;
            return node;
        }
    }
}
