package sulwish;

import sulqn.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
给你一棵二叉树的根节点 root ，返回树的 最大宽度 。

树的 最大宽度 是所有层中最大的 宽度 。

每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。

题目数据保证答案将会在  32 位 带符号整数范围内。
 */

public class L662 {
    Map<Integer, Long> min = new HashMap<>();
    Map<Integer, Long> max = new HashMap<>();
    public int widthOfBinaryTree(TreeNode root) {
        long ans = 1;
        dfs(root, 0, 0);
        for(int row : min.keySet()) {
            long width = max.get(row) - min.get(row) + 1;
            ans = Math.max(ans, width);
        }

        System.out.println(min);
        System.out.println(max);
        return (int)ans;
    }

    private void dfs(TreeNode cur, int row, long col) {
        if(min.containsKey(row)) {
            min.put(row, Math.min(col, min.get(row)));
        } else {
            min.put(row, col);
        }

        if(max.containsKey(row)) {
            max.put(row, Math.max(col, max.get(row)));
        } else {
            max.put(row, col);
        }

        if(cur.left != null){
            dfs(cur.left, row + 1, 2 * col);
        }
        if(cur.right != null) {
            dfs(cur.right, row + 1, 2 * col + 1);
        }
    }
}
