package sulwish;

import sulqn.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：

二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
 */

public class L1609 {
    Map<Integer, Integer> last = new HashMap<>();

    public boolean isEvenOddTree(TreeNode root) {
        return dfs(root, 0);
    }

    private boolean dfs(TreeNode node, int level) {
        if (level % 2 == 0 && node.val % 2 == 0) return false;
        if (level % 2 != 0 && node.val % 2 != 0) return false;
        boolean success = true;
        if (last.containsKey(level)) {
            int l = last.get(level);
            if (level % 2 == 0) success &= (node.val > l);
            else success &= (node.val < l);
            last.put(level, node.val);
        } else {
            last.put(level, node.val);
        }

        if (node.left != null) success &= dfs(node.left, level + 1);
        if (!success) return false;
        if (node.right != null) success &= dfs(node.right, level + 1);
        return success;
    }


}
