package linshen.binarytree;

import sulqn.TreeNode;

import java.util.*;

/*
给你一个 二叉搜索树 的根节点 root ，和一个由正整数组成、长度为 n 的数组 queries 。
请你找出一个长度为 n 的 二维 答案数组 answer ，其中 answer[i] = [mini, maxi] ：

mini 是树中小于等于 queries[i] 的 最大值 。如果不存在这样的值，则使用 -1 代替。
maxi 是树中大于等于 queries[i] 的 最小值 。如果不存在这样的值，则使用 -1 代替。
返回数组 answer 。
提示：

树中节点的数目在范围 [2, 105] 内
1 <= Node.val <= 106
n == queries.length
1 <= n <= 105
1 <= queries[i] <= 106
 */

public class L2476 {
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<Integer> nums = flat(root);
        Collections.sort(queries);
        int i = 0;
        Map<Integer, List<Integer>> ans = new HashMap<>();
        for (int q : queries) {
            ans.putIfAbsent(q, new ArrayList<>());
            int tmp = -1;
            while (i < nums.size() && nums.get(i) <= q) {
                tmp = nums.get(i);
                i++;
            }
            ans.get(q).add(tmp);
        }

        int j = nums.size()-1;
        for (int k = queries.size() - 1; k > -1; k--){
            int q = queries.get(k);
            int tmp = -1;
            while (j > -1 && nums.get(j) >= q) {
                tmp = nums.get(j);
                j--;
            }
            ans.get(q).add(tmp);
        }
        List<List<Integer>> ret = new ArrayList<>();
        for (int x : queries){
            ret.add(ans.get(x));
        }
        return ret;
    }
    private List<Integer> flat(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null)return ans;
        else {
            List<Integer> a = flat(root.left);
            a.add(root.val);
            List<Integer> b = flat(root.right);
            a.addAll(b);
            return a;
        }
    }
}
