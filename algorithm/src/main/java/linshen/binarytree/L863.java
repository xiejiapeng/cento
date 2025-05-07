package linshen.binarytree;

import sulqn.TreeNode;

import java.util.*;

/*
给定一个二叉树（具有根结点 root）， 一个目标结点 target ，
和一个整数值 k ，返回到目标结点 target 距离为 k 的所有结点的值的数组。
答案可以以 任何顺序 返回。
 */

public class L863 {
    Map<TreeNode, TreeNode> parent = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        dfs(root);
        List<Integer> ans = new ArrayList<>();
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        visited.add(target);
        int dist = -1;
        while (!queue.isEmpty()){
            int n = queue.size();
            dist++;
            while (n-->0){
                TreeNode x = queue.poll();
                if(dist == k)ans.add(x.val);
                if(x.left != null && !visited.contains(x.left)){
                    queue.add(x.left);
                    visited.add(x.left);
                }
                if(x.right != null && !visited.contains(x.right)){
                    queue.add(x.right);
                    visited.add(x.right);
                }
                if(parent.containsKey(x)  && !visited.contains(parent.get(x))){
                    queue.add(parent.get(x));
                    visited.add(parent.get(x));
                }
            }
        }
        return ans;
    }

    private void dfs(TreeNode root) {
        if(root == null)return;
        else {
            if(root.left != null) {
                parent.put(root.left, root);
                dfs(root.left);
            }
            if(root.right != null) {
                parent.put(root.right, root);
                dfs(root.right);
            }
        }
    }
}
