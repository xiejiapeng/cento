package linshen.binarytree;

import sulqn.TreeNode;

import java.util.LinkedList;

/*
给你一棵 二叉树 的根节点 root ，这棵二叉树总共有 n 个节点。每个节点的值为 1 到 n 中的一个整数，且互不相同。
给你一个整数 startValue ，表示起点节点 s 的值，和另一个不同的整数 destValue ，表示终点节点 t 的值。

请找到从节点 s 到节点 t 的 最短路径 ，并以字符串的形式返回每一步的方向。每一步用 大写 字母 'L' ，'R' 和 'U' 分别表示一种方向：

'L' 表示从一个节点前往它的 左孩子 节点。
'R' 表示从一个节点前往它的 右孩子 节点。
'U' 表示从一个节点前往它的 父 节点。
请你返回从 s 到 t 最短路径 每一步的方向。
 */

public class L2096 {
    TreeNode ans = null;

    public String getDirections(TreeNode root, int startValue, int destValue) {
        findCommon(root, startValue, destValue);
        System.out.println(ans.val);
        LinkedList<Integer> ls = find(ans, startValue, new LinkedList<>());
        LinkedList<Integer> rs = find(ans, destValue, new LinkedList<>());
        StringBuilder sb = new StringBuilder();
        for (int x : ls) {
            sb.append("U");
        }
        for (int x : rs) {
            if (x == -1) sb.append("L");
            else sb.append("R");
        }
        return sb.toString();
    }

    private TreeNode findCommon(TreeNode root, int p, int q) {
        if (root == null) return null;
        TreeNode l = findCommon(root.left, p, q);
        TreeNode r = findCommon(root.right, p, q);
        if (root.val == p || root.val == q) {

            if ((root.val == p && l != null && l.val == q) || (root.val == p && r != null && r.val == q) || (root.val == q && l != null && l.val == p) || (root.val == q && r != null && r.val == p)) {
                ans = root;
            }
            return root;
        } else {
            if (l != null && r != null) {
                ans = root;
                return null;
            } else if (l != null) {
                return l;
            } else return r;
        }
    }

    private LinkedList<Integer> find(TreeNode root, int x, LinkedList<Integer> path) {
        if (root == null) return null;
        if (root.val == x) {
            return new LinkedList<>(path);
        } else {
            path.addLast(-1);
            LinkedList<Integer> l = find(root.left, x, path);
            path.removeLast();
            path.addLast(1);
            LinkedList<Integer> r = find(root.right, x, path);
            path.removeLast();
            if (l == null && r == null) return null;
            else if (l != null) return l;
            else return r;
        }
    }
}
