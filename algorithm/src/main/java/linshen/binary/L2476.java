package linshen.binary;

import sulqn.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
给你一个 二叉搜索树 的根节点 root ，和一个由正整数组成、长度为 n 的数组 queries 。

请你找出一个长度为 n 的 二维 答案数组 answer ，其中 answer[i] = [mini, maxi] ：

mini 是树中小于等于 queries[i] 的 最大值 。如果不存在这样的值，则使用 -1 代替。
maxi 是树中大于等于 queries[i] 的 最小值 。如果不存在这样的值，则使用 -1 代替。

树中节点的数目在范围 [2, 105] 内
1 <= Node.val <= 106
n == queries.length
1 <= n <= 105
1 <= queries[i] <= 106
返回数组 answer 。

todo not finished，可能不是平衡二叉树，需要保存到数组中再查询
 */

public class L2476 {
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < queries.size(); i++){
            List<Integer> a = new ArrayList<>();
            int target = queries.get(i);
            a.add(findLargest(root, target));
            a.add(findSmallest(root, target));
            ans.add(a);
        }
        return ans;
    }

    private int findLargest(TreeNode root, int target) {
        if(root == null)return -1;
        if(root.val <= target) {
            int u = findLargest(root.right, target);
            if(u != -1)return u;
            else return root.val;
        } else {
            return findLargest(root.left, target);
        }
    }

    private int findSmallest(TreeNode root, int target) {
        if(root == null)return -1;
        else if(root.val >= target) {
            int u = findSmallest(root.left, target);
            if (u != -1) return u;
            else return root.val;
        } else {
            return findSmallest(root.right, target);
        }
    }
}
