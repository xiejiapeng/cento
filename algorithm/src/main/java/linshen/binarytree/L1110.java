package linshen.binarytree;

import sulqn.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
给出二叉树的根节点 root，树上每个节点都有一个不同的值。
如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
返回森林中的每棵树。你可以按任意顺序组织答案。
树中的节点数最大为 1000。
每个节点都有一个介于 1 到 1000 之间的值，且各不相同。
to_delete.length <= 1000
to_delete 包含一些从 1 到 1000、各不相同的值。
 */

public class L1110 {
    List<TreeNode> ans = new ArrayList<>();
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        dfs(root, null, Arrays.stream(to_delete).boxed().collect(Collectors.toSet()));
        return ans;
    }

    private void dfs(TreeNode root, TreeNode parent, Set<Integer> to_delete) {
        if(to_delete.contains(root.val) && parent != null) {
            if(root == parent.left)parent.left = null;
            else parent.right = null;
        } else {
            //todo m: 注意额外判断
            if(!to_delete.contains(root.val) && parent == null)ans.add(root);
        }
        if(root.left != null) {
            //todo m: 注意额外判断
            if(to_delete.contains(root.val) && !to_delete.contains(root.left.val))ans.add(root.left);
            dfs(root.left, root, to_delete);
        }
        if(root.right != null) {
            //todo m: 注意额外判断
            if(to_delete.contains(root.val) && !to_delete.contains(root.right.val))ans.add(root.right);
            dfs(root.right, root, to_delete);
        }
    }
}
