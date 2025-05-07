package linshen.binarytree;

import sulqn.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
树的 最大宽度 是所有层中最大的 宽度 。
每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。
将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。

题目数据保证答案将会在 32 位 带符号整数范围内。
 */

public class L662 {
    Map<Integer, Long> min = new HashMap<>();
    Map<Integer, Long> max = new HashMap<>();
    public int widthOfBinaryTree(TreeNode root) {
        dfs(root, 1, 0);
        int m = Integer.MIN_VALUE;
        for (int h : min.keySet()) {
            long a = min.get(h);
            long b = max.get(h);
            m = Math.max(m, (int)(b-a+1));
        }
        return m;
    }

    private void dfs(TreeNode root, int h, long id) {
        if(min.containsKey(h)) {
            min.put(h, Math.min(min.get(h), id));
        } else {
            min.put(h, id);
        }
        if(max.containsKey(h)) {
            max.put(h, Math.max(max.get(h), id));
        } else {
            max.put(h, id);
        }
        if(root.left != null) {
            dfs(root.left, h+1, id * 2);
        }
        if(root.right != null) {
            dfs(root.right, h+1, id * 2 + 1);
        }
    }
}
