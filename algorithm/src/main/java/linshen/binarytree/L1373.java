package linshen.binarytree;

import sulqn.TreeNode;

import java.util.Arrays;
import java.util.List;

/*
给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。

二叉搜索树的定义如下：

任意节点的左子树中的键值都 小于 此节点的键值。
任意节点的右子树中的键值都 大于 此节点的键值。
任意节点的左子树和右子树都是二叉搜索树。
提示：

每棵树有 1 到 40000 个节点。
每个节点的键值在 [-4 * 10^4 , 4 * 10^4] 之间。
 */

public class L1373 {
    int amax = 0;
    public int maxSumBST(TreeNode root) {
        dfs(root);
        return amax;
    }

    //包容root在内的min, max, sum
    private List<Integer> dfs(TreeNode root) {
        if(root == null){
            return Arrays.asList(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 1);
        }
        if(root.left == null && root.right == null) {
            amax = Math.max(amax, root.val);
            return Arrays.asList(root.val, root.val, root.val, 1);
        } else {
            int min = root.val;
            int max = root.val;
            int sum = root.val;
            List<Integer> l = dfs(root.left);
            List<Integer> r = dfs(root.right);
            boolean search = (l.get(3) == 1) && (r.get(3) == 1) && (l.get(1) < root.val) && (r.get(0) > root.val);
            if(search) amax = Math.max(amax, sum + l.get(2) + r.get(2));
//            if(root.val == 2) {
//                System.out.println("l="+l+",r="+r+"search="+search+",amax="+amax);
//            }
            return Arrays.asList(
              Math.min(min, Math.min(l.get(0), r.get(0))),
              Math.max(max, Math.max(l.get(1), r.get(1))),
              sum + l.get(2) + r.get(2),
              search ? 1 : 0
            );
        }
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(-4);
        TreeNode b = new TreeNode(-2);
        a.left = b;
        System.out.println(new L1373().maxSumBST(a));
    }
}
