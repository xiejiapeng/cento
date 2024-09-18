package sulqn;

import java.util.ArrayList;
import java.util.List;

/*
给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 */

public class L95v2 {
    public static void main(String[] args) {
        System.out.println(new L95v2().generateTrees(4));
    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[][] ans = new List[n + 1][n + 1];
        for (int len = 1; len <= n; len++) {
            if (len == 4) {
//                System.out.println("h");
            }
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                ans[i][j] = new ArrayList<>();

                if (len == 1) {
                    ans[i][j].add(new TreeNode(i));
                } else {
                    for (int k = i; k <= j; k++) {
                        if (k == i) {
                            for (TreeNode r : ans[k + 1][j]) {
                                TreeNode root = new TreeNode(k);
                                root.left = null;
                                root.right = r;
                                ans[i][j].add(root);
                            }
                        } else if (k == j) {
                            for (TreeNode l : ans[i][k - 1]) {
                                TreeNode root = new TreeNode(k);
                                root.left = l;
                                root.right = null;
                                ans[i][j].add(root);
                            }
                        } else {
                            List<TreeNode> left = ans[i][k - 1];
                            List<TreeNode> right = ans[k + 1][j];
                            for (TreeNode l : left) {
                                for (TreeNode r : right) {
                                    TreeNode root = new TreeNode(k);
                                    root.left = l;
                                    root.right = r;
                                    ans[i][j].add(root);
                                }
                            }
                        }

                    }
                }
            }
        }
        return ans[1][n];
    }
}
