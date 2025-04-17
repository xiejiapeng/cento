package linshen.binarytree;

import sulqn.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，
聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。

给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 */

public class L337 {
    Map<TreeNode, Integer> ans = new HashMap<>();
    public int rob(TreeNode root) {
        if(root == null)return 0;
        else {
            if(ans.containsKey(root))return ans.get(root);
            //stole root
            int x = root.val;
            if(root.left != null) {
                x += rob(root.left.left);
                x += rob(root.left.right);
            }
            if(root.right != null) {
                x += rob(root.right.left);
                x += rob(root.right.right);
            }

            //don't stole root
            int y = 0;
            y += rob(root.left);
            y += rob(root.right);
            int a = Math.max(x, y);
            ans.put(root, a);
            return a;
        }
    }
}
